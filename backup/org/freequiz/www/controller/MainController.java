/**
 * 
 */
package org.freequiz.www.controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.freequiz.www.view.*;

/**
 * @author Skylar Hiebert
 *
 */
public class MainController {
	protected QuestionsController questionsController;
	protected ClassesController classesController;
	protected MainFrame mainFrame;
	protected MainMenu mainMenuPanel;
	
	public MainController () {
		initComponents();
		setActionListeners();		
		showMainMenu();
	}
	
	private void initComponents() {
		questionsController = new QuestionsController(this);
		classesController = new ClassesController(this);
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
		setContentPane("Class Administration Menu", classesController.getClassAdminPanel());
		
	}
	
	private void studentAdminActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
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
		
	}
	
	private void quitActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		System.out.println("Quit Action");
		// Close any data connections
		// Confirm any writes or quit without saving
		mainFrame.dispose();
		System.exit(0);
	}

	public void run() {
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
