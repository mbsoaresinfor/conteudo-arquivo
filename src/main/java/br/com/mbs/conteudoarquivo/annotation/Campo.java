package br.com.mbs.conteudoarquivo.annotation;

import java.lang.annotation.*;

import br.com.mbs.conteudoarquivo.formatador.FormatadorValor;
import br.com.mbs.conteudoarquivo.formatador.FormatadorValorNaoDefinido; 

@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.FIELD)  
public @interface Campo {

	int tamParteInteira();
	int tamParteDecimal();
	int posicaoRegistro();
	boolean obrigatorio() ;
	String valorDefault();
	Class<? extends FormatadorValor<?>> formatadorValor() default FormatadorValorNaoDefinido.class;
	
}
