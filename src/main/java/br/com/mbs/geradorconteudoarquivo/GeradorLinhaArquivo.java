package br.com.mbs.geradorconteudoarquivo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import javax.management.RuntimeErrorException;
import br.com.mbs.geradorconteudoarquivo.annotation.Campo;
import br.com.mbs.geradorconteudoarquivo.annotation.CampoHelper;
import br.com.mbs.geradorconteudoarquivo.formatador.FormatadorValor;
import br.com.mbs.geradorconteudoarquivo.formatador.FormatadorValorNaoDefinido;
import br.com.mbs.geradorconteudoarquivo.validador.ValidadorCampo;
import br.com.mbs.geradorconteudoarquivo.validador.ValidadorCampoPadrao;
import br.com.mbs.geradorconteudoarquivo.validador.ValidadorEntidade;
import br.com.mbs.geradorconteudoarquivo.validador.ValidadorEntidadePadrao;

/**
 * Essa classe é a responsavel por fazer o parser de um objeto e gerar uma linha, contendo uma string
 * 
 * @author marcelo.soares
 *
 */
public class GeradorLinhaArquivo {

	
	private GeradorArquivoLog log = new GeradorArquivoLog();
	private CampoHelper campoHelper = new CampoHelper();	
	private GeradorLinhaArquivoConfiguracao geradorLinhaArquivoConfiguracao;
	private ValidadorCampo validadorCampoPadrao ;
	private ValidadorEntidade validadorEntidade;
	
	/**
	 * Cria o GeradorLinhaArquivo, com as configuracoes padroes
	 */
	public GeradorLinhaArquivo(){
		this(new GeradorLinhaArquivoConfiguracaoPadrao());
	}
	
	/**
	 * Cria um GeradorLinhaArquivo
	 * @param geradorLinhaArquivoConfiguracao Seta um GeradorLinhaArquivoConfiguracao
	 */
	public GeradorLinhaArquivo(GeradorLinhaArquivoConfiguracao geradorLinhaArquivoConfiguracao){
		this.geradorLinhaArquivoConfiguracao = geradorLinhaArquivoConfiguracao;
		validadorCampoPadrao = new ValidadorCampoPadrao(geradorLinhaArquivoConfiguracao);
		validadorEntidade = new ValidadorEntidadePadrao(geradorLinhaArquivoConfiguracao);		
	}
	
	/**
	 * Cria uma linha, baseado no conteudo de uma entidade
	 * @param entidade Entidade que vai ser processada
	 * @return Uma linha, contendo o conteudo da entidade
	 * @throws Exception Qualquer erro no parser da entidade.
	 */
	public String criar(Serializable entidade)throws Exception{		
		
		StringBuffer linha = null;
		
		try {
			
			log.inicioProcessamentoEntidade(entidade);			
			validadorEntidade.valida(entidade);			
			linha = criaLinha();			
			processaLinhas(entidade, linha);			
			log.fimProcessamentoEntidade(entidade);
			
		}catch(Exception e) {
			log.printStackTrace(e);
			throw e;
		}
		
		return linha.toString();		
	}

	private void processaLinhas(Serializable entidade, StringBuffer linha) throws Exception {
		for(Field field:  entidade.getClass().getDeclaredFields()){
			Campo annotation = field.getAnnotation(Campo.class);
			if(annotation != null){
				try{
					log.logProcessamentoLinha("INICIANDO ", field, linha);
					processaLinha(linha,field,entidade);
					log.logProcessamentoLinha("FINALIZADO ", field, linha);
					
				}catch(Exception e){
					log.printStackTrace(e);
					geraExcessaoErrorProcessamentoEntidade(entidade, field, e);
				}
			}			
		}
	}

	private void geraExcessaoErrorProcessamentoEntidade(Serializable entidade, Field field, Exception e)
			throws Exception {
		String messageError = "Error na entidade "+ entidade.getClass().getSimpleName() + " Campo: " + field.getName()  
				+ "\n"+ e.getMessage();
		throw new Exception(messageError,e);
	}
	
	
	private StringBuffer criaLinha(){
		int tam = geradorLinhaArquivoConfiguracao.getTotalCaracteresLinha();
		StringBuffer linha = new StringBuffer(tam);
		for(int i=0; i < tam ; i++){
			linha.append(geradorLinhaArquivoConfiguracao.getCharacterPreenchimentoLinha());
		}		
		return linha;
	}
	
	private void processaLinha(StringBuffer linha,Field field,Serializable obj) throws Exception{
		
		field.setAccessible(true);
		
		if(eTipoAlfaNumerico(field)){

			String valor = campoHelper.retornaValorAlfaNumerico(field, obj);
			validadorCampoPadrao.validaAntes(field,valor);
			setaValorAlfaNumericoNaLinha(linha,field,valor);			
			
		}else if(eTipoNumerico(field)){
								
			BigDecimal valor = campoHelper.retornaValorNumerico(field, obj);			
			validadorCampoPadrao.validaAntes(field,valor.toPlainString());
			setaValorNumericoNaLinha(linha, field, valor);
			
		}else{
			throw new IllegalArgumentException("Tipo de dado do campo [" + field.getName() + "] esta invalido. \n"+
					"Tipo de dados validos: [String para AlfhaNumerico e BigDecimal para Numericos]");
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
		int fim = campoHelper.getIndexFinalParteInteira(campoHelper.getAnnotationCampo(field));		
		log.logInformacoesDoValor(valor, valorFormatador, inicio, fim,campoHelper.getAnnotationCampo(field));
		linha.replace(inicio,fim, valorFormatador.toString());
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
		int fim = campoHelper.getIndexFinalParteInteira(campoHelper.getAnnotationCampo(field)) + campoHelper.getAnnotationCampo(field).tamParteDecimal();		
		log.logInformacoesDoValor(valor.toPlainString(), valorFormatador, inicio, fim,campoHelper.getAnnotationCampo(field));
		linha.replace(inicio,fim, valorFormatador.toString());		
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
	
	
}
