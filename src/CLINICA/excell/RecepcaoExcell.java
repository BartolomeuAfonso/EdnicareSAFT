/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.excell;

import CLINICA.controller.ControllerEmpresa;
import CLINICA.estatistica.EstatisticaParticular;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import sf.ce.conexao.ConexaoBancos;
import sf.ce.utilizacoes.Data;

/**
 *
 * @author Probook
 */
public class RecepcaoExcell {

    EstatisticaParticular estatisticaParticular = new EstatisticaParticular();
    private Workbook wb;
    private Sheet st;
    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Data d = new Data();
    ControllerEmpresa controllerEmpresa = new ControllerEmpresa(con);

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

    public void estatisticaGeralRecepcao(String data1, String data2) {
        URL caminho = this.getClass().getResource("/Estatistica/");
        String empresa = "Movimento Geral";
        try {
            WritableWorkbook planilha = Workbook.createWorkbook(new File(caminho + empresa + "-" + data1 + ".xls"));
            // CRIAR ABA
            WritableSheet aba = planilha.createSheet("Estatistica Geral", 0);
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
            int iRows = 8;
            String cabecalho[] = new String[25];
            cabecalho[0] = "N/0";
            cabecalho[1] = "DESIGNAÇÃO";
            cabecalho[1] = "CRIANÇA";
            cabecalho[2] = "ADULTO";
            cabecalho[3] = "A.T";
            cabecalho[4] = "ANST";
            cabecalho[5] = "SAHAM";
            cabecalho[6] = "UNISAÚDE";
            cabecalho[7] = "SAÚDE +";
            cabecalho[8] = "FIDELIDADE";
            cabecalho[9] = "FIDELIDADE";
            cabecalho[10] = "TOTAL GERAL";
            cabecalho[11] = "CRIANÇAS";
            cabecalho[12] = "ADULTO";
            cabecalho[13] = "TOTAL PACIENTE";
            cabecalho[14] = "%PARTICULAR";
            cabecalho[15] = "%ENSA";
            cabecalho[16] = "%A.T";
            cabecalho[17] = "%ANST";
            cabecalho[18] = "%SAHAM";
            cabecalho[19] = "%UNISAÚDE";
            cabecalho[20] = "%SAÚDE +";
            cabecalho[21] = "%FIDELIDADE";
            cabecalho[22] = "%FIDELIDADE";
            cabecalho[23] = "%TOTAL GERAL";
            for (int i = 0; i < cabecalho.length; i++) {
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
                iRows++;
            }

        } catch (Exception e) {
        }
    }

}
