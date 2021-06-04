/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CLINICA.modelo;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author Osvaldo Ramos
 */
public class Definicoes
{
    private ConexaoBancos conexao;
    public static final String ALARME_SOM = "relatorios/som/sirene.wav";
//    public static final String ALARME_SOM = "relatorios/som/Magia.mp3";
    
    public static int codigoPacienteEscolhidoNaTriagem;
    
    public Definicoes()
    {
        conexao = new ConexaoBancos();
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
            return "192.168.40.40";
        }

        return "192.168.40.40";
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
            return "192.168.40.40";
        }

        return "192.168.40.40";
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
