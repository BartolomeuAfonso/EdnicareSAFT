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
import CLINICA.modelo.Utente;
import java.sql.Connection;
import java.sql.Date;
import javax.swing.JOptionPane;
import sf.ce.utilizacoes.Data;

/**
 *
 * @author Desenvolvimento
 */
public class ControllerUtente {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerUtente(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void Inserir(Utente utente) {
//       // conexao.Connectando();
        sql = "INSERT INTO pacientes(nomeCompleto,morada,contacto,nomePai,nomeMae,codigoNaturalidade,telefone,bilheteIdentidade,genero,apn,cartaoPS,codigoSeguro,estadocivil,dataNascimento,nif,email)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, utente.getNomeCompleto());
            ps.setString(2, utente.getMorada());
            ps.setString(3, utente.getContacto());
            ps.setString(4, utente.getNomePai());
            ps.setString(5, utente.getNomeMae());
            ps.setInt(6, utente.getCodigoNaturalidade());
            ps.setString(7, utente.getTelefone());
            ps.setString(8, utente.getBI());
            ps.setString(9, utente.getGenero());
            ps.setString(10, utente.getApn());
            ps.setString(11, utente.getTomadorSegurado());
            ps.setInt(12, utente.getEmpresa());
            ps.setString(13, utente.getEstado_civil());
            ps.setDate(14, (Date) utente.getNascimento());
            ps.setString(15, utente.getBI());
            ps.setString(16, utente.getEmail());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Paciente cadastro com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        // conexao.DesconectaBanco();
    }

    public void editar(Utente utente, int codigo) {
        //conexao.Connectando();
        sql = "update pacientes set nomeCompleto=?,morada=?,contacto=?,nomePai=?,nomeMae=?,codigoNaturalidade=?,telefone=?,bilheteIdentidade=?,genero=?,apn=?,cartaoPS=?,codigoSeguro=?,estadocivil=?,dataNascimento=?,email=? WHERE idPaciente=" + codigo;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, utente.getNomeCompleto());
            ps.setString(2, utente.getMorada());
            ps.setString(3, utente.getContacto());
            ps.setString(4, utente.getNomePai());
            ps.setString(5, utente.getNomeMae());
            ps.setInt(6, utente.getCodigoNaturalidade());
            ps.setString(7, utente.getTelefone());
            ps.setString(8, utente.getBI());
            ps.setString(9, utente.getGenero());
            ps.setString(10, utente.getApn());
            ps.setString(11, utente.getTomadorSegurado());
            ps.setInt(12, utente.getEmpresa());
            ps.setString(13, utente.getEstado_civil());
            ps.setDate(14, (Date) utente.getNascimento());
            ps.setString(15, utente.getEmail());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados do paciente alterado com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //  conexao.DesconectaBanco();
    }

    public boolean existe(String nome, Date dataNascimento, int codigoEmpresa) {
        // conexao.Connectando();
        sql = "SELECT idPaciente FROM pacientes WHERE nomeCompleto = '" + nome + "' and dataNascimento = '" + dataNascimento + "'  and codigoSeguro=" + codigoEmpresa;
        System.out.println("Teste:" + dataNascimento);
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

    public boolean pacienteSegurado(String nome) {
        // conexao.Connectando();
        sql = "SELECT idPaciente FROM pacientes WHERE nomeCompleto = '" + nome + "' and codigoSeguro<>8";
        System.out.println("Paaciente Segurado:" + sql);
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

    public ArrayList<String> getSexo1(String codigo) {
        // conexao.Connectando();
        sql = "SELECT genero FROM pacientes WHERE nomeCompleto = '" + codigo + "'";
        System.out.println("sql:" + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("genero"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public void eliminar(int codigo) {
        //    conexao.Connectando();
        sql = "update pacientes set codigoStatus=2 where idPaciente=" + codigo;
        System.out.println("Teste" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados eliminado com Sucesso");

        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
    }

    public ArrayList<String> getNomeBenefiario() {
        //    conexao.Connectando();
        sql = "SELECT idPaciente, UPPER(nomeCompleto) AS nomeCompleto FROM pacientes where codigoStatus=1 order by 1 desc limit 50";
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

    public ArrayList<String> getNomeBenefiarioSeguradora(int codigo) {
        //     conexao.Connectando();
        sql = "SELECT idPaciente, UPPER(p.nomecompleto) AS nomeCompleto FROM pacientes p where p.codigoSeguro=" + codigo + " order by 1 desc limit 50";
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

    public ArrayList<String> getNome_Segura(int codigo, String nome) {
        //     conexao.Connectando();
        sql = "SELECT UPPER(p.nomecompleto) AS nomeCompleto FROM pacientes p\n"
                + "where nomeCompleto ='" + nome + "' AND p.codigoSeguro=" + codigo;
        System.out.println("Nome e ocdigo da Emprea:" + sql);
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

    public ArrayList<String> getNomeBenefiarioUltimo() {
        //   conexao.Connectando();
        sql = "SELECT idPaciente,UPPER(nomecompleto) FROM pacientes p where nomeCompleto <> 'Diversos' and codigoStatus=1 order by 1 desc limit 50";
        System.out.println("Lista:" + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("p.nomeCompleto"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getNomeBenefiarioUltimoParticular() {
        //  conexao.Connectando();
        sql = "SELECT idPaciente, UPPER(nomecompleto) AS nomeCompleto FROM pacientes p where codigoStatus =1 order by 1 desc limit 50 "; //where p.codigoSeguro <>8 and nomeCompleto <> 'Diversos' order by 1 desc";
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

    public ArrayList<String> getPacienteDiveros() {
        //    conexao.Connectando();
        sql = "SELECT UPPER(nomecompleto) AS nomeCompleto FROM pacientes p where p.codigoSeguro=8 and nomeCompleto = 'Diversos' order by 1 desc";
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

    public ArrayList<String> getNomeBenefiarioSegurados() {
        // conexao.Connectando();
        sql = "SELECT idPaciente, UPPER(nomecompleto) AS nomeCompleto FROM pacientes p where p.codigoSeguro <>8  and codigoStatus =1 order by 1 desc limit 50";
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

    public ArrayList<String> getNomePorLike(String nome) {
//        conexao.Connectando();
        sql = "SELECT UPPER (nomeCompleto) AS nomeCompleto FROM pacientes p where nomecompleto like  '" + nome + "%' order by 2 ";
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

    public ArrayList<String> getNomeBenefiarioHoje() {
//        conexao.Connectando();
        sql = "SELECT UPPER(nomeCompleto) AS nomeCompleto FROM pacientes p\n"
                + "inner join pedidoproduto i\n"
                + "on p.idPaciente=i.codigoCliente\n"
                + "WHERE i.dataPedido=CURRENT_DATE";
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

    public ArrayList<String> getNomeBenefiarioBuscar(String nome) {
//        conexao.Connectando();
        sql = "SELECT UPPER(nomeCompleto) AS nomeCompleto FROM pacientes p\n"
                + "WHERE nomeCompleto LIKE '" + nome + "%'";
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

    public ArrayList<String> getNomeBenefiarioBuscarPorInternar(String nome) {
//        conexao.Connectando();
        sql = "SELECT p.nomeCompleto as nome FROM historicoclinico h inner join pacientes p on h.codigoUtente =p.idPaciente\n"
                + "where p.nomeCompleto like '" + nome + "%' AND date(h.dataAtendimento) = current_Date";

        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("nome"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getNomeBenefiarioBuscarSeguro(String nome, int codigo) {
//        conexao.Connectando();
        sql = "SELECT UPPER(nomeCompleto) AS nomeCompleto FROM pacientes p\n"
                + "WHERE nomeCompleto LIKE '" + nome + "%' AND p.codigoSeguro=" + codigo;
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

    public ArrayList<String> getNome(String nome) {
//        conexao.Connectando();
        sql = "SELECT UPPER(nomeCompleto) AS nomeCompleto FROM pacientes p WHERE nomeCompleto LIKE '" + nome + "%'";
        System.out.println("Teste:" + sql);
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

    public ArrayList<String> getCodigo(int codigo) {
//        conexao.Connectando();
        sql = "SELECT UPPER(nomeCompleto) AS nomeCompleto FROM pacientes p WHERE idPaciente=" + codigo;
        System.out.println("Teste:" + sql);
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

    public ArrayList<String> getCodigoNomeSeguro(int codigo, int codigoSeguro) {
//        conexao.Connectando();
        sql = "SELECT UPPER(nomeCompleto) AS nomeCompleto FROM pacientes p WHERE idPaciente=" + codigo + " AND p.codigoSeguro=" + codigo;
        System.out.println("Teste:" + sql);
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

    public int getCodigoUtente(String designacao) {
        sql = "SELECT idPaciente from pacientes where nomeCompleto ='" + designacao + "'";
        System.out.println("Codigo:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idPaciente");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public int getCodigoUtente1(String designacao, int codigo) {
        sql = "SELECT idPaciente from pacientes where nomeCompleto ='" + designacao + "' and codigoSeguro=" + codigo;
        System.out.println("Codigo:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idPaciente");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
//    public String getEmailPaciente(String designacao) {
//        sql = "SELECT idPaciente from pacientes where nomeCompleto ='" + designacao + "'";
//        System.out.println("Codigo:" + sql);
//        try {
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                return rs.getInt("idPaciente");
//            }
//        } catch (SQLException e) {
//            System.out.println("ERRO:" + e);
//        }
//        return 0;
//    }

    public int getIdade(String designacao) {
//        conexao.Connectando();
        sql = "SELECT year(current_date), year(dataNascimento), year(current_date)-year(dataNascimento) as idade  from pacientes where nomeCompleto ='" + designacao + "'";
        System.out.println("Codigo:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public int getCodigoSeguro(int codigo) {
//        conexao.Connectando();
        sql = "SELECT codigoSeguro from pacientes where idPaciente =" + codigo;
        System.out.println("Codigo:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("codigoSeguro");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public ArrayList<String> getGenero(String codigo) {
//        conexao.Connectando();
        sql = "SELECT genero from pacientes where nomeCompleto ='" + codigo + "'";
        System.out.println("sql:" + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("genero"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public String getSexo(String codigo) {
//        conexao.Connectando();
        sql = "SELECT genero from pacientes where nomeCompleto ='" + codigo + "'";
        System.out.println("sql:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("genero");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return null;
    }

    public String getNif(int codigo) {
//        conexao.Connectando();
        sql = "SELECT nif from pacientes where idPaciente =" + codigo;
        System.out.println("sql:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("nif");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return null;
    }

    public Date getData(String designacao) {
//        conexao.Connectando();
        sql = "SELECT dataNascimento from pacientes where nomeCompleto ='" + designacao + "'";
        System.out.println("Data de Nascimento:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDate("dataNascimento");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return null;
    }

    public double getDescontoUtente(String designacao) {
//        conexao.Connectando();
        sql = "SELECT\n"
                + "     tipoutente.`desconto` AS desconto,\n"
                + "     pacientes.`nomeCompleto` AS pacientes_nomeCompleto\n"
                + "FROM\n"
                + "     `tipoutente` tipoutente INNER JOIN `pacientes` pacientes ON tipoutente.`idparente` = pacientes.`tipoUtente` where nomeCompleto ='" + designacao.trim() + "'";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble("desconto");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public Utente getDadosCliente(String nome) {
//        conexao.Connectando();
        sql = "select * from pacientes where nomeCompleto = '" + nome + "'";
        System.out.println("");
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            Utente clientes;
            if (rs.next()) {
                clientes = new Utente(rs.getString("nomeCompleto"), rs.getString("nomePai"), rs.getString("nomeMae"), rs.getString("contacto"), rs.getString("telefone"), rs.getString("bilheteIdentidade"), rs.getString("cartaoPS"), rs.getString("apn"), rs.getString("morada"), rs.getDate("dataNascimento"),rs.getString("email"));
                return clientes;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Utente getDadosClientes(String nome) {
//        conexao.Connectando();
        sql = "select * from pacientes where nomeCompleto = '" + nome + "'";
        System.out.println("");
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            Utente clientes;
            if (rs.next()) {
                clientes = new Utente(rs.getString("nomeCompleto"), rs.getString("nomePai"), rs.getString("nomeMae"), rs.getString("contacto"), rs.getString("telefone"), rs.getString("bilheteIdentidade"), rs.getString("cartaoPS"), rs.getString("apn"), rs.getString("morada"),rs.getDate("dataNascimento"),rs.getString("email"));
                return clientes;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String verificarClienteFacturado(int codigo) {
        sql = "SELECT DISTINCT  p.bilheteIdentidade AS BI FROM pacientes p INNER JOIN factura f ON f.codigoCliente= p.idPaciente WHERE p.idPaciente=" + codigo;
        System.out.println("SQL:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("BI");
            }
        } catch (SQLException ex) {

            return "";
        }
        return "";
    }
}
