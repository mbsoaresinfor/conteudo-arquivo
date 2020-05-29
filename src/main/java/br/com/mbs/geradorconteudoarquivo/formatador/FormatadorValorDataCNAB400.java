package br.com.mbs.geradorconteudoarquivo.formatador;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.mbs.geradorconteudoarquivo.annotation.Campo;

public class FormatadorValorDataCNAB400 implements FormatadorValor<String> {

	@Override
	public String formata(String valor, Campo campo) {

		String valorFormatado=null;
		String mascara = "ddMMyy";
		try{			
			SimpleDateFormat formatador = new SimpleDateFormat(mascara);
			valorFormatado =  formatador.format(new Date(valor));
		}catch(Exception e){
			throw new IllegalArgumentException("Ocorreu um erro para formatar a data " + valor + " com a mascara " + mascara+"." +
					"\nUtilize a classe java.util.Date e sua funcao toGMTString, para gerar uma String",e);
		}
		return valorFormatado;
	}

}
