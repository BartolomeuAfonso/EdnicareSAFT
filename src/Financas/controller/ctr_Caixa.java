/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Financas.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Financas.Modelo.Caixa;

/**
 *
 * @author El Router
 */
public class ctr_Caixa {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    public ctr_Caixa(Connection con) {
        this.con = con;
    }

    public boolean save(Caixa model) {
        sql = "INSERT INTO caixa(descricao,tipo_caixa,operacao,saldo_atual,data_abertura,data_fecho,sitacao_atual,saldo_inicial,numero_conta,agencia,gerente,data_cadastro,status)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, model.getDescricao());
            ps.setInt(2, model.getTipo_caixa());
            ps.setString(3, model.getOperacao());
            ps.setDouble(4, model.getSaldo_atual());
            ps.setDate(5,(Date) model.getData_abertura());
            ps.setDate(6,(Date) model.getData_fecho());
            ps.setString(7,model.getSitacao_atual());
            ps.setDouble(8,model.getSaldo_inicial());
            ps.setString(9,model.getNumero_conta());
            ps.setString(10,model.getAgencia());
            ps.setString(11,model.getGerente());
            ps.setDate(12, (Date) model.getData_cadastro());
            ps.setInt(13,1);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public boolean update(Caixa model, int codigo) {

        sql = "UPDATE caixa SET descricao=?, tipo_caixa=?,operacao=?,saldo_inicial=?,numero_conta=?,agencia=?,gerente=? WHERE codigo=?";
        System.out.println("Sql:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, model.getDescricao());
            ps.setInt(2, model.getTipo_caixa());
            ps.setString(3, model.getOperacao());
            ps.setDouble(4,model.getSaldo_inicial());
            ps.setString(5,model.getNumero_conta());
            ps.setString(6,model.getAgencia());
            ps.setString(7,model.getGerente());
            ps.setInt(8,codigo);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return false;
    }
    public boolean updateStatus(int codigo,String status) {

        sql = "UPDATE caixa SET  sitacao_atual='"+status+"' WHERE codigo="+codigo;
        System.out.println("Sql:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return false;
    }
    public boolean updateSaldo(int op,double valor, int codigo) {
        if (op == 1) {//ENTRADA +
            sql = "UPDATE caixa SET saldo_atual=saldo_atual+ valor WHERE codigo=?";
        }else{
            sql = "UPDATE caixa SET saldo_atual=saldo_atual-valor WHERE codigo=?";
        }
        System.out.println("Sql:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,codigo);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public boolean delete(int Codigo) {
        sql = "UPDATE caixa SET status=2  WHERE codigo =" + Codigo+"";
        try {
            ps = con.prepareStatement(sql);
//            ps.setInt(1,2);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    public int getCodByNome(String nome) {

        sql = "SELECT codigo FROM  caixa WHERE descricao like '" + nome + "%'";
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
    public String getDescricaoSituacao(int Codigo) {
        sql = "SELECT sitacao_atual FROM caixa WHERE codigo =" + Codigo;
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
    public String getDataAbertura(int Codigo) {
        sql = "SELECT data_abertura FROM caixa WHERE codigo=" + Codigo;
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
    public String getOperacaoByCod(String caixa) {
        sql = "SELECT operacao FROM caixa WHERE descricao like '"+caixa+"'%";
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
    public String getTipoByDescricao(String descricao) {
        sql = "SELECT t.descricao FROM  caixa c inner join tipo_caixa t on c.tipo_caixa=t.codigo where c.descricao like '%"+descricao+"'%";
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
    public double getSaldoCaixa(String descricao) {
        sql = "SELECT saldo_atual FROM caixa where descricao like '%"+descricao+"'%";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public ArrayList<String> getDescricao() {
        ArrayList<String> list = new ArrayList<>();
        sql = "SELECT descricao AS Descricao FROM caixa";
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
    public ArrayList<String> getCaixaBanco() {
        ArrayList<String> list = new ArrayList<>();
        sql = "SELECT descricao AS Descricao FROM  caixa WHERE descricao like 'BANCO%' ";
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
    public ArrayList<String> getCaixaDinheiro() {
        ArrayList<String> list = new ArrayList<>();
        sql = "SELECT descricao AS Descricao FROM  caixa WHERE descricao like 'CAIXA %' ";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("descricao"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a MindVision:" + e);
        }
        return list;
    }
    public ArrayList<String> getDescricao2(String d) {
        ArrayList<String> list = new ArrayList<>();
        sql = "SELECT descricao AS Descricao FROM caixa where not descricao like '"+d+"' ";
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
    public ArrayList<Caixa> getAll(int order) {
        ArrayList<Caixa> list = new ArrayList<>();
        if (order == 0) {
            sql = "SELECT *FROM  caixa c  WHERE c.status = 1 order by 1";
        }else{
            sql = "SELECT *FROM caixa c  WHERE c.status = 1 order by " + order + "";
        }
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add( new Caixa(rs.getInt("codigo"), rs.getString("descricao"), rs.getInt("tipo_caixa"), rs.getString("operacao"),
                rs.getDouble("saldo_atual"),rs.getDate("data_abertura"),rs.getDate("data_fecho"),rs.getString("sitacao_atual"),rs.getDouble("saldo_inicial"), rs.getString("numero_conta"),
                rs.getString("agencia"), rs.getString("gerente"), rs.getDate("data_cadastro")));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }
    public ArrayList<Caixa> getAllCancelados() {
        ArrayList<Caixa> list = new ArrayList<>();
        sql = "SELECT *FROM  caixa c  WHERE c.status= 2";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add( new Caixa(rs.getInt("codigo"), rs.getString("descricao"), rs.getInt("tipo_caixa"), rs.getString("operacao"),
                rs.getDouble("saldo_atual"),rs.getDate("data_abertura"),rs.getDate("data_fecho"),rs.getString("sitacao_atual"),rs.getDouble("saldo_inicial"), rs.getString("numero_conta"),
                rs.getString("agencia"), rs.getString("gerente"), rs.getDate("data_cadastro")));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }
}
