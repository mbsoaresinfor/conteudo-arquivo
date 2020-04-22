package br.com.mbs.conteudoarquivo;

import java.math.BigDecimal;
import java.util.Date;

import br.com.mbs.conteudoarquivo.formatador.FormatadorValor;
import br.com.mbs.conteudoarquivo.formatador.FormatadorValorAlfaNumericoPadrao;
import br.com.mbs.conteudoarquivo.formatador.FormatadorValorDataPadrao;
import br.com.mbs.conteudoarquivo.formatador.FormatadorValorNumericoPadrao;
import br.com.mbs.conteudoarquivo.validador.ValidadorCampo;
import br.com.mbs.conteudoarquivo.validador.ValidadorCampoPadrao;
import br.com.mbs.conteudoarquivo.validador.ValidadorEntidade;
import br.com.mbs.conteudoarquivo.validador.ValidadorEntidadePadrao;



public class  GeradorLinhaArquivoConfiguracaoPadrao implements GeradorLinhaArquivoConfiguracao{

	private FormatadorValor<String> formatadorValorAlfaNumericoPadrao = new FormatadorValorAlfaNumericoPadrao();
	private FormatadorValor<BigDecimal> formatadorValorNumericoPadrao = new FormatadorValorNumericoPadrao();
	private FormatadorValor<Date> formatadorData = new FormatadorValorDataPadrao();
	
	
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
		return 400;
	}

	@Override
	public Character getCharacterPreenchimentoLinha() {
		return " ".charAt(0);
	}
	

	@Override
	public FormatadorValor<Date> getFormatadorData() {		
		return formatadorData;
	}

}
