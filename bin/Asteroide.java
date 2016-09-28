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
	   tela.circulo(this.posX,this.posY,this.tamanho*10,this.corAsteroide); 
	   }
	public void mover(double dt){
	   this.posX+=this.vetor*Math.cos(angulo)*dt;
	   this.posY+=this.vetor*Math.sin(angulo)*dt;
	   }
	public boolean colisao(Tiro tiro){
	   double distancia = 0;
	   distancia = Math.sqrt( Math.pow((tiro.posX - this.posX),2) + Math.pow((tiro.posY - this.posY),2) );//lembre-se que nao esta no meio
	   if (distancia < 30){
	       return true;
	   }
	   return false;
	   }
}
