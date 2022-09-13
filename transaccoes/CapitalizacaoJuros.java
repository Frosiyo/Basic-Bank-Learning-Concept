package transaccoes;

import contas.Conta;

import java.util.Date;

public class CapitalizacaoJuros extends Transacao {
	
	public String obterTipo() {
		return "CapitalizacaoJuros";
	}
	
	public String mostrar() {
		return "\n"+obterTipo() + " - " +obterDataTransacaoString(this.dataTransacao)
		+ " - " +this.contaTransacao.obterNib() + " - " +this.obterValorTransacao()+"\n";
	}
	
	public CapitalizacaoJuros(Date data_transacao, Conta conta_transacao, double valor_transacao) {
		super(data_transacao, conta_transacao, valor_transacao);
	}
}
