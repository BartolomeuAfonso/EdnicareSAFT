/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.Especialidade;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerEspecalidadeMedico {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerEspecalidadeMedico(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvarEspecialidade(Especialidade especialidade) {
        sql = "INSERT INTO especialidade_medico(designacao,codigoStatus)values(?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, especialidade.getDesignacao());
            ps.setInt(2, especialidade.getIdGrupoEspecialidade());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
     //   conexao.DesconectaBanco();
    }

    public ArrayList<String> getNomeEspecialide() {
    //    conexao.Connectando();
        sql = "SELECT UPPER(designacao) AS designacao FROM especialidade_medico s where codigoStatus=1";
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

    public int getCodigoEspecialidade(String designacao) {
       // conexao.Connectando();
        sql = "SELECT idEspecialidade from especialidade_medico where designacao ='" + designacao + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idEspecialidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public void eliminarEspecialidade(int codigo) {
      //  conexao.Connectando();
        sql = "DELETE from especialidade_medico where idEspecialidade =" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }

    }
}
