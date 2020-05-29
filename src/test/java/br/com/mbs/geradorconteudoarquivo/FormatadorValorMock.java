package br.com.mbs.geradorconteudoarquivo;

import br.com.mbs.geradorconteudoarquivo.annotation.Campo;
import br.com.mbs.geradorconteudoarquivo.formatador.FormatadorValor;

public class FormatadorValorMock implements FormatadorValor<String> {

	@Override
	public String formata(String valor, Campo campo) {
		return "TESTEab";
	}

}
