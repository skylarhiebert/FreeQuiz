/**
 * 
 */
package org.freequiz.www.library;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/**
 * @author Skylar Hiebert
 *
 */
public class FQTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5524766051356679085L;
	protected Vector<?> columnIdentifiers;
	protected Vector<Vector <?>> dataVector;

	/**
	 * 
	 */
	public FQTableModel() {
		super();
	}
	
	/**
	 * Constructs a <code>DefaultTableModel</code> with <code>rowCount</code> and <code>columnCount</code> of <code>null</code> object values.
	 * @param rowCount
	 * @param columnCount
	 * @see #setValueAt(Object, int, int)
	 */
	public FQTableModel(int rowCount, int columnCount) {
		
	}
	
	public FQTableModel(Vector<?> columnNames, int rowCount) {
		
	}
	
	/**
	 * Constructs a <code>DefaultTableModel</code> and initializes the table by passing <code>data</code> and <code>columnNames</code> to the <code>setDataVector</code> method.
	 * @param data
	 * @param columnNames
	 */
	public FQTableModel(Vector<?> data, Vector<?> columnNames) {
		super();
		
	//	setDataVector(data);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
