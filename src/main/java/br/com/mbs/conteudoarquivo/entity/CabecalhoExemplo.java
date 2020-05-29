package br.com.mbs.conteudoarquivo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import br.com.mbs.conteudoarquivo.annotation.Campo;

/**
 * Classe exemplo, que simula o cabecalho de um arquivo
 * 
 * @author marcelo.soares
 *
 */
public class CabecalhoExemplo implements Serializable {
    

	private static final long serialVersionUID = 5157498171136544391L;

	@Campo(tamParteInteira=1,tamParteDecimal=0,posicaoRegistro=1,obrigatorio=true,valorDefault="0")
    private BigDecimal campo1 ;
    
    @Campo(tamParteInteira=1,tamParteDecimal=0,posicaoRegistro=2,obrigatorio=true,valorDefault="1")
    private BigDecimal campo2 ;
    
    @Campo(tamParteInteira=7,tamParteDecimal=0,posicaoRegistro=3,obrigatorio=true,valorDefault="")
    private String campo3 ;
    
    @Campo(tamParteInteira=38,tamParteDecimal=2,posicaoRegistro=10,obrigatorio=true,valorDefault="2")
    private BigDecimal campo4 ;

	public BigDecimal getCampo1() {
		return campo1;
	}

	public void setCampo1(BigDecimal campo1) {
		this.campo1 = campo1;
	}

	public BigDecimal getCampo2() {
		return campo2;
	}

	public void setCampo2(BigDecimal campo2) {
		this.campo2 = campo2;
	}

	public String getCampo3() {
		return campo3;
	}

	public void setCampo3(String campo3) {
		this.campo3 = campo3;
	}

	public BigDecimal getCampo4() {
		return campo4;
	}

	public void setCampo4(BigDecimal campo4) {
		this.campo4 = campo4;
	}
    
    
    
    
    
  
}
