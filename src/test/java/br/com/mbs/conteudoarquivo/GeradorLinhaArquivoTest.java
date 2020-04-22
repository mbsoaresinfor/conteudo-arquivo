package br.com.mbs.conteudoarquivo;

import static org.junit.Assert.*;
import java.io.Serializable;
import java.math.BigDecimal;
import junit.framework.Assert;
import org.junit.Test;

import br.com.mbs.conteudoarquivo.annotation.Campo;


public class GeradorLinhaArquivoTest {

	private GeradorLinhaArquivo geradorLinhaArquivo = new GeradorLinhaArquivo();

	
	@Test
	public void testCriacaoFormatadorValor() throws Exception {
		Teste6 objeto = new Teste6();
		String retorno = geradorLinhaArquivo.criar(objeto);
		Assert.assertNotNull(retorno);
	}
	
	@Test
	public void testSetValoresDefault() throws Exception {
		
		Teste5 objeto = new Teste5();	
				
		String retorno = geradorLinhaArquivo.criar(objeto);
		String valorTeste1NoIndex = retorno.substring(0, 7);
		String valorTeste2NoIndex = retorno.substring(9, 14);
		
		Assert.assertTrue(valorTeste1NoIndex.contains("TESTE"));
		Assert.assertTrue(valorTeste2NoIndex.contains("15"));
		Assert.assertTrue(retorno.length()  == 400);
	}
		
	
	@Test
	public void testGeradorLinhaArquivo1() throws Exception {
		String valorTeste1 = "REMESSA";
		Teste objeto = new Teste();		
		objeto.setTeste1(valorTeste1);		
		String retorno = geradorLinhaArquivo.criar(objeto);
		String valorTeste1NoIndex = retorno.substring(2, 9);
		System.out.println(retorno);
		System.out.println(retorno.length());
		
		Assert.assertTrue(valorTeste1.equals(valorTeste1NoIndex));
		Assert.assertTrue(retorno.length()  == 400);		
	}	

	@Test
	public void testGeradorLinhaArquivo2() throws Exception {
		String valorTeste1 = "MARIA";
		String valorTeste2 = "PEDRO";
		Teste2 objeto = new Teste2();		
		objeto.setTeste1(valorTeste1);	
		objeto.setTeste2(valorTeste2);
		String retorno = geradorLinhaArquivo.criar(objeto);
		String valorTeste1NoIndex = retorno.substring(2, 9);
		String valorTeste2NoIndex = retorno.substring(99, 109);
		System.out.println(retorno);
		System.out.println(retorno.length());
		
		String valorTeste1Experado = valorTeste1.concat("  ");
		String valorTeste2Experado = valorTeste2.concat("     ");
		Assert.assertTrue(valorTeste1NoIndex.equals(valorTeste1Experado));
		Assert.assertTrue(valorTeste2NoIndex.equals(valorTeste2Experado));
		Assert.assertTrue(retorno.length()  == 400);
		
	}
	
	@Test
	public void testGeradorLinhaArquivo3() throws Exception {
		String valorTeste1 = "MARIA";
		String valorTeste2 = "PEDRO";
		String valorTeste3 = "I";
		Teste3 objeto = new Teste3();		
		objeto.setTeste1(valorTeste1);	
		objeto.setTeste2(valorTeste2);
		objeto.setTeste3(valorTeste3);
		String retorno = geradorLinhaArquivo.criar(objeto);
		String valorTeste1NoIndex = retorno.substring(2, 9);
		String valorTeste2NoIndex = retorno.substring(99, 109);
		String valorTeste3NoIndex = retorno.substring(0, 2);
		System.out.println(retorno);
		System.out.println(retorno.length());
		
		String valorTeste1Experado = valorTeste1.concat("  ");
		String valorTeste2Experado = valorTeste2.concat("     ");
		String valorTeste3Experado = valorTeste3.concat(" ");
		Assert.assertTrue(valorTeste1NoIndex.equals(valorTeste1Experado));
		Assert.assertTrue(valorTeste2NoIndex.equals(valorTeste2Experado));
		Assert.assertTrue(valorTeste3NoIndex.equals(valorTeste3Experado));
		Assert.assertTrue(retorno.length()  == 400);		
	}
	
	@Test
	public void testGeradorLinhaArquivo4() throws Exception {
		String valorTeste1 = "MARIA";
		String valorTeste2 = "PEDRO";
		String valorTeste3 = "I";
		BigDecimal valorTeste4 = new BigDecimal("15");
		BigDecimal valorTeste5 = new BigDecimal("12");
		
		Teste3 objeto = new Teste3();		
		objeto.setTeste1(valorTeste1);	
		objeto.setTeste2(valorTeste2);
		objeto.setTeste3(valorTeste3);
		objeto.setTeste4(valorTeste4);
		objeto.setTeste5(valorTeste5);
		String retorno = geradorLinhaArquivo.criar(objeto);
		String valorTeste1NoIndex = retorno.substring(2, 9);
		String valorTeste2NoIndex = retorno.substring(99, 109);
		String valorTeste3NoIndex = retorno.substring(0, 2);
		String valorTeste4NoIndex = retorno.substring(14, 16); 
		String valorTeste5NoIndex = retorno.substring(16, 19);
		System.out.println(retorno);
		System.out.println(retorno.length());
		
		String valorTeste1Experado = valorTeste1.concat("  ");
		String valorTeste2Experado = valorTeste2.concat("     ");
		String valorTeste3Experado = valorTeste3.concat(" ");
		String valorTeste4Experado = "15";
		String valorTeste5Experado = "012";
		Assert.assertTrue(valorTeste1NoIndex.equals(valorTeste1Experado));
		Assert.assertTrue(valorTeste2NoIndex.equals(valorTeste2Experado));
		Assert.assertTrue(valorTeste3NoIndex.equals(valorTeste3Experado));
		Assert.assertTrue(valorTeste4NoIndex.equals(valorTeste4Experado));
		Assert.assertTrue(valorTeste5NoIndex.equals(valorTeste5Experado));
		Assert.assertTrue(retorno.length()  == 400);		
	}
	
	@Test
	public void testGeradorLinhaArquivo5() throws Exception {
		String valorTeste1 = "MARIA";		
		BigDecimal valorTeste2 = new BigDecimal("15.14");
		BigDecimal valorTeste3 = new BigDecimal("12.11");
		String valorTeste4 = "PEDRO";
		
		Teste4 objeto = new Teste4();		
		objeto.setTeste1(valorTeste1);	
		objeto.setTeste2(valorTeste2);
		objeto.setTeste3(valorTeste3);
		objeto.setTeste4(valorTeste4);
		
		String retorno = geradorLinhaArquivo.criar(objeto);
		String valorTeste1NoIndex = retorno.substring(0, 100);
		String valorTeste2NoIndex = retorno.substring(100, 200);
		String valorTeste3NoIndex = retorno.substring(200, 300);
		String valorTeste4NoIndex = retorno.substring(300, 400); 
		
		System.out.println(retorno);
		System.out.println(retorno.length());
		
		String valorTeste1Experado = "MARIA                                                                                               ";
		String valorTeste2Experado = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001514";
		String valorTeste3Experado = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000012110";
		String valorTeste4Experado = "PEDRO                                                                                               ";

		Assert.assertTrue(valorTeste1NoIndex.equals(valorTeste1Experado));
		Assert.assertTrue(valorTeste2NoIndex.equals(valorTeste2Experado));
		Assert.assertTrue(valorTeste3NoIndex.equals(valorTeste3Experado));
		Assert.assertTrue(valorTeste4NoIndex.equals(valorTeste4Experado));

		Assert.assertTrue(retorno.length()  == 400);		
	}	
	
	class Teste implements Serializable{
		@Campo(tamParteInteira=7,tamParteDecimal=0,posicaoRegistro=3,obrigatorio=true,valorDefault="REMESSA")
		private String teste1;

		public String getTeste1() {
			return teste1;
		}

		public void setTeste1(String teste1) {
			this.teste1 = teste1;
		}
	}
	
	class Teste2 implements Serializable{
		
		@Campo(tamParteInteira=7,tamParteDecimal=0,posicaoRegistro=3,obrigatorio=true,valorDefault="REMESSA")
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
		
		@Campo(tamParteInteira=7,tamParteDecimal=0,posicaoRegistro=3,obrigatorio=true,valorDefault="REMESSA")
		private String teste1;
		
		@Campo(tamParteInteira=10,tamParteDecimal=0,posicaoRegistro=100,obrigatorio=true,valorDefault="ABC")
		private String teste2;
		
		@Campo(tamParteInteira=2,tamParteDecimal=0,posicaoRegistro=1,obrigatorio=true,valorDefault="YZ")
		private String teste3;
		
		@Campo(tamParteInteira=2,tamParteDecimal=0,posicaoRegistro=15,obrigatorio=true,valorDefault="15")
		private BigDecimal teste4;
		
		@Campo(tamParteInteira=3,tamParteDecimal=0,posicaoRegistro=17,obrigatorio=true,valorDefault="20")
		private BigDecimal teste5;		
		
		public BigDecimal getTeste5() {
			return teste5;
		}

		public void setTeste5(BigDecimal teste5) {
			this.teste5 = teste5;
		}

		public BigDecimal getTeste4() {
			return teste4;
		}

		public void setTeste4(BigDecimal teste4) {
			this.teste4 = teste4;
		}

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
	
	class Teste4 implements Serializable{
		
		@Campo(tamParteInteira=100,tamParteDecimal=0,posicaoRegistro=1,obrigatorio=true,valorDefault="")
		private String teste1;
		
		@Campo(tamParteInteira=98,tamParteDecimal=2,posicaoRegistro=101,obrigatorio=true,valorDefault="0")
		private BigDecimal teste2;
		
		@Campo(tamParteInteira=97,tamParteDecimal=3,posicaoRegistro=201,obrigatorio=true,valorDefault="")
		private BigDecimal teste3;
		
		@Campo(tamParteInteira=100,tamParteDecimal=0,posicaoRegistro=301,obrigatorio=true,valorDefault="")
		private String teste4;

		public String getTeste1() {
			return teste1;
		}

		public void setTeste1(String teste1) {
			this.teste1 = teste1;
		}

		public BigDecimal getTeste2() {
			return teste2;
		}

		public void setTeste2(BigDecimal teste2) {
			this.teste2 = teste2;
		}

		public BigDecimal getTeste3() {
			return teste3;
		}

		public void setTeste3(BigDecimal teste3) {
			this.teste3 = teste3;
		}

		public String getTeste4() {
			return teste4;
		}

		public void setTeste4(String teste4) {
			this.teste4 = teste4;
		}		
	}

	class Teste5 implements Serializable{
		@Campo(tamParteInteira=7,tamParteDecimal=0,posicaoRegistro=1,obrigatorio=true,valorDefault="TESTE")
		private String teste1;
		
		@Campo(tamParteInteira=5,tamParteDecimal=0,posicaoRegistro=10,obrigatorio=true,valorDefault="15")
		private BigDecimal teste2;
				
		public BigDecimal getTeste2() {
			return teste2;
		}

		public void setTeste2(BigDecimal teste2) {
			this.teste2 = teste2;
		}

		public String getTeste1() {
			return teste1;
		}

		public void setTeste1(String teste1) {
			this.teste1 = teste1;
		}
	}
	class Teste6 implements Serializable{
		@Campo(tamParteInteira=7,tamParteDecimal=0,posicaoRegistro=1,obrigatorio=true,valorDefault="TESTE" ,formatadorValor=FormatadorValorMock.class)
		private String teste1;

		public String getTeste1() {
			return teste1;
		}

		public void setTeste1(String teste1) {
			this.teste1 = teste1;
		}
		
	}
		
	
}
