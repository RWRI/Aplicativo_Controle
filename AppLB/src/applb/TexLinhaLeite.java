//Desenvolvedor: Ryan Wyllyan Ribeiro Inacio
//Painel Relação EXLinhaLeite

package applb;

import BancoDados.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TexLinhaLeite extends JPanel{
    private ArrayList<Fornecedor> fornecedores = new ArrayList<>();
    
    public TexLinhaLeite(){
        JTabbedPane abas = new JTabbedPane();

        fornecedores = Acesso.selectFornecedores();
        
        int qnt = fornecedores.size();
        
        for(int i = 0;i < qnt;i++){
            abas.addTab(fornecedores.get(i).getNome(),null,
                new EXLinhaLeite(fornecedores.get(i)),"Fornecedor "+(i+1));
        }
       
        add(abas);
        
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(550,550);
    }
}
