/**
 * 
 */
package org.freequiz.www.model;

import java.util.Arrays;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 * @author Skylar Hiebert
 *
 */
public class FQQuestionsTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4253156443072136843L;
	private Vector<String> columnNames; 
	private Vector<Question> questions;

	/**
	 * Class constructor
	 */
	public FQQuestionsTableModel() {
		super();
		columnNames = new Vector<String>(Arrays.asList(new String[] { "Question", "Subject", "Topic", "Grade Level" } ));
		questions = new Vector<Question>();
	}
	
	public FQQuestionsTableModel(Object[] columns) {
		super();
	}
	
	public Question getQuestionAtRow(int rowIndex) {
		return questions.get(rowIndex);
	}
	
//	public void 
	
	protected static Vector<Object> convertToVector(Object[] anArray) {
		Vector<Object> newVector = new Vector<Object>();	
		newVector.addAll(Arrays.asList(anArray));
		return newVector;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	@Override
	public int getRowCount() {
		return questions.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return questions.get(rowIndex).getQuestionText();
		case 1:
			return questions.get(rowIndex).getSubject();
		case 2:
			return questions.get(rowIndex).getTopic();
		case 3:
			return questions.get(rowIndex).getGradeLevel();
		default:
			return questions.get(rowIndex);
		}
	}
}
