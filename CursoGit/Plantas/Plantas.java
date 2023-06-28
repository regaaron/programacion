package Plantas;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.xml.transform.Source;


/**
 *
 * @author aaron
 */
public class Plantas extends JComponent implements Runnable {
    
    final int col = 11; //columnas
    final int row = 5; 
    final int pixel = 90; //tamanio de  pixel 
    final int extraxIzq = 240; //espacio estra ala izq casa etc
    final int extraDer = 100;   //extra derecha calle 
    final int extraArriba = 65; //extra arriba contador de soles
    final int screenX = col * pixel + extraDer + extraxIzq; //tamanio en x en vase a col y pixel + extras
    final int screenY = row * pixel + 100; ////tamanio en y en base a ren pixeles y espacio extra
    final int FPS = 35; //fotogramas por segundo actualiza la pantalla 30 veces cada segundo
    int cargas=0;
    int puntos = 3000; //puntos iniciales
    Thread gameThread;
    BufferedImage back, score, gisante, girasol, nuez, gisante1,tagGirazol,
     tagNuez, tagGisante, tagBomba, pala1, pala2,explosion,gameover; //imagenes
    Base base; //base de dibujo para no tener sobre saturado el paint
    boolean tag1, tag2, tag3, tag4, tag5, tag6; //boleanos para los tags de las plantas o etiquetas
    int posx, posy; //obtener la posicion en x y y del mouse
    BackgroundSound soundfondo; //sonido de fondo
  //  BackgroundSound soundError;
    GenSoles genSol; //objeto que genera un sol cada 10s pero no mas de 10
    boolean bpala = false; //boleano para saber si esta activa la pala
    CopyOnWriteArrayList<zombies> vectorZombies= new CopyOnWriteArrayList<>(); //vector de zombies
    CopyOnWriteArrayList<Soles> vectorSoles= new CopyOnWriteArrayList<>(); //vector de soles
    CopyOnWriteArrayList<Girazol> vectorGirazoles = new CopyOnWriteArrayList<>();//vector de girazoles
    CopyOnWriteArrayList<Soles> vectorSoles2= new CopyOnWriteArrayList<>(); //vector de soles
    CopyOnWriteArrayList<Gizantes> vectorGisantes=new CopyOnWriteArrayList<>();
    CopyOnWriteArrayList<Nuez> vectorNuez=new CopyOnWriteArrayList<>();
    CopyOnWriteArrayList<Balas> vectorBalas= new CopyOnWriteArrayList<>();
    CopyOnWriteArrayList<Podadoras> vectorPodadoras= new CopyOnWriteArrayList<>();

    int tiempo=0;//cambiar a aumentar tamanio
    int tiempo2=0;
    boolean over=false;
    Nivel1 lvl1; //objeto que es el nivel o que va ir creadno zombies en tiempo y cantidad especificados
    int matriz[][] = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } }; //matriz de las flores

    Plantas() {
        setPreferredSize(new Dimension(screenX, screenY)); //crear pantalla con este tamanio
        cargarImagenes(); //metodo que carga algunas imagenes base
      
        lvl1=new Nivel1(this); //inicializamos el objeto lvl1 y le pasamos este 
                                //objeto para poder tener referencia a el
        lvl1.start();   //corremos el hilo
        genSol= new GenSoles(this); //creamos el objeto de gen de soles 
        genSol.start(); //inicializamos el hilo de los soles
        base = new Base(this); //se encargara de pintar la base el fondo los tags punntos etc
                                //le pasamos el objeto para tener referencia como tam del pixel etc
        soundfondo = new BackgroundSound("/Plantas/Java/resources/fondoz.wav"); 
        //soundError = new BackgroundSound("/Plantas/Java/resources/sonidoError.wav");
        soundfondo.clip.loop(Clip.LOOP_CONTINUOUSLY);
        //creacion del objeto de sonido de fondo con la ruta y reproducimos en buqle
        
        vectorPodadoras.add(new Podadoras(0,this));
        vectorPodadoras.add(new Podadoras(1,this));
        vectorPodadoras.add(new Podadoras(2,this));
        vectorPodadoras.add(new Podadoras(3,this));
        vectorPodadoras.add(new Podadoras(4,this));

        
        // logica de teclado 
        //no se usa
        //no se usa
        //no se usa
        addKeyListener(new KeyAdapter() {

            // si se preciona una tecla envia verdadero y el codigo de la
            public void keyPressed(KeyEvent e) {
                actualiza(e.getKeyCode(), true);
            }

            // Si se preciona una tecla envia falso asi dejara de hacer el movimiento
            public void keyReleased(KeyEvent e) {
                actualiza(e.getKeyCode(), false);
            }

            // en caso de que la tecla se este precionando pasa true y el codigo de teclado
            private void actualiza(int keyCode, boolean pressed) {
            }

        });

        //eventos del mouse
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override//evento de click
            public void mouseClicked(MouseEvent evento) {
               
                //recorremos el arreglo de soles
                    for(Soles sol:vectorSoles){
                        //checamos si se dio click en el sol de ser asi lo eliminamos del arreglo
                            if(sol.mouseClicked(evento)){
                                vectorSoles.remove(sol);//eliminar del arreglo
                            }
                    }

                     for(Soles sol:vectorSoles2){
                        //checamos si se dio click en el sol de ser asi lo eliminamos del arreglo
                            if(sol.mouseClicked(evento)){
                                vectorSoles2.remove(sol);//eliminar del arreglo
                            }
                    }
              
                    //logica del 1er tag posicion
                if (evento.getX() > 59 && evento.getX() < 133 && evento.getY() > 25 && evento.getY() < 87) {
                        //se checa que no este activado ya y que cuentes con los puntos
                    if (tag1 == false && puntos >= 50) {
                        //checa qu ningun otro este activado
                        if (tags()) { //revisa que ningun otro tag este activado
                            tag1 = true;//activamos
                      //      soundError.clip.start();
                        }

                    } else {
                        tag1 = false; //desactivamos
                       // soundError.clip.start();
                    }
                    bpala = false; //ponemos la pala en false siempre al escoger una etiqueta
                    //ya que si no ala hora de colocarlo y si al mismo tiempo teniamos la pala lo agrega
                    //pero a ala vez lo elimina
                }

                //lo mismo pero con tag2
                if (evento.getX() > 59 && evento.getX() < 133 && evento.getY() > 116 && evento.getY() < 175) {
                    if (tag2 == false && puntos >= 60) {
                        if (tags()) {
                            tag2 = true;
                        }
                    } else {
                        tag2 = false;
                     //   soundError.clip.start();
                    }
                    bpala = false;
                }

                //lo mismo pero con tag3
                if (evento.getX() > 59 && evento.getX() < 133 && evento.getY() > 203 && evento.getY() < 267) {
                    if (tag3 == false && puntos >= 70) {
                        if (tags()) {
                            tag3 = true;
                        }
                    } else {
                        tag3 = false;
                      //  soundError.clip.start();
                    }
                    bpala = false;
                }

                //lo mismo pero con tag3
                if (evento.getX() > 59 && evento.getX() < 133 && evento.getY() > 295 && evento.getY() < 359) {
                    if (tag4 == false && puntos >= 80) {
                        if (tags()) {
                            tag4 = true;
                        }
                    } else {
                        tag4 = false;
                 //       soundError.clip.start();
                    }
                    bpala = false;
                }

                //lo mismo pero con tag4
                if (evento.getX() > 59 && evento.getX() < 133 && evento.getY() > 387 && evento.getY() < 448) {
                    if (tag5 == false && puntos >= 90) {
                        if (tags()) {
                            tag5 = true;
                        }
                    } else {
                        tag5 = false;
                    //    soundError.clip.start();
                    }
                    bpala = false;
                }

                //lo mismo pero con tag5
                if (evento.getX() > 59 && evento.getX() < 133 && evento.getY() > 472 && evento.getY() < 540) {
                    if (tag6 == false && puntos >= 100) {
                        if (tags()) {
                            tag6 = true;
                        }
                    } else {
                        tag6 = false;
                    }
                    bpala = false;
                }

                    //logica para plantar plantas
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 5; j++) {
                        //logica de las cordenadas de la matriz en buqle
                        if (evento.getX() > 244 + (i * pixel) && evento.getX() < 325 + (i * pixel)
                                && evento.getY() > 67 + (j * pixel) && evento.getY() < 150 + (j * pixel)) {
                            //metodo para colocar en la matriz  dependiendo que etiqueta estaba activa
                            ColocarMAtriz(i, j);
                            //si la pala esta activa coloca 0 en la posicion de donde se dio click
                            //eliminando la planta
                            if (bpala) {
                                matriz[j][i] = 0;
                                for(Girazol girasol:vectorGirazoles){
                                    if(girasol.eliminar(evento)){
                                        vectorGirazoles.remove(girasol);
                                    }
                                }
                                for(Gizantes gisante:vectorGisantes){
                                    if(gisante.eliminar(evento)){
                                        vectorGisantes.remove(gisante);
                                    }
                                }
                                for(Nuez nuez:vectorNuez){
                                    if(nuez.eliminar(evento)){
                                        vectorNuez.remove(nuez);
                                    }
                                }
                            }

                        }
                    }
                }
                //logica para activar la pala posicion
                if (evento.getX() > 392 && evento.getX() < 453 && evento.getY() > 19 && evento.getY() < 61) {
                    //si esta activa desactivamos si no activamos y colocamos las equitenas el false
                    if (bpala) {
                        bpala = false;
                    } else {
                        bpala = true;
                        tag1=tag2=tag3=tag4=tag5=tag6=false;
                    }
                }
            }

        });

        //otors eventos del mouse
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override//arrastrar
            public void mouseDragged(MouseEvent e) {

            }
            //movel el mouse
            public void mouseMoved(MouseEvent evento) {
                //cada que se mueve actualizamos la refencia del mouse
                posx=evento.getX();
                posy=evento.getY();

            }
        }); 

        setFocusable(true);//se mantiene enfocado a los eventos del mouse

    }

    //este es el metodo que teniamos arriba de colocar en la matriz dependiendo del tag activado
    public void ColocarMAtriz(int i, int j) {
        //antes de eco checamos que no este una planta ya en esa posicion
        if (matriz[j][i] == 0) {
            //si es el tag 1 colocammos 1 colocamos el tag en falso para no poder plantar mas
            //y reducimos el precio por la planta
            if (tag1) {

                matriz[j][i] = 1;
                vectorGirazoles.add(new Girazol(this, i,j));
                tag1 = false;
                puntos -= 50;
            }
            //lo mismo pero con el tag2
            if (tag2) {
                matriz[j][i] = 3;
                vectorNuez.add(new Nuez(this, i,j));

                tag2 = false;
                puntos -= 50;
            }
            //lo mismo pero con el tag3
            if (tag3) {
                matriz[j][i] = 2;
                vectorGisantes.add(new Gizantes(this, i,j));
                tag3 = false;
                puntos -= 50;
            }
            //lo mismo pero con el tag4

            if (tag4) {
                matriz[j][i] = 4;
                tag4 = false;
                puntos -= 50;
            }
            //lo mismo pero con el tag5
            if (tag5) {
                matriz[j][i] = 2;
                tag5 = false;
                puntos -= 50;
            }
            //lo mismo pero con el tag6
            if (tag6) {
                matriz[j][i] = 4;
                tag6 = false;
                puntos -= 50;
            }
        }
    }

    @Override//dibujar 
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        base.draw(g2); //dibujamos toda la base el fondo tags puntos etc
       
        
       // System.out.println(cargas++);
       cargas++;
        if(cargas%FPS==0){
            tiempo2++;
           // System.out.println("segundoosss 3");
        }

        //recorremos el vector de soles y dibujamos cada uno 
        //conocido como for each
        //conocido como for each
        //conocido como for each
        
        //lo mismo pero con los zombies
        for(Girazol girasol:vectorGirazoles){
            girasol.draw(g2);
            if(girasol.vida<=0){
                vectorGirazoles.remove(girasol);
                matriz[(girasol.y-extraArriba)/pixel][(girasol.x-extraDer)/pixel-1]=0;

            }
        }
        for(Gizantes gisante:vectorGisantes){
            gisante.draw(g2);
            if(gisante.vida<=0){
                vectorGisantes.remove(gisante);
               
                matriz[(gisante.y-extraArriba)/pixel][(gisante.x-extraDer)/pixel-1]=0;
            }

        }


        for(Nuez nuez:vectorNuez){
            nuez.draw(g2);
            if(nuez.vida<=0){
                vectorNuez.remove(nuez);
                matriz[(nuez.y-extraArriba)/pixel][(nuez.x-extraDer)/pixel-1]=0;

            }
        }
        
        for(Soles sol:vectorSoles){
            sol.draw(g2);
        }

        for(Soles sol:vectorSoles2){
            sol.draw(g2);
        }

        for(Balas bala:vectorBalas){
            bala.draw(g2);
        }

        if(over){
            g2.drawImage(gameover,((screenX/2)-(pixel)-tiempo),(screenY/2)-tiempo,pixel+(tiempo*2),pixel+(2*tiempo),null);
        }
        for(zombies zombie: vectorZombies){
            zombie.draw(g2);
        }
        g.drawString("tiempo:", screenX-100, 20);

        g.drawString(tiempo2+"", screenX-100, 60);
    
        for(Podadoras podadora: vectorPodadoras){
            podadora.draw(g2);
        }
    }
    
    public static void main(String[] args) {
        JFrame jf = new JFrame("Juego");
        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Menu menu = new Menu(jf);
        jf.getContentPane().add(menu);
        jf.pack();
        jf.setVisible(true);
    }

    //metodo que checa que todos los tags estan desactivados
    public boolean tags() {
        return (!tag1 && !tag2 && !tag3 && !tag4 && !tag5 && !tag6);
    }


    public void cicloPrincipalJuego() {

        gameThread = new Thread(this);
        gameThread.start();

    }
    
    //meotodo que carga todas las imagenes
    private void cargarImagenes() {
        try {
            back = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/resources/background.png"));
            score = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/resources/score.png"));
            gisante = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/resources/gisante.png"));
            girasol = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/resources/girasol.png"));
            nuez = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/resources/nuez1.png"));
            gisante1 = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/resources/gisante1.png"));
            tagGirazol = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/resources/tagGirazol.png"));
            tagGisante = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/resources/tagGisante.png"));
            tagNuez = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/resources/tagNuez.png"));
            tagBomba = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/resources/tagBomba.png"));
            pala1 = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/resources/pala.png"));
            pala2 = ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/resources/pala2.png"));
            explosion=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/resources/explosion.png"));
            gameover=ImageIO.read(getClass().getResourceAsStream("/Plantas/Java/resources/gameover.png"));
        } catch (IOException ex) {
            Logger.getLogger(Plantas.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getLocalizedMessage());
        }
    }

    //logica de los fps no se explicarlo muy bien pero revisalo ya que ahi actualizamos 
    //un par de cosas
    @Override
    public void run() {
        /* 
        double drawIterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawIterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                //recorremos el arreglo de zombies y le hablamos a su fisica para
                //que se mueva cada actualizacion
                for(zombies zz: vectorZombies){
                    zz.fisica();
                    if(zz.vida<=0){
                        
                         zz.sonidoeat.clip.stop();
                        vectorZombies.remove(zz);
                    }
                }
                



                for(Balas b:vectorBalas){
                    if(b.x>=b.xfinal||b.colision()){
                        vectorBalas.remove(b);
                    }
                    
                }

                

                //repintamos
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }
        }

         */
            final int FPS = 35;
            final long FRAME_TIME = 1000 / FPS; // Tiempo en milisegundos por fotograma
    
            long previousTime = System.currentTimeMillis();
            long accumulator = 0;
    
            while (true) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - previousTime;
                previousTime = currentTime;
                accumulator += elapsedTime;
    
                while (accumulator >= FRAME_TIME) {
                    // Aquí va la actualización que deseas realizar a 30 FPS
                    // Puedes agregar tu código dentro de este bloque
                    for(zombies zombie: vectorZombies){
                        zombie.fisica();
                        if(zombie.vida<=0){
                            
                             zombie.sonidoeat.clip.stop();
                            vectorZombies.remove(zombie);
                        }
                    }
                    
                    for(Balas bala:vectorBalas){
                        if(bala.x>=bala.xfinal||bala.colision()){
                            vectorBalas.remove(bala);
                        }
                        
                    }
    
                    repaint();
                    accumulator -= FRAME_TIME;
                }
    
                // Aquí puedes poner cualquier otra lógica o renderizado relacionado con el bucle principal
            }
        
    }

}
