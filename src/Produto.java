public class Produto {
    private String nome;
    private double preco;
    private int quantidade;

    public Produto(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void adicionarQuantidade(int qtd) {
        this.quantidade += qtd;
    }

    public void removerQuantidade(int qtd) {
        if (this.quantidade >= qtd) this.quantidade -= qtd;
        else System.out.println("\nNão há estoque suficiente para remover!\n");
    }

    @Override
    public String toString() {
        return nome + " - R$" + String.format("%.2f", preco) + " - Quantidade: " + quantidade;
    }
}
