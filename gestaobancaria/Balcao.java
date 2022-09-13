package gestaobancaria;

import java.util.Random;
import java.util.Scanner;

//import contas.Conta;
//import clientes.Cliente;

public class Balcao extends InteracaoBanco { 
	public static Scanner inputMenu;
	
	public static void processaMenuBalcao(){
		//implementar o codigo deste método
		 int opcaoMenuBalcao=0;
		 
		 while(opcaoMenuBalcao != 7) {
			 System.out.println("\n[Menu Balcao]\n"
			 					+ "\n1->Criar Cliente"
					 			+ "\n2->Desativar Cliente"
					 			+ "\n3->Criar Conta"
					 			+ "\n4->Desativar Conta"
					 			+ "\n5->Listar Clientes"
					 			+ "\n6->Operacoes sobre cliente"
					 			+ "\n7->Sair");
			 
			opcaoMenuBalcao = inputMenu.nextInt();
			 
			if(opcaoMenuBalcao == 7) {
				System.out.println("Menu fechado");
				break;
			 
			} else if(opcaoMenuBalcao == 1) {
				inputMenu.nextLine();
				System.out.println("Insira o nome do novo cliente");
				String nome = inputMenu.nextLine();

				System.out.println("Insira a password do cliente");
				int password = inputMenu.nextInt();

				 
				Random geradoraleatorio = new Random();
				int numaleatorio = geradoraleatorio.nextInt(Integer.MAX_VALUE);
				 
				if(Banco.procurarCliente(numaleatorio) == null) {
					int userid = numaleatorio;
					Banco.criarCliente(nome, userid, password);
					 
					System.out.println("Cliente criado. O id desde cliente e " +userid);
					} else System.out.println("Ja existe um cliente com o mesmo id");
				
				} else if(opcaoMenuBalcao == 2) {
					inputMenu.nextLine();
					System.out.println("Insira o id da conta a desativar");
					int userid = inputMenu.nextInt();
					
					if(Banco.procurarCliente(userid) != null) {
					Banco.desactivarCliente(userid);
					} else System.out.println("Nao foi encontrado nenhum cliente com o id que especificou");
				 } else if(opcaoMenuBalcao == 3) {
					 inputMenu.nextLine();
					 System.out.println("Insira o tipo de conta a criar (1-Debito, 2-Prazo)");
					 int tipo = inputMenu.nextInt();
					 
					 if(tipo > 0 && tipo < 3) {
						 inputMenu.nextLine();
						 System.out.println("Insira o id do cliente para o cual criar uma nova conta");
						 int userid = inputMenu.nextInt();
						 cli = Banco.procurarCliente(userid);
						 if(cli != null) {
							 Banco.criarConta(cli, tipo);
							 con = cli.obterContas().get(cli.obterContas().size()-1);
							 
							 System.out.println("Informacoes da nova conta"
							 +con.mostrarInformacoes()); 
						 } else System.out.println("Nao foi possivel criar conta");
						 } else System.out.println("Tipo de conta invalido");
					 } else if(opcaoMenuBalcao == 4) {
						 inputMenu.nextLine();
						 System.out.println("Insira o nib da conta a desativar");
						 int nib = inputMenu.nextInt();
						 
						 if(cli.obterConta(nib) != null) {
							 cli.obterConta(nib).desactivar();
							 } else System.out.println("Nao foi encontrada nenhuma conta com o nib que especificou");
						 
						 } else if(opcaoMenuBalcao == 5) {
							 inputMenu.nextLine();
							 System.out.println("Insira o nome do cliente a procurar (em branco para listar todos os clientes)");
							 String nome = inputMenu.nextLine();
							 Banco.listarClientes(nome);
						 } else if(opcaoMenuBalcao == 6) {
							 inputMenu.nextLine();
							 System.out.println("Insira o id da conta com a que operar");
							 int userid = inputMenu.nextInt();
							 cli = Banco.procurarCliente(userid);
							 
							 InteracaoBanco.inputMultiBanco = new Scanner(System.in);
							 InteracaoBanco.processaMenuContas(cli.obterContas());
							 } else System.out.println("Opcao invalida");
			 }
		 }
	}
