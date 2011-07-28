/**
 * 
 */
package org.freequiz.www.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Skylar Hiebert
 *
 */
public class EditDialogFrame extends JDialog {
	private JLabel frameBanner;
	private JLabel frameTitle;
	private JPanel frameContentPanel;
	private JPanel buttonPanel;
	private JButton saveButton;
	private JButton cancelButton;
	
	public EditDialogFrame(Container contentPanel, String title) {
		super();
		initComponents();
		setTitle(title);
		frameTitle.setText(title);
		frameContentPanel.add(contentPanel, BorderLayout.CENTER);
	}
	
	public EditDialogFrame(Frame parent, Container contentPanel, String title) {
		super(parent);
		initComponents();
		frameTitle.setText(title);
		frameContentPanel.add(contentPanel, BorderLayout.CENTER);
	}
	
	public EditDialogFrame(Dialog parent, Container contentPanel, String title) {
		super(parent);
		initComponents();
		frameTitle.setText(title);
		frameContentPanel.add(contentPanel, BorderLayout.CENTER);
	}
	
	public EditDialogFrame(Window parent, Container contentPanel, String title) {
		super(parent);
		initComponents();
		frameTitle.setText(title);
		frameContentPanel.add(contentPanel, BorderLayout.CENTER);
	}
	
	private void initComponents() {
		setModal(true);
		setVisible(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		setTitle("Edit");
		frameBanner = new JLabel();
		frameTitle = new JLabel("Edit");
		frameContentPanel = new JPanel(new BorderLayout());
		frameContentPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		saveButton = new JButton("Save & Close");
		cancelButton = new JButton("Cancel");
		
		/* Add Banner and Title */
		frameBanner.setIcon(new ImageIcon(getClass().getResource("/org/freequiz/www/resources/GameBanner.png")));
		frameBanner.setAlignmentX(CENTER_ALIGNMENT);
		frameTitle.setFont(new Font("Calibri", 1, 24));
		frameTitle.setAlignmentX(CENTER_ALIGNMENT);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		buttonPanel.add(cancelButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(saveButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		
		add(Box.createVerticalStrut(5));
		add(frameBanner, BorderLayout.PAGE_START);
		add(Box.createVerticalStrut(5));
		add(frameTitle, BorderLayout.PAGE_START);
		add(Box.createVerticalStrut(5));
		add(frameContentPanel, BorderLayout.CENTER);
		add(Box.createVerticalStrut(10));
		add(buttonPanel, BorderLayout.PAGE_END);
		add(Box.createVerticalStrut(10));
		
		pack();
	}
	
	public void addCancelButtonListener(ActionListener al) {
		cancelButton.addActionListener(al);
	}
	
	public void addSaveButtonListener(ActionListener al) {
		saveButton.addActionListener(al);
	}
}
