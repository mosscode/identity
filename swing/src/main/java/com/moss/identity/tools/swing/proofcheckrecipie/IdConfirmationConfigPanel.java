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
package com.moss.identity.tools.swing.proofcheckrecipie;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.moss.identity.IdProofCheckRecipe;
import com.moss.identity.IdProofRecipie;

@SuppressWarnings("serial")
public class IdConfirmationConfigPanel extends JPanel {
	
	private final IdConfirmationConfigPanelView view = new IdConfirmationConfigPanelView();
	private JComboBox selector = view.loginMethodSelector();
	
	private final List<TypeOption> options = new LinkedList<TypeOption>();
//	private final List<ActionListener> listeners = new LinkedList<ActionListener>();
	private TypeOption selection;
	
	
	private static class TypeOption {
		final IdConfirmationConfigPanelPlugin plugin;

		public TypeOption(IdConfirmationConfigPanelPlugin plugin) {
			super();
			this.plugin = plugin;
		}
		
		@Override
		public String toString() {
			return plugin.typeName();
		}
	}
	
	public IdConfirmationConfigPanel(IdConfirmationConfigPanelPlugin ... plugins) {
		setLayout(new BorderLayout());
		add(view);
		
		if(plugins==null || plugins.length==0){
			throw new IllegalStateException("You must have at least one plugin.");
		}
		
		for(IdConfirmationConfigPanelPlugin plugin : plugins){
			TypeOption option = new TypeOption(plugin);
			selector.addItem(option);
			this.options.add(option);
		}
		
		selector.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				System.out.println("Selection changed");
				updateTypeSelection();
			}
		});
		
		selector.setSelectedItem(options.get(0));
		updateTypeSelection();
	}
	
//	public void add(ActionListener listener){
//		this.listeners.add(listener);
//	}
	
	public boolean selectionIsValid(){
		if(selection==null){
			return true;
		}else{
			try {
				selection.plugin.getValue();
				return true;
			} catch (Exception e) {
				return false;
			}	
		}
	}
	
	public boolean hasErrors(){
		return selection.plugin.hasErrors();
	}
	
	public IdProofCheckRecipe getValue(){
		return selection.plugin.getValue();
	}
	
	public IdProofRecipie getProofRecipie(){
		return selection.plugin.proofRecipie();
	}
	
	public void setSelection(IdProofCheckRecipe value){
		throw new RuntimeException("NOT IMPLEMENTED");
	}
	
	private void updateTypeSelection(){
		
		TypeOption newSelection = (TypeOption) selector.getSelectedItem();
		

		if(newSelection==this.selection){
			return;// nothing changed
		}else{
			this.selection = newSelection;
			view.holderPanel().removeAll();
			view.holderPanel().setLayout(new BorderLayout());
			view.holderPanel().add(this.selection.plugin.view());
			view.holderPanel().invalidate();
			view.validate();
			view.repaint();
		}
		
	}
}
