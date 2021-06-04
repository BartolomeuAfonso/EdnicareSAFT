/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sf.ce.utilizacoes;

import com.toedter.calendar.JDateChooser;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeSupport;
import static java.lang.Integer.parseInt;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static java.util.Calendar.getInstance;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.swing.JOptionPane;
import sf.ce.conexao.ConexaoBancos;

/**
 *
 * @author Router
 */
public class Data {

    ConexaoBancos conexao = new ConexaoBancos();
    /**
     *
     */
    public static final String PROP_CODIGO = "PROP_CODIGO";
    /**
     *
     */
    public static final String PROP_CODIGOSTATUS = "PROP_CODIGOSTATUS";
    /**
     *
     */
    public static final String PROP_DIA = "PROP_DIA";
    /**
     *
     */
    public static final String PROP_MES = "PROP_MES";
    /**
     *
     */
    public static final String PROP_ANO = "PROP_ANO";
    private static final Logger LOG = getLogger(Data.class.getName());

    /**
     *
     * @return
     */
    public java.sql.Date converteDataSql(Date data) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dataUtil = data;
        java.sql.Date dataSql = null;
        try {
            dataUtil = new java.sql.Date(dataUtil.getTime());
            dataSql = (java.sql.Date) dataUtil;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro de conversão da data: " + ex.getMessage());
        }
        return dataSql;
    }

    public static String getData(JDateChooser txtFacturaFornecedor) {
        try {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(txtFacturaFornecedor.getDate());
            int ano = gc.get(GregorianCalendar.YEAR);

            int mes = (gc.get(GregorianCalendar.MONTH) + 1);

            int dia = gc.get(GregorianCalendar.DATE);

            String d, m;
            if (dia < 10) {
                d = "0" + dia;
            } else {
                d = "" + dia;
            }
            if (mes < 10) {
                m = "0" + mes;
            } else {
                m = "" + mes;
            }

            return "" + ano + "-" + m + "-" + d;
        } catch (NullPointerException ex) {
            return "";
        }
    }

    public static String getDataActual() {
        Calendar calendario = getInstance();

        //buscar data
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int ano = calendario.get(Calendar.YEAR);

        mes++;
        String d, m;
        if (dia < 10) {
            d = "0" + dia;
        } else {
            d = "" + dia;
        }
        if (mes < 10) {
            m = "0" + mes;
        } else {
            m = "" + mes;
        }

        return "" + ano + "-" + m + "-" + d;

    }

    public static int getMesActual() {
        Calendar calendario = getInstance();
        int mes = calendario.get(Calendar.MONTH);
        mes++;
        return mes;
    }

    /**
     *
     * @param txtFacturaFornecedor
     * @return
     */
    /**
     *
     * @param txtFacturaFornecedor
     * @param h
     * @param m
     * @param s
     * @return
     */
    public static String getDataTime(JDateChooser txtFacturaFornecedor, int h, int m, int s) {
        try {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(txtFacturaFornecedor.getDate());

            int ano = gc.get(GregorianCalendar.YEAR);

            int mes = (gc.get(GregorianCalendar.MONTH) + 1);

            int dia = gc.get(GregorianCalendar.DATE);

            String di, me;
            if (dia < 10) {
                di = "0" + dia;
            } else {
                di = "" + dia;
            }
            if (mes < 10) {
                me = "0" + mes;
            } else {
                me = "" + mes;
            }

            return "" + ano + "/" + me + "/" + di + " " + h + ":" + m + ":" + s;
        } catch (NullPointerException ex) {
            return "";
        }
    }

    /**
     *
     * @return
     */
    public static String getDataTimeActual() {
        Calendar calendario = getInstance();

        //buscar data
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH) + 1;
        int ano = calendario.get(Calendar.YEAR);

        int h = calendario.get(Calendar.HOUR_OF_DAY);
        int m = calendario.get(Calendar.MINUTE);
        int s = calendario.get(Calendar.SECOND);

        String di, me;
        if (dia < 10) {
            di = "0" + dia;
        } else {
            di = "" + dia;
        }
        if (mes < 10) {
            me = "0" + mes;
        } else {
            me = "" + mes;
        }

        return "" + ano + "-" + me + "-" + di + " " + h + ":" + m + ":" + s;

    }

    /**
     *
     * @return
     */
    public static String getTimeActual() {
        Calendar calendario = getInstance();

        int h = calendario.get(Calendar.HOUR_OF_DAY);
        int m = calendario.get(Calendar.MINUTE);
        int s = calendario.get(Calendar.SECOND);

        return " " + h + ":" + m + ":" + s;

    }

    /**
     *
     * @param data
     * @param d
     */
    @SuppressWarnings("empty-statement")
    public static void setData(JDateChooser data, String d) {
        //2012-06-26 
        int dia = parseInt(d.substring(9, 10));;
        int mes = parseInt(d.substring(6, 7));;
        int ano = parseInt(d.substring(0, 4));

        data.setDate(new Date(ano - 1900, mes - 1, dia));
    }

    /**
     *
     * @param dias
     * @param data
     * @return
     */
    public static String getDataSumDay(int dias, JDateChooser data) {
        if (data.getDate() != null) {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(data.getDate());
            int ano = gc.get(GregorianCalendar.YEAR);
            int mes = (gc.get(GregorianCalendar.MONTH) + 1);
            int dia = gc.get(GregorianCalendar.DATE);
            dia += dias;
            if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                while (dia >= 30) {
                    mes++;
                    dia -= 29;
                    while (mes >= 12) {
                        ano++;
                        mes -= 12;
                    }
                }
            } else if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
                while (dia >= 31) {
                    mes++;
                    dia -= 30;
                    while (mes >= 12) {
                        ano++;
                        mes -= 12;
                    }
                }
            } else {
                while (dia >= 28) {
                    mes++;
                    dia -= 27;
                    while (mes >= 12) {
                        ano++;
                        mes -= 12;
                    }
                }
            }
            String d, m;
            if (dia < 10) {
                d = "0" + dia;
            } else {
                d = "" + dia;
            }
            if (mes < 10) {
                m = "0" + mes;
            } else {
                m = "" + mes;
            }

            return "" + ano + "/" + m + "/" + d;
        }
        return "";
    }

    /**
     *
     * @param dias
     * @param data
     * @return
     */
    @SuppressWarnings("empty-statement")
    public static String getDataSumDay(int dias, String data) {
        if (data != null) {
            int dia = parseInt(data.substring(8, 10));
            int mes = parseInt(data.substring(5, 7));
            int ano = parseInt(data.substring(0, 4));
            dia += dias;

            if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                while (dia >= 30) {
                    mes++;
                    dia -= 29;
                    while (mes >= 12) {
                        ano++;
                        mes -= 12;
                    }
                }
            } else if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
                while (dia >= 31) {
                    mes++;
                    dia -= 30;
                    while (mes >= 12) {
                        ano++;
                        mes -= 12;
                    }
                }
            } else {
                while (dia >= 28) {
                    mes++;
                    dia -= 27;
                    while (mes >= 12) {
                        ano++;
                        mes -= 12;
                    }
                }
            }
            String di, me;
            if (dia < 10) {
                di = "0" + dia;
            } else {
                di = "" + dia;
            }
            if (mes < 10) {
                me = "0" + mes;
            } else {
                me = "" + mes;
            }

            return "" + ano + "/" + me + "/" + di;

        }
        return "";
    }

    /**
     *
     * @param data
     * @return
     */
    public static boolean comparar(String data) {
        boolean b = false;

        int dia = parseInt(data.substring(8, 10));
        int mes = parseInt(data.substring(5, 7));
        int ano = parseInt(data.substring(0, 4));

        int dia1 = parseInt(getDataActual().substring(8, 10));
        int mes1 = parseInt(getDataActual().substring(5, 7));
        int ano1 = parseInt(getDataActual().substring(0, 4));

        if (ano > ano1) {
            b = true;
        } else if (ano == ano1) {
            if (mes > mes1) {
                b = true;
            } else if (mes == mes1) {
                if (dia >= dia1) {
                    b = true;
                }
            }
        }
        return b;
    }

    public static String getAnoActual() {
        Calendar calendario = getInstance();
        return calendario.get(Calendar.YEAR) + "";

    }
    private Integer codigo;
    private Integer codigoStatus;
    private int dia;
    private int mes;
    private int ano;
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    private final transient VetoableChangeSupport vetoableChangeSupport = new java.beans.VetoableChangeSupport(this);

    /**
     *
     */
    public Data() {
    }

    /**
     *
     * @param dia
     * @param mes
     * @param ano
     */
    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    @Override
    public String toString() {
        return getAno() + "-" + getMes() + "-" + getDia();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.getDia();
        hash = 31 * hash + this.getMes();
        hash = 31 * hash + this.getAno();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Data other = (Data) obj;
        if (this.getDia() != other.getDia()) {
            return false;
        }
        if (this.getMes() != other.getMes()) {
            return false;
        }
        return this.getAno() == other.getAno();
    }

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) throws PropertyVetoException {
        java.lang.Integer oldCodigo = this.codigo;
        vetoableChangeSupport.fireVetoableChange(PROP_CODIGO, oldCodigo, codigo);
        this.codigo = codigo;
        propertyChangeSupport.firePropertyChange(PROP_CODIGO, oldCodigo, codigo);
    }

    /**
     * @return the codigoStatus
     */
    public Integer getCodigoStatus() {
        return codigoStatus;
    }

    /**
     * @param codigoStatus the codigoStatus to set
     */
    public void setCodigoStatus(Integer codigoStatus) throws PropertyVetoException {
        java.lang.Integer oldCodigoStatus = this.codigoStatus;
        vetoableChangeSupport.fireVetoableChange(PROP_CODIGOSTATUS, oldCodigoStatus, codigoStatus);
        this.codigoStatus = codigoStatus;
        propertyChangeSupport.firePropertyChange(PROP_CODIGOSTATUS, oldCodigoStatus, codigoStatus);
    }

    /**
     * @return the dia
     */
    public int getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(int dia) throws PropertyVetoException {
        int oldDia = this.dia;
        vetoableChangeSupport.fireVetoableChange(PROP_DIA, oldDia, dia);
        this.dia = dia;
        propertyChangeSupport.firePropertyChange(PROP_DIA, oldDia, dia);
    }

    /**
     * @return the mes
     */
    public int getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(int mes) throws PropertyVetoException {
        int oldMes = this.mes;
        vetoableChangeSupport.fireVetoableChange(PROP_MES, oldMes, mes);
        this.mes = mes;
        propertyChangeSupport.firePropertyChange(PROP_MES, oldMes, mes);
    }

    /**
     * @return the ano
     */
    public int getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(int ano) throws PropertyVetoException {
        int oldAno = this.ano;
        vetoableChangeSupport.fireVetoableChange(PROP_ANO, oldAno, ano);
        this.ano = ano;
        propertyChangeSupport.firePropertyChange(PROP_ANO, oldAno, ano);
    }

    public static int getDiferenceDias(String d, String d1) {
        //2012-06-26 
        System.out.println("" + d + "  " + d1);
        int dia = parseInt(d.substring(8, 10));
        int mes = parseInt(d.substring(5, 7));
        int ano = parseInt(d.substring(0, 4));

        int dia1 = parseInt(d1.substring(8, 10));
        int mes1 = parseInt(d1.substring(5, 7));
        int ano1 = parseInt(d1.substring(0, 4));

        int anos = 0;
        int dias = 0;
        int messes = 0;
        System.out.println("dia " + dia + " mes " + mes + " ano " + ano);
        System.out.println("dia " + dia1 + " mes " + mes1 + " ano " + ano1);
        System.out.println("" + dia + ".." + dia1);

        if (dia > dia1) {
            dias = dia - dia1;
        }

        if (mes > mes1) {
            if (dia > dia1) {
                messes = (mes - mes1) * 30;
            } else {
                messes = 30 + (dia - dia1) + ((mes - mes1 - 1) * 30);
            }
        }
        if (ano > ano1) {
            if (mes > mes1) {
                if (dia > dia1) {
                    anos = (ano - ano1) * 365;
                }
            } else {
                anos = 365 + (30 + (dia - dia1) + ((mes - mes1 - 1) * 30));//+((ano-ano1-1)*365);
            }

        }

        System.out.println("" + dias + messes + anos);
        return dias + messes + anos;
    }
    
     public java.sql.Date converteDataSql2(Object data) {
        java.sql.Date dataSql = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date dataUtil = df.parse((String) data);
            
            try {
                dataUtil = new java.sql.Date(dataUtil.getTime());
                dataSql = (java.sql.Date) dataUtil;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro de conversão da data: " + ex.getMessage());
            }
           return dataSql; 
        } catch (ParseException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE,null, ex);
        }
        return dataSql;
    }
     

}
