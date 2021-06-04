/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.controller;

import GestaoStock.modelo.TabelaIVa;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author El Router
 */
public class TabelaIvaController {

    ConexaoBancos conexao = new ConexaoBancos();

    public boolean salvarCategoria(TabelaIVa tabelaIVa) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "INSERT INTO tabelaiva(designacao,iva) VALUES(?,?)";
        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setString(1, tabelaIVa.getDesignacao());
            prepara.setDouble(2, tabelaIVa.getIva());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

}
