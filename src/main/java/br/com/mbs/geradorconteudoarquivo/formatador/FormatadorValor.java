package br.com.mbs.geradorconteudoarquivo.formatador;

import br.com.mbs.geradorconteudoarquivo.annotation.Campo;

public interface FormatadorValor<T> {

	public String formata(T valor,Campo campo);
}
