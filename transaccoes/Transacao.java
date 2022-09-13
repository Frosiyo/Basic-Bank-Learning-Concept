package transaccoes;

import contas.Conta;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Transacao {
    protected Date dataTransacao;
    protected Conta contaTransacao;
    protected double valorTransacao;

    public abstract String obterTipo();
    public abstract String mostrar();
    

    public Transacao(Date data_transacao, Conta conta_transacao, double valor) {
        this.dataTransacao = data_transacao;
        this.contaTransacao = conta_transacao;
        this.valorTransacao = valor;
    }
    
    public String obterDataTransacaoString(Date date) {
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    	date = new Date();
		return formatter.format(date);
    }
    
    public Date obterDataTransacaoDate() {
    	return dataTransacao;
    }
    
    public double obterValorTransacao() {
    	return valorTransacao;
    }
    
    public Conta obterContaTransacao() {
    	return contaTransacao;
    }
}