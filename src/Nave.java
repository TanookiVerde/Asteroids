public class Nave {
	
	int posX,
		posY;
	double vX,
		   vY,
		   angulo;
	boolean estaLigada;
	
	public Nave(){
		posX = 0;
		posY = 0;
		vX = 0;
		vY = 0;
		angulo = 0;
		estaLigada = false;
		
	}
	
	public void desenhar(Tela tela){
		int x, y;
		x = 70;
		y = 0;
		tela.imagem("naves.png", x, y, 50, 50, this.angulo, this.posX, this.posY);
		 
	}
	
	public void moveEsquerda(double dt){
		this.angulo -= 1*dt;
	}
	public void moveDireita(double dt){
		this.angulo += 1*dt;
	}
	public void acelera(double dt){
		this.vX = this.vX +1000*dt;
		this.vY = this.vY +1000*dt;
	}
	public void movimentar(double dt){
		this.posX += vX*dt;
		this.posY += vY*dt;
	}
}
