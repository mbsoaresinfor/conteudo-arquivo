package br.com.mbs.conteudoarquivo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import br.com.mbs.conteudoarquivo.annotation.Campo;

/**
 * Classe exemplo, que simula o detalhe de um arquivo
 * 
 * @author marcelo.soares
 *
 */
public class DetalheExemplo2 implements Serializable {    
	
	
	
	private static final long serialVersionUID = -4101026709519789951L;

	@Campo(tamParteInteira=1,tamParteDecimal=0,posicaoRegistro=1,obrigatorio=true,valorDefault="0")
    private BigDecimal	valor1 = new BigDecimal("0");
    
    @Campo(tamParteInteira=2,tamParteDecimal=0,posicaoRegistro=2,obrigatorio=true,valorDefault="1")
    private BigDecimal	valor2 = new BigDecimal("1");
    
    @Campo(tamParteInteira=14,tamParteDecimal=0,posicaoRegistro=4,obrigatorio=true,valorDefault="2")
    private BigDecimal	valor3 = new BigDecimal("2");
    
    @Campo(tamParteInteira=32,tamParteDecimal=0,posicaoRegistro=18,obrigatorio=false,valorDefault="VALOR DEFAULT DA VARIAVEL")
    private String	valor4 ;
    
    
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

	public BigDecimal getValor3() {
		return valor3;
	}

	public void setValor3(BigDecimal valor3) {
		this.valor3 = valor3;
	}

	public String getValor4() {
		return valor4;
	}

	public void setValor4(String valor4) {
		this.valor4 = valor4;
	}

	
    
    
    
}
