
public class Vista {

	public static void main(String[] args) {
		Conexion con = new Conexion();
		Modelo mod = new Modelo();
		mod.addAnswer_question(new Question("¿Quién ganó la liga en 2014?", 1), new Answer("Atleti",true), new Answer("Real Madrid", false));
		//mod.printTest(2);
		//mod.createNewTest("Test de ejemplo");
		
	}	

}
