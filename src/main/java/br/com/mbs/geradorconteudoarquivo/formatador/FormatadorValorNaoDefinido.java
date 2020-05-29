package br.com.mbs.geradorconteudoarquivo.formatador;

import br.com.mbs.geradorconteudoarquivo.annotation.Campo;

public class FormatadorValorNaoDefinido implements FormatadorValor<String> {

	@Override
	public String formata(String valor, Campo campo) {
			return "";
	}

}
