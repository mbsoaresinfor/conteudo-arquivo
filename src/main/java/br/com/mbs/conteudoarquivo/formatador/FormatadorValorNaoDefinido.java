package br.com.mbs.conteudoarquivo.formatador;

import br.com.mbs.conteudoarquivo.annotation.Campo;


public class FormatadorValorNaoDefinido implements FormatadorValor<String> {

	@Override
	public String formata(String valor, Campo campo) {
			return "";
	}

}
