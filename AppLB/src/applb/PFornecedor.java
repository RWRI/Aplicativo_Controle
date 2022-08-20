//Desenvolvedor: Ryan Wyllyan Ribeiro Inacio
//Painel Relação RFornecedor

package applb;

import BancoDados.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PFornecedor extends JPanel{
    private ArrayList<Fornecedor> fornecedores = new ArrayList<>();
    
    public PFornecedor(){
        JTabbedPane abas = new JTabbedPane();

        fornecedores = Acesso.selectFornecedores();
        
        for(int i = 0;i < fornecedores.size();i++){
            abas.addTab(fornecedores.get(i).getNome(),null,
                new RFornecedor(fornecedores.get(i).getNome(),
                fornecedores.get(i).getPreco()),"Fornecedor "+(i+1));
        }
       
        add(abas);
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(500,530);
    }
}
