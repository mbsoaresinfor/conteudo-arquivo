package br.com.mbs.geradorconteudoarquivo;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.com.mbs.geradorconteudoarquivo.CriadorConteudoArquivo;
import br.com.mbs.geradorconteudoarquivo.GeradorLinhaArquivo;
import br.com.mbs.geradorconteudoarquivo.annotation.Campo;

public class CriadorConteudoArquivoTest {

	
	
	@Test
	public void testCriador() throws Exception {

		 Cabecalho cabecalho =  new Cabecalho();
		 cabecalho.setValor("a");
		 
		 Detalhe detalhe = new Detalhe();
		 detalhe.setValor("a");
		 
		 Trailer trailer = new Trailer();
		 trailer.setValor("a");
		 
		 GeradorLinhaArquivoConfiguracaoTest config = new GeradorLinhaArquivoConfiguracaoTest();
		 GeradorLinhaArquivo gerador = new GeradorLinhaArquivo(config);
		 
		String conteudoArquivo = new CriadorConteudoArquivo.Criador(gerador).
				adicionaCabecalho(cabecalho).
				adicionaDetalhes(detalhe).
				adicionaRodape(trailer).
				criaConteudo();
		
		int totalPartesArquivo = 3;
		int totalCaracteresPorLinhaCNAB400 = 400;
		int quantidadeNovasLinhas = totalPartesArquivo -1;
		int experado = totalPartesArquivo * totalCaracteresPorLinhaCNAB400 + quantidadeNovasLinhas;
		Assert.assertEquals(experado, conteudoArquivo.length());
		System.out.println(conteudoArquivo);
		
	}
	
	@Test
	public void testCriadorComVariosDetalhes() throws Exception {

		 Cabecalho cabecalho =  new Cabecalho();
		 cabecalho.setValor("a");
		 
		 Detalhe detalhe1 = new Detalhe();
		 detalhe1.setValor("a");
		 
		 Detalhe detalhe2 = new Detalhe();
		 detalhe2.setValor("a");
		 
		 List<Serializable> listaDetalhes = new ArrayList<Serializable>();
		 listaDetalhes.add(detalhe1);
		 listaDetalhes.add(detalhe2);
		 
		 Trailer trailer = new Trailer();
		 trailer.setValor("a");
		 
		 GeradorLinhaArquivoConfiguracaoTest config = new GeradorLinhaArquivoConfiguracaoTest();
		 GeradorLinhaArquivo gerador = new GeradorLinhaArquivo(config);
		 
		String conteudoArquivo = new CriadorConteudoArquivo.Criador(gerador).
				adicionaCabecalho(cabecalho).
				adicionaDetalhes(listaDetalhes).
				adicionaRodape(trailer).
				criaConteudo();
		
		int totalPartesArquivo = 4;
		int totalCaracteresPorLinhaCNAB400 = 400;
		int quantidadeNovasLinhas = totalPartesArquivo -1;
		int experado = totalPartesArquivo * totalCaracteresPorLinhaCNAB400 + quantidadeNovasLinhas;
		Assert.assertEquals(experado, conteudoArquivo.length());
		System.out.println(conteudoArquivo);
		
	}
	
	
	class Cabecalho implements Serializable{
		
		@Campo(tamParteInteira=10,tamParteDecimal=0,posicaoRegistro=1,obrigatorio=true,valorDefault="CabecalhoExemplo")
		private String valor;

		public String getValor() {
			return valor;
		}

		public void setValor(String valor) {
			this.valor = valor;
		}		
		
	}
	
	class Detalhe implements Serializable{
		
		@Campo(tamParteInteira=10,tamParteDecimal=0,posicaoRegistro=1,obrigatorio=true,valorDefault="detalhe")
		private String valor;

		public String getValor() {
			return valor;
		}

		public void setValor(String valor) {
			this.valor = valor;
		}		
		
	}

	class Trailer implements Serializable{
	
	@Campo(tamParteInteira=10,tamParteDecimal=0,posicaoRegistro=1,obrigatorio=true,valorDefault="trailer")
	private String valor;

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}		
	
}

}
