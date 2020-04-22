package br.com.mbs.conteudoarquivo.validador;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.lang.reflect.Field;

import junit.framework.Assert;

import org.junit.Test;

import br.com.mbs.conteudoarquivo.GeradorLinhaArquivoConfiguracao;
import br.com.mbs.conteudoarquivo.GeradorLinhaArquivoConfiguracaoPadrao;
import br.com.mbs.conteudoarquivo.annotation.Campo;


public class ValidadorEntidadePadraoTest {

	
	GeradorLinhaArquivoConfiguracao configurador = new GeradorLinhaArquivoConfiguracaoPadrao();
	ValidadorEntidadePadrao validadorEntidadePadrao = new ValidadorEntidadePadrao(configurador);
	
	
	
	@Test(expected=Exception.class)
	public void testTamanhoTotalCaracteresNaLinhaAcimaTotalDefinido() throws Exception {	
		validadorEntidadePadrao.valida(new Teste2());
	}
	 
	@Test
	public void testTamanhoTotalCaracteresNaLinha() throws Exception {
		 Teste1 teste = new  Teste1();
		 try{
			 validadorEntidadePadrao.valida(teste);
		 }catch(Exception e){
			 Assert.fail("Noa poderia ter ocorrido uma falha neste teste");
		 }
	}
	
	
	class Teste1 implements Serializable{
		
		@Campo(tamParteInteira=400,tamParteDecimal=0,posicaoRegistro=3,obrigatorio=true,valorDefault="REMESSA")
		private String teste1;
		
		

		public String getTeste1() {
			return teste1;
		}

		public void setTeste1(String teste1) {
			this.teste1 = teste1;
		}

				
	}
	
	class Teste2 implements Serializable{
		
		@Campo(tamParteInteira=399,tamParteDecimal=0,posicaoRegistro=3,obrigatorio=true,valorDefault="REMESSA")
		private String teste1;
		
		@Campo(tamParteInteira=1,tamParteDecimal=1,posicaoRegistro=100,obrigatorio=true,valorDefault="ABC")
		private String teste2;
		

		public String getTeste1() {
			return teste1;
		}

		public void setTeste1(String teste1) {
			this.teste1 = teste1;
		}

		public String getTeste2() {
			return teste2;
		}

		public void setTeste2(String teste2) {
			this.teste2 = teste2;
		}		
	}
	
	private Field retornaAnnotationCampo(Object obj,String nameCampo) throws SecurityException, NoSuchFieldException{		
		Field field = null;
		for(int i=0; i < obj.getClass().getDeclaredFields().length;i++){
			Field fieldAtual = obj.getClass().getDeclaredFields()[i];
			if(fieldAtual.getName().equals(nameCampo)){
				field = fieldAtual;
				break;
			}			
		}
		if(field == null){
			throw new IllegalArgumentException("Nao encontrado campo com nome " + nameCampo);
		}
		return  field;
}

}
