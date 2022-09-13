package transaccoes;

import contas.Conta;

import java.util.Date;

public class Levantamento extends Transacao {
	
	public String obterTipo() {
		return "Levantamento";
	}
	
	public String mostrar() {
		return "\n"+obterTipo() + " - " +obterDataTransacaoString(this.dataTransacao)
		+ " - " +this.contaTransacao.obterNib() + " - " +this.obterValorTransacao()+"\n";
	}
	
	public Levantamento(Date data_transacao, Conta conta_transacao, double valor) {
		super(data_transacao, conta_transacao, valor);
	}
}
