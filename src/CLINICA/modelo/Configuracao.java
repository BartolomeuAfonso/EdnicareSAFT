/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author SONY PC
 */
public class Configuracao implements Serializable {

    private String ip;
    private String user;
    private String pass;

    public Configuracao() {
    }

    public Configuracao(String ip, String user, String pass) {
        this.ip = ip;
        this.user = user;
        this.pass = pass;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    public void gravar(Object modelo, String filNome) {

        try {
            ObjectOutputStream objectInputStream = new ObjectOutputStream(new FileOutputStream(new File("Relatorios/" + filNome + ".dat")));
            objectInputStream.writeObject(modelo);
            objectInputStream.close();
        } catch (IOException ex) {
            getLogger(Configuracao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object ler(String filNome) {
        Object ler = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("Relatorios/" + filNome + ".dat")));
            ler = (Object) objectInputStream.readObject();
        } catch (IOException ex) {
            getLogger(Configuracao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            getLogger(Configuracao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ler;
    }

    @Override
    public String toString() {
        return "Configuracao{" + "ip=" + ip + ", user=" + user + ", pass=" + pass + '}';
    }

}
