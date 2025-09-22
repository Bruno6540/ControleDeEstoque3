public class Perecivel extends Produto {
    private String validade;

    public Perecivel(String nome, double preco, int quantidade, String validade) {
        super(nome, preco, quantidade);
        this.validade = validade;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    @Override
    public String toString() {
        return super.toString() + " - Validade: " + validade;
    }
}
