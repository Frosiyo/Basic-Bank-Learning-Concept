package transaccoes;

import contas.Conta;

import java.util.Date;

public class Transferencia extends Transacao {
	private Conta contadestino;
	
	public String obterTipo() {
		return "Transferencia";
	}
	
	public String mostrar() {
		return "\n"+obterTipo() + " - " +obterDataTransacaoString(this.dataTransacao)
		+ " - " +this.contaTransacao.obterNib() + " - " +this.obterValorTransacao()
		+ " - " +contadestino.obterNib()+ "\n";
	}
	
	public Transferencia(Date data_transacao, Conta conta_transacao, double valor, Conta contadestino) {
		super(data_transacao, conta_transacao, valor);
		this.contadestino = contadestino;
	}
}
