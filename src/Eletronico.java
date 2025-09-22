public class Eletronico extends Produto {
    private String marca;

    public Eletronico(String nome, double preco, int quantidade, String marca) {
        super(nome, preco, quantidade);
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return super.toString() + " - Marca: " + marca;
    }
}
