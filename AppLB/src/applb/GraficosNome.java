//Desenvolvedor: Ryan Wyllyan Ribeiro Inacio
//Painel para montar cada gráfico pelo nome

package applb;

import BancoDados.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class GraficosNome extends JPanel {
    private final String[] an = {"2022","2023","2024","2025","2026","2027","2028","2029","2030"};
    private final JComboBox anos = new JComboBox(an);
    private final String[] nm = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho",
        "Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
    private final JComboBox meses = new JComboBox(nm);

    private ArrayList<LinhaLeite> selectLinha = new ArrayList<>();
    private LinhaLeite l;        
    Histograma hist = new Histograma();

    public GraficosNome(String nome){
        l = new LinhaLeite(an[anos.getSelectedIndex()]+"-"+(meses.getSelectedIndex()+1)+"-",nome,0);
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
        String day;
        if((meses.getSelectedIndex()+1) < 10){
            day = "0"+(meses.getSelectedIndex()+1);
        }else{
            day = ""+(meses.getSelectedIndex()+1);
        }
        l.setDia(an[anos.getSelectedIndex()]+"-"+day+"-");
        selectLinha = Acesso.selectQntLeite(l);
        hist.setDados(selectLinha);
    }

    public Dimension getPreferredSize(){
        return new Dimension(850,600);
    }
}
