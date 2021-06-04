/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.estatistica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author Probook
 */
public class EstatisticaParticular {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;

    public int getTotalClinicaGeral(String data, String data1) {
        conexao.Connectando();
        sql = "SELECT\n"
                + "factura.`dataFactura` AS factura_dataFactura,\n"
                + "     SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "     empresaseguros.`designacao` AS empresa,\n"
                + "     categoriaservico.`idcategoriaServico` AS codigoCategoria,\n"
                + "     servicos.`designacao` AS servico,\n"
                + "     empresaseguros.`idSeguros` AS idEmpresa\n"
                + "FROM\n"
                + "     `factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "     INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "     INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "     INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "     AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "where DATE(factura.`dataFactura`) BETWEEN '" + data + "' AND '" + data1 + "' AND categoriaservico.`idcategoriaServico` = 2 AND  servicos.`designacao` ='CLINICA GERAL'\n"
                + "AND empresaseguros.`idSeguros` = 8";
        System.out.println("Teste:" + sql);
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public int getTotalMedicinaInterna(String data, String data1) {
        conexao.Connectando();
        sql = "SELECT\n"
                + "factura.`dataFactura` AS factura_dataFactura,\n"
                + "     SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "     empresaseguros.`designacao` AS empresa,\n"
                + "     categoriaservico.`idcategoriaServico` AS codigoCategoria,\n"
                + "     servicos.`designacao` AS servico,\n"
                + "     empresaseguros.`idSeguros` AS idEmpresa\n"
                + "FROM\n"
                + "     `factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "     INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "     INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "     INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "     AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "where DATE(factura.`dataFactura`) BETWEEN '" + data + "' AND '" + data1 + "' AND categoriaservico.`idcategoriaServico` = 2 AND  servicos.`designacao` ='MEDICINA INTERNA'\n"
                + "AND empresaseguros.`idSeguros` = 8";
        System.out.println("Teste:" + sql);
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public int getTotalPediatria(String data, String data1) {
        conexao.Connectando();
        sql = "SELECT\n"
                + "factura.`dataFactura` AS factura_dataFactura,\n"
                + "     SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "     empresaseguros.`designacao` AS empresa,\n"
                + "     categoriaservico.`idcategoriaServico` AS codigoCategoria,\n"
                + "     servicos.`designacao` AS servico,\n"
                + "     empresaseguros.`idSeguros` AS idEmpresa\n"
                + "FROM\n"
                + "     `factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "     INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "     INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "     INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "     AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "where DATE(factura.`dataFactura`) BETWEEN '" + data + "' AND '" + data1 + "' AND categoriaservico.`idcategoriaServico` = 2 AND  servicos.`designacao` ='PEDIATRIA'\n"
                + "AND empresaseguros.`idSeguros` = 8";
        System.out.println("Teste:" + sql);
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public int getTotalOfltamologia(String data, String data1) {
        conexao.Connectando();
        sql = "SELECT\n"
                + "factura.`dataFactura` AS factura_dataFactura,\n"
                + "     SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "     empresaseguros.`designacao` AS empresa,\n"
                + "     categoriaservico.`idcategoriaServico` AS codigoCategoria,\n"
                + "     servicos.`designacao` AS servico,\n"
                + "     empresaseguros.`idSeguros` AS idEmpresa\n"
                + "FROM\n"
                + "     `factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "     INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "     INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "     INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "     AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "where DATE(factura.`dataFactura`) BETWEEN '" + data + "' AND '" + data1 + "' AND categoriaservico.`idcategoriaServico` = 2 AND  servicos.`designacao` ='OFTALMOLOGIA'\n"
                + "AND empresaseguros.`idSeguros` = 8";
        System.out.println("Teste:" + sql);
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public int getTotalGincologia(String data, String data1) {
        conexao.Connectando();
        sql = "SELECT\n"
                + "factura.`dataFactura` AS factura_dataFactura,\n"
                + "     SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "     empresaseguros.`designacao` AS empresa,\n"
                + "     categoriaservico.`idcategoriaServico` AS codigoCategoria,\n"
                + "     servicos.`designacao` AS servico,\n"
                + "     empresaseguros.`idSeguros` AS idEmpresa\n"
                + "FROM\n"
                + "     `factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "     INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "     INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "     INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "     AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "where DATE(factura.`dataFactura`) BETWEEN '" + data + "' AND '" + data1 + "' AND categoriaservico.`idcategoriaServico` = 2 AND  servicos.`designacao` ='GINECOLOGIA'\n"
                + "AND empresaseguros.`idSeguros` = 8";
        System.out.println("Teste:" + sql);
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public int getTotalOrtopedia(String data, String data1) {
        conexao.Connectando();
        sql = "SELECT\n"
                + "factura.`dataFactura` AS factura_dataFactura,\n"
                + "     SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "     empresaseguros.`designacao` AS empresa,\n"
                + "     categoriaservico.`idcategoriaServico` AS codigoCategoria,\n"
                + "     servicos.`designacao` AS servico,\n"
                + "     empresaseguros.`idSeguros` AS idEmpresa\n"
                + "FROM\n"
                + "     `factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "     INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "     INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "     INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "     AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "where DATE(factura.`dataFactura`) BETWEEN '" + data + "' AND '" + data1 + "' AND categoriaservico.`idcategoriaServico` = 2 AND  servicos.`designacao` ='ORTOPEDIA'\n"
                + "AND empresaseguros.`idSeguros` = 8";
        System.out.println("Teste:" + sql);
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public int getTotalEstomatogia(String data, String data1) {
        conexao.Connectando();
        sql = "SELECT\n"
                + "factura.`dataFactura` AS factura_dataFactura,\n"
                + "     SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "     empresaseguros.`designacao` AS empresa,\n"
                + "     categoriaservico.`idcategoriaServico` AS codigoCategoria,\n"
                + "     servicos.`designacao` AS servico,\n"
                + "     empresaseguros.`idSeguros` AS idEmpresa\n"
                + "FROM\n"
                + "     `factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "     INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "     INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "     INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "     AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "where DATE(factura.`dataFactura`) BETWEEN '" + data + "' AND '" + data1 + "' AND categoriaservico.`idcategoriaServico` = 2 AND  servicos.`designacao` ='ESTOMATOLOGIA'\n"
                + "AND empresaseguros.`idSeguros` = 8";
        System.out.println("Teste:" + sql);
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public int getTotalGastro(String data, String data1) {
        conexao.Connectando();
        sql = "SELECT\n"
                + "factura.`dataFactura` AS factura_dataFactura,\n"
                + "     SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "     empresaseguros.`designacao` AS empresa,\n"
                + "     categoriaservico.`idcategoriaServico` AS codigoCategoria,\n"
                + "     servicos.`designacao` AS servico,\n"
                + "     empresaseguros.`idSeguros` AS idEmpresa\n"
                + "FROM\n"
                + "     `factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "     INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "     INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "     INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "     AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "where DATE(factura.`dataFactura`) BETWEEN '" + data + "' AND '" + data1 + "' AND categoriaservico.`idcategoriaServico` = 2 AND  servicos.`designacao` ='GASTROENTOLOGIA'\n"
                + "AND empresaseguros.`idSeguros` = 8";
        System.out.println("Teste:" + sql);
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public int getTotalOtorrino(String data, String data1) {
        conexao.Connectando();
        sql = "SELECT\n"
                + "factura.`dataFactura` AS factura_dataFactura,\n"
                + "     SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "     empresaseguros.`designacao` AS empresa,\n"
                + "     categoriaservico.`idcategoriaServico` AS codigoCategoria,\n"
                + "     servicos.`designacao` AS servico,\n"
                + "     empresaseguros.`idSeguros` AS idEmpresa\n"
                + "FROM\n"
                + "     `factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "     INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "     INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "     INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "     AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "where DATE(factura.`dataFactura`) BETWEEN '" + data + "' AND '" + data1 + "' AND categoriaservico.`idcategoriaServico` = 2 AND  servicos.`designacao` ='OTORRINOLARINGOLOGIA'\n"
                + "AND empresaseguros.`idSeguros` = 8";
        System.out.println("Teste:" + sql);
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public int getTotalCardiologia(String data, String data1) {
        conexao.Connectando();
        sql = "SELECT\n"
                + "factura.`dataFactura` AS factura_dataFactura,\n"
                + "     SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "     empresaseguros.`designacao` AS empresa,\n"
                + "     categoriaservico.`idcategoriaServico` AS codigoCategoria,\n"
                + "     servicos.`designacao` AS servico,\n"
                + "     empresaseguros.`idSeguros` AS idEmpresa\n"
                + "FROM\n"
                + "     `factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "     INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "     INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "     INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "     AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "where DATE(factura.`dataFactura`) BETWEEN '" + data + "' AND '" + data1 + "' AND categoriaservico.`idcategoriaServico` = 2 AND  servicos.`designacao` ='CARDIOLOGIA'\n"
                + "AND empresaseguros.`idSeguros` = 8";
        System.out.println("Teste:" + sql);
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public int getTotalUrologia(String data, String data1) {
        conexao.Connectando();
        sql = "SELECT\n"
                + "factura.`dataFactura` AS factura_dataFactura,\n"
                + "     SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "     empresaseguros.`designacao` AS empresa,\n"
                + "     categoriaservico.`idcategoriaServico` AS codigoCategoria,\n"
                + "     servicos.`designacao` AS servico,\n"
                + "     empresaseguros.`idSeguros` AS idEmpresa\n"
                + "FROM\n"
                + "     `factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "     INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "     INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "     INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "     AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "where DATE(factura.`dataFactura`) BETWEEN '" + data + "' AND '" + data1 + "' AND categoriaservico.`idcategoriaServico` = 2 AND  servicos.`designacao` ='UROLOGIA'\n"
                + "AND empresaseguros.`idSeguros` = 8";
        System.out.println("Teste:" + sql);
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public int getTotalDermatologia(String data, String data1) {
        conexao.Connectando();
        sql = "SELECT\n"
                + "factura.`dataFactura` AS factura_dataFactura,\n"
                + "     SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "     empresaseguros.`designacao` AS empresa,\n"
                + "     categoriaservico.`idcategoriaServico` AS codigoCategoria,\n"
                + "     servicos.`designacao` AS servico,\n"
                + "     empresaseguros.`idSeguros` AS idEmpresa\n"
                + "FROM\n"
                + "     `factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "     INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "     INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "     INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "     AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "where DATE(factura.`dataFactura`) BETWEEN '" + data + "' AND '" + data1 + "' AND categoriaservico.`idcategoriaServico` = 2 AND  servicos.`designacao` ='DERMATOLOGIA'\n"
                + "AND empresaseguros.`idSeguros` = 8";
        System.out.println("Teste:" + sql);
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public int getTotalNeuro(String data, String data1) {
        conexao.Connectando();
        sql = "SELECT\n"
                + "factura.`dataFactura` AS factura_dataFactura,\n"
                + "     SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "     empresaseguros.`designacao` AS empresa,\n"
                + "     categoriaservico.`idcategoriaServico` AS codigoCategoria,\n"
                + "     servicos.`designacao` AS servico,\n"
                + "     empresaseguros.`idSeguros` AS idEmpresa\n"
                + "FROM\n"
                + "     `factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "     INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "     INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "     INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "     AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "where DATE(factura.`dataFactura`) BETWEEN '" + data + "' AND '" + data1 + "' AND categoriaservico.`idcategoriaServico` = 2 AND  servicos.`designacao` ='NEUROLOGIA'\n"
                + "AND empresaseguros.`idSeguros` = 8";
        System.out.println("Teste:" + sql);
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public int getTotalEcografia(String data, String data1) {
        conexao.Connectando();
        sql = "SELECT\n"
                + "factura.`dataFactura` AS factura_dataFactura,\n"
                + "     SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "     empresaseguros.`designacao` AS empresa,\n"
                + "     categoriaservico.`idcategoriaServico` AS codigoCategoria,\n"
                + "     servicos.`designacao` AS servico,\n"
                + "     empresaseguros.`idSeguros` AS idEmpresa\n"
                + "FROM\n"
                + "     `factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "     INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "     INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "     INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "     AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "where DATE(factura.`dataFactura`) BETWEEN '" + data + "' AND '" + data1 + "' AND categoriaservico.`idcategoriaServico` = 3\n"
                + "AND empresaseguros.`idSeguros` = 8";
        System.out.println("Teste:" + sql);
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public int getTotalEcocardiogragra(String data, String data1) {
        conexao.Connectando();
        sql = "SELECT\n"
                + "factura.`dataFactura` AS factura_dataFactura,\n"
                + "     SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "     empresaseguros.`designacao` AS empresa,\n"
                + "     categoriaservico.`idcategoriaServico` AS codigoCategoria,\n"
                + "     servicos.`designacao` AS servico,\n"
                + "     empresaseguros.`idSeguros` AS idEmpresa\n"
                + "FROM\n"
                + "     `factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "     INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "     INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "     INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "     AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "where DATE(factura.`dataFactura`) BETWEEN '" + data + "' AND '" + data1 + "' AND servicos.`designacao` ='ECOCARDIOGRAMA'\n"
                + "AND empresaseguros.`idSeguros` = 8";
        System.out.println("Teste:" + sql);
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public int getTotalElectroCardiograma(String data, String data1) {
        conexao.Connectando();
        sql = "SELECT\n"
                + "factura.`dataFactura` AS factura_dataFactura,\n"
                + "     SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "     empresaseguros.`designacao` AS empresa,\n"
                + "     categoriaservico.`idcategoriaServico` AS codigoCategoria,\n"
                + "     servicos.`designacao` AS servico,\n"
                + "     empresaseguros.`idSeguros` AS idEmpresa\n"
                + "FROM\n"
                + "     `factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "     INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "     INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "     INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "     AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "where DATE(factura.`dataFactura`) BETWEEN '" + data + "' AND '" + data1 + "' AND servicos.`designacao` ='ELECTROGRAMA'\n"
                + "AND empresaseguros.`idSeguros` = 8";
        System.out.println("Teste:" + sql);
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public int getTotalRX(String data, String data1) {
        conexao.Connectando();
        sql = "SELECT\n"
                + "factura.`dataFactura` AS factura_dataFactura,\n"
                + "     SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "     empresaseguros.`designacao` AS empresa,\n"
                + "     categoriaservico.`idcategoriaServico` AS codigoCategoria,\n"
                + "     servicos.`designacao` AS servico,\n"
                + "     empresaseguros.`idSeguros` AS idEmpresa\n"
                + "FROM\n"
                + "     `factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "     INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "     INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "     INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "     AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "where DATE(factura.`dataFactura`) BETWEEN '" + data + "' AND '" + data1 + "' AND categoriaservico.`idcategoriaServico` = 5\n"
                + "AND empresaseguros.`idSeguros` = 8";
        System.out.println("Teste:" + sql);
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public int getTotalExames(String data, String data1) {
        conexao.Connectando();
        sql = "SELECT\n"
                + "factura.`dataFactura` AS factura_dataFactura,\n"
                + "     SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "     empresaseguros.`designacao` AS empresa,\n"
                + "     categoriaservico.`idcategoriaServico` AS codigoCategoria,\n"
                + "     servicos.`designacao` AS servico,\n"
                + "     empresaseguros.`idSeguros` AS idEmpresa\n"
                + "FROM\n"
                + "     `factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "     INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "     INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "     INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "     AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "where DATE(factura.`dataFactura`) BETWEEN '" + data + "' AND '" + data1 + "' AND categoriaservico.`idcategoriaServico` = 1\n"
                + "AND empresaseguros.`idSeguros` = 8";
        System.out.println("Teste:" + sql);
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public int getTotalHidro(String data, String data1) {
        conexao.Connectando();
        sql = "SELECT\n"
                + "factura.`dataFactura` AS factura_dataFactura,\n"
                + "     SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "     empresaseguros.`designacao` AS empresa,\n"
                + "     categoriaservico.`idcategoriaServico` AS codigoCategoria,\n"
                + "     servicos.`designacao` AS servico,\n"
                + "     empresaseguros.`idSeguros` AS idEmpresa\n"
                + "FROM\n"
                + "     `factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "     INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "     INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "     INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "     AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "where DATE(factura.`dataFactura`) BETWEEN '" + data + "' AND '" + data1 + "' AND servicos.`designacao` ='HIDROENTUBAÇÃO'\n"
                + "AND empresaseguros.`idSeguros` = 8";
        System.out.println("Teste:" + sql);
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
    public int getTotalInternamento(String data, String data1) {
        conexao.Connectando();
        sql = "SELECT\n"
                + "factura.`dataFactura` AS factura_dataFactura,\n"
                + "     SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "     empresaseguros.`designacao` AS empresa,\n"
                + "     categoriaservico.`idcategoriaServico` AS codigoCategoria,\n"
                + "     servicos.`designacao` AS servico,\n"
                + "     empresaseguros.`idSeguros` AS idEmpresa\n"
                + "FROM\n"
                + "     `factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "     INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "     INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "     INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "     INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "     AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "where DATE(factura.`dataFactura`) BETWEEN '" + data + "' AND '" + data1 + "' AND servicos.`designacao` ='INTERNAMENTO'\n"
                + "AND empresaseguros.`idSeguros` = 8";
        System.out.println("Teste:" + sql);
        try {
            ps = conexao.conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }
}
