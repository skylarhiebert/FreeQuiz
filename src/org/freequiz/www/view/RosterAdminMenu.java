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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import org.freequiz.www.library.FQDefaultListModel;
import org.freequiz.www.library.FQRosterTableModel;

/**
 * @author Skylar Hiebert
 *
 */
public class RosterAdminMenu extends JPanel {
	private JButton backButton;
	private JPanel buttonPanel;
	private JPanel rosterPanel;
	private JTable rosterListTable;
	private JScrollPane rosterScrollPane;
	private JButton newButton;
	private JButton editButton;
	private JButton deleteButton;
	private JPanel studentPanel;
	private JList studentList;
	private JScrollPane studentScrollPane;
	private TableModel rosterTableModel = new FQRosterTableModel();
	private ListModel studentListModel = new FQDefaultListModel();
	
	/**
	 * 
	 */
	public RosterAdminMenu() {
		super();
		initComponents();
	}

	private void initComponents() {
		backButton = new JButton("Back to Main Menu");
		buttonPanel = new JPanel();
		rosterPanel = new JPanel();
		rosterListTable = new JTable(rosterTableModel) {
			public TableCellRenderer getCellRenderer(int row, int col) {
				TableCellRenderer renderer = super.getCellRenderer(row,col);
			
				if(col == 2) 
					((JLabel)renderer).setHorizontalAlignment(SwingConstants.CENTER);
				else
					((JLabel)renderer).setHorizontalAlignment(SwingConstants.LEFT);

		      return renderer;
			}
		};
		rosterListTable.setAutoCreateRowSorter(true);
		rosterListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rosterListTable.setRowSelectionAllowed(true);
		rosterListTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent evt) {
				rosterListSelection(evt);
			}
		});
		rosterListTable.getColumnModel().getColumn(2).setPreferredWidth(75);
		rosterListTable.getColumnModel().getColumn(2).setMaxWidth(75);
		rosterScrollPane = new JScrollPane(rosterListTable);
		
		studentPanel = new JPanel();
		studentList = new JList(studentListModel);
		studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		studentScrollPane = new JScrollPane(studentList);
		studentScrollPane.setPreferredSize(new Dimension(150, studentScrollPane.getPreferredSize().height));
		
		/* Button Panel */
		Dimension buttonPreferredSize = new Dimension(100, 23);
		buttonPanel = new JPanel();
		newButton = new JButton("New");
		newButton.setPreferredSize(buttonPreferredSize);
		editButton = new JButton("Edit");
		editButton.setPreferredSize(buttonPreferredSize);
		editButton.setEnabled(false);
		deleteButton = new JButton("Delete");
		deleteButton.setPreferredSize(buttonPreferredSize);
		deleteButton.setEnabled(false);
		
		/* Button Panel Layout */
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		//buttonPanel.setBorder(new EmptyBorder(0,0,10,0));
		buttonPanel.add(deleteButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(editButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPanel.add(newButton);
		
		rosterPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createCompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), 
						BorderFactory.createBevelBorder(BevelBorder.RAISED)), "Classes"));
//		classPanel.setLayout(new BorderLayout(10, 10));
//		classPanel.add(classScrollPane, BorderLayout.CENTER);
//		classPanel.add(buttonPanel, BorderLayout.PAGE_END);
		
		GroupLayout rosterPanelLayout = new GroupLayout(rosterPanel);
		rosterPanel.setLayout(rosterPanelLayout);
		rosterPanelLayout.setAutoCreateGaps(true);
		rosterPanelLayout.setAutoCreateContainerGaps(true);
		rosterPanelLayout.setHorizontalGroup(rosterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(rosterScrollPane)
				.addComponent(buttonPanel)
		);
	
		rosterPanelLayout.setVerticalGroup(rosterPanelLayout.createSequentialGroup()
				.addComponent(rosterScrollPane)
				.addComponent(buttonPanel)
		);	
		
		
		studentPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createCompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), 
						BorderFactory.createBevelBorder(BevelBorder.RAISED)), "Students in class"));
		studentPanel.setLayout(new BorderLayout(10, 10));
		studentPanel.add(studentScrollPane, BorderLayout.CENTER);
		
		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(rosterPanel)
						.addComponent(studentPanel))
				.addComponent(backButton)
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(rosterPanel)
						.addComponent(studentPanel))
				.addComponent(backButton)
		);

	}

	private void rosterListSelection(ListSelectionEvent evt) {
		deleteButton.setEnabled(true);
		editButton.setEnabled(true);		
	}
	
	public void addRosterSelectionListener(ListSelectionListener sl) {
		rosterListTable.getSelectionModel().addListSelectionListener(sl);
	}
	
	public void addBackButtonListener(ActionListener al) {
		backButton.addActionListener(al);
	}
	
	public void addNewRosterButtonListener(ActionListener al) {
		newButton.addActionListener(al);
	}
	
	public void addEditRosterButtonListener(ActionListener al) {
		editButton.addActionListener(al);
	}
	
	public void addDeleteRosterButtonListener(ActionListener al) {
		deleteButton.addActionListener(al);
	}
	
	public int getSelectedRowIndex() {
		return rosterListTable.convertRowIndexToModel(rosterListTable.getSelectedRow());
	}
	
	public void setRosterListTableModel(TableModel tm) {
		rosterTableModel = tm;
		rosterListTable.setModel(rosterTableModel);
	}
	
	public void setStudentListModel(ListModel lm) {
		studentList.setModel(lm);
	}
	
//	public Roster getSelectedRoster() {
//		return rosterListTable.getSelectedRow()
//	}
}
