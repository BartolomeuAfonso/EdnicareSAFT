/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao.Restaurantes.Controller;
import sf.ce.conexao.BDConexao;
import entidades.Existencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Amarildo Xpecialista
 */
public class ExistenciaController {

//    BDConexao conexao;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    public ExistenciaController(Connection con) {
        this.con = con;
    }

    public void salvarExistencia(Existencia existencia) {
        sql = "INSERT INTO existencia(\n"
                + "            codigo_armazem, existencia, \"dataActualizacao\", \n"
                + "            codigo_usuario, codigo_produto)\n"
                + "    VALUES (?, ?, ?, \n"
                + "            ?, ?);";
        System.out.println(sql);
        java.sql.Date data1 = new java.sql.Date(existencia.getDataActualizacao().getTime());

        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setInt(1, existencia.getCodigoArmazem());
            ps.setInt(2, existencia.getExistencia());
            ps.setDate(3, data1);
            ps.setLong(4, existencia.getIdUtilizador());
            ps.setInt(5, existencia.getIdProduto());
            ps.execute();
//            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");
            ps.close();
//            conexao.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SERA UPS,Deve Contactar com a DEVTEC:" + ex);
        }

    }

    public void actualizarExistencia(Existencia existencia) {
        sql = "UPDATE existencia\n"
                + "   SET existencia=?, \"dataActualizacao\"=?, \n"
                + "       \"codigo_usuario\"=?"
                + " WHERE \"codigo_produto\" = ? and \"codigo_armazem\" = ?";
        System.out.println(sql);
        java.sql.Date data1 = new java.sql.Date(existencia.getDataActualizacao().getTime());
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setInt(1, existencia.getExistencia());
            ps.setDate(2, data1);
            ps.setInt(3, existencia.getIdUtilizador());
            ps.setLong(4, existencia.getIdProduto());
            ps.setLong(5, existencia.getCodigoArmazem());
            ps.execute();
            System.out.println("Dados Actualizados com Sucesso");
            // JOptionPane.showMessageDialog(null, "Dados Editados com Sucesso");
            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("DEVTEC:" + ex);
        }

    }

    public boolean existe(int codigoProduto, int codigoArmazem) {
        sql = "SELECT * FROM public.existencia WHERE \"codigo_produto\" = " + codigoProduto + " and \"codigo_armazem\" = " + codigoArmazem + "";
        ResultSet rs = new BDConexao().executeQuery(sql);

        try {
            if (rs.next()) {
                return true;
            }
             ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    public int getExistenciaAntes(int idTipo) {
        sql = "SELECT * FROM public.existencia where \"codigo_produto\" =" + idTipo + "";

//        ResultSet rs = new BDConexao().executeQuery(sql);
        
        int next = 0;

        try {
            ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("existencia");
            }
             ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Erro:" + ex);
        }

        return 0;
    }
}
