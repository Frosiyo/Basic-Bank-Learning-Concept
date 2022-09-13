package contas;

import static java.lang.System.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import transaccoes.*;

public class Prazo extends Conta {
	private static double taxaJuro = 0.05;
	@SuppressWarnings("unused")
	private double acumulacaoJuros;
	private Date dataValidade;
	
	public boolean levantar(double valor) {
		boolean levantamentoConfirmado = valor <= this.saldo;
		if(levantamentoConfirmado) {
		this.saldo -= valor-calculoJuros(valor);
		out.println("Novo saldo: " +this.obterSaldo());
		Transacao levantar = new Levantamento(new Date(), this, valor);
		this.listaTransaccoes.add(levantar);
		}
		return levantamentoConfirmado;
	}
	
	public boolean transferir(double valor, Conta contadestino) {
		boolean levantamentoConfirmado = valor <= this.saldo;
		if(levantamentoConfirmado) {
			Transacao transferir = new Transferencia(new Date(), this, valor, contadestino);
	        this.listaTransaccoes.add(transferir);
	        contadestino.listaTransaccoes.add(transferir);
	        this.saldo -= valor-calculoJuros(valor);
	        contadestino.saldo += valor;
	        out.println("Novo saldo: " +this.obterSaldo());
		}
		return levantamentoConfirmado;
	}
	
	public String mostrar() {
		return String.valueOf(this.obterNib()+
				"\nTipo de conta: Prazo\n");
	}
	
	public String mostrarInformacoes() {
		return "\nSaldo: " +this.obterSaldo()+
				"\nNIB: " +this.obterNib()+
				"\nData de criacao: " +this.obterDataCriacao()+
				"\nTipo de conta: Prazo" +
				"\nPrazo final: " +this.obterDataValidade()+
				"\nJuros acumulados: " +obterJuros()+
				"\nEstado da conta: " +(this.estaActiva() ? "Ativa\n" : "Nao ativa\n");
	}
	
	public void mostrarSaldo() {
		out.println("Saldo:" +this.obterSaldo()+"\n");
		out.println("Juros acumulados: " +calculoJuros(this.saldo)+"\n");
	}
	
	public String obterJuros() {
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(calculoJuros(this.saldo));
	}
	
	public String obterTipo() {
		return "Prazo";
	}
	
	public Calendar dataValidadeCalendario() {
		Calendar calendarioValidade = Calendar.getInstance();
		calendarioValidade.setTime(this.datacriacao);
		calendarioValidade.add(Calendar.YEAR, +1);
		return calendarioValidade;
	}
	
	public String obterDataValidade() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(dataValidade);
	}
	
	public double calculoJuros(double valor) {
		this.listaTransaccoes.add(new CapitalizacaoJuros(new Date(), this, valor));
		if (calcularDataDiferenca() > 0.01) {
			return calcularDataDiferenca()*taxaJuro*valor;	
		} else return 0;
	}
	
//		Calendar dataTeste = Calendar.getInstance();
//		dataTeste.add(Calendar.MONTH, 6);
//		Date teste = new Date(dataTeste.getTimeInMillis());
	
	public double calcularDataDiferenca() {
		Date atual = new Date();
		long tempoDecorrido = atual.getTime() - this.obterDataCriacaoDate().getTime();
		
		Date prazo = new Date(dataValidadeCalendario().getTimeInMillis());
		long tempoTotal = prazo.getTime() - this.obterDataCriacaoDate().getTime();
		
		return (double)tempoDecorrido / (double)tempoTotal;
	}
	
	public Prazo() {
		acumulacaoJuros = 0;
		dataValidade = dataValidadeCalendario().getTime();
	}
	
	public Prazo(double saldo, int nib, Date datacriacao, boolean activa, Date datavalidade, int acumulacaoJuros) {
		this.saldo = saldo;
		this.nib = nib;
		this.datacriacao = datacriacao;
		this.activa = activa;
		this.dataValidade = datavalidade;
		this.acumulacaoJuros = acumulacaoJuros;
	}
}
