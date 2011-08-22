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

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.*;

import org.freequiz.www.model.Game;
import org.freequiz.www.model.StudentGame;
import org.freequiz.www.model.Topic;

/**
 * @author Skylar Hiebert
 *
 */
public class ActiveGamePanel extends JPanel {
	private final static String GAMEGRIDPANEL = "Game Grid Panel";
	private final static String QUESTIONPANEL = "Question Panel";
	private final static String ANSWERPANEL = "Answer Panel";
	
	Game activeGame;
	private JPanel gameGridPanel;
	private JPanel questionPanel;
	private JTextPane questionTextPane;
	private JPanel answerPanel;
	private JTextPane answerTextPane;
	private List<JButton> topicButtonList;
	private JPanel questionGridPanel;
	private List<JButton> questionButtonList;
	private JPanel studentInfoPanel;
	private JPanel studentListPanel;
	private List<JButton> studentIncreaseScoreButtonList;
	private List<JButton> studentDecreaseScoreButtonList;
	private JButton studentCorrectButton;
	private JButton studentIncorrectButton;
	private ListModel studentListModel = new DefaultListModel();
	private JList studentList;
	private JScrollPane studentScrollPane;
	private JButton saveGameButton;
	private JButton quitGameButton;

	
	public ActiveGamePanel(Game activeGame) {
		if(activeGame != null) this.activeGame = activeGame;
		else throw new NullPointerException();
		initComponents();
	}
	
	private void initComponents() {
		gameGridPanel = new JPanel();
		
		questionPanel = new JPanel();
		questionTextPane = new JTextPane();
		questionTextPane.setEditable(false);
		questionTextPane.setEditorKit(new MyEditorKit());
		questionTextPane.setFont(new Font("Serif", Font.BOLD, 36));
		questionTextPane.setOpaque(false);
		questionPanel.setLayout(new BorderLayout());
		questionPanel.add(questionTextPane, BorderLayout.CENTER);
		
		answerPanel = new JPanel();
		answerTextPane = new JTextPane();
		answerTextPane.setEditable(false);
		answerTextPane.setEditorKit(new MyEditorKit());
		answerTextPane.setFont(new Font("Serif", Font.BOLD, 36));
		answerTextPane.setOpaque(false);
		answerTextPane.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent evt) {
				showGamePanel(GAMEGRIDPANEL);
			}
			@Override
			public void mouseEntered(MouseEvent evt) {}

			@Override
			public void mouseExited(MouseEvent evt) {}

			@Override
			public void mousePressed(MouseEvent evt) {}

			@Override
			public void mouseReleased(MouseEvent evt) {}
		});
		answerPanel.setLayout(new BorderLayout());
		answerPanel.add(answerTextPane, BorderLayout.CENTER);
		
		studentInfoPanel = new JPanel();
		studentCorrectButton = new JButton("Correct");
		studentCorrectButton.setEnabled(false);
		studentIncorrectButton = new JButton("Incorrect");
		studentIncorrectButton.setEnabled(false);
		studentList = new JList(studentListModel);
		studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		studentScrollPane = new JScrollPane(studentList);
		studentScrollPane.setPreferredSize(new Dimension(150, studentScrollPane.getPreferredSize().height));
		
		studentListPanel = new JPanel();
		studentIncreaseScoreButtonList = new ArrayList<JButton>();
		studentDecreaseScoreButtonList = new ArrayList<JButton>();
		
		saveGameButton = new JButton("Save Game");
		quitGameButton = new JButton("Quit Game");
		
		topicButtonList = new ArrayList<JButton>();
		questionGridPanel = new JPanel();
		questionButtonList = new ArrayList<JButton>();
		
		gameGridPanel.setLayout(new CardLayout());
		questionGridPanel.setPreferredSize(gameGridPanel.getSize());
		gameGridPanel.add(questionGridPanel, GAMEGRIDPANEL);
		questionPanel.setPreferredSize(gameGridPanel.getSize());
		gameGridPanel.add(questionPanel, QUESTIONPANEL);
		answerPanel.setPreferredSize(gameGridPanel.getSize());
		gameGridPanel.add(answerPanel, ANSWERPANEL);

		createButtonGrid();
		initializeStudentList();
		
		GroupLayout studentPanelLayout = new GroupLayout(studentInfoPanel);
		studentInfoPanel.setLayout(studentPanelLayout);
		studentPanelLayout.setAutoCreateGaps(true);
		studentPanelLayout.setAutoCreateContainerGaps(false);
		studentPanelLayout.setHorizontalGroup(studentPanelLayout.createParallelGroup()
				.addGroup(studentPanelLayout.createSequentialGroup()
						.addComponent(studentCorrectButton)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(studentIncorrectButton))
				.addComponent(studentScrollPane)
				.addGroup(studentPanelLayout.createSequentialGroup()
						.addComponent(saveGameButton)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(quitGameButton))
		);
		studentPanelLayout.linkSize(saveGameButton, quitGameButton);
		studentPanelLayout.linkSize(studentCorrectButton, studentIncorrectButton);
		
		studentPanelLayout.setVerticalGroup(studentPanelLayout.createSequentialGroup()
				.addGroup(studentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(studentCorrectButton)
						.addComponent(studentIncorrectButton))
				.addComponent(studentScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(studentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(saveGameButton)
						.addComponent(quitGameButton))
		);
		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(gameGridPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(studentInfoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
		
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(gameGridPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(studentInfoPanel));
		
		// Settings that need to be removed as the functions are implemented
		saveGameButton.setVisible(false);
	}
		
	/**
	 * SetTopics prior to calling this function
	 * @param questionsPerTopic
	 * @param multiplier
	 */
	public void createButtonGrid() {	
		if(activeGame == null) 
			System.err.println("Error in creating button grid, activeGame == null");
		int numTopics = activeGame.getTopicList().size();
		int questionsPerTopic = activeGame.getQuestionsAsked().size() / numTopics;
		int multiplier = activeGame.getQuestionsAsked().get(0).getQuestionValue(); // Assumes first question in list always is first row
		
		GridLayout buttonGridLayout = new GridLayout(0, numTopics, 5, 5);
		questionGridPanel.setLayout(buttonGridLayout);
		for(Topic t : activeGame.getTopicList()) {
			JButton button = new JButton(t.getTopicText());
			button.setFocusable(false);
			button.enableInputMethods(false);
			button.setSelected(true);
			button.setFont(new Font("Serif", Font.BOLD, 32));
			button.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createLoweredBevelBorder(), BorderFactory.createLineBorder(Color.BLACK, 3)));
			topicButtonList.add(button);
			questionGridPanel.add(button);
		}
		
		for(int i = 0; i < questionsPerTopic; i++) {
			for(int j = 0; j < numTopics; j++) {
				JButton button = new JButton(Integer.toString((i + 1) * multiplier)); // Create question value
				button.setActionCommand(Integer.toString(i + (j * questionsPerTopic))); // Set Question Index for action command
				button.setFont(new Font("Serif", Font.BOLD, 32));
				questionButtonList.add(button);
				questionGridPanel.add(button);
			}
		}

		showGamePanel(GAMEGRIDPANEL);
	}
	
	private void initializeStudentList() {
		if(activeGame == null) 
			System.err.println("Error in studentList button grid, activeGame == null");
		
		GridBagConstraints gbc = new GridBagConstraints();
		studentListPanel.setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.BASELINE;
		gbc.weighty = 1;
		gbc.weightx = 0.9;
		gbc.insets = new Insets(0,5,5,5);
		gbc.gridy = 0;
		List<StudentGame> studentList = activeGame.getStudentGameList();
		for(int i = 0; i < studentList.size(); i++) {
			StudentGame sg = studentList.get(i);
			gbc.gridx = 0;
			studentListPanel.add(new JLabel(sg.getStudent().getName()), gbc);
			gbc.gridx = 1;
			studentListPanel.add(new JLabel(sg.getScore().toString()), gbc);
			gbc.weightx = 0.1;
			gbc.gridx = 2;
			JButton increaseButton = new JButton("+");
			increaseButton.setActionCommand(Integer.toString(i));
			studentIncreaseScoreButtonList.add(increaseButton);
			studentListPanel.add(increaseButton, gbc);
			gbc.gridx = 3;
			JButton decreaseButton = new JButton("-");
			decreaseButton.setActionCommand(Integer.toString(i));
			studentDecreaseScoreButtonList.add(decreaseButton);
			studentListPanel.add(decreaseButton, gbc);
			gbc.gridy++;
		}
	}
	
	private void showGamePanel(String panel) {
		CardLayout cl = (CardLayout) gameGridPanel.getLayout();
		if(panel == GAMEGRIDPANEL || panel == QUESTIONPANEL || panel == ANSWERPANEL)
			cl.show(gameGridPanel, panel);
	}
	
	public void showQuestionGrid() {
		showGamePanel(GAMEGRIDPANEL);
	}
	
	public void showQuestion(String question) {	
		StyledDocument doc = questionTextPane.getStyledDocument();
		SimpleAttributeSet aSet = new javax.swing.text.SimpleAttributeSet();
		StyleConstants.setAlignment(aSet, StyleConstants.ALIGN_CENTER);
		doc.setCharacterAttributes(0, doc.getLength(), aSet, false);
		doc.setParagraphAttributes(0, doc.getLength()-1, aSet, false);
		try {
			doc.remove(0, doc.getLength());
			doc.insertString(0, question, aSet);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		studentCorrectButton.setEnabled(true);
		studentIncorrectButton.setEnabled(true);
		showGamePanel(QUESTIONPANEL);
	}
	
	public void showAnswer(String answer) {
		System.err.println("Showing Answer " + answer);		
		StyledDocument doc = answerTextPane.getStyledDocument();
		SimpleAttributeSet aSet = new javax.swing.text.SimpleAttributeSet();
		StyleConstants.setAlignment(aSet, StyleConstants.ALIGN_CENTER);
		doc.setCharacterAttributes(0, doc.getLength(), aSet, false);
		doc.setParagraphAttributes(0, doc.getLength()-1, aSet, false);
		try {
			doc.remove(0, doc.getLength());
			doc.insertString(0, answer, aSet);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		studentCorrectButton.setEnabled(false);
		studentIncorrectButton.setEnabled(false);

		showGamePanel(ANSWERPANEL);
	}
	
	public Game getCurrentGame() {
		return activeGame;
	}
	
	public void setStudentListModel(ListModel lm) {
		studentListModel = lm;
		studentList.setModel(studentListModel);
		
	}
	
	public void addStudentSelectionListeners(ListSelectionListener l, MouseListener ml) {
		studentList.addMouseListener(ml);
		studentList.addListSelectionListener(l);
	}
	
	public void addButtonGridActionListener(ActionListener al) {
		for(JButton button : questionButtonList)
			button.addActionListener(al);
	}

	public void addCorrectButtonListener(ActionListener al) {
		studentCorrectButton.addActionListener(al);		
	}

	public void addIncorrectButtonListener(ActionListener al) {
		studentIncorrectButton.addActionListener(al);
	}
	
	public void addSaveGameButtonListener(ActionListener al) {
		saveGameButton.addActionListener(al);
	}
	
	public void addQuitGameButtonListener(ActionListener al) {
		quitGameButton.addActionListener(al);
	}
	
	public StudentGame getSelectedStudentGame() {
		return (StudentGame) studentList.getSelectedValue();
	}	
}

/*
 * The classes below were found at http://java-sl.com/tip_center_vertically.html
 * These are used to vertically center text in a JTextPane
 */
class MyEditorKit extends StyledEditorKit {
    public ViewFactory getViewFactory() {
        return new StyledViewFactory();
    }
 
    static class StyledViewFactory implements ViewFactory {

        public View create(Element elem) {
            String kind = elem.getName();
            if (kind != null) {
                if (kind.equals(AbstractDocument.ContentElementName)) {

                    return new LabelView(elem);
                } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                    return new ParagraphView(elem);
                } else if (kind.equals(AbstractDocument.SectionElementName)) {

                    return new CenteredBoxView(elem, View.Y_AXIS);
                } else if (kind.equals(StyleConstants.ComponentElementName)) {
                    return new ComponentView(elem);
                } else if (kind.equals(StyleConstants.IconElementName)) {

                    return new IconView(elem);
                }
            }
 
            return new LabelView(elem);
        }

    }
}
 
class CenteredBoxView extends BoxView {
    public CenteredBoxView(Element elem, int axis) {

        super(elem,axis);
    }
    protected void layoutMajorAxis(int targetSpan, int axis, int[] offsets, int[] spans) {

        super.layoutMajorAxis(targetSpan,axis,offsets,spans);
        int textBlockHeight = 0;
        int offset = 0;
 
        for (int i = 0; i < spans.length; i++) {

            textBlockHeight = spans[i];
        }
        offset = (targetSpan - textBlockHeight) / 2;
        for (int i = 0; i < offsets.length; i++) {
            offsets[i] += offset;
        }

    }
}
