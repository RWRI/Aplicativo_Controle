//Desenvolvedor: Ryan Wyllyan Ribeiro Inacio
//Painel Relação Linha de Leite

package applb;

import BancoDados.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class RLinhaLeite extends JPanel{
    private final String[] an = {"2022","2023","2024","2025","2026","2027","2028","2029","2030"};
    private final JComboBox anos = new JComboBox(an);
    private final String[] nm = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho",
        "Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
    private final JComboBox meses = new JComboBox(nm);
    private JLabel[] dia = new JLabel[31], qntLeite = new JLabel[31],
            medPreco = new JLabel[31], total = new JLabel[31];
    private JLabel sqnt = new JLabel("    0 L"), stp = new JLabel("    0.0 R$"),
            tot = new JLabel("      0.0 R$");
    private int qntdias = 31, sum;
    private double sp;
    
    public RLinhaLeite(){
        setLayout(new BorderLayout());
        
        JPanel top1 = new JPanel(new GridLayout(1,2,5,5));
        top1.add(anos);
        top1.add(meses);
        JPanel titulo = new JPanel(new GridLayout(1,4,5,5));
        titulo.add(new JLabel("Dia:"));
        titulo.add(new JLabel("Qnt Leite:"));
        titulo.add(new JLabel("Preço Médio:"));
        titulo.add(new JLabel("Total:"));
        JPanel top = new JPanel(new GridLayout(2,1,5,5));
        top.add(top1);
        top.add(titulo);
        add(top,BorderLayout.NORTH);
    
        JPanel centro = new JPanel(new GridLayout(31,4,5,5));
        String day;
        for(int i = 0;i < qntdias;i++){
            day = regulaDia();
            if(i < 9){
                day += "-0"+(i+1);
            }else{
                day += "-"+(i+1);
            }
            dia[i] = new JLabel(day);
            qntLeite[i] = new JLabel("0 L");
            medPreco[i] = new JLabel("0.0 R$");
            total[i] = new JLabel("0.0 R$");
            centro.add(dia[i]);
            centro.add(qntLeite[i]);
            centro.add(medPreco[i]);
            centro.add(total[i]);
        }
        
        JPanel scroll = new JPanel();
        scroll.setLayout(new BoxLayout(scroll, BoxLayout.Y_AXIS));
        scroll.add(centro);
        
        JScrollPane src = new JScrollPane(scroll);
        src.setPreferredSize(new Dimension(600,500));
        add(src,BorderLayout.CENTER);
        
        JPanel sul = new JPanel(new GridLayout(1,4,5,5));
        sul.add(new JLabel("       TOTAL:"));
        sul.add(sqnt);
        sul.add(stp);
        sul.add(tot);
        add(sul,BorderLayout.SOUTH);
        
        arrumardata();
        
        anos.addActionListener(e -> {
            arrumardata();
            meses.requestFocus();
        });
        meses.addActionListener(e -> {
            arrumardata();
            anos.requestFocus();
        });
       
    }
    
    private void atualiza(){
        String day;
        sum = 0;
        sp = 0.0;
        for(int i = 0; i < qntdias;i++){
            day = regulaDia();
            if(i < 9){
                day += "-0"+(i+1);
            }else{
                day += "-"+(i+1);
            }
            dia[i].setText(day);
            int qL = Acesso.qntLeiteDia(day);
            sum += qL;
            qntLeite[i].setText("    "+qL+" L");
            double mP = Acesso.medPrecoDia(day);
            mP = (Math.round(mP*100.0))/100.0;
            sp += mP;
            medPreco[i].setText("    "+mP+" R$");
            double t = qL*mP;
            t = (Math.round(t*100.0))/100.0;
            total[i].setText("      "+t+" R$");
        }
        sqnt.setText(sum+" L");
        sp /= qntdias;
        sp = (Math.round(sp*100.0))/100.0;
        stp.setText(sp+" R$");
        double tm = sp*sum;
        tm = (Math.round(tm*100.0))/100.0;
        tot.setText(tm+" R$");
        
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
    
    private void arrumardata(){
        for(int i = 28;i < 32;i++){
            dia[i-1].setVisible(true);
            qntLeite[i-1].setVisible(true);
            medPreco[i-1].setVisible(true);
            total[i-1].setVisible(true);
        }
        
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR,2022+anos.getSelectedIndex());
        instance.set(Calendar.MONTH, meses.getSelectedIndex());
        instance.set(Calendar.DAY_OF_MONTH, instance.getActualMaximum(Calendar.DAY_OF_MONTH));
        qntdias = instance.get(Calendar.DAY_OF_MONTH);
        
        for(int i = qntdias;i < 31;i++){
            dia[i].setVisible(false);
            qntLeite[i].setVisible(false);
            medPreco[i].setVisible(false);
            total[i].setVisible(false);
        }
        
        atualiza();
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(600,600);
    }
    
}
