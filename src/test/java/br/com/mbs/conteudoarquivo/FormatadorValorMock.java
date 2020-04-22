package br.com.mbs.conteudoarquivo;

import br.com.mbs.conteudoarquivo.annotation.Campo;
import br.com.mbs.conteudoarquivo.formatador.FormatadorValor;

public class FormatadorValorMock implements FormatadorValor<String> {

	@Override
	public String formata(String valor, Campo campo) {
		return "TESTEab";
	}

}
