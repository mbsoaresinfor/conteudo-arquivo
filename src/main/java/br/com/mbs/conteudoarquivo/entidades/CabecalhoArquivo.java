package br.com.mbs.conteudoarquivo.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.mbs.conteudoarquivo.annotation.Campo;



public class CabecalhoArquivo implements Serializable{

	@Campo(tamParteInteira=30,tamParteDecimal=0,posicaoRegistro=1,obrigatorio=true,valorDefault="0")
	private String valor1;
	
	@Campo(tamParteInteira=20,tamParteDecimal=0,posicaoRegistro=31,obrigatorio=true,valorDefault="0")
	private String valor2;

	public String getValor1() {
		return valor1;
	}

	public void setValor1(String valor1) {
		this.valor1 = valor1;
	}

	public String getValor2() {
		return valor2;
	}

	public void setValor2(String valor2) {
		this.valor2 = valor2;
	}

		
	
	
}
