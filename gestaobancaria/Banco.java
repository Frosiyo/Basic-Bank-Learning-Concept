package gestaobancaria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import clientes.Cliente;

import contas.Conta;
import contas.Prazo;
import contas.Debito;

public class Banco {
	private static ArrayList<Cliente> listaclientes;
	private static int numerocliente; //utilizado para as numeracoes dos clientes
	private static int numeroconta;	//utilizado para as numeracoes das contas
	
	private static final String FICHEIROCLIENTES = "dados/clientes.csv";
	private static final String FICHEIROCONTAS = "dados/contas.csv";
	private static final String FICHEIROTRANSACOES = "dados/transacoes.csv";
	private static final String FICHEIROCONFIGURACOES = "dados/configuracoes.txt";
	
//	public static void simularDados(){
//	    Cliente joao = new Cliente("joao",111,222);
//	    joao.adicionarConta(new Prazo());
//	    joao.adicionarConta(new Debito());
//
//	    Cliente pedro = new Cliente("pedro",123,456);
//	    pedro.adicionarConta(new Prazo());
//	    pedro.adicionarConta(new Debito());
//
//	    listaclientes.add(joao);
//	    listaclientes.add(pedro);
//	}
//
//	public static void iniciar(){
//	    listaclientes = new ArrayList<Cliente>();
//	    simularDados();
//	}


	public static void iniciar(){
		Banco.carregarDados();
		listaclientes = new ArrayList<Cliente>();
		numeroconta = 2451;
		numerocliente = 1543;
	}

	public static int gerarNumConta() {
		return numeroconta++;
	}
	
	public static int gerarNumCliente() {
		return numerocliente++;
	}
	

	
	
	
	//METODOS CLIENTES
	
	/**
	 * Desactiva o cliente com o id passado como parametro. Para isto deve usar
	 * o metodo procurar cliente do banco para encontrar o cliente com esse id. Caso exista
	 * define o estado como inactivo atrav�s do metodo desactivar do cliente
	 * @param idcliente id do cliente a desactivar
	 * @return booleano a indicar se foi ou nao possivel de desactivar o cliente
	 */
	
	public static boolean desactivarCliente(int idcliente){
		for (int i = 0; i < listaclientes.size(); ++i) {
			Cliente cliente = listaclientes.get(i);
            if (cliente.obterUserid() == idcliente) {
            	listaclientes.remove(i);
            	return true;
            }
        }
		return false;
	}
	
	/**
	 * Lista os clientes do banco que tem o nome igual ao recebido como parametro.
	 * Caso seja passada uma string vazia entao sao listados todos os clientes
	 * @param criterionome nome do cliente a listar
	 */
	public static void listarClientes(String criterionome){
		//implementar o codigo deste metodo
//		Collections.sort(listaclientes);
		for(Cliente cliente: listaclientes) {
			if(criterionome.equals("") || cliente.obterNome().equalsIgnoreCase(criterionome)) {
				System.out.println(cliente.obterInformacoes());
			}
		}
//		System.out.println("Nao foi encontrado nenhum cliente com o nome especificado");
	}
	
	/**
	 * Procura o cliente com o id recebido como parametro e devolve-o caso exista.
	 * Caso n�o exista devolve null. Este metodo e utilizado noutros metodos do banco.
	 * @param userid id do cliente a procurar
	 * @return devolve o cliente com o id procurado ou null
	 */
	public static Cliente procurarCliente(int userid){
		//implementar o codigo deste metodo
		for (int i = 0; i < listaclientes.size(); ++i) {
			Cliente cliente = listaclientes.get(i);
            if (cliente.obterUserid() == userid) {
            	return cliente;
            }
		}
		return null;
	}
	
	/**
	 * Utiliza o userid para encontrar o respectivo cliente e se existir confirma
	 * que a password e a correcta para esse utilizador. Caso isso se verifique 
	 * devolve o cliente que fez login
	 * @param userid id do utilizador que esta a fazer login
	 * @param password password do utilizador que esta a fazer login
	 * @return Cliente que acabou de fazer login ou null caso n�o coincidam as cred�nciais
	 */
	public static Cliente validarLogin(int userid, int password) {
		//implementar o codigo deste metodo
		for (int i = 0; i < listaclientes.size(); ++i) {
			Cliente cliente = procurarCliente(userid);
            if (cliente.obterPassword() == password) return cliente;
		}
		return null;
	}
	
	/**
	 * Cria um cliente com os dados recebidos e adiciona-o � lista de 
	 * clientes do banco
	 * @param nome nome do cliente a adicionar
	 * @param userid userid do cliente a adicionar
	 * @param password password do cliente a adicionar
	 */
	public static Cliente criarCliente(String nome, int userid, int password){
		//implementar o codigo deste metodo
		Cliente cliente = new Cliente(nome, userid, password);
		listaclientes.add(cliente);
		return cliente;
	}
	
	public static void criarCliente(String nome, int userid, int password, boolean ativo){
		//implementar o codigo deste metodo
		listaclientes.add(new Cliente(nome, userid, password, ativo));
	}
	
	
	
//	public static void adicionarCliente(Cliente cliente) {
//		listaclientes.add(cliente);
//	}
	
////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	* Procura em todos os clientes por uma conta com o nib recebido como par�metro
	* Devolve o objeto conta caso exista ou null caso n�o exista
	* @param nib nib da conta a procurar
	* @return Conta com o nib especificado
	*/
	public static Conta obterConta(int nib){
	    //implementar o codigo deste metodo
		for(int i=0; i>listaclientes.size(); ++i) {
			Cliente cliente = listaclientes.get(i);
			if(cliente.obterConta(nib) != null) {
				return cliente.obterConta(nib);
			}
		}
		return null;
	}

	/**
	* Procura o cliente com o id recebido por par�metro. Caso este exista adiciona
	* o objeto conta recebido por par�metro �s contas desse cliente
	* @param idcliente id do cliente a adicionar a Conta
	* @param c Conta a adicionar
	*/
	@SuppressWarnings("unused")
	private static void adicionaConta(int idcliente, Conta c) {
	    //implementar o codigo deste m�todo
		if(procurarCliente(idcliente) != null) {
			procurarCliente(idcliente).adicionarConta(c);
		}
	}

	/**
	* Cria uma conta para o cliente c com o tipo tipoconta
	* ESTE METODO ASSUME QUE O TIPO DE CONTA E VALIDO (1 - DEBITO / 2 - PRAZO)
	* @param c O cliente sobre o qual vai ser criada a conta
	* @param tipoconta o tipo da conta a criar
	* @return O nib da nova conta criada
	*/
	public static int criarConta(Cliente c, int tipoconta){
	    //implementar o codigo deste m�todo
		if(tipoconta == 1) {
			Conta conta = new Debito();
			c.adicionarConta(conta);
			return conta.obterNib();
			
		} else if (tipoconta == 2) {
			Conta conta = new Prazo();
			c.adicionarConta(conta);
			return conta.obterNib();
		}
		return 0;
	}
	
	public static void criarContaDebito(Cliente cli, double saldo, int nib, Date datacriacao, boolean ativo){
		Conta conta = new Debito(saldo,nib,datacriacao,ativo);
	    cli.listacontas.add(conta);
	}
	
	public static void criarContaPrazo(Cliente cli, double saldo, int nib, Date datacriacao, boolean ativa, Date datavalidade, int acumulacaoJuros){
		Conta conta = new Prazo(saldo,nib,datacriacao,ativa,datavalidade,acumulacaoJuros);
	    cli.listacontas.add(conta);
	}
	
  //////////
//Ficheiros 
 //////////
	
	private static void carregarDados(){
	    File ficheiroclientes = new File(FICHEIROCLIENTES);
	    File ficheirocontas = new File(FICHEIROCONTAS);
	    File ficheirotransacoes = new File(FICHEIROTRANSACOES);
//	    File ficheiroconfiguracoes = new File(FICHEIROCONFIGURACOES);

	    if(!ficheiroclientes.exists()){
	        System.out.println("Nao e possivel carregar dados pois nao existe o ficheiro de clientes para carregar");
	        return;
	    }

	    if(!ficheirocontas.exists()){
	        System.out.println("Nao e possivel carregar dados pois nao existe o ficheiro de contas para carregar");
	        return;
	    }

	    if(!ficheirotransacoes.exists()){
	        System.out.println("Nao e possivel carregar dados pois nao existe o ficheiro de transacoes para carregar");
	        return;
	    }

	    try{
	        importarClientes(ficheiroclientes);
	        importarContas(ficheirocontas);
	        importarTransacoes(ficheirotransacoes);
	    }
	    catch (Exception e) {
	        System.out.println("Erro na importacao dos ficheiros");
	    }
	}
	
	private static void importarClientes(File ficheiroclientes) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(ficheiroclientes));
	    String texto = br.readLine();

	    while (texto != null) {
	        String[] dadosficheiro = texto.split(";");

	        String nome = dadosficheiro[0];
	        int userid = Integer.parseInt(dadosficheiro[1]);
	        int password = Integer.parseInt(dadosficheiro[2]);
	        boolean ativo = Boolean.parseBoolean(dadosficheiro[3]);

	        Banco.criarCliente(nome, userid, password, ativo);
	        texto = br.readLine();
	    }
	}
	
	public static void importarContas(File ficheirocontas) throws IOException, ParseException {
		BufferedReader br = new BufferedReader(new FileReader(ficheirocontas));
		String texto = br.readLine();
		
		 while (texto != null) {
			 String[] dadosficheiro = texto.split(";");
			 
			 int userid = Integer.parseInt(dadosficheiro[0]);
			 String tipo = dadosficheiro[1];
			 double saldo = Double.parseDouble(dadosficheiro[2]);
			 int nib = Integer.parseInt(dadosficheiro[3]);
			 boolean ativo = Boolean.parseBoolean(dadosficheiro[4]);
			 String dataCriacaoStr = dadosficheiro[5];
			 
			 //exclusivas de prazo
//			 = dadosficheiro[6];
			String dataValidadeStr = dadosficheiro[7];
//			 = dadosficheiro[8];
			 
			 SimpleDateFormat dateformat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
			 Date datacriacao = dateformat.parse(dataCriacaoStr);
			 Date datavalidade = dateformat.parse(dataValidadeStr);
			 
			 Cliente cli = procurarCliente(userid);
			 int tipoconta = 0;
			 
		     if(tipo.equalsIgnoreCase("debito")) {
		    	 tipoconta = 1;
		    	 criarContaDebito(cli, saldo, nib, datacriacao, ativo);
		     } else if(tipo.equalsIgnoreCase("prazo")) {
		    	 tipoconta = 2;
		    	 criarContaPrazo(cli, saldo, nib, datacriacao, ativo, datavalidade, acumulacaoJuros);
		     }
//			 Conta con = Banco.obterConta(nib);
			 
			 texto = br.readLine();
			 }

	}
	
	public static void importarTransacoes(File ficheirotransacoes) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(ficheirotransacoes));
		String texto = br.readLine();
		
		 while (texto != null) {
		        String[] dadosficheiro = texto.split(";");

//		        String nome = dadosficheiro[0];
//		        int userid = Integer.parseInt(dadosficheiro[1]);
//		        int password = Integer.parseInt(dadosficheiro[2]);
//		        boolean ativo = Boolean.parseBoolean(dadosficheiro[3]);
//
//		        Banco.criarCliente(nome, userid, password, ativo);
//		        texto = br.readLine();
		    }
	}
}
