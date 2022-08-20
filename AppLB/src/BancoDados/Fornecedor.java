//Desenvolvedor: Ryan Wyllyan Ribeiro Inacio
//Classe para representar o Fornecedor

package BancoDados;

public class Fornecedor {
    private String nome;
    private Double preco;

    public Fornecedor() {
        this.nome = "";
        this.preco = 0.0;
    }

    public Fornecedor(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
