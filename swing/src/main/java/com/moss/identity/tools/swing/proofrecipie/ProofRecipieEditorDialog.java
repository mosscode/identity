/**
 * Copyright (C) 2013, Moss Computing Inc.
 *
 * This file is part of identity.
 *
 * identity is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * identity is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with identity; see the file COPYING.  If not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library.  Thus, the terms and
 * conditions of the GNU General Public License cover the whole
 * combination.
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent
 * modules, and to copy and distribute the resulting executable under
 * terms of your choice, provided that you also meet, for each linked
 * independent module, the terms and conditions of the license of that
 * module.  An independent module is a module which is not derived from
 * or based on this library.  If you modify this library, you may extend
 * this exception to your version of the library, but you are not
 * obligated to do so.  If you do not wish to do so, delete this
 * exception statement from your version.
 */
package com.moss.identity.tools.swing.proofrecipie;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.moss.identity.IdProofRecipie;
import com.moss.swing.dialog.DialogablePanel;
import com.moss.swing.dialog.DialogablePanel.ExitMode;
import com.moss.swing.dialog.YesNoBar;

/**
 * A simple dialog wrapper around ProofRecipieEditor
 */
public class ProofRecipieEditorDialog extends DialogablePanel {
	public static interface Callback {
		void cancelled();
		boolean selected(IdProofRecipie r);
	}
	private final ProofRecipieEditor editor;
	private final Callback callback;
	
	public ProofRecipieEditorDialog(ExitMode exitMode, final ProofRecipieEditor editor, final Callback callback) {
		super(exitMode);
		this.editor = editor;
		this.callback = callback;
		setLayout(new BorderLayout());
		add(editor);
		

		final YesNoBar ynBar = new YesNoBar(
				new AbstractAction("ok") {
					public void actionPerformed(ActionEvent e) {
						if(editor.hasErrors()){
							JOptionPane.showMessageDialog(editor, "There are unresolved errors with the supplied information.");
						}else if(editor.getValue()==null){
							JOptionPane.showMessageDialog(editor, "You have to specify an identity");
						}else{
							if(callback.selected(editor.getValue())){
								dispose();
							}
						}
					}
				},
				new AbstractAction("Cancel") {
					public void actionPerformed(ActionEvent e) {
						callback.cancelled();
						dispose();
					}
				}
		);
		
		JPanel ynHolder = new JPanel(new BorderLayout());
		ynHolder.add(ynBar, BorderLayout.EAST);
		add(ynHolder, BorderLayout.SOUTH);
	}
	
//	@Deprecated
//	public void show(String title, IdProofRecipie initialValue, Component parent){
//		
//		if(initialValue!=null){
//			editor.setSelection(initialValue);
//		}
//	
//		
//		
//		JDialog dialog = holder.makeDialogFor(parent);
//		dialog.pack();
//		if(dialog.getWidth()>640){
//			dialog.setSize(640, dialog.getHeight());
//		}
//		dialog.setLocationRelativeTo(parent);
//		dialog.setTitle(title);
//		dialog.setVisible(true);
//	}
}
