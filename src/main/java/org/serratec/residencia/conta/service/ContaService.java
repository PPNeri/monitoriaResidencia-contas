package org.serratec.residencia.conta.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.serratec.residencia.conta.model.Conta;
import org.serratec.residencia.conta.validacao.SaldoNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

	public List<Conta> contas = new LinkedList<Conta>(Arrays.asList(new Conta(1L, "Paulo", 80.3),
			new Conta(2L, "Joana", 60.0), new Conta(3L, "Maria", 55.0), new Conta(4L, "Rafael", 18.3),
			new Conta(5L, "Ricardo", 180.3), new Conta(6L, "Paulo Henrique", 40.3), new Conta(7L, "Maria Luiza", 80.3),
			new Conta(8L, "Larissa", 80.3))

	);

	public List<Conta> listaContas() {

		return this.contas;

	}

	public Conta buscaContaPorNumero(Long numero) {

		Conta contaEncontrada = null;

		for (Conta conta : contas) {
			if (conta.getNumero() == numero) {
				contaEncontrada = conta;
				break;
			}
		}

		return contaEncontrada;
	}

	public ResponseEntity<?> sacar(Long contaNumero, Double valor) throws SaldoNotValidException {

		Conta conta = buscaContaPorNumero(contaNumero);

		Double saldo = conta.getSaldo();

		double novoSaldo = saldo - valor;

		if (novoSaldo < 0) {
			throw new SaldoNotValidException(conta.getSaldo(), conta.getTitular());

		}

		conta.setSaldo(novoSaldo);

		return new ResponseEntity<>(
				String.format("Sr. %s seu saldo atual agora é %.2f reais", conta.getTitular(), novoSaldo),
				HttpStatus.OK);

	}

	public Double depositar(Long contaNumero, Double valor) {

		Conta conta = buscaContaPorNumero(contaNumero);

		Double saldo = conta.getSaldo();

		double novoSaldo = saldo + valor;

		conta.setSaldo(novoSaldo);

		return novoSaldo;

	}

	public Conta editar(Long numero, Conta c) {
		Conta conta = buscaContaPorNumero(numero);

		conta.setTitular(c.getTitular());
		conta.setNumero(c.getNumero());

		return conta;

	}

}
