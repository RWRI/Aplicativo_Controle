//Desenvolvedor: Ryan Wyllyan Ribeiro Inacio
//Painel de Editar Fornecedores

package applb;

import BancoDados.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class EDFornecedores extends JPanel{
    private ArrayList<Fornecedor> fornecedores = new ArrayList<>(), nfornecedores = new ArrayList<>();
    private JButton salvar = new JButton("Salvar Edições");
    private ArrayList<JTextField> nf  =  new ArrayList<>(), pl =  new ArrayList<>();
    int i = 0;
    
    
    public EDFornecedores(){
        setLayout(new BorderLayout());
        
        fornecedores = Acesso.selectFornecedores();
                
        i = fornecedores.size();
        JPanel forne = new JPanel(new GridLayout(i+2,2,5,5));
        forne.add(new JLabel("Nomes:"));
        forne.add(new JLabel("Preço do litro pago:"));
        for(int j = 0; j < i;j++){
            nf.add(new JTextField(fornecedores.get(j).getNome()));
            pl.add(new JTextField(fornecedores.get(j).getPreco().toString()));
            forne.add(nf.get(j));
            forne.add(pl.get(j));
        }
        add(forne,BorderLayout.CENTER);
        
        add(salvar,BorderLayout.SOUTH);
        salvar.addActionListener(e -> salve());
    }
    
    public void salve(){
        int o = JOptionPane.showConfirmDialog(EDFornecedores.this, "Deseja realmente salvar as edições?", 
                        "ATENÇÃO", JOptionPane.NO_OPTION);
        if(o != JOptionPane.YES_OPTION) return;
        
        try{
           for(int i = 0; i < fornecedores.size();i++){
                nfornecedores.add(new Fornecedor(nf.get(i).getText(),Double.parseDouble(pl.get(i).getText())));
           }    
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(EDFornecedores.this, "Dados Invalidos!\nUtilize o \".\" no lugar da \",\" ", 
                        "Falha no cadastro", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        
        boolean resp = true;
        for(int i = 0; i < fornecedores.size();i++){
            resp = (resp && Acesso.updateFornecedores(fornecedores.get(i).getNome(), nfornecedores.get(i)));
        }
        
        if(resp){
            JOptionPane.showMessageDialog(EDFornecedores.this, "Fornecedores atualizados com sucesso", 
                        "Update concluído", JOptionPane.PLAIN_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(EDFornecedores.this, "Fornecedores não foram atualizados, problemas com o banco de dados", 
                        "Update não realizado", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(520,60+30*i);
    }
}
