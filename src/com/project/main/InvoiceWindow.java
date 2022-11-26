
package com.project.main;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InvoiceWindow extends JDialog {
    
    private JLabel nameOfCustomerLabel;
    private JLabel invoiceDateLabel;
    private JTextField nameOfCustomerField;
    private JTextField invoiceDateField;
    private JButton confirmationButton;
    private JButton cancelButton;
    
    public InvoiceWindow(MainProjectFrame mpf) {
        nameOfCustomerLabel = new JLabel("Customer Name:");
        nameOfCustomerField = new JTextField(20);
        invoiceDateLabel = new JLabel("Invoice Date:");
        invoiceDateField = new JTextField(20);
        confirmationButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        confirmationButton.setActionCommand("createInvoiceConfirm");
        cancelButton.setActionCommand("createInvoiceCancel");
        
        confirmationButton.addActionListener(mpf.getActions());
        cancelButton.addActionListener(mpf.getActions());
        setLayout(new GridLayout(3, 2));
        
        add(invoiceDateLabel);
        add(invoiceDateField);
        add(nameOfCustomerLabel);
        add(nameOfCustomerField);
        add(confirmationButton);
        add(cancelButton);
        
        pack();
        
    }

    public JTextField getNameOfCustomerField() {
        return nameOfCustomerField;
    }

    public JTextField getInvoiceDateField() {
        return invoiceDateField;
    }
    
}
