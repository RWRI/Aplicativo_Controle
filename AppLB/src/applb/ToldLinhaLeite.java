//Desenvolvedor: Ryan Wyllyan Ribeiro Inacio
//Painel Relação OldLinhaLeite

package applb;

import BancoDados.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ToldLinhaLeite extends JPanel{
    private ArrayList<Fornecedor> fornecedores = new ArrayList<>();
    private ArrayList<OldLinhaLeite> paineis = new ArrayList<>();
    private final String[] an = {"2022","2023","2024","2025","2026","2027","2028","2029","2030"};
    private final JComboBox anos = new JComboBox(an);
    private final String[] nm = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho",
        "Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
    private final JComboBox meses = new JComboBox(nm);
    JTabbedPane abas = new JTabbedPane();
    
    public ToldLinhaLeite(){
        setLayout(new BorderLayout());
        
        JPanel top = new JPanel(new GridLayout(1,2,5,5));
        top.add(anos);
        top.add(meses);
        add(top,BorderLayout.NORTH);

        fornecedores = Acesso.selectFornedorLinhaLeite(regulaDia());
        
        int qnt = fornecedores.size();
        
        for(int i = 0;i < qnt;i++){
            LinhaLeite l = new LinhaLeite(regulaDia(),fornecedores.get(i));
            paineis.add(new OldLinhaLeite(l));
            abas.addTab(fornecedores.get(i).getNome(),null,paineis.get(i),"Fornecedor "+(i+1));
        }
        
        anos.addActionListener(e -> {
            atualiza();
            meses.requestFocus();
        });
        meses.addActionListener(e -> {
            atualiza();
            anos.requestFocus();
        });
       
        add(abas,BorderLayout.CENTER);
    }
    
    private void atualiza(){
        fornecedores.clear();
        
        fornecedores = Acesso.selectFornedorLinhaLeite(regulaDia());
        
        for(Fornecedor f : fornecedores){
            System.out.println(f.getNome());
        }
        
        if(fornecedores.size() >= paineis.size()){
            for(int i = 0;i < paineis.size();i++){
                paineis.get(i).atualiza(new LinhaLeite(regulaDia(),fornecedores.get(i)));
                abas.setTitleAt(i, fornecedores.get(i).getNome());
            }
            for(int i = paineis.size();i < fornecedores.size();i++){
                LinhaLeite l = new LinhaLeite(regulaDia(),fornecedores.get(i));
                paineis.add(new OldLinhaLeite(l));
                abas.addTab(fornecedores.get(i).getNome(),null,paineis.get(i),"Fornecedor "+(i+1));
            }
        }else{
           for(int i = 0;i < fornecedores.size();i++){
                paineis.get(i).atualiza(new LinhaLeite(regulaDia(),fornecedores.get(i)));
                abas.setTitleAt(i, fornecedores.get(i).getNome());
            }
            for(int i = paineis.size()-1;i >= fornecedores.size();i--){
                abas.remove(i);
                paineis.remove(i);
            } 
        }
        
        revalidate();
    }
    
    private String regulaDia(){
        String day;
        
        if((meses.getSelectedIndex()+1) < 10){
            day = an[anos.getSelectedIndex()]+"-0"+(meses.getSelectedIndex()+1);
        }else{
            day = an[anos.getSelectedIndex()]+"-"+(meses.getSelectedIndex()+1);
        }
        return day;
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(500,550);
    }
    
}
