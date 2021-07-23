/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.Utilizador;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerUsuario {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerUsuario(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvar(Utilizador utilizador) {
        //  conexao.Connectando();
        sql = "INSERT INTO utilizadores(nomeCompleto,Username,password, codigoTipoUtilizador,codigoStatus,morada,contacto,email,dataCadastro)values(?,?,md5(?),?,?,?,?,?,now())";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, utilizador.getNomeCompleto());
            ps.setString(2, utilizador.getUsername());
            ps.setString(3, utilizador.getSenha());
            ps.setInt(4, utilizador.getCodigoTipoUtilizador());
            ps.setInt(5, utilizador.getCodigoStatus());
            ps.setString(6, utilizador.getMorada());
            ps.setString(7, utilizador.getContacto());
            ps.setString(8, utilizador.getEmail());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //  conexao.DesconectaBanco();
    }

    public void editar(Utilizador utilizador, int codigo) {
        //  conexao.Connectando();
        sql = "UPDATE utilizadores SET nomeCompleto=?,Username=?,password =md5('" + utilizador.getSenha() + "'), codigoTipoUtilizador=?,codigoStatus=?,morada=?,contacto=?,email=? WHERE idUtilizador=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, utilizador.getNomeCompleto());
            ps.setString(2, utilizador.getUsername());
            //    ps.setString(3, utilizador.getSenha());
            ps.setInt(3, utilizador.getCodigoTipoUtilizador());
            ps.setInt(4, utilizador.getCodigoStatus());
            ps.setString(5, utilizador.getMorada());
            ps.setString(6, utilizador.getContacto());
            ps.setString(7, utilizador.getEmail());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados Alterado com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //  conexao.DesconectaBanco();
    }

    public void getAltualizarSenha(String senha, int codigo) {
        //conexao.Connectando();
        sql = " update utilizadores set password =md5('" + senha + "') where idUtilizador=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Senha alterado com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

    }
     public void getAltualizarSenhaData(String senha, int codigo) {
        //conexao.Connectando();
        sql = " update utilizadores set password =md5('" + senha + "'),dataCadastro='"+getDataOntem()+"' where idUtilizador=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Senha alterado com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

    }

    public ArrayList<String> getNomeCompleto() {
        //conexao.Connectando();
        sql = "SELECT * FROM utilizadores s where codigoStatus=1";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("nomeCompleto"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public String getNomeCompletoFactura(Integer codigo) {
        sql = "SELECT * FROM utilizadores s inner join factura f on s.idUtilizador =f.codigoUtilizador where f.idFactura=" + codigo;
        System.out.println("Utilizador:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nomeCompleto");
            }
        } catch (SQLException ex) {
        }
        return null;
    }
    public String getDataOntem() {
        sql = "SELECT DATE_ADD(DATE(CURRENT_TIMESTAMP), INTERVAL -1 DAY) as Data";
        System.out.println("Utilizador:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Data");
            }
        } catch (SQLException ex) {
        }
        return null;
    }

//    public ArrayList<String> getNomeCompletoFactura(int codigo) {
//        //conexao.Connectando();
//        sql = "SELECT * FROM utilizadores s inner join factura f on s.idUtilizador =f.codigoUtilizador where f.idFactura=" + codigo;
//        System.out.println("sql:" + sql);
//        ArrayList<String> lista = new ArrayList<>();
//        try {
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                lista.add(rs.getString("nomeCompleto"));
//            }
//        } catch (SQLException ex) {
//        }
//        return lista;
//    }
    public ArrayList<String> getUsernameRecepcao() {
        //  conexao.Connectando();
        sql = "SELECT UPPER(username) as Nome FROM utilizadores s where codigoStatus= 1 and codigoTipoUtilizador=4";
        System.out.println("Sql:" + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            // ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("Nome"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getTodos() {
        //  conexao.Connectando();
        sql = "SELECT UPPER(username) as Nome FROM utilizadores s where codigoStatus= 1";
        System.out.println("Sql:" + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            // ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("Nome"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

     public ArrayList<String> getUm(String nome) {
        //  conexao.Connectando();
        sql = "SELECT UPPER(username) as Nome FROM utilizadores s where username='"+nome+"'";
        System.out.println("Sql:" + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("Nome"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }
    public String getNome(String nome) {
        sql = "SELECT nomeCompleto FROM utilizadores s where username='" + nome + "'";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nomeCompleto");
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public ArrayList<String> getNomeporCodigo(int codigo) {
        conexao.Connectando();
        sql = "SELECT nomeCompleto FROM utilizadores s where idUtilizador=" + codigo;
        System.out.println("Nome do Utilizador: " + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("nomeCompleto"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getNomeporCodigoMedico(int codigo) {
        //   conexao.Connectando();
        sql = "SELECT nomeCompleto FROM utilizadores s where codigoTipoUtilizador =1 AND idUtilizador=" + codigo;
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("nomeCompleto"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getNomeMedico() {
        //     conexao.Connectando();
        sql = "SELECT * FROM medicos s";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("nomeCompleto"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public void eliminar(int codigo) {
        //   conexao.Connectando();
        sql = "update utilizadores set codigoStatus=2 where idUtilizador=" + codigo;
        System.out.println("Teste" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            //     JOptionPane.showMessageDialog(null, "Dados eliminado com Sucesso");

        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
    }

    public void eliminarMedico(int codigo) {
        //   conexao.Connectando();
        sql = "update medicos set codigoStatus=2 where tipoUtilizador=" + codigo;
        System.out.println("Teste" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            //    JOptionPane.showMessageDialog(null, "Dados eliminado com Sucesso");

        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
    }

    public int getCodigoUtilizador(String designacao) {
        //   conexao.Connectando();
        sql = "SELECT idUtilizador from utilizadores where nomeCompleto ='"+designacao+"'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idUtilizador");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public int getCodigoUtilizadorUsername(String designacao) {
        // conexao.Connectando();
        sql = "SELECT idUtilizador from utilizadores where username ='" + designacao + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idUtilizador");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public int getTipoUtilizador(String designacao) {
        //      conexao.Connectando();
        sql = "SELECT codigoTipoUtilizador from utilizadores where username ='" + designacao + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("codigoTipoUtilizador");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public String getDataCadastro(String designacao) {
        //      conexao.Connectando();
        sql = "SELECT date(dataCadastro) as dataCadastro from utilizadores where username ='" + designacao + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("dataCadastro");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return null;
    }

    public int getTipoUtilizadorporID(int designacao) {
        //      conexao.Connectando();
        sql = "SELECT codigoTipoUtilizador from utilizadores where idUtilizador ='" + designacao + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("codigoTipoUtilizador");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public int getCodigoMedico(String designacao) {
        // conexao.Connectando();
        sql = "SELECT idMedico from medicos where nomeCompleto ='" + designacao + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idMedico");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public int getLastUtilizador() {
        try {
            //       conexao.Connectando();
            sql = "select max(idUtilizador) as max from utilizadores";
            System.out.println("Teste:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            try {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return 0;
    }

    public boolean login(String user, String pass) {
        //   conexao.Connectando();
        sql = "SELECT idUtilizador from utilizadores u where u.username ='" + user + "' and u.password=md5('" + pass + "') and codigoStatus=1";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {

            return false;
        }
        return false;
    }
}
