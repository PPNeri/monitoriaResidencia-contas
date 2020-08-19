package org.serratec.residencia.conta.controller;

import java.util.List;

import org.serratec.residencia.conta.model.Conta;
import org.serratec.residencia.conta.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	ContaService contaService;

	@GetMapping()
	public List<Conta> findAll() {
		return contaService.listaContas();

	}

	@GetMapping("/saque/{contaNumero}/{valor}")
	public ResponseEntity<?> operacaoSacar(@PathVariable Long contaNumero, @PathVariable Double valor) {
		return contaService.sacar(contaNumero, valor);
	}

	@GetMapping("/deposito/{contaNumero}/{valor}")
	public Double operacaoDepositar(@PathVariable Long contaNumero, @PathVariable Double valor) {
		return contaService.depositar(contaNumero, valor);
	}

	@PutMapping("/edit/{numero}")
	public Conta update(@PathVariable Long numero, @RequestBody Conta conta) {

		return contaService.editar(numero, conta);

	}

}
