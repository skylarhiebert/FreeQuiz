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

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;

/**
 * @author Skylar Hiebert
 *
 */
public class MainFrame extends JFrame {
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
	public MainFrame() {
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
		testLabel = new JLabel();
		testLabel.setText("Test Label");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		setMinimumSize(new Dimension(800, 600));
		setPreferredSize(new Dimension(800, 600));
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
		this.getContentPane().add(Box.createVerticalStrut(5));
		this.getContentPane().add(frameBanner, BorderLayout.NORTH);
		this.getContentPane().add(Box.createVerticalStrut(5));
		this.getContentPane().add(frameTitle, BorderLayout.NORTH);
		this.getContentPane().add(Box.createVerticalStrut(5));
		this.getContentPane().add(frameContentPanel, BorderLayout.CENTER);
		
		// Settings that need to be removed as the functions are implemented
		editMenu.setVisible(false);
		loadGameMenuItem.setVisible(false);
		
		pack();
	}
	
	/**
	 * Exits from the program
	 * @param evt
	 */
	public void addQuitMenuItemActionListener(ActionListener al) {
		quitMenuItem.addActionListener(al);
	}
	
	public void addNewGameMenuItemActionListener(ActionListener al) {
		newGameMenuItem.addActionListener(al);
	}
	
	/**
	 * @param newTitle new title to set
	 */
	public void setFrameTitle(String newTitle) {
		if(newTitle == null || newTitle.isEmpty()) 
			frameTitle.setVisible(false);
		else
			frameTitle.setText(newTitle);		
	}
	
	/**
	 * Removes old panel and sets new panel for menu frame
	 * @param newPanel the new panel to be set
	 */
	public void setContentPane(Container newPanel) {
		frameContentPanel.removeAll();
		frameContentPanel.add(newPanel, BorderLayout.CENTER);
	}
	
	public java.awt.Component getCurrentContentPane() {
		if(frameContentPanel.getComponentCount() > 0)
			return frameContentPanel.getComponent(0);
		else
			return null;
	}
}
