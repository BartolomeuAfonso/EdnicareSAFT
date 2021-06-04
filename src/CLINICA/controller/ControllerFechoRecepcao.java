/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.FechoRecepcao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerFechoRecepcao {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerFechoRecepcao(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvar(FechoRecepcao factura) {
        sql = "INSERT INTO fechoconta(valorEcografia,valorLaboratorio,valorRaio,valorInternamento,valorOutros,valorMedico,valorTotal,data)values(?,?,?,?,?,?,?,now())";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setDouble(1, factura.getValorEcografia());
            ps.setDouble(2, factura.getValorLaboratorio());
            ps.setDouble(3, factura.getValorRaiox());
            ps.setDouble(4, factura.getValorInternamento());
            ps.setDouble(5, factura.getValorOutros());
            ps.setDouble(6, factura.getValorMedico());
            ps.setDouble(7, factura.getValorTotal());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Guia realizada com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //    conexao.DesconectaBanco();
    }

    public int getLastFactura() {
        try {
            //conexao.Connectando();
            String sql = "select max(idFechoConta) as idFechoConta from fechoconta";
            System.out.println("Teste:" + sql);
            PreparedStatement prepara = con.prepareStatement(sql);
            ResultSet rs = prepara.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getInt("idFechoConta");
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
