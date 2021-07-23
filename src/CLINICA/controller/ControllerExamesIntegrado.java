/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.ExamesIntegrado;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Probook
 */
public class ControllerExamesIntegrado {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerExamesIntegrado(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void create(ExamesIntegrado e) {
      
        sql = "insert into examesintegrado(designacao,referencia,unidade,resultado,codigoStatus,codigoServico,codigoCategoria)values(?,?,?,?,?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, e.getDesignacao());
            ps.setString(2, e.getReferencia());
            ps.setString(3, e.getUnidade());
            ps.setString(4, e.getResultado());
            ps.setInt(5, e.getCodigoStatus());
            ps.setInt(6, e.getCodigoServico());
            ps.setInt(7, e.getCodigoCategoria());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
        }
        //    conexao.DesconectaBanco();

    }

}
