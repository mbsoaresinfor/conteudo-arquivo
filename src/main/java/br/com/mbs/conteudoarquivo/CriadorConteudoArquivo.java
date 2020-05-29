package br.com.mbs.conteudoarquivo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CriadorConteudoArquivo {


	private CriadorConteudoArquivo(){}
	
	public static class CriadorSimples{
		
		private GeradorLinhaArquivo geradorLinhaArquivo;
		private List<Serializable> linhas = new ArrayList<Serializable>();		
		
		/**
		 * Usado quando deseja criar um arquivo com conteudo baseado na 
		 * adicao de objetos. Cada objeto, representa uma linha, onde o resutaldo final do arquivo
		 * e a compilacao de todos esses arquivos adicionados, na ordem da adicao.
		 */
		public CriadorSimples(){
			this(new GeradorLinhaArquivo());
		}
		
		/**
		 * Usado quando deseja criar um arquivo com conteudo baseado na 
		 * adicao de objetos. Cada objeto, representa uma linha, onde o resutaldo final do arquivo
		 * e a compilacao de todos esses arquivos adicionados, na ordem da adicao.
		 * 
		 * @param geradorLinhaArquivo Seta um GeradorLinhaArquivo 
		 */
		public CriadorSimples(GeradorLinhaArquivo geradorLinhaArquivo){
			this.geradorLinhaArquivo = geradorLinhaArquivo;
		}
		
		public CriadorSimples adiciona(Serializable objeto){
			this.linhas.add(objeto);
			return this;
		}
		
		/**
		 * Cria o conteudo do arquivo
		 * @return o conteudo completo do arquivo
		 * @throws Exception Qualquer erro que ocorra na geracao do arquivo.
		 */
		public String criaConteudo() throws Exception{
			
			StringBuilder conteudo = new StringBuilder();			
			for(Serializable detalhe: linhas){
				conteudo.append(geradorLinhaArquivo.criar(detalhe));
				conteudo.append("\n");
			}
			
			return conteudo.toString();			
		}	
		
	}
		

	
	public static class Criador{
	
		private GeradorLinhaArquivo geradorLinhaArquivo;
		private Serializable cabecalho;
		private List<Serializable> detalhes = new ArrayList<Serializable>();
		private Serializable rodape;
		
		/**
		 * Usado quando deseja criar um arquivo com conteudo baseado na 
		 * estrutura (CabecalhoExemplo, detalhes e rodape). O datalhe é o corpo do arquivo,
		 * onde e possivel inserir diversas linhas.
		 */
		public Criador(){
			this(new GeradorLinhaArquivo());
		}
		
		/**
		 * Usado quando deseja criar um arquivo com conteudo baseado na 
		 * estrutura (CabecalhoExemplo, detalhes e rodape). O datalhe é o corpo do arquivo,
		 * onde e possivel inserir diversas linhas.
		 * 
		 * @param geradorLinhaArquivo Seta um GeradorLinhaArquivo 
		 */
		public Criador(GeradorLinhaArquivo geradorLinhaArquivo){
			this.geradorLinhaArquivo = geradorLinhaArquivo;
		}
		
		public Criador adicionaCabecalho(Serializable cabecalho){
			this.cabecalho= cabecalho;
			return this;
		}
		
		public Criador adicionaDetalhes(Serializable detalhe){
			detalhes.add(detalhe);
			return this;
		}
		
		public Criador adicionaDetalhes(List<? extends Serializable > detalhes){
			this.detalhes.addAll(detalhes);
			return this;
		}
		
		public Criador adicionaRodape(Serializable rodape){
			this.rodape= rodape;
			return this;
		}
		
		/**
		 * Cria o conteudo do arquivo
		 * @return o conteudo completo do arquivo
		 * @throws Exception Qualquer erro que ocorra na geracao do arquivo.
		 */
		public String criaConteudo() throws Exception{
			validaPartesArquivo();
			StringBuilder conteudo = new StringBuilder();
			conteudo.append(geradorLinhaArquivo.criar(cabecalho));
			conteudo.append("\n");
			for(Serializable detalhe: detalhes){
				conteudo.append(geradorLinhaArquivo.criar(detalhe));
				conteudo.append("\n");
			}
			conteudo.append(geradorLinhaArquivo.criar(rodape));
			
			return conteudo.toString();
			
		}

		private void validaPartesArquivo() {
			if(cabecalho == null || rodape == null || detalhes.isEmpty()){
				throw new IllegalArgumentException("Defina o CabecalhoExemplo, detalhe(s) e o rodape para geracao do conteudo do arquivo");
			}
		}
		
		
	}
	
}
