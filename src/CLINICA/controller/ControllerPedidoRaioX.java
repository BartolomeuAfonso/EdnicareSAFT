/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.RaioX;
import CLINICA.modelo.PedidoItensRaioX;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Desenvolvimento
 */
public class ControllerPedidoRaioX {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerPedidoRaioX(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void Salvar(RaioX pedidoExames) {
        //  conexao.Connectando();
        sql = "INSERT INTO pedidoraixo(dataEntrada,codigoPaciente,quantidade,descricao,codigoServico)values(now(),?,?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pedidoExames.getCodigoPaciente());
            ps.setInt(2, pedidoExames.getQuantidade());
            ps.setString(3, pedidoExames.getDesignacao());
            ps.setInt(4, pedidoExames.getCodigoServico());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Requisição enviado com Sucesso");

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //  conexao.DesconectaBanco();
    }

    public void salvar(RaioX pedidoExames) {
        //  conexao.Connectando();
        sql = "INSERT INTO pedidoraixo(dataEntrada,codigoPaciente,quantidade,descricao)values(now(),?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pedidoExames.getCodigoPaciente());
            ps.setInt(2, pedidoExames.getQuantidade());
            ps.setString(3, pedidoExames.getDesignacao());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Requisição enviado com Sucesso");

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //  conexao.DesconectaBanco();
    }

    public void updatePago(int codigo) {
        sql = "UPDATE pedidoitensraio SET estado ='PAGO' WHERE codigoPedidoRaio=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Pedido Pago com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
    }

    public void SalvarItens(PedidoItensRaioX pedidoItensRaioX) {
        //  conexao.Connectando();
        sql = "INSERT INTO pedidoitensraio(codigoPedidoRaio,codigoServico,quantidade)values(?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pedidoItensRaioX.getCodigoRaio());
            ps.setInt(2, pedidoItensRaioX.getCodigoServico());
            ps.setInt(3, pedidoItensRaioX.getQuantidade());
            ps.execute();

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //conexao.DesconectaBanco();
    }

    public void salvarItens(PedidoItensRaioX pedidoItensRaioX) {
        //  conexao.Connectando();
        sql = "INSERT INTO pedidoitensraio(codigoPedidoRaio,codigoServico,quantidade,estado)values(?,?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pedidoItensRaioX.getCodigoRaio());
            ps.setInt(2, pedidoItensRaioX.getCodigoServico());
            ps.setInt(3, pedidoItensRaioX.getQuantidade());
            ps.setString(4, pedidoItensRaioX.getEstado());
            ps.execute();

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //conexao.DesconectaBanco();
    }

    public int getLastFactura() {
        try {
            //    conexao.Connectando();
            String sql = "select max(idRaio) as max from pedidoraixo";
            System.out.println("Teste:" + sql);
            PreparedStatement prepara = con.prepareStatement(sql);
            ResultSet rs = prepara.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return 0;
    }

    public int getCodigoLastFactura(String data, int paciente) {
        try {
            //    conexao.Connectando();
            sql = "SELECT idRaio as last FROM pedidoraixo p where Date(dataEntrada)='" + data + "' AND codigoPaciente =" + paciente;
            System.out.println("Teste:" + sql);
            PreparedStatement prepara = con.prepareStatement(sql);
            ResultSet rs = prepara.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return 0;
    }

    public void updateRaioX(int codigoRaixo) {
        //conexao.Connectando();
        sql = "UPDATE pedidoraixo SET estado ='PRONTO' where idRaio=" + codigoRaixo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //    conexao.DesconectaBanco();
    }

    public void updateRaioXItens(int codigoServico) {
        //conexao.Connectando();
        sql = "UPDATE pedidoitensraio SET estado ='PRONTO' where codigoServico=" + codigoServico;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //  conexao.DesconectaBanco();
    }

    public ArrayList<String> getListaPedido(int codigoPaciente) {
//        conexao.Connectando();
        String sql = "SELECT s.designacao as pedidos from pedidos_exames p inner join servicos s\n"
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
}
