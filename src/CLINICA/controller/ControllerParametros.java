/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author Desenvolvimento
 */
public class ControllerParametros {

    ConexaoBancos conexao = new ConexaoBancos();
    String sql;
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public ControllerParametros(Connection con) {
        this.con = conexao.ConexaoBD();
    }

    public double getvalorApagar() {
        try {
            sql = "SELECT valor FROM parametro p where codParametro =" + 7;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("valor");
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

    public int getParamento(String designacao) {
        sql = "SELECT valor FROM parametro p where designacao ='" + designacao + "'";
        System.out.println("Consulta:" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("valor");
            }
        } catch (SQLException e) {
            System.out.println("ERRO:" + e);
        }
        return 0;
    }

    public double getValorExames() {
        try {
            sql = "SELECT valor FROM parametro p where codParametro =" + 6;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("valor");
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

    public double getValorConsulta() {
        try {
            sql = "SELECT valor FROM parametro p where codParametro =" + 7;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("valor");
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

    public double getValorRaioX() {
        try {
            sql = "SELECT valor FROM parametro p where codParametro =" + 10;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("valor");
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

    public double getValorEcografia5() {
        conexao.Connectando();
        try {
            sql = "SELECT valor FROM parametro p where codParametro =" + 13;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("valor");
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

    public double getValorEcografiaCola5() {
        conexao.Connectando();
        try {
            sql = "SELECT valor FROM parametro p where codParametro =" + 18;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("valor");
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

    public double getValorEcografiaCola20() {
        conexao.Connectando();
        try {
            sql = "SELECT valor FROM parametro p where codParametro =" + 19;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("valor");
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

    public double getValorEcografia7() {
        conexao.Connectando();
        try {
            sql = "SELECT valor FROM parametro p where codParametro =" + 14;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("valor");
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

    public double getValorEcografia10() {
        conexao.Connectando();
        try {
            sql = "SELECT valor FROM parametro p where codParametro =" + 15;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("valor");
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

    public double getValorEcografia20() {
        conexao.Connectando();
        try {
            sql = "SELECT valor FROM parametro p where codParametro =" + 16;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("valor");
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

    public double getValorCardiologia() {
        conexao.Connectando();
        try {
            sql = "SELECT valor FROM parametro p where codParametro =" + 11;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("valor");
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

    public double getElectroColaborador() {
        conexao.Connectando();
        try {
            sql = "SELECT valor FROM parametro p where codParametro =" + 20;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("valor");
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

    public double getValorElectrocardiograma() {
        conexao.Connectando();
        try {
            sql = "SELECT valor FROM parametro p where codParametro =" + 17;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("valor");
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

    public double getValorIrt() {
        try {
            sql = "SELECT valor FROM parametro p where codParametro =" + 9;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("valor");
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

    public double getValorIrtMedico() {
        try {
            sql = "SELECT valor FROM parametro p where codParametro =" + 12;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("valor");
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

    public double getValorIPSELO() {
        try {
            sql = "SELECT valor FROM parametro p where codParametro =" + 1;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("valor");
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

    public int getValorImpressao() {
        try {
            sql = "SELECT valor FROM parametro p where codParametro =" + 8;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getInt("valor");
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

    ///////////////////////// Percentagem ///////////////////////////////////
    public double getPercentagemExames() {
        try {
            sql = "SELECT percentagem FROM parametro p where codParametro =" + 6;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("percentagem");
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

    public double getPercentagemConsulta() {
        try {
            sql = "SELECT percentagem FROM parametro p where codParametro =" + 7;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("percentagem");
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

    public double getPercentagemRaioX() {
        try {
            sql = "SELECT percentagem FROM parametro p where codParametro =" + 10;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("percentagem");
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

    public double getPercentagemEcografia() {
        try {
            sql = "SELECT percentagem FROM parametro p where codParametro =" + 11;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("percentagem");
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

    public double getPercentagemEcografia5() {
        try {
            sql = "SELECT percentagem FROM parametro p where codParametro =" + 13;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("percentagem");
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

    public double getPercentagemEcografia7() {
        try {
            sql = "SELECT percentagem FROM parametro p where codParametro =" + 14;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("percentagem");
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

    public double getPercentagemEcografia10() {
        try {
            sql = "SELECT percentagem FROM parametro p where codParametro =" + 15;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("percentagem");
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

    public double getPercentagemEcografia20() {
        try {
            sql = "SELECT percentagem FROM parametro p where codParametro =" + 16;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("percentagem");
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

    public double getPercentagemEcografia571020() {
        try {
            sql = "SELECT percentagem FROM parametro p where codParametro =" + 18;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("percentagem");
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
    
     public double getPercentagemElectroCardiograma() {
        try {
            sql = "SELECT percentagem FROM parametro p where codParametro =" + 17;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    return rs.getDouble("percentagem");
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
}
