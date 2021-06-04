/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.FechoRecepcaoItens;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerFechoRecepcaoItens {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
     Connection con;

    public ControllerFechoRecepcaoItens(Connection con) {
        this.con = conexao.ConexaoBD();
    }


    public void salvar(FechoRecepcaoItens factura) {
      //  conexao.Connectando();
        sql = "INSERT INTO fechocontaitens(codigoConta,codigoMedico,valoritens,descricao)values(?,?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, factura.getCodigoConta());
            ps.setInt(2, factura.getCodigoMedico());
            ps.setDouble(3, factura.getValorItens());
            ps.setDouble(3, factura.getValorTotal());
            ps.setString(4, factura.getDescricao());
            ps.execute();
           // JOptionPane.showMessageDialog(null, "Guia realizada com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
       // conexao.DesconectaBanco();
    }

//    public int getCodigoNumeradorEmpresa(String codigoEmpresa) {
//        conexao.Connectando();
//        sql = "SELECT max(numerador) as max FROM guia g inner join pacientes p on g.codigoCliente =p.idPaciente\n"
//                + "inner join empresaseguros e on e.idSeguros = p.codigoSeguro\n"
//                + "where e.designacao='" + codigoEmpresa + "';";
//        System.out.println("Teste:" + sql);
//        try {
//            ps = conexao.conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                return rs.getInt("max");
//            }
//        } catch (SQLException e) {
//            System.out.println("ERRO:" + e);
//        }
//        return 0;
//    }
}
