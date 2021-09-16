package programas;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import entidades.Produto;

public class Application {

	public static void main(String[] args) {

		// FUNÇÕES
		Scanner leia = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("#.00");

		// VARIAVEIS
		String auxCod;
		int pos, auxQntd = 0, x, pagamento;
		char op = ' ', op2;
		double contador = 0.0;

		// LISTAS E OBJETO
		Produto notaFiscal = new Produto();
		ArrayList<Produto> itens = new ArrayList<>();
		ArrayList<Produto> carrinho = new ArrayList<>();
		ArrayList<Produto> estoque = new ArrayList<>();

		itens.add(new Produto("G7-01", "PULSEIRA ICY", 70.99, 10));
		itens.add(new Produto("G7-02", "ANEL TOPÁZIO", 50.99, 10));
		itens.add(new Produto("G7-03", "COlAR SAFIRA", 39.99, 10));
		itens.add(new Produto("G7-04", "PULSEIRA LIZ", 59.99, 10));
		itens.add(new Produto("G7-05", "ANEL CRISTAL", 65.59, 10));
		itens.add(new Produto("G7-06", "BRINCO JASPE", 85.99, 10));
		itens.add(new Produto("G7-07", "PINGENTE LUA", 45.59, 10));
		itens.add(new Produto("G7-08", "COLAR AMÉLIA", 40.59, 10));
		itens.add(new Produto("G7-09", "ANEL CRISTAL", 99.59, 10));
		itens.add(new Produto("G7-10", "BRINCO ÁGATA", 59.79, 10));

		int auxQntd2[] = new int[itens.size()];

		// TABELA RELAÇÃO PRODUTOS + NOME/SLOGAN
		linha();
		System.out.print("\n\t\t    \n");
		nome();
		for (Produto lista : itens) {
			System.out.print("\n" + lista.getCodigo() + "\t     " + lista.getNome() + "\t  " + df.format(lista.getValor())+ "\t             " + lista.getEstoque());
		}

		// VALIDAÇÃO DE COMPRA
		linha();
		System.out.print("\nDESEJA FAZER COMPRAS? ");
		System.out.print("\n(DIGITE 'S' PARA SIM E 'N' PARA NÃO): ");
		op = leia.next().toUpperCase().charAt(0);
		linha();

		if (op == 'S') {

			// COMEÇO DA PRIMEIRA FUNÇÃO DE FAZER COMPRAS
			do {
				leia.nextLine();
				System.out.print("\nDIGITE O CÓDIGO DO PRODUTO: ");
				auxCod = leia.nextLine().toUpperCase();

				// LAÇO DA VALIDAÇÃO DO CÓDIGO DO PRODUTO
				for (x = 0; x < itens.size(); x++) {

					// LAÇO CONDICIONAL DE VALIDAÇÃO DO CÓDIGO INSERIDO
					if (itens.get(x).getCodigo().equals(auxCod)) {
						pos = x;
						System.out.print("INFORME A QUANTIDADE QUE DESEJA: ");
						auxQntd = leia.nextInt();

						// LAÇO CONDICIONAL DE VALIDAÇÃO DA QUANTIDADE NO ESTOQUE
						if (auxQntd > itens.get(pos).getEstoque() || auxQntd < 0 || auxQntd == 0) {
							linha();
							System.out.println("\nQUANTIDADE INVÁLIDA.");
							System.out.println("O ESTOQUE É DE: " + itens.get(pos).getEstoque());
							linha();

						} else {
							carrinho.add(new Produto(itens.get(pos).getCodigo(), itens.get(pos).getNome(), itens.get(pos).getValor(), auxQntd));
							itens.get(pos).retirarEstoque(auxQntd);
							leia.nextLine();

							auxQntd2[pos] = auxQntd;

							// EXIBIÇÃO DE CARRINHO
							linha();
							System.out.print("\t        ✧ CARRINHO DE COMPRAS ✧");
							linha();
							System.out.print("CÓDIGO \t\tPRODUTO\t\t    PREÇO\t      QNTD\n");

							for (Produto lista2 : carrinho) {
								System.out.print("\n" + lista2.getCodigo() + "\t     " + lista2.getNome() + "\t    " + lista2.getValor() + " \t       " + lista2.getEstoque());
							}
							linha();
						}
					}
				}
				
				// CONDICIONAL QUE SAI DO LAÇO DO..WHILE
				System.out.println("\nDESEJA CONTINUAR COMPRANDO?");
				System.out.print("DIGITE 'S' PARA SIM E 'N' PARA NÃO: ");
				op2 = leia.next().toUpperCase().charAt(0);
				linha();
				
				// RELAÇÃO DE PRODUTOS NOVAMENTE ANTES DE FINALIZAR A COMPRA
				if (op2 == 'S') {
					System.out.print("\t        ✧ RELAÇÃO DE PRODUTOS ✧");
					linha();
					System.out.print("CÓDIGO \t\tPRODUTO\t\t  PREÇO\t\t   ESTOQUE\n");
					for (Produto lista : itens) {
						System.out.print("\n" + lista.getCodigo() + "\t     " + lista.getNome() + "\t  " + df.format(lista.getValor())+ "\t             " + lista.getEstoque());
					}
					linha ();
				}	

			} while (op2 == 'S');

			// EXIBIÇÃO DO CARRINHO FINAL
			if (contador != 0) {
				System.out.println("\t         ✧ CARRINHO DE COMPRAS ✧");
				for (Produto lista2 : carrinho) {
					System.out.print("\n" + lista2.getCodigo() + "\t" + lista2.getNome() + "\t  "
							+ df.format(lista2.getValor()) + " \t" + lista2.getEstoque());
				}
			}
			// FINALIZAÇÃO DAS COMPRAS
			for (int i = 0; i < carrinho.size(); i++) {
				pos = i;
				contador += (carrinho.get(pos).getValor() * carrinho.get(pos).getEstoque());
			}
			System.out.print("\t   VALOR TOTAL DA COMPRA: R$" + df.format(contador));
			linha();
			System.out.print("\n\t\t  FORMAS DE PAGAMENTOS:\n\n");
			System.out.print(" OPÇÃO 1 - A VISTA COM 10% DESCONTO\n");
			System.out.print(" OPÇÃO 2 - NO CARTÃO COM ACRESCIMO DE 10%\n");
			System.out.print(" OPÇÃO 3 - EM 2X COM 15% TOTAL DE ACRESCIMENTO\n");
			linha();

			// FUNÇÃO FORMA DE PAGAMENTO
			do {
				System.out.print("\nINSIRA AQUI SUA FORMA DE PAGAMENTO: ");
				pagamento = leia.nextInt();
				linha();

				switch (pagamento) {

				case 1:
					System.out.print("\t\t    ✧ NOTA FISCAL ✧");
					linha();
					nota();
					notaFiscal.pagamentos(pagamento, contador);
					carrinho.clear();
					linha();
					break;

				case 2:
					System.out.print("\t\t    ✧ NOTA FISCAL ✧");
					linha();
					nota();
					notaFiscal.pagamentos(pagamento, contador);
					carrinho.clear();
					linha();

					break;

				case 3:
					System.out.print("\t\t    ✧ NOTA FISCAL ✧");
					linha();
					nota();
					notaFiscal.pagamentos(pagamento, contador);
					carrinho.clear();
					linha();

					break;

				default:
					System.out.printf("OPÇÃO INVÁLIDA, TENTE NOVAMENTE.\n");
					break;

				}

			} while (pagamento > 3);
		}
	}

	// FUNÇÕES EXTRAS
	public static void nome() {
		System.out.print("\t     ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆\n");
		System.out.print("\t     ☆  ╔═════════════════════╗	 ☆\n");
		System.out.print("\t     ☆   ❝     JÓIAS CODE     ❞	 ☆\n"); 
		System.out.print("\t     ☆  ╚═════════════════════╝	 ☆\n"); 
		System.out.print("\t     ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆\n");
		System.out.print("\n\t    ✧ TRAZENDO SEU BRILHO INTERIOR ✧\n");
		linha();
		System.out.print("\t        ✧ RELAÇÃO DE PRODUTOS ✧");
		linha();
		System.out.print("CÓDIGO \t\tPRODUTO\t\t  PREÇO\t\t   ESTOQUE\n");
	}

	public static void nota() {
		System.out.println();
		System.out.print("\t     ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆\n");
		System.out.print("\t     ☆  ╔═════════════════════╗	 ☆\n");
		System.out.print("\t     ☆   ❝     JÓIAS CODE     ❞	 ☆\n"); 
		System.out.print("\t     ☆  ╚═════════════════════╝	 ☆\n"); 
		System.out.print("\t     ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆\n");
	}

	public static void linha() {
		System.out.println("\n══════════════════════════════════════════════════════════");
	}

}
