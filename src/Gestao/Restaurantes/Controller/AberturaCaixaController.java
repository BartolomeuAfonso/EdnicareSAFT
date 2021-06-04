package Gestao.Restaurantes.Controller;

import entidades.AberturaCaixa;
import entidades.FechoCaixa;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AberturaCaixaController {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
//    Conexao conexao = new Conexao();

    public AberturaCaixaController(Connection con) {
        this.con = con;
    }

    public void Salvar(AberturaCaixa aberturaCaixa) {
        sql = "INSERT INTO public.\"aberturaCaixa\"(\"Descricao\",\"HoraAbertura\", \"ValorAbertura\", \"CodigoCaixa\", \"DataAbertura\", \"CodigoUtilizador\", \"StatusCaixa\")\n"
                + "VALUES (?, ?, ?, ?, ?,?,?)";
        try {
//            con = new Conexao().ConexaoBD();
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, aberturaCaixa.getDescricao());
            ps.setString(2, aberturaCaixa.getHoraAbertura());
            ps.setDouble(3, aberturaCaixa.getValorAbertura());
            ps.setInt(4, aberturaCaixa.getCodigoCaixa());
            ps.setDate(5, (Date) aberturaCaixa.getDataAbertura());
            ps.setInt(6, aberturaCaixa.getCodigoUtilizador());
            ps.setString(7, aberturaCaixa.getStatusCaixa());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Caixa aberto com Sucesso");
        } catch (SQLException e) {
            System.out.println("UPS, Deve contactar com a DEVTEC" + e);
        }
    }

    public void Editar(AberturaCaixa aberturaCaixa, int Codigo) {
        sql = "UPDATE aberturaCaixa SET \"Descricao\"=?, \"HoraAbertura\"=?, \"ValorAbertura\"=?, \"CodigoCaixa\"=? WHERE Codigo=" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps.setString(1, aberturaCaixa.getDescricao());
            ps.setString(2, aberturaCaixa.getHoraAbertura());
            ps.setDouble(3, aberturaCaixa.getValorAbertura());
            ps.setInt(4, aberturaCaixa.getCodigoCaixa());
            ps.executeQuery();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados salvado com Sucesso");
        } catch (SQLException e) {
            System.out.println("UPS, Deve contactar com a DEVTEC" + e);
        }
    }

    public void Eliminar(int Codigo) {
        sql = "DELETE FROM \"aberturaCaixa\" WHERE Codigo=" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.executeQuery();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Eliminado com Sucesso");
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC" + e);

        }
    }

    public double valorAberturaCaixa(int user, Date data) {
        sql = "SELECT \"ValorAbertura\" AS ValorAbertura FROM  \"aberturaCaixa\" WHERE \"CodigoUtilizador\" =" + user + " AND \"DataAbertura\" ='" + data + "' AND \"StatusCaixa\"='ABERTO'";
        System.out.println("Teste:" + sql);
        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble("ValorAbertura");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    // Tudo sobre o caixa
    public ArrayList<String> getCaixa() {
        sql = "SELECT \"Descricao\" as Descricao  FROM public.caixas order by 1";
        ArrayList<String> list = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("Descricao"));
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }

    public int CodigoCaixa(String nome) {
        sql = "SELECT \"Codigo\" AS Codigo FROM caixas WHERE \"Descricao\" ='" + nome + "'";

        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("Codigo");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public String getNomeCaixaporCodigo(int codigo) {
        sql = "SELECT \"Descricao\" AS Descricao FROM caixas WHERE \"Codigo\" =" + codigo;

        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("Descricao");

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return "";
    }

    public void fechoCaixa(int Codigo) {
//        sql = "UPDATE  \"aberturaCaixa\" SET \"StatusCaixa\"='FECHADO'  WHERE  \"DataAbertura\"=CURRENT_DATE AND  \"CodigoUtilizador\"=" + Codigo;
        sql = "UPDATE  \"aberturaCaixa\" SET \"StatusCaixa\"='FECHADO'  WHERE   \"CodigoUtilizador\"=" + Codigo;
        System.out.println("Teste Fecho:" + sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.execute();
            ps.close();
            //       JOptionPane.showMessageDialog(null, "Dados salvado com Sucesso");
        } catch (SQLException e) {
            System.out.println("UPS, Deve contactar com a DEVTEC" + e);
        }
    }

    public int CodigoCaixaAberto(int codigo) {
        sql = "SELECT  \"CodigoUtilizador\" as CodigoUtilizador FROM public.\"aberturaCaixa\"\n"
                + "WHERE  \"StatusCaixa\" ='ABERTO' AND \"CodigoUtilizador\"=" + codigo;
//        sql = "SELECT  \"CodigoUtilizador\" as CodigoUtilizador FROM public.\"aberturaCaixa\"\n"
//                + "WHERE \"DataAbertura\"=CURRENT_DATE AND \"StatusCaixa\" ='ABERTO' AND \"CodigoUtilizador\"=" + codigo;
        System.out.println("Teste Utilizador:" + sql);
        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("CodigoUtilizador");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public int getCaixa(int codigo) {
        sql = "SELECT  \"CodigoCaixa\" as CodigoCaixa FROM public.\"aberturaCaixa\"\n"
                + "WHERE \"DataAbertura\"=CURRENT_DATE AND \"StatusCaixa\" ='ABERTO' AND \"CodigoUtilizador\"=" + codigo;
        //   System.out.println("Teste Utilizador:"+sql);
        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("CodigoCaixa");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }
    public int getLastAberturaByUser(int codigo) {
        sql = "SELECT  MAX(\"Codigo\") as Last FROM public.\"aberturaCaixa\" WHERE  \"StatusCaixa\" ='ABERTO' AND \"CodigoUtilizador\"=" + codigo;
        //   System.out.println("Teste Utilizador:"+sql);
        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return 0;
    }

    public boolean CodigoAbertura(int codigo) {
        sql = "SELECT  \"CodigoUtilizador\" as CodigoUtilizador FROM public.\"aberturaCaixa\"\n"
                + "WHERE  \"StatusCaixa\" ='ABERTO' AND \"CodigoUtilizador\"=" + codigo;
//        sql = "SELECT  \"CodigoUtilizador\" as CodigoUtilizador FROM public.\"aberturaCaixa\"\n"
//                + "WHERE \"DataAbertura\"=CURRENT_DATE AND \"StatusCaixa\" ='ABERTO' AND \"CodigoUtilizador\"=" + codigo;

        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return false;
    }
    public boolean verificarStatus(int codigo) {
        sql = "SELECT  * FROM public.\"aberturaCaixa\"\n"
                + "WHERE  \"StatusCaixa\" ='ABERTO' AND \"Codigo\"=" + codigo;
//        sql = "SELECT  \"CodigoUtilizador\" as CodigoUtilizador FROM public.\"aberturaCaixa\"\n"
//                + "WHERE \"DataAbertura\"=CURRENT_DATE AND \"StatusCaixa\" ='ABERTO' AND \"CodigoUtilizador\"=" + codigo;

        try {
//            con = conexao.ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return false;
    }

    // Tudo sobre fecho de Caixa
    public void salvarFecho(FechoCaixa fechoCaixa) {
        sql = "INSERT INTO public.\"fechoCaixa\"(\n"
                + "\"Descricao\", \"HoraFecho\", \"ValorAbertura\", \"ValorFecho\", \"valorCash\", \"valorMulticaixa\",\"DataFecho\", \"CodigoAberturaCaixa\", \"CodigoUtilizador\")\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?);";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, fechoCaixa.getDescricao());
            ps.setString(2, fechoCaixa.getHoraFecho());
            ps.setDouble(3, fechoCaixa.getValorAbertura());
            ps.setDouble(4, fechoCaixa.getValorFecho());
            ps.setDouble(5, fechoCaixa.getValorCash());
            ps.setDouble(6, fechoCaixa.getValorMulticaixa());
            ps.setDate(7, (Date) fechoCaixa.getDataFecho());
            ps.setInt(8, fechoCaixa.getCodigoCaixa());
            ps.setInt(9, fechoCaixa.getCodigoUtilizador());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Caixa Fechado com Sucesso");
        } catch (SQLException e) {
            System.out.println("UPS, Deve contactar com a DEVTEC" + e);
        }
    }

    public int getLastInsertFechoCaixa() {
        String sql = "SELECT MAX(\"Codigo\") as UltimoCodigo FROM \"fechoCaixa\" ";

        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("UltimoCodigo");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    public int getLastInsertAbertura(int usercode) {
        String sql = "SELECT MAX(\"Codigo\") as UltimoCodigo FROM \"aberturaCaixa\" where \"CodigoUtilizador\"= "+usercode;

        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("UltimoCodigo");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    public boolean getLastAbertura(int usercode) {
        String sql = "SELECT MAX(\"Codigo\") as UltimoCodigo FROM \"aberturaCaixa\" where \"CodigoUtilizador\"= "+usercode+" AND \"StatusCaixa\" ='FECHADO'";

        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean verificarCaixaAberto(int codigo) {
        sql = "SELECT\n"
                + "     aberturaCaixa.\"CodigoCaixa\" AS aberturaCaixa_CodigoCaixa\n"
                + "FROM\n"
                + " \"public\".\"aberturaCaixa\" aberturaCaixa\n"
                + "WHERE \"DataAbertura\"=CURRENT_DATE AND aberturaCaixa.\"StatusCaixa\"='FECHADO' AND  aberturaCaixa.\"CodigoCaixa\"=" + codigo;
        //      System.out.println("Teste:"+sql);
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            ps.close();
        } catch (SQLException ex) {

            return false;
        }
        return false;
    }
}
