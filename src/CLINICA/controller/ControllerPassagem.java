/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import CLINICA.modelo.CategoriaServicos;
import CLINICA.modelo.Passagem;
import java.sql.Connection;
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
public class ControllerPassagem {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerPassagem(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvar(Passagem passagem) {
        conexao.Connectando();
        sql = "INSERT INTO entradadocumento(descricao,codigoPaciente,dataEntrada)values(?,?,now())";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, passagem.getDesignacao());
            ps.setInt(2, passagem.getCodigoUtilizador());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Entrada com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
      //  conexao.DesconectaBanco();
    }

}
