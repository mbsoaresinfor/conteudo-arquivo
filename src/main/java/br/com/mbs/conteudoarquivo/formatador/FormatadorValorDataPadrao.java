package br.com.mbs.conteudoarquivo.formatador;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.mbs.conteudoarquivo.annotation.Campo;



public class FormatadorValorDataPadrao implements FormatadorValor<Date> {

	
	public String formata(Date valor, Campo campo) {

		String valorFormatado=null;
		String mascara = "ddMMyy";
		try{			
			SimpleDateFormat formatador = new SimpleDateFormat(mascara);
			valorFormatado =  formatador.format(valor);
		}catch(Exception e){
			throw new IllegalArgumentException("Ocorreu um erro para formatar a data " + valor + " com a mascara " + mascara);
					
		}
		return valorFormatado;
	}

	

}
