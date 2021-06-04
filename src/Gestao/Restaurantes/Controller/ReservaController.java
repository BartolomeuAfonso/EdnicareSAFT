/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestao.Restaurantes.Controller;

import entidades.Reservas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Agostinho
 */
public class ReservaController {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    public ReservaController(Connection con) {
        this.con = con;
    }

    public void Salvar(entidades.Reservas reservas) {
        sql = "INSERT INTO reserva(\"HoraAbertura\",\"HoraFecho\",\"CodigoMesa\",\"CodigoCliente\")VALUES (?, ?, ?, ?)";
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, reservas.getHoraAbertura());
            ps.setString(2, reservas.getHoraFecho());
            ps.setInt(3, reservas.getCodigoMesa());
            ps.setInt(4, reservas.getCodigoCliente());
            ps.executeQuery();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
    }

    public void Editar(entidades.Reservas reservas, int Codigo) {
        sql = "UPDATE reserva SET \"HoraAbertura\"=?,\"HoraFecho\"=?,\"CodigoMesa\"=?,\"CodigoCliente\"=? WHERE Codigo =" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.setString(1, reservas.getHoraAbertura());
            ps.setString(2, reservas.getHoraFecho());
            ps.setInt(3, reservas.getCodigoMesa());
            ps.setInt(4, reservas.getCodigoCliente());
            ps.executeQuery();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");

        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
    }

    public void Eliminar(int Codigo) {
        sql = "DELETE FROM reserva WHERE Codigo =" + Codigo;
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            ps.executeQuery();
            ps.close();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso");
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
    }

    public ArrayList<entidades.Reservas> listar() {
        ArrayList<entidades.Reservas> list = new ArrayList<>();
        try {
//            con = new Conexao().ConexaoBD();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Reservas reservas = new Reservas(rs.getInt("Codigo"), rs.getInt("CodigoMesa"), rs.getInt("CodigoCliente"), rs.getString("HoraAbertura"), rs.getString("HoraFecho"));
                list.add(reservas);
            }
             ps.close();
        } catch (SQLException e) {
            System.out.println("UPS,Deve Contactar com a DEVTEC:" + e);
        }
        return list;
    }

}
