package br.com.mbs.geradorconteudoarquivo;

import br.com.mbs.geradorconteudoarquivo.formatador.FormatadorValor;

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
