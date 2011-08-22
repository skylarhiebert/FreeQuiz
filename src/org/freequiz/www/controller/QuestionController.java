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
package org.freequiz.www.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.freequiz.www.dao.AbstractDAOFactory;
import org.freequiz.www.dao.QuestionDAO;
import org.freequiz.www.dao.SubjectDAO;
import org.freequiz.www.dao.TopicDAO;
import org.freequiz.www.library.FQDefaultComboBoxModel;
import org.freequiz.www.library.FQQuestionsTableModel;
import org.freequiz.www.model.Question;
import org.freequiz.www.model.Subject;
import org.freequiz.www.model.Topic;
import org.freequiz.www.view.EditDialogFrame;
import org.freequiz.www.view.EditQuestionDialogPanel;
import org.freequiz.www.view.QuestionAdminMenu;

/**
 * @author Skylar Hiebert
 *
 */
public class QuestionController {
	private int pageIndex;
	private Subject selectedSubjectFilter;
	private Topic selectedTopicFilter;
	private String selectedGradeLevelFilter;
	private Long selectedDifficultyFilter;
	private MainController mainController;
	private Vector<Topic> topicList;
	private Vector<Subject> subjectList;
	private Vector<String> gradeLevelList;
	private Vector<Long> difficultyList;
	private Vector<Question> questionList;
	private QuestionAdminMenu questionsPanel;
	private EditDialogFrame editQuestionDialog;
	private EditQuestionDialogPanel editQuestionPanel;
	private FQDefaultComboBoxModel subjectComboBoxModel;
	private FQDefaultComboBoxModel topicComboBoxModel;
	private FQDefaultComboBoxModel gradeLevelComboBoxModel;
	private FQDefaultComboBoxModel difficultyComboBoxModel;
	private FQDefaultComboBoxModel editSubjectComboBoxModel;
	private FQDefaultComboBoxModel editTopicComboBoxModel;
	private FQDefaultComboBoxModel editGradeLevelComboBoxModel;
	private FQDefaultComboBoxModel editDifficultyComboBoxModel;
	private FQQuestionsTableModel questionsTableModel;
	private TopicDAO topicDAO;
	private SubjectDAO subjectDAO;
	private QuestionDAO questionDAO;


	
	public QuestionController(MainController mainController) {
		this.mainController = mainController;
		initComponents();
		setActionListeners();
	}
	
	private void initComponents() {
		pageIndex = 0;
		selectedSubjectFilter = null;
		selectedTopicFilter = null;
		selectedGradeLevelFilter = null;
		selectedDifficultyFilter = null;
		AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
		topicDAO = factory.getTopicDAO();
		subjectDAO = factory.getSubjectDAO();
		questionDAO = factory.getQuestionDAO();
		questionsPanel = new QuestionAdminMenu();
		editQuestionPanel = new EditQuestionDialogPanel();
		editQuestionDialog = new EditDialogFrame(mainController.mainFrame, editQuestionPanel, "Edit Question");
		editQuestionDialog.setMinimumSize(new Dimension(400, 450));
		editQuestionDialog.setLocationRelativeTo(mainController.mainFrame);
		subjectList = new Vector<Subject>();
		subjectComboBoxModel = new FQDefaultComboBoxModel(subjectList);
		questionsPanel.setSubjectFilterComboBoxModel(subjectComboBoxModel);
		editSubjectComboBoxModel = new FQDefaultComboBoxModel(subjectList);
		editQuestionPanel.setSubjectComboBoxModel(editSubjectComboBoxModel);
		topicList = new Vector<Topic>();
		topicComboBoxModel = new FQDefaultComboBoxModel(topicList);
		questionsPanel.setTopicFilterComboBoxModel(topicComboBoxModel);
		editTopicComboBoxModel = new FQDefaultComboBoxModel(topicList);
		editQuestionPanel.setTopicComboBoxModel(editTopicComboBoxModel);
		gradeLevelList = new Vector<String>();
		gradeLevelList.addAll(Arrays.asList(MainController.GRADELEVELS));
		gradeLevelComboBoxModel = new FQDefaultComboBoxModel(gradeLevelList);
		questionsPanel.setGradeLevelFilterComboBoxModel(gradeLevelComboBoxModel);
		editGradeLevelComboBoxModel = new FQDefaultComboBoxModel(gradeLevelList);
		editQuestionPanel.setGradeLevelComboBoxModel(editGradeLevelComboBoxModel);
		difficultyList = new Vector<Long>();
		difficultyList.addAll(Arrays.asList(MainController.DIFFICULTIES));
		difficultyComboBoxModel = new FQDefaultComboBoxModel(difficultyList);
		questionsPanel.setDifficultyFilterComboBoxModel(difficultyComboBoxModel);
		editDifficultyComboBoxModel = new FQDefaultComboBoxModel(difficultyList);
		editQuestionPanel.setDifficultyComboBoxModel(editDifficultyComboBoxModel);
		questionList = new Vector<Question>();
		questionsTableModel = new FQQuestionsTableModel(questionList);
		questionsPanel.setQuestionTableModel(questionsTableModel);
		
	}
	
	private void setActionListeners() {
		questionsPanel.addSearchButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				searchButtonActionPerformed(evt);	
			}
		});
		
		questionsPanel.addClearFilterButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				clearFilterButtonActionPerformed(evt);
			}
		});
		
		questionsPanel.addBackButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				backButtonActionPerformed(evt);
			}
		});
		
		questionsPanel.addNewQuestionButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				newButtonActionPerformed(evt);
			}
		});
		
		questionsPanel.addEditQuestionButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				editButtonActionPerformed(evt);
			}
		});
		
		questionsPanel.addDeleteQuestionButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				deleteButtonActionPerformed(evt);
			}
		});
		
		questionsPanel.addNextPageButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				nextPageActionPerformed(evt);
			}
		});
		
		questionsPanel.addPrevPageButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				prevPageActionPerformed(evt);
			}
		});
		
		questionsPanel.addSubjectFilterListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				subjectFilterChange(evt);
			}
		});
		
		questionsPanel.addTopicFilterListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				topicFilterChange(evt);
			}
		});
		
		questionsPanel.addGradeLevelFilterListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				gradeLevelFilterChange(evt);
			}
		});
		
		questionsPanel.addDifficultyFilterListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				difficultyFilterChange(evt);
			}
		});
		
		editQuestionDialog.addCancelButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				editQuestionCancelButtonActionPerformed(evt);
			}
		});
		
		editQuestionDialog.addSaveButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				editQuestionSaveButtonActionPerformed(evt);
			}
		});
		
		editQuestionPanel.addNewSubjectButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				newSubjectButtonActionPerformed(evt);
			}
		});
		
		editQuestionPanel.addNewTopicButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				newTopicButtonActionPerformed(evt);
			}
		});
	}
	
	public JPanel getQuestionsPanel() {
		clearFilterSelections();
		initializeFilterLists();
		questionsPanel.setVisible(true);
		return questionsPanel;
	}
	
	private void searchButtonActionPerformed(ActionEvent evt) {
		// TODO Add search methods to edit the displayed questions
		System.out.println("searchButton");
	}
	
	private void clearFilterButtonActionPerformed(ActionEvent evt) {
		clearFilterSelections(); // May not need from selection change events
		refreshQuestionList();
	}
	
	private void backButtonActionPerformed(ActionEvent evt) {
		topicComboBoxModel.removeAllElements();
		subjectComboBoxModel.removeAllElements();
		questionsTableModel.removeAllRows();
		questionsPanel.setVisible(false);
		mainController.showMainMenu();
	}
	
	private void newButtonActionPerformed(ActionEvent evt) {
//		setFilterSelections();
		editQuestionPanel.setQuestion(new Question());
		editQuestionDialog.setVisible(true);
		restoreFilterSelections();
	}
	
	private void editButtonActionPerformed(ActionEvent evt) {
//		setFilterSelections();
		editQuestionPanel.setQuestion(questionsTableModel.getQuestionAtRow(questionsPanel.getSelectedRow()));	
//		editQuestionPanel.setQuestion(questionList.get(questionsPanel.getSelectedRow()));
		editQuestionDialog.setVisible(true);
		restoreFilterSelections();
	}
	
	private void deleteButtonActionPerformed(ActionEvent evt) {
		Question deleteQuestion = questionList.get(questionsPanel.getSelectedRow()); 
		Topic oldTopic = deleteQuestion.getTopic();
		Subject oldSubject = oldTopic.getSubject();
		assert(deleteQuestion.getQuestionid() != null);
		assert(oldTopic != null);
		assert(oldSubject != null);
		questionDAO.beginTransaction();
		int yesNo =  JOptionPane.showConfirmDialog(mainController.mainFrame, 
				"Are you sure you want to permanently delete\n\n" + deleteQuestion + "\n\nfrom the database?", 
				"Delete a question?", JOptionPane.YES_NO_OPTION);
		if(yesNo == JOptionPane.YES_OPTION) {
			deleteQuestion.setTopic(null); // Prepare question for deletion
			questionDAO.delete(deleteQuestion);
			questionsTableModel.removeRow(deleteQuestion);
		}
		// Check if Topic is orphaned
		if(oldTopic != null && questionDAO.getCount(oldTopic) == 0) {
			if(deleteTopic(oldTopic)) {
				topicDAO.delete(oldTopic);
				topicComboBoxModel.removeElement(oldTopic);
			}
		}
		// Check if Subject is orphaned

		if(oldSubject != null && topicDAO.getCount(oldSubject) == 0) {
			if(deleteSubject(oldSubject)) {
				subjectDAO.delete(oldSubject);
				subjectComboBoxModel.removeElement(oldSubject);
			}
		}
		questionDAO.commitTransaction();
		editQuestionDialog.dispose();

	}
	
	private void editQuestionSaveButtonActionPerformed(ActionEvent evt) {
		if(editQuestionPanel.validateInputs()) {
			Subject editSubject = editQuestionPanel.getSelectedSubject();
			Topic editTopic = editQuestionPanel.getSelectedTopic();
			Question editQuestion = editQuestionPanel.getQuestion();
			Topic oldTopic = null;
			Subject oldSubject = null;	
			oldTopic = editQuestion.getTopic();
			if(oldTopic != null)
				oldSubject = oldTopic.getSubject();
			boolean newSubject = (editSubject.getSubjectid() == null);
			boolean newTopic = (newSubject || editTopic.getTopicid() == null || 
					(oldSubject != null && !editTopic.getSubject().equals(oldSubject)));
			boolean newQuestion = (editQuestion.getQuestionid() == null);
			editQuestion.setQuestionText(editQuestionPanel.getQuestionText());
			editQuestion.setAnswerText(editQuestionPanel.getAnswerText());
			editQuestion.setGradeLevel(editQuestionPanel.getGradeLevel());
			editQuestion.setDifficulty(editQuestionPanel.getSelectedDifficulty());
			try {
				questionDAO.beginTransaction();
				if(newSubject) { 
					subjectDAO.save(editSubject); 
				}
				if(newTopic) { 
					if(editTopic.getTopicid() == null) { // Create New Topic
						System.err.println("New Topic");
						editTopic.setSubject(editSubject);
					} else { // Change Subject for Topic
						Topic topic = new Topic();
						topic.setSubject(editSubject);
						topic.setTopicText(editTopic.getTopicText());
						editTopic = topic;
					}
					topicDAO.save(editTopic);
				}
				editQuestion.setTopic(editTopic);
				questionDAO.save(editQuestion);
				questionDAO.commitTransaction();
				if(newQuestion) {
					refreshQuestionList();
				} else {
					questionDAO.beginTransaction();
					if(oldSubject != null && topicDAO.getCount(oldSubject) == 0) { // Check for orphaned Subject
						if(deleteSubject(oldSubject)) {
							subjectDAO.delete(oldSubject);
							subjectComboBoxModel.removeElement(oldSubject);
						}
					}
					if(oldTopic != null && questionDAO.getCount(oldTopic) == 0) { // Check for orphaned Topic
						if(deleteTopic(oldTopic)) {
							topicDAO.delete(oldTopic);
							topicComboBoxModel.removeElement(oldTopic);
						}
					}
					questionDAO.commitTransaction();
				}			
			} catch (Exception ex) {
				//TODO Display error message for save failure
				System.err.println("error attempting to save " + ex);
			}
			editQuestionDialog.dispose();
		} else {
			// TODO Display error message for input errors
			System.err.println("Input Errors on edit Question panel");
		}
	}
	
	private void editQuestionCancelButtonActionPerformed(ActionEvent evt) {
		Subject cancelSubject = editQuestionPanel.getSelectedSubject();
		Topic cancelTopic = editQuestionPanel.getSelectedTopic();
		if(cancelSubject.getSubjectid() == null)
			editSubjectComboBoxModel.removeElement(cancelSubject);
		if(cancelTopic.getTopicid() == null)
			editTopicComboBoxModel.removeElement(cancelTopic);
		editQuestionDialog.dispose();
	}	
	
	private void newSubjectButtonActionPerformed(ActionEvent evt) {
		String subjectString = (String) JOptionPane.showInputDialog(mainController.mainFrame, 
				"New Subject:", 
				"New Subject", 
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				null);
		
		if(subjectString != null && subjectString.length() > 0) {
			Subject newSubject = new Subject(subjectString);
			subjectDAO.beginTransaction();
			List<Subject> dbSubject = subjectDAO.findByExample(newSubject, null);
			subjectDAO.commitTransaction();
			if(dbSubject != null && dbSubject.size() > 0) {
				int yesNo = JOptionPane.showConfirmDialog(mainController.mainFrame, 
						"Subject \"" + newSubject + "\" already exists in the database.\n" +
							"Would you like to use the existing subject?",
						"Subject already exists...", JOptionPane.YES_NO_OPTION);
				if(yesNo == JOptionPane.YES_OPTION) {
					if(dbSubject.size() == 1) {
						editSubjectComboBoxModel.setSelectedItem(dbSubject.get(0));
						return;
					} else
						System.err.println("More than one matching subject");
				} 
			} 
			editSubjectComboBoxModel.addElement(newSubject);
			editSubjectComboBoxModel.setSelectedItem(newSubject);
		} else if (subjectString != null && subjectString.length() == 0) {
			// No input string, redisplay
			newSubjectButtonActionPerformed(evt);
		}
	}
	
	private void newTopicButtonActionPerformed(ActionEvent evt) {
		String topicString = (String) JOptionPane.showInputDialog(mainController.mainFrame, 
				"New Topic:", 
				"New Topic", 
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				null);
		
		if(topicString != null && topicString.length() > 0) {
			Topic newTopic = new Topic(topicString);
			Subject editSubject = (Subject) editSubjectComboBoxModel.getSelectedItem();
			if(editSubject.getSubjectid() != null)
				newTopic.setSubject(editSubject); 
			topicDAO.beginTransaction();
			List<Topic> dbTopic = topicDAO.findByExample(newTopic, null);
			topicDAO.commitTransaction();
			if(dbTopic != null && dbTopic.size() > 0) {
				int yesNo = JOptionPane.showConfirmDialog(mainController.mainFrame, 
						"Topic \"" + dbTopic.get(0) + "\" with subject \"" + dbTopic.get(0).getSubject() + 
						"\" already exists in the database.\nWould you like to use the existing topic?",
						"Topic already exists...", JOptionPane.YES_NO_OPTION);
				if(yesNo == JOptionPane.YES_OPTION) { 
					if(dbTopic.size() == 1) {
						editTopicComboBoxModel.setSelectedItem(dbTopic.get(0));
						return;
					} else
						System.err.println("More than one matching topic");
				}
			}
			editTopicComboBoxModel.addElement(newTopic);
			editTopicComboBoxModel.setSelectedItem(newTopic);
		} else if (topicString != null && topicString.length() == 0) {
			// No input string, redisplay
			newTopicButtonActionPerformed(evt);
		}
	}
	
	private void subjectFilterChange(ActionEvent evt) {
		pageIndex = 0;
		if(subjectComboBoxModel.getSelectedItemIndex() >= 0)
			selectedSubjectFilter = (Subject) subjectComboBoxModel.getSelectedItem();
		else
			selectedSubjectFilter = null;
				
		refreshTopicFilterList(); // Reset topic selection options based on subject
	}
	
	private void topicFilterChange(ActionEvent evt) {
		pageIndex = 0;
		if(topicComboBoxModel.getSelectedItemIndex() >= 0)
			selectedTopicFilter = (Topic) topicComboBoxModel.getSelectedItem();
		else
			selectedTopicFilter = null;
		refreshQuestionList();
	}
	
	private void gradeLevelFilterChange(ActionEvent evt) {
		pageIndex = 0;
		if(gradeLevelComboBoxModel.getSelectedItemIndex() >= 0)
			selectedGradeLevelFilter = (String) gradeLevelComboBoxModel.getSelectedItem();
		else
			selectedGradeLevelFilter = null;
		refreshQuestionList();
	}
	
	private void difficultyFilterChange(ActionEvent evt) {
		pageIndex = 0;
		if(difficultyComboBoxModel.getSelectedItemIndex() >= 0)
			selectedDifficultyFilter = (Long) difficultyComboBoxModel.getSelectedItem();
		else
			selectedDifficultyFilter = null;
		refreshQuestionList();
	}
		
	private void nextPageActionPerformed(ActionEvent evt) {
		pageIndex += questionsPanel.getPageSize();
		refreshQuestionList();
	}
	
	private void prevPageActionPerformed(ActionEvent evt) {
		pageIndex -= questionsPanel.getPageSize();
		if(pageIndex < 0) pageIndex = 0;
		refreshQuestionList();
	}
	
	private void clearFilterSelections() {
		selectedSubjectFilter = null;
		subjectComboBoxModel.setSelectedItemIndex(-1);
		selectedTopicFilter = null;
		topicComboBoxModel.setSelectedItemIndex(-1);
		selectedGradeLevelFilter = null;
		gradeLevelComboBoxModel.setSelectedItemIndex(-1);
		selectedDifficultyFilter = null;
		difficultyComboBoxModel.setSelectedItemIndex(-1);
	}
	
	private void initializeFilterLists() {
		topicComboBoxModel.removeAllElements();
		subjectComboBoxModel.removeAllElements();
		questionsTableModel.removeAllRows();
		topicDAO.beginTransaction();
		topicList.addAll(topicDAO.findAll());
		subjectList.addAll(subjectDAO.findAll());		
//		if(questionDAO.getCount() > questionsPanel.getPageSize()) 
//			questionsPanel.enableNextPageButton();
//		System.err.println("Range; PageIndex:" + pageIndex + "  pageSize:" + questionsPanel.getPageSize());
//		questionsTableModel.addRows(questionDAO.findRange(pageIndex, questionsPanel.getPageSize()));
		topicDAO.commitTransaction();
		refreshQuestionList();
	}
	
	private void refreshTopicFilterList() {
		try {
			// Disable listeners for programmed update
			javax.swing.event.ListDataListener[] listeners = topicComboBoxModel.getListDataListeners();
			for(javax.swing.event.ListDataListener el: listeners)
				topicComboBoxModel.removeListDataListener(el);
			topicComboBoxModel.removeAllElements();
			topicDAO.beginTransaction();
			if(selectedSubjectFilter != null && selectedSubjectFilter.getSubjectid() != null)
				topicComboBoxModel.addAll(topicDAO.findBySubject(selectedSubjectFilter));
			else
				topicComboBoxModel.addAll(topicDAO.findAll());
			topicDAO.commitTransaction();
			// Re-enable listeners
			for(javax.swing.event.ListDataListener el : listeners)
				topicComboBoxModel.addListDataListener(el);
			if(selectedTopicFilter == null || topicComboBoxModel.getIndexOf(selectedTopicFilter) < 0)	
				topicComboBoxModel.setSelectedItemIndex(-1);
			else 
				topicComboBoxModel.setSelectedItem(selectedTopicFilter);
		} catch (Exception ex) {
			System.err.println("RefreshTopicFilterList Exception: " + ex);
			// TODO Generate Database error message
		}
	}
	
	private void refreshQuestionList() {
		int pageSize = questionsPanel.getPageSize();
		Question example = new Question();
		example.setTopic(selectedTopicFilter);
		example.setGradeLevel(selectedGradeLevelFilter);
		example.setDifficulty(selectedDifficultyFilter);

		try {
			questionDAO.beginTransaction();
			questionsTableModel.removeAllRows();
			if(pageIndex == 0)
				questionsPanel.disablePrevPageButton();
			if(pageIndex > 0)
				questionsPanel.enablePrevPageButton();
			if(selectedSubjectFilter != null && selectedTopicFilter == null) { // Filter on subject without topic
				if(selectedGradeLevelFilter != null || selectedDifficultyFilter != null) {
					if(questionDAO.getCountByExampleWithSubject(example, selectedSubjectFilter, null) > pageIndex + pageSize)
						questionsPanel.enableNextPageButton();
					else
						questionsPanel.disableNextPageButton();
					questionsTableModel.addRows(questionDAO.findRangeByExampleWithSubject(pageIndex, pageSize, example, selectedSubjectFilter, null));
				} else {
					if(questionDAO.getCount(selectedSubjectFilter) > pageIndex + pageSize)
						questionsPanel.enableNextPageButton();
					else
						questionsPanel.disableNextPageButton();
					questionsTableModel.addRows(questionDAO.findRangeBySubject(pageIndex, pageSize, selectedSubjectFilter));
				}
			} else { // Filter on topic, subject is inherent in topic
				if(questionDAO.getCountByExample(example, null) > pageIndex + pageSize) 
					questionsPanel.enableNextPageButton();
				else
					questionsPanel.disableNextPageButton();
				questionsTableModel.addRows(questionDAO.findRangeByExample(pageIndex, pageSize, example, null));
			} 

			questionDAO.commitTransaction();
			questionsTableModel.fireTableDataChanged();
		} catch (Exception ex) {
			System.err.println("RefreshQuestionFilterList Exception: " + ex);
			// TODO Generate Database error message
		}
	}
	
//	private void setTopicList(Subject subject) {
//		try {
//			System.err.println("Beginning Transaction - SetTopicList");
//			topicDAO.beginTransaction();
//			topicComboBoxModel.removeAllElements();
//			topicComboBoxModel.addAll(topicDAO.findBySubject(subject));
//			System.err.println("Committing Transaction SetTopicList");
//			topicDAO.commitTransaction();
//		} catch (Exception ex) {
//			System.err.println("setTopicList Exception: " + ex);
//			// TODO Generate Database error message
//		}
//	}
	
//	private void setFilterSelections() {
//		selectedSubjectIndex = subjectComboBoxModel.getIndexOf(subjectComboBoxModel.getSelectedItem());
//		selectedTopicIndex = topicComboBoxModel.getIndexOf(topicComboBoxModel.getSelectedItem());
//		selectedGradeLevelIndex = gradeLevelComboBoxModel.getIndexOf(gradeLevelComboBoxModel.getSelectedItem());
//		selectedDifficultyIndex = difficultyComboBoxModel.getIndexOf(difficultyComboBoxModel.getSelectedItem());
//	}
	
	private void restoreFilterSelections() {
		subjectComboBoxModel.setSelectedItem(selectedSubjectFilter);
		topicComboBoxModel.setSelectedItem(selectedTopicFilter);
		gradeLevelComboBoxModel.setSelectedItem(selectedGradeLevelFilter);
		difficultyComboBoxModel.setSelectedItem(selectedDifficultyFilter);
	}
	
	private boolean deleteSubject(Subject subject) {
		int yesNo = JOptionPane.showConfirmDialog(mainController.mainFrame, 
				"No more questions reference subject : " + subject + ". \n" +
						"Would you like to delete it from the database?",
							"Delete subject?", JOptionPane.YES_NO_OPTION);
		if(yesNo == JOptionPane.YES_OPTION) return true;
		else return false;
	}
		
	private boolean deleteTopic(Topic topic) {
		int yesNo = JOptionPane.showConfirmDialog(mainController.mainFrame, 
				"No more questions reference topic : " + topic + ". \n" +
						"Would you like to delete it from the database?",
							"Delete topic?", JOptionPane.YES_NO_OPTION);
		if(yesNo == JOptionPane.YES_OPTION) return true;
		else return false;
	}
	
//	private void saveEditedQuestion(Question) {
//		
//	}
}
