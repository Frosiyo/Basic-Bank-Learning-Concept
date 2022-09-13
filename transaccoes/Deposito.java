package transaccoes;

import contas.Conta;

import java.util.Date;

public class Deposito extends Transacao {
	
	public String obterTipo() {
		return "Deposito";
	}
	
	public String mostrar() {
		return "\n"+obterTipo() + " - " +obterDataTransacaoString(this.dataTransacao)
		+ " - " +this.contaTransacao.obterNib() + " - " +this.obterValorTransacao()+"\n";
	}
	
	public Deposito(Date data_transacao, Conta conta_transacao, double valor) {
		super(data_transacao, conta_transacao, valor);
	}
}
