/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import CLINICA.modelo.ExamesPorFazerItem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.ExamesporFazer;
import CLINICA.modelo.ResultadoExame;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Probook
 */
public class ControllerExamesporFazer {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerExamesporFazer(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void create(ExamesporFazer e) {
        //  conexao.Connectando();
        sql = "insert into examesporfazer(codigoPaciente,CodigoUtilizador,hora,dataPedido,quantidade,codigoStatus,codigoMedico,colaborador)values(?,?,LOCALTIME(),?,?,?,?,?)";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, e.getCodigoPaciente());
            ps.setInt(2, e.getCodigoUtilizador());
            ps.setString(3, e.getDataPedido());
            ps.setInt(4, e.getQuantidade());
            ps.setInt(5, 4);
            ps.setInt(6, 1);
            ps.setString(7, e.getColaborador());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
        }
        //    conexao.DesconectaBanco();

    }

    public int codigoExameRelatorio(int codigoExame) {
        // conexao.Connectando();
        sql = "SELECT codigoExamesIntegrado as codigo from  examesporfazeritems e where codigoExames =" + codigoExame + " and codigoExamesIntegrado=0";
        System.out.println("Consulta:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

        return 0;
    }

    public void createMedico(ExamesporFazer e) {
        //  conexao.Connectando();
        sql = "insert into examesporfazer(codigoHistorico,codigoPaciente,CodigoUtilizador, dataPedido,codigoMedico,pacienteInterno,hora,quantidade,prescricao) values(?,?,?,?,?,?,LOCALTIME(),?,?)";
        System.out.println("sql:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, e.getCodigoHistorico());
            ps.setInt(2, e.getCodigoPaciente());
            ps.setInt(3, e.getCodigoUtilizador());
            ps.setString(4, e.getDataPedido());
            ps.setInt(5, e.getCodigoMedico());
            ps.setString(6, e.getPacienteInterno());
            ps.setInt(7, e.getQuantidade());
            ps.setString(8, e.getPrescricao());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
        }

    }

    public int getLastInsert() {
        //   conexao.Connectando();
        sql = "select max(idexamesPorFazer) as last from examesporfazer";
        System.out.println("Ultimo exame lançado:" + sql);
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

    public int getCodigoExames(Date data, int codigoPaciente) {
        //  conexao.Connectando();
        sql = "select idexamesPorFazer as last from examesporfazer where dataPedido='" + data + "' and codigoPaciente=" + codigoPaciente;
        System.out.println("Ultimo exame lançado:" + sql);
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

    public int getCodigoExamesPor(String data, int codigoPaciente) {
        //  conexao.Connectando();
        sql = "select idexamesPorFazer as last from examesporfazer where dataPedido='" + data + "' and codigoPaciente=" + codigoPaciente;
        //    System.out.println("Ultimo exame lançado:" + sql);
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

    public void updatePago(int codigo) {
        sql = "UPDATE examesporfazer SET codigoStatus =4 WHERE idexamesPorFazer=" + codigo;
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Exames Pago com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
    }

    public void getNomedoTecnico(String codigo, String codigoExame) {
        sql = "UPDATE examesporfazer SET tecnico ='" + codigo + "' WHERE idexamesPorFazer=" + codigoExame;
        System.out.println("Nome do Tecnico:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
    }

    public String getDataPedido(int codigo) {
        // conexao.Connectando();
        sql = "select dataPedido from examesporfazer where idexamesPorFazer =" + codigo;
        System.out.println("Ultimo exame lançado:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("dataPedido");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public int getCodigoExameIntegrado(int codigo) {
        conexao.Connectando();
        sql = "SELECT codigoExamesIntegrado FROM examesporfazeritems p inner join examesporfazer ex on ex.idexamesPorFazer = p.codigoExames\n"
                + "where codigoExamesIntegrado <>0 and p.codigoExames=" + codigo;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getCodigoStatusExames(String designacao) {
        // conexao.Connectando();
        sql = "SELECT idstatus_exames FROM status_exames where designacao ='" + designacao + "'";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("idstatus_exames");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int codigoItem(String produto, String dataPedido, int codigoPaciente, int codigoTriagem) {
        //conexao.Connectando();
        sql = "SELECT * FROM examesporfazeritems p inner join servicos s on p.codigoServico=s.idServico\n"
                + "inner join examesporfazer ex on ex.idexamesPorFazer = p.codigoExames\n"
                + "inner join historicoClinico h on ex.codigoHistorico = h.idhistoricoClinico\n"
                + "inner join pacientes pa on ex.codigoPaciente = pa.idPaciente\n"
                + "where s.designacao ='" + produto + "' and ex.codigoHistorico =" + codigoTriagem + " and ex.codigoPaciente=" + codigoPaciente + " and ex.dataPedido='" + dataPedido + "'";

        System.out.println("dataPedido " + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            ex.getMessage();
        }

        return 0;

    }

    public  ArrayList<String> getCodigo(int codigoServico) {
      //  conexao.Connectando();
        sql = "SELECT examesintegrado.designacao FROM examesintegrado where codigoServico="+codigoServico;
        System.out.println("Sql:" + sql);
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
     public  ArrayList<String> getCodigoporCategoria_exames(int codigoServico,int codigoProduto) {
      //  conexao.Connectando();
        sql = "SELECT examesintegrado.designacao FROM examesintegrado where codigocategoria="+codigoServico+" AND codigoServico="+codigoProduto;
        System.out.println("Sql:" + sql);
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
    public ArrayList<String> getCodigoporProduto(String designacao, int codigoServico) {
        //  conexao.Connectando();
        sql = "SELECT examesintegrado.designacao FROM examesintegrado where examesintegrado.designacao LIKE '%"+designacao+"%' AND codigoServico=" + codigoServico;
        System.out.println("Sql:" + sql);
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

    public List<ExamesPorFazerItem> getResultadosProdutosItem(int codigoServico, String dataPedido, int codigoPaciente) {
        //conexao.Connectando();
        sql = "SELECT DISTINCT s.designacao,ex.resultado,p.dataPedido,ex.dataResultado,e1.referencia FROM examesintegrado e inner join servicos s on e.codigoServico=s.idServico\n"
                + "inner join examesporfazeritems ex on ex.codigoProduto = s.idServico\n"
                + "inner join examesintegrado e1 on e1.codigoServico=s.idServico\n"
                + "inner join examesporfazer p on ex.codigoExames = p.idexamesPorFazer\n"
                + "inner join pacientes pa on p.codigoPaciente = pa.idPaciente\n"
                + "where s.idServico=" + codigoServico + " and p.dataPedido='" + dataPedido + "' and pa.idPaciente =" + codigoPaciente;
        System.out.println("Consulta:" + sql);
        List<ExamesPorFazerItem> lista = new ArrayList();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                lista.add(new ExamesPorFazerItem(rs.getString("s.designacao"), rs.getString("ex.resultado"), rs.getDate("p.dataPedido"), rs.getDate("ex.dataResultado"), rs.getString("e1.referencia")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public List<ExamesPorFazerItem> getResultadosProdutosItemMicro(int codigoServico, String dataPedido, int codigoPaciente) {
        //conexao.Connectando();
        sql = "SELECT DISTINCT s.designacao,ex.resultado,p.dataPedido,ex.dataResultado,e1.referencia FROM examesintegrado e inner join servicos s on e.codigoServico=s.idServico\n"
                + "inner join examesporfazeritems ex on ex.codigoProduto = s.idServico\n"
                + "inner join examesintegrado e1 on e1.codigoServico=s.idServico\n"
                + "inner join examesporfazer p on ex.codigoExames = p.idexamesPorFazer\n"
                + "inner join pacientes pa on p.codigoPaciente = pa.idPaciente\n"
                + "where s.idServico=" + codigoServico + " and p.dataPedido='" + dataPedido + "' and pa.idPaciente =" + codigoPaciente;
        System.out.println("Consulta:" + sql);
        List<ExamesPorFazerItem> lista = new ArrayList();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                lista.add(new ExamesPorFazerItem(rs.getString("s.designacao"), rs.getString("ex.resultado"), rs.getDate("p.dataPedido"), rs.getDate("ex.dataResultado"), rs.getString("e1.referencia")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public int codigoItem(String produto, String dataPedido, int codigoPaciente) {
        // conexao.Connectando();
        try {
            sql = "SELECT e1.codigoExames from examesporfazer e inner join examesporfazeritems e1\n"
                    + "inner join servicos s on s.idServico= e1.codigoProduto\n"
                    + "inner join pacientes p on p.idPaciente =e.codigoPaciente\n"
                    + "where e.dataPedido='" + dataPedido + "' and s.designacao ='" + produto + "' and e.codigoPaciente=" + codigoPaciente;
            System.out.println("Codigo Item:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControllerExamesporFazer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;

    }

    public int codigoItemMicro(String produto, String categoria, String dataPedido, int codigoPaciente) {
        // conexao.Connectando();
        try {
            sql = "SELECT DISTINCT e1.codigoExames from examesporfazer e inner join examesporfazeritems e1\n"
                    + "inner join servicos s on s.idServico= e1.codigoProduto\n"
                    + "inner join pacientes p on p.idPaciente =e.codigoPaciente\n"
                    + "inner join examesintegrado exame on exame.codigoServico=s.idServico\n"
                    + "inner join categoria_exames cx on cx.codigo = exame.codigoCategoria\n"
                    + "where e.dataPedido='" + dataPedido + "' and s.designacao ='" + produto + "'\n"
                    + "AND cx.designacao = '" + categoria + "' and e.codigoPaciente=" + codigoPaciente;
            System.out.println("Codigo Item:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControllerExamesporFazer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;

    }

    public List<ResultadoExame> getResultadosByPaciente3(int codigoPaciente, String data1, String data2, int codigoExameStatus, boolean listarTodos, boolean temProdutoItem, int codigoExame, int codigoProduto) {
        //   conexao.Connectando();
        codigoExameStatus = 1;
        sql = "SELECT DISTINCT\n"
                + "     examesporfazeritems.`codigoExames` AS codigoExames,\n"
                + "     examesporfazeritems.`resultado` AS Resultado,\n"
                + "     examesintegrado.`designacao` AS Designacao,\n"
                + "     examesintegrado.`referencia` AS Referencia\n"
                + "FROM\n"
                + "     `examesporfazer` examesporfazer INNER JOIN `examesporfazeritems` examesporfazeritems ON examesporfazer.`idexamesPorFazer` = examesporfazeritems.`codigoExames`\n"
                + "     INNER JOIN `servicos` servicos ON examesporfazeritems.`codigoProduto` = servicos.`idServico`\n"
                + "     INNER JOIN `examesintegrado` examesintegrado ON servicos.`idServico` = examesintegrado.`codigoServico`\n"
                + "     INNER JOIN `pacientes` pacientes  ON examesporfazer.`codigoPaciente` =  `pacientes`.idPaciente\n"
                + "WHERE\n"
                + "     examesporfazeritems.`codigoExames` = " + codigoExame + "";
        sql += " And examesporfazeritems.`codigoStatusExame` = " + codigoExameStatus + " and examesporfazeritems.codigoProduto = " + codigoProduto;
        //  date(examesporfazer.`dataPedido`) between '" + data1 + "' And '" + data2 + "' 
        System.out.println("Segunda consulta:" + sql);
        List<ResultadoExame> lista = new ArrayList();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new ResultadoExame(rs.getInt("codigoExames"), rs.getString("Designacao"), rs.getString("Resultado"), rs.getString("Referencia")));
            }
//            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public List<ResultadoExame> getResultadosByPaciente3porLike(int codigoPaciente, String data1, String data2, int codigoExameStatus, boolean listarTodos, boolean temProdutoItem, int codigoExame, int codigoProduto, String designacao) {
        //   conexao.Connectando();
        codigoExameStatus = 1;
        sql = "SELECT DISTINCT\n"
                + "     examesporfazeritems.`codigoExames` AS codigoExames,\n"
                + "     examesporfazeritems.`resultado` AS Resultado,\n"
                + "     examesintegrado.`designacao` AS Designacao,\n"
                + "     examesintegrado.`referencia` AS Referencia\n"
                + "FROM\n"
                + "     `examesporfazer` examesporfazer INNER JOIN `examesporfazeritems` examesporfazeritems ON examesporfazer.`idexamesPorFazer` = examesporfazeritems.`codigoExames`\n"
                + "     INNER JOIN `servicos` servicos ON examesporfazeritems.`codigoProduto` = servicos.`idServico`\n"
                + "     INNER JOIN `examesintegrado` examesintegrado ON servicos.`idServico` = examesintegrado.`codigoServico`\n"
                + "     INNER JOIN `pacientes` pacientes  ON examesporfazer.`codigoPaciente` =  `pacientes`.idPaciente\n"
                + "WHERE\n"
                + "     examesporfazeritems.`codigoExames` = " + codigoExame + "";
        sql += " And examesporfazeritems.`codigoStatusExame` = " + codigoExameStatus + " and examesporfazeritems.codigoProduto = " + codigoProduto + " AND examesintegrado.`designacao` LIKE '%" + designacao + "%'";
        //  date(examesporfazer.`dataPedido`) between '" + data1 + "' And '" + data2 + "' 
        System.out.println("Segunda consulta:" + sql);
        List<ResultadoExame> lista = new ArrayList();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new ResultadoExame(rs.getInt("codigoExames"), rs.getString("Designacao"), rs.getString("Resultado"), rs.getString("Referencia")));
            }
//            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public List<ResultadoExame> getResultadosByPaciente3Micro(int codigoPaciente, int codigoCategoria, String data1, String data2, int codigoExameStatus, boolean listarTodos, boolean temProdutoItem, int codigoExame, int codigoProduto) {
        //   conexao.Connectando();
        codigoExameStatus = 1;
        sql = "SELECT DISTINCT\n"
                + "     examesporfazeritems.`codigoExames` AS codigoExames,\n"
                + "     examesporfazeritems.`resultado` AS Resultado,\n"
                + "     examesintegrado.`designacao` AS Designacao,\n"
                + "     examesintegrado.`referencia` AS Referencia\n"
                + "FROM\n"
                + "     `examesporfazer` examesporfazer INNER JOIN `examesporfazeritems` examesporfazeritems ON examesporfazer.`idexamesPorFazer` = examesporfazeritems.`codigoExames`\n"
                + "     INNER JOIN `servicos` servicos ON examesporfazeritems.`codigoProduto` = servicos.`idServico`\n"
                + "     INNER JOIN `examesintegrado` examesintegrado ON servicos.`idServico` = examesintegrado.`codigoServico`\n"
                + "     INNER JOIN `pacientes` pacientes  ON examesporfazer.`codigoPaciente` =  `pacientes`.idPaciente\n"
                + "WHERE\n"
                + "     examesporfazeritems.`codigoExames` = " + codigoExame + " AND examesintegrado.`Codigocategoria` =" + codigoCategoria + "";
        sql += " And examesporfazeritems.`codigoStatusExame` = " + codigoExameStatus + " and examesporfazeritems.codigoProduto = " + codigoProduto;
        //  date(examesporfazer.`dataPedido`) between '" + data1 + "' And '" + data2 + "' 
        System.out.println("Segunda consulta:" + sql);
        List<ResultadoExame> lista = new ArrayList();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new ResultadoExame(rs.getInt("codigoExames"), rs.getString("Designacao"), rs.getString("Resultado"), rs.getString("Referencia")));
            }
//            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public int getCodigoCategoriaServicoMicro(String designacao) {
        //conexao.Connectando();
        sql = "SELECT codigo FROM categoria_exames c where designacao ='" + designacao + "'";
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

}
