/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.Notacredito;
import CLINICA.modelo.NotaCreditoItens;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Familia do Fresco
 */
public class MovimentoItemController {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    ControllerUsuario controllerUsuario = new ControllerUsuario(con);

    public MovimentoItemController() {
        this.con = conexao.ConexaoBD();
    }

    public MovimentoItemController(Connection con) {
        this.con = conexao.ConexaoBD();
    }

}
