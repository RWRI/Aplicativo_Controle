//Desenvolvedor: Ryan Wyllyan Ribeiro Inacio
//Painel para ver Linha de Leite cadastradas

package applb;

import BancoDados.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class OldLinhaLeite extends JPanel{
    private ArrayList<LinhaLeite> linha = new ArrayList<>();
    private JLabel[] dia = new JLabel[31];
    private JLabel[] qntLeite  =  new JLabel[31], pl =  new JLabel[31];
    private String fornec;
    private JLabel nof = new JLabel("");
    
    public OldLinhaLeite(LinhaLeite l){
          setLayout(new BorderLayout());
        
        fornec = l.getFornecedor();
        
        nof.setText(fornec.toUpperCase());
        nof.setFont(new Font("Monospace", Font.BOLD, 16));
                
        JPanel titulo = new JPanel(new GridLayout(1,3,5,5));
        titulo.add(new JLabel("Dia:"));
        titulo.add(new JLabel("Quantidade Leite"));
        titulo.add(new JLabel("Preço do Litro"));
        
        JPanel top = new JPanel(new GridLayout(2,1,5,5)), aux = new JPanel();
        aux.add(nof);
        top.add(aux);
        top.add(titulo);
        add(top,BorderLayout.NORTH);
        
        linha = Acesso.selectQntLeite(l);
        
        int qnt = linha.size();
        JPanel forne = new JPanel(new GridLayout(31,3,5,5));        
        for(int j = 0; j < qnt;j++){
            JPanel aux1 = new JPanel(), aux2 = new JPanel();
            dia[j] = new JLabel(linha.get(j).getDia());
            qntLeite[j] = new JLabel(linha.get(j).getQnt_leite()+"");
            pl[j] = new JLabel(linha.get(j).getPreco()+"");
            forne.add(dia[j]);
            aux1.add(qntLeite[j]);
            forne.add(aux1);
            aux2.add(pl[j]);
            forne.add(aux2);
        }
        for(int k = qnt; k < 31;k++){
            JPanel aux1 = new JPanel(), aux2 = new JPanel();
            dia[k] = new JLabel("");
            qntLeite[k] = new JLabel("");
            pl[k] = new JLabel("");
            forne.add(dia[k]);
            dia[k].setVisible(false);
            aux1.add(qntLeite[k]);
            forne.add(aux1);
            qntLeite[k].setVisible(false);
            aux2.add(pl[k]);
            forne.add(aux2);
            pl[k].setVisible(false);
        }
            
        JPanel scroll = new JPanel();
        scroll.setLayout(new BoxLayout(scroll, BoxLayout.Y_AXIS));
        scroll.add(forne);
        
        JScrollPane src = new JScrollPane(scroll);
        src.setPreferredSize(new Dimension(250,400));
        add(src,BorderLayout.CENTER);
    }
    
    public void atualiza(LinhaLeite l){
        nof.setText(l.getFornecedor().toUpperCase());
        
        linha.clear();
        linha = Acesso.selectQntLeite(l);
        
        for(int k = 0; k < 31;k++){
            dia[k].setVisible(true);
            qntLeite[k].setVisible(true);
            pl[k].setVisible(true);
        }
        
        int j;
        for(j = 0; j < linha.size();j++){
            dia[j].setText(linha.get(j).getDia());
            qntLeite[j].setText(linha.get(j).getQnt_leite()+"");
            pl[j].setText(linha.get(j).getPreco()+"");
        }
        for(int k = j; k < 31;k++){
            dia[k].setVisible(false);
            qntLeite[k].setVisible(false);
            pl[k].setVisible(false);
        }
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(300,500);
    }
}
