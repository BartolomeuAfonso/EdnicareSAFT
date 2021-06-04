/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Financas.Modelo.FecharCaixa;

/**
 *
 * @author El Router
 */
public class ctr_FecharCaixa {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    public ctr_FecharCaixa(Connection con) {
        this.con = con;
    }

    public boolean save(FecharCaixa model) {
        sql = "INSERT INTO fecharCaixa(data, hora, caixa,saldo,valor_final,quebra,caixa_tranferir,observacao,utilizador)VALUES (?,?, ?, ?, ?, ?, ?, ?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setDate(1, (Date)model.getData());
            ps.setString(2, model.getHora());
            ps.setInt(3, model.getCaixa());
            ps.setDouble(4, model.getSaldoCaixa());
            ps.setDouble(5, model.getValorFecho());
            ps.setDouble(6,model.getQuebra());
            ps.setString(7,model.getCaixaTransferir());
            ps.setString(8,model.getObservacao());
            ps.setInt(9,model.getUtilizador());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    
}
