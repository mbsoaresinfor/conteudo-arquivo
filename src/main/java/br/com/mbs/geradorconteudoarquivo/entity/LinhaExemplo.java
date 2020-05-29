package br.com.mbs.geradorconteudoarquivo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.mbs.geradorconteudoarquivo.annotation.Campo;

/**
 * Classe exemplo, que simula uma linha de um arquivo
 * 
 * @author marcelo.soares
 *
 */
public class LinhaExemplo implements Serializable {
    

	private static final long serialVersionUID = -4561146545440673645L;

	@Campo(tamParteInteira=1,tamParteDecimal=0,posicaoRegistro=1,obrigatorio=true,valorDefault="0")
    private BigDecimal campo1;
    
    @Campo(tamParteInteira=5,tamParteDecimal=2,posicaoRegistro=2,obrigatorio=true,valorDefault="1")
    private BigDecimal campo2 ;
    
    @Campo(tamParteInteira=11,tamParteDecimal=0,posicaoRegistro=9,obrigatorio=true,valorDefault="")
    private String campo3;
    
    @Campo(tamParteInteira=30,tamParteDecimal=0,posicaoRegistro=20,obrigatorio=true,valorDefault="2")
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
