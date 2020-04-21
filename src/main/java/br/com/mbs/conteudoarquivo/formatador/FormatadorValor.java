package br.com.mbs.conteudoarquivo.formatador;

import br.com.mbs.conteudoarquivo.annotation.Campo;

public interface FormatadorValor<T> {

	public String formata(T valor,Campo campo);
}
