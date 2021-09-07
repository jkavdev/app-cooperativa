package br.com.jkavdev.cooperativa.api.exceptionhandler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.jkavdev.cooperativa.api.exceptionhandler.ErroRequisicao.Campo;
import br.com.jkavdev.cooperativa.dominio.exception.EntidadeNaoEncontradaException;
import br.com.jkavdev.cooperativa.dominio.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionhandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Campo> campos = ex.getBindingResult().getAllErrors().stream().map(error -> {
			return new ErroRequisicao.Campo(((FieldError) error).getField(),
					messageSource.getMessage(error, LocaleContextHolder.getLocale()));
		}).collect(Collectors.toList());

		ErroRequisicao erro = new ErroRequisicao(status.value(), "campo(s) inválido(s)!", campos);

		return handleExceptionInternal(ex, erro, headers, status, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErroRequisicao erro = new ErroRequisicao(status.value(), ex.getMessage());

		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		ErroRequisicao erro = new ErroRequisicao(status.value(), ex.getMessage());

		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}

}
