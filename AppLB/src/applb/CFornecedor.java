//Desenvolvedor: Ryan Wyllyan Ribeiro Inacio
//Painel Cadastro Fornecedor

package applb;

import BancoDados.*;
import javax.swing.*;
import java.awt.*;

public class CFornecedor extends JPanel {
    private JTextField nome = new JTextField("",50), preco = new JTextField("",10);
    private final JButton salvar = new JButton("Salvar");
    
    public CFornecedor(){
        setLayout(new BorderLayout(5,5));
        
        JPanel top = new JPanel();
        top.add(new JLabel("Insira os dados solitados a baixo:"));
        add(top,BorderLayout.NORTH);
        
        JPanel dados = new JPanel(new GridLayout(2,2,3,3));
        dados.add(new JLabel("Nome do fornecedor:"));
        dados.add(nome);
        nome.addActionListener(e -> preco.requestFocus());
        dados.add(new JLabel("Preço do litro pago ao fornecedor:"));
        dados.add(preco);
        preco.addActionListener(e -> nome.requestFocus());
        add(dados,BorderLayout.CENTER);
        
        add(salvar,BorderLayout.SOUTH);
        salvar.addActionListener(e -> salva());
    }
    
    public void salva(){
        Fornecedor forne = new Fornecedor();
        
        if(nome.getText().equals("")){
            JOptionPane.showMessageDialog(CFornecedor.this, "Dados Invalidos!", 
                        "Falha no cadastro", JOptionPane.PLAIN_MESSAGE);
            return;
        }else{
            forne.setNome(nome.getText()); 
        }
        
        try{
           forne.setPreco(Double.parseDouble(preco.getText()));
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(CFornecedor.this, "Dados Invalidos!\nUtilize o \".\" no lugar da \",\" ", 
                        "Falha no cadastro", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        
        if(Acesso.insertFornecedor(forne)){
            JOptionPane.showMessageDialog(CFornecedor.this, "Fornecedor inserido no banco de dados com sucesso", 
                        "Cadastro realizado", JOptionPane.PLAIN_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(CFornecedor.this, "Fornecedor não foi cadastrado, problemas com o banco de dados", 
                        "Cadastro não realizado", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(520,110);
    }
}
