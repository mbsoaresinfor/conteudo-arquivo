package br.com.mbs.conteudoarquivo.formatador;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import br.com.mbs.conteudoarquivo.annotation.Campo;



public class FormatadorValorDataPadraoTest {

	@Test
	public void testFormatacao() throws SecurityException, NoSuchFieldException {
		FormatadorValorDataPadrao formatador = new FormatadorValorDataPadrao();
		Teste objeto = new Teste();
		Date date = new Date(); 
		
		String ret = formatador.formata(date, retornaAnnotationCampo(objeto,"valor1"));
		Assert.assertTrue(ret.length() == 6);
		System.out.println(ret);
		
	}
	
	
	
	
	
	class Teste{
		
		@Campo(tamParteInteira=2,tamParteDecimal=0,posicaoRegistro=1,obrigatorio=true,valorDefault="0",formatadorValor=FormatadorValorDataPadrao.class)
		private String valor1;

		public String getValor1() {
			return valor1;
		}

		public void setValor1(String valor1) {
			this.valor1 = valor1;
		}
			
		
	}
	
	private Campo retornaAnnotationCampo(Object obj,String nameCampo) throws SecurityException, NoSuchFieldException{		
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
		return  field.getAnnotation(Campo.class);
}

}
