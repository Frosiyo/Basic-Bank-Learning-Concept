package gestaobancaria;

import java.util.ArrayList;
import java.util.Scanner;

import clientes.Cliente;
import contas.Conta;

public abstract class InteracaoBanco {

	public static Cliente cli; //� medida que os menus avan�am o cliente a ser usado � guardado nesta variavel
	protected static Conta con;//� medida que os menus avan�am a conta a ser usada � guardada nesta variavel
	public static Scanner inputMultiBanco;
	/**
	 * Este metodo est� em repeti��o a mostrar o menu de opera��es disponiveis numa conta.
	 * Para cada uma das op��es existentes e atrav�s de um switch solicita a informa��o
	 * necessaria ao utilizador e invoca os metodos correspondentes.
	 */
	protected static void processaMenuConta(){
		//implementar o codigo deste m�todo
		 int opcaoMenuConta=0;
		 
		 while(opcaoMenuConta != 7) {
			 System.out.println("\n[Menu Contas]\n\n"
			 					+ "\n1->Levantar"
					 			+ "\n2->Depositar"
					 			+ "\n3->Transferir"
					 			+ "\n4->Obter extracto"
					 			+ "\n5->Obter saldo"
					 			+ "\n6->Obter informacoes"
					 			+ "\n7->Sair do Menu");
			 
			 opcaoMenuConta = inputMultiBanco.nextInt();
			 
			 if(opcaoMenuConta == 7) {
				 System.out.println("Menu fechado");
				 break;
			 
			 } else if(opcaoMenuConta == 1) {
				 System.out.println("Quanto dinheiro pretende levantar?");
				 int quantidade = inputMultiBanco.nextInt();
				 
				 boolean sucesso = con.levantar((double)quantidade);
				 
				 if(sucesso == true) {
					 System.out.println("Dinheiro levantado com sucesso");
				
				 } else System.out.println("Nao possui o saldo suficiente para esta operacao");
				 
			 } else if(opcaoMenuConta == 2) {
				 System.out.println("Quanto dinheiro quer depositar?");
				 int quantidade = inputMultiBanco.nextInt();
				 con.depositar((double)quantidade);
				 
			 } else if(opcaoMenuConta == 3) {
				 System.out.println("Qual o NIB da conta a que pretende transferir?");
				 int nibcontadestino = inputMultiBanco.nextInt();
				 
				 Conta contadestino = Banco.obterConta(nibcontadestino);
				 
				 if(contadestino != null) {
					 System.out.println("Qual o valor a transferir?");
					 int quantidade = inputMultiBanco.nextInt();
					 
					 boolean sucesso = con.transferir((double)quantidade, contadestino);
					 
					 if(sucesso == true) {
						 System.out.println("Transferencia efetuada com sucesso");
					 } else System.out.println("Nao possui o saldo suficiente para esta operacao");
				 } else System.out.println("A conta destino que especificou nao existe");
				 
			 } else if(opcaoMenuConta == 4) {
				 con.mostrarExtrato();
				 
			 } else if(opcaoMenuConta == 5) {
				 System.out.println("Saldo: " +con.obterSaldo());
				 
			 } else if(opcaoMenuConta == 6) {
				 System.out.println(con.mostrarInformacoes());
				 
			 } else System.out.println("Opcao invalida");
		 }
	}
	
	/**
	 * Este metodo est� em repeti��o a mostrar o menu de contas disponiveis do cliente.
	 * De notar que APENAS AS CONTAS ACTIVAS s�o mostradas.
	 * Ap�s ser seleccionada uma conta � invocado o metodo processaMenuConta referente � conta escolhida
	 * @param contascliente Cliente sobre o qual se quer visualizar as contas
	 */
	public static void processaMenuContas(ArrayList<Conta> contascliente) {
		//implementar o codigo deste m�todo
		 int opcaoMenuContas=0, sair=contascliente.size()+1;
		 
		 while(opcaoMenuContas != sair) {
			 System.out.println("\nMenu Contas");
			 for(int i=0; i<contascliente.size(); ++i) {
				 System.out.println((i+1)+ "->" +contascliente.get(i).obterTipo()+ "--" +contascliente.get(i).obterNib());
			 }
			 System.out.println(sair+ "->Sair do Menu");
			 opcaoMenuContas = inputMultiBanco.nextInt();
			 if(opcaoMenuContas == sair) {
				 System.out.println("Menu fechado");
				 break;
			 } else if(opcaoMenuContas < sair) {
				 con = contascliente.get(opcaoMenuContas-1);
				 processaMenuConta();
			 } else System.out.println("Opcao invalida");
		 }
	}
}
