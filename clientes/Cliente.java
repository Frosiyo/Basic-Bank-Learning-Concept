package clientes;

import contas.Conta;

import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class Cliente implements Comparable {
	protected String nome;
	protected int password, userid;
	protected boolean ativo;
	public ArrayList<Conta> listacontas;
	
	public String obterInformacoes() {
		return ("Nome: " +nome+
				"\nPassword: " +password+
				"\nUserid: " +userid+
				"\nativo:  " +ativo+ "\n");
	}
	
	public void adicionarConta(Conta contaCliente) {
		listacontas.add(contaCliente);
	}
	
	public String obterNome() {return nome;}
	
	public ArrayList<Conta> obterContas() {
		return listacontas;
		}
	
	public int obterPassword() {return password;}
	
	public int obterUserid() {return userid;}
	
	public boolean obterAtivo() {return ativo;}
	
//	public Cliente obterCliente() {return this;}
	
	public Conta obterConta(int nib) {
        for (int i = 0; i < listacontas.size(); ++i) {
            Conta conta = listacontas.get(i);
            if (conta.obterNib() == nib) return conta;
        }
        return null;
    }
	
	@Override
	public int compareTo(Object arg0) {
		Cliente clienteAComparar = (Cliente) arg0;
		return nome.compareTo(clienteAComparar.nome);
	}
	
//	public int compareTo(Object arg0) {
//	    Cliente clienteAComparar = (Cliente) arg0;
//	    return userid-clienteAComparar.userid;
//	}
	
	public Cliente(String nome_cliente, int userid_cliente, int password_cliente, boolean ativo_cliente) {
		nome = nome_cliente;
		password = password_cliente;
		userid = userid_cliente;
		ativo = ativo_cliente;
		listacontas = new ArrayList<Conta>();
	}
	
	public Cliente(String nome_cliente, int userid_cliente, int password_cliente) {
		nome = nome_cliente;
		password = password_cliente;
		userid = userid_cliente;
		ativo = true;
		listacontas = new ArrayList<Conta>();
	}
}
