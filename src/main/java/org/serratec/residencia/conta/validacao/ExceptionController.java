package org.serratec.residencia.conta.validacao;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

	private final static Logger Log = Logger.getLogger(ExceptionController.class.getName());

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(SaldoNotValidException.class)
	public ResponseEntity<?> tratamentoSaldoIndisponivel(SaldoNotValidException e) {
		String str = String.format("Sr. %s seu saldo é insuficiente. Saldo atual : %.2f reais", e.getNome(),
				e.getSaldo());
		Log.info(str);
		return new ResponseEntity<>(str, HttpStatus.BAD_REQUEST);
	}

}
