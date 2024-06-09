package com.livraria.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.livraria.exception.NegocioException;
import com.livraria.exception.TabelaDeErros;
import com.livraria.model.dto.ErroDto;

@RestControllerAdvice
public class ErroController {

	@Autowired
	private MessageSource messageSource;
	@ExceptionHandler(NegocioException.class)
	@ResponseBody
	//validacao: erro de negocio
	public ResponseEntity<ErroDto> handle(NegocioException exception) {
				
		ErroDto erroDto = new ErroDto();
		erroDto.setCodigoErro(exception.getCodigoErro());
		erroDto.setMensagem(exception.getMensagem());

		return ResponseEntity.status(exception.getCodigoHttp()).body(erroDto);
	}
	
	
	@ExceptionHandler(BindException.class)
	@ResponseBody
	//validacao: erro de requisicao
	public ResponseEntity<ErroDto> handle(BindException exception) {
		List<String> validacoes = new ArrayList<>();
		for (FieldError error : exception.getBindingResult().getFieldErrors()) {
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			validacoes.add(error.getField() + ": " + mensagem);
		}
		
		TabelaDeErros tabela = TabelaDeErros.VALIDACAO;
		
		ErroDto erroDto = new ErroDto();
		erroDto.setCodigoErro(tabela.getCodigoErro());
		erroDto.setMensagem(tabela.getMensagem());
		erroDto.setValidacoes(validacoes);
		
		return ResponseEntity.status(tabela.getCodigoHttp()).body(erroDto);
	}
	
	
}