package br.com.mbs.conteudoarquivo;

import br.com.mbs.conteudoarquivo.formatador.FormatadorValor;

/**
 * Configuracao de um GeradorLinhaArquivo
 * 
 * @author marcelo.soares
 *
 */
public interface GeradorLinhaArquivoConfiguracao {

	public FormatadorValor getFormatadorValorAlfaNumerico();
	
	public FormatadorValor getFormatadorValorNumerico();
	
	
	public int getTotalCaracteresLinha();
	
	public Character getCharacterPreenchimentoLinha();
}
