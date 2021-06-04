/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao.Restaurantes.Controller;
import entidades.PontoVenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Agostinho
 */
public class PontoVendaController {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
//    Conexao conexao = new Conexao();

    public PontoVendaController(Connection con) {
        this.con = con;
    }

    public void Salvar(entidades.PontoVenda pontoVenda) {
        sql = "INSERT INTO \"pontoVendas\" (\"Descricao\",\"CodigoStatus\",\"CodigoArmazem\" )VALUES (?,?,?)";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, pontoVenda.getDescricao());
            ps.setInt(2, pontoVenda.getCodigoStatus());
            ps.setInt(3, pontoVenda.getCodigoArmazem());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }

    public void Editar(PontoVenda pontoVenda, int codigo) {
        sql = "UPDATE public.\"pontoVendas\" SET \"Descricao\"=?, \"CodigoStatus\"=?, \"CodigoArmazem\"=? WHERE \"Codigo\"=" + codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, pontoVenda.getDescricao());
            ps.setInt(2, pontoVenda.getCodigoStatus());
            ps.setInt(3, pontoVenda.getCodigoArmazem());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }

    public ArrayList<entidades.PontoVenda> listar() {
        sql = "SELECT * FROM pontoVendas order by 1";
        ArrayList<entidades.PontoVenda> list = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PontoVenda pv = new PontoVenda();
                pv.setCodigo(rs.getInt("Codigo"));
                pv.setDescricao(rs.getString("Descricao"));
                pv.setCodigoStatus(rs.getInt("CodigoStatus"));
                list.add(pv);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

        return list;
    }

    public void Eliminar(int Codigo) {
        sql = "DELETE FROM pontoVendas WHERE Codigo=" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.executeQuery();
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }

    public int CodigoPontoVenda(String nome) {

        sql = "SELECT \"Codigo\" FROM  \"pontoVendas\" WHERE  \"Descricao\"  ='" + nome + "'";
        //System.out.println("Teste:" + sql);

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

    public String DescricaoPonto(int Codigo) {
        sql = "SELECT \"Descricao\" FROM pontoVendas WHERE Codigo =" + Codigo;
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

    public ArrayList<String> Descricao() {
        ArrayList<String> list = new ArrayList<>();
        sql = "SELECT \"Descricao\" AS Descricao FROM  \"pontoVendas\"  WHERE  \"CodigoStatus\" =1";
        try {
//            con = conexao.ConexaoBD();
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

}
