package Gestao.Restaurantes.Controller;

import entidades.Licenca;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LicencaController {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
//    Conexao conexao = new Conexao();

    public LicencaController(Connection con) {
        this.con = con;
    }

    public void update(Licenca model) {
        sql = "UPDATE licenca SET  \"mac\"=?, \"data_inicial\"=?,\"data_final\"=? where \"codigo\"=? ";
        System.out.println("Consulta:"+sql);
        java.sql.Date data = new java.sql.Date(model.getDataInicial().getTime());
        java.sql.Date data1 = new java.sql.Date(model.getDataFinal().getTime());
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, model.getMac());
            ps.setDate(2, data);
            ps.setDate(3, data1);
            ps.setInt(4, 1);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro ao salvar " + e);
        }
    }

    public int getDiferencaDatas(Date data1, Date data2) {
    
        try {

            String sql = "SELECT ( DATE ('" + data1 + "') - DATE ('" + data2 + "') )";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            int next = 0;
            try {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return 1;
        } catch (SQLException ex) {
//            Logger.getLogger(Utilitarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 10;
    }

    public Date getDataAtivacao() {

        try {

            String sql = "SELECT data_final from licenca";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            int next = 0;
            try {
                if (rs.next()) {
                    return rs.getDate(1);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } catch (SQLException ex) {
//            Logger.getLogger(Utilitarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
