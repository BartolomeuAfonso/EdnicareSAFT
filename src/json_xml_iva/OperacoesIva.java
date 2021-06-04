/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json_xml_iva;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import CLINICA.controller.ControllerFactura;
import CLINICA.controller.ControllerFacturaItens;
import CLINICA.controller.ControllerNotasItens;
import CLINICA.controller.ControllerEmpresa;
import sf.ce.conexao.ConexaoBancos;
import sf.ce.utilizacoes.Data;
import sf.ce.conexao.Definicoes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import CLINICA.modelo.Empresa;
import java.util.ArrayList;

/**
 *
 * @author Ramossoft
 */
public class OperacoesIva {

    ControllerFactura controllerFactura;
    ControllerFacturaItens controllerFacturaItens;
    public DadosFacturaIva dadosFacturaIva;
    public String dataInicio, dataFinal;
    Connection con;
    Data DataComponent;

    public OperacoesIva() {
        System.setProperty("file.encoding", "UTF-8");
        con = new ConexaoBancos().ConexaoBD();
        DataComponent = new Data();
        controllerFactura = new ControllerFactura(con);
        controllerFacturaItens = new ControllerFacturaItens(con);
    }

    public String getValorCasaDecimal(String str, String expressao) {
        String output = "";
        if (str.charAt(0) == ',') {
            output += "0";
        }

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '.') {
                output += str.charAt(i);
            }
        }
        System.out.println("output : " + output);
        return output;
    }

    public void createXML(String output) {
        try {
            String value = Definicoes.file.getAbsolutePath();
            String pathDestinoXSD = value.substring(0, value.lastIndexOf(System.getProperty("file.separator")));

            Runtime rt = Runtime.getRuntime();
            String str = "\"" + pathDestinoXSD + "\"";

            //rt.exec("xcopy C:\\arquivos_xml\\SAFTAO1.01_01.xsd  " + str + "  /y");
            //System.out.println("Local aonde salvou-se o ficheiro :" + pathDestinoXSD);
            Path path = Paths.get(Definicoes.file.getAbsolutePath());

            Files.write(path, output.getBytes("UTF8"), StandardOpenOption.CREATE);

            //FileWriter writer = new FileWriter(Definicoes.file.getAbsolutePath());
            //writer.write(output);
            File file2 = new File(Definicoes.file.getAbsolutePath()).getAbsoluteFile();
            // writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dadosOperacoes() {

        LocalDate date = LocalDate.now();

        BuscarFactura factura = new BuscarFactura();

        ControllerEmpresa empresaController = new ControllerEmpresa(con);
        ArrayList<Empresa> empresa = empresaController.listar();

        String numFactura = factura.totalFactura(getDataInicio(), getDataFinal());
        ControllerNotasItens notasItens = new ControllerNotasItens(con);
        String numNota = notasItens.geTotalNota(getDataInicio(), getDataFinal());
        //MovimentoItemController mc = new MovimentoItemController();

        if (!numFactura.equals("0")) {

            // Quantidade de notas de crédito
            Long numDoc = Long.parseLong(numNota) + Long.parseLong(numFactura);
            numFactura = String.valueOf(numDoc);

            dadosFacturaIva = new DadosFacturaIva();
            //Classe Header
            dadosFacturaIva.getAuditFile().getHeader().setCompanyName(empresa.get(0).getNomeEmpresa());
            dadosFacturaIva.getAuditFile().getHeader().setBusinessName(empresa.get(0).getNomeEmpresa());
            dadosFacturaIva.getAuditFile().getHeader().setTaxRegistrationNumber(empresa.get(0).getNif());
            dadosFacturaIva.getAuditFile().getHeader().setFiscalYear("" + factura.getAnoemCurso());
            dadosFacturaIva.getAuditFile().getHeader().setStartDate(getDataInicio());
            dadosFacturaIva.getAuditFile().getHeader().setEndDate(getDataFinal());
            dadosFacturaIva.getAuditFile().getHeader().setDateCreated(date.toString());
            dadosFacturaIva.getAuditFile().getHeader().setEmail(empresa.get(0).getEmail());
            dadosFacturaIva.getAuditFile().getHeader().setWebsite(empresa.get(0).getWebsite());

            dadosFacturaIva.getAuditFile().getHeader().setTelephone(empresa.get(0).getTelefone());
            //Classe Header End
            dadosFacturaIva.getAuditFile().getHeader().getCompanyAddress().setAddressDetail(empresa.get(0).getEndereco());
            dadosFacturaIva.getAuditFile().getSourceDocuments().getSalesInvoices().setNumberOfEntries("" + numFactura);
            dadosFacturaIva.getAuditFile().getHeader().getCompanyAddress().setCity("Luanda");
            //dadosFacturaIva.getAuditFile().getHeader().getCompanyAddress().setCity(empresa.get(0).getEndereco());
            dadosFacturaIva.getAuditFile().getHeader().getCompanyAddress().setAddressDetail(empresa.get(0).getEndereco());

            int totalFactura = Integer.parseInt(numFactura);
            int totalNotaCredito = Integer.parseInt("0");

            dadosFacturaIva.getAuditFile().getSourceDocuments().getSalesInvoices().setNumberOfEntries("" + (totalFactura + totalNotaCredito));

            List<TaxTableItem> items = factura.listaIsencao(dataInicio, dataFinal);
            dadosFacturaIva.getAuditFile().getMasterFiles().getTaxTable().setTaxTableItems(items);

            DecimalFormat df = new DecimalFormat("#,##0.00#", new DecimalFormatSymbols(new Locale("pt", "BR")));;
            double value = factura.getTotalDebito(getDataInicio(), getDataFinal());
            //    double valueND = factura.getTotalCreditoND(getDataInicio(), getDataFinal());

            String totalDebit = getValorCasaDecimal(df.format(value), ".").replace(',', '.');
            //em analise
            double value1 = factura.getTotalCredit(getDataInicio(), getDataFinal());

            String totalCredit = getValorCasaDecimal(df.format(value1), ".").replace(',', '.');
            dadosFacturaIva.getAuditFile().getSourceDocuments().getSalesInvoices().setTotalDebit(totalDebit);
            dadosFacturaIva.getAuditFile().getSourceDocuments().getSalesInvoices().setTotalCredit(totalCredit);
            dadosFacturaIva.getAuditFile().getSourceDocuments().getSalesInvoices().setInvoices(factura.getDocumentosCormercias(getDataInicio(), getDataFinal()));

            /* dadosFacturaIva.getAuditFile().getSourceDocuments().getMovementOfGoods().setStockMovements(facturaController.getGuias(getDataInicio(), getDataFinal()));
            dadosFacturaIva.getAuditFile().getSourceDocuments().getMovementOfGoods().setNumberOfMovementLines(facturaController.getTotalLinhasGuias(getDataInicio(), getDataFinal()) + "");
            dadosFacturaIva.getAuditFile().getSourceDocuments().getMovementOfGoods().setTotalQuantityIssued(getValorCasaDecimal(df.format(facturaController.getQuantidadesItensGuias(getDataInicio(), getDataFinal())), ".").replace(',', '.'));
             */
            dadosFacturaIva.getAuditFile().getSourceDocuments().getWorkingDocuments().setWorkDocuments(factura.getProformas(getDataInicio(), getDataFinal()));
            dadosFacturaIva.getAuditFile().getSourceDocuments().getWorkingDocuments().setNumberOfEntries(factura.totalFacturaProforma(getDataInicio(), getDataFinal()) + "");
            dadosFacturaIva.getAuditFile().getSourceDocuments().getWorkingDocuments().setTotalCredit(getValorCasaDecimal(df.format(factura.getTotalCreditPerfoma(getDataInicio(), getDataFinal())), ".").replace(',', '.') + "");

            dadosFacturaIva.getAuditFile().getSourceDocuments().getAllPayments().setPayment(factura.getRecibo(getDataInicio(), getDataFinal()));
            dadosFacturaIva.getAuditFile().getSourceDocuments().getAllPayments().setNumberOfEntries(factura.totalFacturaRecibo(getDataInicio(), getDataFinal()) + "");
            dadosFacturaIva.getAuditFile().getSourceDocuments().getAllPayments().setTotalCredit(getValorCasaDecimal(df.format(factura.getTotalCreditRecibo(getDataInicio(), getDataFinal())), ".").replace(',', '.') + "");

            dadosFacturaIva.getAuditFile().getMasterFiles().setCustomerIten(factura.listaClientes(getDataInicio(), getDataFinal()));
            //dadosFacturaIva.getAuditFile().getMasterFiles().setSupplierIten(fornecedoresController.getDadosFornecedor());
            dadosFacturaIva.getAuditFile().getMasterFiles().setProdutItem(factura.getTodosProdutosVendidos(getDataInicio(), getDataFinal()));
            XStream xstream = new XStream(new DomDriver());
            DadosFacturaIva.AuditFile file = dadosFacturaIva.getAuditFile();
            String xml = xstream.toXML(file);
            String output = estruturarAquivoXML(xml);

//            if (verificaCamposObrigatorios(output)) {
            createXML(output);
            //        System.out.println(output);
            JOptionPane.showMessageDialog(null, "Ficheiro salvo com sucesso. ", "Mind Vision Tecnology!", 1);
//            } else {
//                JOptionPane.showMessageDialog(null, "Ficheiro não pode ser gerado, existem campos obrigatórios que não foram preenchidos. ", "Erro!", 1);
//            }

        } else {
            JOptionPane.showMessageDialog(null, "Não Existe Documentos neste periodo", "Mind Vision Tecnology!", JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean verificaCamposObrigatorios(String output) {
        boolean falseTrue = true;
        if (output.contains("<CompanyName></CompanyName>")) {
            falseTrue = false;
        }
        if (output.contains("<AddressDetail></AddressDetail>")) {
            falseTrue = false;
        }
        if (output.contains("<City></City>")) {
            falseTrue = false;
        }
        if (output.contains("<TaxRegistrationNumber></TaxRegistrationNumber>")) {
            falseTrue = false;
        }
        return falseTrue;
    }

    public String estruturarAquivoXML(String xml) {

        String mxlns = " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
                + "  \n "
                + "xsi:schemaLocation=\"urn:OECD:StandardAuditFile-Tax:AO_1.01_01 \\SAFTAO1.01_01.xsd\" xmlns=\"urn:OECD:StandardAuditFile-Tax:AO_1.01_01\"";

        String strAuditFileFirst = xml.replaceFirst("<json__xml__iva.DadosFacturaIva_-AuditFile>", "<AuditFile>");
        String strAuditFileFinish = strAuditFileFirst.replaceFirst("</json__xml__iva.DadosFacturaIva_-AuditFile>", "</AuditFile>");
        String strAuditFile = strAuditFileFinish.replaceFirst("<AuditFile>", "<AuditFile " + mxlns + ">");

        String xml2 = strAuditFile.replaceFirst("<Header>", " <Header>");
//        String xml3 = xml2.replaceFirst("<MasterFiles>", " <Header>");
        String strInvoiceHead = xml2;
        String strInvoiceTail = strInvoiceHead.replaceAll("</json__xml__iva.Invoice>", "");
        String strInvoicesHead = strInvoiceTail.replaceAll("<Invoices>", "");
        String strInvoicesTail = strInvoicesHead.replaceAll("</Invoices>", "");
        String strJsonXMLIvaInvoicesHead = strInvoicesTail.replaceAll("<json__xml__iva.Invoices>", "");
        String strJsonXMLIvaInvoicesTail = strJsonXMLIvaInvoicesHead.replaceAll("</json__xml__iva.Invoices>", "");
        String strLineHead = strJsonXMLIvaInvoicesTail.replaceAll("<json__xml__iva.Line>", "");
        String strLineTail = strLineHead.replaceAll("</json__xml__iva.Line>", "");

        String removeOuterClass = strLineTail.replaceAll("<outer-class reference=\"../..\"/>", "");
        String removeOuterClass2 = removeOuterClass.replaceAll("<outer-class>", "");
        String removeOuterClass3 = removeOuterClass2.replaceAll("</outer-class>", "");
        String removeAuditFile = removeOuterClass3.replaceAll("<AuditFile reference=\"../..\"/>", "");
        String removeLineItensHead = removeAuditFile.replaceAll("<LineItens>", "");
        String removeLineItensTail = removeLineItensHead.replaceAll("</LineItens>", "");
        String removeLineItensTail2 = removeLineItensTail.replaceAll("<LineItens/>", "");

        String removeLineItens2Head = removeLineItensTail2.replaceAll("<json__xml__iva.LineItens>", "");
        String removeLineItens2Tail = removeLineItens2Head.replaceAll("</json__xml__iva.LineItens>", "");
        String removeLineItensReferences = removeLineItens2Tail.replaceAll(" <Line reference=\"../..\"/>", "");
        String removeAmountDebit = removeLineItensReferences.replaceAll("<DebitAmount>0.00</DebitAmount>", "");
        String removeCreditAmount = removeAmountDebit.replaceAll("<CreditAmount>0.00</CreditAmount>", "");
        String removerIvaIonvoice = removeCreditAmount.replaceAll(" <json__xml__iva.Invoice>", "");
        String removerprodutCollectionInit = removerIvaIonvoice.replaceAll("<json__xml__iva.ProdutItem>", "");
        String removerprodutCollectionFinish = removerprodutCollectionInit.replaceAll("</json__xml__iva.ProdutItem>", "");
        String removerprodutItemInit = removerprodutCollectionFinish.replaceAll("<produtItem>", "");
        String removerprodutItemFinish = removerprodutItemInit.replaceAll("</produtItem>", "");
        String removerMasterFilesProdutItemInit = removerprodutItemFinish.replaceAll("<json__xml__iva.DadosFacturaIva_-AuditFile_-MasterFiles_-ProdutItem>", "");
        String removerMasterFilesProdutItemFinish = removerMasterFilesProdutItemInit.replaceAll("</json__xml__iva.DadosFacturaIva_-AuditFile_-MasterFiles_-ProdutItem>", "");
        String removerMasterFilesProdutInit = removerMasterFilesProdutItemFinish.replaceAll("<json__xml__iva.DadosFacturaIva_-AuditFile_-MasterFiles_-ProdutItem_-Product>", "");
        String removerMasterFilesProdutFinish = removerMasterFilesProdutInit.replaceAll("</json__xml__iva.DadosFacturaIva_-AuditFile_-MasterFiles_-ProdutItem_-Product>", "");
        String removerOuterClassReference3Transversais = removerMasterFilesProdutFinish.replaceAll("<outer-class reference=\"../../..\"/>", "");
        String removerOuterClassReference4Transversais = removerOuterClassReference3Transversais.replaceAll("<outer-class reference=\"../../../..\"/>", "");
        String removeCustomerItenInit = removerOuterClassReference4Transversais.replaceAll("<customerIten>", "");
        String removeCustomerItenFinish = removeCustomerItenInit.replaceAll("</customerIten>", "");
        String removeAuditFileMasterFilesCustomerItenInit = removeCustomerItenFinish.replaceAll("<json__xml__iva.DadosFacturaIva_-AuditFile_-MasterFiles_-CustomerIten>", "");
        String removeAuditFileMasterFilesCustomerItenFinish = removeAuditFileMasterFilesCustomerItenInit.replaceAll("</json__xml__iva.DadosFacturaIva_-AuditFile_-MasterFiles_-CustomerIten>", "");

        String removeCustomerIten2Init = removeAuditFileMasterFilesCustomerItenFinish.replaceAll("<json__xml__iva.CustomerIten>", "");
        String removeCustomerIten2Finish = removeCustomerIten2Init.replaceAll("</json__xml__iva.CustomerIten>", "");

        String removeCustomerInit = removeCustomerIten2Finish.replaceAll("<Customer reference=\"../..\"/>", "");
        String removeCustomerFinish = removeCustomerInit.replaceAll("<Customer reference=\"../..\"/>", "");

        String removeReferenced = removeCustomerInit.replaceAll("<Product reference=\"../..\"/>", "");
        String removetaxTableItemsInit = removeReferenced.replaceAll("<taxTableItems>", "");
        String removetaxTableItemsFinish = removetaxTableItemsInit.replaceAll("</taxTableItems>", "");
        String removetaxTableItemsInit2 = removetaxTableItemsFinish.replaceAll("<taxTableItems/>", "");
        String removeTaxTableItemInit = removetaxTableItemsInit2.replaceAll("<json__xml__iva.TaxTableItem>", "");
        String removeTaxTableItemFinish = removeTaxTableItemInit.replaceAll("</json__xml__iva.TaxTableItem>", "");

        String removeTaxTableEntryFinish = removeTaxTableItemFinish.replaceAll("<TaxTableEntry reference=\"../..\"/>", "");
        String removeTaxAmountInit = removeTaxTableEntryFinish.replaceAll("<TaxAmount>0</TaxAmount>", "");

        String removeSupplierItemsFinish = removeTaxAmountInit.replaceAll("</supplierIten>", "");
        String removeSuppliertemsInit2 = removeSupplierItemsFinish.replaceAll("<supplierIten/>", "");
        String removeSuppliertemsInit3 = removeSuppliertemsInit2.replaceAll("<supplierIten>", "");
        String removeSupplierItemInit = removeSuppliertemsInit3.replaceAll("<json__xml__iva.SupplierIten>", "");
        String removeSupplierItemInit4 = removeSupplierItemInit.replaceAll("<Supplier reference=\"../..\"/>", "");
        String removeSupplierItemFinish = removeSupplierItemInit4.replaceAll("</json__xml__iva.SupplierIten>", "");
        String removePaymentItem = removeSupplierItemFinish.replaceAll("<json__xml__iva.Payment>", "");
        String removePaymentItemFinish = removePaymentItem.replaceAll("</json__xml__iva.Payment>", "");
        String removeLineItensPayment = removePaymentItemFinish.replaceAll("<json__xml__iva.LineItensPayment>", "");
        String removeLineItensPaymentFinish = removeLineItensPayment.replaceAll("</json__xml__iva.LineItensPayment>", "");
        String removeLineItensWithholding = removeLineItensPaymentFinish.replaceAll("<json__xml__iva.LineItensWithholding_-WithholdingTax>", "");
        String removeLineItensWithholdingFinish = removeLineItensWithholding.replaceAll("</json__xml__iva.LineItensWithholding_-WithholdingTax>", "");
        String removeWithholdingTax = removeLineItensWithholdingFinish.replaceAll("<WithholdingTax/>", "");
        String removePayments = removeWithholdingTax.replaceAll("<json__xml__iva.Payments>", "");
        String removePaymentsfinish = removePayments.replaceAll("</json__xml__iva.Payments>", "");
        String removeReferencesfinish = removePaymentsfinish.replaceAll("<References/>", "");
        //String removeReferencesfinish = removePaymentsfinish.replaceAll("<References/>", "");
        String removeJsonReferences = removeReferencesfinish.replaceAll("<json__xml__iva.LineItensReferences_-References>", "");
        String removeJsonReferencesfinish = removeJsonReferences.replaceAll("</json__xml__iva.LineItensReferences_-References>", "");
        
        // Entrou aqui
        String removeComparebleDebit = removeJsonReferencesfinish.replaceAll("<OrderReferences/>", ""); 
        String removeJsonReferences1 = removeComparebleDebit.replaceAll("<json__xml__iva.LineItensOrderReferences_-OrderReferences>", "");
        String removeJsonReferencesfinish2 = removeJsonReferences1.replaceAll("</json__xml__iva.LineItensOrderReferences_-OrderReferences>", "");
       
        String removePaymentsLast = removeJsonReferencesfinish2.replaceAll("<PaymentItem/>", "");
        String removeInvoiceLast = removePaymentsLast.replaceAll("<Invoices/>", "");
        String removePaymentItemN = removeInvoiceLast.replaceAll("<PaymentItem>", "");
        String removePaymentItemNfinish = removePaymentItemN.replaceAll("</PaymentItem>", "");
        String removeTaxExemptionReason = removePaymentItemNfinish.replaceAll("<TaxExemptionReason></TaxExemptionReason>", "");
        String removeTaxExemptionCode = removeTaxExemptionReason.replaceAll("<TaxExemptionCode></TaxExemptionCode>", "");
        String removeStockMovements = removeTaxExemptionCode.replaceAll("<StockMovements>", "");
        String removeStockMovementsFinish = removeStockMovements.replaceAll("</StockMovements>", "");
        String removejson__xml__ivaStockMovements = removeStockMovementsFinish.replaceAll("<json__xml__iva.StockMovements>", "");
        String removejson__xml__ivaStockMovementsFinish = removejson__xml__ivaStockMovements.replaceAll("</json__xml__iva.StockMovements>", "");
        String removejson__xml__ivaStockMovement = removejson__xml__ivaStockMovementsFinish.replaceAll("<json__xml__iva.StockMovement>", "");
        String removeremovejson__xml__ivaStockMovementFinish = removejson__xml__ivaStockMovement.replaceAll("</json__xml__iva.StockMovement>", "");
        String removejson__xml__ivaLineItensStockMovement = removeremovejson__xml__ivaStockMovementFinish.replaceAll("<json__xml__iva.LineItensStockMovement>", "");
        String removejson__xml__ivaLineItensStockMovementFinish = removejson__xml__ivaLineItensStockMovement.replaceAll("</json__xml__iva.LineItensStockMovement>", "");
        String removeStockMovementsVoid = removejson__xml__ivaLineItensStockMovementFinish.replaceAll("<StockMovements/>", "");
        String removejson__xml__ivaWorkDocuments = removeStockMovementsVoid.replaceAll("<json__xml__iva.WorkDocuments>", "");
        String removejson__xml__ivaWorkDocumentsFinish = removejson__xml__ivaWorkDocuments.replaceAll("</json__xml__iva.WorkDocuments>", "");
        String removejson__xml__ivaWorkDocument = removejson__xml__ivaWorkDocumentsFinish.replaceAll("<json__xml__iva.WorkDocument>", "");
        String removejson__xml__ivaWorkDocumentFinish = removejson__xml__ivaWorkDocument.replaceAll("</json__xml__iva.WorkDocument>", "");
        String removejson__xml__ivaLineItensWorkDocument = removejson__xml__ivaWorkDocumentFinish.replaceAll("<json__xml__iva.LineItensWorkDocument>", "");
        String removejson__xml__ivaLineItensWorkDocumentFinish = removejson__xml__ivaLineItensWorkDocument.replaceAll("</json__xml__iva.LineItensWorkDocument>", "");
        String removeWorkDocuments = removejson__xml__ivaLineItensWorkDocumentFinish.replaceAll("<WorkDocuments>", "");
        String removeWorkDocumentsFinish = removeWorkDocuments.replaceAll("</WorkDocuments>", "");
        String removeWorkDocumentsVoid = removeWorkDocumentsFinish.replaceAll("<WorkDocuments/>", "");
        
       // String removeComparebleCredit = removeComparebleDebit.replaceAll("</OrderReferences>", "");

        if (removeReferencesfinish.contains("<References/>")) {
            removeReferencesfinish.replaceAll("<References/>", "");
            removeReferencesfinish.replaceAll("<References>", "");
            removeReferencesfinish.replaceAll("<References>", "");
        }
        if (removeComparebleDebit.contains("<OrderReferences/>")) {
            removeComparebleDebit.replaceAll("<OrderReferences/>", "");
            removeComparebleDebit.replaceAll("<OrderReferences>", "");
            removeComparebleDebit.replaceAll("<OrderReferences>", "");
        }
        //JOptionPane.showMessageDialog(null, removeTaxAmountInit);

        String novaXML = removeWorkDocumentsVoid;

        String vet[] = novaXML.split("\n");

        String output = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        for (String valor : vet) {
            if (!verificarCaracteresVazioString(valor)) {
                output += valor + "\n";
            }
        }
        return output;
    }

    public boolean verificarCaracteresVazioString(String valor) {
        int cont = 0;
        for (int i = 0; i < valor.length(); i++) {
            if (valor.charAt(i) == ' ') {
                cont++;
            }
        }

        if (cont == valor.length()) {
            return true;
        }

        return false;
    }

    public JSONObject getReadJSonFile() {
        JSONObject jsonObject = null;
        try {
            //Cria o parse de tratamento
            JSONParser parser = new JSONParser();
            //Salva no objeto JSONObject o que o parse tratou do arquivo
            jsonObject = (JSONObject) parser.parse(new FileReader("file_2019-04-26_Saft.json"));
        } //Trata as exceptions que podem ser lançadas no decorrer do processo
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return jsonObject;
    }

    public DadosFacturaIva getDadosFacturaIva() {
        return dadosFacturaIva;
    }

    public void setDadosFacturaIva(DadosFacturaIva dadosFacturaIva) {
        this.dadosFacturaIva = dadosFacturaIva;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public String getDataFinal() {
        return dataFinal;
    }

//    public static void main(String[] args) {
//      
//        }
}
