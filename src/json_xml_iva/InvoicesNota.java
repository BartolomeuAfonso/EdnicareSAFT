/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json_xml_iva;

import java.util.List;

/**
 *
 * @author Ramossoft
 */
public class InvoicesNota {
    
    List<InvoiceNota> Invoice;

    public InvoicesNota(List<InvoiceNota> Invoice) {
        this.Invoice = Invoice;
    }

    public List<InvoiceNota> getInvoice() {
        return Invoice;
    }

    public void setInvoice(List<InvoiceNota> Invoice) {
        this.Invoice = Invoice;
    }
    
}
