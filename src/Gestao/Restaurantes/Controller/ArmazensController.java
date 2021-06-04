/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao.Restaurantes.Controller;
import entidades.Armazens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Agostinho
 */
public class ArmazensController {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
//    Conexao conexao = new Conexao();

    public ArmazensController(Connection con) {
        this.con = con;
    }

    public void Salvar(Armazens armazens) {
        sql = "INSERT INTO public.armazens(\"descricao\",\"codigoUtilizador\",\"codigoStatus\")VALUES (?,?,?)";
        System.out.println("Teste" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, armazens.getDescricao());
            ps.setInt(2, armazens.getCodigoUsuario());
            ps.setInt(3, armazens.getCodigoStatus());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }

    public ArrayList<Armazens> listar() {
        sql = "SELECT *  FROM public.armazens order by 1";
        ArrayList<Armazens> list = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Armazens armazens = new Armazens();
                armazens.setCodigoArmazens(rs.getInt("idArmazens"));
                armazens.setDescricao(rs.getString("descricao"));
                list.add(armazens);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }

    public ArrayList<String> getArmazensDesignacao() {
        sql = "SELECT \"descricao\" as Descricao  FROM public.armazens order by 1";
        ArrayList<String> list = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("Descricao"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }

    public void Eliminar(int Codigo) {
        sql = "UPDATE armazens SET  \"codigoStatus\" =2 WHERE   \"idArmazens\" =" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.executeQuery();
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }

    public int CodigoArmazens(String nome) {
        sql = "SELECT \"idArmazens\" AS Codigo FROM armazens WHERE \"descricao\" ='" + nome + "'";
        //sql = "SELECT  "Codigo" FROM PUBLIC.categorias  WHERE "Descricao" = 'nome'";

        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("Codigo");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public String NomeArmazens(int Codigo) {
        sql = "SELECT \"descricao\" as Descricao FROM armazens WHERE \"idArmazens\" =" + Codigo;
        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("Descricao");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }

    public void Editar(Armazens armazens, int Codigo) {

        sql = "UPDATE armazens Set  \"descricao\"=?,  \"codigoUtilizador\" =?,  \"codigoStatus\"=? Where \"idArmazens\"=" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, armazens.getDescricao());
            ps.setInt(2, armazens.getCodigoUsuario());
            ps.setInt(3, armazens.getCodigoStatus());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Alterados com Sucessos");

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }

}
