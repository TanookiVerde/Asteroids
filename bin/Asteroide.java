package bin;

import java.util.Random;

public class Asteroide {
    double posX,
           posY,
           angulo,
           vetor;
    int tamanho;
    boolean alive;
    Cor corAsteroide;
    
    public Asteroide(){
        Random gerador = new Random();
        this.posX = gerador.nextInt(800);
        this.posY = gerador.nextInt(600);
        this.tamanho = 1 + gerador.nextInt(4);
        this.vetor = gerador.nextInt(150);
        this.angulo = (Math.PI)*gerador.nextInt(60)/30;
        this.corAsteroide = Cor.VERMELHO;
        this.alive = true;
    }
    public void desenhar(Tela tela){
       int size = 0,
           x = 0,
           y = 0;
       //tela.circulo(this.posX,this.posY,this.tamanho*10,Cor.VERMELHO);
       switch(this.tamanho){
            case 1:
                    size = 15;
                    x = 0;
                    y = 48;
                    break;
            case 2:
                    size = 16;
                    x = 16;
                    y = 48*3;
                    break;
            case 3:
                    size = 33;
                    x = 32;
                    y = 96;
                    break;
            case 4:
                    x = 65;
                    size = 48;
                    y = 0;
                    break;
        }
       tela.imagem("asteroids.png", x, y, size, size, this.angulo+Math.PI/2, this.posX, this.posY);
       }
    public void mover(double dt){
       this.posX+=this.vetor*Math.cos(angulo)*dt;
       this.posY+=this.vetor*Math.sin(angulo)*dt;
       this.angulo += Math.PI*dt*0.05;
       }
    public boolean colisao(Tiro tiro){
       double distancia = 0;
       distancia = Math.sqrt( Math.pow((this.posX - tiro.posX),2) + Math.pow((this.posY - tiro.posY),2) );//lembre-se que nao esta no meio
       if (distancia < 5 + this.tamanho*10){
           return true;
       }
       return false;
       }
    public boolean colisaoAsteroide(Asteroide asteroide){
       double distancia = 0;
       distancia = Math.sqrt( Math.pow((this.posX - asteroide.posX),2) + Math.pow((this.posY - asteroide.posY),2) );//lembre-se que nao esta no meio
       if (distancia < asteroide.tamanho*7 + this.tamanho*7){
           return true;
       }
       return false;
       }
}
