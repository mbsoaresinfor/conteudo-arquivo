package br.com.mbs.conteudoarquivo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CriadorConteudoArquivo {


	private CriadorConteudoArquivo(){}
	
	public static class Criador{
	
		private GeradorLinhaArquivo geradorLinhaArquivo;
		private Serializable cabecalho;
		private List<Serializable> detalhes = new ArrayList<Serializable>();
		private Serializable rodape;
		
		public Criador(){
			this(new GeradorLinhaArquivo());
		}
		
		public Criador(GeradorLinhaArquivo geradorLinhaArquivo){
			this.geradorLinhaArquivo = geradorLinhaArquivo;
		}
		
		public Criador adicionaCabecalho(Serializable cabecalho){
			this.cabecalho= cabecalho;
			return this;
		}
		
		public Criador adicionaDetalhes(Serializable linha){
			detalhes.add(linha);
			return this;
		}
		
		public Criador adicionaDetalhes(List<? extends Serializable > linha){
			detalhes.addAll(linha);
			return this;
		}
		
		public Criador adicionaRodape(Serializable rodape){
			this.rodape= rodape;
			return this;
		}
		
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
				throw new IllegalArgumentException("Defina o cabecalho, detalhe(s) e o rodape para geracao do conteudo do arquivo");
			}
		}
		
		
	}
	
}
