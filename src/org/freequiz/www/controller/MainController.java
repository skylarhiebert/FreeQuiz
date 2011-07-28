/**
 * 
 */
package org.freequiz.www.controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.freequiz.www.dao.AbstractDAOFactory;
import org.freequiz.www.dao.*;
import org.freequiz.www.dao.hibernate.HibernateUtil;
import org.freequiz.www.model.Question;
import org.freequiz.www.model.Roster;
import org.freequiz.www.model.Student;
import org.freequiz.www.model.Subject;
import org.freequiz.www.model.Topic;
import org.freequiz.www.view.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * @author Skylar Hiebert
 *
 */
public class MainController {
	protected QuestionController questionsController;
	protected RosterController classesController;
	protected MainFrame mainFrame;
	protected MainMenu mainMenuPanel;
	
	public MainController () {
		initComponents();
		setActionListeners();		
		showMainMenu();
	}
	
	private void initComponents() {
		HibernateUtil.initializeHibernate();
		questionsController = new QuestionController(this);
		classesController = new RosterController(this);
		mainFrame = new MainFrame();
		mainMenuPanel = new MainMenu();
	}
	
	private void setActionListeners() {
		mainFrame.addQuitMenuItemActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				quitActionPerformed(evt);
			}
		});
		
		mainMenuPanel.addClassAdminButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				classAdminActionPerformed(evt);	
			}
		});
		
		mainMenuPanel.addStudentAdminButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				studentAdminActionPerformed(evt);	
			}
		});
		
		mainMenuPanel.addQuestionAdminButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				questionAdminActionPerformed(evt);	
			}
		});
		
		mainMenuPanel.addNewGameButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				newGameActionPerformed(evt);	
			}
		});
		
		mainMenuPanel.addLoadGameButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				loadGameActionPerformed(evt);	
			}
		});
		
		mainMenuPanel.addReviewGameButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				reviewGameActionPerformed(evt);	
			}
		});
		
		mainMenuPanel.addImportExportButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				importExportActionPerformed(evt);	
			}
		});
		
		mainMenuPanel.addQuitButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				quitActionPerformed(evt);
			}
		});
	}
	
	private void classAdminActionPerformed(ActionEvent evt) {
		mainMenuPanel.setVisible(false);
		setContentPane("Class Administration Menu", classesController.getRosterAdminPanel());
		
	}
	
	private void studentAdminActionPerformed(ActionEvent evt) {
		mainMenuPanel.setVisible(false);
		setContentPane("Student Administration Menu", classesController.getStudentAdminPanel());
		
	}
	
	private void questionAdminActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		mainMenuPanel.setVisible(false);
		setContentPane("Question Administration Menu", questionsController.getQuestionsPanel());
	}
	
	private void newGameActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	private void loadGameActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	private void reviewGameActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	private void importExportActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		System.err.println("Starting to Delete Entire Database");
		AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
		TopicDAO topicDAO = factory.getTopicDAO();
		SubjectDAO subjectDAO = factory.getSubjectDAO();
		QuestionDAO questionDAO = factory.getQuestionDAO();
		StudentDAO studentDAO = factory.getStudentDAO();
		RosterDAO rosterDAO = factory.getRosterDAO();
		subjectDAO.beginTransaction();
		List<Question> qList = questionDAO.findAll();
		System.err.println("Deleting " + qList.size() + " questions");
		for(Question q : qList) {
			System.err.println("Deleting Question : " + q);
			q.setTopic(null);
			questionDAO.delete(q);
		}
		List<Topic> topList = topicDAO.findAll();
		System.err.println("Deleting " + topList.size() + " topics");
		for(Topic t : topList) {
			System.err.println("Deleting Topic : " + t);
			t.setSubject(null);
			topicDAO.delete(t);
		}
		List<Subject> subList = subjectDAO.findAll();
		System.err.println("Deleting " + subList.size() + " subjects");
		for(Subject s : subList) {
			System.err.println("Deleting Subject : " + s);
			subjectDAO.delete(s);
		}
		List<Student> studList = studentDAO.findAll();
		System.err.println("Deleting " + studList.size() + " students");
		for(Student s : studList) {
			System.err.println("Deleting Student : " + s);
			studentDAO.delete(s);
		}
		List<Roster> rosterList = rosterDAO.findAll();
		System.err.println("Deleting " + rosterList.size() + " rosters");
		for(Roster r : rosterList) {
			System.err.println("Deleting Roster : " + r);
			rosterDAO.delete(r);
		}
		
		subjectDAO.commitTransaction();
//		org.hibernate.cfg.Configuration config = new Configuration();
//		config.addAnnotatedClass(Subject.class);
//		config.addAnnotatedClass(Topic.class);
//		config.addAnnotatedClass(Question.class);
//		config.addAnnotatedClass(Roster.class);
//		config.addAnnotatedClass(Student.class);
//		config.setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
//		config.setProperty("hibernate.connection.driver_class", "org.apache.derby.jdbc.EmbeddedDriver");
//		config.setProperty("hibernate.connection.url", "jdbc:derby:./fqdb;create=true");
//		config.configure();
//
//		new org.hibernate.tool.hbm2ddl.SchemaExport(config).drop(true, true);
//		new org.hibernate.tool.hbm2ddl.SchemaExport(config).create(true, true);
	}
	
	private void quitActionPerformed(ActionEvent evt) {
		// Close any data connections
		// Confirm any writes or quit without saving
		mainFrame.dispose();
		System.exit(0);
	}

	public void run() {
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	
	public void setContentPane(Container newPanel) {
		mainFrame.setContentPane(newPanel);
	}
	
	public void setContentPane(String newTitle, Container newPanel) {
		newPanel.setVisible(true);
		mainFrame.setFrameTitle(newTitle);
		mainFrame.setContentPane(newPanel);	
	}

	public void showMainMenu() {
		this.setContentPane("Main Menu", mainMenuPanel);
		mainMenuPanel.setVisible(true);
	}
	
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
