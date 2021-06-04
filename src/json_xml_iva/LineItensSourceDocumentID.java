/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json_xml_iva;

/**
 *
 * @author Ba
 */
public class LineItensSourceDocumentID {
    
    SourceDocumentID sourceDocumentID = new SourceDocumentID();

    public class SourceDocumentID {

        String OriginatingON = "";
        String InvoiceDate = "";

        public String getOriginatingON() {
            return OriginatingON;
        }

        public void setOriginatingON(String OriginatingON) {
            this.OriginatingON = OriginatingON;
        }

        public String getInvoiceDate() {
            return InvoiceDate;
        }

        public void setInvoiceDate(String InvoiceDate) {
            this.InvoiceDate = InvoiceDate;
        }
        
    }

    
}
