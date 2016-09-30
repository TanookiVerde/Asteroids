package bin;

import java.util.Set;
import java.util.HashSet;
import java.util.Random;

public class Jogo {
    
    Nave nave;
    Random aleatorio = new Random();
    Set<Tiro> tiros = new HashSet<Tiro>();
    Set<Asteroide> listaAsteroides = new HashSet<Asteroide>();
    Cor cor;
    
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
        //NAVE ESTA VIVA?
        if (nave.life <= 0)nave.alive = false;
        
        //RECEBIMENTO DE CONTROLES
        if(keys.contains("left")) nave.moveEsquerda(dt);
        if(keys.contains("right")) nave.moveDireita(dt);
        if(keys.contains("up")) nave.acelera(dt);
        if(keys.contains("down"))nave.desacelera(dt);

        //COLISAO
        sistemaColisao();
        
        //MOVIMENTO
        nave.movimentar(dt);
        for(Tiro tiro: this.tiros){
          tiro.mover(dt);
          if (tiro.posX < -200 || tiro.posY > 1000) tiro.alive = false;
          if (tiro.posY < -200 || tiro.posY > 800) tiro.alive = false;
          }
        for(Asteroide asteroide: this.listaAsteroides){
          asteroide.mover(dt);
          if (asteroide.posX < 0 || asteroide.posY > 800) asteroide.alive = false;
          if (asteroide.posY < 0 || asteroide.posY > 600) asteroide.alive = false;
          }
          
        //SPAWN
        if (aleatorio.nextInt(1000) >= 980)this.listaAsteroides.add(this.spawn());
    }
    public void tecla(String c){
        if(c.contains(" "))this.tiros.add(nave.shoot());
    }
    public void desenhar(Tela tela){
        //TIROS
        for(Tiro tiro: this.tiros){
          if (tiro.alive)tiro.desenhar(tela);
          }
        //NAVE
        nave.desenhar(tela);
        //ASTEROIDE
        for(Asteroide asteroide:this.listaAsteroides){
          if (asteroide.alive)asteroide.desenhar(tela);
        }
        //HUD
        tela.texto(String.format("VIDA: %d", nave.life), 10, 40, 40, Cor.BRANCO);
        tela.texto(String.format("SCORE: %d", nave.score), 250, 40, 40, Cor.BRANCO);
    }
    public static void main (String[] args){
        new Motor(new Jogo());
    }
    public Asteroide spawn(){
        return new Asteroide();
    }
    public void sistemaColisao(){
        boolean splitAst = false;
        double anguloTemp = 0,
               posXTemp = 0,
               posYTemp = 0;
        Asteroide asteroideTemp;
        for(Asteroide asteroide: this.listaAsteroides){
            if (asteroide.alive && nave.colisao(asteroide)){nave.life -= 1; asteroide.alive = false;}
            for(Tiro tiro: this.tiros){
                if (tiro.alive && asteroide.alive && asteroide.colisao(tiro)){
                    //Motor.tocar("explosion.wav");
                    nave.score+= asteroide.tamanho*100;//Aumenta SCORE de acordo com o tamanho do asteroide
                    asteroide.alive = false;//Asteroide eh dado como morto
                    tiro.alive = false;//Tiro acaba
                    if (asteroide.tamanho > 2){
                        asteroide.alive = true;//Volta para ser reutilizado com diferente tamanho e direcao
                        asteroide.angulo = tiro.angulo + Math.PI/2;
                        if(asteroide.tamanho == 3) asteroide.tamanho = 1; 
                        if(asteroide.tamanho == 4) asteroide.tamanho = 2;
                        anguloTemp = asteroide.angulo - Math.PI;
                        posXTemp = asteroide.posX;
                        posYTemp = asteroide.posY;
                        splitAst = true;//permite a criacao de um novo asteroide no if abaixo
                    }
                }
            }
        }
        if (splitAst){
            asteroideTemp = new Asteroide();
            asteroideTemp.angulo = anguloTemp;
            asteroideTemp.tamanho = 2;
            asteroideTemp.posX = posXTemp;
            asteroideTemp.posY = posYTemp;
            this.listaAsteroides.add(asteroideTemp);
            splitAst = false;
        }
    }
}
