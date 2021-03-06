/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json_xml_iva;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ramossoft
 */
public class LineItensNota {

    LineItem Line = new LineItem();

    public class LineItem {

        String LineNumber = "1";
        OrderReferences OrderReferences = new OrderReferences();
        String ProductCode = "PR LJ5P";
        String ProductDescription = "Impressora Laser Jet 5P";
        String Quantity = "1";
        String UnitOfMeasure = "Unid";
        String UnitPrice = "482.59";
        String TaxPointDate = "2007-01-24";
        References References = new References();

        //List<LineItensSourceDocumentID> sourceDocumentIDs = new ArrayList<>();
        String Description = "bom";
        String DebitAmount = "0.00";
        String CreditAmount = "0.00";
        //  SourceDocumentID sourceDocumentID = new SourceDocumentID();
        Tax Tax = new Tax();
        String TaxExemptionReason = "";
        String TaxExemptionCode = "";
        String SettlementAmount = "0";

        public class OrderReferences {

            String OriginatingON = "";
            String OrderDate = "";

            public String getOriginatingON() {
                return OriginatingON;
            }

            public void setOriginatingON(String OriginatingON) {
                this.OriginatingON = OriginatingON;
            }

            public String getOrderDate() {
                return OrderDate;
            }

            public void setOrderDate(String OrderDate) {
                this.OrderDate = OrderDate;
            }
        }

        public class References {

            String Reference = "";
            String Reason = "";

            public String getReference() {
                return Reference;
            }

            public void setReference(String Reference) {
                this.Reference = Reference;
            }

            public String getReason() {
                return Reason;
            }

            public void setReason(String Reason) {
                this.Reason = Reason;
            }
        }

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

        public String getLineNumber() {
            return LineNumber;
        }

        public void setLineNumber(String LineNumber) {
            this.LineNumber = LineNumber;
        }

        public String getProductCode() {
            return ProductCode;
        }

        public void setProductCode(String ProductCode) {
            this.ProductCode = ProductCode;
        }

        public String getProductDescription() {
            return ProductDescription;
        }

        public void setProductDescription(String ProductDescription) {
            this.ProductDescription = ProductDescription;
        }

        public String getQuantity() {
            return Quantity;
        }

        public void setQuantity(String Quantity) {
            this.Quantity = Quantity;
        }

        public String getUnitOfMeasure() {
            return UnitOfMeasure;
        }

        public void setUnitOfMeasure(String UnitOfMeasure) {
            this.UnitOfMeasure = UnitOfMeasure;
        }

        public String getUnitPrice() {
            return UnitPrice;
        }

        public void setUnitPrice(String UnitPrice) {
            this.UnitPrice = UnitPrice;
        }

        public String getTaxPointDate() {
            return TaxPointDate;
        }

        public void setTaxPointDate(String TaxPointDate) {
            this.TaxPointDate = TaxPointDate;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getDebitAmount() {
            return DebitAmount;
        }

        public void setDebitAmount(String DebitAmount) {
            this.DebitAmount = DebitAmount;
        }

        public String getCreditAmount() {
            return CreditAmount;
        }

        public void setCreditAmount(String CreditAmount) {
            this.CreditAmount = CreditAmount;
        }

        public OrderReferences getOrderReferences() {
            return OrderReferences;
        }

        public void setOrderReferences(OrderReferences OrderReferences) {
            this.OrderReferences = OrderReferences;
        }

        public References getReferences() {
            return References;
        }

        public void setReferences(References References) {
            this.References = References;
        }
        

        public Tax getTax() {
            return Tax;
        }

        public void setTax(Tax Tax) {
            this.Tax = Tax;
        }

//        public SourceDocumentID getSourceDocumentID() {
//            return sourceDocumentID;
//        }
//
//        public void setSourceDocumentID(SourceDocumentID sourceDocumentID) {
//            this.sourceDocumentID = sourceDocumentID;
//        }
        public String getSettlementAmount() {
            return SettlementAmount;
        }

        public void setSettlementAmount(String SettlementAmount) {
            this.SettlementAmount = SettlementAmount;
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

    }

    public LineItem getLine() {
        return Line;
    }

    public void setLine(LineItem Line) {
        this.Line = Line;
    }

}
