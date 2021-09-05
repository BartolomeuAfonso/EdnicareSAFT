/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.estatistica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author bafonso
 */
public class EstatisticaLaboratorio {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;

    public int getQuantidadeGotaEspesa(String data1, String data2) {
        conexao.Connectando();
        sql = "SELECT DISTINCT\n"
                + "       SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "       servicos.`designacao` AS servico\n"
                + "FROM\n"
                + "`factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "WHERE DATE(factura.`dataFactura`) BETWEEN '" + data1 + "' AND '" + data2 + "' AND servicos.`designacao`='GOTA ESPESSA'\n"
                + "GROUP BY 2";
        System.out.println("Gota Espesa::" + sql);
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

    public int getQuantidadeGrupoSanguineo(String data1, String data2) {
        conexao.Connectando();
        sql = "SELECT DISTINCT\n"
                + "       SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "       servicos.`designacao` AS servico\n"
                + "FROM\n"
                + "`factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "WHERE DATE(factura.`dataFactura`) BETWEEN '" + data1 + "' AND '" + data2 + "' AND servicos.`designacao`='Grupo Sanguino'\n"
                + "GROUP BY 2";
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

    public int getQuantidadeBK(String data1, String data2) {
        conexao.Connectando();
        sql = "SELECT DISTINCT\n"
                + "       SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "       servicos.`designacao` AS servico\n"
                + "FROM\n"
                + "`factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "WHERE DATE(factura.`dataFactura`) BETWEEN '" + data1 + "' AND '" + data2 + "' AND servicos.`designacao`='BK'\n"
                + "GROUP BY 2";
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

    public int getQuantidadeVS(String data1, String data2) {
        conexao.Connectando();
        sql = "SELECT DISTINCT\n"
                + "       SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "       servicos.`designacao` AS servico\n"
                + "FROM\n"
                + "`factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "WHERE DATE(factura.`dataFactura`) BETWEEN '" + data1 + "' AND '" + data2 + "' AND servicos.`designacao`='Velocidade de sedimentação'\n"
                + "GROUP BY 2";
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

    public int getQuantidadeFacilformacao(String data1, String data2) {
        conexao.Connectando();
        sql = "SELECT DISTINCT\n"
                + "       SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "       servicos.`designacao` AS servico\n"
                + "FROM\n"
                + "`factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "WHERE DATE(factura.`dataFactura`) BETWEEN '" + data1 + "' AND '" + data2 + "' AND servicos.`designacao`='Falciformação'\n"
                + "GROUP BY 2";
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

    public int getQuantidadeUrina(String data1, String data2) {
        conexao.Connectando();
        sql = "SELECT DISTINCT\n"
                + "       SUM(factura_itens.`quantidade`) AS quantidade,\n"
                + "       servicos.`designacao` AS servico\n"
                + "FROM\n"
                + "`factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "WHERE DATE(factura.`dataFactura`) BETWEEN '" + data1 + "' AND '" + data2 + "' AND servicos.`designacao`='Urina ii (análise sumária da urina)'\n"
                + "GROUP BY 2";
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

    public int getQuantidadeHematologia(String data1, String data2) {
        conexao.Connectando();
        sql = "SELECT DISTINCT\n"
                + "       SUM(factura_itens.`quantidade`) AS quantidade\n"
                + "FROM\n"
                + "`factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "INNER JOIN categoria_exames c1 ON  servicos.codigoGrupo = c1.codigo\n"
                + "AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "WHERE DATE(factura.`dataFactura`) BETWEEN '" + data1 + "' AND '" + data2 + "' AND c1.designacao='HEMATOLOGIA'";
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

    public int getQuantidadeSerologia(String data1, String data2) {
        conexao.Connectando();
        sql = "SELECT DISTINCT\n"
                + "       SUM(factura_itens.`quantidade`) AS quantidade\n"
                + "FROM\n"
                + "`factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "INNER JOIN categoria_exames c1 ON  servicos.codigoGrupo = c1.codigo\n"
                + "AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "WHERE DATE(factura.`dataFactura`) BETWEEN '" + data1 + "' AND '" + data2 + "' AND c1.designacao='SEROLOGIA'";
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

    public int getQuantidadeFezes(String data1, String data2) {
        conexao.Connectando();
        sql = "SELECT DISTINCT\n"
                + "       SUM(factura_itens.`quantidade`) AS quantidade\n"
                + "FROM\n"
                + "`factura` factura INNER JOIN `factura_itens` factura_itens ON factura.`idfactura` = factura_itens.`codigoFactura`\n"
                + "INNER JOIN `pacientes` pacientes ON factura.`codigoCliente` = pacientes.`idPaciente`\n"
                + "INNER JOIN `empresaseguros` empresaseguros ON pacientes.`codigoSeguro` = empresaseguros.`idSeguros`\n"
                + "INNER JOIN `categoriaservico` categoriaservico ON factura_itens.`codigoCategoria` = categoriaservico.`idcategoriaServico`\n"
                + "INNER JOIN `servicos` servicos ON factura_itens.`codigoProduto` = servicos.`idServico`\n"
                + "AND categoriaservico.`idcategoriaServico` = servicos.`codigoCategoria`\n"
                + "WHERE DATE(factura.`dataFactura`) BETWEEN '"+data1+"' AND '"+data2+"' AND servicos.`designacao`='fezes directa' OR servicos.`designacao`='fezes directa 2 amostras' OR servicos.`designacao`='fezes directa 3 amostras'";
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
