
package com.project.sub;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class InvoicesLineTable extends AbstractTableModel {
    
    private ArrayList<InvoiceLineSide> ils;
    private String[] columns = {"No.","Item Name","Item Price","Count","Item Total"};

    public InvoicesLineTable(ArrayList<InvoiceLineSide> ils) {
        this.ils = ils;
    }
    
    public ArrayList<InvoiceLineSide> getIls(){
    return ils;
    }
    
    
    @Override
    public int getRowCount() {
    return ils.size();
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
    InvoiceLineSide invoiceLineSide = ils.get(rowIndex);
    
    switch(columnIndex){
        case 0: return invoiceLineSide.getInvoiceHeader().getIhNumber();
        case 1: return invoiceLineSide.getIlItem();
        case 2: return invoiceLineSide.getIlPrice();
        case 3: return invoiceLineSide.getIlCount();
        case 4: return invoiceLineSide.getInvoiceLineSideTotal();
        default : return "";
    }
    }
    
}
