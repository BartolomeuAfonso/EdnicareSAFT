/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.controller;

import GestaoStock.modelo.CategoriaProduto;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author El Router
 */
public class CategoriaController {
    
    ConexaoBancos conexao = new ConexaoBancos();
     public boolean salvarCategoria(CategoriaProduto categoria) throws NoSuchAlgorithmException, UnsupportedEncodingException {
         conexao.Connectando();
        String inserir = "INSERT INTO categoriaservico(designacao) VALUES(?)";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setString(1, categoria.getCategoria());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:"+ex);
        }

        return true;
    }
     public boolean salvarProvincia(CategoriaProduto categoria) throws NoSuchAlgorithmException, UnsupportedEncodingException {
         conexao.Connectando();
        String inserir = "INSERT INTO naturalidades(designacao) VALUES(?)";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setString(1, categoria.getCategoria());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:"+ex);
        }

        return true;
    }
   
   
}
