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
public class EstatisticaConsulta {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;

    public int getClinicaGeral(String data1, String data2) {
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
                + "WHERE DATE(factura.`dataFactura`) BETWEEN '" + data1 + "' AND '" + data2 + "' AND servicos.`designacao`='Consulta de Clinica Geral'\n"
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

    public int getPediatria(String data1, String data2) {
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
                + "WHERE DATE(factura.`dataFactura`) BETWEEN '" + data1 + "' AND '" + data2 + "' AND servicos.`designacao`='Consulta de Pediatria'\n"
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

    public int getGinecologia(String data1, String data2) {
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
                + "WHERE DATE(factura.`dataFactura`) BETWEEN '" + data1 + "' AND '" + data2 + "' AND servicos.`designacao`='Consulta de Ginecologia'\n"
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

    public int getGineologiaObstetricia(String data1, String data2) {
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
                + "WHERE DATE(factura.`dataFactura`) BETWEEN '" + data1 + "' AND '" + data2 + "' AND servicos.`designacao`='Consulta de Ginecologia & Obstetricia'\n"
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

    public int getPluericultura(String data1, String data2) {
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
                + "WHERE DATE(factura.`dataFactura`) BETWEEN '" + data1 + "' AND '" + data2 + "' AND servicos.`designacao`='Consulta de Pluericultura'\n"
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

    public int getPlaneamento(String data1, String data2) {
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
                + "WHERE DATE(factura.`dataFactura`) BETWEEN '" + data1 + "' AND '" + data2 + "' AND servicos.`designacao`='Consulta de Planeamento Familiar'\n"
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

    public int getPlaneamento1(String data1, String data2) {
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
                + "WHERE DATE(factura.`dataFactura`) BETWEEN '" + data1 + "' AND '" + data2 + "' AND servicos.`designacao`='Planeamento Familiar'\n"
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

    public int getCirurgia(String data1, String data2) {
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
                + "WHERE DATE(factura.`dataFactura`) BETWEEN '" + data1 + "' AND '" + data2 + "' AND servicos.`designacao`='Consulta de Cirurgia'\n"
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

    public int getClinicaGeralPorMedico(String data1, String data2) {
        conexao.Connectando();
        sql = "SELECT DISTINCT SUM(f1.quantidade) AS quantidade, s.designacao from    historicoclinico h  INNER JOIN triagem t ON h.codigoConsulta=t.idtriagem\n"
                + "INNER JOIN pacientes p ON t.codigoPaciente=p.idPaciente \n"
                + "INNER JOIN factura f ON f.codigoCliente=p.idPaciente\n"
                + "INNER JOIN factura_itens f1 ON f1.codigoFactura=f.idfactura\n"
                + "INNER JOIN servicos s ON s.idServico = f1.codigoProduto\n"
                + "INNER JOIN medicos m ON m.idMedico = h.codigoMedico\n"
                + "INNER JOIN especialidade_medico e ON m.codigoEspecialidade = e.idEspecialidade\n"
                + "WHERE  DATE(f.dataFactura) BETWEEN '" + data1 + "' AND '" + data2 + "' AND s.designacao='Consulta de Clinica Geral' AND f1.codigoCategoria=1\n"
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

    public int getPediatriaPorMedico(String data1, String data2) {
        conexao.Connectando();
        sql = "SELECT DISTINCT SUM(f1.quantidade) AS quantidade, s.designacao from    historicoclinico h  INNER JOIN triagem t ON h.codigoConsulta=t.idtriagem\n"
                + "INNER JOIN pacientes p ON t.codigoPaciente=p.idPaciente \n"
                + "INNER JOIN factura f ON f.codigoCliente=p.idPaciente\n"
                + "INNER JOIN factura_itens f1 ON f1.codigoFactura=f.idfactura\n"
                + "INNER JOIN servicos s ON s.idServico = f1.codigoProduto\n"
                + "INNER JOIN medicos m ON m.idMedico = h.codigoMedico\n"
                + "INNER JOIN especialidade_medico e ON m.codigoEspecialidade = e.idEspecialidade\n"
                + "WHERE  DATE(f.dataFactura) BETWEEN '" + data1 + "' AND '" + data2 + "' AND s.designacao='Consulta de Pediatria' AND f1.codigoCategoria=1\n"
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

    public int getCirurgiaPorMedico(String data1, String data2) {
        conexao.Connectando();
        sql = "SELECT DISTINCT SUM(f1.quantidade) AS quantidade, s.designacao from    historicoclinico h  INNER JOIN triagem t ON h.codigoConsulta=t.idtriagem\n"
                + "INNER JOIN pacientes p ON t.codigoPaciente=p.idPaciente \n"
                + "INNER JOIN factura f ON f.codigoCliente=p.idPaciente\n"
                + "INNER JOIN factura_itens f1 ON f1.codigoFactura=f.idfactura\n"
                + "INNER JOIN servicos s ON s.idServico = f1.codigoProduto\n"
                + "INNER JOIN medicos m ON m.idMedico = h.codigoMedico\n"
                + "INNER JOIN especialidade_medico e ON m.codigoEspecialidade = e.idEspecialidade\n"
                + "WHERE  DATE(f.dataFactura) BETWEEN '" + data1 + "' AND '" + data2 + "' AND s.designacao='Consulta de Cirurgia' AND f1.codigoCategoria=1\n"
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

    public int getPluericulturaPorMedico(String data1, String data2) {
        conexao.Connectando();
        sql = "SELECT DISTINCT SUM(f1.quantidade) AS quantidade, s.designacao from    historicoclinico h  INNER JOIN triagem t ON h.codigoConsulta=t.idtriagem\n"
                + "INNER JOIN pacientes p ON t.codigoPaciente=p.idPaciente \n"
                + "INNER JOIN factura f ON f.codigoCliente=p.idPaciente\n"
                + "INNER JOIN factura_itens f1 ON f1.codigoFactura=f.idfactura\n"
                + "INNER JOIN servicos s ON s.idServico = f1.codigoProduto\n"
                + "INNER JOIN medicos m ON m.idMedico = h.codigoMedico\n"
                + "INNER JOIN especialidade_medico e ON m.codigoEspecialidade = e.idEspecialidade\n"
                + "WHERE  DATE(f.dataFactura) BETWEEN '" + data1 + "' AND '" + data2 + "' AND s.designacao='Consulta de Puericultura' AND f1.codigoCategoria=1\n"
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

    public int getObstetriciaPorMedico(String data1, String data2) {
        conexao.Connectando();
        sql = "SELECT DISTINCT SUM(f1.quantidade) AS quantidade, s.designacao from    historicoclinico h  INNER JOIN triagem t ON h.codigoConsulta=t.idtriagem\n"
                + "INNER JOIN pacientes p ON t.codigoPaciente=p.idPaciente \n"
                + "INNER JOIN factura f ON f.codigoCliente=p.idPaciente\n"
                + "INNER JOIN factura_itens f1 ON f1.codigoFactura=f.idfactura\n"
                + "INNER JOIN servicos s ON s.idServico = f1.codigoProduto\n"
                + "INNER JOIN medicos m ON m.idMedico = h.codigoMedico\n"
                + "INNER JOIN especialidade_medico e ON m.codigoEspecialidade = e.idEspecialidade\n"
                + "WHERE  DATE(f.dataFactura) BETWEEN '" + data1 + "' AND '" + data2 + "' AND s.designacao='Consulta de Ginecologia & Obstetrícia' AND f1.codigoCategoria=1\n"
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
    
    public int getGinecologiaPorMedico(String data1, String data2) {
        conexao.Connectando();
        sql = "SELECT DISTINCT SUM(f1.quantidade) AS quantidade, s.designacao from    historicoclinico h  INNER JOIN triagem t ON h.codigoConsulta=t.idtriagem\n"
                + "INNER JOIN pacientes p ON t.codigoPaciente=p.idPaciente \n"
                + "INNER JOIN factura f ON f.codigoCliente=p.idPaciente\n"
                + "INNER JOIN factura_itens f1 ON f1.codigoFactura=f.idfactura\n"
                + "INNER JOIN servicos s ON s.idServico = f1.codigoProduto\n"
                + "INNER JOIN medicos m ON m.idMedico = h.codigoMedico\n"
                + "INNER JOIN especialidade_medico e ON m.codigoEspecialidade = e.idEspecialidade\n"
                + "WHERE  DATE(f.dataFactura) BETWEEN '" + data1 + "' AND '" + data2 + "' AND s.designacao='Consulta de Ginecologia' AND f1.codigoCategoria=1\n"
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

    public int getPlaneamentoFamiliarMedico(String data1, String data2) {
        conexao.Connectando();
        sql = "SELECT DISTINCT SUM(f1.quantidade) AS quantidade, s.designacao from    historicoclinico h  INNER JOIN triagem t ON h.codigoConsulta=t.idtriagem\n"
                + "INNER JOIN pacientes p ON t.codigoPaciente=p.idPaciente \n"
                + "INNER JOIN factura f ON f.codigoCliente=p.idPaciente\n"
                + "INNER JOIN factura_itens f1 ON f1.codigoFactura=f.idfactura\n"
                + "INNER JOIN servicos s ON s.idServico = f1.codigoProduto\n"
                + "INNER JOIN medicos m ON m.idMedico = h.codigoMedico\n"
                + "INNER JOIN especialidade_medico e ON m.codigoEspecialidade = e.idEspecialidade\n"
                + "WHERE  DATE(f.dataFactura) BETWEEN '" + data1 + "' AND '" + data2 + "' AND s.designacao='Consulta de Planeamento Familiar' AND f1.codigoCategoria=1\n"
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
    public int getPlaneamentoFamiliarMedico1(String data1, String data2) {
        conexao.Connectando();
        sql = "SELECT DISTINCT SUM(f1.quantidade) AS quantidade, s.designacao from    historicoclinico h  INNER JOIN triagem t ON h.codigoConsulta=t.idtriagem\n"
                + "INNER JOIN pacientes p ON t.codigoPaciente=p.idPaciente \n"
                + "INNER JOIN factura f ON f.codigoCliente=p.idPaciente\n"
                + "INNER JOIN factura_itens f1 ON f1.codigoFactura=f.idfactura\n"
                + "INNER JOIN servicos s ON s.idServico = f1.codigoProduto\n"
                + "INNER JOIN medicos m ON m.idMedico = h.codigoMedico\n"
                + "INNER JOIN especialidade_medico e ON m.codigoEspecialidade = e.idEspecialidade\n"
                + "WHERE  DATE(f.dataFactura) BETWEEN '" + data1 + "' AND '" + data2 + "' AND s.designacao='Planeamento Familiar' AND f1.codigoCategoria=1\n"
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
}
