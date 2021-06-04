/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.MotivoModelo;
import java.sql.SQLException;

/**
 *
 * @author Ba
 */
public class MotivoController {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public MotivoController(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public MotivoModelo getById(int codigo) {
        sql = "SELECT m.codigoMotivo,m.descricacao  FROM tipotaxa t inner join motivo m on t.codigoMotivo =m.codigo where t.codigoMotivo =" + codigo;
        System.out.println("Motivo:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                MotivoModelo modelo = new MotivoModelo();
                modelo.setDescricao(rs.getString("m.descricacao"));
                modelo.setCodigoMotivo(rs.getString("m.codigoMotivo"));

                return modelo;
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

        return null;
    }

}
