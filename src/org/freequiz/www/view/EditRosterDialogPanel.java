/**
 * 
 */
package org.freequiz.www.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.freequiz.www.library.FQDefaultListModel;
import org.freequiz.www.model.Roster;
import org.freequiz.www.model.Student;

/**
 * @author Skylar Hiebert
 *
 */
public class EditRosterDialogPanel extends JPanel {

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
	private JPanel namePanel;
	private JPanel descriptionPanel;
	private JPanel studentSelectionPanel;
	private JLabel rosterNameLabel;
	private JTextField rosterNameTextField;
	private JLabel rosterDescriptionLabel;
	private JTextArea rosterDescriptionTextArea;
	private JLabel numberOfStudentsLabel;
	private JTextField numberOfStudentsTextField;
	private JList rosterList;
	private JScrollPane rosterListScrollPane;
	private JList studentList;
	private JScrollPane studentListScrollPane;
	private JButton toRosterListButton;
	private JButton toStudentListButton;
	private Roster roster;
	private ListModel rosterListModel = new FQDefaultListModel();
	private ListModel studentListModel = new FQDefaultListModel();
	
	/**
	 * @param arg0
	 */
	public EditRosterDialogPanel() {
		super();
		initComponents();
		roster = null;
	}

//	/**
//	 * @param arg0
//	 */
//	public EditRosterDialogPanel(Roster editRoster) {
//		super();
//		initComponents();
//		setRoster(editRoster);
//	}	
	
	private void initComponents() {
		setVisible(true);
		attributesPanel = new JPanel();
		namePanel = new JPanel();
		descriptionPanel = new JPanel();
		studentSelectionPanel = new JPanel();
		
		/* Roster Details Initializers */
		rosterNameLabel = new JLabel("Name:");
		rosterDescriptionLabel = new JLabel("Description:");
		numberOfStudentsLabel = new JLabel("Number of Students:");
		if(roster != null) {
			rosterNameTextField = new JTextField(new LimitedPlainDocument(45), roster.getName(), 55);
			rosterDescriptionTextArea = new JTextArea(new LimitedPlainDocument(255), roster.getDescription(), 3, 15);
			numberOfStudentsTextField = new JTextField(Integer.toString(roster.getStudentList().size()), 5);
		}
		else {
			rosterNameTextField = new JTextField(new LimitedPlainDocument(45), "New Class Name", 12);
			rosterDescriptionTextArea = new JTextArea(new LimitedPlainDocument(255), "New Class Description", 3, 15);
			numberOfStudentsTextField = new JTextField("0", 5);
		}
		rosterNameTextField.setMaximumSize(new Dimension(355, 20));
		numberOfStudentsTextField.setHorizontalAlignment(JTextField.CENTER);
		numberOfStudentsTextField.setMaximumSize(new Dimension(45, 20));
		rosterDescriptionTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		rosterDescriptionTextArea.setLineWrap(true);
		rosterDescriptionTextArea.setWrapStyleWord(true);
		
		
		numberOfStudentsTextField.setEditable(false);
		
		/* Class Roster Initializers */
		rosterList = new JList(rosterListModel);
		rosterListScrollPane = new JScrollPane(rosterList);
		studentList = new JList(studentListModel);
		studentListScrollPane = new JScrollPane(studentList);
		toRosterListButton = new JButton("<<");
		toRosterListButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				performRosterListButtonAction(evt);
			}
		});
		toStudentListButton = new JButton(">>");
		toRosterListButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				performRosterListButtonAction(evt);
			}
		});
			
		attributesPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createCompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), 
						BorderFactory.createBevelBorder(BevelBorder.RAISED)), "Class Details"));
		
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.LINE_AXIS));
		namePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		namePanel.add(rosterNameLabel);
		namePanel.add(Box.createRigidArea(new Dimension(5,0)));
		namePanel.add(rosterNameTextField);
		namePanel.add(Box.createHorizontalGlue());
		namePanel.add(numberOfStudentsLabel);
		namePanel.add(Box.createRigidArea(new Dimension(5,0)));
		namePanel.add(numberOfStudentsTextField);	
		
		GridBagConstraints gbc = new GridBagConstraints();
		descriptionPanel.setLayout(new GridBagLayout());
		descriptionPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.weighty = 0.0;
		gbc.weightx = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		descriptionPanel.add(rosterDescriptionLabel, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridy = 1;
		descriptionPanel.add(rosterDescriptionTextArea, gbc);
		
		
		attributesPanel.setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.weighty = 0.0;
		gbc.weightx = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		attributesPanel.add(namePanel, gbc);
//		attributesPanel.add(namePanel, BorderLayout.CENTER);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weighty = 1.0;
		gbc.gridy = 1;
		attributesPanel.add(descriptionPanel, gbc);
		
		studentSelectionPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createCompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), 
						BorderFactory.createBevelBorder(BevelBorder.RAISED)), "Roster Details"));
		
		rosterListScrollPane.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createCompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), 
						BorderFactory.createBevelBorder(BevelBorder.RAISED)), "Students in Roster"));
		
		studentListScrollPane.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createCompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), 
						BorderFactory.createBevelBorder(BevelBorder.RAISED)), "Available Students" +
								""));
		studentSelectionPanel.setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 0.5;
		gbc.weighty = 1.0;
		gbc.gridheight = 2;
		gbc.gridy = 0;
		gbc.gridx = 0;
		studentSelectionPanel.add(rosterListScrollPane, gbc);
		gbc.gridx = 2;
		studentSelectionPanel.add(studentListScrollPane, gbc);
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.weightx = 0.0;
		gbc.weighty = 0.5;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 10, 5, 10);
		studentSelectionPanel.add(toRosterListButton, gbc);
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		studentSelectionPanel.add(toStudentListButton, gbc);
		
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
		add(studentSelectionPanel, gbc);
	}
	
	private void performRosterListButtonAction(ActionEvent evt) {
		numberOfStudentsTextField.setText(Integer.toString(rosterListModel.getSize()));
	}

	public void addToRosterListButtonListener(ActionListener al) {
		toRosterListButton.addActionListener(al);
	}
	
	public void addToStudentListButtonListener(ActionListener al) {
		toStudentListButton.addActionListener(al);
	}
	
	public void setRoster(Roster roster) {
		this.roster = roster;
		rosterNameTextField.setText(roster.getName());
		rosterDescriptionTextArea.setText(roster.getDescription());
		numberOfStudentsTextField.setText(Integer.toString(roster.getNumberOfStudents()));
	}
	
	public Roster getRoster() {
		return roster;
	}
	
//	private void setRosterName(String name) {
//		rosterNameTextField.setText(name);
//	}
	
	public String getRosterName() {
		return rosterNameTextField.getText();
	}
	
//	private void setRosterDescription(String desc) {
//		rosterDescriptionTextArea.setText(desc);
//	}
	
	public String getRosterDescription() {
		return rosterDescriptionTextArea.getText();
	}
	
//	private void setNumberOfStudents(int numStudents) {
//		numberOfStudentsTextField.setText(Integer.toString(numStudents));
//	}
	
//	public int getNumberOfStudents() {
//		return Integer.parseInt(numberOfStudentsTextField.getText());
//	}
		
	public void setRosterListModel(ListModel lm) {
		rosterListModel = lm;
		rosterList.setModel(rosterListModel);
	}
	
	public void setStudentListModel(ListModel lm) {
		studentListModel = lm;
		studentList.setModel(studentListModel);
	}
	
	public int[] getAddStudentsSelectedIndices() {
		return studentList.getSelectedIndices();
	}
	
	public int[] getRemoveStudentsSelectedIndices() {
		return rosterList.getSelectedIndices();
	}
	
	public boolean validateInputs() {
		boolean valid = true;
		Color defaultJLabelColor = new JLabel().getForeground();
		if(rosterNameTextField.getText().isEmpty()) {
			rosterNameLabel.setForeground(Color.RED);
			valid = false;
		} else {
			rosterNameLabel.setForeground(defaultJLabelColor);
		}
		if(rosterDescriptionTextArea.getText().isEmpty()) {
			rosterDescriptionLabel.setForeground(Color.RED);
			valid = false;
		} else {
			rosterDescriptionLabel.setForeground(defaultJLabelColor);
		}
		return valid;
	}
}
