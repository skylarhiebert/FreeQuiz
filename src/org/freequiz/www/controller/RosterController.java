/**
 * 
 */
package org.freequiz.www.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Enumeration;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.freequiz.www.dao.AbstractDAOFactory;
import org.freequiz.www.dao.RosterDAO;
import org.freequiz.www.dao.StudentDAO;
import org.freequiz.www.library.FQDefaultListModel;
import org.freequiz.www.library.FQRosterTableModel;
import org.freequiz.www.model.Roster;
import org.freequiz.www.model.Student;
import org.freequiz.www.view.RosterAdminMenu;
import org.freequiz.www.view.EditRosterDialogPanel;
import org.freequiz.www.view.EditDialogFrame;
import org.freequiz.www.view.StudentAdminMenu;

/**
 * @author Skylar Hiebert
 *
 */
public class RosterController {
	private MainController mainController;
	private RosterAdminMenu rosterPanel;
	private StudentAdminMenu studentPanel;
	private EditDialogFrame editRosterDialog;
	private EditRosterDialogPanel editRosterPanel;
	private FQRosterTableModel rosterTableModel;
	private FQDefaultListModel rosterStudentListModel;
	private FQDefaultListModel studentListModel;
	private FQDefaultListModel editRosterStudentListModel;
	private FQDefaultListModel editRosterListModel;
	private StudentDAO studentDAO;
	private RosterDAO rosterDAO;
	
	public RosterController(MainController mainController) {
		this.mainController = mainController;
		initComponents();
		setActionListeners();
	}
	private void initComponents() {
		AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
		studentDAO = factory.getStudentDAO();
		rosterDAO = factory.getRosterDAO();
		rosterPanel = new RosterAdminMenu();
		rosterTableModel = new FQRosterTableModel();
		rosterPanel.setRosterListTableModel(rosterTableModel);
		rosterStudentListModel = new FQDefaultListModel();
		rosterPanel.setStudentListModel(rosterStudentListModel);
		studentPanel = new StudentAdminMenu();
		studentListModel = new FQDefaultListModel();
		studentPanel.setStudentListModel(studentListModel);
		editRosterPanel = new EditRosterDialogPanel();
		editRosterListModel = new FQDefaultListModel();
		editRosterPanel.setRosterListModel(editRosterListModel);
		editRosterStudentListModel = new FQDefaultListModel();
		editRosterPanel.setStudentListModel(editRosterStudentListModel);
		editRosterDialog = new EditDialogFrame(editRosterPanel, "Edit Class");
		editRosterDialog.setMinimumSize(new Dimension(700,550));
		
		try {
			studentDAO.beginTransaction();
			studentListModel.addAll(studentDAO.findAll());
			studentDAO.commitTransaction();
		} catch (Exception ex) {
			System.err.println("Error initializing student list");
			throw new ExceptionInInitializerError();
		}
	}
	
	private void setActionListeners() {
		rosterPanel.addBackButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				backButtonActionPerformed(evt);
			}
		});
		
		rosterPanel.addNewRosterButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				rosterNewButtonActionPerformed(evt);
			}
		});
		
		rosterPanel.addEditRosterButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				rosterEditButtonActionPerformed(evt);
			}
		});
		
		rosterPanel.addDeleteRosterButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				rosterDeleteButtonActionPerformed(evt);
			}
		});
		
		rosterPanel.addRosterSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent evt) {
				if(!evt.getValueIsAdjusting())
					rosterListSelectionEventPerformed(evt);
			}
		});
		
		studentPanel.addBackButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				backButtonActionPerformed(evt);
			}
		});
		
		studentPanel.addNewStudentButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				studentNewButtonActionPerformed(evt);
			}
		});
		
		studentPanel.addEditStudentButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				studentEditButtonActionPerformed(evt);
			}
		});
		
		studentPanel.addDeleteStudentButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				studentDeleteButtonActionPerformed(evt);
			}
		});
		
		studentPanel.addStudentListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent evt) {
				studentListSelectionEventPerformed(evt);
			}
		});
		
		editRosterDialog.addCancelButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				rosterEditCancelButtonActionPerformed(evt);
			}
		});
		
		editRosterDialog.addSaveButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				rosterEditSaveButtonActionPerformed(evt);
			}
		});
		
		editRosterPanel.addToRosterListButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				rosterEditToRosterListButtonActionPerformed(evt);
			}
		});
		
		editRosterPanel.addToStudentListButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				rosterEditToStudentListButtonActionPerformed(evt);
			}
		});
	}
	
	public JPanel getRosterAdminPanel() {
		try {
			rosterTableModel.removeAllRows();
			rosterStudentListModel.clear();
			rosterDAO.beginTransaction();
			rosterTableModel.addRows(rosterDAO.findAll());
			rosterDAO.commitTransaction();
			rosterPanel.setVisible(true);
		} catch (Exception ex) {
			// TODO Write error method
			System.err.println("Error retrieving rosters");
		}
		return rosterPanel;
	}
	
	public JPanel getStudentAdminPanel() {
		studentPanel.setVisible(true);
		return studentPanel;
	}
	
	private void backButtonActionPerformed(ActionEvent evt) {
		rosterPanel.setVisible(false);
		studentPanel.setVisible(false);
		mainController.showMainMenu();
	}
	
	private void rosterNewButtonActionPerformed(ActionEvent evt) {
		System.out.println("newRosterButton");
		editRosterPanel.setRoster(new Roster());
		editRosterListModel.clear();
		
		try {
			studentDAO.beginTransaction();
			editRosterStudentListModel.setElements(studentDAO.findAll());
			studentDAO.commitTransaction();	
			editRosterPanel.setStudentListModel(editRosterStudentListModel);
		} catch (Exception ex) {
			// TODO Write error method
			System.err.println("Error retrieving students");
		}
		editRosterDialog.setVisible(true);
	}
	
	private void rosterEditButtonActionPerformed(ActionEvent evt) {
		System.out.println("editButton");
		Roster roster = rosterTableModel.getRosterAtRow(rosterPanel.getSelectedRowIndex());
		editRosterPanel.setRoster(roster);
		editRosterListModel.setElements(roster.getStudentList());
		try {
			studentDAO.beginTransaction();
			editRosterStudentListModel.setElements(studentDAO.findAll());
			studentDAO.commitTransaction();
		} catch (Exception ex) {
			// TODO Write error method
			System.err.println("Error in edit " + ex);
		}
		for(Student s : roster.getStudentList()) // Removes elements from list that exist in roster
			editRosterStudentListModel.removeElement(s);

		editRosterDialog.setVisible(true);		
	}
	
	private void rosterDeleteButtonActionPerformed(ActionEvent evt) {
		Roster roster = rosterTableModel.getRosterAtRow(rosterPanel.getSelectedRowIndex());
		int yesNo = JOptionPane.showConfirmDialog(mainController.mainFrame,
				"Are you sure you want to permanently delete\n\n" + roster + "\n\nfrom the database?",
				"Delete roster?", JOptionPane.YES_NO_OPTION);
		if(yesNo == JOptionPane.YES_OPTION) {
			try {
				roster.removeAllStudents();
				rosterDAO.beginTransaction();
				rosterDAO.delete(roster);
				rosterDAO.commitTransaction();
				rosterTableModel.removeRow(roster);
			} catch (Exception ex) {
				// TODO Write error method
				System.err.println("Error attempting to delete");
			}
			
		}
		System.out.println("rosterdeleteButton");
	}
	
	private void rosterListSelectionEventPerformed(ListSelectionEvent evt) {
		int selectedRow = rosterPanel.getSelectedRowIndex();
		if(!evt.getValueIsAdjusting() && selectedRow >= 0) {
			Roster roster = rosterTableModel.getRosterAtRow(selectedRow);
			rosterStudentListModel.setElements(roster.getStudentList());
		}
	}
	
	private void rosterEditCancelButtonActionPerformed(ActionEvent evt) {
		System.out.println("editRostercancelButton");
		editRosterDialog.dispose();
	}
	
	private void rosterEditSaveButtonActionPerformed(ActionEvent evt) {
		if(editRosterPanel.validateInputs()) {
			Roster editRoster = editRosterPanel.getRoster();
			assert(editRoster != null);
			boolean newRoster = (editRoster.getRosterid() == null || editRoster.getRosterid() < 0);
			editRoster.setName(editRosterPanel.getRosterName());
			editRoster.setDescription(editRosterPanel.getRosterDescription());
			editRoster.getStudentList().clear();
			
			Enumeration<?> students = editRosterListModel.elements();
			while(students.hasMoreElements())
				editRoster.addStudent((Student) students.nextElement());
			
			try {
				rosterDAO.beginTransaction();
				rosterDAO.save(editRoster);
				rosterDAO.commitTransaction();
				if(newRoster) {
					rosterTableModel.addRow(editRoster);
				}
				else {
					rosterTableModel.fireTableRowsUpdated(rosterPanel.getSelectedRowIndex(), rosterPanel.getSelectedRowIndex());
					rosterStudentListModel.setElements(rosterTableModel.getRosterAtRow(rosterPanel.getSelectedRowIndex()).getStudentList());
				}
			} catch (Exception ex) {
				System.err.println("Error saving roster " + ex);
			}
		} else {
			// TODO Display error message for input errors
			System.err.println("Input Errors on edit roster panel");
		}
		
		editRosterDialog.dispose();
	}
	
	private void rosterEditToRosterListButtonActionPerformed(ActionEvent evt) {
		int [] selectedIndices = editRosterPanel.getAddStudentsSelectedIndices();
		for(int i = selectedIndices.length - 1; i >= 0; i--) 
			editRosterListModel.addElement((Student) editRosterStudentListModel.remove(selectedIndices[i]));
	}
	
	private void rosterEditToStudentListButtonActionPerformed(ActionEvent evt) {
		int [] selectedIndices = editRosterPanel.getRemoveStudentsSelectedIndices();
		for(int i = selectedIndices.length - 1; i >= 0; i--) 
			editRosterStudentListModel.addElement((Student) editRosterListModel.remove(selectedIndices[i]));
	}
	
	private void studentNewButtonActionPerformed(ActionEvent evt) {
		String studentString = (String) JOptionPane.showInputDialog(mainController.mainFrame, 
				"New Student Name:", 
				"New Student...", 
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				null);
		
		if(studentString != null && studentString.length() > 0) {
			try {
				Student newStudent = new Student(studentString);
				studentDAO.beginTransaction();
				java.util.List<Student> dbStudent = studentDAO.findByExample(newStudent, null);
				if(dbStudent != null && dbStudent.size() > 0) {
					int yesNo = JOptionPane.showConfirmDialog(mainController.mainFrame,
							"Student \"" + newStudent + "\" already exists in the database.\n" + "Continue adding student to database?", 
							"Student already exists...", JOptionPane.YES_NO_OPTION);
					if(yesNo == JOptionPane.NO_OPTION) {
						return;
					}
				}
				studentDAO.save(newStudent);
				studentDAO.commitTransaction();
				studentListModel.addElement(newStudent);
			} catch (Exception ex) {
				// TODO Write error method
				System.err.println("Error creating new student");
			}
		}
	}
	
	private void studentEditButtonActionPerformed(ActionEvent evt) {
		Student student = studentPanel.getSelectedStudent();
		String studentString = (String) JOptionPane.showInputDialog(mainController.mainFrame, 
				"Student Name:", 
				"Edit Student...", 
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				student.getName());
		if(studentString != null) {
			try {
				studentListModel.removeElement(student);
				student.setName(studentString);
				studentDAO.beginTransaction();
				studentDAO.save(student);
				studentDAO.commitTransaction();
				studentListModel.addElement(student);
			} catch (Exception ex) {
				// TODO Write error method
				System.err.println("Error saving edited student");
			}
		}
	}
	
	private void studentDeleteButtonActionPerformed(ActionEvent evt) {
		Student student = studentPanel.getSelectedStudent();
		int yesNo = JOptionPane.showConfirmDialog(mainController.mainFrame,
				"Are you sure you want to permanently delete\n\n" + student + "\n\nfrom the database?",
				"Delete student?", JOptionPane.YES_NO_OPTION);
		if(yesNo == JOptionPane.YES_OPTION) {
			try {
				studentDAO.beginTransaction();
				studentDAO.delete(student);
				studentDAO.commitTransaction();
				studentListModel.removeElement(student);
			} catch (Exception ex) {
				// TODO Write error method
				System.err.println("Error deleting student");
			}
		}
	}
	
	private void studentListSelectionEventPerformed(ListSelectionEvent evt) {
		Student selectedStudent = studentPanel.getSelectedStudent();
		List<Integer> scores = selectedStudent.getScores();
		float averageScore = 0;
		int topScore = 0;
		studentPanel.setGamesPlayed(selectedStudent.getGamesPlayed());
		if(scores != null) {
			for(int score : scores) {
				if(score > topScore)
					topScore = score;
				averageScore += score;
			}
			averageScore /= scores.size();
		}
		studentPanel.setAverageScore(averageScore);
		studentPanel.setTopScore(topScore);
	}
}
