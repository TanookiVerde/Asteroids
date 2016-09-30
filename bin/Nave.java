package bin;

public class Nave {
    int life,
        score;
    double posX,
           posY,
           vetor,
           angulo;
    boolean estaLigada,
            alive;
    
    public Nave(){
        posX = 100.0;
        posY = 100.0;
        vetor = 0;
        angulo = 0;
        life = 3;
        score = 0;
        estaLigada = false;
        alive = true;
        
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
    public void desacelera(double dt){
        this.vetor -= 100*dt;
        if (this.vetor <= 0) this.vetor = 0;
    }
    public void movimentar(double dt){
        this.posX += vetor*Math.cos(angulo)*dt;
        this.posY += vetor*Math.sin(angulo)*dt;
        if (this.posX+50 >=850) this.posX = 0;
        if (this.posY+50 >=650) this.posY = 0;
        if (this.posX+50 <=-50) this.posX = 800;
        if (this.posY+50 <=-50) this.posY = 600;
        
    }
    public Tiro shoot(){
       return new Tiro(this.posX,this.posY,this.angulo,500.0);
       }
    public boolean colisao(Asteroide asteroide){
       double distancia = 0;
       distancia = Math.sqrt( Math.pow((asteroide.posX - this.posX-25),2) + Math.pow((asteroide.posY - this.posY-25),2) );//lembre-se que nao esta no meio
       if (distancia < 10 + asteroide.tamanho*10){
           return true;
       }
       return false;
       }
    
}
