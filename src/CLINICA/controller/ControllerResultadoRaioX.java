/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import CLINICA.modelo.ResultadoRaioX;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerResultadoRaioX {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;

    Connection con;

    public ControllerResultadoRaioX(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void insertTo(ResultadoRaioX resultadoRaioX) {
        //  conexao.Connectando();
        sql = "INSERT INTO resultadoraixo(descricao,estado,imagem,som,video,raioX,nomepaciente,codigoServico,codigoPaciente,codigoUser,data)VALUES (?,?,?,?,?,?,?,?,?,?,now())";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, resultadoRaioX.getDescricao());
            ps.setString(2, resultadoRaioX.getEstado());
            ps.setString(3, resultadoRaioX.getImagem());
            ps.setString(4, resultadoRaioX.getSom());
            ps.setString(5, resultadoRaioX.getVideo());
            ps.setString(6, resultadoRaioX.getRaioX());
            ps.setString(7, resultadoRaioX.getNomePaciente());
            ps.setInt(8, resultadoRaioX.getCodigoServico());
            ps.setInt(9, resultadoRaioX.getCodigoPaciente());
            ps.setInt(10, resultadoRaioX.getCodigoUser());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

    }

    public void insertToEcografia(ResultadoRaioX resultadoRaioX) {
        //   conexao.Connectando();
        sql = "INSERT INTO resultadoecografia(descricao,estado,imagem,som,video,nomepaciente,codigoServico,codigoPaciente,codigoUser,data,ovarios,conclusao)VALUES (?,?,?,?,?,?,?,?,?,now(),?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, resultadoRaioX.getDescricao());
            ps.setString(2, resultadoRaioX.getEstado());
            ps.setString(3, resultadoRaioX.getImagem());
            ps.setString(4, resultadoRaioX.getSom());
            ps.setString(5, resultadoRaioX.getVideo());
            //   ps.setString(6, resultadoRaioX.getRaioX());
            ps.setString(6, resultadoRaioX.getNomePaciente());
            ps.setInt(7, resultadoRaioX.getCodigoServico());
            ps.setInt(8, resultadoRaioX.getCodigoPaciente());
            ps.setInt(9, resultadoRaioX.getCodigoUser());
            ps.setString(10, resultadoRaioX.getOvarios());
            ps.setString(11, resultadoRaioX.getConclusao());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

    }

    public void insertToEcografiaMorfologica(ResultadoRaioX resultadoRaioX) {
        //   conexao.Connectando();
        sql = "INSERT INTO resultadoecografia(descricao,nomepaciente,codigoServico,codigoPaciente,codigoUser,data,conclusao,face,cranio,segmentoCefalico,biometrica,pescocofetal,torax,coracao,abdomen,inferiores,superior,anexoFetais,orgaoGenitais,colunavertebral,estado)VALUES (?,?,?,?,?,now(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println("");
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, resultadoRaioX.getDescricao());
            ps.setString(2, resultadoRaioX.getNomePaciente());
            ps.setInt(3, resultadoRaioX.getCodigoServico());
            ps.setInt(4, resultadoRaioX.getCodigoPaciente());
            ps.setInt(5, resultadoRaioX.getCodigoUser());
            ps.setString(6, resultadoRaioX.getConclusao());
            ps.setString(7, resultadoRaioX.getFace());
            ps.setString(8, resultadoRaioX.getCranio());
            ps.setString(9, resultadoRaioX.getSegmentocefalico());
            ps.setString(10, resultadoRaioX.getBiometria());
            ps.setString(11, resultadoRaioX.getPescocoFetal());
            ps.setString(12, resultadoRaioX.getTorax());
            ps.setString(13, resultadoRaioX.getCoracao());
            ps.setString(14, resultadoRaioX.getAbdomen());
            ps.setString(15, resultadoRaioX.getInferior());
            ps.setString(16, resultadoRaioX.getSuperior());
            ps.setString(17, resultadoRaioX.getAnexofetais());
            ps.setString(18, resultadoRaioX.getOrgaogenitas());
            ps.setString(19, resultadoRaioX.getColunaVertebral());
            ps.setString(20, resultadoRaioX.getEstado());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

    }

    public ArrayList<String> getLisitaNomeRaioX() {
        //   conexao.Connectando();
        sql = "SELECT * FROM resultadoraixo s";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("s.descricao"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public int getCodigo(String designacao) {
        // conexao.Connectando();
        sql = "SELECT id from resultadoraixo where descricao ='" + designacao + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public String getImagem(String designacao) {
        // conexao.Connectando();
        sql = "SELECT imagem from resultadoraixo where descricao ='" + designacao + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("imagem");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return null;
    }
}
