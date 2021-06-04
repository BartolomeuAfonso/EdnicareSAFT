/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.Guia;
import CLINICA.modelo.OficioModelo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerGuia {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerGuia(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvar(Guia factura) {
       // conexao.Connectando();
        sql = "INSERT INTO guia(dataGuia,codigoUser,codigoCliente,valorApagar,nomeClientes,numerador,desconto,codigoSeguro)values(now(),?,?,?,?,?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, factura.getCodigoUser());
            ps.setInt(2, factura.getCodigoCliente());
            ps.setDouble(3, factura.getValorApagar());
            ps.setString(4, factura.getNomecliente());
            ps.setInt(5, factura.getNumerador());
            ps.setDouble(6, factura.getDesconto());
            ps.setInt(7, factura.getCodigoSeguro());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Guia realizada com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //conexao.DesconectaBanco();
    }

    public void update(double valor, double desconto, int codigo) {
        //conexao.Connectando();
        sql = "UPDATE guia SET valorApagar =" + valor + ", desconto =" + desconto + " WHERE idGuia=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Guia Actualizado");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
      //  conexao.DesconectaBanco();
    }

    public int getLastGuia() {
        try {
           // conexao.Connectando();
            String sql = "select max(idGuia) as max from guia";
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

    public void getAnularGuia(int codigo) {

    //    conexao.Connectando();
        String sql = "update guia set estado ='ANULADO' WHERE idGuia=" + codigo;
        System.out.println("Feito:" + sql);
        try {
            PreparedStatement prepara = con.prepareStatement(sql);
            prepara.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public ArrayList<String> getNomeGuia(String empresa) {
      ///  conexao.Connectando();
        sql = "SELECT DISTINCT g.nomeClientes as designacao FROM guia g inner join pacientes p on g.codigoCliente =p.idPaciente\n"
                + "inner join empresaseguros e on e.idSeguros=p.codigoSeguro where Date(dataGuia)=Current_date and e.designacao='" + empresa + "'";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("designacao"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<OficioModelo> getOficio(String empresa) {
   //     conexao.Connectando();
        sql = "SELECT DISTINCT g.idGuia as guia,e.designacao as empresa, p.cartaoPS as cartao, p.nomecompleto as nome,date(g.dataGuia) as dataGuia, g.valorApagar as valor FROM guia g inner join pacientes p on g.codigoCliente=p.idPaciente\n"
                + "inner join empresaseguros e on e.idSeguros =p.codigoSeguro\n"
                + "where e.designacao ='" + empresa + "'";
        System.out.println("Consulta:" + sql);
        OficioModelo oficioModelo = new OficioModelo();
        ArrayList<OficioModelo> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                oficioModelo.setGuia(rs.getString("guia"));
                oficioModelo.setEmpresa(rs.getString("empresa"));
                oficioModelo.setCartao(rs.getString("cartao"));
                oficioModelo.setNome(rs.getString("nome"));
                oficioModelo.setData(rs.getString("dataGuia"));
                oficioModelo.setValor(rs.getString("valor"));
                lista.add(oficioModelo);
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getNomeGuiaporData(String data) {
      //  conexao.Connectando();
        sql = "SELECT DISTINCT g.nomeClientes as designacao FROM guia g where Date(dataGuia)='" + data + "'";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("designacao"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getNumeroGuia(String data, String nome) {
        //conexao.Connectando();
        sql = "SELECT distinct g.idGuia as guia FROM guia g where date(g.dataGuia) ='" + data + "' and g.nomeclientes ='" + nome + "'";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("guia"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public boolean existeGuia(int numero_guia) {
       // conexao.Connectando();
        sql = "SELECT idGuia FROM guia g where idGuia=" + numero_guia;
        System.out.println("Nº Guia:" + numero_guia);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            return true;
        }

        return false;
    }

    public boolean existeGuiaProduto(int numero_guia, int produto) {
      //  conexao.Connectando();
        sql = "SELECT codigoServico FROM guia g where idGuia=" + numero_guia + " AND codigoServico=" + produto;
        System.out.println("Nº Guia:" + numero_guia);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            return true;
        }

        return false;
    }

    public int getCodigoNumeradorEmpresa(String codigoEmpresa) {
       // conexao.();
        sql = "SELECT max(numerador) as max FROM guia g inner join pacientes p on g.codigoCliente =p.idPaciente\n"
                + "inner join empresaseguros e on e.idSeguros = p.codigoSeguro\n"
                + "where e.designacao='" + codigoEmpresa + "';";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("max");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public double dividaSeguros(String data1, String data2, String seguro) {
       // conexao.Connectando();
        sql = "ELECT sum(g.valorApagar) as totalApagar from guia g inner join guia_itens gt on g.idGuia =gt.codigoGuia\n"
                + "inner join pacientes p on g.codigoCliente = p.idPaciente\n"
                + "inner join empresaSeguros e on e.idSeguros = p.codigoSeguro\n"
                + "where date(g.dataGuia) between '" + data1 + "' and '" + data2 + "' and e.designacao ='" + seguro + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble("totalApagar");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
}
