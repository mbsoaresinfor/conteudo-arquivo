package br.com.mbs.geradorconteudoarquivo.annotation;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;


public class CampoHelper {
	
	public BigDecimal  retornaValorNumerico(Field field,Serializable obj) {
		BigDecimal retorno = null;
		try{
			field.setAccessible(true);
			Campo annotation = field.getAnnotation(Campo.class);
			retorno = (BigDecimal) field.get(obj) != null ? (BigDecimal) field.get(obj) : new BigDecimal(annotation.valorDefault());			
		}catch(Exception e){
			throw new RuntimeException("N�o foi possivel retornar o valor em BigDecimal para o campo " + field.getName() + " da classe " + obj.getClass().getName());
		}
		return retorno;
	}
	
	public String  retornaValorAlfaNumerico(Field field,Serializable obj) throws IllegalArgumentException, IllegalAccessException{
		String retorno = null;
		try{
			field.setAccessible(true);
			Campo annotation = field.getAnnotation(Campo.class);
			retorno =  (String) field.get(obj) != null ? (String) field.get(obj) :annotation.valorDefault();
		}catch(Exception e){
			throw new RuntimeException("N�o foi possivel retornar o valor em String para o campo " + field.getName()+ " da classe " + obj.getClass().getName());
		}
		return retorno;
	}
	
	
	public Integer getIndexFinalParteInteira(Campo annotation){
		return getPosicaoRegistro(annotation) + ( annotation.tamParteInteira())  ;	
	}
	
	/**
	 * O index de uma String comeca em 0, mas na annotation inicia em 1 <P>  
	 * @param annotation Campo
	 * @return P=(posicaoRegistro -1)
	 */
	public Integer getPosicaoRegistro(Campo annotation){
		 return annotation.posicaoRegistro()-1;
	}
	
	public Campo getAnnotationCampo(Field field){
		return  field.getAnnotation(Campo.class);
	}
	
	public boolean temAnnotationCampo(Field field) {
		return getAnnotationCampo(field) == null ? false : true;
	}
}
