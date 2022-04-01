/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import CLINICA.modelo.CheckModelo;
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
public class ControllerCheckUp {

    ConexaoBancos conexao = new ConexaoBancos();
    Connection con;
    String sql;
    PreparedStatement ps;
    ResultSet rs;

    public ControllerCheckUp(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvar(CheckModelo checkModelo) {

        sql = "INSERT INTO checkup(nome,medico,datacheck,dataFinal,informacao, funcao,codigoPaciente,resultado,codigoMedico)values(?,?,?,?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, checkModelo.getNome());
            ps.setString(2, checkModelo.getNomeMedico());
            ps.setString(3, checkModelo.getDatacheck());
            ps.setString(4, checkModelo.getDatavalidade());
            ps.setString(5, checkModelo.getDescricao());
            ps.setString(6, checkModelo.getFuncao());
            ps.setInt(7, checkModelo.getCodigoPaciente());
            ps.setString(8, checkModelo.getResultado());
            ps.setInt(9, checkModelo.getCodigoMedico());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

    }

    public int getLastCodigo() {
        try {
            String sql = "select max(id) as max from checkup";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getInt("max");
                }
            } catch (SQLException ex) {

                ex.printStackTrace();
            }
            return 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
