package br.com.mbs.conteudoarquivo.formatador;

import java.lang.reflect.Field;
import junit.framework.Assert;
import org.junit.Test;
import br.com.mbs.conteudoarquivo.annotation.Campo;


public class FormatadorValorAlfaNumericoPadraoTest {

	@Test
	public void testFormat() throws SecurityException, NoSuchFieldException{
		FormatadorValorAlfaNumericoPadrao format = new FormatadorValorAlfaNumericoPadrao();
		Teste teste = new Teste();
		teste.setNome("TESTE");
		String ret = format.formata(teste.getNome(),retornaAnnotationCampo(teste,"nome"));
		Assert.assertEquals("TESTE     ", ret);
		
	}
	
	@Test
	public void testFormatComEspaco() throws SecurityException, NoSuchFieldException{
		FormatadorValorAlfaNumericoPadrao format = new FormatadorValorAlfaNumericoPadrao();
		Teste teste = new Teste();
		teste.setNome("    TESTE    ");
		String ret = format.formata(teste.getNome(),retornaAnnotationCampo(teste,"nome"));
		Assert.assertEquals("TESTE     ", ret);
		teste.setNome1("                BRASIL");
		ret = format.formata(teste.getNome1(),retornaAnnotationCampo(teste,"nome1"));
		Assert.assertEquals("BRASIL         ", ret);
		
	}
	
	@Test
	public void testFormatComValorMaiorqueDefinido() throws SecurityException, NoSuchFieldException{
		FormatadorValorAlfaNumericoPadrao format = new FormatadorValorAlfaNumericoPadrao();
		Teste teste = new Teste();
		teste.setNome("12345678901");
		String ret = format.formata(teste.getNome(),retornaAnnotationCampo(teste,"nome"));
		Assert.assertEquals("1234567890", ret);
		
		
	}	
	
	class Teste{
		
		@Campo(tamParteInteira=10,tamParteDecimal=0,posicaoRegistro=1,obrigatorio=true,valorDefault="TESTE")
		private String nome;
		
		@Campo(tamParteInteira=15,tamParteDecimal=0,posicaoRegistro=11,obrigatorio=true,valorDefault="")
		private String nome1;

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getNome1() {
			return nome1;
		}

		public void setNome1(String nome1) {
			this.nome1 = nome1;
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
