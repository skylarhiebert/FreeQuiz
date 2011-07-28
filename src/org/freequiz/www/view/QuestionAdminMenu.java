/**
 * 
 */
package org.freequiz.www.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import org.freequiz.www.library.FQDefaultComboBoxModel;
import org.freequiz.www.library.FQQuestionsTableModel;


/**
 * @author Skylar Hiebert
 *
 */
public class QuestionAdminMenu extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4225972790538476617L;
	public static final int PAGESIZESMALL = 25;
	public static final int PAGESIZEMEDIUM = 50;
	public static final int PAGESIZELARGE = 100;
	private JPanel filterPanel;
	private JPanel pagePanel;
	private JLabel topicFilterLabel;
	private JLabel subjectFilterLabel;
	private JLabel gradeLevelFilterLabel;
	private JLabel difficultyFilterLabel;
	private JComboBox topicFilterComboBox;
	private JComboBox subjectFilterComboBox;
	private JComboBox gradeLevelFilterComboBox;
	private JComboBox difficultyFilterComboBox;
	private JTextField searchFilterTextField;
	private JButton searchFilterButton;
	private JButton clearFilterButton;
	private JPanel questionsPanel;
	private JScrollPane questionsScrollPane;
	private JTable questionsListTable;
	private JPanel buttonPanel;
	private JButton newButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton backButton;
	private JLabel pageLabel;
	private JButton nextPageButton;
	private JButton prevPageButton;
	private JRadioButton smallPageSizeRadioButton;
	private JRadioButton mediumPageSizeRadioButton;
	private JRadioButton largePageSizeRadioButton;
	private ButtonGroup pageSizeButtonGroup;
	
	/**
	 * Class constructor
	 */
	public QuestionAdminMenu() {
		super();
		initComponents();
//		questionsListTable.
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
	// TODO Set tab order and keyboard shortcuts
	private void initComponents() {
		/* Control Buttons */
		backButton = new JButton("Back to Main Menu");

		/* Filter Panel  */
		filterPanel = new JPanel();
		topicFilterLabel = new JLabel("Topic:");
		topicFilterComboBox = new JComboBox(new DefaultComboBoxModel());
		subjectFilterLabel = new JLabel("Subject:");
		subjectFilterComboBox = new JComboBox();
		subjectFilterComboBox = new JComboBox(new DefaultComboBoxModel());
		gradeLevelFilterLabel = new JLabel("Grade Level:");
		gradeLevelFilterComboBox = new JComboBox();
		gradeLevelFilterComboBox = new JComboBox(new DefaultComboBoxModel());
		difficultyFilterLabel = new JLabel("Difficulty:");
		difficultyFilterComboBox = new JComboBox(new DefaultComboBoxModel());
		difficultyFilterComboBox.setSelectedIndex(-1);
		clearFilterButton = new JButton("Clear");
		clearFilterButton.setPreferredSize(new Dimension(clearFilterButton.getWidth(), 14));
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
		pagePanel = new JPanel();
		pageLabel = new JLabel("Results per page:");
		nextPageButton = new JButton(">>");
		prevPageButton = new JButton("<<");
		nextPageButton.setEnabled(false);
		prevPageButton.setEnabled(false);
		smallPageSizeRadioButton = new JRadioButton(Integer.toString(PAGESIZESMALL));
		smallPageSizeRadioButton.setActionCommand(Integer.toString(PAGESIZESMALL));
		mediumPageSizeRadioButton = new JRadioButton(Integer.toString(PAGESIZEMEDIUM));
		mediumPageSizeRadioButton.setActionCommand(Integer.toString(PAGESIZEMEDIUM));
		largePageSizeRadioButton = new JRadioButton(Integer.toString(PAGESIZELARGE));
		largePageSizeRadioButton.setActionCommand(Integer.toString(PAGESIZELARGE));
		pageSizeButtonGroup = new ButtonGroup();
		pageSizeButtonGroup.add(smallPageSizeRadioButton);
		pageSizeButtonGroup.add(mediumPageSizeRadioButton);
		pageSizeButtonGroup.add(largePageSizeRadioButton);
		pageSizeButtonGroup.setSelected(smallPageSizeRadioButton.getModel(), true);
		questionsListTable = new JTable(new FQQuestionsTableModel()) {
			public TableCellRenderer getCellRenderer(int row, int col) {
				TableCellRenderer renderer = super.getCellRenderer(row,col);
			
				if(col == 3 || col == 4) 
					((JLabel)renderer).setHorizontalAlignment(SwingConstants.CENTER);
				else
					((JLabel)renderer).setHorizontalAlignment(SwingConstants.LEFT);

		      return renderer;
			}
		};
		questionsListTable.setAutoCreateRowSorter(true);
		questionsListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		questionsListTable.setRowSelectionAllowed(true);
		questionsListTable.getColumnModel().getColumn(0).setPreferredWidth(300);
		questionsListTable.getColumnModel().getColumn(3).setPreferredWidth(75);
		questionsListTable.getColumnModel().getColumn(3).setMaxWidth(75);
		questionsListTable.getColumnModel().getColumn(4).setMaxWidth(75);
		questionsListTable.setAutoCreateColumnsFromModel(false);
		questionsListTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent evt) {
				questionsListSelection(evt);
			}
		});
		questionsScrollPane = new JScrollPane(questionsListTable);

		pagePanel.setLayout(new BoxLayout(pagePanel, BoxLayout.LINE_AXIS));
		pagePanel.add(prevPageButton);
		pagePanel.add(questionsScrollPane);
		pagePanel.add(nextPageButton);

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
					.addGroup(filterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(subjectFilterComboBox)
						.addGroup(filterPanelLayout.createSequentialGroup()
								.addComponent(gradeLevelFilterComboBox)
								.addComponent(difficultyFilterLabel)
								.addComponent(difficultyFilterComboBox)))
					.addGroup(filterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(filterPanelLayout.createSequentialGroup()
							.addComponent(topicFilterLabel)
							.addComponent(topicFilterComboBox))
						.addGroup(filterPanelLayout.createSequentialGroup()
							.addComponent(searchFilterTextField)
							.addComponent(searchFilterButton)
							.addComponent(clearFilterButton)))
		);
		
		filterPanelLayout.setVerticalGroup(filterPanelLayout.createSequentialGroup()
				.addGroup(filterPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(subjectFilterLabel)
						.addComponent(subjectFilterComboBox)
						.addComponent(topicFilterLabel)
						.addComponent(topicFilterComboBox))
				.addGroup(filterPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(gradeLevelFilterLabel)
						.addComponent(gradeLevelFilterComboBox)
						.addComponent(difficultyFilterLabel)
						.addComponent(difficultyFilterComboBox)
						.addComponent(searchFilterTextField)
						.addComponent(searchFilterButton)
						.addComponent(clearFilterButton))
		);	
		filterPanelLayout.linkSize(SwingConstants.HORIZONTAL, searchFilterButton, clearFilterButton);
		
		/* Button Panel Layout */
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(Box.createRigidArea(new Dimension(2, 0)));
		buttonPanel.add(deleteButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(editButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPanel.add(newButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(2, 0)));
		
		questionsPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createCompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true),
						BorderFactory.createBevelBorder(BevelBorder.RAISED)), "Available Questions"));
		
		/* Questions Panel Layout */
		GroupLayout questionsPanelLayout = new GroupLayout(questionsPanel);
		questionsPanel.setLayout(questionsPanelLayout);
		questionsPanelLayout.setAutoCreateGaps(true);
		questionsPanelLayout.setAutoCreateContainerGaps(true);
		questionsPanelLayout.setHorizontalGroup(questionsPanelLayout.createSequentialGroup()
				.addComponent(prevPageButton)
				.addGroup(questionsPanelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addGroup(questionsPanelLayout.createSequentialGroup()
								.addComponent(pageLabel)
								.addComponent(smallPageSizeRadioButton)
								.addComponent(mediumPageSizeRadioButton)
								.addComponent(largePageSizeRadioButton))
						.addComponent(questionsScrollPane)
						.addComponent(buttonPanel))
				.addComponent(nextPageButton)
		);
	
		questionsPanelLayout.setVerticalGroup(questionsPanelLayout.createSequentialGroup()
				.addGroup(questionsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(pageLabel)
						.addComponent(smallPageSizeRadioButton)
						.addComponent(mediumPageSizeRadioButton)
						.addComponent(largePageSizeRadioButton))				
				.addGroup(questionsPanelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(prevPageButton)
						.addComponent(questionsScrollPane)
						.addComponent(nextPageButton))
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
		
	public void setTopicFilterComboBoxModel(ComboBoxModel cbm) {
		topicFilterComboBox.setModel(cbm);		
	}
	
	public void setSubjectFilterComboBoxModel(ComboBoxModel cbm) {
		subjectFilterComboBox.setModel(cbm);
	}
	
	public void setGradeLevelFilterComboBoxModel(ComboBoxModel cbm) {
		gradeLevelFilterComboBox.setModel(cbm);
		gradeLevelFilterComboBox.setSelectedIndex(-1);
	}
	
	public void setDifficultyFilterComboBoxModel(ComboBoxModel cbm) {
		difficultyFilterComboBox.setModel(cbm);
		difficultyFilterComboBox.setSelectedIndex(-1);
	}
	
	public void setQuestionTableModel(TableModel tm) {
		questionsListTable.setModel(tm);
	}
	
	public String getSearchText() {
		return searchFilterTextField.getText();
	}
	
	private void questionsListSelection(ListSelectionEvent evt) {
		deleteButton.setEnabled(true);
		editButton.setEnabled(true);		
	}
	
	public int getSelectedRow() {
		return questionsListTable.getSelectedRow();
	}
	
	public void clearFilterSelections() {
		topicFilterComboBox.setSelectedIndex(-1);
		subjectFilterComboBox.setSelectedIndex(-1);
		gradeLevelFilterComboBox.setSelectedIndex(-1);
		difficultyFilterComboBox.setSelectedIndex(-1);
	}
	
	public void addSearchButtonListener(ActionListener al) {
		searchFilterButton.addActionListener(al);
	}
	
	public void addClearFilterButtonListener(ActionListener al) {
		clearFilterButton.addActionListener(al);
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
	
	public void addDifficultyFilterListener(ActionListener al) {
		difficultyFilterComboBox.addActionListener(al);
	}
	
	public void addNewQuestionButtonListener(ActionListener al) {
		newButton.addActionListener(al);
	}
	
	public void addNextPageButtonListener(ActionListener al) {
		nextPageButton.addActionListener(al);
	}
	
	public void addPrevPageButtonListener(ActionListener al) {
		prevPageButton.addActionListener(al);
	}
	
	public void addEditQuestionButtonListener(ActionListener al) {
		editButton.addActionListener(al);
	}
	
	public void addDeleteQuestionButtonListener(ActionListener al) {
		deleteButton.addActionListener(al);
	}
	
	public int getPageSize() {
		return Integer.parseInt(pageSizeButtonGroup.getSelection().getActionCommand());
	}
	
	public void enableNextPageButton() {
		nextPageButton.setEnabled(true);
	}
	
	public void disableNextPageButton() {
		nextPageButton.setEnabled(false);
	}
	
	public void enablePrevPageButton() {
		prevPageButton.setEnabled(true);
	}
	
	public void disablePrevPageButton() {
		prevPageButton.setEnabled(false);
	}
	
//	public Question getSelectedQuestion() {
//		return new Question();
//	}
	
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
}


