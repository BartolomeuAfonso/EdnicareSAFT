/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

;
import java.sql.Connection;
import java.sql.ResultSet;
import CLINICA.modelo.Empresa;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author Agostinho
 */


public class ControllerEmpresa {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerEmpresa(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public void salvar(Empresa empresas) {
        //    conexao.Connectando();
        sql = "INSERT INTO empresa(designacao,nif,telemovel,contaBancaria1,contaBancaria2,endereco,email,website,logotivo)VALUES(?,?,?,?,?,?,?,?,?)";
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, empresas.getNomeEmpresa());
            ps.setString(2, empresas.getNif());
            ps.setString(3, empresas.getTelefone());
            ps.setString(4, empresas.getConta1());
            ps.setString(5, empresas.getConta2());
            ps.setString(6, empresas.getEndereco());
            ps.setString(7, empresas.getEmail());
            ps.setString(8, empresas.getWebsite());
            ps.setString(9, empresas.getLogotipo());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");

        } catch (SQLException e) {
            System.out.println("SERA UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }

    public void salvarSeguro(Empresa empresas) {
        //  conexao.Connectando();
        sql = "INSERT INTO empresaseguros(designacao,telefone,nif,endereco,email,desconto)VALUES(?,?,?,?,?,?)";
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, empresas.getNomeEmpresa());
            ps.setString(2, empresas.getTelefone());
            ps.setString(3, empresas.getNif());
            ps.setString(4, empresas.getEndereco());
            ps.setString(5, empresas.getEmail());
            ps.setDouble(6, empresas.getDesconto());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");

        } catch (SQLException e) {
            System.out.println("SERA UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }

    public void editar(Empresa empresas, int codigo) {
        sql = "UPDATE empresas SET  designacao=?, Nif=?,contactos=?, contaBancaria1=?,contaBancaria1=?,endereco=?,email=?,logotipo=?,wbesite=? WHERE idEmpresa " + codigo;

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, empresas.getNomeEmpresa());
            ps.setString(2, empresas.getNif());
            ps.setString(3, empresas.getTelefone());
            ps.setString(4, empresas.getConta1());
            ps.setString(5, empresas.getConta2());
            ps.setString(6, empresas.getEndereco());
            ps.setString(7, empresas.getEmail());
            ps.setString(8, empresas.getLogotipo());
            ps.setString(9, empresas.getWebsite());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");

        } catch (SQLException e) {
            System.out.println("SERA UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }

    public void editarSeguro(Empresa empresas, int codigo) {
        sql = "UPDATE empresaseguros SET  designacao=?, nif=?,telefone=?,endereco=?,email=?,desconto =? WHERE idSeguros=" + codigo;
        System.out.println("Sql:" + sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, empresas.getNomeEmpresa());
            ps.setString(2, empresas.getNif());
            ps.setString(3, empresas.getTelefone());
            ps.setString(4, empresas.getEndereco());
            ps.setString(5, empresas.getEmail());
            ps.setDouble(6, empresas.getDesconto());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Alterado com Sucesso");

        } catch (SQLException e) {
            System.out.println("SERA UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }

    public void eliminarEmpresaSeguradora(int codigo) {
        sql = "DELETE FROM empresaseguros WHERE idSeguros=" + codigo;
        try {
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Eliminado com Sucesso");

        } catch (SQLException e) {
            System.out.println("SERA UPS,Deve Contactar com a DEVTEC:" + e);
        }

    }

    public String getLogotipo() {
        sql = "SELECT logotivo as Logotipo FROM empresa";

        try {
            // conexao.Connectando();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("Logotipo");
            }
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }

    public String getNomeEmpresa() {
        sql = "SELECT descricao as NomeEmpresa FROM empresa";
        System.out.println("Empresa:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getString("NomeEmpresa");
            }
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }

    public String getEndereco() {
        sql = "SELECT endereco as endereco FROM empresa";
        System.out.println("Empresa:" + sql);
        try {
            //   conexao.Connectando();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getString("endereco");
            }
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }

    public String getEmail() {
        sql = "SELECT email as email FROM empresa";
        System.out.println("Empresa:" + sql);
        try {
            //   conexao.Connectando();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getString("email");
            }
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }

    public String getNif() {
        sql = "SELECT nif as nif FROM empresa";
        System.out.println("Empresa:" + sql);
        try {
            //   conexao.Connectando();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getString("nif");
            }
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }

    public String getEmpresa(String campo) {
        sql = "select " + campo + " from empresa where codEmpresa = 1";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public String getWebSite() {
        sql = "SELECT website as website FROM empresa";
        System.out.println("Empresa:" + sql);
        try {
            //   conexao.Connectando();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getString("website");
            }
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }

    public String getContacto() {
        sql = "SELECT contacto as contacto FROM empresa";
        System.out.println("Empresa:" + sql);
        try {
            //   conexao.Connectando();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getString("contacto");
            }
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }

    public String getAnoFiiscal() {

        LocalDate date = LocalDate.now();

        return "" + date.getYear();
    }

    public String getSite() {
        sql = "SELECT website as website FROM empresa";

        try {
            //    conexao.Connectando();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getString("website");
            }
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }

    public ArrayList<Empresa> listar() {
        sql = "SELECT codEmpresa,descricao,nif,email,endereco,contacto,website FROM empresa WHERE codEmpresa=" + 1;
//        System.out.println("Teste" + sql);
        ArrayList<Empresa> list = new ArrayList<>();
        try {
            //   conexao.Connectando();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empresa empresas = new Empresa();
                empresas.setCodigo(rs.getInt("codEmpresa"));
                empresas.setNomeEmpresa(rs.getString("descricao"));
                empresas.setNif(rs.getString("nif"));
                empresas.setEmail(rs.getString("email"));
                empresas.setEndereco(rs.getString("endereco"));
                empresas.setTelefone(rs.getString("contacto"));
                empresas.setWebsite(rs.getString("website"));
                list.add(empresas);
            }

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

        return list;
    }

    public ArrayList<Empresa> getlistaEmpresa() {
        sql = "SELECT designacao from empresaseguros";
//        System.out.println("Teste" + sql);
        ArrayList<Empresa> list = new ArrayList<>();
        try {
            //  conexao.Connectando();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empresa empresas = new Empresa();
                empresas.setNomeEmpresa(rs.getString("designacao"));
                list.add(empresas);
            }

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

        return list;
    }

    
    public ArrayList<String> getEmpresaSeguradora() {
        //    conexao.Connectando();
        sql = "SELECT designacao from empresaseguros";
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
    public ArrayList<Empresa> getlistaEmpresaSeguro(int codigo) {
        sql = "SELECT *from empresaseguros where idSeguros=" + codigo;
        ArrayList<Empresa> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empresa empresas = new Empresa();
                empresas.setNomeEmpresa(rs.getString("designacao"));
                empresas.setNif(rs.getString("nif"));
                empresas.setEndereco(rs.getString("endereco"));
                empresas.setEmail(rs.getString("email"));
                empresas.setTelefone(rs.getString("telefone"));
                empresas.setDesconto(rs.getDouble("telefone"));
                list.add(empresas);
            }

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }

        return list;
    }

    public int getCodigoNumeracao(String designacao) {
        //  conexao.Connectando();
        sql = "SELECT numeradorConsulta from empresaseguros where designacao ='" + designacao + "'";
//        System.out.println("Teste:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("numeradorConsulta");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

}
