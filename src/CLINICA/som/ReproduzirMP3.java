/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLINICA.som;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

/**
 *
 * @author Probook
 */
public class ReproduzirMP3 {

    /**
     * Objeto para nosso arquivo MP3 a ser tocado
     */
    private File mp3;
    /**
     * Objeto Player da biblioteca jLayer. Ele tocará o arquivo MP3
     */
    private Player player;

    /**
     * Construtor que recebe o objeto File referenciando o arquivo MP3 a ser
     * tocado e atribui ao atributo MP3 da classe.
     *
     * @param mp3
     */
    public ReproduzirMP3(File mp3) {
        this.mp3 = mp3;
    }

    /**
     * Método que toca o MP3
     */
    public void play() {
        try {
            FileInputStream fis = new FileInputStream(mp3);
            BufferedInputStream bis = new BufferedInputStream(fis);
            this.player = new Player(bis);
            System.out.println("Tocando!");
            //     this.player.play();

            System.out.println("Terminado!");
        } catch (Exception e) {
            System.out.println("Problema ao tocar " + mp3);
            e.printStackTrace();
        }
    }

    public void pause() {
        this.player.close();
    }
}
