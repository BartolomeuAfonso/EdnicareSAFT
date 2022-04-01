/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import CLINICA.modelo.Factura;
import CLINICA.modelo.FacturaItens;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sf.ce.conexao.ConexaoBancos;
import CLINICA.modelo.Medico;
import CLINICA.modelo.MedicoServicos;
import java.sql.Connection;
import java.sql.Date;

/**
 *
 * @author Familia do Fresco
 */
public class ControllerMedico {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Factura factura = new Factura();
    FacturaItens facturaItens = new FacturaItens();
    Connection con;

    public ControllerMedico(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvarMedico(Medico medico) {
        //  conexao.Connectando();
        sql = "INSERT INTO medicos(nomeCompleto,morada,contacto,email,numeroOrdem,Username,percentagemAGanhar,senha,codigoEspecialidade,tipoUtilizador,dataCadastro,limite)values(?,?,?,?,?,?,?,md5(?),?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, medico.getNomeCompleto());
            ps.setString(2, medico.getMorada());
            ps.setString(3, medico.getContacto());
            ps.setString(4, medico.getEmail());
            ps.setString(5, medico.getNumeroOrdem());
            ps.setString(6, medico.getUsername());
            ps.setDouble(7, medico.getPercetagemAGanhar());
            ps.setString(8, medico.getSenha());
            ps.setInt(9, medico.getCodigoEspecialidade());
            ps.setInt(10, medico.getCodigoTipoUtilizador());
            ps.setDate(11, (Date) medico.getDataCadastro());
            ps.setInt(12, medico.getLimite());
            ps.execute();
            //   JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //   conexao.DesconectaBanco();
    }

    public void editar(Medico medico, int codigo) {
        //  conexao.Connectando();
        sql = "UPDATE medicos SET nomeCompleto=?,morada=?,contacto=?,email=?,numeroOrdem=?,Username=?,percentagemAGanhar=?,codigoEspecialidade=?,limite=?  WHERE tipoUtilizador=" + codigo;
        System.out.println("Actualizaçao Médico:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, medico.getNomeCompleto());
            ps.setString(2, medico.getMorada());
            ps.setString(3, medico.getContacto());
            ps.setString(4, medico.getEmail());
            ps.setString(5, medico.getNumeroOrdem());
            ps.setString(6, medico.getUsername());
            ps.setDouble(7, medico.getPercetagemAGanhar());
            ps.setInt(8, medico.getCodigoEspecialidade());
            ps.setInt(9, medico.getLimite());
            ps.execute();
            //   JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //   conexao.DesconectaBanco();
    }

    public double getPercentagem(int codigo) {
        try {
            sql = "select percentagemAGanhar as max from medicos where idMedico=" + codigo;
            System.out.println("Ultima Factura:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            try {
                if (rs.next()) {
                    return rs.getDouble("max");
                }
            } catch (SQLException ex) {
                System.out.println("Erro:" + ex);
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return 0;
    }

    public void salvarMedicoColaboradores(Medico medico) {
        //  conexao.Connectando();
        sql = "INSERT INTO medicocolaboradores(nomeCompleto,morada,contacto,numeroOrdem,codigoEspecialidade,dataCadastro)values(?,?,?,?,?,now())";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, medico.getNomeCompleto());
            ps.setString(2, medico.getMorada());
            ps.setString(3, medico.getContacto());
            ps.setString(4, medico.getNumeroOrdem());
            ps.setInt(5, medico.getCodigoEspecialidade());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //   conexao.DesconectaBanco();
    }

    public void salvarMedicoServicos() {
        sql = "INSERT INTO medicoServico(dataCadastro)values(now())";
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

    }

    public void editarMedicoColaboradores(Medico medico, int codigo) {
        sql = "update medicocolaboradores set nomeCompleto =?,morada=?,contacto=?,numeroOrdem=?,codigoEspecialidade=? where idMedicoColaboradores =" + codigo;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, medico.getNomeCompleto());
            ps.setString(2, medico.getMorada());
            ps.setString(3, medico.getContacto());
            ps.setString(4, medico.getNumeroOrdem());
            ps.setInt(5, medico.getCodigoEspecialidade());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados Alterado com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //   conexao.DesconectaBanco();
    }

    public ArrayList<String> getNomeMedico() {
//        conexao.Connectando();
        sql = "SELECT * FROM medicos s where codigoStatus=1";
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

    public ArrayList<String> getNomeMedicoporLike(String nome) {
//        conexao.Connectando();
        sql = "SELECT * FROM medicos s where s.nomeCompleto like '%" + nome + "%' and codigoStatus=1";
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

    public ArrayList<String> getNomeMedicoColaboradores() {
//        conexao.Connectando();
        sql = "SELECT * FROM medicocolaboradores s where codigoStatus=1";
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

    public ArrayList<String> getNomeMedicoColaboradoresporLike(String nome) {
//        conexao.Connectando();
        sql = "SELECT * FROM medicocolaboradores s where nomeCompleto like '%" + nome + "%'";
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

    public ArrayList<String> getUsernameMedico() {
        //conexao.Connectando();
        sql = "SELECT Username from medicos s where codigoStatus=1";
        System.out.println("sql:" + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("s.Username"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public ArrayList<String> getEspecialidadeMedico(int codigo) {
        //conexao.Connectando();
        sql = "SELECT e.designacao FROM especialidade_medico e inner join medicos m\n"
                + "on e.idEspecialidade =m.codigoEspecialidade\n"
                + "where idMedico=" + codigo;
        System.out.println("sql:" + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("e.designacao"));
            }
        } catch (SQLException ex) {
        }
        return lista;
    }

    public String getNomeMedico(int codigo) {
        // conexao.Connectando();
        sql = "SELECT nomeCompleto from medicos s where idMedico=" + codigo;
        System.out.println("sql:" + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("nomeCompleto");
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public String getNomeMedicoColaboradores(int codigo) {
        // conexao.Connectando();
        sql = "SELECT nomeCompleto from medicocolaboradores s where idMedicoColaboradores=" + codigo;
        System.out.println("sql:" + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("nomeCompleto");
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public String getNumeroOrdem(int codigo) {
        // conexao.Connectando();
        sql = "SELECT numeroOrdem from medicos s where idMedico=" + codigo;
        System.out.println("sql:" + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("numeroOrdem");
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public String getEspecialidade(int codigo) {
        //conexao.Connectando();
        sql = "SELECT e.designacao FROM especialidade_medico e inner join medicos m\n"
                + "on e.idEspecialidade =m.codigoEspecialidade\n"
                + "where idMedico=" + codigo;
        System.out.println("sql:" + sql);
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("e.designacao");
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public int getCodigoMedico(String designacao) {
        //conexao.Connectando();
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

    public int getCodigoMedicoColaboradores(String designacao) {
        //conexao.Connectando();
        sql = "SELECT idMedicoColaboradores from medicocolaboradores where nomeCompleto ='" + designacao + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("idMedicoColaboradores");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public int getCodigoMedicoUsername(String designacao) {
//        conexao.Connectando();
        sql = "SELECT idMedico from medicos where Username ='" + designacao + "'";
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

    public int getCodigoMedicoEspecialidade(String designacao) {
        //conexao.Connectando();
        sql = "SELECT codigoEspecialidade from medicos where nomeCompleto ='" + designacao + "'";
        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("codigoEspecialidade");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

//    public void eliminar(int codigo) {
//        //   conexao.Connectando();
//        sql = "update medicocolaboradores set codigoStatus=2 where idMedicoColaboradores=" + codigo;
//        System.out.println("Teste" + sql);
//        try {
//            ps = con.prepareStatement(sql);
//            ps.execute();
//            JOptionPane.showMessageDialog(null, "Dados eliminado com Sucesso");
//
//        } catch (SQLException e) {
//            System.out.println("ERRO:" + e);
//        }
//    }
    public void eliminarColaboradores(int codigo) {
        //   conexao.Connectando();
        sql = "update medicocolaboradores set codigoStatus=2 where idMedicoColaboradores=" + codigo;
        System.out.println("Teste" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados eliminado com Sucesso");

        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
    }

    public void eliminarHonorarioMedico(int codigo, int codigoServico) {
        //   conexao.Connectando();
        sql = " delete from  honorariomedicoitens where codigoServico =" + codigoServico + " and codigoFactura1 =" + codigo;
        System.out.println("Teste" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados Eliminado com Sucesso");

        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
    }

    public void editarHonorarioMedico(int codigo, int codigoMedico) {
        //   conexao.Connectando();
        sql = " update honorariomedico set codigoUtilizador=" + codigoMedico + " where idhonorarioMedico =" + codigo;
        System.out.println("Teste" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados Alterado com Sucesso");

        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
    }

    // Factura para colaboradores
    public void salvarHonorario(Factura factura) {
        //  conexao.Connectando();
        sql = "INSERT INTO facturacolaboradores(data,codigoUtilizador,codigoColaboradores,Total,nFactura)values(?,?,?,?,?)";
        System.out.println("Consulta:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, factura.getDataVencimento());
            ps.setInt(2, factura.getCodigoUtilizador());
            ps.setInt(3, factura.getCodigoCliente());
            ps.setDouble(4, factura.getTotalFactura());
            ps.setString(5, factura.getEstado());
            ps.execute();
            //        JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //   conexao.DesconectaBanco();
    }

    public void salvarHonorarioItens(FacturaItens facturaItens) {
        //  conexao.Connectando();
        sql = "INSERT INTO facturacolaboradoresitens(codigoFactura1,codigoServico,preco,percentagem,percetagemRaio,percentagemEcografia5,percentagemEcografia7,percentagemEcografia10,percentagemMorfologia,percentagemEco,percentagemEle,percentagemConsulta)values(?,?,?,?,?,?,?,?,?,?,?,?)";
        //totalGEral
        System.out.println("Inserto:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, facturaItens.getCodigoFactura());
            ps.setInt(2, facturaItens.getCodigoServico());
            ps.setDouble(3, facturaItens.getPreco());
            ps.setDouble(4, facturaItens.getPercentagem());
            System.out.println("Laboratorio:" + facturaItens.getPercentagem());
            ps.setDouble(5, facturaItens.getRaioX());
            ps.setDouble(6, facturaItens.getEcografia());
            ps.setDouble(7, facturaItens.getEcografia7());
            ps.setDouble(8, facturaItens.getEcografia10());
            ps.setDouble(9, facturaItens.getEcografiaMorfologia());
            ps.setDouble(10, facturaItens.getEcocardiograma());
            ps.setDouble(11, facturaItens.getElectrocardiograma());
            ps.setDouble(12, facturaItens.getConsulta());
            ps.execute();
            //   JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //   conexao.DesconectaBanco();
    }

    public int getLastFacturaHonorario() {
        try {
            sql = "select max(codigo) as max from facturacolaboradores";
            System.out.println("Ultima Factura:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            try {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                System.out.println("Erro:" + ex);
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return 0;
    }
    // Factura para Honorário Médicos

    public void salvarHonorarioMedico(Factura factura) {
        //  conexao.Connectando();
        sql = "INSERT INTO honorariomedico(data,codigoUtilizador,codigoMedico,nFactura)values(now(),?,?,?)";
        System.out.println("Honorário Médico:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, factura.getCodigoUtilizador());
            ps.setInt(2, factura.getCodigoCliente());
            ps.setString(3, factura.getEstado());
            ps.execute();
            //        JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //   conexao.DesconectaBanco();
    }

    public void salvarHonorarioMedico1(Factura factura) {
        //  conexao.Connectando();
        sql = "INSERT INTO honorariomedico(data,codigoUtilizador,codigoMedico,nFactura)values(?,?,?,?)";
        System.out.println("Honorário Médico:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, factura.getDataVencimento());
            ps.setInt(2, factura.getCodigoUtilizador());
            ps.setInt(3, factura.getCodigoCliente());
            ps.setString(4, factura.getEstado());
            ps.execute();
            //   JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //   conexao.DesconectaBanco();
    }

    public void salvarMedicoHonorarioItens(FacturaItens facturaItens) {
        //  conexao.Connectando();
        sql = "INSERT INTO honorariomedicoitens(codigoFactura1,codigoServico,preco,percentagem,irt)values(?,?,?,?,?)";
        //totalGEral
        System.out.println("Inserto:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, facturaItens.getCodigoFactura());
            ps.setInt(2, facturaItens.getCodigoServico());
            ps.setDouble(3, facturaItens.getPreco());
            ps.setDouble(4, facturaItens.getPercentagem());
            ps.setDouble(5, facturaItens.getEcografia());
            ps.execute();
            // JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //   conexao.DesconectaBanco();
    }

    public int getLastMedicoFacturaHonorario() {
        try {
            sql = "select max(idhonorarioMedico) as max from honorariomedico";
            System.out.println("Ultima Factura:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            try {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                System.out.println("Erro:" + ex);
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return 0;
    }

    public int getLastMedicoServico() {
        try {
            sql = "select max(IdMedicoServico) as max from medicoServico";
            System.out.println("Ultima Factura:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            try {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                System.out.println("Erro:" + ex);
            }
            return 0;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return 0;
    }

    public void salvarMedicoServico(MedicoServicos medico) {
        //  conexao.Connectando();
        sql = "INSERT INTO servicoMedicos(codigoMedico,codigoServico,designacao,preco,precoNormal,precoPercentual,codigoMedicoServico)values(?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, medico.getCodigoMedico());
            ps.setInt(2, medico.getCodigoServico());
            ps.setString(3, medico.getDesignacao());
            ps.setDouble(4, medico.getPreco());
            ps.setDouble(5, medico.getPreconormal());
            ps.setDouble(6, medico.getPrecopercentual());
            ps.setInt(7, medico.getCodigoMedicoServico());
            ps.execute();
            //        JOptionPane.showMessageDialog(null, "Dados salvo com Sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        //   conexao.DesconectaBanco();
    }

    public ArrayList<MedicoServicos> getServicoMedico(int codigoMedico, int codigoServico) {
        sql = "select codigoMedico,codigoServico,preco,preconormal,precoPercentual from servicomedicos s where codigoMedico =" + codigoMedico + " and codigoServico =" + codigoServico;
        System.out.println("Consulta:" + sql);
        try {
            ArrayList<MedicoServicos> lista = new ArrayList<>();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                MedicoServicos tipo = new MedicoServicos();
                tipo.setCodigoMedico(rs.getInt(1));
                tipo.setCodigoServico(rs.getInt(2));
                tipo.setPreco(rs.getInt(3));
                tipo.setPreconormal(rs.getInt(4));
                tipo.setPrecopercentual(rs.getInt(5));
                lista.add(tipo);
                return lista;
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

        return null;
    }

    public ArrayList<MedicoServicos> getServico(int codigoMedico, int codigoServico) {
        sql = "select codigoServico from servicomedicos s where codigoMedico =" + codigoMedico + " and codigoServico =" + codigoServico;
        System.out.println("Consulta:" + sql);
        try {
            ArrayList<MedicoServicos> lista = new ArrayList<>();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                MedicoServicos tipo = new MedicoServicos();
                tipo.setCodigoServico(rs.getInt(1));
                return lista;
            }
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

        return null;
    }

    public String getMedicoServico(int codigoMedico, int codigoServico) {
        try {
            sql = "select codigoServico from servicomedicos s where codigoMedico =" + codigoMedico + " and codigoServico =" + codigoServico;
            System.out.println("Ultima Factura:" + sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            try {
                if (rs.next()) {
                    return rs.getString(1);
                }
            } catch (SQLException ex) {
                System.out.println("Erro:" + ex);
            }
            return null;
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return null;
    }

    public void getDeleteServicoColaborador(int codigoFactura, int codigoServico) {
        try {
            sql = "DELETE  FROM facturacolaboradoresitens  WHERE codigoFactura1='" + codigoFactura + "' and codigoServico='" + codigoServico + "'";
            System.out.println("" + sql);
            ps = con.prepareStatement(sql);
            ps.execute();

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }

    }

}
