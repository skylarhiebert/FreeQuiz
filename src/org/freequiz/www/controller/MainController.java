/**
 * 
 */
package org.freequiz.www.controller;

import org.freequiz.www.dao.SubjectsDao;
import org.freequiz.www.dao.TopicsDao;
import org.freequiz.www.library.FQDefaultComboBoxModel;
import org.freequiz.www.library.FQDefaultListModel;
//import org.freequiz.www.questions.controller.QuestionsController;
import org.freequiz.www.view.FreeQuizFrame;
import org.freequiz.www.view.QuestionAdminMenuPanel;



/**
 * @author Skylar Hiebert
 *
 */
public class MainController {
//	QuestionsController questionsController;
	private FreeQuizFrame mainFrame;
	private QuestionAdminMenuPanel questionAdminPanel;
	private TopicsDao topicsDao;
	private SubjectsDao subjectsDao;
	
	public MainController () {
		initComponents();
		mainFrame.setContentPane(questionAdminPanel);
	}
	
	private void initComponents() {
		mainFrame = new FreeQuizFrame();
		mainFrame.setFrameTitle("Question Administration Menu");
		topicsDao = new TopicsDao();
		subjectsDao = new SubjectsDao();
		questionAdminPanel = new QuestionAdminMenuPanel();
		questionAdminPanel.setTopicFilter(topicsDao.getAllTopicsArrayList());
		questionAdminPanel.setSubjectFilter(subjectsDao.getAllTopicsArrayList());
	}
	
	public void run() {
		mainFrame.setVisible(true);
	}
//	private FQDefaultComboBoxModel topicComboBoxDataModel;
//	private FQDefaultComboBoxModel subjectComboBoxDataModel;
//	private FQDefaultComboBoxModel gradeLevelComboBoxDataModel;
//	private FQDefaultListModel questionsListDataModel;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
            	MainController controller = new MainController();
            	controller.run(); 
            }
        });

	}
}
