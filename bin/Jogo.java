package bin;

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
        double posX =0,
               posY =0,
               A1=0;
        boolean split =false;
        if(keys.contains("left")) nave.moveEsquerda(dt);
        if(keys.contains("right")) nave.moveDireita(dt);
        if(keys.contains("up")) nave.acelera(dt);
        if(keys.contains(" ") && !nave.jaAtirou)this.tiros.add(nave.shoot()); nave.jaAtirou = true;
        if(keys.contains("down"))nave.desacelera(dt);
        
        nave.contador += 1;
        if (nave.contador == 5){
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
        if (aleatorio.nextInt(1000) >= 980){
            this.asteroides.add(this.spawn());
        }
        for(Asteroide asteroide: this.asteroides){
            for(Tiro tiro: this.tiros){
                if (tiro.alive && asteroide.colisao(tiro)){
                    tiro.alive = false;
                    if (asteroide.tamanho >= 3){
                        split = true;
                        posX = tiro.posX;
                        posY = tiro.posY;
                        A1 = tiro.angulo;
                    }
                    asteroide.alive = false;
                }
            }
        }
        if (split){
           Asteroide novoAst1 = new Asteroide();
           Asteroide novoAst2 = new Asteroide();
           novoAst1.tamanho = 2;
           novoAst2.tamanho = 2;
           novoAst1.posX = posX;
           novoAst2.posX = posX;
           novoAst1.posY = posY;
           novoAst2.posY = posY;
           novoAst1.vetor = 300;
           novoAst2.vetor = 300;
           novoAst1.angulo = A1-Math.PI/3;
           novoAst2.angulo = A1+Math.PI/3;
           this.asteroides.add(novoAst1);
           this.asteroides.add(novoAst2);
           split = false;
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
