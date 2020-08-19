package org.serratec.residencia.conta.validacao;

public class SaldoNotValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Double saldo;
	private String nome;

	public SaldoNotValidException(Double saldo, String nome) {
		super();
		this.saldo = saldo;
		this.nome = nome;
	}

	public Double getSaldo() {
		return saldo;
	}

	public String getNome() {
		return nome;
	}

}
