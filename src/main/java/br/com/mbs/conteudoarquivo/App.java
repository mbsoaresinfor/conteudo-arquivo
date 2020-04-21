package br.com.mbs.conteudoarquivo;

import java.math.BigDecimal;

import br.com.mbs.conteudoarquivo.entidades.CabecalhoArquivo;
import br.com.mbs.conteudoarquivo.entidades.DetalheArquivo;
import br.com.mbs.conteudoarquivo.entidades.RodapeArquivo;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	CabecalhoArquivo cabecalho = new CabecalhoArquivo();
    	cabecalho.setValor1("abc");
    	cabecalho.setValor2("abc");
    	
    	DetalheArquivo detalhe = new DetalheArquivo();
    	detalhe.setValor1(new BigDecimal(5));
    	detalhe.setValor2("yze");
    	
    	RodapeArquivo rodape = new RodapeArquivo();
    	rodape.setValor1(new BigDecimal(9));
    	rodape.setValor2("abc");
    	
    	String conteudo = new CriadorConteudoArquivo.Criador().
    			adicionaCabecalho(cabecalho).adicionaDetalhes(detalhe).
    			adicionaRodape(rodape).criaConteudo();
    	
    	System.out.println(conteudo);
    	
        
        
    }
}
