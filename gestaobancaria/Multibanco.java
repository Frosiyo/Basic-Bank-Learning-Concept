package gestaobancaria;

import java.util.Scanner;

public class Multibanco extends InteracaoBanco {
	private static boolean sair = false;
	public static Scanner inputMultiBanco;
	
	public static void processaMenuMultibanco(){
		//implementar o codigo deste método		 
		 while(!sair) {
			 System.out.println("\n[Menu Multibanco]\n"
			 					+ "\nInsira o seu Login");
			 int loginInput = inputMultiBanco.nextInt();
			 System.out.println("\nInsira a sua Password");
			 int passwordInput = inputMultiBanco.nextInt();
			 cli = Banco.validarLogin(loginInput, passwordInput);
			 if(cli != null) {
				 if(cli.obterAtivo()) {
					 System.out.println("Login correto. Bem vindo");
					 InteracaoBanco.inputMultiBanco = new Scanner(System.in);
					 processaMenuContas(cli.obterContas());
				 } else System.out.println("Nao pode operar sobre um cliente inativo");
			 } else System.out.println("Dados de login invalidos");
		 }
	}
}
