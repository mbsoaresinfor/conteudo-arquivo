package br.com.mbs.conteudoarquivo.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Classe exemplo, que simula um arquivo
 * 
 * @author marcelo.soares
 *
 */
public class ArquivoExemplo {

    private CabecalhoExemplo cabelcalho;
    private List<Serializable> listaDetalhes;
    private RodapeExemplo rodape;
    
    
	public CabecalhoExemplo getCabelcalho() {
		return cabelcalho;
	}
	public void setCabelcalho(CabecalhoExemplo cabelcalho) {
		this.cabelcalho = cabelcalho;
	}
	public List<Serializable> getListaDetalhes() {
		return listaDetalhes;
	}
	public void setListaDetalhes(List<Serializable> listaDetalhes) {
		this.listaDetalhes = listaDetalhes;
	}
	public RodapeExemplo getRodape() {
		return rodape;
	}
	public void setRodape(RodapeExemplo rodape) {
		this.rodape = rodape;
	}
    
    
    
	
    
    
}
