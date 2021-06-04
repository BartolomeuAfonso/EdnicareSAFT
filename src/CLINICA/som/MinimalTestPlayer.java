///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package CLINICA.som;
//
//import com.sun.jna.NativeLibrary;
//import java.awt.BorderLayout;
//import java.awt.Canvas;
//import java.awt.Frame;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//
//import java.util.ArrayList;
//import java.util.List;
//import uk.co.caprica.vlcj.player.MediaPlayerFactory;
//import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
//import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
//import uk.co.caprica.vlcj.runtime.RuntimeUtil;
//import uk.co.caprica.vlcj.runtime.windows.WindowsRuntimeUtil;
//
///**
// *
// * @author Probook
// */
//public class MinimalTestPlayer {
//    
//    public MinimalTestPlayer() {
//        registerLibrary();
//    }
//
//    /**
//     * Executa arquivo de audio/video
//     * @param filename
//     */
//    public void play(final String filename) {
//        final Canvas videoSurface = new Canvas();
//        final CanvasVideoSurface videoSurface1 = new CanvasVideoSurface(videoSurface, null);
//        final Frame frame = buildFrame(videoSurface1);
//        final List<String> vlcArgs
//                = new ArrayList<String>();
//        
//        configureParameters(vlcArgs);
//        
//        final EmbeddedMediaPlayer mediaPlayer
//                = createPlayer(vlcArgs, videoSurface1);
//        mediaPlayer.playMedia(filename);
//    }
//
//    /**
//     * Importante: Informa onde está a libvlc, que contem as funções nativas de
//     * manipulacao do player
//     *
//     * Windows: libvlc.dll Linux: libvlc.so
//     */
//    private void registerLibrary() {
//        NativeLibrary.addSearchPath("libvlc",
//                "C:\\Program Files (x86)\\VideoLAN\\VLC\\");
//
//        // Windows 64 bits:
//        // NativeLibrary.addSearchPath("libvlc", 
////        "c:\\Program Files (x86)\\VideoLAN\\VLC\\");
//    }
//    
//      /**
//     * Configura parametros do VLC
//     */
//   
//
//     private void configureParameters(final List<String> vlcArgs) {
//        vlcArgs.add("--no-plugins-cache");
//        vlcArgs.add("--no-video-title-show");
//        vlcArgs.add("--no-snapshot-preview");
//
//        // Importante, se esse parametro nao 
////        for configurado no Windows, a aplicacao nao funcionara
//        if (RuntimeUtil.isWindows()) {
//            vlcArgs.add("--plugin-path="
//                    + WindowsRuntimeUtil.getVlcInstallDir() + "\\plugins");
//        }
//    }
//    /**
//     * Cria frame onde será exibido o filme
//     */
//    private Frame buildFrame(final CanvasVideoSurface videoSurface) {
//        final Frame f = new Frame("Test Player");
//        f.setSize(1200, 750);
//        f.addWindowListener(new WindowAdapter() {
//            
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });
//        f.setLayout(new BorderLayout());
//        f.add(videoSurface.canvas(), BorderLayout.CENTER);
//        f.setVisible(true);
//        f.setLocationRelativeTo(null);
//        return f;
//    }
//
//  
//
//    /**
//     * Constroi o player
//     */
//    private EmbeddedMediaPlayer createPlayer(final List<String> vlcArgs, final CanvasVideoSurface videoSurface) {
//        final MediaPlayerFactory factory = new MediaPlayerFactory(vlcArgs.toArray(new String[vlcArgs.size()]));
////        EmbeddedMediaPlayer mediaPlayer = factory.newEmbeddedMediaPlayer(null);
////        mediaPlayer.setVideoSurface(videoSurface);
////        return mediaPlayer;
////    }
//    
////    public static void main(String[] args) throws InterruptedException {
////        MinimalTestPlayer player = new MinimalTestPlayer();
////        // Pode ser MP4, AVI, MOV, MKV, WMA, MPG, MP3, WAV, etc.
////        player.play("C:\\Users\\Probook\\Videos\\Como reproduzir Mp3 com Java Parte 1 (Programação Noob).mp4");
////
////        // Aguarda janela do player ser fechada
////        Thread.currentThread().join();
////    }
//}
