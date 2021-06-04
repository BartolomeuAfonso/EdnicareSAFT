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
import CLINICA.modelo.Triagem;
import java.sql.Connection;
import java.sql.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerTriagem {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;

    Connection con;

    public ControllerTriagem(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvar(Triagem triagem) {
       // conexao.Connectando();
        sql = "INSERT INTO triagem(codigoPaciente,codigoUtilizador,dataCadastro,peso,tensao,temperatura,frequenciaCardiaca,altura,IMC,pulso,codigoMedico,fuma,bebe)values(?,?,now(),?,?,?,?,?,?,?,?,?,?)";
        System.out.println("TESTE:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, triagem.getCodigoPaciente());
            ps.setInt(2, triagem.getCodigoUtilizador());
            //   ps.setDate(3, (Date) triagem.getDataCadastro());
            ps.setString(3, triagem.getPeso());
            ps.setString(4, triagem.getTensao());
            ps.setString(5, triagem.getTemperatura());
            ps.setString(6, triagem.getFrequenciaCardiaca());
            ps.setString(7, triagem.getAltura());
            ps.setDouble(8, triagem.getIMC());
            ps.setString(9, triagem.getPulso());
            ps.setInt(10, triagem.getCodigoMedico());
            //   ps.setString(12, triagem.getGuiaFactura());
            ps.setString(11, triagem.getFumo());
            ps.setString(12, triagem.getBebe());
            ps.execute();
            //   JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
      //  conexao.DesconectaBanco();
    }

    public void editar(Triagem triagem, int codigo) {
       // conexao.Connectando();
        sql = "UPDATE triagem SET codigoPaciente=?,peso=?,tensao=?,temperatura=?,frequenciaCardiaca=?,altura=?,IMC=?,pulso=?,codigoMedico=? WHERE idTriagem=" + codigo;
        System.out.println("TESTE:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, triagem.getCodigoPaciente());
            ps.setString(2, triagem.getPeso());
            ps.setString(3, triagem.getTensao());
            ps.setString(4, triagem.getTemperatura());
            ps.setString(5, triagem.getFrequenciaCardiaca());
            ps.setString(6, triagem.getAltura());
            ps.setDouble(7, triagem.getIMC());
            ps.setString(8, triagem.getPulso());
            ps.setInt(9, triagem.getCodigoMedico());
            ps.execute();
            //   JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
       // conexao.DesconectaBanco();
    }

    public ArrayList<String> getPacienteEspera() {
     //   conexao.Connectando();
        sql = "SELECT f.nomeclientes as Nome from factura f inner join factura_itens itens on f.idFactura=itens.codigoFactura\n"
                + "inner join servicos s on s.idServico=itens.codigoProduto\n"
                + "WHERE Datafactura =current_date and s.codigoCategoria=2 AND f.Triagem='NAO'";
        System.out.println("Teste:" + sql);
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

    public ArrayList<String> PacienteEspera() {
   //     conexao.Connectando();
        sql = "SELECT distinct p.nomecompleto FROM marcacaoconsulta m\n"
                + "inner join pacientes p on m.codigoPAciente = p.idPaciente\n"
                + "where m.dataAtendimento=current_date and Atendido='Nao'";
        System.out.println("Teste:" + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("p.nomecompleto"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getPacienteEsperaPorMedico(int codigo) {
      //  conexao.Connectando();
        sql = "SELECT p.nomeCompleto as Nome from triagem t inner join pacientes p on t.codigoPaciente =p.idPaciente\n"
                + "inner join medicos m on t.codigoMedico = m.idMedico\n"
                + "where t.dataCadastro=current_date() and t.Atendido='NAO' AND m.idMedico=" + codigo;
        //
        System.out.println("Teste:" + sql);
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

    public void actualizarStatusTriagem(int codigo) {
      //  conexao.Connectando();
        sql = "update triagem set Atendido='SIM' where idtriagem=" + codigo;
        //  System.out.println("Teste" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            //  JOptionPane.showMessageDialog(null, "Paciente Atendido com Sucesso");

        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
    }
    public String getNomeServicoConsulta(String nome) {
      //  conexao.Connectando();
        sql = "SELECT DISTINCT s.designacao as servico FROM servicos s inner join marcacaoconsulta m on s.idServico = m.codigoServico\n"
                + "inner join pacientes p on p.idPaciente = m.codigoPaciente\n"
                + "inner join categoriaservico c on s.codigoCategoria=c.idcategoriaservico\n"
                + "where m.dataAtendimento = current_date and p.nomecompleto ='" + nome + "' and s.codigoCategoria=1";
        System.out.println("Teste:" + sql);

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("servico");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public String getNomeServico(String nome) {
      //  conexao.Connectando();
        sql = "SELECT s.designacao as servico FROM servicos s inner join marcacaoconsulta m on s.idServico = m.codigoServico\n"
                + "inner join pacientes p on p.idPaciente = m.codigoPaciente\n"
                + "inner join categoriaservico c on s.codigoCategoria=c.idcategoriaservico\n"
                + "where m.dataAtendimento = current_date and p.nomecompleto ='" + nome + "' and s.codigoCategoria=2";
        System.out.println("Teste:" + sql);

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("servico");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public String getNomeSeguraora(String nome) {
     //   conexao.Connectando();
        sql = "SELECT e.designacao AS empresa from empresaseguros e inner join pacientes p on e.idSeguros=p.codigoseguro\n"
                + "where p.nomecompleto ='" + nome + "';";
        System.out.println("Teste:" + sql);

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("empresa");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }
//    public String getNomeServico(String nome) {
//        conexao.Connectando();
//        sql = "SELECT s.designacao as servico from factura f inner join factura_itens itens on f.idFactura=itens.codigoFactura\n"
//                + "inner join servicos s on s.idServico=itens.codigoProduto\n"
//                + "where f.Datafactura =current_date and f.nomeClientes = '" + nome + "' and s.codigoCategoria=2";
//        System.out.println("Teste:" + sql);
//
//        try {
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                return rs.getString("servico");
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return "";
//    }

    public int getCodigoPaciente(int codigo) {
    //    conexao.Connectando();
        sql = "select codigoPaciente from triagem where idtriagem = " + codigo;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigoPaciente");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getLastCodigoByPacienteToday(int codigo, String data) {
      //  conexao.Connectando();
        sql = "select max(Codigo) as max from triagem where CodigoPaciente = " + codigo + " And date(dataCadastro) = '" + data + "'";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("max");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public void actualizarStatus(int codigoCliente) {
     //   conexao.Connectando();
        String sql = "update factura set Triagem ='SIM' where codigoCliente =" + codigoCliente;
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public int getLastCodigo() {
   //     conexao.Connectando();
        sql = "select max(idtriagem) as max from triagem";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("max");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getLastCodigoByPaciente(int codigoPaciente) {
     //   conexao.Connectando();
        sql = "select max(idtriagem) as max from triagem where CodigoPaciente = " + codigoPaciente;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("max");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public Triagem getDadosTriagem(int codigoPaciente) {
     //   conexao.Connectando();
        sql = "SELECT * FROM triagem t where t.dataCadastro = CURRENT_DATE AND t.codigoPaciente=" + codigoPaciente;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            Triagem pacienteTriagem;
            if (rs.next()) {
                pacienteTriagem = new Triagem(rs.getInt("idtriagem"), rs.getString("peso"), rs.getString("altura"), rs.getString("tensao"), rs.getString("fuma"), rs.getString("bebe"), rs.getString("temperatura"), rs.getString("pulso"), rs.getDouble("IMC"));
                return pacienteTriagem;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
