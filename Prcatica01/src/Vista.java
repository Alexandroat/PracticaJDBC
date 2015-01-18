import java.util.InputMismatchException;
import java.util.Scanner;


public class Vista {
	public static int option() {
		Scanner scanner = new Scanner(System.in);
		int opt = scanner.nextInt();
		return opt;
	}
	public static void main(String[] args) {
			Modelo modelo = new Modelo();
			boolean exit = false;
			try {
				do {
					System.out
							.println("\nSelecciona una opción (solo números 1-5)");
					System.out.println("1-Introducir nueva pregunta y respuestas");
					System.out.println("2-Mostrar todas las preguntas y respuestas de un test");
					System.out.println("3-crear un nuevo test con 5 preguntas aleatorias");
					System.out.println("4-Mejores resultados");
					System.out.println("5-Salir");

					switch (option()) {
					case 2:
						Scanner scanner = new Scanner(System.in);
						System.out.println("Introduzca el número del test del cual desea ver sus preguntas y respuestas");
						int Test = scanner.nextInt();
						modelo.printTest(Test);
						break;
					case 1:
						Scanner scanner1 = new Scanner(System.in);
						System.out.println("Introduzca una nueva pregunta");
						String question = scanner1.next();
						
						System.out.println("Introduzca el numero del test en el que quieres guardarlo");
						int numTest = scanner1.nextInt();

						System.out.println("Introduzca una respuesta");
						String answer = scanner1.next();

						System.out.println("Escriba 'true' si es la respuesta verdadera o 'false' si no lo es :");
						boolean is_true = scanner1.nextBoolean();
						
						System.out.println("Introduzca una respuesta");
						String answer2 = scanner1.next();

						System.out.println("Escriba 'true' si es la respuesta verdadera o 'false' si no lo es :");
						boolean is_true2 = scanner1.nextBoolean();
						
						modelo.addAnswer_question(new Question(question, numTest), new Answer(answer,is_true), new Answer(answer2, is_true2));

						break;
					case 3:
						Scanner scanner2 = new Scanner(System.in);
						System.out.println("Introduzca el nombre del nuevo test");
						String testName = scanner2.next();
						modelo.createNewTest(testName);
						break;
					case 4:
						
						break;
					case 5:
						exit = true;
						System.out.println("Programa Finalizado");
						break;
					default:
						System.out.println("No existe esa Opción.");
					}
				} while (exit == false);

			} catch (InputMismatchException e) {
				System.err.println("Error, solo puede introducir numeros.");
			} catch (Exception e) {
				System.err.println("Se ha producido un Error.");
			}
		}
		
	}	


