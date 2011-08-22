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
