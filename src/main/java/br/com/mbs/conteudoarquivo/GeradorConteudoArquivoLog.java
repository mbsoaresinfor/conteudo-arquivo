package br.com.mbs.conteudoarquivo;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import br.com.mbs.conteudoarquivo.annotation.Campo;

class GeradorConteudoArquivoLog {

	private static final Logger LOGGER = Logger.getLogger(GeradorLinhaArquivo.class);
	
	public void inicioProcessamentoEntidade(Serializable entidade) {
	LOGGER.debug("\n#######################################################################\n" +
			"\tInicio Processamento entidade: " + entidade.getClass().getSimpleName()+"\n"+
			"#######################################################################");
	}
	
	public void fimProcessamentoEntidade(Serializable entidade) {
		LOGGER.debug("\n#######################################################################\n" +
				"\tFim Processamento entidade: " + entidade.getClass().getSimpleName()+"\n"+
				"#######################################################################");
	}
	
	public void logProcessamentoLinha(String mensagemInicial,Field field,StringBuffer linha){
		StringBuilder mensagem = new StringBuilder("\n# " + mensagemInicial + ":");
		mensagem.append(" Campo: ").append(field.getName());
		mensagem.append(" Tamanho Linha: ").append(linha.length());		
		LOGGER.debug(mensagem.toString());
	}
	
	public void logInformacoesDoValor(String valorOriginal,String valorFormatado,int indexInicio,int indexFim,Campo campo){
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("Valor Original: [").append(valorOriginal).append("]");
		mensagem.append("\n");
		mensagem.append("Valor Formatado: [").append(valorFormatado).append("]");
		mensagem.append("\n");
		mensagem.append("Index inicio: ").append(indexInicio);
		mensagem.append(" Index Fim: ").append(indexFim);
		mensagem.append("\n");
		mensagem.append("Informacoes da anotatation " + Campo.class.getSimpleName() + ":");		
		mensagem.append(" posicaoRegistro: ").append(campo.posicaoRegistro());
		mensagem.append(" tamParteInteira: ").append(campo.tamParteInteira());
		mensagem.append(" tamParteDecimal: ").append(campo.tamParteDecimal());
		mensagem.append(" obrigatorio: ").append(campo.obrigatorio());
		mensagem.append(" valorDefault: ").append(campo.valorDefault());
		mensagem.append(" formatadorValor: ").append(campo.formatadorValor());		
		LOGGER.debug(mensagem.toString());
	}
	
	public void printStackTrace(Exception e) {
		LOGGER.error(e);
	}
	
}
