package br.com.mbs.conteudoarquivo.validador;


import java.lang.reflect.Field;
import br.com.mbs.conteudoarquivo.GeradorLinhaArquivoConfiguracao;
import br.com.mbs.conteudoarquivo.annotation.Campo;
import br.com.mbs.conteudoarquivo.annotation.CampoHelper;

public class ValidadorCampoPadrao extends ValidadorCampo {

	private CampoHelper campoHelper = new CampoHelper();
	
	public ValidadorCampoPadrao(GeradorLinhaArquivoConfiguracao configuracao) {
		super(configuracao);
	}

	@Override
	public void validaAntes(Field field, String campoValorOriginal)
			throws Exception {
		Campo campo = campoHelper.getAnnotationCampo(field);
		if(campo.posicaoRegistro() < 1){
			throw new Exception("O campo " + field.getName() + " esta com sua posicao de registro menor que 1. Somente valores validos >=  1");
		}
		
		boolean campoObritatorioEstaNull = campo.obrigatorio() && ( campoValorOriginal == null || "".equals(campoValorOriginal)) && "".equals(campo.valorDefault());
		if(campoObritatorioEstaNull){
			throw new Exception("O campo " + field.getName() + " esta NULL ou BRANCO, e ele esta definido como obrigatorio.");
		}
		
		if(campo.posicaoRegistro() > configuracao.getTotalCaracteresLinha()) {
			throw new Exception("O campo " + field.getName() + " possui sua posicao de registro maior que o definido na configuracao "+configuracao.getTotalCaracteresLinha());
		}
		
		if(campo.tamParteDecimal() > configuracao.getTotalCaracteresLinha()) {
			throw new Exception("O campo " + field.getName() + " possui seu tamanho da parte decimal maior que o definido na configuracao "+configuracao.getTotalCaracteresLinha());
		}
		
		if(campo.tamParteInteira() > configuracao.getTotalCaracteresLinha()) {
			throw new Exception("O campo " + field.getName() + " possui seu tamanho da parte inteira maior que o definido na configuracao "+configuracao.getTotalCaracteresLinha());
		}
		
		int totalParteInteiraMaisParteDecimal = campo.tamParteInteira() + campo.tamParteDecimal(); 
		if(totalParteInteiraMaisParteDecimal > configuracao.getTotalCaracteresLinha()) {
			throw new Exception("O campo " + field.getName() + " esta com a soma do seu tamanho da parte inteira mais o tamanho decimal, maior que o definido na configuracao "+configuracao.getTotalCaracteresLinha());
		}
		
	}

	@Override
	public void validaApos(Field field, String campoFormatado) throws Exception {
		
		Campo campo = campoHelper.getAnnotationCampo(field);
		int tamanhoCampoDefinido = campo.tamParteInteira() + campo.tamParteDecimal();
		int tamanhoCampoFormatado =campoFormatado.length();
		if(tamanhoCampoDefinido != tamanhoCampoFormatado){
			throw new Exception("O Campo formatado " + field.getName() + " esta com o tamanho maior que o definido ["+tamanhoCampoDefinido+"]" + ". Valor do campo: "+ campoFormatado);
		}
		
	}
	
	 

	

}
