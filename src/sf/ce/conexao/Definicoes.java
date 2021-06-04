/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sf.ce.conexao;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Probook
 */
public class Definicoes {

    private BDConexao conexao;
    public static File file;
    
     public Definicoes()
    {
        conexao = new BDConexao();
    }
    
      public String getImpressoraTicket()
    {
        String sql = "select Designacao from parametros where(Valor = 50)";
        ResultSet rs = conexao.executeQuery(sql);

        try
        {
            if(rs.next())
                return rs.getString(1);
        }
        catch(SQLException ex)
        {         
            ex.printStackTrace();
            return "Microsoft XPS Document Writer";
        }

        return "Microsoft XPS Document Writer";
    }
    
    public String getIpServido()
    {
        String sql = "select Designacao from parametros where(Codigo = 1)";
        ResultSet rs = conexao.executeQuery(sql);

        try
        {
            if(rs.next())
                return rs.getString(1);
        }
        catch(SQLException ex)
        {         
            ex.printStackTrace();
            return "localhost";
        }

        return "localhost";
    }
    
    public String getVersao()
    {
        String sql = "select Designacao from parametros where(Codigo = 6)";
        ResultSet rs = conexao.executeQuery(sql);

        try
        {
            if(rs.next())
                return rs.getString(1);
        }
        catch(SQLException ex)
        {         
            ex.printStackTrace();
            return "localhost";
        }

        return "localhost";
    }
    public String getUserBD()
    {
        String sql = "select Designacao from parametros where(Codigo = 3)";
        ResultSet rs = conexao.executeQuery(sql);

        try
        {
            if(rs.next())
                return rs.getString(1);
        }
        catch(SQLException ex)
        {            
            ex.printStackTrace();
            return "root";
        }

        return "root";
    }
    
    public String getHoraBackup()
    {
        String sql = "select valor from parametros where Codigo = 15";
        ResultSet rs = conexao.executeQuery(sql);

        try
        {
            if(rs.next())
                return rs.getString("valor");
        }
        catch(SQLException ex)
        {            
            ex.printStackTrace();
            return "root";
        }
        return "root";
    }
    
    public String getSenhaBD()
    {
        String sql = "select Designacao from parametros where(Codigo = 4)";
        ResultSet rs = conexao.executeQuery(sql);

        try
        {
            if(rs.next())
                return rs.getString(1);
        }
        catch(SQLException ex)
        {
                
            ex.printStackTrace();
            return "root";
        }

        return "root";
    }
    
    public int getQuantidadeFacturas()
    {
        String sql = "select Vida from parametros where(Codigo = 5)";
        ResultSet rs = conexao.executeQuery(sql);

        try
        {
            if(rs.next())
                return rs.getInt(1);
        }
        catch(SQLException ex)
        {
                
            ex.printStackTrace();
            return 2;
        }

        return 2;
    }
}
