/**
 * 
 */
package org.freequiz.www.view;

import java.awt.Color;
import java.awt.Dimension;
//import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.ArrayList;
import java.util.Collection;
//import java.util.Vector;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.TableModel;

import org.freequiz.www.library.FQDefaultComboBoxModel;
//import org.freequiz.www.library.FQDefaultListModel;
//import org.freequiz.www.model.Subject;
//import org.freequiz.www.model.Topic;

/**
 * @author Skylar Hiebert
 *
 */
public class QuestionAdminMenu extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4225972790538476617L;
	private JPanel filterPanel;
	private JLabel topicFilterLabel;
	private JLabel subjectFilterLabel;
	private JLabel gradeLevelFilterLabel;
	private JComboBox topicFilterComboBox;
	private JComboBox subjectFilterComboBox;
	private JComboBox gradeLevelFilterComboBox;
	private JTextField searchFilterTextField;
	private JButton searchFilterButton;
	private JPanel questionsPanel;
	private JScrollPane questionsScrollPane;
//	private JList questionsList;
	private JTable questionsListTable;
	private JPanel buttonPanel;
	private JButton newButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton backButton;
	private ComboBoxModel topicFilterDataModel;
	private ComboBoxModel subjectFilterDataModel;
	private ComboBoxModel gradeLevelFilterDataModel;
//	private FQDefaultListModel questionsListDataModel;
//	private ArrayList<Topic> testVector;
	
	/**
	 * Class constructor
	 */
	public QuestionAdminMenu() {
		super();
		initComponents();
	}
	
//	public QuestionAdminMenuPanel(ComboBoxModel topicFilterModel, ComboBoxModel subjectFilterModel, ComboBoxModel gradeLevelFilterModel, 
//			TableModel questionsTableModel) {
//		topicFilterDataModel = (FQDefaultComboBoxModel) topicFilterModel;
//		subjectFilterDataModel = (FQDefaultComboBoxModel) subjectFilterModel;
//		gradeLevelFilterDataModel = (FQDefaultComboBoxModel) gradeLevelFilterModel;
//	}
	/**
	 * Initializes all frame components
	 */
	private void initComponents() {
		/* Initializing strings for lists and combo boxes */
//		String[] defaultTopicFilterList = new String[] { "Any", "Topic 1", "Topic 2", "Topic 3", "Topic 4", "Topic 5" };
//		String[] defaultSubjectFilterList = new String[] { "Any", "Subject 1", "Subject 2", "Subject 3", "Subject 4", "Subject 5" };
//		String[] defaultGradeLevelFilterList = new String[] { "Any", "K", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "College" };
//		String[] defaultQuestionsList = new String[] { "Question 1", "Question 2", "Question 3", "Question 4", "Question 5", "Question 6",  "Question 7", "Question 8", "Question 9", "Question 10", "Question 11", "Question 12" };
//		testVector = new ArrayList<Topic>();
//		testVector.add(new Topic(1, "Subtraction", new Subject(1, "Math"), 1));
//		testVector.add(new Topic(1, "Multiplication", new Subject(1, "Math"), 1));
		
		/* Control Buttons */

		backButton = new JButton("Back to Main Menu");
		
		/* Filter Panel  */
		filterPanel = new JPanel();
		topicFilterLabel = new JLabel("Topic:");
//		topicFilterComboBox = new JComboBox();
//		topicFilterComboBox = new JComboBox(new FQDefaultComboBoxModel(defaultTopicFilterList));
//		topicFilterDataModel = new FQDefaultComboBoxModel(defaultTopicFilterList);
//		topicFilterDataModel = new FQDefaultComboBoxModel(testVector.toArray());
//		topicFilterDataModel = new FQDefaultComboBoxModel();
		topicFilterDataModel = new FQDefaultComboBoxModel();
		topicFilterComboBox = new JComboBox(topicFilterDataModel);
		subjectFilterLabel = new JLabel("Subject:");
		subjectFilterComboBox = new JComboBox();
		subjectFilterDataModel = new FQDefaultComboBoxModel();
		subjectFilterComboBox = new JComboBox(subjectFilterDataModel);
		gradeLevelFilterLabel = new JLabel("Grade Level:");
		gradeLevelFilterComboBox = new JComboBox();
		gradeLevelFilterDataModel = new FQDefaultComboBoxModel();
		gradeLevelFilterComboBox = new JComboBox(gradeLevelFilterDataModel);
		searchFilterTextField = new JTextField();
		searchFilterButton = new JButton("Search");

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
	
		/* Questions Panel */
		questionsPanel = new JPanel();
//		questionsListDataModel = new FQDefaultListModel(defaultQuestionsList);		
		Object rowData[][] = {{ "Question 1", "Subject 1", "Topic 3", "1" }, { "Question 2", "Subject 2", "Topic 2", null }, { "Question 3", "Subject 1", "Topic 1", "3" }, {"Question 4", "Subject 3", "Topic 1", "1" } };
		Object columnNames[] = { "Question", "Subject", "Topic", "Grade Level" };
		questionsListTable = new JTable(new DefaultTableModel(rowData, columnNames)) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

//		questionsListTable = new JTable();
		questionsListTable.setAutoCreateRowSorter(true);
		questionsListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		questionsListTable.setRowSelectionAllowed(true);
//		questionsListTable.setCellSelectionEnabled(false);
		questionsListTable.getColumnModel().getColumn(0).setPreferredWidth(370);
		questionsListTable.getColumnModel().getColumn(3).setPreferredWidth(45);
		questionsListTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent evt) {
				questionsListSelection(evt);
			}
		});
//		questionsListTable.getModel().addTableModelListener(new TableModelListener() {
//			@Override
//			public void tableChanged(TableModelEvent evt) {
//				System.out.println("Table Changed Event");
//				questionsListSelection(evt);			
//			}
//		});
//		questionsList = new JList();
//		questionsList = new JList(questionsListDataModel);
//		questionsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		questionsList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
//		questionsList.setVisibleRowCount(-1);
//		questionsList.setFixedCellWidth(350);
//		if(questionsListDataModel.getSize() > 0) {
//				questionsList.setSelectedIndex(0);
//				deleteButton.setEnabled(true);
//				editButton.setEnabled(true);
//		}
//		questionsListTable.addListSelectionListener(new ListSelectionListener() {
//			public void valueChanged(ListSelectionEvent evt) {
//				questionsListSelection(evt);
//			}
//		});
		//questionsScrollPane = new JScrollPane(questionsList);
		questionsScrollPane = new JScrollPane(questionsListTable);
		

		filterPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createCompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), 
						BorderFactory.createBevelBorder(BevelBorder.RAISED)), "Filter"));
		
		/* Filter Panel Layout */
		GroupLayout filterPanelLayout = new GroupLayout(filterPanel);
		filterPanel.setLayout(filterPanelLayout);
		filterPanelLayout.setAutoCreateGaps(true);
		filterPanelLayout.setAutoCreateContainerGaps(true);
		filterPanelLayout.setHorizontalGroup(filterPanelLayout.createSequentialGroup()
					.addGroup(filterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(subjectFilterLabel)
						.addComponent(gradeLevelFilterLabel))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(filterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(subjectFilterComboBox, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
						.addComponent(gradeLevelFilterComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(filterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(filterPanelLayout.createSequentialGroup()
							.addComponent(topicFilterLabel)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(topicFilterComboBox))
						.addGroup(filterPanelLayout.createSequentialGroup()
							.addComponent(searchFilterTextField)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(searchFilterButton)))
		);
		filterPanelLayout.linkSize(SwingConstants.HORIZONTAL, topicFilterLabel, gradeLevelFilterLabel);
		
		filterPanelLayout.setVerticalGroup(filterPanelLayout.createSequentialGroup()
				.addGroup(filterPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(subjectFilterLabel)
						.addComponent(subjectFilterComboBox)
						.addComponent(topicFilterLabel)
						.addComponent(topicFilterComboBox))
				.addGroup(filterPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(gradeLevelFilterLabel)
						.addComponent(gradeLevelFilterComboBox)
						.addComponent(searchFilterTextField)
						.addComponent(searchFilterButton))
		);	
		
		/* Button Panel Layout */
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		buttonPanel.add(deleteButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(editButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPanel.add(newButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		
		questionsPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createCompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true),
						BorderFactory.createBevelBorder(BevelBorder.RAISED)), "Available Questions"));
		
		/* Questions Panel Layout */
		GroupLayout questionsPanelLayout = new GroupLayout(questionsPanel);
		questionsPanel.setLayout(questionsPanelLayout);
		questionsPanelLayout.setAutoCreateGaps(true);
		questionsPanelLayout.setAutoCreateContainerGaps(true);
		questionsPanelLayout.setHorizontalGroup(questionsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(questionsScrollPane)
				.addComponent(buttonPanel)
		);
	
		questionsPanelLayout.setVerticalGroup(questionsPanelLayout.createSequentialGroup()
				.addComponent(questionsScrollPane)
				.addComponent(buttonPanel)
		);	
		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(filterPanel)
				.addComponent(questionsPanel)
				.addComponent(backButton)
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(filterPanel)
				.addComponent(questionsPanel)
				.addComponent(backButton)
		);
	}
		
//	/**
//	 * Sets the topicFilterComboBox list
//	 * Caution: This will erase the existing list
//	 * @param list the list of elements to be set
//	 */
//	public void setTopicFilter(Object[] list) {
//		topicFilterDataModel.setElements(list);
//	}
//	
//	/**
//	 * Sets the topicFilterComboBox list
//	 * Caution: This will erase the existing list
//	 * @param c the collection of elements to be set
//	 */
//	public void setTopicFilter(Collection<?> c) {
//		topicFilterDataModel.setElements(c);
//	}
	
	public void setTopicFilterComboBoxModel(ComboBoxModel cbm) {
		topicFilterComboBox.setModel(cbm);
	}
	
//	/**
//	 * Sets the subjectFilterComboBox list
//	 * Caution: This will erase the existing list
//	 * @param list the list of elements to be set
//	 */
//	public void setSubjectFilter(Object[] list) {
//		subjectFilterDataModel.setElements(list);
//	}
//	
//	/**
//	 * Sets the subjectFilterComboBox list
//	 * Caution: This will erase the existing list
//	 * @param c the collection of elements to be set
//	 */
//	public void setSubjectFilter(Collection<?> c) {
//		subjectFilterDataModel.setElements(c);
//	}
	
	public void setSubjectFilterComboBoxModel(ComboBoxModel cbm) {
		subjectFilterComboBox.setModel(cbm);
	}
	
//	/**
//	 * Sets the gradeLevelFilterComboBox list
//	 * Caution: This will erase the existing list
//	 * @param list the list of elements to be set
//	 */
//	public void setGradeLevelFilter(Object[] list) {
//		gradeLevelFilterDataModel.setElements(list);
//	}
//	
//	/**
//	 * Sets the gradeLevelFilterComboBox list
//	 * Caution: This will erase the existing list
//	 * @param c the collection of elements to be set
//	 */
//	public void setGradeLevelFilter(Collection<?> c) {
//		gradeLevelFilterDataModel.setElements(c);
//	}
	
	public void setGradeLevelFilterComboBoxModel(ComboBoxModel cbm) {
		gradeLevelFilterComboBox.setModel(cbm);
	}
//	
//	public void setQuestionsList(Object[] list) {
//		questionsListDataModel.setElements(list);
//	}
//	
//	public void setQuestionsList(Collection<?> c) {
//		questionsListDataModel.setElements(c);
//	}
//	
//	private void searchButtonActionPerformed(ActionEvent evt) {
//		topicFilterDataModel.addElement(new Topic(1, "Addition", new Subject(1, "Math"), 1));
//		//testVector.add(new Topic(1, "Addition", new Subject(1, "Math"), 1));
//		for (Topic myString : testVector)
//			System.out.println(myString);
//	}
	
	public String getSearchText() {
		return searchFilterTextField.getText();
	}
	
	private void questionsListSelection(ListSelectionEvent evt) {
		deleteButton.setEnabled(true);
		editButton.setEnabled(true);		
	}
	
	public void addSearchButtonListener(ActionListener al) {
		searchFilterButton.addActionListener(al);
	}
	
	public void addBackButtonListener(ActionListener al) {
		backButton.addActionListener(al);
	}
	
	public void addSubjectFilterListener(ActionListener al) {
		subjectFilterComboBox.addActionListener(al);
	}
	
	public void addTopicFilterListener(ActionListener al) {
		topicFilterComboBox.addActionListener(al);
	}
	
	public void addGradeLevelFilterListener(ActionListener al) {
		gradeLevelFilterComboBox.addActionListener(al);
	}
	
	public void addNewQuestionButtonListener(ActionListener al) {
		newButton.addActionListener(al);
	}
	
	public void addEditQuestionButtonListener(ActionListener al) {
		editButton.addActionListener(al);
	}
	
	public void addDeleteQuestionButtonListener(ActionListener al) {
		deleteButton.addActionListener(al);
	}
	
//	public Question getSelectedQuestion() {
//		return new Question();
//	}
}


