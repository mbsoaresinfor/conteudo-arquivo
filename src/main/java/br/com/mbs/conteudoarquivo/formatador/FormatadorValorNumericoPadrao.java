package br.com.mbs.conteudoarquivo.formatador;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import br.com.mbs.conteudoarquivo.annotation.Campo;
import br.com.mbs.conteudoarquivo.formatador.FormatadorValor;




public class FormatadorValorNumericoPadrao implements FormatadorValor<BigDecimal>{
	
	private Locale specialLocale ;
	
	public FormatadorValorNumericoPadrao(){
		specialLocale = new Locale("pr", "BR");
	}
	
	public FormatadorValorNumericoPadrao(Locale locale){
		specialLocale = locale;
	}
	
	@Override
	public String formata(BigDecimal valor, Campo campo) {
		
		String mascara = criaMascara(valor, campo);		
		DecimalFormat formatter = getDecimalFormat();
		formatter.applyPattern(mascara);
        String retFormatado = formatter.format(valor);
        String retFinal = retFormatado;
        if(temParteDecimal(campo)){
        	retFinal = criaFormatacaoComValorComParteDecimal(retFormatado);
        }
		return retFinal;
	}
	
	private String criaFormatacaoComValorComParteDecimal(String valorJaFormatado ){
		int indiceSplitParteInteira = 0;
		int indiceSplitParteDecimal = 1;
		return valorJaFormatado.split(splitRegex())[indiceSplitParteInteira] + 
				valorJaFormatado.split(splitRegex())[indiceSplitParteDecimal];
	}
	
	private String splitRegex(){
		return "\\"+getDecimalSeparador();
	}
	
	private DecimalFormat getDecimalFormat(){
		return (DecimalFormat) NumberFormat.getNumberInstance(specialLocale);
	}
	
	private String criaMascara(BigDecimal valor, Campo campo){
		StringBuilder ret = new StringBuilder();
		for(int i=0; i < campo.tamParteInteira();i++){
			ret.append("0");			
		}
		if(temParteDecimal(campo)){
			ret.append(getDecimalSeparador());
			for(int i=0; i < campo.tamParteDecimal();i++){
				ret.append("0");			
			}
		}
		return ret.toString();
		
	}
	
	private char getDecimalSeparador(){
		return getDecimalFormat().getDecimalFormatSymbols().getDecimalSeparator();
	}
	
	private boolean temParteDecimal( Campo campo){
		return  campo.tamParteDecimal() != 0;
	}

}
