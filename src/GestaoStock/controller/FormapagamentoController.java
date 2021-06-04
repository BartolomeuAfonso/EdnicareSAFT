/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.controller;

import GestaoStock.modelo.FormaPagamento;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author El Router
 */
public class FormapagamentoController {

    ConexaoBancos conexao = new ConexaoBancos();

    public boolean salvarCategoria(FormaPagamento forma) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "INSERT INTO formapagamento(designacao) VALUES(?)";
        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setString(1, forma.getDescicao());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

    public int getCodigoFormaPagamento(String designacao) {
        try {
            conexao.Connectando();
            String sql = "SELECT codForma from formapagamento where designacao ='" + designacao + "'";
            System.out.println("Teste:" + sql);
            PreparedStatement ps = conexao.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("codForma");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

}
