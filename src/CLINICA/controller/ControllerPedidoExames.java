/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.PedidoExames;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Desenvolvimento
 */
public class ControllerPedidoExames {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerPedidoExames(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void Salvar(PedidoExames pedidoExames) {
        //conexao.Connectando();
        sql = "INSERT INTO pedidos_exames(codigoMedico,codigoServico,codigoTriagem,dataPedido,codigoPaciente,hora)values(?,?,?,?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pedidoExames.getCodigoMedico());
            ps.setInt(2, pedidoExames.getCodigoServico());
            ps.setInt(3, pedidoExames.getCodigoTriagem());
            ps.setDate(4, (Date) pedidoExames.getDataPedido());
            ps.setInt(5, pedidoExames.getCodigoPaciente());
            ps.setString(6, pedidoExames.getHora());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
//        conexao.DesconectaBanco();
    }

    public void SalvarEcografia(PedidoExames pedidoExames) {
        //  conexao.Connectando();
        sql = "INSERT INTO pedidos_ecografia(codigoServico,dataPedido,designacao,diagnostico,codigoPedido)values(?,now(),?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pedidoExames.getCodigoServico());
            ps.setString(2, pedidoExames.getDescricao());
            ps.setString(3, pedidoExames.getDiagnostico());
            ps.setInt(4, pedidoExames.getCodigoTriagem());
          //  ps.setInt(5, pedidoExames.getCodigoFactura());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
//        conexao.DesconectaBanco();
    }

    public void SalvarEcografiaItens(PedidoExames pedidoExames) {
        sql = "INSERT INTO pedidoecografia(codigoPaciente,codigoMedico,dataPedido)values(?,?,now())";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pedidoExames.getCodigoPaciente());
            ps.setInt(2, pedidoExames.getCodigoMedico());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
//        conexao.DesconectaBanco();
    }

    public void SalvarParticular(PedidoExames pedidoExames) {
        //   conexao.Connectando();
        sql = "INSERT INTO pedidos_exames(codigoServico,dataPedido,codigoPaciente)values(?,now(),?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pedidoExames.getCodigoServico());
            ps.setInt(2, pedidoExames.getCodigoPaciente());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //  conexao.DesconectaBanco();
    }

    public ArrayList<String> getListaPedido(int codigoPaciente) {
        //conexao.Connectando();
        sql = "SELECT s.designacao as pedidos from pedidos_exames p inner join servicos s\n"
                + "WHERE p.dataPedido= current_date() and p.codigoPaciente=" + codigoPaciente;
        System.out.println("Teste:" + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("pedidos"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public int getLastInsertEcografia() {
        //  conexao.Connectando();
        sql = "select max(idPedido) as last from pedidoecografia";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("last");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getCodigoEcografia(int codigo) {
        //  conexao.Connectando();
        sql = "SELECT idPedido as last from pedidoecografia where Date(dataPedido) = current_Date and codigoPaciente=" + codigo;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("last");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getCodigoEcografiaporData(int codigo, String data) {
        //  conexao.Connectando();
        sql = "SELECT idPedido as last from pedidoecografia where Date(dataPedido) ='" + data + "' AND codigoPaciente=" + codigo;
        System.out.println("Pedido de Ecografia:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("last");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getLastInsertEcografiaItens() {
        //  conexao.Connectando();
        sql = "select max(idPedido) as last from pedidoecografia";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("last");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

}
