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
package com.moss.identity.simple.swing;

import java.awt.Component;
import java.util.LinkedList;
import java.util.List;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.moss.identity.IdProofRecipie;
import com.moss.identity.simple.SimpleId;
import com.moss.identity.standard.PasswordProofRecipie;
import com.moss.identity.tools.swing.proofrecipie.ProofRecipieEditorPlugin;

public class SimpleProofRecipieEditor implements ProofRecipieEditorPlugin<PasswordProofRecipie> {
	private final boolean allowEmptyPasswords;
	
	private final SimpleProofRecipieEditorPluginView view = new SimpleProofRecipieEditorPluginView();
	
	public SimpleProofRecipieEditor(final boolean allowEmptyPasswords) {
		view.errorsField().setVisible(false);

		this.allowEmptyPasswords = allowEmptyPasswords;
		
		DocumentListener l = new DocumentListener() {
			
			public void removeUpdate(DocumentEvent e) {
				validate();
			}
			
			public void insertUpdate(DocumentEvent e) {
				validate();
			}
			
			public void changedUpdate(DocumentEvent e) {
				validate();
			}
			
		};
		view.nameField().getDocument().addDocumentListener(l);
		view.passwordField().getDocument().addDocumentListener(l);
		validate();
	}
	

	
	private List<String> errors = new LinkedList<String>();
	
	public void showErrors() {
		view.errorsField().setVisible(true);
	}
	
	private void validate(){
		errors.clear();
		{
			String text = view.nameField().getText();
			if(text==null || text.trim().length()==0){
				errors.add("You must supply an ID");
			}
		}
		{	
			String text = view.passwordField().getText();
			if(!allowEmptyPasswords && text==null || text.trim().length()==0){
				errors.add("You must supply a password");
			}
		}
		
		StringBuilder text = new StringBuilder();
		for(String next : errors){
			text.append(next);
			text.append('\n');
		}
		view.errorsField().setText(text.toString());
	}
	
	public boolean hasErrors() {
		return errors.size()>0;
	}
	
	public Component view() {
		return view;
	}
	
	public void setValue(PasswordProofRecipie value) {
		view.nameField().setText(value.id().toString());
		view.passwordField().setText(value.password().toString());
	}
	
	public String typeName() {
		return "Basic ID+Password";
	}
	public PasswordProofRecipie getValue(){
		return new PasswordProofRecipie(new SimpleId(view.nameField().getText()), view.passwordField().getText());
	}
	public boolean canHandle(IdProofRecipie r) {
		return r instanceof PasswordProofRecipie && r.id() instanceof SimpleId;
	}
}
