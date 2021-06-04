/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.controller;

import GestaoStock.modelo.Movimentacoes;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author El Router
 */
public class MovimentacoesController {

    ConexaoBancos conexao = new ConexaoBancos();

    public boolean salvarMovimentacoes(Movimentacoes mov) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "INSERT INTO movimentacoes(data,tipo,item,qtdAnteriorCx,qtdAnteriorUn,qtdMovimentadaCx,qtdMovimentadaUn,qtdDisponivelCx,qtdDisponivelUn,codUtilizador,loteProduto,codProduto) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println("Sql:" + inserir);
        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setString(1, mov.getData());
            prepara.setInt(2, mov.getTipo());
            prepara.setString(3, mov.getItem());
            prepara.setInt(4, mov.getQtdAnteriorCX());
            prepara.setInt(5, mov.getQtdAnteriorUN());
            prepara.setInt(6, mov.getQtdMovimentadaCX());
            prepara.setInt(7, mov.getQtdMovimentadaUN());
            prepara.setInt(8, mov.getQtdDisponivelCX());
            prepara.setInt(9, mov.getQtdDisponivelUN());
            prepara.setInt(10, mov.getCodUtilizador());
            prepara.setString(11, mov.getLoteProduto());
            prepara.setInt(12, mov.getCodProduto());
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }

}
