package br.com.mbs.conteudoarquivo.validador;

import java.io.Serializable;
import java.lang.reflect.Field;

import br.com.mbs.conteudoarquivo.GeradorLinhaArquivoConfiguracao;
import br.com.mbs.conteudoarquivo.annotation.Campo;
import br.com.mbs.conteudoarquivo.annotation.CampoHelper;


public class ValidadorEntidadePadrao extends ValidadorEntidade {

	private CampoHelper campoHelper = new CampoHelper();
	
	public ValidadorEntidadePadrao(GeradorLinhaArquivoConfiguracao configuracao) {
		super(configuracao);
	}

	@Override
	public void valida(Serializable entidade) throws Exception {

		validaTotalCaracteres(entidade);
		
	}

	private void validaTotalCaracteres(Serializable entidade) throws Exception {

		int contadorCaracteres = 0;
		for(Field field:  entidade.getClass().getDeclaredFields()){
			Campo annotation = field.getAnnotation(Campo.class);
			if(annotation != null){
				contadorCaracteres = contadorCaracteres + annotation.tamParteInteira();
				contadorCaracteres = contadorCaracteres + annotation.tamParteDecimal();
			}
		}	
		
		if(contadorCaracteres > configuracao.getTotalCaracteresLinha()){
			throw new Exception("A entidade " + entidade.getClass().getSimpleName() + " possui mais caracteres do que esta definido"+
		  " na " + GeradorLinhaArquivoConfiguracao.class.getSimpleName() + "\nVerifique o tamanho da parte inteira e decimal.");
			
		}
		
		
	}

	
	
	 

	

}
