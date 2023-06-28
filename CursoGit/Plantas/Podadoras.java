/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Plantas;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOError;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author aaron
 */
public class Podadoras {
    int x,y;
    BufferedImage imgPodadora;
    int xmax;
    Plantas p;
    boolean activadar=false;
    Podadoras(int y,Plantas p){
            this.p=p;
            this.y=p.extraArriba+(y*p.pixel);
            this.x=p.extraDer+p.pixel;
            cargarImagen();
    }
    
    public void draw(Graphics2D g2){
        g2.drawImage(imgPodadora, x,y, p.pixel,p.pixel,p);
        colision();
        fisica();
    }
    
    public void fisica(){
         
          if(activadar){
            x+=5;
            if(x>p.screenX-p.pixel*4){
                p.vectorPodadoras.remove(this);
            }
        }
    }
    
    public void colision(){
    for(zombies zombie:p.vectorZombies){

                if((this.x)+p.pixel/4>=zombie.x&&(this.x)+p.pixel/4<=zombie.x+p.pixel
                        &&this.y+p.pixel/4>=zombie.y&&this.y+p.pixel/4<=zombie.y+p.pixel){
                    activadar=true;
                    zombie.vida=-10;

                }
      }
    
        }
    
    
    public void cargarImagen(){
        try{
            imgPodadora=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/resources/podadora.png"));
        }catch(IOException e){
            System.out.println("Error podadora");
        }
    }
}
