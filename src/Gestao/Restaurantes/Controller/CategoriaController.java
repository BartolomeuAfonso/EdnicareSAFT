/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao.Restaurantes.Controller;

import entidades.Categorias;
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
public class CategoriaController {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
//    Conexao conexao = new Conexao();

    public CategoriaController(Connection con) {
        this.con = con;
    }

    public boolean Salvar(Categorias c, int ponto) {
        sql = "INSERT INTO categorias(\"Descricao\",\"CodigoStatus\", \"codigo_pontoVenda\") VALUES (?,?,?)";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getDescricao());
            ps.setInt(2, 1);
            ps.setInt(3, ponto);
            ps.execute();
            ps.close();
            return true;

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public ArrayList<Categorias> listar() {
        sql = "SELECT *  FROM public.categorias order by 1";
        ArrayList<Categorias> list = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Categorias categorias = new Categorias();
                /// categorias.setCodigo(rs.getInt("Codigo"));
                categorias.setDescricao(rs.getString("Descricao"));
                list.add(categorias);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }

    public ArrayList<Categorias> getlistar() {
        sql = "SELECT *  FROM public.categorias order by 1";
        ArrayList<Categorias> list = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Categorias categorias = new Categorias();
                categorias.setCodigo(rs.getInt("Codigo"));
                categorias.setDescricao(rs.getString("Descricao"));
                list.add(categorias);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }

    public ArrayList<String> getCategoriaDesignacao() {
        sql = "SELECT \"Descricao\" as Descricao  FROM public.categorias WHERE \"CodigoStatus\"=1 order by 1";
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
    public ArrayList<String> getCategoriaDesignacaoByarea(int area) {
        sql = "SELECT \"Descricao\" as Descricao  FROM public.categorias WHERE \"CodigoStatus\"=1 and \"codigo_pontoVenda\" = "+area+" order by 1";
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
        sql = "UPDATE  categorias set \"CodigoStatus\"=2 WHERE \"Codigo\"=" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.executeQuery();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Eliminados com Sucesso");
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }

    public int CodigoCategoria(String nome) {
        sql = "SELECT \"Codigo\" FROM categorias WHERE \"Descricao\" ='" + nome + "'";
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

    public String NomeCategoria(int Codigo) {
        sql = "SELECT \"Descricao\" FROM categorias WHERE Codigo =" + Codigo;
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

    public int CodCategoria(String Nome) {
        sql = "SELECT * FROM categorias WHERE Descricao ='" + Nome + "'";
        int codigo = 1;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt("codigo");
            }
            ps.close();
        } catch (SQLException ex) {
            new RuntimeException(ex);
        }
        return codigo;
    }

    public List RetornarObjectoCategoria() {
        List ObjectoCompleto = new ArrayList<>();
        sql = "SELECT \"Descricao\" AS Descricao FROM  \"categorias\" WHERE \"CodigoStatus\"=1 ORDER BY 1";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ObjectoCompleto.add(rs.getString("Descricao"));
            }
            ps.close();
        } catch (SQLException ex) {
            new RuntimeException(ex);
        }

        return ObjectoCompleto;
    }

    public List RetornarObjectoCategoria2() {
//        conexao.ConexaoBD();
        List ObjectoCompleto = new ArrayList<>();
        sql = "SELECT * FROM categorias";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ObjectoCompleto.add(rs.getString("Descricao"));
            }
            ps.close();
        } catch (SQLException ex) {
            new RuntimeException(ex);
        }
        return ObjectoCompleto;
    }

    public List RetornarObjectoCategoria3(int codigo) {
        List ObjectoCompleto = new ArrayList<>();
        sql = "SELECT \"Descricao\" AS Descricao FROM  \"categorias\" WHERE \"CodigoStatus\"=1 and \"codigo_pontoVenda\" = " + codigo + " ORDER BY 1";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ObjectoCompleto.add(rs.getString("Descricao"));
            }
            ps.close();
        } catch (SQLException ex) {
            new RuntimeException(ex);
        }

        return ObjectoCompleto;
    }
    public boolean ValidarExistencia(int codigo,int pontoVenda) {
        sql = "SELECT * FROM  \"categorias\" WHERE \"CodigoStatus\"=1 and \"Codigo\" = "+codigo+" and \"codigo_pontoVenda\" = " + pontoVenda + "";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
            ps.close();
        } catch (SQLException ex) {
            new RuntimeException(ex);
        }

        return false;
    }
    public boolean ValidarExistencia2(String categoria,int pontoVenda) {
        sql = "SELECT * FROM  \"categorias\" WHERE \"CodigoStatus\"=1 and \"Descricao\" = '"+categoria+"' and \"codigo_pontoVenda\" = " + pontoVenda + "";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
            ps.close();
        } catch (SQLException ex) {
            new RuntimeException(ex);
        }

        return false;
    }

    public void Editar(entidades.Categorias categoria, int Codigo, int ponto) {
        sql = "UPDATE categorias Set  \"Descricao\"=?, \"codigo_pontoVenda\"=? Where \"Codigo\"=" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getDescricao());
            ps.setInt(2, ponto);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Alterados com Sucessos");

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }

}
