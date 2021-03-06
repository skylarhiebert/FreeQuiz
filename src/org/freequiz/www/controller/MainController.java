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
package org.freequiz.www.controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.freequiz.www.dao.*;
import org.freequiz.www.dao.hibernate.HibernateUtil;
import org.freequiz.www.model.Game;
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
	public final static int MINTOPICS = 2;
	public final static int MAXTOPICS = 8;
	public final static int MINQUESTIONS = 2;
	public final static int MAXQUESTIONS = 8;
	public final static String GRADELEVELS[] = {"Pre-K", "K", "1", "2", "3", "4", "5", "6", "7", "8", "High School", "College" };
	public final static Long DIFFICULTIES[] = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L};
	
	protected QuestionController questionsController;
	protected RosterController classesController;
	protected GameController gameController;
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
		gameController = new GameController(this);
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
		
		mainFrame.addNewGameMenuItemActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				newGameActionPerformed(evt);
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
		mainMenuPanel.setVisible(false);
		setContentPane("Question Administration Menu", questionsController.getQuestionsPanel());
	}
	
	private void newGameActionPerformed(ActionEvent evt) {
		mainMenuPanel.setVisible(false);
		setContentPane("Start New Game", gameController.getGamePanel());		
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
		GameDAO gameDAO = factory.getGameDAO();
		subjectDAO.beginTransaction();
//		List<Question> qList = questionDAO.findAll();
//		System.err.println("Deleting " + qList.size() + " questions");
//		for(Question q : qList) {
//			System.err.println("Deleting Question : " + q);
//			q.setTopic(null);
//			questionDAO.delete(q);
//		}
//		List<Topic> topList = topicDAO.findAll();
//		System.err.println("Deleting " + topList.size() + " topics");
//		for(Topic t : topList) {
//			System.err.println("Deleting Topic : " + t);
//			t.setSubject(null);
//			topicDAO.delete(t);
//		}
//		List<Subject> subList = subjectDAO.findAll();
//		System.err.println("Deleting " + subList.size() + " subjects");
//		for(Subject s : subList) {
//			System.err.println("Deleting Subject : " + s);
//			subjectDAO.delete(s);
//		}
//		List<Student> studList = studentDAO.findAll();
//		System.err.println("Deleting " + studList.size() + " students");
//		for(Student s : studList) {
//			System.err.println("Deleting Student Games: " + s.getGamesPlayed());
//			s.getGamesPlayed().clear();
//			studentDAO.save(s);
//		}
//		List<Roster> rosterList = rosterDAO.findAll();
//		System.err.println("Deleting " + rosterList.size() + " rosters");
//		for(Roster r : rosterList) {
//			System.err.println("Deleting Roster : " + r);
//			rosterDAO.delete(r);
//		}
//		List<Game> gameList = gameDAO.findAll();
//		for(Game g : gameList) {
//			System.err.println("Deleting Game : " + g);
//			gameDAO.delete(g);
//		}
		
		
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
		try {
			if(mainFrame.getCurrentContentPane() instanceof ActiveGamePanel) 
				gameController.saveCurrentGame();
		} catch (NullPointerException ex) {
			System.err.println("No content panes in main frame. " + ex);
		}
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
