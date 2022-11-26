//Desenvolvedor: Ryan Wyllyan Ribeiro Inacio
//Painel para montar abas com gŕafico por fornecedor

package applb;

import BancoDados.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class GraficosForne extends JPanel {
    private ArrayList<Fornecedor> fornecedores = new ArrayList<>();
    
    public GraficosForne(){
        JTabbedPane abas = new JTabbedPane();

        fornecedores = Acesso.selectFornecedores();
        
        for(int i = 0;i < fornecedores.size();i++){
            abas.addTab(fornecedores.get(i).getNome(),null,
                new GraficosNome(fornecedores.get(i).getNome()),"Fornecedor "+(i+1));
        }
       
        add(abas);
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(1000,650);
    }
}
