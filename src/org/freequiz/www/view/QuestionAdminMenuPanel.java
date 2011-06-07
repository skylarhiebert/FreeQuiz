/**
 * 
 */
package org.freequiz.www.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

/**
 * @author Skylar Hiebert
 *
 */
public class QuestionAdminMenuPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4225972790538476617L;
	private JPanel filterPanel;
	private JLabel topicFilterLabel;
	private JLabel subjectFilterLabel;
	private JLabel gradeLevelFilterLabel;
	private JLabel searchFilterLabel;
	private JComboBox topicFilterComboBox;
	private JComboBox subjectFilterComboBox;
	private JComboBox gradeLevelFilterComboBox;
	private JTextField searchFilterTextField;
	private JButton searchFilterButton;
	private JPanel questionsPanel;
	private JButton newButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton backButton;
	private String[] defaultTopicFilterList;
	private String[] defaultSubjectFilterList;
	private String[] defaultGradeLevelList;
	
	/**
	 * Class constructor
	 */
	public QuestionAdminMenuPanel() {
		super();
		initComponents();
	}
	
	/**
	 * Initializes all frame components
	 */
	private void initComponents() {
		defaultTopicFilterList = new String[] { "Any", "Topic 1", "Topic 2", "Topic 3", "Topic 4", "Topic 5" };
		defaultSubjectFilterList = new String[] { "Any", "Subject 1", "Subject 2", "Subject 3", "Subject 4", "Subject 5" };
		defaultGradeLevelList = new String[] { "Any", "K", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "College" };
		filterPanel = new JPanel();
		topicFilterLabel = new JLabel();
		subjectFilterLabel = new JLabel();
		gradeLevelFilterLabel = new JLabel();
		topicFilterComboBox = new JComboBox(defaultTopicFilterList);
		subjectFilterComboBox = new JComboBox(defaultSubjectFilterList);
		gradeLevelFilterComboBox = new JComboBox(defaultGradeLevelList);
		searchFilterTextField = new JTextField();
		searchFilterButton = new JButton();
		questionsPanel = new JPanel();
		newButton = new JButton();
		editButton = new JButton();
		deleteButton = new JButton();
		backButton = new JButton();

		
		topicFilterLabel.setText("Topic:");
		subjectFilterLabel.setText("Subject:");
		gradeLevelFilterLabel.setText("Grade Level:");
		searchFilterButton.setText("Search");

		/* Filter Panel */
		filterPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createCompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), 
						BorderFactory.createBevelBorder(BevelBorder.RAISED)), "Filter"));
		
		/* Layout for Filter Panel */
		GroupLayout filterPanelLayout = new GroupLayout(filterPanel);
		filterPanel.setLayout(filterPanelLayout);
		filterPanelLayout.setAutoCreateGaps(true);
		filterPanelLayout.setAutoCreateContainerGaps(true);
		filterPanelLayout.setHorizontalGroup(filterPanelLayout.createSequentialGroup()
					.addGroup(filterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(topicFilterLabel)
						.addComponent(gradeLevelFilterLabel))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(filterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(topicFilterComboBox, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
						.addComponent(gradeLevelFilterComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(filterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(filterPanelLayout.createSequentialGroup()
							.addComponent(subjectFilterLabel)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(subjectFilterComboBox, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
						.addGroup(filterPanelLayout.createSequentialGroup()
							.addComponent(searchFilterTextField)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(searchFilterButton)))
		);
		filterPanelLayout.linkSize(SwingConstants.HORIZONTAL, topicFilterLabel, gradeLevelFilterLabel);
		
		filterPanelLayout.setVerticalGroup(filterPanelLayout.createSequentialGroup()
			.addGroup(filterPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(topicFilterLabel)
				.addComponent(topicFilterComboBox)
				.addComponent(subjectFilterLabel)
				.addComponent(subjectFilterComboBox))
			.addGroup(filterPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(gradeLevelFilterLabel)
				.addComponent(gradeLevelFilterComboBox)
				.addComponent(searchFilterTextField)
				.addComponent(searchFilterButton))
		);	
		
		add(filterPanel, BorderLayout.NORTH);
	}
	
	public void setTopicFilterList(Vector<String> list) {
		if(list.isEmpty())
			gradeLevelFilterComboBox.setModel(new DefaultComboBoxModel()
	}
	
	public void setTopicFilterList(String[] list) {
		
	}
}


