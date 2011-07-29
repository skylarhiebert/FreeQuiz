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


import java.awt.Color;
import java.awt.Component;

import java.awt.Dimension;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;

import javax.swing.ComboBoxModel;
import javax.swing.GroupLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


import org.freequiz.www.model.Question;
import org.freequiz.www.model.Subject;
import org.freequiz.www.model.Topic;

/**
 * @author Skylar Hiebert
 *
 */
public class EditQuestionDialogPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6816094302966044347L;

	// Class for limiting number of characters in TextArea
	private class LimitedPlainDocument extends PlainDocument {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1335358823898601178L;
		int maxLength;
		
		public LimitedPlainDocument(int maxLength) {
	        this.maxLength = maxLength;
	    }

	    public void insertString(int offset, String str, AttributeSet a)
	            throws BadLocationException {
	        int length = str.length();

	        if (offset + length > maxLength)
	            length = maxLength - offset;

	        super.insertString(offset, str.substring(0, length), a);
	    }
	}
	
	
	private JPanel attributesPanel;
	private JPanel textAreaPanel;
	private JLabel subjectLabel;
	private JComboBox subjectComboBox;
	private JButton newSubjectButton;
	private JLabel topicLabel;
	private JComboBox topicComboBox;
	private JButton newTopicButton;
	private JLabel gradeLevelLabel;
	private JComboBox gradeLevelComboBox;
	private JLabel difficultyLabel;
	private JComboBox difficultyComboBox;
	private JLabel questionLabel;
	private JTextArea questionTextArea;
	private JLabel answerLabel;
	private JTextArea answerTextArea;
	private Question question;
	
	/**
	 * @param arg0
	 */
	public EditQuestionDialogPanel() {
		super();
		initComponents();
		question = null;
	}

	/**
	 * @param arg0
	 */
	public EditQuestionDialogPanel(Question question) {
		super();
		initComponents();
		setQuestion(question);
	}

	private void initComponents() {
		setVisible(true);
		attributesPanel = new JPanel();
		textAreaPanel = new JPanel();

		subjectLabel = new JLabel("Subject:");
		subjectComboBox = new JComboBox();
//		subjectComboBox.setEditable(true);
		newSubjectButton = new JButton("New Subject");
		topicLabel = new JLabel("Topic:");
		topicComboBox = new JComboBox();
//		topicComboBox.setEditable(true);
		newTopicButton = new JButton("New Topic");
		gradeLevelLabel = new JLabel("Grade Level:");
		gradeLevelComboBox = new JComboBox();
//		gradeLevelComboBox.setEditable(true);
		gradeLevelComboBox.setPreferredSize(new Dimension(20, gradeLevelComboBox.getPreferredSize().height));
		difficultyLabel = new JLabel("Difficulty (1-8):");
		difficultyComboBox = new JComboBox();
		difficultyComboBox.setPreferredSize(new Dimension(20, difficultyComboBox.getPreferredSize().height));
		questionLabel = new JLabel("Question Text:");
		questionTextArea = new JTextArea(new LimitedPlainDocument(255), null, 5, 20);
		questionTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		questionTextArea.setLineWrap(true);
		questionTextArea.setWrapStyleWord(true);
//		questionTextArea.setFocusTraversalKeysEnabled(true);
		questionTextArea.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent evt) {
				keyPress(evt);
			}
			@Override
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyTyped(KeyEvent arg0) {}
		});
		questionTextArea.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent arg0) {
				questionTextArea.selectAll();
			}

			@Override
			public void focusLost(FocusEvent e) {}
		});
		answerLabel = new JLabel("Answer Text:");
		answerTextArea = new JTextArea(new LimitedPlainDocument(255), null, 5, 20);
		answerTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		answerTextArea.setLineWrap(true);
		answerTextArea.setWrapStyleWord(true);
		answerTextArea.setFocusable(true);
		answerTextArea.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent evt) {
				keyPress(evt);		
			}
			@Override
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyTyped(KeyEvent arg0) {}
		});
		answerTextArea.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent arg0) {
				answerTextArea.selectAll();
			}

			@Override
			public void focusLost(FocusEvent e) {}
		});
		if(question != null) {
			subjectComboBox.setSelectedItem(question.getTopic().getSubject());
			topicComboBox.setSelectedItem(question.getTopic());
			gradeLevelComboBox.setSelectedItem(question.getGradeLevel());
			questionTextArea.setText(question.getQuestionText());
			answerTextArea.setText(question.getAnswerText());
		}
	
		GroupLayout attributesLayout = new GroupLayout(attributesPanel);
		attributesPanel.setLayout(attributesLayout);
		attributesLayout.setAutoCreateGaps(true);
		attributesLayout.setAutoCreateContainerGaps(true);
		attributesLayout.setHorizontalGroup(attributesLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(attributesLayout.createSequentialGroup()
						.addComponent(subjectLabel)
						.addComponent(subjectComboBox)
						.addComponent(newSubjectButton))
				.addGroup(attributesLayout.createSequentialGroup()
						.addComponent(topicLabel)
						.addComponent(topicComboBox)
						.addComponent(newTopicButton))
				.addGroup(attributesLayout.createSequentialGroup()
						.addComponent(gradeLevelLabel)
						.addComponent(gradeLevelComboBox)
						.addComponent(difficultyLabel)
						.addComponent(difficultyComboBox))
				);
		attributesLayout.linkSize(subjectLabel, topicLabel, gradeLevelLabel, difficultyLabel);
		attributesLayout.linkSize(newSubjectButton, newTopicButton);
		
		attributesLayout.setVerticalGroup(attributesLayout.createSequentialGroup()
				.addGroup(attributesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(subjectLabel)
						.addComponent(subjectComboBox)
						.addComponent(newSubjectButton))
				.addGroup(attributesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(topicLabel)
						.addComponent(topicComboBox)
						.addComponent(newTopicButton))
				.addGroup(attributesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(gradeLevelLabel)
						.addComponent(gradeLevelComboBox)
						.addComponent(difficultyLabel)
						.addComponent(difficultyComboBox))
				);
		
		GridBagConstraints gbc = new GridBagConstraints();
		textAreaPanel.setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0;
		textAreaPanel.add(questionLabel, gbc);
		gbc.insets = new Insets(0,5,0,0);
		gbc.gridx = 1;
		textAreaPanel.add(answerLabel, gbc);
		gbc.insets = new Insets(0,0,0,5);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weighty = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		textAreaPanel.add(questionTextArea, gbc);
		gbc.insets = new Insets(0,5,0,0);
		gbc.gridx = 1;
		textAreaPanel.add(answerTextArea, gbc);
		gbc.insets = new Insets(0,0,0,5);
			
		setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(attributesPanel, gbc);
		gbc.gridy = 1;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		add(textAreaPanel, gbc);
	}

	public void keyPress(KeyEvent evt) {
		switch(evt.getKeyCode()) {
		case KeyEvent.VK_TAB:
			Component comp = evt.getComponent();
			
			if(comp instanceof JTextArea) {
				JTextArea jta = (JTextArea) comp;
				evt.consume();
				jta.transferFocus();
			}
		}
	}
	
	public void setQuestion(Question editQuestion) {
		question = editQuestion;
		if(question.getQuestionid() != null && question.getQuestionid() > 0) {
			questionTextArea.setText(editQuestion.getQuestionText());
			answerTextArea.setText(editQuestion.getAnswerText());
			topicComboBox.setSelectedItem(question.getTopic());
			subjectComboBox.setSelectedItem(question.getTopic().getSubject());
			gradeLevelComboBox.setSelectedItem(question.getGradeLevel());
			difficultyComboBox.setSelectedItem(question.getDifficulty());
		} else {
			questionTextArea.setText(null);
			answerTextArea.setText(null);
			subjectComboBox.setSelectedIndex(-1);
			topicComboBox.setSelectedIndex(-1);
			gradeLevelComboBox.setSelectedIndex(-1);
			difficultyComboBox.setSelectedIndex(-1);
			
		}
	}
	
	public void addNewSubjectButtonListener(ActionListener al) {
		newSubjectButton.addActionListener(al);
	}
	
	public void addNewTopicButtonListener(ActionListener al) {
		newTopicButton.addActionListener(al);
	}
	
	public Question getQuestion() {
		return question;
	}
	
	public String getQuestionText() {
		return questionTextArea.getText();
	}
	
	public String getAnswerText() {
		return answerTextArea.getText();
	}
	
	public void setTopicComboBoxModel(ComboBoxModel cbm) {
		topicComboBox.setModel(cbm);
	}
	
	public void setSubjectComboBoxModel(ComboBoxModel cbm) {
		subjectComboBox.setModel(cbm);
	}
	
	public void setGradeLevelComboBoxModel(ComboBoxModel cbm) {
		gradeLevelComboBox.setModel(cbm);
	}
	
	public void setDifficultyComboBoxModel(ComboBoxModel cbm) {
		difficultyComboBox.setModel(cbm);
	}
	
	public Subject getSelectedSubject() {
		if(subjectComboBox.getSelectedIndex() < 0)
			return new Subject((String) subjectComboBox.getSelectedItem());
		else
			return (Subject) subjectComboBox.getSelectedItem();
	}
	
	public int getSelectedSubjectIndex() {
		return subjectComboBox.getSelectedIndex();
	}
	
	public Topic getSelectedTopic() {
		if(topicComboBox.getSelectedIndex() < 0)
			return new Topic((String) topicComboBox.getSelectedItem());
		else
			return (Topic) topicComboBox.getSelectedItem();
	}
	
	public int getSelectedTopicIndex() {
		return topicComboBox.getSelectedIndex();
	}
	
	public String getGradeLevel() {
		return (String) gradeLevelComboBox.getSelectedItem();
	}
	
	public int getSelectedGradeLevelIndex() {
		return gradeLevelComboBox.getSelectedIndex();
	}
	
	public Long getSelectedDifficulty() {
		return (Long) difficultyComboBox.getSelectedItem();
	}
	
	public int getSelectedDifficultyIndex() {
		return difficultyComboBox.getSelectedIndex();
	}
	
	public boolean validateInputs() {
		boolean valid = true;
		Color defaultJLabelColor = new JLabel().getForeground();
		if(subjectComboBox.getSelectedIndex() >= 0) {
			subjectLabel.setForeground(defaultJLabelColor);
		} else {
			subjectLabel.setForeground(Color.RED);
			valid = false;
		}
		if(topicComboBox.getSelectedIndex() >= 0) {
			topicLabel.setForeground(defaultJLabelColor);
		} else {
			topicLabel.setForeground(Color.RED);
			valid = false;
		}
		if(gradeLevelComboBox.getSelectedIndex() >= 0) {
			gradeLevelLabel.setForeground(defaultJLabelColor);
		} else {
			gradeLevelLabel.setForeground(Color.RED);
			valid = false;
		}
		if(difficultyComboBox.getSelectedIndex() >= 0) {
			difficultyLabel.setForeground(defaultJLabelColor);
		} else {
			difficultyLabel.setForeground(Color.RED);
			valid = false;
		}
		if(questionTextArea.getText().isEmpty()) {
			questionLabel.setForeground(Color.RED);
			valid = false;
		} else {
			questionLabel.setForeground(defaultJLabelColor);
		}
		if(answerTextArea.getText().isEmpty()) {
			answerLabel.setForeground(Color.RED);
			valid = false;
		} else {
			answerLabel.setForeground(defaultJLabelColor);
		}
		return valid;
	}
}
