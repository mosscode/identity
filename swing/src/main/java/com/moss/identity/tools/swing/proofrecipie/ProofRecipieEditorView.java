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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

class ProofRecipieEditorView extends JPanel {
	private JLabel loginUsingLabel;
	private JPanel panel;
	private JPasswordField textField;
	private JComboBox comboBox_2;
	private JTextField textField_1;
	private JComboBox comboBox_1;
	public ProofRecipieEditorView() {
		super();
		setLayout(new BorderLayout());

		final JPanel panel_2 = new JPanel();
		panel_2.setLayout(new GridBagLayout());
		add(panel_2);

		final JPanel panel_5 = new JPanel();
		panel_5.setLayout(new GridBagLayout());
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 1;
		panel_2.add(panel_5, gridBagConstraints);

		loginUsingLabel = new JLabel();
		loginUsingLabel.setText("Login using your ");
		panel_5.add(loginUsingLabel, new GridBagConstraints());

		comboBox_1 = new JComboBox();
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.gridy = 0;
		gridBagConstraints_1.gridx = 1;
		panel_5.add(comboBox_1, gridBagConstraints_1);

		final JLabel idLabel = new JLabel();
		idLabel.setText(" ID:");
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.gridy = 0;
		gridBagConstraints_2.gridx = 2;
		panel_5.add(idLabel, gridBagConstraints_2);

		final JSeparator separator = new JSeparator();
		final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.insets = new Insets(5, 0, 5, 0);
		gridBagConstraints_3.fill = GridBagConstraints.BOTH;
		gridBagConstraints_3.gridy = 1;
		gridBagConstraints_3.gridx = 1;
		panel_2.add(separator, gridBagConstraints_3);

		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_4.gridy = 2;
		gridBagConstraints_4.gridx = 1;
		panel_2.add(panel, gridBagConstraints_4);

		final JLabel loginLabel = new JLabel();
		loginLabel.setText("Id");
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.insets = new Insets(0, 5, 5, 5);
		gridBagConstraints_5.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_5.gridy = 0;
		panel.add(loginLabel, gridBagConstraints_5);

		textField_1 = new JTextField();
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.ipadx = 100;
		gridBagConstraints_6.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_6.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_6.weightx = 1.0;
		gridBagConstraints_6.gridy = 0;
		panel.add(textField_1, gridBagConstraints_6);

		final JLabel label = new JLabel();
		label.setText("@");
		final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
		gridBagConstraints_7.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_7.gridy = 0;
		gridBagConstraints_7.gridx = 2;
		panel.add(label, gridBagConstraints_7);

		comboBox_2 = new JComboBox();
		comboBox_2.setEditable(true);
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_8.gridy = 0;
		gridBagConstraints_8.gridx = 3;
		panel.add(comboBox_2, gridBagConstraints_8);

		final JLabel passwordLabel = new JLabel();
		passwordLabel.setText("Password");
		final GridBagConstraints gridBagConstraints_9 = new GridBagConstraints();
		gridBagConstraints_9.insets = new Insets(0, 5, 5, 5);
		gridBagConstraints_9.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_9.gridy = 1;
		gridBagConstraints_9.gridx = 0;
		panel.add(passwordLabel, gridBagConstraints_9);

		textField = new JPasswordField();
		final GridBagConstraints gridBagConstraints_10 = new GridBagConstraints();
		gridBagConstraints_10.insets = new Insets(0, 0, 5, 5);
		gridBagConstraints_10.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_10.weightx = 1.0;
		gridBagConstraints_10.gridwidth = 3;
		gridBagConstraints_10.gridy = 1;
		gridBagConstraints_10.gridx = 1;
		panel.add(textField, gridBagConstraints_10);

		final JPanel panel_4 = new JPanel();
		panel_4.setLayout(new GridBagLayout());
		final GridBagConstraints gridBagConstraints_11 = new GridBagConstraints();
		gridBagConstraints_11.fill = GridBagConstraints.BOTH;
		gridBagConstraints_11.gridy = 3;
		gridBagConstraints_11.gridx = 1;
		panel_2.add(panel_4, gridBagConstraints_11);
	}
	public JComboBox loginMethodSelector() {
		return comboBox_1;
	}
	public JPanel holderPanel() {
		return panel;
	}
	public JLabel blurbLabel() {
		return loginUsingLabel;
	}

}
