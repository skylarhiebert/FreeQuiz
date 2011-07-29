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

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.freequiz.www.model.Roster;

/**
 * @author Skylar Hiebert
 *
 */
public class FQRosterTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4253156443072136843L;
	private List<String> columnNames; 
	private List<Roster> rosters;

	/**
	 * Class constructor
	 */
	public FQRosterTableModel() {
		this(new ArrayList<Roster>());
	}
	
	public FQRosterTableModel(List<Roster> rosters) {
		super();
		columnNames = new ArrayList<String>(java.util.Arrays.asList(new String[] { "Name", "Description", "Number of Students" } ));
		this.rosters = rosters;
	}
	
	public Roster getRosterAtRow(int rowIndex) {
		try {
			return rosters.get(rowIndex);
		} catch (ArrayIndexOutOfBoundsException ex) {
			return null;
		}
	}
	
	protected static List<Object> convertToList(Object[] anArray) {
		List<Object> newList = new ArrayList<Object>();	
		newList.addAll(java.util.Arrays.asList(anArray));
		return newList;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnName()
	 */
	@Override 
	public String getColumnName(int columnIndex) {
		return columnNames.get(columnIndex);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return rosters.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(rosters.isEmpty())
			return null;
		switch(columnIndex) {
		case 0:
			return rosters.get(rowIndex).getName();
		case 1:
			return rosters.get(rowIndex).getDescription();
		case 2:
			return rosters.get(rowIndex).getNumberOfStudents();
		default:
			return rosters.get(rowIndex);
		}
	}
	
		public void addRow(Roster newRow) {
			int firstRow = getRowCount() - 1;
			rosters.add(newRow);
			int lastRow = getRowCount() - 1;
			fireTableRowsInserted(firstRow, lastRow);
		}
		
		public void addRows(java.util.Collection<Roster> c) {
			int firstRow = getRowCount() - 1;
			rosters.addAll(c);
			int lastRow = getRowCount() - 1;
			fireTableRowsInserted(firstRow, lastRow);
		}
		
		public void removeRow(Roster roster) {
			int row = rosters.indexOf(roster);
			rosters.remove(roster);
			fireTableRowsDeleted(row, row);
		}
		
		public void removeAllRows() {
			int lastRow = getRowCount() - 1;
			if(lastRow < 0) return; // No rows to delete
			rosters.removeAll(rosters);
			fireTableRowsDeleted(0, lastRow);
		}
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}

