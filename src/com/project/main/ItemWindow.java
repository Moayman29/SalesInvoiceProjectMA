

package com.project.main;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class ItemWindow extends JDialog {
    private JTextField inField;
    private JTextField icField;
    private JTextField ipField;
    private JLabel inLabel;
    private JLabel icLabel;
    private JLabel ipLabel;
    private JButton okButton;
    private JButton cancelButton;
    
    
    public ItemWindow(MainProjectFrame mpf) {
        inField = new JTextField(20);
        inLabel = new JLabel("Item Name");
        
        icField = new JTextField(20);
        icLabel = new JLabel("Item Count");
        
        ipField = new JTextField(20);
        ipLabel = new JLabel("Item Price");
        
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        okButton.setActionCommand("createItemConfirm");
        cancelButton.setActionCommand("createItemCancel");
        
        okButton.addActionListener(mpf.getActions());
        cancelButton.addActionListener(mpf.getActions());
        setLayout(new GridLayout(4, 2));
        
        add(inLabel);
        add(inField);
        add(icLabel);
        add(icField);
        add(ipLabel);
        add(ipField);
        add(okButton);
        add(cancelButton);
        
        pack();
    }

    public JTextField getInField() {
        return inField;
    }

    public JTextField getIcField() {
        return icField;
    }

    public JTextField getIpField() {
        return ipField;
    }
}
