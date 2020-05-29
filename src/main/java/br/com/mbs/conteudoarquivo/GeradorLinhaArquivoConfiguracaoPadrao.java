package br.com.mbs.conteudoarquivo;

import java.math.BigDecimal;

import br.com.mbs.conteudoarquivo.formatador.FormatadorValor;
import br.com.mbs.conteudoarquivo.formatador.FormatadorValorAlfaNumericoPadrao;
import br.com.mbs.conteudoarquivo.formatador.FormatadorValorNumericoPadrao;
import br.com.mbs.conteudoarquivo.validador.ValidadorCampo;
import br.com.mbs.conteudoarquivo.validador.ValidadorCampoPadrao;
import br.com.mbs.conteudoarquivo.validador.ValidadorEntidade;
import br.com.mbs.conteudoarquivo.validador.ValidadorEntidadePadrao;

/**
 * GeradorLinhaArquivoConfiguracao padrao
 * 
 * @author marcelo.soares
 *
 */
public class  GeradorLinhaArquivoConfiguracaoPadrao implements GeradorLinhaArquivoConfiguracao{

	private FormatadorValor<String> formatadorValorAlfaNumericoPadrao = new FormatadorValorAlfaNumericoPadrao();
	private FormatadorValor<BigDecimal> formatadorValorNumericoPadrao = new FormatadorValorNumericoPadrao();
	private ValidadorCampo validadorCampoPadrao = new ValidadorCampoPadrao(this);
	private ValidadorEntidade validadorEntidade = new ValidadorEntidadePadrao(this);
	
	@Override
	public FormatadorValor<String> getFormatadorValorAlfaNumerico() {		
		return formatadorValorAlfaNumericoPadrao;
	}

	@Override	
	public FormatadorValor<BigDecimal> getFormatadorValorNumerico() {
		return formatadorValorNumericoPadrao;
	}
	
	@Override
	public int getTotalCaracteresLinha() {		
		return 50;
	}

	@Override
	public Character getCharacterPreenchimentoLinha() {
		return " ".charAt(0);
	}

}
