/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.MarcacaoConsulta;
import CLINICA.modelo.AgendaMedica;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerMarcarcaoConsulta {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;

    Connection con;

    public ControllerMarcarcaoConsulta(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvar(MarcacaoConsulta marcacaoConsulta) {

        // conexao.Connectando();
        sql = "INSERT INTO marcacaoconsulta(codigoPaciente,codigoMedico,codigoUtilizador,codigoServico,dataAgenda,dataAtendimento,observacao,hora,preco,estado,codigoEstado)values(?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, marcacaoConsulta.getCodigoPaciente());
            ps.setInt(2, marcacaoConsulta.getCodigoMedico());
            ps.setInt(3, marcacaoConsulta.getCodigoUtlizador());
            ps.setInt(4, marcacaoConsulta.getCodigoServico());
            ps.setString(5, marcacaoConsulta.getDataMarcacao());
            ps.setDate(6, (java.sql.Date) (Date) marcacaoConsulta.getDataAtendimento());
            ps.setString(7, marcacaoConsulta.getObservacao());
            ps.setString(8, marcacaoConsulta.getHora());
            ps.setDouble(9, marcacaoConsulta.getPreco());
            ps.setString(10, marcacaoConsulta.getEstado());
            ps.setInt(11, marcacaoConsulta.getCodigoEstado());
            ps.execute();
            //        JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //   conexao.DesconectaBanco();
    }

    public void salvarRapida(MarcacaoConsulta marcacaoConsulta) {
        //  conexao.Connectando();//dataAtendimento
        sql = "INSERT INTO marcacaoconsulta(codigoPaciente,codigoMedico,codigoUtilizador,codigoServico,dataAgenda,dataAtendimento,observacao,preco,estado,codigoEstado,pago)values(?,?,?,?,now(),now(),?,?,?,?,?)";
        System.out.println("Marcação Rápida:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, marcacaoConsulta.getCodigoPaciente());
            ps.setInt(2, marcacaoConsulta.getCodigoMedico());
            ps.setInt(3, marcacaoConsulta.getCodigoUtlizador());
            ps.setInt(4, marcacaoConsulta.getCodigoServico());
            ps.setString(5, marcacaoConsulta.getObservacao());
            ps.setDouble(6, marcacaoConsulta.getPreco());
            ps.setString(7, marcacaoConsulta.getEstado());
            ps.setInt(8, marcacaoConsulta.getCodigoEstado());
            ps.setString(9, marcacaoConsulta.getPago());
            ps.execute();
            //   JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //   conexao.DesconectaBanco();
    }

    public void eliminar(int codigo) {
        //   conexao.Connectando();
        sql = "DELETE FROM marcacaoconsulta WHERE idMarcacao=" + codigo;
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
    }

    public ArrayList<String> getPacienteEsperaPorMedico(int codigo) {
        //   conexao.Connectando();
        sql = "SELECT p.nomeCompleto as Nome from marcacaoconsulta m inner join medicos md on m.codigomedico=md.idMedico\n"
                + "inner join pacientes p on m.codigoPaciente=p.idPaciente\n"
                + "WHERE m.dataAtendimento=Current_date and m.Atendido='NAO'and md.idMedico=" + codigo;
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

    public void actualizarStatusMarcacao(int codigoCliente, Date data) {
        // conexao.Connectando();
        String sql = "update marcacaoconsulta set Atendido ='SIM' where dataAtendimento='" + data + "' AND codigoPaciente=" + codigoCliente;
        System.out.println("SQL:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public int getLastCodigoByPacienteToday(int codigoPaciente, String data) {
        // conexao.Connectando();
        sql = "select max(idMarcacao) as max from marcacaoconsulta where codigoPaciente = " + codigoPaciente + " And dataAtendimento = '" + data + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("max");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public boolean consultaExistente(int paciente, int medico, int consulta, String data) {
        //  conexao.Connectando();
        sql = "SELECT idMarcacao FROM marcacaoconsulta where codigoPaciente =" + paciente + " and codigoMedico =" + medico + " and codigoServico=" + consulta + " and date(dataAtendimento)='" + data + "'";
        System.out.println("Teste:" + sql);
//        ResultSet rs = conexao.executeQuery(sql);

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {

        }
        return false;
    }

    public int getTotalConsultasAgendadasPorMedico(int medico, String data) {
        //    conexao.Connectando();
        sql = "select count(idMarcacao) as total from marcacaoconsulta where codigoMedico = " + medico + " And date(dataAtendimento) = '" + data + "'";
        //  System.out.println("Teste:" + sql);

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public String getPacienteUrgente() {
        //    conexao.Connectando();
        sql = "SELECT m.codigoPaciente, p.nomeCompleto as nome,m.estado FROM marcacaoconsulta m inner join estadodoente e\n"
                + "on m.codigoEstado = e.idestadodoente\n"
                + "inner join pacientes p on m.codigoPaciente = p.idPaciente\n"
                + "where date(m.dataAtendimento)=current_date\n"
                + "and m.estado ='Emergência (Vermelho) - Atendiento Imediato.' OR m.estado ='Muito Urgente (Laranja) - Atendimento em até 10 min.'"
                + "AND m.Atendido='NAO'";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("nome");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int getLimiteDiario(int medico) {
        //  conexao.Connectando();
        sql = "select limite from Medicos where idMedico = " + medico;
        //  System.out.println("Tste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("limite");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    /// Agenda Médica do Doctor
    // Para melhorar o desenvolvimento da ideia
    AgendaMedica agendaMedica = new AgendaMedica();

    public void salvarAgenda(AgendaMedica agendaMedica) {
        //  conexao.Connectando();
        sql = "INSERT INTO agendamedica(dias,mes,codigoMedico,horaEntrada,horaSaida)values(?,?,?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, agendaMedica.getDia());
            ps.setString(2, agendaMedica.getDataAgenda());
            ps.setInt(3, agendaMedica.getCodigoMedico());
            ps.setString(4, agendaMedica.getHoraEntrada());
            ps.setString(5, agendaMedica.getHoraSaida());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Agenda salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //  conexao.DesconectaBanco();
    }

    public ArrayList<String> getNomeCids() {
        // conexao.Connectando();
        sql = "SELECT * FROM cids s";
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("s.designacao"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public void editar(String codigoMarcacao, int codigoMedico) {

//        conexao.Connectando();
        sql = "UPDATE marcacaoconsulta SET codigoMedico=" + codigoMedico + " WHERE idMarcacao=" + codigoMarcacao;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados Alterado com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //  conexao.DesconectaBanco();
    }

    public void getRemarcarcao(String codigoMarcacao, int codigoMedico, Date data) {
        sql = "UPDATE marcacaoconsulta SET codigoMedico=" + codigoMedico + ", dataAtendimento ='" + data + "' WHERE idMarcacao=" + codigoMarcacao;
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados Alterado com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
    }
}
