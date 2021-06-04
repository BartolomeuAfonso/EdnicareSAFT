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

/**
 *
 * @author Ba
 */
public class ControllerNeurologia {

    ConexaoBancos conexao = new ConexaoBancos();
    Connection con;
    String sql;
    PreparedStatement ps;
    ResultSet rs;

    public ControllerNeurologia(Connection con) {
        this.con = conexao.ConexaoBD();
    }
}
