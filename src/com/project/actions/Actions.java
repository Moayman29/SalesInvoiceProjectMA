
package com.project.actions;

import com.project.main.MainProjectFrame;
import com.project.main.InvoiceWindow;
import com.project.main.ItemWindow;
import com.project.sub.InvoiceHeader;
import com.project.sub.InvoiceLineSide;
import com.project.sub.InvoicesHeaderTable;
import com.project.sub.InvoicesLineTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.Date;
import javax.swing.JOptionPane;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.FileWriter;



public class Actions implements ActionListener, ListSelectionListener {
    
    private MainProjectFrame mpf;
    private InvoiceWindow invoiceWindow;
    private ItemWindow itemWindow;
    
    public Actions(MainProjectFrame mpf){
        this.mpf = mpf;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        System.out.println("Perform " + actionCommand);
        switch(actionCommand){
            case "Load File":
                loadFile();
               break;
            case "Save File":
                saveFile();
               break;
            case "Create New Invoice":
                createNewInvoice();
               break;
            case "Delete Invoice":
                deleteInvoice();
               break;
            case "Create New Item":
                createNewItem();
               break; 
            case "Delete Item":
                deleteItem();
               break;   
            case "createInvoiceConfirm":
                createInvoiceConfirm();
                break;
            case "createInvoiceCancel":
                createInvoiceCancel();
                break;
            case "createItemConfirm":
                createItemConfirm();
                break;
            case "createItemCancel":
                createItemCancel();
                break;
        }
     }
    
        @Override
    public void valueChanged(ListSelectionEvent e) {
        int choosenIndex = mpf.getInvoiceTable().getSelectedRow();
        if (choosenIndex != -1){
        System.out.println("Row Choosen:" + choosenIndex);
        InvoiceHeader currentIH = mpf.getInvoiceHeaders().get(choosenIndex);
        mpf.getInvoiceNumberLabel().setText(""+currentIH.getIhNumber());
        mpf.getInvoiceDateField().setText(currentIH.getIhDate());
        mpf.getCustomerNameField().setText(currentIH.getIhCustomer());
        mpf.getInvoiceTotalLabel().setText(""+currentIH.getIhTotal());
        InvoicesLineTable invoicesLineTable = new InvoicesLineTable(currentIH.getIls());
        mpf.getInvoiceItems().setModel(invoicesLineTable);
        invoicesLineTable.fireTableDataChanged();
        }
    }

    private void loadFile() {
        JFileChooser fileChooser= new JFileChooser();
        try{
            int found= fileChooser.showOpenDialog(mpf);
            if (found  == JFileChooser.APPROVE_OPTION) {
                File mainFile= fileChooser.getSelectedFile();
                Path mainPath= Paths.get(mainFile.getAbsolutePath());
                List<String> mainLines= Files.readAllLines(mainPath);
                System.out.println("Invoices read");
                
                ArrayList<InvoiceHeader> ihArray = new ArrayList<>();
                for (String mainLine : mainLines){
                    try{
                        String[] mainParts = mainLine.split(",");
                        int invoiceNumberLabel = Integer.parseInt(mainParts[0]);
                        String invoiceDateField = mainParts[1];
                        String customerNameField = mainParts[2];
                    
                        InvoiceHeader invoiceHeader= new InvoiceHeader
                            (invoiceNumberLabel,invoiceDateField,customerNameField);
                        ihArray.add(invoiceHeader);
                    }catch (Exception es) {
                        es.printStackTrace();
                        JOptionPane.showMessageDialog(mpf, "format error", "Error", JOptionPane.ERROR_MESSAGE);
                    }    
                }
                System.out.println("C Point");
               found = fileChooser.showOpenDialog(mpf);
               if (found == JFileChooser.APPROVE_OPTION){
                   File subFile = fileChooser.getSelectedFile();
                   Path subPath = Paths.get(subFile.getAbsolutePath());
                   List<String> subLines = Files.readAllLines(subPath);
                 System.out.println("Lines read");   
                 for (String subLine : subLines){
                     try{
                        String subParts[] = subLine.split(",");
                        int ilNumber =Integer.parseInt(subParts[0]);
                        String ilItem= subParts[1];
                        double ilPrice= Double.parseDouble(subParts[2]);
                        int ilCount = Integer.parseInt(subParts[3]);
                        InvoiceHeader inh= null;
                        for(InvoiceHeader invoiceHeader : ihArray){
                            if (invoiceHeader.getIhNumber()==ilNumber){
                             inh = invoiceHeader;
                             break; 
                         }
                     }
                     
                        InvoiceLineSide invoiceLineSide = new InvoiceLineSide(ilItem,ilPrice,ilCount,inh);
                        inh.getIls().add(invoiceLineSide);
                     } catch (Exception es) {
                        es.printStackTrace();
                        JOptionPane.showMessageDialog(mpf, "format error", "Error", JOptionPane.ERROR_MESSAGE);
                     }
                 }
                 
                 System.out.println("C Point 2");
               }
              mpf.setInvoiceHeaders(ihArray);
              InvoicesHeaderTable invoicesHeaderTable = new InvoicesHeaderTable(ihArray);
              mpf.setInvoicesHeaderTable(invoicesHeaderTable);
              mpf.getInvoiceTable().setModel(invoicesHeaderTable);
              mpf.getInvoicesHeaderTable().fireTableDataChanged();
            
            }
        }catch (IOException es){
           es.printStackTrace();
           JOptionPane.showMessageDialog(mpf, "file not read", "Error", JOptionPane.ERROR_MESSAGE);
        }
           
    }

    private void saveFile() {
    ArrayList<InvoiceHeader> invoiceHeaders = mpf.getInvoiceHeaders();
        String headerTitle = "";
        String rowLines = "";
        for (InvoiceHeader invoiceHeader : invoiceHeaders) {
            String invFileType = invoiceHeader.getFileTypeCSV();
            headerTitle += invFileType;
            headerTitle += "\n";

            for (InvoiceLineSide invoiceLineSide : invoiceHeader.getIls()) {
                String ILFileType = invoiceLineSide.getFileTypeCSV();
                rowLines += ILFileType;
                rowLines += "\n";
            }
        }
        
        System.out.println("Check point 3");
        try {
            JFileChooser fileChooser = new JFileChooser();
            int found = fileChooser.showSaveDialog(mpf);
            if (found == JFileChooser.APPROVE_OPTION) {
                File mainFile = fileChooser.getSelectedFile();
                FileWriter mainFileWriter = new FileWriter(mainFile);
                mainFileWriter.write(headerTitle);
                mainFileWriter.flush();
                mainFileWriter.close();
                found = fileChooser.showSaveDialog(mpf);
                if (found == JFileChooser.APPROVE_OPTION) {
                    File rowFile = fileChooser.getSelectedFile();
                    FileWriter rowFileWriter = new FileWriter(rowFile);
                    rowFileWriter.write(rowLines);
                    rowFileWriter.flush();
                    rowFileWriter.close();
                }
            }
        } catch (Exception es) {

        }
        
   }

    private void createNewInvoice() {
        invoiceWindow = new InvoiceWindow(mpf);
        invoiceWindow.setVisible(true);
    }

    private void deleteInvoice() {
        int selectedRow = mpf.getInvoiceTable().getSelectedRow();
        if (selectedRow != -1) {
            mpf.getInvoiceHeaders().remove(selectedRow);
            mpf.getInvoicesHeaderTable().fireTableDataChanged();
        }
    }

    private void createNewItem() {
        itemWindow = new ItemWindow(mpf);
        itemWindow.setVisible(true);
    }

    private void deleteItem() {
        int selectedRow = mpf.getInvoiceItems().getSelectedRow();
        if (selectedRow != -1) {
            InvoicesLineTable invoicesLineTable = (InvoicesLineTable) mpf.getInvoiceItems().getModel();
            invoicesLineTable.getIls().remove(selectedRow);
            invoicesLineTable.fireTableDataChanged();
        }
   }

    private void createInvoiceConfirm() {
        String ihdate = invoiceWindow.getInvoiceDateField().getText();
        String ihcustomer = invoiceWindow.getNameOfCustomerField().getText();
        int ihNumber = mpf.getNextInvoiceNumber();
        try {
            String[] dateParts = ihdate.split("-");
            if (dateParts.length < 3) {
                JOptionPane.showMessageDialog(mpf, "date format error", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int day = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);
                if (day > 31 || month > 12) {
                    JOptionPane.showMessageDialog(mpf, "date format error", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    InvoiceHeader invoiceHeader = new InvoiceHeader(ihNumber, ihdate, ihcustomer);
                    mpf.getInvoiceHeaders().add(invoiceHeader);
                    mpf.getInvoicesHeaderTable().fireTableDataChanged();
                    invoiceWindow.setVisible(false);
                    invoiceWindow.dispose();
                    invoiceWindow = null;
                }
            }
        } catch (Exception es) {
            JOptionPane.showMessageDialog(mpf, "date format error", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void createInvoiceCancel() {
        invoiceWindow.setVisible(false);
        invoiceWindow.dispose();
        invoiceWindow = null;
    }

    private void createItemConfirm() {
        String ilItem = itemWindow.getInField().getText();
        String stringOfCount = itemWindow.getIcField().getText();
        String stringOfPrice = itemWindow.getIpField().getText();
        int ilCount = Integer.parseInt(stringOfCount);
        double ilPrice = Double.parseDouble(stringOfPrice);
        int choosenInvoice = mpf.getInvoiceTable().getSelectedRow();
        if (choosenInvoice != -1) {
            InvoiceHeader invoiceHeader = mpf.getInvoiceHeaders().get(choosenInvoice);
            InvoiceLineSide invoiceLineSide = new InvoiceLineSide(ilItem, ilPrice, ilCount, invoiceHeader);
            invoiceHeader.getIls().add(invoiceLineSide);
            InvoicesLineTable invoicesLineTable = (InvoicesLineTable) mpf.getInvoiceItems().getModel();
            invoicesLineTable.fireTableDataChanged();
            mpf.getInvoicesHeaderTable().fireTableDataChanged();
        }
        itemWindow.setVisible(false);
        itemWindow.dispose();
        itemWindow = null;
    }

    private void createItemCancel() {
        itemWindow.setVisible(false);
        itemWindow.dispose();
        itemWindow = null;
    }
   
}
