package br.com.mbs.conteudoarquivo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

import javax.management.RuntimeErrorException;

import br.com.mbs.conteudoarquivo.annotation.Campo;
import br.com.mbs.conteudoarquivo.annotation.CampoHelper;
import br.com.mbs.conteudoarquivo.formatador.FormatadorValor;
import br.com.mbs.conteudoarquivo.formatador.FormatadorValorNaoDefinido;
import br.com.mbs.conteudoarquivo.validador.ValidadorCampo;
import br.com.mbs.conteudoarquivo.validador.ValidadorCampoPadrao;
import br.com.mbs.conteudoarquivo.validador.ValidadorEntidade;
import br.com.mbs.conteudoarquivo.validador.ValidadorEntidadePadrao;


public class GeradorLinhaArquivo {

	
	private CampoHelper campoHelper = new CampoHelper();	
	private GeradorLinhaArquivoConfiguracao geradorLinhaArquivoConfiguracao;
	private ValidadorCampo validadorCampoPadrao ;
	private ValidadorEntidade validadorEntidade ;
	
	
	public GeradorLinhaArquivo(){
		this(new GeradorLinhaArquivoConfiguracaoPadrao());
	}
	
	public GeradorLinhaArquivo(GeradorLinhaArquivoConfiguracao geradorLinhaArquivoConfiguracao){
		this.geradorLinhaArquivoConfiguracao = geradorLinhaArquivoConfiguracao;
		validadorCampoPadrao = new ValidadorCampoPadrao(geradorLinhaArquivoConfiguracao);
		validadorEntidade = new ValidadorEntidadePadrao(geradorLinhaArquivoConfiguracao);
	}
	
	
	public String criar(Serializable entidade)throws Exception{		
		
		validadorEntidade.valida(entidade);
		
		StringBuffer linha = criaLinha();
		
		for(Field field:  entidade.getClass().getDeclaredFields()){
			Campo annotation = field.getAnnotation(Campo.class);
			if(annotation != null){
				logProcessamentoLinha("iniciando ", field, linha);
				try {
					processa(linha,field,entidade);
				}catch(Exception e) {
					System.out.println("Error na entidade: " + entidade.getClass().getSimpleName() + "\n" + e.getMessage());
					throw e;
				}finally {
					logProcessamentoLinha("finalizado ", field, linha);
				}
								
			}			
		}		
		return linha.toString();		
	}
	
	private void logProcessamentoLinha(String mensagemInicial,Field field,StringBuffer linha){
		StringBuilder mensagem = new StringBuilder("# " + mensagemInicial + ":");
		mensagem.append(" Campo: ").append(field.getName());
		mensagem.append(" Tamanho Linha: ").append(linha.length());		
		System.out.println(mensagem.toString());
	}
	
	private void logInformacoesDoValor(String valorOriginal,String valorFormatado,int indexInicio,int indexFim,Campo campo){
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
		System.out.println(mensagem.toString());
	}
	
	private StringBuffer criaLinha(){
		int tam = geradorLinhaArquivoConfiguracao.getTotalCaracteresLinha();
		StringBuffer linha = new StringBuffer(tam);
		for(int i=0; i < tam ; i++){
			linha.append(geradorLinhaArquivoConfiguracao.getCharacterPreenchimentoLinha());
		}		
		return linha;
	}
	
	private void processa(StringBuffer linha,Field field,Serializable obj) throws Exception{
		
		field.setAccessible(true);
		
		
		if(eTipoAlfaNumerico(field)){

			String valor = campoHelper.retornaValorAlfaNumerico(field, obj);
			validadorCampoPadrao.validaAntes(field,valor);
			setaValorAlfaNumericoNaLinha(linha,field,valor);			
			
		}else if(eTipoNumerico(field)){
								
			BigDecimal valor = campoHelper.retornaValorNumerico(field, obj);			
			validadorCampoPadrao.validaAntes(field,valor.toPlainString());
			setaValorNumericoNaLinha(linha, field, valor);
			
		}else if(eTipoData(field)) {
			Date valor = campoHelper.retornaValorData(field, obj);
			validadorCampoPadrao.validaAntes(field,valor.toGMTString());
			setaValorDataNaLinha(linha,field,valor);
			
		}else{
			throw new IllegalArgumentException("Tipo de dado do campo [" + field.getName() + "] esta invalido. \n"+
					"Tipo de dados validos: [String para AlfhaNumerico, BigDecimal para Numericos, java.util.Date para Data]");
		}
	}
	
	@SuppressWarnings("unchecked")
	private void setaValorAlfaNumericoNaLinha(StringBuffer linha,Field field,String valor) throws Exception{
		
		String valorFormatador = "NULL";
		boolean temFormatador = campoHelper.getAnnotationCampo(field).formatadorValor() != FormatadorValorNaoDefinido.class ;
		if(temFormatador){
			FormatadorValor<String> formatador = criaInstanceFormatadorValor(campoHelper.getAnnotationCampo(field).formatadorValor());
			valorFormatador = formatador.formata(valor, campoHelper.getAnnotationCampo(field) );
		}else{
			valorFormatador = geradorLinhaArquivoConfiguracao.getFormatadorValorAlfaNumerico().
					formata(valor, campoHelper.getAnnotationCampo(field));			
		}
		
		validadorCampoPadrao.validaApos(field,valorFormatador);
				 
		int inicio 	= campoHelper.getPosicaoRegistro(campoHelper.getAnnotationCampo(field));
		int end = campoHelper.getIndexFinalParteInteira(campoHelper.getAnnotationCampo(field));
		logInformacoesDoValor(valor, valorFormatador, inicio, end,campoHelper.getAnnotationCampo(field));
		linha.replace(inicio,end, valorFormatador.toString());
	}
	
	@SuppressWarnings("unchecked")
	private void setaValorDataNaLinha(StringBuffer linha,Field field,Date valor) throws Exception{
		
		String valorFormatador = "NULL";
		boolean temFormatador = campoHelper.getAnnotationCampo(field).formatadorValor() != FormatadorValorNaoDefinido.class ;
		if(temFormatador){
			FormatadorValor<Date> formatador = criaInstanceFormatadorValor(campoHelper.getAnnotationCampo(field).formatadorValor());
			valorFormatador = formatador.formata(valor, campoHelper.getAnnotationCampo(field) );
		}else{
			valorFormatador = geradorLinhaArquivoConfiguracao.getFormatadorData().
					formata(valor, campoHelper.getAnnotationCampo(field));			
		}
		
		validadorCampoPadrao.validaApos(field,valorFormatador);
				 
		int inicio 	= campoHelper.getPosicaoRegistro(campoHelper.getAnnotationCampo(field));
		int end = campoHelper.getIndexFinalParteInteira(campoHelper.getAnnotationCampo(field));
		logInformacoesDoValor(valor.toGMTString(), valorFormatador, inicio, end,campoHelper.getAnnotationCampo(field));
		linha.replace(inicio,end, valorFormatador.toString());
	}
	
	
	@SuppressWarnings("unchecked")
	private void setaValorNumericoNaLinha(StringBuffer linha,Field field,BigDecimal valor) throws Exception{
		
		String valorFormatador = "NULL";
		boolean temFormatador = campoHelper.getAnnotationCampo(field).formatadorValor() != FormatadorValorNaoDefinido.class ;
		if(temFormatador){
			FormatadorValor<BigDecimal> formatador = criaInstanceFormatadorValor(campoHelper.getAnnotationCampo(field).formatadorValor());
			valorFormatador = formatador.formata(valor, campoHelper.getAnnotationCampo(field) );
		}else{
			valorFormatador = geradorLinhaArquivoConfiguracao.getFormatadorValorNumerico().formata(valor, campoHelper.getAnnotationCampo(field));			
		}
		
		validadorCampoPadrao.validaApos(field,valorFormatador);
				
		int inicio 	= campoHelper.getPosicaoRegistro(campoHelper.getAnnotationCampo(field));
		int end = campoHelper.getIndexFinalParteInteira(campoHelper.getAnnotationCampo(field)) + campoHelper.getAnnotationCampo(field).tamParteDecimal();
		logInformacoesDoValor(valor.toPlainString(), valorFormatador, inicio, end,campoHelper.getAnnotationCampo(field));
		linha.replace(inicio,end, valorFormatador.toString());		
	}
	
	
	
	@SuppressWarnings("unchecked")
	private FormatadorValor criaInstanceFormatadorValor(
			Class<? extends FormatadorValor<?>> formatador) {
		FormatadorValor<?> formatadorObjecto = null;
		try{
			formatadorObjecto = (FormatadorValor)formatador.newInstance();
		}catch(Exception e){
			throw new RuntimeErrorException(new Error(e),"Nao foi possivel criar o formatador: " + formatador.getName());			
		}
		return formatadorObjecto;
	}

	
	private boolean eTipoNumerico(Field field){
		return field.getType() == BigDecimal.class ? true : false;
	}
	
	private boolean eTipoAlfaNumerico(Field field){
		return field.getType() == String.class ? true : false;
	}
	
	private boolean eTipoData(Field field){
		return field.getType() == Date.class ? true : false;
	}
	
	
}
