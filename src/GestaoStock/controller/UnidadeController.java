/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.controller;

import GestaoStock.modelo.Unidade;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author El Router
 */
public class UnidadeController {
    
    ConexaoBancos conexao = new ConexaoBancos();
     public boolean salvarUnidade(Unidade unidade) throws NoSuchAlgorithmException, UnsupportedEncodingException {
         conexao.Connectando();
        String inserir = "INSERT INTO unidademedida(descricaoUnidade) VALUES(?)";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setString(1, unidade.getUnidade());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:"+ex);
        }

        return true;
    }
}
