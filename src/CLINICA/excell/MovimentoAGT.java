/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.excell;

import CLINICA.controller.ControllerEspecalidadeMedico;
import CLINICA.controller.ControllerFacturaItens;
import CLINICA.controller.ControllerMedico;
import CLINICA.controller.ControllerSeguradora;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import sf.ce.conexao.ConexaoBancos;
import sf.ce.utilizacoes.Data;

/**
 *
 * @author Probook
 */
public class MovimentoAGT {

    private Workbook wb;
    private Sheet st;
    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Data d = new Data();
    Connection con;
    String obterCaminho;
    ControllerMedico controllerMedico = new ControllerMedico(con);
    ControllerEspecalidadeMedico controllerEspecialidade = new ControllerEspecalidadeMedico(con);
    ControllerSeguradora controllerSeguradora = new ControllerSeguradora(con);
    ControllerFacturaItens controllerFacturaItens = new ControllerFacturaItens(con);

    public void iniciar() {
        try {

            JFileChooser fopen = new JFileChooser();
            fopen.showOpenDialog(null);
            wb = Workbook.getWorkbook(fopen.getSelectedFile());

            // se não usar o JFileChooser poderia abri a planilha
            //passando o nome do arquivo diretamente ex:
            // wb= Workbook.getWorkbook("Teste.xls");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void finalizar() {
        wb.close();
    }

    public void MovimentoSintetico(String data1, String data2) {
        URL caminho = this.getClass().getResource("/Movimentos/");
        String empresa = "Movimento Geral";
        try {

            double valorNumerario = controllerFacturaItens.getValorTotalNumerario(data1, data2);
            double valorMulticaixa = controllerFacturaItens.getValorTotalMulticaixa(data1, data2);

            WritableWorkbook planilha = Workbook.createWorkbook(new File(caminho + empresa + "-" + data1 + ".xls"));
            // CRIAR ABA
            WritableSheet aba = planilha.createSheet("Movimento", 0);
            // WritableSheet s7 = planilha.createSheet("Images", 1);
            Colour bckcolor = Colour.WHITE;
            WritableCellFormat cellFormat = new WritableCellFormat();
            cellFormat.setBackground(bckcolor);

            // Cor e tipo de fonte
            WritableFont fonte = new WritableFont(WritableFont.TIMES);
            fonte.setColour(Colour.BLACK);
            fonte.setItalic(true);
            //fonte.setScriptStyle(ScriptStyle.SUPERSCRIPT); //Para sublinhar o texto
            cellFormat.setFont(fonte);

            cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);

            // Label imagLabel = new Label(0, 0, "IMAGEM");
            String valor = String.format("%1$,.2f", valorNumerario);
            String valor1 = String.format("%1$,.2f", valorMulticaixa);
            String total = String.format("%1$,.2f", valorMulticaixa + valorNumerario);
            aba.addCell(new Label(3, 4, "Referente: " + data1 + " à " + data2, cellFormat));
            aba.addCell(new Label(1, 8, "Movimento Caixa", cellFormat));
            aba.addCell(new Label(1, 10, "1", cellFormat));
            aba.addCell(new Label(1, 11, "2", cellFormat));
            aba.addCell(new Label(2, 10, "Numerário", cellFormat));
            aba.addCell(new Label(2, 11, "Multicaixa", cellFormat));
            aba.addCell(new Label(2, 12, "Total Geral", cellFormat));
            aba.addCell(new Label(3, 10, valor, cellFormat));
            aba.addCell(new Label(3, 11, valor1, cellFormat));
            aba.addCell(new Label(3, 12, String.valueOf(total), cellFormat));

            planilha.write();
            planilha.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void folhaHonorario(String data1, String data2, int codigoMedico, double preco, double irt, double imposto, double totalImposto) {
        try {
            conexao.Connectando();
            String nomeMedico = controllerMedico.getNomeMedico(codigoMedico).toString();
            String especialidade = controllerMedico.getEspecialidade(codigoMedico);
            String sql = "SELECT\n"
                    + "     marcacaoconsulta.`dataAtendimento` AS data,\n"
                    + "     UPPER(pacientes.`nomeCompleto`) AS nome,\n"
                    + "     servicos.`designacao` AS servicos,\n"
                    + "     empresaseguros.`designacao` AS empresa,\n"
                    + "     marcacaoconsulta.`preco` AS preco,\n"
                    + "     medicos.`nomeCompleto` AS medico,\n"
                    + "     medicos.`numeroOrdem` AS nOrdem,\n"
                    + "     medicos.`idMedico` AS medicos_idMedico,\n"
                    + "     especialidade_medico.`designacao` AS especialidade\n"
                    + "FROM\n"
                    + "     `pacientes` pacientes INNER JOIN `marcacaoconsulta` marcacaoconsulta ON pacientes.`idPaciente` = marcacaoconsulta.`codigoPaciente`\n"
                    + "     INNER JOIN `servicos` servicos ON marcacaoconsulta.`codigoServico` = servicos.`idServico`\n"
                    + "     INNER JOIN `medicos` medicos ON marcacaoconsulta.`codigoMedico` = medicos.`idMedico`\n"
                    + "     INNER JOIN `especialidade_medico` especialidade_medico ON medicos.`codigoEspecialidade` = especialidade_medico.`idEspecialidade`\n"
                    + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                    + "WHERE\n"
                    + "     marcacaoconsulta.`dataAtendimento` between '" + data1 + "' and '" + data2 + "'\n"
                    + " AND medicos.`idMedico` =" + codigoMedico;
            System.out.println("Sql:" + sql);
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            URL caminho = this.getClass().getResource("/Honorario/");
            WritableWorkbook planilha = Workbook.createWorkbook(new File(caminho + nomeMedico + "-" + data1 + ".xls"));
            WritableSheet aba = planilha.createSheet("FOLHA DE HONORÁRIO", 0);
            // WritableSheet s7 = planilha.createSheet("Images", 1);

            // Cabeçalhos
            String cabecalho[] = new String[11];

            //cabecalho[0] = "Entidade";
            cabecalho[0] = "Nº";
            cabecalho[1] = "Data";
            cabecalho[2] = "Nome";
            cabecalho[3] = "Serviço";
            cabecalho[4] = "Entidade";
            cabecalho[5] = "AKZ";
            cabecalho[6] = "Valor";
            cabecalho[7] = "IRT";
            cabecalho[8] = "I.Selo";
            cabecalho[9] = "T.Desconto";
            cabecalho[10] = "Valor Liquido";

            // Cor de fundo das celular
            Colour bckcolor = Colour.WHITE;

            WritableCellFormat cellFormat = new WritableCellFormat();
            WritableCellFormat cellFormat1 = new WritableCellFormat();
            cellFormat.setBackground(bckcolor);
            WritableCellFormat floatFormat = new WritableCellFormat(NumberFormats.FLOAT);
            floatFormat.setBackground(bckcolor);
            // Cor e tipo de fonte
            WritableFont fonte = new WritableFont(WritableFont.TIMES);
            fonte.setColour(Colour.BLACK);
            fonte.setItalic(true);
            //fonte.setScriptStyle(ScriptStyle.SUPERSCRIPT); //Para sublinhar o texto
            cellFormat.setFont(fonte);
            cellFormat1.setFont(fonte);
            cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);
            cellFormat1.setBorder(jxl.format.Border.NONE, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);
            aba.addCell(new Label(0, 11, "MEDICO:" + nomeMedico, cellFormat1));
            aba.addCell(new Label(0, 12, "ESPECIALIDADE: " + especialidade, cellFormat1));
            int iRows = 14;
            double totalAkz = 0;
            double totalPercentagem = 0;
            double totatIrt = 0;
            double totalIp = 0;
            double totalIPGeral = 0;
            double totalGeral = 0;
            for (int i = 0; i < cabecalho.length; i++) {
                //9- Posição onde começa o documento
                // Esta linha de código criar o cabeçalho do documento
                Label label = new Label(i, 13, cabecalho[i]);
                aba.addCell(label);
                WritableCell cell = aba.getWritableCell(i, 13);
                cell.setCellFormat(cellFormat);
            }
            int cont = 1;
            while ((rs != null) && (rs.next())) {
                // Preencher o excell com dados que vem da base de dados. 
                String dados = String.valueOf(cont);
                cont++;
                aba.addCell(new Label(0, iRows, dados, cellFormat));
                aba.addCell(new Label(1, iRows, rs.getString("data"), cellFormat));
                aba.addCell(new Label(2, iRows, rs.getString("nome"), cellFormat));
                aba.addCell(new Label(3, iRows, rs.getString("servicos"), cellFormat));
                aba.addCell(new Label(4, iRows, rs.getString("empresa"), cellFormat));

                aba.addCell(new Label(5, iRows, String.format("%1$,.2f", Double.parseDouble(rs.getString("preco"))), cellFormat));
                aba.addCell(new Label(6, iRows, String.format("%1$,.2f", Double.parseDouble(rs.getString("preco")) * preco), cellFormat));
                aba.addCell(new Label(7, iRows, String.format("%1$,.2f", Double.parseDouble(rs.getString("preco")) * preco * 0.105), cellFormat));
                aba.addCell(new Label(8, iRows, String.format("%1$,.2f", Double.parseDouble(rs.getString("preco")) * preco * 0.01), cellFormat));
                aba.addCell(new Label(9, iRows, String.format("%1$,.2f", Double.parseDouble(rs.getString("preco")) * preco * 0.115), cellFormat));
                aba.addCell(new Label(10, iRows, String.format("%1$,.2f", Double.parseDouble(rs.getString("preco")) * preco - Double.parseDouble(rs.getString("preco")) * preco * 0.115), cellFormat));
                totalAkz = totalAkz + Double.parseDouble(rs.getString("preco"));
                totalPercentagem = totalPercentagem + Double.parseDouble(rs.getString("preco")) * preco;
                totatIrt = totatIrt + Double.parseDouble(rs.getString("preco")) * preco * 0.105;
                totalImposto = totalImposto + Double.parseDouble(rs.getString("preco")) * preco * 0.01;
                totalIPGeral = totalIPGeral + Double.parseDouble(rs.getString("preco")) * preco * 0.115;
                totalGeral = totalGeral + Double.parseDouble(rs.getString("preco")) * preco - Double.parseDouble(rs.getString("preco")) * preco * 0.115;
                iRows++;
            }
            aba.addCell(new Label(0, iRows, "", cellFormat));
            aba.addCell(new Label(1, iRows, "", cellFormat));
            aba.addCell(new Label(2, iRows, "Total Geral", cellFormat));
            aba.addCell(new Label(3, iRows, "", cellFormat));
            aba.addCell(new Label(4, iRows, "", cellFormat));
            aba.addCell(new Label(5, iRows, String.format("%1$,.2f", totalAkz), cellFormat));
            aba.addCell(new Label(6, iRows, String.format("%1$,.2f", totalPercentagem), cellFormat));
            aba.addCell(new Label(7, iRows, String.format("%1$,.2f", totatIrt), cellFormat));
            aba.addCell(new Label(8, iRows, String.format("%1$,.2f", totalImposto), cellFormat));
            aba.addCell(new Label(9, iRows, String.format("%1$,.2f", totalIPGeral), cellFormat));
            aba.addCell(new Label(10, iRows, String.format("%1$,.2f", totalGeral), cellFormat));
            aba.addCell(new Label(0, iRows + 1, "", cellFormat1));
            aba.addCell(new Label(0, iRows + 2, "", cellFormat1));
            planilha.write();
            planilha.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void lrelatorioDetalhado(Date data1, Date data2) {
        con = new ConexaoBancos().ConexaoBD();
        HashMap hashMap = new HashMap();
        hashMap.put("DATA1", data1);
        hashMap.put("DATA2", data2);
        String relatorio = "Relatorios/.jasper";
        System.out.println("Relatorio:" + relatorio);
        File file = new File(relatorio).getAbsoluteFile();
        String obterCaminho = file.getAbsolutePath();

        try {
            JasperFillManager.fillReport(obterCaminho, hashMap, con);

            JasperPrint jasperPrint = JasperFillManager.fillReport(obterCaminho, hashMap, con);

            if (jasperPrint.getPages().size() >= 1) {
                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                //    jasperViewer.setTitle("Relatório de Vendas Detalhado");
                jasperViewer.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nao Existem Venda neste Périodo!...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO TENTAR MOSTRAR AS ENTRADAS !..." + ex);
        }

    }

}
