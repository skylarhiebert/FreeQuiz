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
package org.freequiz.www.library;

import java.util.Arrays;
import java.util.Collection;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import org.freequiz.www.model.Question;

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
		this(new Vector<Question>());
	}
	
	public FQQuestionsTableModel(Vector<Question> questions) {
		super();
		columnNames = new Vector<String>(Arrays.asList(new String[] { "Question", "Subject", "Topic", "Grade Level", "Difficulty" } ));
		this.questions = questions;
	}
	
	public Question getQuestionAtRow(int rowIndex) {
		try {
			return questions.get(rowIndex);
		} catch (ArrayIndexOutOfBoundsException ex) {
			return null;
		}
		
	}
	
	protected static Vector<Object> convertToVector(Object[] anArray) {
		Vector<Object> newVector = new Vector<Object>();	
		newVector.addAll(Arrays.asList(anArray));
		return newVector;
	}
	
	@Override 
	public String getColumnName(int columnIndex) {
		return columnNames.get(columnIndex);
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
		if(questions.isEmpty())
			return null;
		switch(columnIndex) {
		case 0:
			return questions.get(rowIndex).getQuestionText();
		case 1:
			return questions.get(rowIndex).getTopic().getSubject();
		case 2:
			return questions.get(rowIndex).getTopic();
		case 3:
			return questions.get(rowIndex).getGradeLevel();
		case 4:
			return questions.get(rowIndex).getDifficulty();
		default:
			return questions.get(rowIndex);
		}
	}
	
	public void addRow(Question newRow) {
		questions.add(newRow);
		fireTableRowsInserted(getRowCount() - 2, getRowCount() - 1);
	}
	
	public void addRows(Collection<Question> c) {
		int firstRow = getRowCount() - 1;
		questions.addAll(c);
		int lastRow = getRowCount() - 1;
		fireTableRowsInserted(firstRow, lastRow);
	}
	
	public void removeRow(Question question) {
		int row = questions.indexOf(question);
		questions.remove(question);
		fireTableRowsDeleted(row, row);
	}
	
	public void removeAllRows() {
		int lastRow = getRowCount() - 1;
		if(lastRow < 0) return; // No rows to delete
		questions.removeAll(questions);
		fireTableRowsDeleted(0, lastRow);
	}
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
