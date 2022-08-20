//Desenvolvedor: Ryan Wyllyan Ribeiro Inacio
//Painel Relação RFornecedor

package applb;

import BancoDados.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class RFornecedor extends JPanel{
    private final String[] an = {"2022","2023","2024","2025","2026","2027","2028","2029","2030"};
    private final JComboBox anos = new JComboBox(an);
    private final String[] nm = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho",
        "Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
    private final JComboBox meses = new JComboBox(nm);
    private JTextField de = new JTextField("0.0",10), pl = new JTextField("0.0",10);
    private JLabel Lsoma  = new JLabel(), LtotL  = new JLabel(), LtotP = new JLabel();
    final JLabel[] qdia, qld = new JLabel[32];

    private ArrayList<LinhaLeite> selectLinha = new ArrayList<>();
    private LinhaLeite l;
    int[] qntLeite = new int[31];
    double soma = 0.0, totL = 0.0, totP = 0.0, pref;
    
    public RFornecedor(String nome, Double pre){
        this.qdia = new JLabel[32];
        pref = pre;
        setLayout(new BorderLayout(5,5));
        
        JPanel top = new JPanel(new GridLayout(2,1,2,2)), data = new JPanel(new GridLayout(1,2,20,1)),
                aux = new JPanel(), aux2 = new JPanel(), aux3 = new JPanel(), aux4 = new JPanel();
        aux.add(new JLabel("Ano:"));
        aux.add(anos);
        aux2.add(new JLabel("Mes: "));
        aux2.add(meses);
        data.add(aux);
        data.add(aux2);
        nome = nome.toUpperCase();
        JLabel nof = new JLabel(nome);
        aux3.add(nof);
        nof.setFont(new Font("Monospace", Font.BOLD, 16));
        top.add(aux3);
        top.add(data);
        aux4.add(top);
        add(aux4,BorderLayout.NORTH);
        
        anos.addActionListener(e -> {
            arrumardata();
            meses.requestFocus();
        });
        meses.addActionListener(e -> {
            arrumardata();
            pl.requestFocus();
        });
        
        l = new LinhaLeite(an[anos.getSelectedIndex()]+"-"+(meses.getSelectedIndex()+1)+"-",nome,0);
        selectLinha = Acesso.selectQntLeite(l);
     
        JPanel dias = new JPanel(new GridLayout(16,4));
        int d = 1;
        for(int i = 1; i <= 32;i++){
            if(i%2 != 0){
               JPanel pd = new JPanel();
               qdia[d-1] = new JLabel("Dia "+d+":");
               pd.add(qdia[d-1]);
               dias.add(pd);
               qld[d-1] = new JLabel("xx");
               dias.add(qld[d-1]);
            }else{
                d += 16;
                JPanel pd = new JPanel();
                qdia[d-1] = new JLabel("Dia "+d+":");
                pd.add(qdia[d-1]);
                dias.add(pd);
                qld[d-1] = new JLabel("xx");
                dias.add(qld[d-1]);
                d -= 15;
            }
        }
        add(dias,BorderLayout.CENTER);
        
        JPanel preco = new JPanel(new GridLayout(2,4,3,3));
        pl.setText(pref+"");
        Lsoma.setText(""+soma);
        LtotL.setText(""+totL);
        LtotP.setText("TOTAL A PAGAR: "+totP+" R$");
        LtotP.setFont(new Font("Monospace", Font.BOLD, 18));
        conta();
        preco.add(new JLabel("Total Litros:"));
        preco.add(Lsoma);
        preco.add(new JLabel("Total R$:"));
        preco.add(LtotL);
        preco.add(new JLabel("Preço do litro:"));
        preco.add(pl);
        preco.add(new JLabel("Descontos R$:"));
        preco.add(de);
        JPanel baixo = new JPanel(new GridLayout(2,1,4,4));
        baixo.add(preco);
        JPanel total = new JPanel();
        total.add(LtotP);
        baixo.add(total);
        add(baixo,BorderLayout.SOUTH);
        
        pl.addActionListener(e -> {
            try{
               Double.parseDouble(pl.getText());
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(RFornecedor.this, "Utilize o \".\" no lugar da \",\" ", 
                            "Falha no valor do preço do litro", JOptionPane.PLAIN_MESSAGE);
                return;
            }
            conta();
            de.requestFocus();
        });
        de.addActionListener(e -> {
            try{
               Double.parseDouble(de.getText());
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(RFornecedor.this, "Utilize o \".\" no lugar da \",\" ", 
                            "Falha no valor do desconto", JOptionPane.PLAIN_MESSAGE);
                return;
            }
            conta();
            pl.requestFocus();
        });
        
        arrumardata();//para arrumar tudo na verdade   
    }
    
    private void arrumardata(){
        for(int i = 28;i <= 32;i++){
            qdia[i-1].setVisible(true);
            qld[i-1].setVisible(true);
        }
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR,2022+anos.getSelectedIndex());
        instance.set(Calendar.MONTH, meses.getSelectedIndex());
        instance.set(Calendar.DAY_OF_MONTH, instance.getActualMaximum(Calendar.DAY_OF_MONTH));
        int day = instance.get(Calendar.DAY_OF_MONTH);
        for(int i = day+1;i <= 32;i++){
            qdia[i-1].setVisible(false);
            qld[i-1].setVisible(false);
        }
        AqntLeite();
        revalidate();
    }
    
    private void AqntLeite(){
        for(int i = 0;i < 31;i++){
            qntLeite[i] = 0;
        }
        
        String day;
        if((meses.getSelectedIndex()+1) < 10){
            day = "0"+(meses.getSelectedIndex()+1);
        }else{
            day = ""+(meses.getSelectedIndex()+1);
        }
        
        l.setDia(an[anos.getSelectedIndex()]+"-"+day+"-");
        selectLinha = Acesso.selectQntLeite(l);
        for(int i = 0;i < selectLinha.size();i++){
            String[] tokens = selectLinha.get(i).getDia().split("-");
            int dia = Integer.parseInt(tokens[2])-1;
            qntLeite[dia] =  selectLinha.get(i).getQnt_leite();
        }
        
        for(int i = 0;i < 31;i++){
            qld[i].setText(qntLeite[i]+"");
        }
        if(selectLinha.size() > 0) pl.setText(selectLinha.get(0).getPreco()+"");
        
        conta();
    }
    
    private void conta(){
       soma = 0;
       for(int i = 0; i < 31;i++) soma += qntLeite[i];
       totL = (Math.round(soma*Double.parseDouble(pl.getText())*100.0))/100.0;
       totP = (Math.round((totL-(Double.parseDouble(de.getText())))*100.0))/100.0;
       Lsoma.setText(""+soma);
       LtotL.setText(""+totL);
       LtotP.setText("TOTAL A PAGAR: "+totP+" R$");
       revalidate();
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(500,500);
    }
}
