package com.cs521.team7;

import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by Acer on 6/29/2016.
 */
public class HomePageProductTableModel implements TableModel {
    public CachedRowSet productRowSet;
    ResultSetMetaData metadata; // Additional information about the results
    int numcols, numrows; // How many rows and columns in the table
    public CachedRowSet getCoffeesRowSet() {
        return productRowSet;
    }
    public HomePageProductTableModel(CachedRowSet myCachedRowSet) throws SQLException {
        this.productRowSet = myCachedRowSet;
        this.metadata = this.productRowSet.getMetaData();
        numcols = metadata.getColumnCount();

        // Retrieve the number of rows.
        this.productRowSet.beforeFirst();
        this.numrows = 0;
        while (this.productRowSet.next()) {
            this.numrows++;
        }
        this.productRowSet.beforeFirst();
    }

    public void addEventHandlersToRowSet(RowSetListener listener) {
        this.productRowSet.addRowSetListener(listener);
    }

    public void close() {
        try {
            productRowSet.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getRowCount() {
        return numrows;
    }

    @Override
    public int getColumnCount() {
        return numcols;
    }

    @Override
    public String getColumnName(int columnIndex) {
        // TODO Auto-generated method stub
        try {
            return this.metadata.getColumnLabel(columnIndex + 1);
        } catch (SQLException e) {
            return e.toString();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // TODO Auto-generated method stub
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        try {
            this.productRowSet.absolute(rowIndex + 1);
            Object o = this.productRowSet.getObject(columnIndex + 1);
            if (o == null)
                return null;
            else
                return o.toString();
        } catch (SQLException e) {
            return e.toString();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        System.out.println("Calling setValueAt row " + rowIndex + ", column " + columnIndex);

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
