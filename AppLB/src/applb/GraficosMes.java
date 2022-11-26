//Desenvolvedor: Ryan Wyllyan Ribeiro Inacio
//Painel para montar cada gráfico pelo nome

package applb;

import BancoDados.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class GraficosMes extends JPanel {
    private final String[] an = {"2022","2023","2024","2025","2026","2027","2028","2029","2030"};
    private final JComboBox anos = new JComboBox(an);
    private final String[] nm = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho",
        "Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
    private final JComboBox meses = new JComboBox(nm);

    private ArrayList<Integer> qntLeiteMes = new ArrayList<>();        
    Histograma hist = new Histograma();

    public GraficosMes(){
        setLayout(new BorderLayout(5,5));
        
        JPanel top = new JPanel(new GridLayout(2,1,2,2)), data = new JPanel(new GridLayout(1,2,20,1)),
                aux = new JPanel(), aux2 = new JPanel(), aux3 = new JPanel();
        aux.add(new JLabel("Ano:"));
        aux.add(anos);
        aux2.add(new JLabel("Mes: "));
        aux2.add(meses);
        data.add(aux);
        data.add(aux2);
        top.add(data);
        aux3.add(top);
        add(aux3,BorderLayout.NORTH);
        
        anos.addActionListener(e -> {
            meses.requestFocus();
            atualizaDados();
        });
        meses.addActionListener(e -> {
            anos.requestFocus();
            atualizaDados();
        }); 
        
        atualizaDados();
        add(hist,BorderLayout.CENTER);
    }

    public void atualizaDados(){
        String day = "2022-01-01", d;

        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR,2022+anos.getSelectedIndex());
        instance.set(Calendar.MONTH, meses.getSelectedIndex());
        instance.set(Calendar.DAY_OF_MONTH, instance.getActualMaximum(Calendar.DAY_OF_MONTH));
        int dias = instance.get(Calendar.DAY_OF_MONTH);

        qntLeiteMes.clear();

        for(int i = 1;i <= dias;i++){
            d = (i < 10) ? "-0"+i : "-"+i;
            if((meses.getSelectedIndex()+1) < 10){
                day = an[anos.getSelectedIndex()]+"-0"+(meses.getSelectedIndex()+1)+d;
            }else{
                day = an[anos.getSelectedIndex()]+"-"+(meses.getSelectedIndex()+1)+d;
            }
            qntLeiteMes.add(Acesso.qntLeiteDia(day));
        }
        hist.setQnt(qntLeiteMes);
    }

    public Dimension getPreferredSize(){
        return new Dimension(850,600);
    }
}
