import java.util.Set;

public class Jogo {
	
	Nave nave;
		
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
		
		nave.movimentar(dt);
	}
	public void tecla(String c){
		//vazio
	}
	public void desenhar(Tela tela){
		nave.desenhar(tela);
	}
	public static void main (String[] args){
		new Motor(new Jogo());
	}
	
}
