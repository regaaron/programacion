package Plantas;
import java.awt.Graphics2D;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Soles {
   int solx;
   int soly,solyfinal;
    BufferedImage [] soles = new BufferedImage[22];
    BufferedImage img=null;
    Plantas p;
    int frame=0;
    int cambio=1;
    BackgroundSound sound= new BackgroundSound("/Plantas/Java/resources/bien.wav");
    Soles(Plantas p){
        Random random = new Random();
      
        solx = random.nextInt((p.screenX-p.extraDer-p.pixel*2)-p.extraxIzq) + p.extraxIzq;
        solyfinal = random.nextInt((p.screenY-200)-40) + 40;
        soly=10;
        this.p=p;
        cargarImagen();
       
    }

    Soles(Plantas p,int x,int y){
        this.p=p;
        cargarImagen();
        solx=x;
        soly=y;
        solyfinal=y;
    }

    public void cargarImagen(){
        try {
            soles[0] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_00.png"));
            soles[1] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_01.png"));
            soles[2] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_02.png"));
            soles[3] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_03.png"));
            soles[4] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_04.png"));
            soles[5] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_05.png"));
            soles[6] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_06.png"));
            soles[7] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_07.png"));
            soles[8] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_08.png"));
            soles[9] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_09.png"));
            soles[10] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_10.png"));
            soles[11] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_11.png"));
            soles[12] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_12.png"));
            soles[13] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_13.png"));
            soles[14] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_14.png"));
            soles[15] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_15.png"));
            soles[16] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_16.png"));
            soles[17] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_17.png"));
            soles[18] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_18.png"));
            soles[19] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_19.png"));
            soles[20] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_20.png"));
            soles[21] = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunlight/frame_21.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean mouseClicked(MouseEvent evento){
            if(evento.getX()>solx&&evento.getX()<solx+p.pixel
                &&evento.getY()>soly&&evento.getY()<soly+p.pixel){
                    p.puntos+=25;   
                    sound.clip.start();      
                    return true;
                   
                }else{
                    return false;
                }
    }

    public void draw(Graphics2D g2){
        selecFrame();
        if(soly<solyfinal){
            g2.drawImage(img, solx,soly,p.pixel/2,p.pixel/2,null);
            soly+=1.5;
        }else{
            g2.drawImage(img, solx, soly, p.pixel/2,p.pixel/2,null);
        }
        
        cambio++;
        if(cambio==3){
            frame++;
            cambio=0;
        }

            frame++;
        
    }

    public void selecFrame(){
       
        switch(frame){
            case 0: img=soles[0]; break;
            case 1: img=soles[1]; break;
            case 2: img=soles[2]; break;
            case 3: img=soles[3]; break;
            case 4: img=soles[4]; break;
            case 5: img=soles[5]; break;
            case 6: img=soles[6]; break;
            case 7: img=soles[7]; break;
            case 8: img=soles[8]; break;
            case 9: img=soles[9]; break;
            case 10: img=soles[10]; break;
            case 11: img=soles[11]; break;
            case 12: img=soles[12]; break;
            case 13: img=soles[13]; break;
            case 14: img=soles[14]; break;
            case 15: img=soles[15]; break;
            case 16: img=soles[16]; break;
            case 17: img=soles[17]; break;
            case 18: img=soles[18]; break;
            case 19: img=soles[19]; break;
            case 20: img=soles[20]; break;
            case 21: img=soles[21]; break;
            default: frame=0;
        }
    }
    

}
