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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;

import org.freequiz.www.dao.*;
import org.freequiz.www.library.*;
import org.freequiz.www.model.Game;
import org.freequiz.www.model.GameQuestion;
import org.freequiz.www.model.Question;
import org.freequiz.www.model.Roster;
import org.freequiz.www.model.StudentGame;
import org.freequiz.www.model.Topic;
import org.freequiz.www.view.ActiveGamePanel;
import org.freequiz.www.view.NewGameMenu;

/**
 * @author Skylar Hiebert
 *
 */
public class GameController {
	private MainController mainController;
	private NewGameMenu newGamePanel;
	private ActiveGamePanel activeGamePanel;
	private GameDAO gameDAO;
	private TopicDAO topicDAO;
	private QuestionDAO questionDAO;
	private StudentDAO studentDAO;
	private RosterDAO rosterDAO;
	private Game currentGame;
	private FQDefaultComboBoxModel numTopicsComboBoxModel;
	private FQDefaultComboBoxModel numQuestionsComboBoxModel;
	private SpinnerNumberModel questionMultiplierSpinnerModel;
	private FQDefaultComboBoxModel rosterSelectionComboBoxModel;
	private FQDefaultListModel rosterListModel;
	private FQDefaultListModel currentStudentGameListModel;
	private List<FQDefaultComboBoxModel> topicSelectionComboBoxModels;
	private List<FQDefaultComboBoxModel> gradeLevelSelectionComboBoxModels;
	private GameQuestion currentQuestion;
	
	public GameController(MainController mainController) {
		this.mainController = mainController;
		initComponents();
		setActionListeners();
	}
	
	private void initComponents() {
		AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
		gameDAO = factory.getGameDAO();
		topicDAO = factory.getTopicDAO();
		questionDAO = factory.getQuestionDAO();
		studentDAO = factory.getStudentDAO();
		rosterDAO = factory.getRosterDAO();
		currentGame = new Game();
		newGamePanel = new NewGameMenu();
		numTopicsComboBoxModel = new FQDefaultComboBoxModel();
		for(int i = MainController.MINTOPICS; i <= MainController.MAXTOPICS; i++)
			numTopicsComboBoxModel.addElement(i);
		newGamePanel.setNumTopicsComboBoxModel(numTopicsComboBoxModel);
		numQuestionsComboBoxModel = new FQDefaultComboBoxModel();
		for(int i = MainController.MINQUESTIONS; i <= MainController.MAXQUESTIONS; i++)
			numQuestionsComboBoxModel.addElement(i);
		newGamePanel.setNumQuestionsComboBoxModel(numQuestionsComboBoxModel);
		questionMultiplierSpinnerModel = new SpinnerNumberModel(Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(10000), 
				Integer.valueOf(10));
		newGamePanel.setQuestionMultiplierSpinnerModel(questionMultiplierSpinnerModel);
		
		rosterSelectionComboBoxModel = new FQDefaultComboBoxModel();
		newGamePanel.setRosterSelectionComboBoxModel(rosterSelectionComboBoxModel);
		
		rosterListModel = new FQDefaultListModel();
		newGamePanel.setRosterListModel(rosterListModel);
		
		currentStudentGameListModel = new FQDefaultListModel();
		
		topicSelectionComboBoxModels = new ArrayList<FQDefaultComboBoxModel>();
		gradeLevelSelectionComboBoxModels = new ArrayList<FQDefaultComboBoxModel>();
		for(int i = 0; i < MainController.MAXTOPICS; i++) {
			topicSelectionComboBoxModels.add(new FQDefaultComboBoxModel());
			gradeLevelSelectionComboBoxModels.add(new FQDefaultComboBoxModel());
			newGamePanel.setTopicSelectionComboBoxModel(i, topicSelectionComboBoxModels.get(i));
			newGamePanel.setGradeLevelSelectionComboBoxModel(i, gradeLevelSelectionComboBoxModels.get(i));		
		}
	}
	
	private void setActionListeners() {
		newGamePanel.addBackButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				backButtonActionPerformed(evt);
			}
		});
		
		newGamePanel.addStartGameButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				startGameButtonActionPerformed(evt);
			}
		});
		
		newGamePanel.setRosterSelectionActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				rosterSelectionActionPerformed(evt);
			}
		});
	}
	
	private void backButtonActionPerformed(ActionEvent evt) {
		newGamePanel.setVisible(false);
		mainController.showMainMenu();
	}
	
	private void startGameButtonActionPerformed(ActionEvent evt) {
		List<Question> questionList = new ArrayList<Question>();
		List<Topic> topics = new ArrayList<Topic>();
		int numTopics = (Integer) numTopicsComboBoxModel.getSelectedItem();
		int numQuestions = (Integer) numQuestionsComboBoxModel.getSelectedItem();
		int questionMultiplier = (Integer) questionMultiplierSpinnerModel.getValue();
		
		try {
			questionDAO.beginTransaction();
			currentGame.setDatePlayed(new Date());
			currentGame.setGameRoster((Roster) rosterSelectionComboBoxModel.getSelectedItem()); 
			for(int i = 0; i < numTopics; i++) {
				Question exampleQuestion = new Question();
				topics.add((Topic) topicSelectionComboBoxModels.get(i).getSelectedItem());
				exampleQuestion.setTopic(topics.get(i));
				exampleQuestion.setGradeLevel((String) gradeLevelSelectionComboBoxModels.get(i).getSelectedItem());
				questionList.addAll(questionDAO.findRandomQuestionSetByExample(numQuestions, exampleQuestion));
			}
			
			List<GameQuestion> gameList = new ArrayList<GameQuestion>(); //currentGame.getQuestionsAsked();		
			for(int i = 0; i < questionList.size(); i++) {
				gameList.add(new GameQuestion(i, questionList.get(i), questionMultiplier * ((i % numQuestions) + 1)));
			}
			currentGame.setQuestionsAsked(gameList);
			currentStudentGameListModel.removeAllElements();
			currentStudentGameListModel.addAll(currentGame.getStudentGameList());
			
			gameDAO.save(currentGame);
			
			questionDAO.commitTransaction();
			activeGamePanel = new ActiveGamePanel(currentGame);
			activeGamePanel.setStudentListModel(currentStudentGameListModel);
			activeGamePanel.addButtonGridActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					buttonGridActionPerformed(evt);
				}
			});
			activeGamePanel.addCorrectButtonListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					correctAnswerButtonActionPerformed(evt);
				}
			});
			activeGamePanel.addIncorrectButtonListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					incorrectAnswerButtonActionPerformed(evt);
				}
			});
			activeGamePanel.addSaveGameButtonListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					saveGameButtonActionPerformed(evt);
				}
			});
			activeGamePanel.addQuitGameButtonListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					quitGameButtonActionPerformed(evt);
				}
			});
			mainController.setContentPane(null, activeGamePanel);
		} catch (IndexOutOfBoundsException ex) { 
			JOptionPane.showMessageDialog(mainController.mainFrame, "There is a problem retrieving questions from the database." +
					"\nPlease verify that there are enough questions in the database to fill the gameboard." +
					"\n\nReminder: There must be at least one question per rank up to the number of questions per topic selected.",
					"Not Enough Questions Error", JOptionPane.WARNING_MESSAGE);
		} catch (Exception ex) {
			// TODO write error method
			System.err.println("Error initializing startgame interface " + ex);
			ex.printStackTrace();
		}
	}
	
	private void initializeComboBoxes() {
		// Database initialization
		try {
			gameDAO.beginTransaction();
			rosterSelectionComboBoxModel.removeAllElements();
			rosterSelectionComboBoxModel.addAll(rosterDAO.findAll());
			rosterSelectionComboBoxModel.setSelectedItemIndex(-1);

			List<Topic> topicList = topicDAO.findAll();
			

			for(int i = 0; i < MainController.MAXTOPICS; i++) {
				FQDefaultComboBoxModel cbm = topicSelectionComboBoxModels.get(i);
				cbm.removeAllElements();
				cbm.addAll(topicList);
				cbm.setSelectedItemIndex(-1);
				newGamePanel.setTopicSelectionComboBoxModel(i, cbm);
				cbm = gradeLevelSelectionComboBoxModels.get(i);
				cbm.removeAllElements();
				cbm.addAll(questionDAO.findAllGradeLevels());
				cbm.setSelectedItemIndex(-1);
				newGamePanel.setGradeLevelSelectionComboBoxModel(i, cbm);				
			}
			gameDAO.commitTransaction();
		} catch (Exception ex) {
			
			System.err.println("Error initializing new game menu: " + ex);
		}
	}

	private void rosterSelectionActionPerformed(ActionEvent evt) {
		Roster selectedRoster = (Roster) rosterSelectionComboBoxModel.getSelectedItem();
		rosterListModel.clear();
		if(selectedRoster != null) {
			rosterListModel.addAll(selectedRoster.getStudentList());
		}
	}
	
	private void buttonGridActionPerformed(ActionEvent evt) {
		AbstractButton button = (AbstractButton) evt.getSource();
		button.setEnabled(false);
		button.setText("");
		currentQuestion = currentGame.getQuestionsAsked().get(Integer.parseInt(evt.getActionCommand()));
		activeGamePanel.showQuestion(currentQuestion.getQuestion().getQuestionText());
	}
	
	private void saveGameButtonActionPerformed(ActionEvent evt) {
		// TODO Implement save game feature
	}
	
	private void quitGameButtonActionPerformed(ActionEvent evt) {
		saveCurrentGame();
		activeGamePanel.setVisible(false);
		mainController.showMainMenu();	
	}
	
	private void correctAnswerButtonActionPerformed(ActionEvent evt) {
		if(currentQuestion == null) {
			System.err.println("ERROR: No current question");
		}
		StudentGame sg = activeGamePanel.getSelectedStudentGame();
//		StudentGame sg = currentGame.getStudentGameList().get(currentGame.getStudentGameList().indexOf(activeGamePanel.getSelectedStudentGame()));
		if(sg != null) {			
			sg.setScore(sg.getScore() + currentQuestion.getQuestionValue());
			activeGamePanel.showAnswer(currentQuestion.getQuestion().getAnswerText());
			currentQuestion = null;
			activeGamePanel.repaint();
		}
	}
	
	private void incorrectAnswerButtonActionPerformed(ActionEvent evt) {
		if(currentQuestion == null) {
			System.err.println("ERROR: No current question");
		}
		StudentGame sg = activeGamePanel.getSelectedStudentGame();
		if(sg != null) {
			sg.setScore(sg.getScore() - currentQuestion.getQuestionValue());
			activeGamePanel.repaint();
		}
	}
	
	public void saveCurrentGame() {
		try{
			gameDAO.beginTransaction();
			System.err.println("Before Save: " + currentGame.getStudentGameList());
			
			
//			for(StudentGame sg : currentGame.getStudentGameList()) {
//				System.err.println("Saving StudentGame:" + sg);
//				studentDAO.save(sg.getStudent());
//			}
			
			System.err.println("After Save: " + currentGame.getStudentGameList());
			gameDAO.save(currentGame);
			System.err.println("After Save2: " + currentGame.getStudentGameList());
			gameDAO.commitTransaction();
		} catch (Exception ex) {
			System.err.println("Error saving game data. " + ex.getStackTrace());
		}
	}
	
	public JPanel getGamePanel() {
		numTopicsComboBoxModel.setSelectedItemIndex(2);
		numQuestionsComboBoxModel.setSelectedItemIndex(4);
		rosterSelectionComboBoxModel.setSelectedItemIndex(-1);
		initializeComboBoxes();
		return newGamePanel;
	}	
}
