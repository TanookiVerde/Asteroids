package bin;

public class Nave {
    int contador;
    double posX,
           posY,
           vetor,
           angulo;
    boolean estaLigada,//se o motor esta em acao
            jaAtirou;//se a nave atirou (depois de 1 segundo recarrega)
    
    public Nave(){
        posX = 100.0;
        posY = 100.0;
        vetor = 0;
        angulo = 0;
        estaLigada = false;
        jaAtirou = false;
        contador = 0;
        
    }
    
    public void desenhar(Tela tela){
        int x, y;
        x = 70;
        y = 0;
        tela.imagem("naves.png", x, y, 50, 50, this.angulo+Math.PI/2, this.posX, this.posY);
         
    }
    
    public void moveEsquerda(double dt){
        this.angulo -= Math.PI*dt;
    }
    public void moveDireita(double dt){
        this.angulo += Math.PI*dt;
    }
    public void acelera(double dt){
        if (this.vetor >= 300){
           return;
           }
        this.vetor = this.vetor +100*dt;
    }
    public void movimentar(double dt){
        this.posX += vetor*Math.cos(angulo)*dt;
        this.posY += vetor*Math.sin(angulo)*dt;
    }
    public Tiro shoot(){
       return new Tiro(this.posX,this.posY,this.angulo,400.0);
       }
}
