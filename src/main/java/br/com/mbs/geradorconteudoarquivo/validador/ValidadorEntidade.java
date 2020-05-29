package br.com.mbs.geradorconteudoarquivo.validador;


import java.io.Serializable;

import br.com.mbs.geradorconteudoarquivo.GeradorLinhaArquivoConfiguracao;


public abstract class  ValidadorEntidade{

	protected GeradorLinhaArquivoConfiguracao configuracao;
	
	public ValidadorEntidade(GeradorLinhaArquivoConfiguracao configuracao){
		this.configuracao = configuracao;
	}
	
	public abstract void valida(Serializable entidade) throws Exception;
	
	
}
