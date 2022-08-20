//Desenvolvedor: Ryan Wyllyan Ribeiro Inacio
//Painel de Excluir e Exibir Fornecedores


package applb;

import BancoDados.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class EXFornecedores extends JPanel{
    private ArrayList<Fornecedor> fornecedores = new ArrayList<>();
    private JButton excluir = new JButton("Excluir Fornecedores");
    private ArrayList<JCheckBox> nf = new ArrayList<>();
    private ArrayList<JLabel> preco = new ArrayList<>();
    int i = 0;
    
    
    public EXFornecedores(){
        setLayout(new BorderLayout());
        
        fornecedores = Acesso.selectFornecedores();
                
        i = fornecedores.size();
        JPanel forne = new JPanel(new GridLayout(i+2,2,5,5));
        forne.add(new JLabel("Nomes:"));
        forne.add(new JLabel("Preço do litro pago:"));
        for(int j = 0; j < i;j++){ 
            nf.add(new JCheckBox(fornecedores.get(j).getNome(),false));
            forne.add(nf.get(j));
            preco.add(new JLabel(fornecedores.get(j).getPreco().toString()));
            forne.add(preco.get(j));
        }
        add(forne,BorderLayout.CENTER);
        
        add(excluir,BorderLayout.SOUTH);
        excluir.addActionListener(e -> exc());
    }
    
    public void exc(){
        int o = JOptionPane.showConfirmDialog(EXFornecedores.this, "Deseja realmente exluir os fornecedores selecionados?", 
                        "ATENÇÃO", JOptionPane.NO_OPTION);
        if(o != JOptionPane.YES_OPTION) return;
        boolean resp = true;
        for(int j = i-1;j >= 0;j--){
            if(nf.get(j).isSelected()){
                resp = (resp && Acesso.deleteFornedor(nf.get(j).getText()));
                nf.get(j).setSelected(false);
                nf.get(j).setVisible(false);
                preco.get(j).setVisible(false);
            }
        }
        revalidate();
        
        if(resp){
            JOptionPane.showMessageDialog(EXFornecedores.this, "Fornecedores deletados com sucesso", 
                        "Delete concluído", JOptionPane.PLAIN_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(EXFornecedores.this, "Fornecedores não foram deletados, problemas com o banco de dados", 
                        "Delete não realizado", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(520,60+20*i);
    }
}
