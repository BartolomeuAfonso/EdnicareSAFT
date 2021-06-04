/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao.Restaurantes.Controller;

import entidades.Mesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.TipoMesa;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Agostinho
 */
public class MesaController {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
//    Conexao conexao = new Conexao();

    public MesaController(Connection con) {
        this.con = con;
    }

    public void Salvar(Mesa mesa) {
        sql = "INSERT INTO mesa(\"Descricao\", \"nOCupante\", \"CodigoStatusMesa\", \"CodigoTipoMesa\")VALUES (?, ?, ?, ?)";
//        System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, mesa.getDescricao());
            ps.setInt(2, mesa.getnOCupante());
            ps.setInt(3, mesa.getCodigoStatusMesa());
            ps.setInt(4, mesa.getCodigoTipoMesa());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");
        } catch (SQLException e) {
            System.out.println("UPS, Deve Contactar  a DEVTEC:" + e);
        }
    }

    public void Editar(entidades.Mesa mesa, int Codigo) {
        sql = "UPDATE mesa SET \"Descricao\"=?, \"nOCupante\"=?, \"CodigoStatusMesa\"=? WHERE  \"Codigo\"=" + Codigo;
//        System.out.println("Teste" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, mesa.getDescricao());
            ps.setInt(2, mesa.getnOCupante());
            ps.setInt(3, mesa.getCodigoStatusMesa());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Alterados com Sucesso");
        } catch (SQLException e) {

            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
    }

    public ArrayList<Mesa> listar() {
        sql = " SELECT * FROM mesa c";
        ArrayList<entidades.Mesa> cl = new ArrayList<>();
        try {
            Mesa mesa = new Mesa();
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                mesa.setCodigo(rs.getInt("Codigo"));
                mesa.setDescricao(rs.getString("Descricao"));
                mesa.setCodigoStatusMesa(rs.getInt("CodigoStatusMesa"));
                mesa.setnOCupante(rs.getInt("nOcupante"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return cl;
    }

    public ArrayList<String> getMesalistar(String nome) {
        sql = " SELECT  \"Descricao\" as Descricao FROM mesa WHERE \"Descricao\" NOT like '"+nome+"' order by 1";
        ArrayList<String> cl = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cl.add(rs.getString("Descricao"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return cl;
    }

    public ArrayList<String> getMesalistarporCodigo(int codigo) {
        sql = " SELECT  \"Descricao\" as Descricao FROM mesa where \"CodigoTipoMesa\"=" + codigo + " order by 1";
//        System.out.println("Teste:" + sql);
        ArrayList<String> cl = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cl.add(rs.getString("Descricao"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return cl;
    }
    public ArrayList<Mesa> getMesa(int codigo) {
        sql = " SELECT  \"Descricao\" as d,\"CodigoStatusMesa\" as Descricao FROM mesa where \"CodigoTipoMesa\"=" + codigo + " order by 1";
//        System.out.println("Teste:" + sql);
        ArrayList<Mesa> cl = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cl.add(new Mesa(rs.getString("d"),rs.getInt("Descricao")));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return cl;
    }

    public void Eliminar(int Codigo) {
        sql = "DELETE FROM mesa WHERE Codigo =" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Eliminado com Sucesso:");
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC" + e);

        }
    }

    public int CodigoMesa(String nome) {
        sql = "SELECT \"Codigo\" AS Codigo FROM mesa WHERE \"Descricao\" like '" + nome + "' ";
//        System.out.println("Teste:" + nome);
        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Teste: " + rs.getInt("Codigo"));
                return rs.getInt("Codigo");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS, Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }
    public int LastCod() {
        sql = "SELECT MAX(\"Codigo\") AS Codigo FROM mesa ";
//        System.out.println("Teste:" + nome);
        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
//                System.out.println("Teste: " + rs.getInt("Codigo"));
                return rs.getInt("Codigo");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS, Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }
    public String Mesa(int cod) {
        sql = "SELECT \"Descricao\" AS Codigo FROM mesa WHERE \"Codigo\" = " + cod + " ";
//        System.out.println("Teste:" + nome);
        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
//                System.out.println("Teste: " + rs.getInt("Codigo"));
                return rs.getString("Codigo");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS, Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }
    public String Mesa2(int cod) {
        sql = "SELECT \"Descricao\" AS Codigo FROM mesa WHERE \"Codigo\" = " + (cod) + " ";
//        System.out.println("Teste:" + nome);
        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
//                System.out.println("Teste: " + rs.getInt("Codigo"));
                return rs.getString("Codigo");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS, Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }
    public int MesasLivres(int codArea) {
        sql = "SELECT MAX(c.\"Codigo\") AS Codigo FROM mesa WHERE \"CodigoStatusMesa\"=1 and  \"CodigoTipoMesa\" ='" + codArea + "' ";
      
        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Teste: " + rs.getInt("Codigo"));
                return rs.getInt("Codigo");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS, Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }
    public int MesasOcupadas(int codArea) {
        sql = "SELECT MAX(\"Codigo\") AS Codigo FROM mesa  WHERE \"CodigoStatusMesa\"=2 and  \"CodigoTipoMesa\" ='" + codArea + "' ";
      
        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Teste: " + rs.getInt("Codigo"));
                return rs.getInt("Codigo");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS, Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    // Tipo de Mesa
    public void salvarTipoMesa(TipoMesa tipoMesa) {
        sql = "INSERT INTO public.tipo_mesa(\"Descricao\")VALUES (?)";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, tipoMesa.getDescricao());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");
        } catch (SQLException e) {
            System.out.println("UPS, Deve Contactar  a DEVTEC:" + e);
        }
    }

    public List RetornarObjectoMesa() {
        List ObjectoCompleto = new ArrayList<>();
        sql = "SELECT \"Descricao\" AS Descricao FROM  \"tipo_mesa\"  ORDER BY 1";
//        System.out.println("Teste:" + sql);
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

    public ArrayList<String> getTipoMesa() {
        sql = " SELECT   \"Descricao\" as Descricao FROM  \"tipo_mesa\" order by 1";
        ArrayList<String> cl = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cl.add(rs.getString("Descricao"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return cl;
    }

    public int CodigotipoMesa(String nome) {
        sql = "SELECT \"Codigo\"  as Codigo FROM  \"tipo_mesa\" WHERE \"Descricao\" ='" + nome + "'";
//        System.out.println("Teste:" + sql);
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

}
