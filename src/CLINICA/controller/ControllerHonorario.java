/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import CLINICA.modelo.CategoriaServicos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.FolhaHonorario;
import java.sql.Connection;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerHonorario {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
   
    
    Connection con;

    public ControllerHonorario(Connection con) {
        this.con = conexao.ConexaoBD();
    }


    public void Inserir(FolhaHonorario folhaHonorario) {
        //conexao.Connectando();
        sql = "INSERT INTO folhahonorario (designacao,codigoEmpresa,codigoServico,percetagem,IRT,impostoSelo,preco) value(?,?,?,?,?,?,?)";
        System.out.println("Consulta:"+sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, folhaHonorario.getDesignacao());
            ps.setInt(2, folhaHonorario.getCodigoEmpresa());
            ps.setInt(3, folhaHonorario.getCodigoServico());
            ps.setDouble(4, folhaHonorario.getPercentagem());
            ps.setDouble(5, folhaHonorario.getIRT());
            ps.setDouble(6, folhaHonorario.getImpostoSelo());
            ps.setDouble(7, folhaHonorario.getPrecoNormal());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Erro:" + e);
        }
      //  conexao.DesconectaBanco();
    }

    public ArrayList<String> getNomeCids() {
        //conexao.Connectando();
        sql = "SELECT * FROM cids s";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("s.designacao"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

}
