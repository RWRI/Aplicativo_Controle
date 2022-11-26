//Desenvolvedor: Ryan Wyllyan Ribeiro Inacio
//Painel Histrograma com as quantidades de leite

package applb;

import BancoDados.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Histograma extends JPanel {
        ArrayList<Integer> qnt = new ArrayList<>();

    public Histograma(){
        repaint();
    }

    public void setDados(ArrayList<LinhaLeite> dados){
        qnt.clear();
        for(int i = 0; i < dados.size();i++){
            qnt.add(dados.get(i).getQnt_leite());
        }
        repaint();
    }

    public void setQnt(ArrayList<Integer> qntDias){
        qnt.clear();
        for(Integer i : qntDias) qnt.add(i);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        this.setBackground(Color.WHITE);

        g.setFont(new Font("Monospace", Font.BOLD, 12));

        Random random = new Random();

        g.drawLine(25, 25, 25, 500);
        g.drawString("Quantidade em litros", 30, 20);
        g.drawLine(25, 480, 720, 480);
        g.drawString("Dias do mês", 730, 482);

        int max = 0, min = 100000000;//maior frequencia
        for(int i = 0; i < qnt.size(); i++){
            if(qnt.get(i) < min)
                min = qnt.get(i);
            if(qnt.get(i) > max)
                max = qnt.get(i);
        }
        max++;
        if(min > 5) min -= 5;
        min = (min/10)*10;
        max -= min;

        for(int i = 0; i < qnt.size(); i++){
            g.setColor(new Color(random.nextInt(256), random.nextInt(256),random.nextInt(256)));
            g.fillRect(35 + 22*i, 480 - (455/max)*(qnt.get(i)-min),22,(455/max)*(qnt.get(i)-min));
            g.setColor(Color.BLACK);
            g.drawLine(20, (480 - 455/max*(qnt.get(i)-min)), 30,(480 - 455/max*(qnt.get(i)-min)));
            g.drawLine(45 + 22*i, 475, 45 + 22*i, 485);
            g.drawString(""+ (i+1), 40+22*i, 500);
            g.drawString(""+ qnt.get(i), 2, (480 - 455/max*(qnt.get(i)-min)));
        }
    }

    public Dimension getPreferredSize(){
        return new Dimension(750,600);
    }
}
