/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.io.Serializable;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Bartolomeu José Afonso
 */
public class IO {

    public IO() {
    }

    public void write(Object classe, String fileNome) {

        try {
            ObjectOutputStream objectInputStream = new ObjectOutputStream(new FileOutputStream(new File("relatorios/" + fileNome + ".dat")));
            objectInputStream.writeObject(classe);
            objectInputStream.close();
        } catch (IOException ex) {
            getLogger(Configuracao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object read(String fileNome) {
        Object read = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("relatorios/" + fileNome + ".dat")));
            read = (Object) objectInputStream.readObject();
        } catch (IOException ex) {
            getLogger(Configuracao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            getLogger(Configuracao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return read;
    }

    public void salvar(Config config) {
//        Configuracao configuracao = new Configuracao("localhost", "root", "root", getMc(), "2017-12-24", "2018-05-31");
        write(config, "conf");
    }

    public String getMc() {
        String macAddr = null;
        List<String> Enderecomac = new ArrayList<String>();
        StringBuilder sb = null;
        try {
            Enumeration<NetworkInterface> eth = NetworkInterface
                    .getNetworkInterfaces();
            while (eth.hasMoreElements()) {
                NetworkInterface eth0 = eth.nextElement();
                byte[] mac = eth0.getHardwareAddress();
                sb = new StringBuilder();
                if (mac != null) {
                    for (int i = 0; i < mac.length; i++) {
                        String aux = String.format("%02X%s", mac[i],
                                (i < mac.length - 1) ? "-" : "");
                        sb.append(aux);
                    }
                    if (sb.toString().length() <= 18) {
                        macAddr = sb.toString();
                    }
                    String a = String.valueOf(sb);
                    System.out.println("Mac 1 " + sb);
                    Enderecomac.add(a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Enderecomac.get(0).toString();
    }

    public static void main(String[] args) {

        IO io = new IO();
//        Config c = new Config("localhost", "devtec", "postgres", io.getMc(), "2017-12-24", "2022-09-24");
        Config c = (Config) new IO().read("conf");
//        io.salvar(c);
        System.out.println("ip--> " + c.getIp());
        System.out.println("user--> " + c.getUsuario());
        System.out.println("senha--> " + c.getSenha());
        System.out.println("mac--> " + c.getMc());

    }

}
