public class Asteroide {
	int posX,
		posY,
		tamanho, //1,2,3 ou 4 ???
		vX,
		vY,
		vRotacao;
	// falta cor;
	
	public Asteroide defineAsteroide(){
		Asteroide Ast = new Asteroide();
		this.posX = 0;
		this.posY = 0;
		this.tamanho = 0;
		this.vX = 0;
		this.vY = 0;
		this.vRotacao = 0;
		//implementa usando random
		return Ast;
	}
}
