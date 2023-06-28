package Plantas;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

public class Balas {
    int x,y;
    int xfinal;
    BufferedImage bala=null;
    Plantas p;
    BackgroundSound danio=new BackgroundSound("/Plantas/Java/resources/splat.wav");
    BackgroundSound disparo=new BackgroundSound("/Plantas/Java/resources/peashooter_attack.wav");
    Balas(Plantas p,int x,int y){
        this.p=p;
        this.x=x+p.pixel;
        this.y=y+p.pixel/4;
        this.xfinal=p.screenX-300;
        try{
            bala=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/bullet/peabullet.png"));
        }catch(IOException e){
            System.out.println("Error al cargar bala\n"+e.getMessage());
        }

        disparo.clip.start();

    }

    public void draw(Graphics2D g2){
            g2.drawImage(bala, x, y, p.pixel/3,p.pixel/3,null);
            if(x<xfinal){
                x+=4.5;
            }
        
    }

    public boolean colision(){
        for(zombies zombie:p.vectorZombies){
            if((this.x+4.5)+p.pixel/4>=zombie.x&&(this.x+4.5)+p.pixel/4<=zombie.x+p.pixel&&this.y>=zombie.y&&this.y<=zombie.y+p.pixel){
                danio.clip.start();
                zombie.vida-=10;
                return true;
            }
            
        }
        return false;
    }
    
}
