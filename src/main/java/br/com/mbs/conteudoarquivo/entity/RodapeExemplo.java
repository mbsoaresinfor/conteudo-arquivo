package br.com.mbs.conteudoarquivo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import br.com.mbs.conteudoarquivo.annotation.Campo;

/**
 * Classe exemplo, que simula o rodape de um arquivo
 * 
 * @author marcelo.soares
 *
 */
public class RodapeExemplo implements Serializable {

    
    
	private static final long serialVersionUID = 3085993727236930162L;

	@Campo(tamParteInteira=20,tamParteDecimal=0,posicaoRegistro=1,obrigatorio=true,valorDefault="0")
    private BigDecimal valor1 = new BigDecimal("0");
    
    @Campo(tamParteInteira=30,tamParteDecimal=0,posicaoRegistro=21,obrigatorio=true,valorDefault="0")
    private BigDecimal valor2 ;

	public BigDecimal getValor1() {
		return valor1;
	}

	public void setValor1(BigDecimal valor1) {
		this.valor1 = valor1;
	}

	public BigDecimal getValor2() {
		return valor2;
	}

	public void setValor2(BigDecimal valor2) {
		this.valor2 = valor2;
	}
   
    
    

}
