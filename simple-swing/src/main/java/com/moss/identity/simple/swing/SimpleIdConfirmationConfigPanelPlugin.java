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

import com.moss.identity.IdProofCheckRecipe;
import com.moss.identity.IdProofRecipie;
import com.moss.identity.simple.SimpleId;
import com.moss.identity.standard.PasswordIdProofCheckRecipe;
import com.moss.identity.standard.PasswordProofRecipie;
import com.moss.identity.tools.swing.proofcheckrecipie.IdConfirmationConfigPanelPlugin;
import com.moss.swing.event.DocumentAdapter;

public class SimpleIdConfirmationConfigPanelPlugin implements IdConfirmationConfigPanelPlugin {

	private final SimpleProofRecipieEditorPluginView view = new SimpleProofRecipieEditorPluginView();
	private final List<String> errors = new LinkedList<String>();
	
	private String logon;
	private String password;
	
	public SimpleIdConfirmationConfigPanelPlugin() {
		view.errorsField().setVisible(false);
		view.nameField().getDocument().addDocumentListener(new DocumentAdapter(){
			@Override
			public void updateHappened() {
				logon = view.nameField().getText();
				validate();
			}
		});
		view.passwordField().getDocument().addDocumentListener(new DocumentAdapter(){
			@Override
			public void updateHappened() {
				password = view.passwordField().getText();
				validate();
			}
		});
		
		validate();
	}
	
	private void validate(){
		errors.clear();
		
		if(logon==null || logon.trim().length()==0){
			errors.add("You must specify a logon");
		}
		if(password==null || password.trim().length()==0){
			errors.add("You must specify a password");
		}
		
		StringBuilder text = new StringBuilder();
		for(String next : errors){
			text.append(next);
			text.append('\n');
		}
		view.errorsField().setText(text.toString());
	}
	
	public String typeName() {
		return "simple logon + password";
	}
	
	public Component view() {
		return view;
	}
	
	public boolean hasErrors() {
		return errors.size()>0;
	}
	
	public void showErrors() {
		view.errorsField().setVisible(true);
		view.invalidate();
		view.repaint();
	}
	
	public PasswordIdProofCheckRecipe getValue() {
		String logonText = view.nameField().getText().trim();
		if(logonText.length()==0){
			return null;
		}else{
			return new PasswordIdProofCheckRecipe(new SimpleId(view.nameField().getText()), view.nameField().getText());
		}
	}
	
	public IdProofRecipie proofRecipie() {
		PasswordIdProofCheckRecipe checkRecipie = getValue();
		
		if(checkRecipie==null){
			return null;
		}else{
			return new PasswordProofRecipie(checkRecipie.id(), checkRecipie.password());
		}
	}
	
	public void setValue(IdProofCheckRecipe value) {
		if(value instanceof PasswordIdProofCheckRecipe){
			PasswordIdProofCheckRecipe v = (PasswordIdProofCheckRecipe) value;
			view.nameField().setText(v.id().toString());
			view.passwordField().setText(v.password());
		}else{
			throw new RuntimeException(value.getClass().getName() + " is not a " + IdProofCheckRecipe.class.getName());
		}
	}
}
