package src;

public class Tiro
{
   double posX,
          posY,
          vetor,
          angulo;
   boolean alive;
   Cor corTiro = Cor.BRANCO;
   
  public Tiro(double x, double y, double angulo, double velocidade){
    this.posX = x+23+23*Math.cos(angulo);//pos+metadeDoTam-raioTiro+parteQueFaltaPraCabecaDaNave
    this.posY = y+23+23*Math.sin(angulo);//same here
    this.vetor = velocidade;
    this.angulo = angulo;
    this.alive = true;
    }
  public void mover(double dt){
    this.posX += this.vetor*Math.cos(this.angulo)*dt;
    this.posY += this.vetor*Math.sin(this.angulo)*dt;
    }
  public void desenhar(Tela tela){
	tela.circulo(this.posX,this.posY,2,this.corTiro); 
	}
}
