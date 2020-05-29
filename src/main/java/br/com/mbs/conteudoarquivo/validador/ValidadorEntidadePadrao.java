package br.com.mbs.conteudoarquivo.validador;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
		validaValoresSobrescritoDaAnotacaoCAmpo(entidade);
	}
	
	private void validaValoresSobrescritoDaAnotacaoCAmpo(Serializable entidade) throws Exception {
		
		List<Integer> listaPosicoes = new ArrayList<Integer>();
		
		for(Field field:  entidade.getClass().getDeclaredFields()){			
			if(campoHelper.temAnnotationCampo(field)){
				processaValidaValoresSobrescritoDaAnotacaoCAmpo(entidade, listaPosicoes, field);				
			}
		}
	}

	private void processaValidaValoresSobrescritoDaAnotacaoCAmpo(Serializable entidade, List<Integer> listaPosicoes,
			Field field) throws Exception {
		
		int tamanhoTotal =  getTamanhoTotal(campoHelper.getAnnotationCampo(field));
		int posicaoFinal = getTamanhoFinal(campoHelper.getAnnotationCampo(field), tamanhoTotal);
		for(int i=campoHelper.getAnnotationCampo(field).posicaoRegistro(); i <= posicaoFinal -1;i++) {
			if(listaPosicoes.contains(i)) {					
				geraExcessaoSobrescritaValoresAnotacaoCampo(entidade);
			}else {
				listaPosicoes.add(i);
			}					
		}
	}

	private void geraExcessaoSobrescritaValoresAnotacaoCampo(Serializable entidade) throws Exception {
		throw new Exception("A entidade " + entidade.getClass().getSimpleName() +" possui problemas nos valores da anotacao " + Campo.class.getSimpleName() + 
				"\nVerifique o valor de cada campo a posicaoRegistro, tamanhoParteInteira e tamanhoParteDecimal, pois parece que tem valores sobrescrendo de outros campos");
	}

	private int getTamanhoFinal(Campo annotation, int tamanhoTotal) {
		return tamanhoTotal + annotation.posicaoRegistro();
	}

	private int getTamanhoTotal(Campo annotation) {
		return annotation.tamParteInteira() + annotation.tamParteDecimal();
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
