package br.com.mbs.conteudoarquivo.formatador;

import br.com.mbs.conteudoarquivo.annotation.Campo;

public class FormatadorValorAlfaNumericoPadrao implements FormatadorValor<String> {
	
	
	public String formata(String valor, Campo campo) {
		
		String valorTrim = valor.trim().toString();
		Integer quantPrenchimento = campo.tamParteInteira() - valorTrim.length();
		int tamStringBuffer = valorTrim.length() + quantPrenchimento;
		StringBuffer retorno = new StringBuffer(tamStringBuffer);
		retorno.append(valorTrim);
		String espaco = " ";		
		for(int i=0; i < quantPrenchimento; i++){
			retorno.append(espaco);
		}		
		return retorno.toString();
		
	}

	

}
