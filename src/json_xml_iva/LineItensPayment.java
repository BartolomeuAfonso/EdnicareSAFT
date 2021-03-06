/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json_xml_iva;

import java.util.List;
import json_xml_iva.LineItensPayment.LineItem;

/**
 *
 * @author Ramossoft
 */
public class LineItensPayment {

    LineItem Line = new LineItem();

    public class LineItem {

        String LineNumber = "3467";
        SourceDocumentID SourceDocumentID = new SourceDocumentID();
        String SettlementAmount = "0";
        String CreditAmount = "0";
        Tax Tax = new Tax();
        String TaxExemptionReason = "";
        String TaxExemptionCode = "";

        public class Tax {

            String TaxType = "IVA";
            String TaxCountryRegion = "AO";
            String TaxCode = "26";
            String TaxPercentage = "14";

            public String getTaxType() {
                return TaxType;
            }

            public void setTaxType(String TaxType) {
                this.TaxType = TaxType;
            }

            public String getTaxCode() {
                return TaxCode;
            }

            public void setTaxCode(String TaxCode) {
                this.TaxCode = TaxCode;
            }

            public String getTaxPercentage() {
                return TaxPercentage;
            }

            public void setTaxPercentage(String TaxPercentage) {
                this.TaxPercentage = TaxPercentage;
            }

            public String getTaxCountryRegion() {
                return TaxCountryRegion;
            }

            public void setTaxCountryRegion(String TaxCountryRegion) {
                this.TaxCountryRegion = TaxCountryRegion;
            }
        }

        public class SourceDocumentID {

            String OriginatingON = "string";
            String InvoiceDate = "2005-04-01";
            //  String Description = "t";

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

//            public String getDescription() {
//                return Description;
//            }
//
//            public void setDescription(String Description) {
//                this.Description = Description;
//            }
        }

        public String getLineNumber() {
            return LineNumber;
        }

        public void setLineNumber(String LineNumber) {
            this.LineNumber = LineNumber;
        }

        public SourceDocumentID getSourceDocumentID() {
            return SourceDocumentID;
        }

        public void setSourceDocumentID(SourceDocumentID SourceDocumentID) {
            this.SourceDocumentID = SourceDocumentID;
        }

        public String getCreditAmount() {
            return CreditAmount;
        }

        public void setCreditAmount(String CreditAmount) {
            this.CreditAmount = CreditAmount;
        }
        public String getTaxExemptionReason() {
            return TaxExemptionReason;
        }

        public void setTaxExemptionReason(String TaxExemptionReason) {
            this.TaxExemptionReason = TaxExemptionReason;
        }

        public String getTaxExemptionCode() {
            return TaxExemptionCode;
        }

        public void setTaxExemptionCode(String TaxExemptionCode) {
            this.TaxExemptionCode = TaxExemptionCode;
        }

        public String getSettlementAmount() {
            return SettlementAmount;
        }

        public void setSettlementAmount(String SettlementAmount) {
            this.SettlementAmount = SettlementAmount;
        }

        public Tax getTax() {
            return Tax;
        }

        public void setTax(Tax Tax) {
            this.Tax = Tax;
        }

        public LineItem() {
        }

    }

    public LineItem getLine() {
        return Line;
    }

    public void setLine(LineItem Line) {
        this.Line = Line;
    }

}
