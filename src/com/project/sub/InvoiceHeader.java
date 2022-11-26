
package com.project.sub;

import java.util.ArrayList;


public class InvoiceHeader { 
    private int ihNumber;
    private String ihDate;
    private String ihCustomer;
    private ArrayList<InvoiceLineSide> ils;
    

     public InvoiceHeader() {
    }
    
     
     public InvoiceHeader(int ihNumber, String ihDate, String ihCustomer) {
        this.ihNumber = ihNumber;
        this.ihDate = ihDate;
        this.ihCustomer = ihCustomer;
    }
     
     
     
    public double getIhTotal(){
      double total = 0.0;
      for (InvoiceLineSide invoiceLineSide : getIls()){
           total += invoiceLineSide.getInvoiceLineSideTotal();
         }
      return total;
     }
    
    public ArrayList<InvoiceLineSide> getIls() {
        if (ils == null){
            ils= new ArrayList<>();
        }
        return ils;
    }
    public String getIhCustomer() {
        return ihCustomer;
    }

    public void setIhCustomer(String ihCustomer) {
        this.ihCustomer = ihCustomer;
    }

    public int getIhNumber() {
        return ihNumber;
    }

    public void setIhNumber(int ihNumber) {
        this.ihNumber = ihNumber;
    }

    public String getIhDate() {
        return ihDate;
    }

    public void setIhDate(String ihDate) {
        this.ihDate = ihDate;
    }


    @Override
    public String toString() {
        return "InvoiceHeader{" + "ihNumber=" + ihNumber + ", ihDate=" + ihDate + ", ihCustomer=" + ihCustomer + ", ils=" + ils + '}';
    }


    public String getFileTypeCSV() {
        return ihNumber + "," + ihDate + "," + ihCustomer;
    }
}
