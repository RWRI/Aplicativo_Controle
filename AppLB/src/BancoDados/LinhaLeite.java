//Desenvolvedor: Ryan Wyllyan Ribeiro Inacio
//Classe para representar o cadastro elemento linha de leite

package BancoDados;

public class LinhaLeite {
    private String dia;
    private String fornecedor;
    private int qnt_leite;
    private double preco;

    public LinhaLeite(String dia, String fornecedor, int qnt_leite) {
        this.dia = dia;
        this.fornecedor = fornecedor;
        this.qnt_leite = qnt_leite;
    }
    
    public LinhaLeite(String dia, Fornecedor f) {
        this.dia = dia;
        this.fornecedor = f.getNome();
        this.preco = f.getPreco();
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getQnt_leite() {
        return qnt_leite;
    }

    public void setQnt_leite(int qnt_leite) {
        this.qnt_leite = qnt_leite;
    }
    
    public LinhaLeite(String dia, String fornecedor, int qnt_leite, double preco) {
        this.dia = dia;
        this.fornecedor = fornecedor;
        this.qnt_leite = qnt_leite;
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
