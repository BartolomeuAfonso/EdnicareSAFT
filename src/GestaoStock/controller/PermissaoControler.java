/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.controller;

import GestaoStock.modelo.Acesso;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author Eletronicos
 */
public class PermissaoControler {

    ConexaoBancos conexao = new ConexaoBancos();

    public ArrayList<Acesso> getAllByFilter(String query) {
        try {
            String sql = "SELECT * FROM acesso";
            conexao.Connectando();

            ArrayList lista = new ArrayList<>();
            PreparedStatement prepara = conexao.conn.prepareStatement(sql);
            ResultSet rs = prepara.executeQuery();
            try {
                while (rs.next()) {
                    lista.add(
                            new Acesso(rs.getString("descricao")
                            )
                    );
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PermissaoControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
