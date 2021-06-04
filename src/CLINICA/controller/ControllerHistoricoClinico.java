/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import CLINICA.modelo.HistorialClinico;
import CLINICA.modelo.HistoricaInterno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerHistoricoClinico {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    HistoricaInterno historicaInterno;
    Connection con;

    public ControllerHistoricoClinico(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvar(HistorialClinico historialClinico) {
        //conexao.Connectando();
        sql = "INSERT INTO historicoclinico(codigoConsulta,queixaPresente,historiaDoencaActual,exameFisico,hipoteseDiagnostico,codigoCid,codigoMedico,dataAtendimento,codigoUtente,codigoInterno,apf,cabeca,pescoco,torax,abdomen,orgaogenital,membros)values(?,?,?,?,?,?,?,now(),?,?,?,?,?,?,?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, historialClinico.getCodigoconsulta());
            ps.setString(2, historialClinico.getQueixaPrincipal());
            ps.setString(3, historialClinico.getHistorialDoenca());
            ps.setString(4, historialClinico.getExameFisico());
            ps.setString(5, historialClinico.getHipoteseDiagnostico());
            ps.setInt(6, historialClinico.getCodigoCids());
            ps.setInt(7, historialClinico.getCodigoMedico());
            // ps.setDate(8, (Date) historialClinico.getDataAtendimento());
            ps.setInt(8, historialClinico.getCodigoPaciente());
            ps.setInt(9, historialClinico.getCodigoDoenca());
            ps.setString(10, historialClinico.getApf());
            ps.setString(11, historialClinico.getCabeca());
            ps.setString(12, historialClinico.getPescoco());
            ps.setString(13, historialClinico.getTorax());
            ps.setString(14, historialClinico.getAbdomem());
            ps.setString(15, historialClinico.getOrgaogenital());
            ps.setString(16, historialClinico.getMembroinferior());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        // conexao.DesconectaBanco();
    }

    public void editar(HistorialClinico historialClinico, int codigoClinico) {
        //conexao.Connectando();
        sql = "UPDATE historicoclinico SET queixaPresente=?,historiaDoencaActual=?,exameFisico=?,hipoteseDiagnostico=?,apf=?, cabeca=?,pescoco=?,torax=?,abdomen=?,orgaogenital=?,membros=? WHERE idHistoricoClinico=" + codigoClinico;
        // cabeca =?,pescoco=?,torax=?,abdomen=?,orgaogenital=?,membros=?
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, historialClinico.getQueixaPrincipal());
            ps.setString(2, historialClinico.getHistorialDoenca());
            ps.setString(3, historialClinico.getExameFisico());
            ps.setString(4, historialClinico.getHipoteseDiagnostico());
            ps.setString(5, historialClinico.getApf());
            ps.setString(6, historialClinico.getCabeca());
            ps.setString(7, historialClinico.getPescoco());
            ps.setString(8, historialClinico.getTorax());
            ps.setString(9, historialClinico.getAbdomem());
            ps.setString(10, historialClinico.getOrgaogenital());
            ps.setString(11, historialClinico.getMembroinferior());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados Alterado com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //   conexao.DesconectaBanco();
    }

    public int getLastCodigo() {
        // conexao.Connectando();
        sql = "select max(idHistoricoClinico) as max from historicoclinico";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("max");
            }
        } catch (SQLException ex) {
            //    ex.printStackTrace();
            System.out.println("Erro:" + ex);
        }
        return 0;
    }

    public ArrayList<String> getNomeHistorico() {
        //  conexao.Connectando();
        sql = "SELECT DISTINCT f.nomeClientes as designacao  FROM factura f inner join factura_itens f1 on f.idfactura = f1.codigoFactura\n"
                + "where f1.codigoCategoria =2 ";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("designacao"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public boolean getLastCodigoByTriagem(int triagem) {
        // conexao.Connectando();
        sql = "select max(idHistoricoClinico) as max from historicoclinico where  codigoConsulta= " + triagem;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            //     ex.printStackTrace();
            System.out.println("Erro:" + ex);
        }
        return false;
    }

    public int getLastCodigobyHystorico(int triagem, int codigoPaciente) {
        // conexao.Connectando();
        sql = "select max(idHistoricoClinico) as max from historicoclinico where codigoConsulta= " + triagem + " AND codigoUtente=" + codigoPaciente;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("max");
            }
        } catch (SQLException ex) {
            // ex.printStackTrace();
            System.out.println("Erro:" + ex);
        }
        return 0;
    }

    public HistorialClinico getDadosClinico(int codigoClinico) {
        // conexao.Connectando();
        sql = "SELECT * FROM historicoclinico h where idHistoricoClinico=" + codigoClinico;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            HistorialClinico historialClinico;
            if (rs.next()) {
                historialClinico = new HistorialClinico(rs.getInt("idHistoricoClinico"), rs.getInt("codigoConsulta"), rs.getString("queixaPresente"), rs.getString("historiaDoencaActual"), rs.getString("exameFisico"), rs.getString("hipoteseDiagnostico"), rs.getString("apf"));
                return historialClinico;

            }
        } catch (SQLException ex) {
            //    ex.printStackTrace();
            System.out.println("Erro:" + ex);
        }
        return null;
    }

    public HistorialClinico getDadosClinicoDados(int codigoClinico) {
        // conexao.Connectando();
        sql = "SELECT * FROM historicoclinico h where idHistoricoClinico=" + codigoClinico;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            HistorialClinico historialClinico;
            if (rs.next()) {
                historialClinico = new HistorialClinico(rs.getString("queixaPresente"), rs.getString("historiaDoencaActual"), rs.getString("exameFisico"), rs.getString("hipoteseDiagnostico"), rs.getString("apf"), rs.getString("cabeca"), rs.getString("pescoco"), rs.getString("torax"), rs.getString("abdomen"), rs.getString("orgaogenital"), rs.getString("membros"));
                return historialClinico;

            }
        } catch (SQLException ex) {
            //    ex.printStackTrace();
            System.out.println("Erro:" + ex);
        }
        return null;
    }

    // Doenças crónicas dos pacientes
    public HistoricaInterno getDoencasCronicas(int codigoClinico) {
        // conexao.ConexaoBD();
        sql = "SELECT * FROM historicointerno h where codigoPaciente=" + codigoClinico;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            HistorialClinico historialClinico;
            if (rs.next()) {
                historicaInterno = new HistoricaInterno(rs.getInt("idInterno"), rs.getInt("codigoPaciente"), rs.getString("ultimoTratamento"), rs.getString("alergias"), rs.getString("doencasCronicas"));
                return historicaInterno;

            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return null;
    }

    public void salvarDoencas(HistoricaInterno historicaInterno) {
        // conexao.Connectando();
        sql = "INSERT INTO historicointerno(codigoPaciente,alergias,doencasCronicas,dataCadastro,ultimoTratamento)values(?,?,?,now(),?)";
        System.out.println("sql:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, historicaInterno.getCodigoPaciente());
            ps.setString(2, historicaInterno.getAlergias());
            ps.setString(3, historicaInterno.getDoencas());
            ps.setString(4, historicaInterno.getSetUltimoTratamento());
            ps.execute();
            // JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //  conexao.DesconectaBanco();
    }

    public boolean existeHistoriaDoenca(int codigoPessoa) {
        //conexao.Connectando();
        sql = "SELECT codigoPaciente FROM historicointerno WHERE codigoPaciente = " + codigoPessoa;
        System.out.println("Se existe historical Clínico de Vida:" + codigoPessoa);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            return true;
        }

        return false;
    }

    public int getCodigoDoenca(int codigo) {
        //conexao.Connectando();
        sql = "select idInterno as max from historicointerno where codigoPaciente= " + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("max");
            }
        } catch (SQLException ex) {
        }
        return 0;
    }

    public String getUltimadata(int codigo) {
        //conexao.Connectando();
        sql = "SELECT max(date(h.dataAtendimento)) as data from historicoclinico h inner join triagem t on h.codigoConsulta=t.idtriagem\n"
                + "inner join pacientes p on t.codigoPaciente = p.idpaciente where p.idpaciente=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("data");
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public String getMedicoHistorico(String data) {
        //conexao.Connectando();
        sql = "SELECT m.nomecompleto as nome from historicoclinico h inner join medicos m on h.codigoMedico = m.idMedico\n"
                + "where date(h.dataAtendimento)='" + data + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nome");
            }
        } catch (SQLException ex) {
            //ex.printStackTrace();
            System.out.println("Erro:" + ex);
        }
        return null;
    }

}
