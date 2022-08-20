//Desenvolvedor: Ryan Wyllyan Ribeiro Inacio
//Painel de Excluir dados linha de leite Linha de Leite


package applb;

import BancoDados.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class EXLinhaLeite extends JPanel{
    private ArrayList<LinhaLeite> linha = new ArrayList<>(), nlinha= new ArrayList<>();
    private JButton excluir = new JButton("Excluir Dados");
    private JCheckBox[] dia = new JCheckBox[31];
    private JTextField[] qntLeite  =  new JTextField[31], pl =  new JTextField[31];
    int qnt = 0;
    private final String[] an = {"2022","2023","2024","2025","2026","2027","2028","2029","2030"};
    private final JComboBox anos = new JComboBox(an);
    private final String[] nm = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho",
        "Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
    private final JComboBox meses = new JComboBox(nm);
    private String fornec;
    private JLabel nof = new JLabel("");
    
    public EXLinhaLeite(Fornecedor f){
        setLayout(new BorderLayout());
        
        fornec = f.getNome();
        
        nof.setText(fornec.toUpperCase());
        nof.setFont(new Font("Monospace", Font.BOLD, 16));
        
        JPanel data = new JPanel(new GridLayout(1,2,5,5));
        data.add(anos);
        data.add(meses);
                
        JPanel titulo = new JPanel(new GridLayout(1,3,5,5));
        titulo.add(new JLabel("Dia:"));
        titulo.add(new JLabel("Quantidade Leite"));
        titulo.add(new JLabel("Preço do Litro"));
        
        JPanel top = new JPanel(new GridLayout(3,1,5,5)), aux = new JPanel();
        aux.add(nof);
        top.add(aux);
        top.add(data);
        top.add(titulo);
        add(top,BorderLayout.NORTH);
        
        anos.addActionListener(e -> {
            atualiza(new LinhaLeite(regulaDia(), fornec,0,f.getPreco()));
            meses.requestFocus();
        });
        meses.addActionListener(e -> {
            atualiza(new LinhaLeite(regulaDia(), fornec,0,f.getPreco()));
            anos.requestFocus();
        });
        
        LinhaLeite l = new LinhaLeite(regulaDia(),fornec,0,f.getPreco());
        linha = Acesso.selectQntLeite(l);
        
        qnt = linha.size();
        JPanel forne = new JPanel(new GridLayout(31,3,5,5));        
        for(int j = 0; j < qnt;j++){
            dia[j] = new JCheckBox(linha.get(j).getDia(),false);
            qntLeite[j] = new JTextField(linha.get(j).getQnt_leite()+"",10);
            pl[j] = new JTextField(linha.get(j).getPreco()+"",10);
            forne.add(dia[j]);
            forne.add(qntLeite[j]);
            forne.add(pl[j]);
        }
        for(int k = qnt; k < 31;k++){
            dia[k] = new JCheckBox("",false);
            qntLeite[k] = new JTextField("",10);
            pl[k] = new JTextField("",10);
            forne.add(dia[k]);
            dia[k].setVisible(false);
            forne.add(qntLeite[k]);
            qntLeite[k].setVisible(false);
            forne.add(pl[k]);
            pl[k].setVisible(false);
        }
            
        JPanel scroll = new JPanel();
        scroll.setLayout(new BoxLayout(scroll, BoxLayout.Y_AXIS));
        scroll.add(forne);
        
        JScrollPane src = new JScrollPane(scroll);
        src.setPreferredSize(new Dimension(400,400));
        add(src,BorderLayout.CENTER);
        
        add(excluir,BorderLayout.SOUTH);
        excluir.addActionListener(e -> exc());
    }
    
    public void exc(){
        int o = JOptionPane.showConfirmDialog(EXLinhaLeite.this, "Deseja realmente exluir os fornecedores selecionados?", 
                        "ATENÇÃO", JOptionPane.NO_OPTION);
        if(o != JOptionPane.YES_OPTION) return;
        boolean resp = true;
        for(int j = 31-1;j >= 0;j--){
            if(dia[j].isSelected()){
                resp = (resp && Acesso.deleteLinhaLeite(new LinhaLeite(dia[j].getText(),
                        fornec,Integer.parseInt(qntLeite[j].getText()))));
                dia[j].setSelected(false);
                dia[j].setVisible(false);
                qntLeite[j].setVisible(false);
                pl[j].setVisible(false);
            }
        }
        revalidate();
        
        if(resp){
            JOptionPane.showMessageDialog(EXLinhaLeite.this, "Dados deletados com sucesso", 
                        "Delete concluído", JOptionPane.PLAIN_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(EXLinhaLeite.this, "Dados não foram deletados, problemas com o banco de dados", 
                        "Delete não realizado", JOptionPane.PLAIN_MESSAGE);
        }
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
    
    public void atualiza(LinhaLeite l){
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
        return new Dimension(450,500);
    }
}
