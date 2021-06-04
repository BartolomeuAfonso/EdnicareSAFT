/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.controller;

import java.sql.Connection;
import java.util.Date;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Financas.Modelo.Movimentacoes;

/**
 *
 * @author El Router
 */
public class ctr_Movimentacoes {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    public ctr_Movimentacoes(Connection con) {
        this.con = con;
    }

    public boolean save(Movimentacoes model) {
        sql = "INSERT INTO movimentacoes(data,tipo_movimento, conta,caixa, titulo,descricao,destinatario,valor,saldo,status,saldo_anterior)VALUES (?,?, ?, ?, ?, ?, ?, ?,?, ?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setDate(1, (java.sql.Date) model.getDate());
            ps.setInt(2, model.getTipo_movimento());
            ps.setInt(3, model.getConta());
            ps.setInt(4, model.getCaixa());
            ps.setString(5, model.getTitulo());
            ps.setString(6, model.getDescricao());
            ps.setString(7, model.getDestinatario());
            ps.setDouble(8, model.getValor());
            ps.setDouble(9, model.getSaldo());
            ps.setDouble(11, model.getSaldo_anterior());
            ps.setInt(10, 1);

            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public boolean update(Movimentacoes model, int codigo) {

        sql = "UPDATE movimentacoes SET data=?, tipo_movimento=?, conta=?, caixa=?, titulo=?,descricao=?, destinatario=?, valor=?, saldo=? WHERE codigo=?";
        System.out.println("Sql:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setDate(1, (java.sql.Date) model.getDate());
            ps.setInt(2, model.getTipo_movimento());
            ps.setInt(3, model.getConta());
            ps.setInt(4, model.getCaixa());
            ps.setString(5, model.getTitulo());
            ps.setString(6, model.getDescricao());
            ps.setString(7, model.getDestinatario());
            ps.setDouble(8, model.getValor());
            ps.setDouble(9, model.getSaldo());
            ps.setInt(10, codigo);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public boolean delete(int Codigo) {
        sql = "DELETE FROM movimentacoes  WHERE  codigo=" + Codigo;
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public int getCodByNome(String nome) {

        sql = "SELECT codigo FROM caixa WHERE descricao like '" + nome + "%'";
        System.out.println("Teste:" + sql);

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("codigo");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public int getCodByTitulo(String nome) {

        sql = "SELECT codigo FROM movimentacoes WHERE titulo like '" + nome + "%'";
        System.out.println("Teste:" + sql);

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("codigo");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public double getEntradasByCaixa(String data1, String data2, int caixa) {

        sql = "SELECT SUM(valor) FROM movimentacoes WHERE tipo_movimento=1 AND data between '" + data1 + "' and '" + data2 + "' AND caixa=" + caixa;
        System.out.println("Teste:" + sql);

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public double getEntradasByCentro(String data1, String data2, int centro) {

        sql = "SELECT SUM(valor) FROM movimentacoes WHERE \"tipo_movimento\"=1 AND \"data\" between '" + data1 + "' and '" + data2 + "' AND \"centro\"=" + centro;
        System.out.println("Teste:" + sql);

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public double getSaidasByCentro(String data1, String data2, int centro) {

        sql = "SELECT SUM(valor) FROM movimentacoes WHERE tipo_movimento=2 AND data between '" + data1 + "' and '" + data2 + "' AND centro=" + centro;
        System.out.println("Teste:" + sql);

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public double getEntradasByCaixaAndConta(String data1, String data2, int caixa, int conta) {

        sql = "SELECT SUM(valor) FROM movimentacoes WHERE tipo_movimento=1 AND data between '" + data1 + "' and '" + data2 + "' AND caixa=" + caixa + " AND conta=" + conta;
        System.out.println("Teste:" + sql);

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public double getSaidasByCaixa(String data1, String data2, int caixa) {

        sql = "SELECT SUM(valor) FROM movimentacoes WHERE tipo_movimento=2 AND data between '" + data1 + "' and '" + data2 + "' AND caixa=" + caixa;
//        System.out.println("Teste:" + sql);

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public double getSaidasByCaixaAndConta(String data1, String data2, int caixa, int conta) {

        sql = "SELECT SUM(valor) FROM movimentacoes WHERE tipo_movimento=2 AND data between '" + data1 + "' and '" + data2 + "' AND caixa=" + caixa + " AND conta=" + conta;
//        System.out.println("Teste:" + sql);

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public double getEntradasByCaixaAndCentro(String data1, String data2, int caixa, String centro) {

        if (caixa == 0) {
            sql = "SELECT SUM(valor)\n"
                    + "FROM\n"
                    + "     caixa caixa INNER JOIN movimentacoes movimentacoes ON caixa.codigo = movimentacoes.caixa\n"
                    + "     INNER JOIN contas contas ON movimentacoes.conta = contas.codigo\n"
                    + "     INNER JOIN centro_custo\" centro_custo ON contas.centro_custo = centro_custo.codigo\n"
                    + "WHERE movimentacoes.\"data\" BETWEEN '" + data1 + "' and '" + data2 + "'\n"
                    + " AND centro_custo.descricao LIKE '" + centro + "%' AND tipo_movimento\"=1";
        } else {
            sql = "SELECT SUM(valor)\n"
                    + "FROM\n"
                    + "     caixa\" caixa INNER JOIN movimentacoes movimentacoes ON caixa.codigo = movimentacoes.caixa\n"
                    + "     INNER JOIN contas contas ON movimentacoes.conta = contas.codigo\n"
                    + "     INNER JOIN centro_custo centro_custo ON contas.centro_custo = centro_custo.codigo\n"
                    + "WHERE movimentacoes.data BETWEEN '" + data1 + "' and '" + data2 + "'\n"
                    + " AND movimentacoes.caixa = " + caixa + "\n"
                    + " AND centro_custo.descricao LIKE '" + centro + "%' AND tipo_movimento\"=1";
        }

//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public double getSaidasByCaixaAndCentro(String data1, String data2, int caixa, String centro) {

        if (caixa == 0) {
            sql = "SELECT SUM(valor)\n"
                    + "FROM\n"
                    + "    caixa caixa INNER JOIN movimentacoes movimentacoes ON caixa.codigo = movimentacoes.caixa\n"
                    + "     INNER JOIN contas contas ON movimentacoes.conta = contas.codigo\n"
                    + "     INNER JOIN centro_custo centro_custo ON contas.centro_custo= centro_custo.codigo\n"
                    + "WHERE movimentacoes.data BETWEEN '" + data1 + "' and '" + data2 + "'\n"
                    + " AND centro_custo.descricao LIKE '" + centro + "%' AND  tipo_movimento\"=2";
        } else {
            sql = "SELECT SUM(valor)\n"
                    + "FROM\n"
                    + "     caixa INNER JOIN movimentacoes ON caixa.codigo = movimentacoes.caixa\n"
                    + "     INNER JOIN contas contas ON movimentacoes conta = contas.codigo\n"
                    + "     INNER JOIN centro_custo centro_custo ON contas.centro_custo = centro_custo.codigo\n"
                    + "WHERE movimentacoes.data BETWEEN '" + data1 + "' and '" + data2 + "'\n"
                    + " AND movimentacoes.caixa= " + caixa + "\n"
                    + " AND centro_custo.descricao LIKE '" + centro + "' AND  \"tipo_movimento\"=2";
        }

//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public String getDescricaoByCod(int Codigo) {
        sql = "SELECT descricao FROM caixa WHERE codigo =" + Codigo;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("descricao");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }

    public String getTipoByDescricao(String descricao) {
        sql = "SELECT t.descricao FROM caixa c inner join tipo_caixa t on c.tipo_caixa=t.codigo "
                + "where c.descricao like '" + descricao + "%'";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }

    public ArrayList<String> getDescricao() {
        ArrayList<String> list = new ArrayList<>();
        sql = "SELECT descricao AS Descricao FROM  caixa";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("descricao"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }

    public ArrayList<String> getDescricao2(String d) {
        ArrayList<String> list = new ArrayList<>();
        sql = "SELECT descricao AS Descricao FROM  caixa where not descricao like '" + d + "%' ";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("descricao"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }


    /*PEGANDO O ULTIMO MOVIMENTO DO CAIXA*/
    public int getLastMovimentoByCaixa(int caixa) {
        sql = "SELECT MAX(codigo) FROM movimentacoes  where caixa= " + caixa;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    /*PEGANDO O ULTIMO SALDO DO CAIXA(SALDO DO ULTIMO MOVIMENTO DO CAIXA)*/
    public int getLastSaldoByCaixa(int caixa) {
        sql = "SELECT saldo FROM movimentacoes  where codigo = " + caixa;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public int getLastSaldoAnteriorByCaixa(int caixa) {
        sql = "SELECT saldo_anterior FROM movimentacoes  where codigo = " + caixa + "";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public ArrayList<Movimentacoes> getAllByDataAndCaixa(String data1, String data2, int caixa) {
        ArrayList<Movimentacoes> list = new ArrayList<>();
        sql = "SELECT *FROM  movimentacoes m inner join contas c on c.codigo=m.conta WHERE m.status = 1 AND caixa = " + caixa + " AND data between '" + data1 + "' AND '" + data2 + "'";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
//                System.out.println("cá estou wi!");
                list.add(new Movimentacoes(rs.getInt("codigo"), rs.getDate("data"), rs.getString("titulo"),
                        rs.getInt("conta"), rs.getInt("tipo_movimento"),
                        rs.getString("descricao"), rs.getDouble("valor"),
                        rs.getDouble("saldo_anterior"), rs.getDouble("saldo")));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }

    public ArrayList<Movimentacoes> getAllByDataAndCaixaAnsConta(String data1, String data2, int caixa, String conta) {
        ArrayList<Movimentacoes> list = new ArrayList<>();
        sql = "SELECT *FROM movimentacoes m inner join contas c on c.codigo=m.conta"
                + " WHERE m.status = 1 AND caixa = " + caixa + " AND data between '" + data1 + "' AND '" + data2 + "' "
                + "and c.descricao like '" + conta + "%'";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
//                System.out.println("cá estou wi!");
                list.add(new Movimentacoes(rs.getInt("codigo"), rs.getDate("data"), rs.getString("titulo"),
                        rs.getInt("conta"), rs.getInt("tipo_movimento"),
                        rs.getString("descricao"), rs.getDouble("valor"),
                        rs.getDouble("saldo_anterior"), rs.getDouble("saldo")));
//              
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }
}
