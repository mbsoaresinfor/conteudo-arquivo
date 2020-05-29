package br.com.mbs.conteudoarquivo.formatador;

import br.com.mbs.conteudoarquivo.annotation.Campo;

public class FormatadorValorAlfaNumericoPadrao implements FormatadorValor<String>{
	
	@Override
	public String formata(String valor, Campo campo) {
		
		StringBuffer retorno = null ;
		String valorTrim = valor.trim().toString();
		Integer quantPrenchimento = campo.tamParteInteira() - valorTrim.length();
		boolean valorMaiorDefinido = quantPrenchimento < 0;
		if(valorMaiorDefinido){
			retorno = new StringBuffer(valorTrim.length());
			retorno.append(valorTrim.substring(0, campo.tamParteInteira()));
		}else{
			int tamStringBuffer = valorTrim.length() + quantPrenchimento;
			retorno = new StringBuffer(tamStringBuffer);
			retorno.append(valorTrim);
			String espaco = " ";		
			for(int i=0; i < quantPrenchimento; i++){
				retorno.append(espaco);
			}
		}		
		return retorno.toString();
		
	}
}
