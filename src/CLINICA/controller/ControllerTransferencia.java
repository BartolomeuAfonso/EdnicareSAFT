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
import CLINICA.modelo.TransferenciaMedica;
import java.sql.Connection;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerTransferencia {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerTransferencia(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvarTransferencia(TransferenciaMedica transferenciaMedica) {
        //  conexao.Connectando();
        sql = "INSERT INTO guiatransferencia(codigoMedico,codigoPaciente,motivo,tratamento,data,codigoHospital)values(?,?,?,?,now(),?)";
        System.out.println("Guia de Transferência:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, transferenciaMedica.getCodigoMedico());
            ps.setInt(2, transferenciaMedica.getCodigoPaciente());
            ps.setString(3, transferenciaMedica.getMotivo());
            ps.setString(4, transferenciaMedica.getTratamento());
            ps.setInt(5, transferenciaMedica.getCodigoHospital());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        // conexao.DesconectaBanco();
    }

    public int getLastFactura() {
        try {
            //   conexao.Connectando();
            sql = "select max(idGuia) as max from guiatransferencia";
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
}
