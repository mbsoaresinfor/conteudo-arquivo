package br.com.mbs.conteudoarquivo;

import java.math.BigDecimal;
import br.com.mbs.conteudoarquivo.entity.CabecalhoExemplo;
import br.com.mbs.conteudoarquivo.entity.DetalheExemplo1;
import br.com.mbs.conteudoarquivo.entity.DetalheExemplo2;
import br.com.mbs.conteudoarquivo.entity.LinhaExemplo;
import br.com.mbs.conteudoarquivo.entity.RodapeExemplo;

public class MainExemploTest {

	
	public static void main(String[] args) throws Exception {
		
		// ####################
		// ##  1 Exemplo	###
		// ####################
		
		// cria o objeto (entidade), onde esta definido o posionamento dos dados no arquivo
		LinhaExemplo linha1 = new LinhaExemplo();
		
		linha1.setCampo1(new BigDecimal("1")); 
		linha1.setCampo2(new BigDecimal("12.56"));
		linha1.setCampo3("ABC");
		linha1.setCampo4(new BigDecimal("123456789"));
		
		String conteudoArquivo = new CriadorConteudoArquivo.CriadorSimples().
								adiciona(linha1).
								criaConteudo();
		
		System.out.println(conteudoArquivo);
		
	
		// ####################
		// ##  2 Exemplo	###
		// ####################
		
		// cria o objeto (entidade), onde esta definido o posionamento dos dados no arquivo
		linha1 = new LinhaExemplo();		
		linha1.setCampo1(new BigDecimal("1")); 
		linha1.setCampo2(new BigDecimal("12.56"));
		linha1.setCampo3("ABC");
		linha1.setCampo4(new BigDecimal("123456789"));
		// pode ser adicionado diversas entidades (linhas), de classes diferentes
		LinhaExemplo linha2 = new LinhaExemplo();		
		linha2.setCampo1(new BigDecimal("2")); 
		linha2.setCampo2(new BigDecimal("122.30"));
		linha2.setCampo3("yacad");
		linha2.setCampo4(new BigDecimal("6898644"));
		
		
		conteudoArquivo = new CriadorConteudoArquivo.CriadorSimples().
								adiciona(linha1).
								adiciona(linha2).
								criaConteudo();
		
		System.out.println(conteudoArquivo);

	
		// ####################
		// ##  3 Exemplo	###
		// ####################
		
		CabecalhoExemplo cabecalho = new CabecalhoExemplo();
		cabecalho.setCampo1(new BigDecimal("1"));
		cabecalho.setCampo2(new BigDecimal("2"));
		cabecalho.setCampo3("abc");
		cabecalho.setCampo4(new BigDecimal("150.55"));

		// nao esta sendo setado os valores de valor1,valor2,valor3 e valor4, onde serao configurados pelo valor default
		DetalheExemplo1 detalhe1 = new DetalheExemplo1();		
		detalhe1.setValor5("VALOR5");
		
		// setando as propriedades, pelo valor default
		DetalheExemplo2 detalhe2 = new DetalheExemplo2();	

		RodapeExemplo rodape = new RodapeExemplo();
		rodape.setValor1(new BigDecimal("100"));
		rodape.setValor2(new BigDecimal("200"));
		
		conteudoArquivo = new CriadorConteudoArquivo.Criador().
								adicionaCabecalho(cabecalho).
								adicionaDetalhes(detalhe1).
								adicionaDetalhes(detalhe2).
								adicionaRodape(rodape).
								criaConteudo();
		
		System.out.println(conteudoArquivo);
		
	}
	

	
	
}
