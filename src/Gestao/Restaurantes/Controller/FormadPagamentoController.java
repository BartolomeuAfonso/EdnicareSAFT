/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao.Restaurantes.Controller;
import entidades.FormaPagamento;
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
public class FormadPagamentoController {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
//    Conexao conexao = new Conexao();

    public FormadPagamentoController(Connection con) {
        this.con = con;
    }

    public void Salvar(FormaPagamento fp) {
        sql = "INSERT INTO public.\"formaPagamento\"(\"Descricao\", \"CodigoStatus\")VALUES (?, ?)";
        System.out.println("Teste:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, fp.getDescricao());
            ps.setInt(2, fp.getCodigoStatus());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");

        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }

    }

    public ArrayList<FormaPagamento> listar() {
        sql = "SELECT * FROM formaPagamento order by 1";
        ArrayList<entidades.FormaPagamento> list = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                entidades.FormaPagamento formaPagamento = new FormaPagamento();
                formaPagamento.setCodigo(rs.getInt("Codigo"));
                formaPagamento.setCodigo(rs.getInt("CodigoStatus"));
                formaPagamento.setDescricao(rs.getString("Descricao"));
                list.add(formaPagamento);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }

        return list;
    }

    public ArrayList<String> getlistar() {
        sql = "SELECT \"Descricao\" as Descricao FROM  \"formaPagamento\"  order by 1";
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
            System.out.println("Error:" + e);
        }

        return list;
    }

    public void Eliminar(int Codigo) {
        sql = "UPDATE public.\"formaPagamento\" SET \"CodigoStatus\"=2  WHERE \"Codigo\"=" + Codigo;
        System.out.println("Teste" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Eliminado com Sucesso:");
        } catch (SQLException e) {
            System.out.println("Error" + e);

        }
    }

    public void editar(FormaPagamento fp, int Codigo) {
        sql = "UPDATE public.\"formaPagamento\" SET  \"Descricao\"=?, \"CodigoStatus\"=? WHERE \"Codigo\"=" + Codigo;
//        System.out.println("Teste" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, fp.getDescricao());
            ps.setInt(2, fp.getCodigoStatus());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Editados com Sucesso:");
        } catch (SQLException e) {
            System.out.println("Error" + e);

        }
    }

    public int getCodigo(String nome) {
        sql = "SELECT \"Codigo\" AS Codigo FROM \"formaPagamento\" WHERE \"Descricao\" ='" + nome + "'";

        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("Codigo");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS, Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

}
