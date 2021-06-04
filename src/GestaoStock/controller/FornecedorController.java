/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.controller;

import GestaoStock.modelo.Fornecedor;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author El Router
 */
public class FornecedorController {

    ConexaoBancos conexao = new ConexaoBancos();

    public boolean salvarFornecedor(Fornecedor fornecedor) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "INSERT INTO fornecedor("
                + "nomeFornecedor,endereco,email,telefone,pais,nif,status) VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setString(1, fornecedor.getNome());
            prepara.setString(2, fornecedor.getEndereco());
            prepara.setString(3, fornecedor.getEmail());
            prepara.setString(4, fornecedor.getContacto());
            prepara.setString(5, fornecedor.getPais());
            prepara.setString(6, fornecedor.getNif());
            prepara.setInt(7, 1);//Situação ATIVA
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }
    public boolean alterarFornecedor(Fornecedor fornecedor,int id) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE  fornecedor set nomeFornecedor=?,endereco=?,email=?,telefone=?,pais=?,nif=? WHERE codFornecedor=?";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setString(1, fornecedor.getNome());
            prepara.setString(2, fornecedor.getEndereco());
            prepara.setString(3, fornecedor.getEmail());
            prepara.setString(4, fornecedor.getContacto());
            prepara.setString(5, fornecedor.getPais());
            prepara.setString(6, fornecedor.getNif());
            prepara.setLong(7, id);
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }
    public boolean eliminarFornecedor(int id) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE  fornecedor set status=? WHERE codFornecedor=?";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setInt(1, 2);//Mudar a situação para 2(DESATIVADO).
            prepara.setLong(2, id);
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }
    
    
}
