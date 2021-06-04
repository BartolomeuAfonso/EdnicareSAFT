/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.controller;

import GestaoStock.modelo.Saida;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author El Router
 */
public class SaidaController {

    ConexaoBancos conexao = new ConexaoBancos();

    public boolean salvarSaida(Saida saida) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "INSERT INTO saidas(dataSaida,descricao,codUser) VALUES(?,?,?)";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setString(1, saida.getDataSaida());
            prepara.setString(2, saida.getDescricao());
            prepara.setInt(3, saida.getCodFarmanceutico());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

    public boolean atualizarSaida(Saida saida) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE  saidas set dataSaida = ?,descricao = ?, WHERE codSaida=?";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setString(1, saida.getDataSaida());
            prepara.setString(2, saida.getDescricao());
            prepara.setInt(5, saida.getIdSaida());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }
    public boolean eliminarSaida(Saida saida) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "UPDATE  saidas set status = ? WHERE codSaida=?";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setInt(1, 3);
            prepara.setInt(2, saida.getIdSaida());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

   

}
