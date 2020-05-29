package br.com.mbs.conteudoarquivo.validador;

import static org.junit.Assert.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import junit.framework.Assert;
import org.junit.Test;
import br.com.mbs.conteudoarquivo.GeradorLinhaArquivoConfiguracao;
import br.com.mbs.conteudoarquivo.GeradorLinhaArquivoConfiguracaoPadrao;
import br.com.mbs.conteudoarquivo.annotation.Campo;
import br.com.mbs.conteudoarquivo.validador.ValidadorCampoPadrao;

public class ValidadorCampoPadraoTest {

	
	GeradorLinhaArquivoConfiguracao configurador = new GeradorLinhaArquivoConfiguracaoPadrao();
	ValidadorCampoPadrao validadorEntidadePadrao = new ValidadorCampoPadrao(configurador);
	
	@Test
	public void testTamanhoTotalCampoFormatado() throws Exception {
		Teste1 objeto = new Teste1();		
		try{
			validadorEntidadePadrao.validaApos(retornaAnnotationCampo(objeto,"teste1"), "1234657");
		}catch(Exception e){
			Assert.fail("Noa poderia ter ocorrido falha neste teste");
		}
	}
	
	@Test(expected=Exception.class)
	public void testTamanhoTotalCampoFormatadoMaiorQueDefinido() throws Exception {
		Teste1 objeto = new Teste1();		
		validadorEntidadePadrao.validaApos(retornaAnnotationCampo(objeto,"teste1"), "12345");
	}
	
	@Test(expected=Exception.class)
	public void testposicaoRegistroIgualAZero() throws Exception {
		Teste3 objeto = new Teste3();		
		validadorEntidadePadrao.validaAntes(retornaAnnotationCampo(objeto,"teste1"), "12345");
	}
	
	@Test(expected=Exception.class)
	public void testValorDefaultNaoConfiguradoEObrigatorioValidaAntes() throws Exception {
		Teste4 objeto = new Teste4();		
		validadorEntidadePadrao.validaAntes(retornaAnnotationCampo(objeto,"teste1"), null);
	}

	
	@Test(expected=Exception.class)
	public void testValorDefaultConfiguradoEObrigatorioValidaAntes() throws Exception {
		Teste4 objeto = new Teste4();		
		validadorEntidadePadrao.validaAntes(retornaAnnotationCampo(objeto,"teste2"), null);
	}

	@Test(expected=Exception.class)
	public void testValorPosicaoRegistroMaiorTamanhoDefinidoConfiguracaoValidaAntes() throws Exception {
		Teste5 objeto = new Teste5();		
		validadorEntidadePadrao.validaAntes(retornaAnnotationCampo(objeto,"teste1"), null);
	}
	
	@Test(expected=Exception.class)
	public void testValorTamanhoInteiroMaiorTamanhoDefinidoConfiguracaoValidaAntes() throws Exception {
		Teste6 objeto = new Teste6();		
		validadorEntidadePadrao.validaAntes(retornaAnnotationCampo(objeto,"teste1"), null);
	}
	
	@Test(expected=Exception.class)
	public void testValorTamanhoDecimalMaiorTamanhoDefinidoConfiguracaoValidaAntes() throws Exception {
		Teste7 objeto = new Teste7();		
		validadorEntidadePadrao.validaAntes(retornaAnnotationCampo(objeto,"teste1"), null);
	}
	
	@Test(expected=Exception.class)
	public void testValorTamanhoSomaTamanhoInteiroComTamanhoDecimalMaiorTamanhoDefinidoConfiguracaoValidaAntes() throws Exception {
		Teste8 objeto = new Teste8();		
		validadorEntidadePadrao.validaAntes(retornaAnnotationCampo(objeto,"teste1"), null);
	}
	
	
	

	
	class Teste1 implements Serializable{
		
		@Campo(tamParteInteira=7,tamParteDecimal=0,posicaoRegistro=3,obrigatorio=true,valorDefault="REMESSA")
		private String teste1;
		
		@Campo(tamParteInteira=2,tamParteDecimal=2,posicaoRegistro=100,obrigatorio=true,valorDefault="ABCD")
		private String teste2;
		
		@Campo(tamParteInteira=2,tamParteDecimal=0,posicaoRegistro=100,obrigatorio=true,valorDefault="1")
		private String teste3;
		
		
		

		public String getTeste3() {
			return teste3;
		}

		public void setTeste3(String teste3) {
			this.teste3 = teste3;
		}

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
	
	class Teste2 implements Serializable{
		
		@Campo(tamParteInteira=400,tamParteDecimal=0,posicaoRegistro=3,obrigatorio=true,valorDefault="REMESSA")
		private String teste1;
		
		@Campo(tamParteInteira=10,tamParteDecimal=0,posicaoRegistro=100,obrigatorio=true,valorDefault="ABC")
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
	
	class Teste3 implements Serializable{
		
		@Campo(tamParteInteira=1,tamParteDecimal=1,posicaoRegistro=0,obrigatorio=true,valorDefault="REMESSA")
		private String teste1;
		
		public String getTeste1() {
			return teste1;
		}

		public void setTeste1(String teste1) {
			this.teste1 = teste1;
		}
				
	}
	
	class Teste4 implements Serializable{
		
		@Campo(tamParteInteira=1,tamParteDecimal=1,posicaoRegistro=1,obrigatorio=true,valorDefault="")
		private String teste1;
		
		@Campo(tamParteInteira=10,tamParteDecimal=1,posicaoRegistro=0,obrigatorio=true,valorDefault="n/a")
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
	
	class Teste5 implements Serializable{
		
		@Campo(tamParteInteira=1,tamParteDecimal=1,posicaoRegistro=401,obrigatorio=true,valorDefault="abc")
		private String teste1;
		public String getTeste1() {
			return teste1;
		}
		public void setTeste1(String teste1) {
			this.teste1 = teste1;
		}		
				
	}
	
class Teste6 implements Serializable{
		
		@Campo(tamParteInteira=401,tamParteDecimal=1,posicaoRegistro=10,obrigatorio=true,valorDefault="abc")
		private String teste1;
		public String getTeste1() {
			return teste1;
		}
		public void setTeste1(String teste1) {
			this.teste1 = teste1;
		}		
				
	}

class Teste7 implements Serializable{
	
	@Campo(tamParteInteira=4,tamParteDecimal=401,posicaoRegistro=10,obrigatorio=true,valorDefault="abc")
	private String teste1;
	public String getTeste1() {
		return teste1;
	}
	public void setTeste1(String teste1) {
		this.teste1 = teste1;
	}		
			
}

class Teste8 implements Serializable{
	
	@Campo(tamParteInteira=1,tamParteDecimal=400,posicaoRegistro=10,obrigatorio=true,valorDefault="abc")
	private String teste1;
	public String getTeste1() {
		return teste1;
	}
	public void setTeste1(String teste1) {
		this.teste1 = teste1;
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
