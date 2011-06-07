/**
 * 
 */
package org.freequiz.www.view;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;

/**
 * @author Skylar Hiebert
 *
 */
public class MenuFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2739773647588800569L;
	private JLabel frameBanner;
	private JLabel frameTitle;
	private JPanel frameContentPanel;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu helpMenu;
	private JMenuItem newGameMenuItem;
	private JMenuItem loadGameMenuItem;
	private JMenuItem quitMenuItem;
	private JMenuItem copyMenuItem;
	private JMenuItem pasteMenuItem;
	private JMenuItem preferencesMenuItem;
	private JMenuItem aboutMenuItem;
	
	private JLabel testLabel;
	
	//private QuestionAdminMenuPanel qamp;
	
	/**
	 * Class constructor
	 */
	public MenuFrame() {
		initComponents();
	}
	
	/**
	 * Initializes all frame components
	 */
	private void initComponents() {
		frameBanner = new JLabel();
		frameTitle = new JLabel();
		frameContentPanel = new JPanel(new BorderLayout());
		menuBar = new JMenuBar();
		fileMenu = new JMenu();
		editMenu = new JMenu();
		helpMenu = new JMenu();
		newGameMenuItem = new JMenuItem();
		loadGameMenuItem = new JMenuItem();
		quitMenuItem = new JMenuItem();
		copyMenuItem = new JMenuItem();
		pasteMenuItem = new JMenuItem();
		preferencesMenuItem = new JMenuItem();
		aboutMenuItem = new JMenuItem();
		//qamp = new QuestionAdminMenuPanel();
		testLabel = new JLabel();
		testLabel.setText("Test Label");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		setMinimumSize(new Dimension(800, 600));
		setResizable(false);
		setTitle("Freequiz");
		
		/* Add Banner and Title */
		frameBanner.setIcon(new ImageIcon(getClass().getResource("/org/freequiz/www/resources/GameBanner.png")));
		frameBanner.setAlignmentX(CENTER_ALIGNMENT);
		frameTitle.setText("Main Menu");
		frameTitle.setFont(new Font("Calibri", 1, 24));
		frameTitle.setAlignmentX(CENTER_ALIGNMENT);
		frameContentPanel.add(testLabel, BorderLayout.CENTER);
		
		/* Create Menu Bar */
		fileMenu.setText("File");
		newGameMenuItem.setText("Start New Game");
		newGameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		fileMenu.add(newGameMenuItem);
		loadGameMenuItem.setText("Load Saved Game");
		loadGameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		fileMenu.add(loadGameMenuItem);
		quitMenuItem.setText("Quit");
		quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		quitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				quitMenuItemAction(evt);
			}
		});
		fileMenu.add(quitMenuItem);
		menuBar.add(fileMenu);
		
		editMenu.setText("Edit");
		copyMenuItem.setText("Copy");
		copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		editMenu.add(copyMenuItem);
		pasteMenuItem.setText("Paste");
		pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		editMenu.add(pasteMenuItem);
		preferencesMenuItem.setText("Preferences");
		editMenu.add(preferencesMenuItem);
		menuBar.add(editMenu);
		
		helpMenu.setText("Help");
		aboutMenuItem.setText("About");
		helpMenu.add(aboutMenuItem);
		menuBar.add(helpMenu);
		
		//frameContentPanel.add(qamp, BorderLayout.CENTER);
		
		setJMenuBar(menuBar);
		add(Box.createVerticalStrut(5));
		add(frameBanner, BorderLayout.NORTH);
		add(Box.createVerticalStrut(5));
		add(frameTitle, BorderLayout.NORTH);
		add(Box.createVerticalStrut(5));
		add(frameContentPanel, BorderLayout.CENTER);
		
		pack();
	}
	
	/**
	 * Exits from the program
	 * @param evt
	 */
	private void quitMenuItemAction(ActionEvent evt) {
		System.exit(1);
	}
	
	/**
	 * @param newTitle new title to set
	 */
	public void setFrameTitle(String newTitle) {
		if(newTitle.isEmpty()) 
			System.err.println("new title cannot be null.");
		else
			frameTitle.setText(newTitle);		
	}
	
	/**
	 * Removes old panel and sets new panel for menu frame
	 * @param newPanel the new panel to be set
	 */
	public void setContentPane(Container newPanel) {
		this.frameContentPanel.removeAll();
		this.frameContentPanel.add(newPanel, BorderLayout.CENTER);
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                MenuFrame mainFrame = new MenuFrame();
                mainFrame.setFrameTitle("Question Administration Menu");
                mainFrame.setContentPane(new QuestionAdminMenuPanel());
                mainFrame.setVisible(true);
                
            }
        });

	}

}
