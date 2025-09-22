import java.util.*;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        ArrayList<Produto> estoque = new ArrayList<>();
        ArrayList<String> historico = new ArrayList<>();

        // Produtos - Perecíveis
        estoque.add(new Perecivel("Arroz", 5.50, 10, "10/09/2025"));
        estoque.add(new Perecivel("Carne", 30.00, 5, "09/09/2025"));
        estoque.add(new Perecivel("Feijão", 7.80, 15, "15/09/2025"));
        estoque.add(new Perecivel("Leite", 4.20, 20, "12/09/2025"));
        estoque.add(new Perecivel("Queijo", 18.00, 8, "20/09/2025"));

        // Produtos - Eletrônicos
        estoque.add(new Eletronico("Celular", 1200.00, 5, "Samsung"));
        estoque.add(new Eletronico("Fone de Ouvido", 200.00, 12, "Sony"));
        estoque.add(new Eletronico("Monitor", 800.00, 4, "Acer"));
        estoque.add(new Eletronico("Notebook", 3500.00, 3, "Dell"));
        estoque.add(new Eletronico("TV", 2500.00, 2, "LG"));

        int escolha;
        do {
            double valorTotal = 0;
            System.out.println("\n==== Estoque Atual ====");
            boolean separadorImpresso = false;
            for (int i = 0; i < estoque.size(); i++) {
                Produto p = estoque.get(i);
                System.out.println(p);
                valorTotal += p.getPreco() * p.getQuantidade();

                if (!separadorImpresso && p instanceof Perecivel) {
                    if (i + 1 < estoque.size() && estoque.get(i + 1) instanceof Eletronico) {
                        System.out.println("-------------------------------------------------------");
                        separadorImpresso = true;
                    }
                }
            }
            System.out.println("Valor total do estoque: R$ " + String.format("%.2f", valorTotal) + ".");

            System.out.println("\n*** Menu ***");
            System.out.println("1- Adicionar produto ao estoque");
            System.out.println("2- Remover unidades de um produto");
            System.out.println("3- Ver histórico de movimentação");
            System.out.println("4- Excluir produto do estoque");
            System.out.println("5- Encerrar");
            System.out.print("Escolha uma opção: ");
            escolha = leitura.nextInt();
            leitura.nextLine();

            switch (escolha) {
                case 1:
                    System.out.print("\nNome do produto: ");
                    String nome = leitura.nextLine();
                    System.out.print("Quantidade: ");
                    int quantidade = leitura.nextInt();
                    leitura.nextLine();

                    System.out.print("Tipo (1-Perecível / 2-Eletrônico): ");
                    int tipo = leitura.nextInt();
                    leitura.nextLine();

                    Produto novoProduto;
                    if (tipo == 1) {
                        System.out.print("Preço: ");
                        double preco = leitura.nextDouble();
                        leitura.nextLine();
                        System.out.print("Validade: ");
                        String validade = leitura.nextLine();
                        novoProduto = new Perecivel(nome, preco, quantidade, validade);
                    } else {
                        System.out.print("Marca: ");
                        String marca = leitura.nextLine();
                        System.out.print("Preço: ");
                        double preco = leitura.nextDouble();
                        novoProduto = new Eletronico(nome, preco, quantidade, marca);
                    }

                    boolean existe = false;
                    for (Produto p : estoque) {
                        if (p.getNome().equalsIgnoreCase(nome) && p.getClass() == novoProduto.getClass()) {
                            p.adicionarQuantidade(quantidade);
                            historico.add("Adicionado +" + quantidade + " ao produto " + nome);
                            existe = true;
                            break;
                        }
                    }

                    if (!existe) {
                        if (novoProduto instanceof Perecivel) {
                            int pos = 0;
                            for (int i = 0; i < estoque.size(); i++) {
                                if (estoque.get(i) instanceof Eletronico) {
                                    pos = i;
                                    break;
                                } else pos = estoque.size();
                            }
                            estoque.add(pos, novoProduto);
                        } else {
                            estoque.add(novoProduto);
                        }
                        historico.add("Novo produto cadastrado: " + nome + " (" + quantidade + " unidades)");
                    }
                    estoque.sort(new Comparator<Produto>() {
                        @Override
                        public int compare(Produto p1, Produto p2) {
                            if (p1 instanceof Perecivel && p2 instanceof Eletronico) return -1;
                            if (p1 instanceof Eletronico && p2 instanceof Perecivel) return 1;
                            return p1.getNome().compareToIgnoreCase(p2.getNome());
                        }
                    });

                    System.out.println("\nProduto adicionado com sucesso!");
                    break;

                case 2:
                    System.out.print("\nNome do produto para remover unidades: ");
                    String removerNome = leitura.nextLine();
                    boolean encontrado = false;
                    for (Produto p : estoque) {
                        if (p.getNome().equalsIgnoreCase(removerNome)) {
                            System.out.print("Quantidade para remover: ");
                            int removerQtd = leitura.nextInt();
                            leitura.nextLine();
                            if (removerQtd > 0) {
                                p.removerQuantidade(removerQtd);
                                historico.add("Removido -" + removerQtd + " do produto " + removerNome);
                            }
                            System.out.println("Produto atualizado com sucesso!\n");
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) System.out.println("Produto não encontrado!\n");
                    break;

                case 3:
                    System.out.println("\n==== Histórico de movimentação ====");
                    if (historico.isEmpty()) System.out.println("Nenhuma movimentação registrada.");
                    else for (String registro : historico) System.out.println("- " + registro);
                    System.out.println("----------------------");
                    break;

                case 4:
                    System.out.print("\nNome do produto para excluir: ");
                    String excluirNome = leitura.nextLine();
                    boolean achou = false;
                    for (int i = 0; i < estoque.size(); i++) {
                        Produto p = estoque.get(i);
                        if (p.getNome().equalsIgnoreCase(excluirNome)) {
                            estoque.remove(i);
                            historico.add("Produto " + excluirNome + " removido do estoque completamente");
                            System.out.println("Produto excluído do estoque!\n");
                            achou = true;
                            break;
                        }
                    }
                    if (!achou) System.out.println("Produto não encontrado!\n");
                    break;

                case 5:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.\n");
            }

        } while (escolha != 5);

        leitura.close();
    }
}
