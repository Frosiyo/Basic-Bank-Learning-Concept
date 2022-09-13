package contas;

import static java.lang.System.*;

import java.util.Date;

import transaccoes.Levantamento;
import transaccoes.Transacao;
import transaccoes.Transferencia;

public class Debito extends Conta{
	
	public boolean levantar(double valor) {
		boolean levantamentoConfirmado = valor <= this.saldo;
		if(levantamentoConfirmado) {
		this.saldo -= valor;
		out.println("Novo saldo: " +this.obterSaldo());
		Transacao levantar = new Levantamento(new Date(), this, valor);
        this.listaTransaccoes.add(levantar);
		}
		return levantamentoConfirmado;
	}
	
	public boolean transferir(double valor, Conta contadestino) {
		boolean levantamentoConfirmado = valor <= this.saldo;
		if(levantamentoConfirmado) {
		this.saldo -= valor;
		contadestino.saldo += valor;
		out.println("Novo saldo: " +this.obterSaldo());
		Transacao transferir = new Transferencia(new Date(), this, valor, contadestino);
        this.listaTransaccoes.add(transferir);
		}
		return levantamentoConfirmado;
	}
	
	public String mostrar() {
		return String.valueOf(this.obterNib()+
				"\nTipo de conta: Debito\n");
	}
	
	public String mostrarInformacoes() {
		return "\nSaldo: " +this.obterSaldo()+
				"\nNIB: " +this.obterNib()+
				"\nData de criacao: " +this.obterDataCriacao()+
				"\nTipo de conta: Debito" +
				"\nEstado da conta: " +(this.estaActiva() ? "Ativa\n" : "Nao ativa\n");
	}
	
	public void mostrarSaldo() {
		out.println("Saldo:" +this.obterSaldo()+"\n");
	}
	
	public String obterTipo() {
		return "Debito";
	}
	
	public Debito() {}
	
	public Debito(double saldo, int nib, Date datacriacao, boolean activa) {
		this.saldo = saldo;
		this.nib = nib;
		this.datacriacao = datacriacao;
		this.activa = activa;
	}
}
