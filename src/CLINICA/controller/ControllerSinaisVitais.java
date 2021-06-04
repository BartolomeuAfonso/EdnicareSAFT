/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import CLINICA.modelo.DiagnosticoInternamento;
import CLINICA.modelo.DiarioClinico;
import CLINICA.modelo.SinaisVitais;
import CLINICA.modelo.FolhaTeraupetica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author Probook
 */
public class ControllerSinaisVitais {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;

    Connection con;

    public ControllerSinaisVitais(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvar(SinaisVitais sinaisVitais) {
        //     conexao.Connectando();
        sql = "INSERT INTO sinaisvitais(codigoInternamento,frequenciaCardiaca,pulso,tensao,temperatura,data,user)values(?,?,?,?,?,now(),?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, sinaisVitais.getCodigoInternamento());
            ps.setString(2, sinaisVitais.getFrequenciaCardiaca());
            ps.setString(3, sinaisVitais.getPulso());
            ps.setString(4, sinaisVitais.getTensao());
            ps.setString(5, sinaisVitais.getTemperatura());
            ps.setString(6, sinaisVitais.getCodigUsuario());
            ps.execute();
        } catch (SQLException ex) {
        }
//        conexao.DesconectaBanco();
    }

    public void salvarAlta(int codigo, int codigoInternamento, String recomendacao, String resumo) {
        //   conexao.Connectando();
        sql = "update internamento set codigoAlta =" + codigo + ", recomendacao ='" + recomendacao + "', resumoclinico='" + resumo + "', dataSaida =now() where codigo=" + codigoInternamento;
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
        }
        //  conexao.DesconectaBanco();
    }

    public void salvarFolhaTerupetica(FolhaTeraupetica sinaisVitais) {
        //    conexao.Connectando();
        sql = "INSERT INTO folhateraupetica(codigoInternamento,descricao,data)values(?,?,now())";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, sinaisVitais.getCodigoInternamento());
            ps.setString(2, sinaisVitais.getDescricao());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //   conexao.DesconectaBanco();
    }

    public void salvarDiarioClinico(DiarioClinico sinaisVitais) {
        conexao.Connectando();
        sql = "INSERT INTO diariaclinico(codigoInternamento,descricao,data)values(?,?,now())";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, sinaisVitais.getCodigoInternamento());
            ps.setString(2, sinaisVitais.getDescricao());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
//        conexao.DesconectaBanco();
    }

    public void salvarNotasEnfermagem(DiarioClinico sinaisVitais) {
        //   conexao.Connectando();
        sql = "INSERT INTO notaenfermagem(codigoInternamento,descricao,data)values(?,?,now())";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, sinaisVitais.getCodigoInternamento());
            ps.setString(2, sinaisVitais.getDescricao());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
//        conexao.DesconectaBanco();
    }

    public void salvarMedicacaoInternamento(DiarioClinico sinaisVitais) {
        //   conexao.Connectando();
        sql = "INSERT INTO medicainternamento(codigoInternamento,descricao,data)values(?,?,now())";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, sinaisVitais.getCodigoInternamento());
            ps.setString(2, sinaisVitais.getDescricao());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //  conexao.DesconectaBanco();
    }

    public void salvarDiagnostico(DiagnosticoInternamento sinaisVitais) {
        //   conexao.Connectando();
        sql = "INSERT INTO diagnosticointernamento(codigoInternamento,descricao,data)values(?,?,now())";
        //sql = "INSERT INTO diagnosticointernamento(codigoInternamento,descricao,descricao1,descricao2,descricao3,descricao4,data)values(?,?,now())";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, sinaisVitais.getCodigoInternamento());
            ps.setString(2, sinaisVitais.getDescricao1());
//            ps.setString(2, sinaisVitais.getDescricao2());
//            ps.setString(2, sinaisVitais.getDescricao3());
//            ps.setString(2, sinaisVitais.getDescricao4());
//            ps.setString(2, sinaisVitais.getDescricao5());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //    conexao.DesconectaBanco();
    }

}
