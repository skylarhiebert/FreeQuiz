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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

/**
 * @author Skylar Hiebert
 *
 */
public class NewGameMenu extends JPanel {
//	private int minTopics = 2;
	private int maxTopics = 8;
//	private int minQuestions = 2;
//	private int maxQuestions = 8;
//	public String gradeLevels[] = {"Pre-K", "K", "1", "2", "3", "4", "5", "6", "7", "8", "High School", "College" };
//	public Long difficulties[] = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L};
	
	private JPanel buttonPanel;
	private JButton backButton;
	private JButton startGameButton;
	private JPanel menuPanel;
	private JPanel gameSettingsPanel;
	private JLabel numTopicsLabel;
	private JComboBox numTopicsComboBox;
	private ComboBoxModel numTopicsComboBoxModel = new DefaultComboBoxModel();
	private JLabel numQuestionsLabel;
	private JComboBox numQuestionsComboBox;
	private ComboBoxModel numQuestionsComboBoxModel = new DefaultComboBoxModel();
	private JLabel questionMultiplierLabel;
	private JSpinner questionMultiplierSpinner;
	private SpinnerModel questionMultiplierSpinnerModel = new SpinnerNumberModel();
	private JPanel rosterPanel;
	private JLabel rosterSelectionLabel;
	private JComboBox rosterSelectionComboBox;
	private ComboBoxModel rosterSelectionComboBoxModel = new DefaultComboBoxModel();
	private JLabel rosterListLabel;
	private JList rosterList;
	private JScrollPane rosterScrollPane;
	private ListModel rosterListModel = new DefaultListModel();
	private JPanel instructionPanel;
	private JTextArea instructionTextArea;
	private JPanel topicSelectionPanel;
	private JLabel topicSelectionLabel;
	private JLabel gradeLevelSelectionLabel;
	private List<JComboBox> topicComboBoxes;
	private List<JComboBox> gradeLevelComboBoxes;
	private List<ComboBoxModel> topicSelectionComboBoxModels;
	private List<ComboBoxModel> gradeLevelSelectionComboBoxModels;
	
	public NewGameMenu() {
		super();
		initComponents();
	}
	
	private void initComponents() {
		buttonPanel = new JPanel();
		backButton = new JButton("Back to Main Menu");
		startGameButton = new JButton("Start Game");
		startGameButton.setMinimumSize(backButton.getMinimumSize());
		gameSettingsPanel = new JPanel();
		
		menuPanel = new JPanel();
		numTopicsLabel = new JLabel("Number of Topics:");
		numTopicsComboBox = new JComboBox(numTopicsComboBoxModel);
		numTopicsComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				numTopicsComboBoxAction(evt);
			}
		});
		numTopicsComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				topicSelectionComboBoxAction(evt);
			}
		});
		numQuestionsLabel = new JLabel("Number of Questions per Topic:");	
		numQuestionsComboBox = new JComboBox(numQuestionsComboBoxModel);
		numQuestionsComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				topicSelectionComboBoxAction(evt);
			}
		});
		questionMultiplierLabel = new JLabel("Question Point Value Multiplier:");		
		questionMultiplierSpinner = new JSpinner(questionMultiplierSpinnerModel);
		
		rosterPanel = new JPanel();
		rosterSelectionLabel = new JLabel("Select a Class:");
		rosterSelectionComboBox = new JComboBox(rosterSelectionComboBoxModel);
		rosterSelectionComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				topicSelectionComboBoxAction(evt);
			}
		});
		rosterListLabel = new JLabel("Students:");
		rosterList = new JList(rosterListModel);
		rosterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rosterList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		rosterScrollPane = new JScrollPane(rosterList);
		
		instructionPanel = new JPanel();
		instructionTextArea = new JTextArea();
		instructionTextArea.setPreferredSize(new Dimension(350, 100));
		instructionTextArea.setEditable(false);
		instructionTextArea.setLineWrap(true);
		instructionTextArea.setWrapStyleWord(true);
		instructionTextArea.setBorder(BorderFactory.createEmptyBorder());
		instructionTextArea.setText("The Question Point Multiplier determines the value of each question." +
				"\n\nExample: Question Point Multiplier = 100\n" +
				"The third question in a topic will be valued at 300 points.");
		
		topicSelectionPanel = new JPanel();
		topicSelectionLabel = new JLabel("Select Topic");
		gradeLevelSelectionLabel = new JLabel("Select Grade Level");
		topicComboBoxes = new ArrayList<JComboBox>();
		topicSelectionComboBoxModels = new ArrayList<ComboBoxModel>();
		gradeLevelComboBoxes = new ArrayList<JComboBox>();
		gradeLevelSelectionComboBoxModels = new ArrayList<ComboBoxModel>();
		for(int i = 0; i < maxTopics; i++) {
			topicSelectionComboBoxModels.add(new DefaultComboBoxModel());
			topicComboBoxes.add(new JComboBox(topicSelectionComboBoxModels.get(i)));
			topicComboBoxes.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					topicSelectionComboBoxAction(evt);
				}
			});
			gradeLevelSelectionComboBoxModels.add(new DefaultComboBoxModel());
			gradeLevelComboBoxes.add(new JComboBox(gradeLevelSelectionComboBoxModels.get(i)));
			gradeLevelComboBoxes.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					topicSelectionComboBoxAction(evt);
				}
			});
		}
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
		buttonPanel.add(backButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(startGameButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
		
		// Settings Panel Layout
		gameSettingsPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createCompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), 
						BorderFactory.createBevelBorder(BevelBorder.RAISED)), "Game Settings"));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gameSettingsPanel.setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.weighty = 0.5;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5,5,0,5);
		gameSettingsPanel.add(numTopicsLabel, gbc);
		gbc.gridx = 1;
		gbc.insets = new Insets(5,5,0,5);
		gameSettingsPanel.add(numTopicsComboBox, gbc);
		gbc.gridy = 1;
		gbc.gridx = 0;
		gbc.insets = new Insets(5,5,0,5);
		gameSettingsPanel.add(numQuestionsLabel, gbc);
		gbc.gridx = 1;
		gbc.insets = new Insets(5,5,0,5);
		gameSettingsPanel.add(numQuestionsComboBox, gbc);
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.insets = new Insets(5,5,5,5);
		gameSettingsPanel.add(questionMultiplierLabel, gbc);
		gbc.gridx = 1;
		gbc.insets = new Insets(5,5,5,5);
		gameSettingsPanel.add(questionMultiplierSpinner, gbc);
		
		rosterPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createCompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true),
						BorderFactory.createBevelBorder(BevelBorder.RAISED)), "Class Selection"));
		
		GroupLayout rosterPanelLayout = new GroupLayout(rosterPanel);
		rosterPanel.setLayout(rosterPanelLayout);
		rosterPanelLayout.setAutoCreateGaps(true);
		rosterPanelLayout.setAutoCreateContainerGaps(true);
		rosterPanelLayout.setHorizontalGroup(rosterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(rosterPanelLayout.createSequentialGroup()
					.addComponent(rosterSelectionLabel)
					.addComponent(rosterSelectionComboBox))
				.addComponent(rosterListLabel)
				.addComponent(rosterScrollPane));
		rosterPanelLayout.setVerticalGroup(rosterPanelLayout.createSequentialGroup()
				.addGroup(rosterPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(rosterSelectionLabel)
					.addComponent(rosterSelectionComboBox))
				.addComponent(rosterListLabel)
				.addComponent(rosterScrollPane));
		
		instructionPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createCompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true),
						BorderFactory.createBevelBorder(BevelBorder.RAISED)), "Instructions"));
		
		instructionPanel.setLayout(new BorderLayout());
		instructionPanel.add(instructionTextArea, BorderLayout.CENTER);
		
		topicSelectionPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createCompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true),
						BorderFactory.createBevelBorder(BevelBorder.RAISED)), "Topic Selection"));
		
//		topicSelectionPanel.setLayout(new BoxLayout(topicSelectionPanel, BoxLayout.PAGE_AXIS));
//		Dimension buffer = new Dimension(0,5);
		topicSelectionPanel.setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.weighty = 1.0;
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.weightx = 0.75;
		gbc.insets = new Insets(0,5,5,0);
		topicSelectionPanel.add(topicSelectionLabel, gbc);
		gbc.gridx = 1;
		gbc.weightx = 0.25;
		gbc.insets = new Insets(0,5,0,0);
		topicSelectionPanel.add(gradeLevelSelectionLabel, gbc);
		for(int i = 0; i < maxTopics; i++) {
			gbc.gridy = i + 1;
			gbc.gridx = 0;
			gbc.weightx = 0.75;
			gbc.insets = new Insets(0,5,5,0);
			topicSelectionPanel.add(topicComboBoxes.get(i), gbc);
			gbc.gridx = 1;
			gbc.weightx = 0.25;
			gbc.insets = new Insets(0,5,0,0);
			topicSelectionPanel.add(gradeLevelComboBoxes.get(i), gbc);
		}
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.weightx = 1.0;
		gbc.weighty = 0.25;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,5,0,5);
		leftPanel.add(gameSettingsPanel, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridy = 1;
		gbc.weighty = 0.75;
		gbc.insets = new Insets(5,5,0,5);
		leftPanel.add(rosterPanel, gbc);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.weightx = 1.0;
		gbc.weighty = 0.25;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,5,0,5);
		rightPanel.add(instructionPanel, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridy = 1;
		gbc.weighty = 0.75;
		gbc.insets = new Insets(5,5,0,5);
		rightPanel.add(topicSelectionPanel, gbc);
		
		menuPanel.setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.insets = new Insets(0,0,0,0);
		gbc.weighty = 0.5;
		gbc.weightx = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		menuPanel.add(leftPanel, gbc);
		gbc.gridx = 1;
		menuPanel.add(rightPanel, gbc);
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(menuPanel);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(buttonPanel);
		add(Box.createRigidArea(new Dimension(0,10)));
	}
	
	/**
	 * @param maxTopics the maxTopics to set
	 */
	public void setMaxTopics(int maxTopics) {
		this.maxTopics = maxTopics;
	}

	private void numTopicsComboBoxAction(ActionEvent evt) {
		int numTopics = (Integer) numTopicsComboBox.getSelectedItem();
		for(int i = 0; i < maxTopics; i++) {
			if(i < numTopics) {
				topicComboBoxes.get(i).setEnabled(true);
				gradeLevelComboBoxes.get(i).setEnabled(true);
			} else {
				topicComboBoxes.get(i).setEnabled(false);
				gradeLevelComboBoxes.get(i).setEnabled(false);
			}
		}
	}
	
	private void topicSelectionComboBoxAction(ActionEvent evt) {
		int numTopics = (Integer) numTopicsComboBox.getSelectedItem();
		startGameButton.setEnabled(true);
		for(int i = 0; i < numTopics; i++) {
			if(topicComboBoxes.get(i).getSelectedIndex() < 0 || gradeLevelComboBoxes.get(i).getSelectedIndex() < 0) {
				startGameButton.setEnabled(false);
				break; // Once false, always false
			}
		}
		if(rosterSelectionComboBox.getSelectedIndex() < 0)
			startGameButton.setEnabled(false);
	}
	
	public void addBackButtonListener(ActionListener al) {
		backButton.addActionListener(al);
	}
	
	public void addStartGameButtonListener(ActionListener al) {
		startGameButton.addActionListener(al);
	}
	
	public void setRosterSelectionActionListener(ActionListener al) {
		rosterSelectionComboBox.addActionListener(al);
	}
	
	public void setNumTopicsComboBoxModel(ComboBoxModel cbm) {
		numTopicsComboBoxModel = cbm;
		numTopicsComboBox.setModel(numTopicsComboBoxModel);
	}
	
	public void setNumQuestionsComboBoxModel(ComboBoxModel cbm) {
		numQuestionsComboBoxModel = cbm;
		numQuestionsComboBox.setModel(numQuestionsComboBoxModel);
	}
	
	public void setQuestionMultiplierSpinnerModel(SpinnerModel sm) {
		questionMultiplierSpinnerModel = sm;
		questionMultiplierSpinner.setModel(questionMultiplierSpinnerModel);
	}
	
	public void setRosterSelectionComboBoxModel(ComboBoxModel cbm) {
		rosterSelectionComboBoxModel = cbm;
		rosterSelectionComboBox.setModel(rosterSelectionComboBoxModel);
	}
	
	public void setRosterListModel(ListModel lm) {
		rosterListModel = lm;
		rosterList.setModel(rosterListModel);
	}
	
	public void setTopicSelectionComboBoxModel(int index, ComboBoxModel cbm) {
		topicSelectionComboBoxModels.set(index, cbm);
		topicComboBoxes.get(index).setModel(topicSelectionComboBoxModels.get(index));
	}
	
	public void setTopicSelectionComboBoxModels(List<ComboBoxModel> cbms) {
		topicSelectionComboBoxModels = cbms;
		for(int i = 0; i < topicSelectionComboBoxModels.size(); i++) {
			topicComboBoxes.get(i).setModel(topicSelectionComboBoxModels.get(i));
		}
	}

	public void setGradeLevelSelectionComboBoxModel(int index, ComboBoxModel cbm) {
		gradeLevelSelectionComboBoxModels.set(index, cbm);
		gradeLevelComboBoxes.get(index).setModel(gradeLevelSelectionComboBoxModels.get(index));
	}
}
