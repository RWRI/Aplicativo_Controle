//Desenvolvedor: Ryan Wyllyan Ribeiro Inacio
//Painel Cadastro da Linha de Leite

package applb;

import BancoDados.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class CLinhaLeite extends JPanel {
    private final String[] an = {"2022","2023","2024","2025","2026","2027","2028","2029","2030"};
    private final JComboBox anos = new JComboBox(an);
    private final String[] nm = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho",
        "Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"}, nd = new String[31];
    private final JComboBox meses = new JComboBox(nm), dias;
    private ArrayList<Fornecedor> fornecedores = new ArrayList<>();
    private ArrayList<JLabel> nf = new ArrayList<>();
    private ArrayList<JTextField> qtleite = new ArrayList<>();
    private JButton salvar = new JButton("Salvar dados linha de leite");
    int qt = 0;
    
    public CLinhaLeite(){
        setLayout(new BorderLayout(5,5));
        
        JPanel top = new JPanel(new GridLayout(2,1)), data = new JPanel(new GridLayout(1,3)),
                dia = new JPanel(), mes = new JPanel(), ano = new JPanel(), aux = new JPanel();
        for(int i = 1;i < 32;i++) nd[i-1] = Integer.toString(i);
        dias = new JComboBox(nd);
        dia.add(new JLabel("Dia:"));
        dia.add(dias);
        mes.add(new JLabel("Mês:"));
        mes.add(meses);
        ano.add(new JLabel("Ano:"));
        ano.add(anos);
        data.add(ano);
        data.add(mes);
        data.add(dia);
        aux.add(new JLabel("Defina a data para o cadastro:"));
        top.add(aux);
        top.add(data);
        add(top,BorderLayout.NORTH);
        
        anos.addActionListener(e -> {
            arrumardata();
            meses.requestFocus();
        });
        meses.addActionListener(e -> {
            arrumardata();
            dias.requestFocus();
        });
        dias.addActionListener(e -> {
            qtleite.get(0).requestFocus();
        });
        
        fornecedores = Acesso.selectFornecedores();
        
         for(int i = 0;i < fornecedores.size();i++){
             nf.add(new JLabel(fornecedores.get(i).getNome()));
             qtleite.add(new JTextField("",10));
         }
         
         qt = nf.size();
         JPanel linha = new JPanel(new GridLayout(qt,2,3,3));
         for(int i = 0;i < qt;i++){
             JPanel a  = new JPanel(), a2 = new JPanel();
             a.add(nf.get(i));
             a2.add(qtleite.get(i));
             linha.add(a);
             linha.add(a2);
         }
         add(linha,BorderLayout.CENTER);
         
         for(int j = 0;j < qt;j++){
             JTextField a = qtleite.get(j);
             qtleite.get(j).addActionListener(e -> {
                 mudarf(qtleite.indexOf(a));
             });
         }
         
         add(salvar,BorderLayout.SOUTH);
         salvar.addActionListener(e -> salve());
    }
    
    private void arrumardata(){
        dias.removeAllItems();
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR,2022+anos.getSelectedIndex());
        instance.set(Calendar.MONTH, meses.getSelectedIndex());
        instance.set(Calendar.DAY_OF_MONTH, instance.getActualMaximum(Calendar.DAY_OF_MONTH));
        int day = instance.get(Calendar.DAY_OF_MONTH);
        for(int i = 1;i <= day;i++){
            nd[i-1] = Integer.toString(i);
            dias.addItem(nd[i-1]);
        }        
        revalidate();
    }
    
    public void mudarf(int i){
        if(i < qt-1){
            qtleite.get(i+1).requestFocus();
        }else{
           qtleite.get(0).requestFocus(); 
        }
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(380,100+30*qt);
    }
    
    public void salve(){
        int o = JOptionPane.showConfirmDialog(CLinhaLeite.this, "Deseja realmente salvar os dados?", 
                        "ATENÇÃO", JOptionPane.NO_OPTION);
        if(o != JOptionPane.YES_OPTION) return;
        
        String dia = an[anos.getSelectedIndex()]+"-"+(meses.getSelectedIndex()+1)+"-"
                +(dias.getSelectedIndex()+1);
        
        for(int i = nf.size()-1;i >= 0 ;i--){
            if(!Acesso.insert(new LinhaLeite(dia, nf.get(i).getText(),Integer.parseInt(
                    (qtleite.get(i).getText().equals(""))?"0":qtleite.get(i).getText()),
                    fornecedores.get(i).getPreco()))){
                JOptionPane.showMessageDialog(CLinhaLeite.this, "Ja foram cadastrados valores para a data selecionada",
                        "Cadastro duplicado", JOptionPane.PLAIN_MESSAGE);
                return;
            }   
        }
        JOptionPane.showMessageDialog(CLinhaLeite.this, "Foram cadastrados com sucesso os valores do dia "+dia,
                        "Cadastro completo", JOptionPane.PLAIN_MESSAGE);
        revalidate();   
    }
}
