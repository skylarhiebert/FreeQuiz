/*******************************************************************************
 * Copyright (c) 2011 Skylar Hiebert.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and 
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of 
 * the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY 
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE 
 * SOFTWARE.
 * 
 * Contributors:
 *     Skylar Hiebert - initial API and implementation
 ******************************************************************************/
/**
 * 
 */
package org.freequiz.www.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Skylar Hiebert
 *
 */
public class MainMenu extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2230626947237599527L;
	private JPanel navigationPanel;
	private JPanel controlPanel;
	private JLabel classAdminLabel;
	private JButton classAdminButton;
	private JLabel studentAdminLabel;
	private JButton studentAdminButton;
	private JLabel questionAdminLabel;
	private JButton questionAdminButton;
	private JLabel newGameLabel;
	private JButton newGameButton;
	private JLabel loadGameLabel;
	private JButton loadGameButton;
	private JLabel reviewGameLabel;
	private JButton reviewGameButton;
	private JLabel importExportLabel;
	private JButton importExportButton;
	private JButton quitButton;
	
	/**
	 * 
	 */
	public MainMenu() {
		super();
		initComponents();
	}

	private void initComponents() {
		navigationPanel = new JPanel();
		controlPanel = new JPanel();
		classAdminLabel = new JLabel("Create new classes, add students to a class, and change class settings");
		classAdminButton = new JButton("View/Edit Class files");
		studentAdminLabel = new JLabel("Create new students, edit or delete existing students, view student data");
		studentAdminButton = new JButton("View/Edit Student records");
		questionAdminLabel = new JLabel("Add, edit or delete questions, topics and subjects");
		questionAdminButton = new JButton("View/Edit Question records");
		newGameLabel = new JLabel("Start a new FreeQuiz game session");
		newGameButton = new JButton("Start New Game");
		loadGameLabel = new JLabel("Restore a previously saved FreeQuiz game session");
		loadGameButton = new JButton("Load Saved Game");
		reviewGameLabel = new JLabel("Review data from complete game session");
		reviewGameButton = new JButton("Review Previous Game");
		importExportLabel = new JLabel("Import/Export Classes, Students or Questions");
		importExportButton = new JButton("Import/Export Data");
		quitButton = new JButton("Quit");
		quitButton.setPreferredSize(new Dimension(100, 23));
		
		/* Button Panel Layout */
		GridBagLayout navigationPanelLayout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.weightx = 0.0;
		gbc.insets = new Insets(10,0,10,10);
		navigationPanel.setLayout(navigationPanelLayout);
		gbc.gridx = 0;
		gbc.gridy = 0;
		navigationPanel.add(studentAdminButton, gbc);
		gbc.gridy = 1;
		navigationPanel.add(classAdminButton, gbc);
		gbc.gridy = 2;
		navigationPanel.add(questionAdminButton, gbc);
		gbc.gridy = 3;
		navigationPanel.add(newGameButton, gbc);
		gbc.gridy = 4;
		navigationPanel.add(loadGameButton, gbc);
		gbc.gridy = 5;
		navigationPanel.add(reviewGameButton, gbc);
		gbc.gridy = 6;
		navigationPanel.add(importExportButton, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		navigationPanel.add(studentAdminLabel, gbc);
		gbc.gridy = 1;
		navigationPanel.add(classAdminLabel, gbc);
		gbc.gridy = 2;
		navigationPanel.add(questionAdminLabel, gbc);
		gbc.gridy = 3;
		navigationPanel.add(newGameLabel, gbc);
		gbc.gridy = 4;
		navigationPanel.add(loadGameLabel, gbc);
		gbc.gridy = 5;
		navigationPanel.add(reviewGameLabel, gbc);
		gbc.gridy = 6;
		navigationPanel.add(importExportLabel, gbc);
		
		// Quit Button layout
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.LINE_AXIS));
		controlPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		controlPanel.add(quitButton);
		controlPanel.add(Box.createHorizontalGlue());
		
		// Panel Layout
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(navigationPanel, GroupLayout.PREFERRED_SIZE, 730, Short.MAX_VALUE)
				.addComponent(controlPanel)
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(navigationPanel)
				.addComponent(controlPanel)
		);

		// Settings that need to be removed as the functions are implemented
		loadGameLabel.setVisible(false);
		loadGameButton.setVisible(false);
		reviewGameLabel.setVisible(false);
		reviewGameButton.setVisible(false);
		importExportLabel.setVisible(false);
		importExportButton.setVisible(false);
		
	}

	// Button Action Listeners
	public void addClassAdminButtonListener(ActionListener al) {
		classAdminButton.addActionListener(al);
	}
	
	public void addStudentAdminButtonListener(ActionListener al) {
		studentAdminButton.addActionListener(al);
	}
	
	public void addQuestionAdminButtonListener(ActionListener al) {
		questionAdminButton.addActionListener(al);
	}
	
	public void addNewGameButtonListener(ActionListener al) {
		newGameButton.addActionListener(al);
	}
	
	public void addLoadGameButtonListener(ActionListener al) {
		loadGameButton.addActionListener(al);
	}
	
	public void addReviewGameButtonListener(ActionListener al) {
		reviewGameButton.addActionListener(al);
	}
	
	public void addImportExportButtonListener(ActionListener al) {
		importExportButton.addActionListener(al);
	}
	
	public void addQuitButtonListener(ActionListener al) {
		quitButton.addActionListener(al);
	}
	
}
