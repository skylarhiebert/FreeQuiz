/**
 * 
 */
package org.freequiz.www.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Collection;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.freequiz.www.model.Student;
import org.freequiz.www.library.FQDefaultListModel;

/**
 * @author Skylar Hiebert
 *
 */
public class StudentAdminMenu extends JPanel {
	private JButton backButton;
	private JPanel buttonPanel;
	private JButton newButton;
	private JButton editButton;
	private JButton deleteButton;
	private JPanel studentPanel;
	private JList studentList;
	private JScrollPane studentScrollPane;
	private JPanel statisticsPanel;
	private JLabel gamesPlayedLabel;
	private JTextField gamesPlayedTextField;
	private JLabel averageScoreLabel;
	private JTextField averageScoreTextField;
	private JLabel topScoreLabel;
	private JTextField topScoreTextField;
	
	/**
	 * 
	 */
	public StudentAdminMenu() {
		super();
		initComponents();
	}
	
	private void initComponents() {
		backButton = new JButton("Back to Main Menu");
		buttonPanel = new JPanel();
		studentPanel = new JPanel();
		studentList = new JList();
		studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		studentList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		studentList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				studentListSelection(evt);
			}
		});
		studentScrollPane = new JScrollPane(studentList);
//		studentScrollPane.setBorder(BorderFactory.createTitledBorder("Students"));
		statisticsPanel = new JPanel();
		gamesPlayedLabel = new JLabel("Number of Games Played:");
		gamesPlayedTextField = new JTextField();
		gamesPlayedTextField.setHorizontalAlignment(SwingConstants.CENTER);
		gamesPlayedTextField.setEditable(false);
		averageScoreLabel = new JLabel("Average Score:");
		averageScoreTextField = new JTextField();
		averageScoreTextField.setHorizontalAlignment(SwingConstants.CENTER);
		averageScoreTextField.setEditable(false);
		topScoreLabel = new JLabel("Top Score:");
		topScoreTextField = new JTextField();
		topScoreTextField.setHorizontalAlignment(SwingConstants.CENTER);
		topScoreTextField.setEditable(false);

		
		/* Button Panel */
		Dimension buttonPreferredSize = new Dimension(100, 23);
		buttonPanel = new JPanel();
		newButton = new JButton("New");
		newButton.setPreferredSize(buttonPreferredSize);
		editButton = new JButton("Edit");
		editButton.setPreferredSize(buttonPreferredSize);
		editButton.setEnabled(false);
		deleteButton = new JButton("Delete");
		deleteButton.setPreferredSize(buttonPreferredSize);
		deleteButton.setEnabled(false);
		
		/* Button Panel Layout */
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(deleteButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(editButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPanel.add(newButton);
		
		studentPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createCompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), 
						BorderFactory.createBevelBorder(BevelBorder.RAISED)), "Students"));
		GroupLayout studentPanelLayout = new GroupLayout(studentPanel);
		studentPanel.setLayout(studentPanelLayout);
		studentPanelLayout.setAutoCreateGaps(true);
		studentPanelLayout.setAutoCreateContainerGaps(true);
		studentPanelLayout.setHorizontalGroup(studentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(studentScrollPane)
				.addComponent(buttonPanel)
		);
	
		studentPanelLayout.setVerticalGroup(studentPanelLayout.createSequentialGroup()
				.addComponent(studentScrollPane)
				.addComponent(buttonPanel)
		);	
		
		statisticsPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createCompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), 
						BorderFactory.createBevelBorder(BevelBorder.RAISED)), "Student Statistics"));
		GroupLayout statisticsPanelLayout = new GroupLayout(statisticsPanel);
		statisticsPanel.setLayout(statisticsPanelLayout);
		statisticsPanelLayout.setAutoCreateGaps(true);
		statisticsPanelLayout.setAutoCreateContainerGaps(true);
		statisticsPanelLayout.setHorizontalGroup(statisticsPanelLayout.createSequentialGroup()
				.addGroup(statisticsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(gamesPlayedLabel)
						.addComponent(averageScoreLabel)
						.addComponent(topScoreLabel))
				.addGroup(statisticsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(gamesPlayedTextField, 60, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(averageScoreTextField, 60, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(topScoreTextField, 60, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		statisticsPanelLayout.setVerticalGroup(statisticsPanelLayout.createSequentialGroup()
				.addGroup(statisticsPanelLayout.createParallelGroup()
						.addComponent(gamesPlayedLabel)
						.addComponent(gamesPlayedTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(statisticsPanelLayout.createParallelGroup()
						.addComponent(averageScoreLabel)
						.addComponent(averageScoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(statisticsPanelLayout.createParallelGroup()
						.addComponent(topScoreLabel)
						.addComponent(topScoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		statisticsPanelLayout.linkSize(SwingConstants.HORIZONTAL, gamesPlayedLabel, averageScoreLabel, topScoreLabel);
//		statisticsPanelLayout.linkSize(SwingConstants.HORIZONTAL, gamesPlayedTextField, averageScoreTextField, topScoreTextField);
//		statisticsPanelLayout.linkSize(SwingConstants.VERTICAL, gamesPlayedLabel, averageScoreLabel, topScoreLabel, 
//				gamesPlayedTextField, averageScoreTextField, topScoreTextField);
//		statisticsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		GridBagLayout statisticsPanelLayout = new GridBagLayout();
//		statisticsPanel.setLayout(statisticsPanelLayout);
//		GridBagConstraints gbc = new GridBagConstraints();
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.insets = new Insets(10,0,10,0);
//		gbc.gridy = 0;
//		gbc.gridx = 0;
//		statisticsPanel.add(gamesPlayedLabel, gbc);
//		gbc.gridx = 1;
//		statisticsPanel.add(gamesPlayedTextField, gbc);
//		gbc.gridy = 1;
//		gbc.gridx = 0;
//		statisticsPanel.add(averageScoreLabel, gbc);
//		gbc.gridx = 1;
//		statisticsPanel.add(averageScoreTextField, gbc);
//		gbc.gridy = 2;
//		gbc.gridx = 0;
//		statisticsPanel.add(topScoreLabel, gbc);
//		gbc.gridx = 1;
//		statisticsPanel.add(topScoreTextField, gbc);		
		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(studentPanel)
						.addComponent(statisticsPanel))
				.addComponent(backButton)
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(studentPanel)
						.addComponent(statisticsPanel))
				.addComponent(backButton)
		);
	}
	
	private void studentListSelection(ListSelectionEvent evt) {
		deleteButton.setEnabled(true);
		editButton.setEnabled(true);	
	}
	
	public void addStudentListSelectionListener(ListSelectionListener listener) {
		studentList.addListSelectionListener(listener);
	}
	
	public void addBackButtonListener(ActionListener al) {
		backButton.addActionListener(al);
	}
	
	public void addNewStudentButtonListener(ActionListener al) {
		newButton.addActionListener(al);
	}
	
	public void addEditStudentButtonListener(ActionListener al) {
		editButton.addActionListener(al);
	}
	
	public void addDeleteStudentButtonListener(ActionListener al) {
		deleteButton.addActionListener(al);
	}
	
	public Student getSelectedStudent() {
		return (Student) studentList.getSelectedValue();
	}
	
	public void setGamesPlayed(String gamesPlayed) {
		gamesPlayedTextField.setText(gamesPlayed);
	}
	
	public void setGamesPlayed(int gamesPlayed) {
		gamesPlayedTextField.setText(Integer.toString(gamesPlayed));
	}
	
	public void setAverageScore(String avgScore) {
		averageScoreTextField.setText(avgScore);
	}
	
	public void setAverageScore(float avgScore) {
		averageScoreTextField.setText(new DecimalFormat("#.##").format(avgScore));
	}
	
	public void setTopScore(String topScore) {
		topScoreTextField.setText(topScore);
	}
	
	public void setTopScore(int topScore) {
		topScoreTextField.setText(Integer.toString(topScore));
	}
	
	public void setStudentListModel(ListModel lm) {
		studentList.setModel(lm);
	}
}
