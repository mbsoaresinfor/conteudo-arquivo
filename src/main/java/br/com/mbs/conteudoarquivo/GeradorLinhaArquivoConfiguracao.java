package br.com.mbs.conteudoarquivo;

import br.com.mbs.conteudoarquivo.formatador.FormatadorValor;
import br.com.mbs.conteudoarquivo.validador.ValidadorCampo;
import br.com.mbs.conteudoarquivo.validador.ValidadorEntidade;

public interface GeradorLinhaArquivoConfiguracao {

	public FormatadorValor getFormatadorValorAlfaNumerico();
	
	public FormatadorValor getFormatadorValorNumerico();
	
	public FormatadorValor getFormatadorData();

	
	public int getTotalCaracteresLinha();
	
	public Character getCharacterPreenchimentoLinha();
}
