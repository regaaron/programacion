package Plantas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Menu extends JComponent {
    JFrame jf;
   
    int x=1334;
    int y=750;
    BufferedImage fondo,play,moregames;
    BufferedImage img=null;
    BackgroundSound menusound;
    Menu(JFrame jf) {
        this.setVisible(true);
        this.setPreferredSize(new Dimension(x, y));
        this.jf=jf;
        cagarImagenes();
        menusound=new BackgroundSound("/Plantas/Java/resources/start_menu.wav");
       // menusound.clip.loop(Clip.LOOP_CONTINUOUSLY);
        img=fondo;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getX()>650&&e.getX()<1025&&e.getY()>221&&e.getY()<276||
                (e.getX()>707&&e.getX()<903&&e.getY()>146&&e.getY()<205)){
                    salir();
                }
                
            }
            
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
              //  System.out.println("x: " +e.getX());
               // System.out.println("y: " +e.getY());

                
                img=fondo;
                if((e.getX()>650&&e.getX()<1025&&e.getY()>221&&e.getY()<276)||
                    (e.getX()>707&&e.getX()<903&&e.getY()>146&&e.getY()<205)){
                   img=play;
                }
                    
                if(e.getX()>663&&e.getX()<900&&e.getY()>355&&e.getY()<511){
                    img=moregames;
                }
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
              
            }
        });
        setFocusable(true);
    }

    public void cagarImagenes(){
        try {
            fondo=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/resources/fondoz.jpeg"));
            play=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/resources/play.jpeg"));
            moregames=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/resources/moregames.jpeg"));
            
        } catch (Exception e) {
            System.out.println("Error al cargar imagenes del menu "+e.getMessage());
        }
    }
    public void salir(){
        JFrame jf2 = new JFrame("Juego");
                jf2.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
                // jf.setLocationRelativeTo(null);
                // jf.setResizable(false);
               menusound.clip.stop();
                Plantas demo1 = new Plantas();
                jf2.getContentPane().add(demo1);

                // jf.getContentPane().add(demo1);
                jf2.pack();
                jf2.setVisible(true);
                demo1.cicloPrincipalJuego();
        this.jf.dispose();
    }

    @Override
    public void paint(Graphics g) {
        
        g.setColor(Color.black);
        g.fillRect(0, 0, x, y);
        g.drawImage(img, 0, 0,x,y, this);
        
    }

}
