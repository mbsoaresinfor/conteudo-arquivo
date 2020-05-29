Esse projeto tem o intuito de facilitar em um problema recorrente
no dia-a-dia dos desenvolvedores, que é o parseamento de dados para 
a geração de arquivos.

Existem situações que temos informações, onde devemos parsear em determinadas posições
em um arquivo txt por exemplo.

Pegamos um caso, onde é necessário gerar um arquivo TXT, com os dados de uma pessoa.
O arquivo txt, deve ser parseado conforme abaixo:

Arquivo:
Posição: 00..30 (inserir o nome da pessoa)
Posição: 31..60 (inserir o endereco da pessoa)
Posição: 61..65 (inserir a idade da pessoa)

Nesse exemplo, os desenvolvedores deveram buscar os dados da pessoa, e realizar o parser em cada posição.
Isso pode ser algo fácil, para algumas situações, mas pode em outras ser mais complicado.
Além disso, pode ocorrer a necessidade de também realizar um parser de dados de um Produto, 
Item, ContaBancaria por exemplo.

Dessa forma, esse projeto tem o objetivo de facilitar esse trabalho, onde os desenvolvedores,
terão apenas o trabalho de criar classes com os atributos que devem ser gerados no arquivo e,
definir as posições que estes, devem ser setados.
Este projeto não cria o arquivo, ele gera o conteudo do arquivo.

Por definição, temos a entidade na qual é uma classe, que armazena os dados,
que serão gerados a linha do arquivo. Cada campo da classe, deve usar
a anotação @Campo, onde essa, defini os posicionamento, na linha do arquivo a ser gerado.
Após, instaciar a classe, setar os valores e, criar o conteudo do arquivo,
através da classe 'CriadorConteudoArquivo'

Foi criado entidades exemplos, para demostrar o uso, conforme abaixo:

...

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

...





