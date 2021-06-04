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
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Probook
 */
public class ControllerExamesporFazerItens {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerExamesporFazerItens(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void create(ExamesPorFazerItem e) {
        //   conexao.Connectando();
        sql = "insert into examesporfazeritems(codigoExames, CodigoProduto, CodigoStatusExame, codigoExamesIntegrado,quantidade) values(?,?,?,?,?)";
        System.out.println("Insert:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, e.getCodigoExame());
            ps.setInt(2, e.getCodigoServico());
            ps.setInt(3, e.getCodigoStatusExame());
            ps.setInt(4, e.getCodigoExameItengrado());
            ps.setInt(5, e.getQuantidade());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
        }

    }

    public boolean actualizarResultadoDoProdutoItem(int codigo, String resultado, String data, int codigoItengrado) {
        conexao.Connectando();
        sql = " update examesporfazeritems set CodigoStatusExame = 2,Espera='SIM', resultado = '" + resultado + "', DataResultado = '" + data + "'\n "
                + "where examesporfazeritems.CodigoExames=" + codigo + " and examesporfazeritems.CodigoExamesIntegrado=" + codigoItengrado;
        System.out.println("´Actualização:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (Exception e) {
        }
        return true;
    }

    public void actualizarResultadoDoProdutoItem1(int codigo, String resultado, String referencia, String data, int codigoItengrado) {
        //   conexao.Connectando();
        sql = " update examesporfazeritems set CodigoStatusExame = 2,Espera='SIM', resultado = '" + resultado + "', referencia = '" + referencia + "', DataResultado = '" + data + "'\n "
                + "where examesporfazeritems.CodigoExames=" + codigo + " and examesporfazeritems.CodigoExamesIntegrado=" + codigoItengrado;
        System.out.println("´Actualização:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (Exception e) {
        }

    }

    public int getLastInsert() {
        sql = "select max(idexamesPorFazer) as last from examesporfazer";
        System.out.println("Teste:" + sql);
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

    public int codigoItem(String produto, String dataPedido, int codigoPaciente, int codigoTriagem) {
        //  conexao.Connectando();
        sql = "SELECT * FROM pedidos_exames p inner join servicos s on p.codigoServico=s.idServico\n"
                + "inner join historicoClinico h on p.codigoTriagem = h.idhistoricoClinico\n"
                + "inner join pacientes pa on p.codigoPaciente = pa.idPaciente\n"
                + "where s.designacao ='" + produto + "' and p.codigoTriagem =" + codigoTriagem + " and p.codigoPaciente=" + codigoPaciente + " and p.dataPedido='" + dataPedido + "'";

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

    public List<ExamesPorFazerItem> getResultadosProdutosItem(int codigoServico, String dataPedido, int codigoPaciente) {
        //conexao.Connectando();
        sql = "SELECT * FROM examesintegrado e inner join servicos s on e.codigoServico=s.idServico\n"
                + "inner join pedidos_exames p on p.codigoServico = s.idServico\n"
                + "inner join pacientes pa on p.codigoPaciente = p.codigoPaciente\n"
                + "where s.idServico=" + codigoServico + " and p.dataPedido='" + dataPedido + "' and pa.idPaciente =" + codigoPaciente + "";

        List<ExamesPorFazerItem> lista = new ArrayList();
        try {
            // ResultSet rs = conexao.executeQuery(sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                lista.add(new ExamesPorFazerItem(rs.getString("designacao"), rs.getString("resultado"), rs.getDate("DataPedido"), rs.getDate("DataResultado"), rs.getString("examesintegrado.referencia")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public int codigoItem(String produto, String dataPedido, String codigo, int codigoPaciente) {
        conexao.Connectando();
        sql = "select examesporfazeritems.codigo from examesporfazer, examesporfazeritems, "
                + "servicos where servicos.idServico = examesporfazeritems.codigoProduto and examesporfazer.idexamesPorFazer = examesporfazeritems.codigoExames and "
                + "  examesporfazer.dataPedido = '" + dataPedido + "' "
                + " and servicos.designacao = '" + produto + "' and examesporfazer.codigoPaciente = " + codigoPaciente + " and codigoExames=" + codigo;

        System.out.println("Consulta:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControllerExamesporFazerItens.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;

    }

    public void actualizarResultado(int codigo, String codigoExame, String resultado, String data, String factorResultado, String referencia) {
        // conexao.Connectando();
        sql = "update examesporfazeritems set codigoStatusExame = 2, referencia = '" + referencia + "', Resultado = '" + resultado + "', dataResultado ='" + data + "',factor_rh = '" + factorResultado + "' where Codigo = " + codigo + " AND codigoExames= " + codigoExame;
        System.out.println("Consulta do Resultado Pronto:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
        }
    }

    public void actualizarResultado1(String codigo, String codigoExame, String resultado, String data, String referencia) {
        // conexao.Connectando();
        sql = "update examesporfazeritems set codigoStatusExame = 2, referencia = '" + referencia + "', Resultado = '" + resultado + "', dataResultado ='" + data + "' where codigoProduto = " + codigo + " AND codigoExames= " + codigoExame;
        System.out.println("Consulta do Resultado Pronto:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
        }
    }

    public void actualizarResultado(int codigo, String resultado, String data, String factorResultado, String referencia) {
        // conexao.Connectando();
        sql = "update examesporfazeritems set codigoStatusExame = 2, referencia = '" + referencia + "', Resultado = '" + resultado + "', dataResultado ='" + data + "',factor_rh = '" + factorResultado + "' where Codigo = " + codigo;
        System.out.println("Código:" + codigo);
        System.out.println("Resultado:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
        }
    }

    public void actualizarStatus(String codigo, String codigoProduto) {
        //  conexao.Connectando();
        sql = "update examesporfazeritems set Espera ='SIM' where codigoExames= " + codigo + " AND codigoProduto =" + codigoProduto;
        System.out.println("Em espera:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
        }
    }

    public int codigoExameItem(int codigoExameItem, int codigoPaciente, int codigoExame) {
        // conexao.Connectando();
        sql = "SELECT DISTINCT\n"
                + "     examesporfazeritems.`codigoExames` AS examesporfazeritems_codigoExames,\n"
                + "     examesporfazer.`dataPedido` AS examesporfazer_dataPedido\n"
                + "FROM\n"
                + "     `servicos` servicos INNER JOIN `examesintegrado` examesintegrado ON servicos.`idServico` = examesintegrado.`codigoServico`\n"
                + "     INNER JOIN `examesporfazeritems` examesporfazeritems ON servicos.`idServico` = examesporfazeritems.`codigoProduto`\n"
                + "     INNER JOIN `examesporfazer` examesporfazer ON examesporfazeritems.`codigoExames` = examesporfazer.`idexamesPorFazer`\n"
                + "where  examesporfazeritems.`codigoExamesIntegrado`=" + codigoExameItem + " and date(examesporfazer.`dataPedido`) =current_date\n"
                + " and examesporfazer.`codigoPaciente`=" + codigoPaciente + " AND examesporfazeritems.`codigoExames`=" + codigoExame;
        System.out.println("Segunda Pesquisa:" + sql);
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

    public int getCodigoExameItemComProdutoItem(int codigoExame, String produtoItem) {
        // conexao.Connectando();
        sql = "SELECT\n"
                //   + "     servicos.`idServico` AS servicos_idServico,\n"
                //    + "     examesintegrado.`idExamesIntegrado` AS examesintegrado_idExamesIntegrado,\n"
                + "     examesporfazeritems.`codigoExames` AS codigoExames\n"
                // + "     examesintegrado.`designacao` AS examesintegrado_designacao\n"
                + "FROM\n"
                + "     `servicos` servicos INNER JOIN `examesporfazeritems` examesporfazeritems ON servicos.`idServico` = examesporfazeritems.`codigoProduto`\n"
                + "     INNER JOIN `examesintegrado` examesintegrado ON servicos.`idServico` = examesintegrado.`codigoServico`\n"
                + "WHERE  examesintegrado.`designacao`='" + produtoItem + "' AND  examesporfazeritems.`codigoExames`=" + codigoExame;
        System.out.println("Controlo:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

     public boolean actualizarResultadoDoProdutoItemBartolomeu(int codigo, String resultado, int codigoItengrado) {
        String sql = " update examesporfazeritems set CodigoStatusExame = 2,resultado = '" + resultado + "' where examesporfazeritems.CodigoExames=" + codigo + " and examesporfazeritems.CodigoExamesIntegrado=" + codigoItengrado;
        System.out.println("´Actualização:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
        } catch (Exception e) {
        }
        return true;
    }
}
