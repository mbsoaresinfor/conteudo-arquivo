package br.com.mbs.conteudoarquivo.validador;


import java.lang.reflect.Field;

import br.com.mbs.conteudoarquivo.GeradorLinhaArquivoConfiguracao;



public abstract class  ValidadorCampo{

	protected GeradorLinhaArquivoConfiguracao configuracao;
	
	public ValidadorCampo(GeradorLinhaArquivoConfiguracao configuracao){
		this.configuracao = configuracao;
	}
	
	public abstract void validaAntes(Field field, String campoValorOriginal) throws Exception;
	
	public abstract void validaApos(Field field, String campoFormatado) throws Exception;
}
