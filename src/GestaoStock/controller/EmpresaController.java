/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestaoStock.controller;

import GestaoStock.modelo.Empresa;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author El Router
 */
public class EmpresaController {

    ConexaoBancos conexao = new ConexaoBancos();

    public boolean salvarEmpresa(Empresa empresa) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        conexao.Connectando();
        String inserir = "update  empresa set descricao=?,endereco=?,email=?,contacto=?,nif=?,website=?";

        try {
            PreparedStatement prepara = conexao.conn.prepareStatement(inserir);
            prepara.setString(1, empresa.getNome());
            prepara.setString(2, empresa.getEndereco());
            prepara.setString(3, empresa.getEmail());
            prepara.setString(4, empresa.getContacto());
            prepara.setString(5, empresa.getNif());
            prepara.setString(6, empresa.getWebsite());
            
            prepara.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO:" + ex);
        }

        return true;
    }
       public ArrayList<Empresa> dadosEmpresa() throws SQLException {
        conexao.Connectando();
        String sql = "SELECT * FROM empresa";
        ArrayList lista = new ArrayList<>();
        PreparedStatement prepara = conexao.conn.prepareStatement(sql);
        ResultSet rs = prepara.executeQuery();
        int next = 0;
        try {
            while (rs.next()) {
                lista.add(
                        new Empresa(
                                rs.getString("descricao"), 
                                rs.getString("endereco"), 
                                rs.getString("email"), 
                                rs.getString("contacto"), 
                                rs.getString("nif"), 
                                rs.getString("website")
                        )
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }
    
}
