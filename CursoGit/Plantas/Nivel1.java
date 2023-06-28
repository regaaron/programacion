package Plantas;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

//clase del nivel enbase a un tiempo iremos poniendo zombies en ciertas posiciones
//ya definidas en tiempos definidos
public class Nivel1 extends Thread{
    CopyOnWriteArrayList<zombies>vectorZombies;//arreglo de zombies
    Plantas p;
    Nivel1(Plantas p){
        this.p=p;//obtenemos la referencia de la clase principal
        this.vectorZombies=p.vectorZombies;//igualamos los arreglos 
    }

    //iniciamos el hilo
    @Override
    public void run() {

            try {
                Thread.sleep(10000); // Espera 10 segundos y panta  3 zombies
                vectorZombies.add(new zombies(p,0));
                vectorZombies.add(new zombies(p,2));
                vectorZombies.add(new zombies(p,3));
                Thread.sleep(10000);//espera 10 mas depues del anterior y coloca 3 mas
                vectorZombies.add(new zombies(p,4));
                vectorZombies.add(new zombies(p,2));
                vectorZombies.add(new zombies(p,1));
                Thread.sleep(10000);//espera 10 mas depues del anterior y coloca 3 mas
                vectorZombies.add(new zombies(p,4));
                vectorZombies.add(new zombies(p,2));
                vectorZombies.add(new zombies(p,1));
                Thread.sleep(10000);//espera 10 mas depues del anterior y coloca 3 mas
                vectorZombies.add(new zombies(p,4));
                vectorZombies.add(new zombies(p,2));
                vectorZombies.add(new zombies(p,1));
                Thread.sleep(10000);//espera 10 mas depues del anterior y coloca 3 mas
                vectorZombies.add(new zombies(p,4));
                vectorZombies.add(new zombies(p,2));
                vectorZombies.add(new zombies(p,1));
                Thread.sleep(10000);//espera 10 mas depues del anterior y coloca 3 mas
                vectorZombies.add(new zombies(p,4));
                vectorZombies.add(new zombies(p,2));
                vectorZombies.add(new zombies(p,1));
                Thread.sleep(10000);//espera 10 mas depues del anterior y coloca 3 mas
                vectorZombies.add(new zombies(p,4));
                vectorZombies.add(new zombies(p,2));
                vectorZombies.add(new zombies(p,1));
                Thread.sleep(10000);//espera 10 mas depues del anterior y coloca 3 mas
                vectorZombies.add(new zombies(p,4));
                vectorZombies.add(new zombies(p,2));
                vectorZombies.add(new zombies(p,1));

                while(true){
                    if(vectorZombies.size()==0){
                        System.exit(0);
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}