package Plantas;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

public class Girazol {
    int x, y;
    BufferedImage [] imagenes = new BufferedImage[18];
    BufferedImage girazol=null;
    Plantas p;
    int frame=0;
    int contador=0;
    int contador2=0;
    int contador3=0;
    int vida=100;
    CopyOnWriteArrayList <Soles> vectorSoles2;

    Girazol(Plantas p,int x,int y){
        this.p=p;
        this.x=p.extraxIzq+(x*p.pixel);
        this.y=p.extraArriba+(y*p.pixel);
        vectorSoles2=p.vectorSoles2;

        cargarImagenes();
    }
    
    public void colision(){

        for(zombies zombie:p.vectorZombies){
            if((this.x)+p.pixel>=zombie.x&&(this.x)+p.pixel<=zombie.x+p.pixel&&this.y>=zombie.y&&this.y<=zombie.y+p.pixel/2){
                contador3++;
                if(contador3==30*2.5){//30 frames y 10 son los segundos 
                    vida-=25;
                    contador3=0;
                }
                
            }
            
        }
    }

    public boolean eliminar(MouseEvent e){
        
            if(e.getX()>=this.x&&e.getX()<=this.x+p.pixel&&e.getY()>=this.y&&e.getY()<=this.y+p.pixel){
              return true;
            }
        
        return false;
    }
    public void cargarImagenes(){
        try{
            imagenes[0]=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunflower/frame_00.png"));
            imagenes[1]=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunflower/frame_01.png"));
            imagenes[2]=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunflower/frame_02.png"));
            imagenes[3]=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunflower/frame_03.png"));
            imagenes[4]=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunflower/frame_04.png"));
            imagenes[5]=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunflower/frame_05.png"));
            imagenes[6]=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunflower/frame_06.png"));
            imagenes[7]=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunflower/frame_07.png"));
            imagenes[8]=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunflower/frame_08.png"));
            imagenes[9]=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunflower/frame_09.png"));
            imagenes[10]=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunflower/frame_10.png"));
            imagenes[11]=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunflower/frame_11.png"));
            imagenes[12]=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunflower/frame_12.png"));
            imagenes[13]=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunflower/frame_13.png"));
            imagenes[14]=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunflower/frame_14.png"));
            imagenes[15]=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunflower/frame_15.png"));
            imagenes[16]=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunflower/frame_16.png"));
            imagenes[17]=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/imagenes/sunflower/frame_17.png"));
           


        }catch(IOException e){
            System.out.println("Error al cargar girazoles\n "+ e.getMessage());
        }
    }

    public void draw(Graphics2D g2){
        cambiarFrame();
        generarSol();
        colision();
        g2.drawImage(girazol,x, y,p.pixel,p.pixel,p);
        g2.drawString(vida+"", x-20, y-20);

    }

    public void generarSol(){
        contador2++;
        if(contador2==30*10){//30 frames y 20 son los segundos 
            vectorSoles2.add(new Soles(p, x, y));
            contador2=0;
        }
    }
    public void cambiarFrame(){
        
        switch(frame){
            case 0: girazol=imagenes[0]; break;
            case 1: girazol=imagenes[1]; break;
            case 2: girazol=imagenes[2]; break;
            case 3: girazol=imagenes[3]; break;
            case 4: girazol=imagenes[4]; break;
            case 5: girazol=imagenes[5]; break;
            case 6: girazol=imagenes[6]; break;
            case 7: girazol=imagenes[7]; break;
            case 8: girazol=imagenes[8]; break;
            case 9: girazol=imagenes[9]; break;
            case 10: girazol=imagenes[10]; break;
            case 11: girazol=imagenes[11]; break;
            case 12: girazol=imagenes[12]; break;
            case 13: girazol=imagenes[13]; break;
            case 14: girazol=imagenes[14]; break;
            case 15: girazol=imagenes[15]; break;
            case 16: girazol=imagenes[16]; break;
            case 17: girazol=imagenes[17]; break;

            default: frame=0;
        }

        contador++;
        if(contador==3){
            frame++;
            contador=0;
        }
    }
}