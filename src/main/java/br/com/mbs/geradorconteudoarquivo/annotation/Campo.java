package br.com.mbs.geradorconteudoarquivo.annotation;

import java.lang.annotation.*;

import br.com.mbs.geradorconteudoarquivo.formatador.FormatadorValor;
import br.com.mbs.geradorconteudoarquivo.formatador.FormatadorValorNaoDefinido;

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
