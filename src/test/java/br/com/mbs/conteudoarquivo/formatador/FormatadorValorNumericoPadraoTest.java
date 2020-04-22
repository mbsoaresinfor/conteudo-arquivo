package br.com.mbs.conteudoarquivo.formatador;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import junit.framework.Assert;
import org.junit.Test;

import br.com.mbs.conteudoarquivo.annotation.Campo;



public class FormatadorValorNumericoPadraoTest  {

	
	@Test
	public void testFormat() throws SecurityException, NoSuchFieldException{
		FormatadorValorNumericoPadrao format = new FormatadorValorNumericoPadrao();
		Teste objeto = new Teste();
		
		objeto.setValor1(new BigDecimal("1"));
		String ret = format.formata(objeto.getValor1(), retornaAnnotationCampo(objeto,"valor1"));
		Assert.assertEquals("01", ret);
		
		objeto.setValor2(new BigDecimal("123"));
		ret = format.formata(objeto.getValor2(), retornaAnnotationCampo(objeto,"valor2"));
		Assert.assertEquals("00123", ret);
		
		objeto.setValor3(new BigDecimal("50.32"));
		ret = format.formata(objeto.getValor3(), retornaAnnotationCampo(objeto,"valor3"));
		Assert.assertEquals("05032", ret);
		
		objeto.setValor4(new BigDecimal("34.1"));
		ret = format.formata(objeto.getValor4(), retornaAnnotationCampo(objeto,"valor4"));
		Assert.assertEquals("00034100", ret);
		
	}
	
	class Teste{
		
		@Campo(tamParteInteira=2,tamParteDecimal=0,posicaoRegistro=1,obrigatorio=true,valorDefault="0")
		private BigDecimal valor1;
		
		@Campo(tamParteInteira=5,tamParteDecimal=0,posicaoRegistro=5,obrigatorio=true,valorDefault="0")
		private BigDecimal valor2;
		
		@Campo(tamParteInteira=3,tamParteDecimal=2,posicaoRegistro=50,obrigatorio=true,valorDefault="0")
		private BigDecimal valor3;
		
		@Campo(tamParteInteira=5,tamParteDecimal=3,posicaoRegistro=100,obrigatorio=true,valorDefault="0")
		private BigDecimal valor4;

		public BigDecimal getValor1() {
			return valor1;
		}

		public void setValor1(BigDecimal valor1) {
			this.valor1 = valor1;
		}

		public BigDecimal getValor2() {
			return valor2;
		}

		public void setValor2(BigDecimal valor2) {
			this.valor2 = valor2;
		}

		public BigDecimal getValor3() {
			return valor3;
		}

		public void setValor3(BigDecimal valor3) {
			this.valor3 = valor3;
		}

		public BigDecimal getValor4() {
			return valor4;
		}

		public void setValor4(BigDecimal valor4) {
			this.valor4 = valor4;
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
