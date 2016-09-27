package src;

import java.util.Set;
import java.util.HashSet;
import java.util.Random;

public class Jogo {
    
    Nave nave;
    int contador=0;
    Random aleatorio = new Random();
    Set<Tiro> tiros = new HashSet<Tiro>();
    Set<Asteroide> asteroides = new HashSet<Asteroide>();
        
    public Jogo(){
        nave = new Nave();
    }
    public String getTitulo(){
        return "Asteroids";
    }
    public int getLargura(){
        return 800;
    }
    public int getAltura(){
        return 600;
    }
    public void tique(Set<String> keys, double dt){
        if(keys.contains("left")) nave.moveEsquerda(dt);
        if(keys.contains("right")) nave.moveDireita(dt);
        if(keys.contains("up")) nave.acelera(dt);
        if(keys.contains(" ") && !nave.jaAtirou)this.tiros.add(nave.shoot()); nave.jaAtirou = true;
        
        nave.contador += 1;
        if (nave.contador == 10){
            nave.contador = 0;
            nave.jaAtirou = false;
        }
        
        nave.movimentar(dt);
        for(Tiro tiro: this.tiros){
          tiro.mover(dt);
          }
        for(Asteroide asteroide: this.asteroides){
          asteroide.mover(dt);
          }
        if (aleatorio.nextInt(1000) >= 970){
            this.asteroides.add(this.spawn());
        }
        for(Asteroide asteroide: this.asteroides){
            for(Tiro tiro: this.tiros){
                if (asteroide.colisao(tiro)){
                    asteroide.alive = false;
                    tiro.alive = false;
                }
            }
        }
    }
    public void tecla(String c){
        //vazio
    }
    public void desenhar(Tela tela){
        for(Tiro tiro: this.tiros){
          if (tiro.alive)tiro.desenhar(tela);
          }
        nave.desenhar(tela);
        for(Asteroide asteroide:this.asteroides){
          if (asteroide.alive)asteroide.desenhar(tela);
        }
    }
    public static void main (String[] args){
        new Motor(new Jogo());
    }
    public Asteroide spawn(){
        return new Asteroide();
    }
}
