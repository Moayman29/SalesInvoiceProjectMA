
package com.project.sub;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class InvoicesHeaderTable extends AbstractTableModel {
    private ArrayList<InvoiceHeader> invoiceHeaders;
    private String[] columns = {"No.","Date","Customer","Total"};

    public InvoicesHeaderTable(ArrayList<InvoiceHeader> invoiceHeaders) {
        this.invoiceHeaders = invoiceHeaders;
    }

    @Override
    public int getRowCount() {
    return invoiceHeaders.size();
    }

    @Override
    public int getColumnCount() {
    return columns.length;
    }
    
    @Override
    public String getColumnName(int column){
    return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
     InvoiceHeader invoiceHeader = invoiceHeaders.get(rowIndex);
     
     switch (columnIndex) {
         case 0: return invoiceHeader.getIhNumber();
         case 1: return invoiceHeader.getIhDate();
         case 2: return invoiceHeader.getIhCustomer();
         case 3: return invoiceHeader.getIhTotal();
         default: return "";
     }
    }
    
    
}
