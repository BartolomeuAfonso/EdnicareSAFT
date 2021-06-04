/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import CLINICA.modelo.ModeloEstatistica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerEstatista {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

      public ControllerEstatista(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    
    public void salvarItensEstatisitca(ModeloEstatistica categoriaServicos) {
        //   conexao.Connectando();
        sql = "INSERT INTO itemestatistica(designacao,codigoServico,particular,advance,unisaude,saudemais,fidelidade,saham,masterplus,angolatelecom,empresa,data,codigoEstatistica)values(?,?,?,?,?,?,?,?,?,?,?,now(),?)";
       // System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, categoriaServicos.getDesignacao());
            ps.setInt(2, categoriaServicos.getCodigoServico());
            ps.setInt(3, categoriaServicos.getParticular());
            ps.setInt(4, categoriaServicos.getAdvance());
            ps.setInt(5, categoriaServicos.getUnisaude());
            ps.setInt(6, categoriaServicos.getSaudemais());
            ps.setInt(7, categoriaServicos.getFidelidade());
            ps.setInt(8, categoriaServicos.getSaham());
            ps.setInt(9, categoriaServicos.getMasterplus());
            ps.setInt(10, categoriaServicos.getAngolatecom());
            ps.setInt(11, categoriaServicos.getEmpresa());
            ps.setInt(12, categoriaServicos.getIdestatistica());
            ps.execute();
            //       JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
//        conexao.DesconectaBanco();
    }

    /// estatisitca
    public int getLastEstatistica() {
        try {
            //     conexao.Connectando();
            sql = "select max(idestatistica) as max from estatistica";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return 0;
    }

    public void salvar(ModeloEstatistica estatistica) {
        // conexao.Connectando();
        sql = "INSERT INTO estatistica(data,codigoPaciente,codigoFactura)values(now(),?,?)";
    //    System.out.println("Consulta:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, estatistica.getCodigoPaciente());
            ps.setString(2, estatistica.getCodigoFactura());
            ps.execute();
            //JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (Exception e) {
            System.out.println("Erro:" + e);
        }
//        conexao.DesconectaBanco();
    }

    public void eliminar(int codigoGuia) {
        //  conexao.Connectando();
        sql = "DELETE FROM itemestatistica WHERE codigoEstatistica=" + codigoGuia;
        System.out.println("Update:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
//        conexao.DesconectaBanco();
    }
}
