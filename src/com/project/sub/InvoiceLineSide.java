
package com.project.sub;


public class InvoiceLineSide {
    private String ilItem;
    private double ilPrice;
    private int ilCount;
    private InvoiceHeader invoiceHeader;

    public InvoiceLineSide() {
    }

    public InvoiceLineSide( String ilItem, double ilPrice, int ilCount, InvoiceHeader invoiceHeader) {
        this.ilItem = ilItem;
        this.ilPrice = ilPrice;
        this.ilCount = ilCount;
        this.invoiceHeader = invoiceHeader;
    }
    
    public double getInvoiceLineSideTotal(){
       return ilPrice * ilCount;
    }

    public int getIlCount() {
        return ilCount;
    }

    public void setIlCount(int ilCount) {
        this.ilCount = ilCount;
    }


    public String getIlItem() {
        return ilItem;
    }

    public void setIlItem(String ilItem) {
        this.ilItem = ilItem;
    }

    public double getIlPrice() {
        return ilPrice;
    }

    public void setIlPrice(double ilPrice) {
        this.ilPrice = ilPrice;
    }

    public InvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }

    @Override
    public String toString() {
        return "InvoiceLineSide{" + "ilNumber=" + invoiceHeader.getIhNumber() + ", ilItem=" + ilItem + ", ilPrice=" + ilPrice + ", ilCount=" + ilCount + '}';
    }
    
    public String getFileTypeCSV() {
        return invoiceHeader.getIhNumber() + "," + ilItem + "," + ilPrice + "," + ilCount;
    }

  //  public String getFileTypeCSV() {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    
    
}
