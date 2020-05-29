package br.com.mbs.conteudoarquivo.annotation;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

import br.com.mbs.conteudoarquivo.annotation.Campo;
import br.com.mbs.conteudoarquivo.annotation.CampoHelper;

public class CampoHelperTest {

	private CampoHelper campoHelper = new CampoHelper();
	
	@Test
	public void testGetPosicaoRegistro() throws IllegalArgumentException, IllegalAccessException{
			TesteAlfaNumerico teste = new TesteAlfaNumerico();
			teste.setValor("teste");
		
			Integer valor = campoHelper.getPosicaoRegistro(retornaAnnotationCampo(teste));
			Assert.assertTrue(valor.equals(2));			
	}
	
	@Test
	public void testGetIndexFinalParteInteira() throws IllegalArgumentException, IllegalAccessException{
			TesteAlfaNumerico teste = new TesteAlfaNumerico();
			teste.setValor("teste");
		
			Integer valor = campoHelper.getIndexFinalParteInteira(retornaAnnotationCampo(teste));
			Assert.assertTrue(valor.equals(9));			
	}
	
	@Test
	public void testRetornaValorAlfaNumerico() throws IllegalArgumentException, IllegalAccessException{
			TesteAlfaNumerico teste = new TesteAlfaNumerico();
			teste.setValor("teste");
		
			String valor = campoHelper.retornaValorAlfaNumerico(retornaCampo(teste), teste);
			Assert.assertEquals("teste",valor);			
	}
	
	@Test
	public void testRetornaValorNumerico() throws IllegalArgumentException, IllegalAccessException{
			TesteNumerico teste = new TesteNumerico();
			teste.setValor(new BigDecimal("10"));
		
			BigDecimal valor = campoHelper.retornaValorNumerico(retornaCampo(teste), teste);
			Assert.assertTrue( new BigDecimal("10").equals(valor));			
	}	
	
	
	class TesteAlfaNumerico implements Serializable{
		
		@Campo(tamParteInteira=7,tamParteDecimal=0,posicaoRegistro=3,obrigatorio=true,valorDefault="0")
		private String valor;

		public String getValor() {
			return valor;
		}

		public void setValor(String valor) {
			this.valor = valor;
		}			
	}
	
	class TesteNumerico implements Serializable{
		
		@Campo(tamParteInteira=2,tamParteDecimal=0,posicaoRegistro=1,obrigatorio=true,valorDefault="0")
		private BigDecimal valor;

		public BigDecimal getValor() {
			return valor;
		}

		public void setValor(BigDecimal valor) {
			this.valor = valor;
		}		
	}
	
	private Campo retornaAnnotationCampo(Object obj){
		Campo campo = null;
		for(Field field:  obj.getClass().getDeclaredFields()){
			Campo annotation = field.getAnnotation(Campo.class);
			if(annotation != null){
				campo = annotation;
				break;
			}
		}
		return campo;
	}
	
	private Field retornaCampo(Object obj){
		Field campo = null;
		for(Field field:  obj.getClass().getDeclaredFields()){
			Campo annotation = field.getAnnotation(Campo.class);
			if(annotation != null){
				campo = field;
				break;
			}
		}
		return campo;
	}
}
