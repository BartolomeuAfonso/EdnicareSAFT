/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao.Restaurantes.Controller;


import entidades.Movimentos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Amarildo Xpecialista
 */
public class StockMovimentoContrller {

//    private BDConexao conexao;
    Connection con;
    private String sql;
    PreparedStatement ps;
    ResultSet rs;

    public StockMovimentoContrller(Connection con) {
        this.con = con;
    }

//    public StockMovimentoContrller() {
////        this.conexao = conexao;
//        sql = new String();
//    }
    public void salvarStockMovimento(Movimentos stock) {
        sql = "INSERT INTO movimentos(\n"
                + "            quantidade_movimentada, data_movimento, tipo_movimento, \n"
                + "            codigo_produto, codigo_armazem, codigo_usuario, quantidade_anterior, \n"
                + "            quantidade_posterior)\n"
                + "    VALUES (?, ?, ?, \n"
                + "            ?, ?, ?, ?, \n"
                + "            ?);";
        System.out.println(sql);
        java.sql.Date data1 = new java.sql.Date(stock.getDataEntrada().getTime());

        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setInt(1, stock.getQuantidade());
            ps.setDate(2, data1);
            ps.setString(3, stock.getTipoMovimento());
            ps.setLong(4, stock.getIdproduto());
            ps.setLong(5, stock.getIdArmazem());
            ps.setLong(6, stock.getIdUsuario());
            ps.setLong(7, stock.getQuantidadeAntes());
            ps.setLong(8, stock.getQuantidadeDepois());
            ps.execute();
            ps.close();
//            conexao.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Erro:" + ex);
        }
    }
}
