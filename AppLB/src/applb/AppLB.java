//Desenvolvedor: Ryan Wyllyan Ribeiro Inacio
//Janela Principal

package applb;

import javax.swing.*;
import java.awt.*;

public class AppLB extends JFrame{
    
    private final JDesktopPane JP = new JDesktopPane();
    
    public AppLB(){
        super("Latícinio Benata");
       
        logo();
        menu();
        
        add(JP);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
    }
    
    public void logo(){
        Icon imagem = new ImageIcon("logo.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage("icone.png"));
        JLabel lab = new JLabel();
        lab.setIcon(imagem);
        double alt = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        double larg = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        int altura = (int) alt;
        int largura = (int) larg;

        lab.setBounds(0,0,largura,altura);

        JP.add(lab);
    }
    
    public void menu(){
         //definindo menu
        JMenuBar barra = new JMenuBar();
        setJMenuBar(barra);
        
        JMenu arq = new JMenu("Arquivo");
        barra.add(arq);
        JMenuItem itemSobre = new JMenuItem ( "Sobre ...");
        arq.add(itemSobre);
        itemSobre.addActionListener( e -> {
                JOptionPane.showMessageDialog(AppLB.this,
                "                 APLICATIVO LATÍCINIO BENATA\n\n"
                        +"            Aplicativo  Desenvolvido e Projetado\n"
                        +"para discilplina de Engenharia de Software 2022.2\n\n"
                        +"Desenvolvedores:\n"
                        +"   Bárbara de Alves Paiva Barbosa\n"
                        +"   Luiz Guilherme de Godoy Gerulaitis\n"
                        +"   Maíssa Maniezzo de Oliveira\n"
                        +"   Maria Clara Martins Santana\n"
                        +"   Ryan Wyllyan Ribeiro Inácio\n",
                "Sobre", JOptionPane.PLAIN_MESSAGE);
        });
        arq.addSeparator();
        JMenuItem sair = new JMenuItem ("Sair");
        sair.addActionListener( e->System.exit(0) );
        arq.add(sair);
        
        JMenu fornecedores = new JMenu("Fornecedores");
        barra.add(fornecedores);
        JMenuItem cfornecedor = new JMenuItem ( "Cadastrar Fornecedor");
        fornecedores.add(cfornecedor);
        cfornecedor.addActionListener(e ->{
            JInternalFrame forne = new JInternalFrame("Cadastro de novo fornecedor", false, true, false, false);
            forne.add(new CFornecedor());
            forne.pack();
            JP.add(forne);
            forne.setVisible(true);
        });
        JMenuItem edfornecedores = new JMenuItem ( "Editar Fornecedores Cadastrados");
        fornecedores.add(edfornecedores);
        edfornecedores.addActionListener(e ->{
            JInternalFrame forne = new JInternalFrame("Fornecedores Cadastrados", true, true, false, false);
            forne.add(new EDFornecedores());
            forne.pack();
            JP.add(forne);
            forne.setVisible(true);
        });
        JMenuItem emfornecedores = new JMenuItem ( "Excluir Fornecedores Cadastrados");
        fornecedores.add(emfornecedores);
        emfornecedores.addActionListener(e ->{
            JInternalFrame forne = new JInternalFrame("Fornecedores Cadastrados", true, true, false, false);
            forne.add(new EXFornecedores());
            forne.pack();
            JP.add(forne);
            forne.setVisible(true);
        });
        JMenuItem rfornecedor = new JMenuItem ( "Relações Mensais Fornecedores");
        fornecedores.add(rfornecedor);
        rfornecedor.addActionListener(e ->{
            JInternalFrame forne = new JInternalFrame("Relações Mensais Fornecedores", true, true, true, false);
            forne.add(new PFornecedor());
            forne.pack();
            JP.add(forne);
            forne.setVisible(true);
        });
        
        JMenu linha = new JMenu("Linha de Leite");
        barra.add(linha);
        JMenuItem linhaleite = new JMenuItem ( "Cadastrar Linha de Leite");
        linha.add(linhaleite);
        linhaleite.addActionListener(e ->{
            JInternalFrame forne = new JInternalFrame("Cadastro Linha de Leite", true, true, true, false);
            forne.add(new CLinhaLeite());
            forne.pack();
            JP.add(forne);
            forne.setVisible(true);
        });
        JMenuItem edlinhaleite = new JMenuItem ( "Editar dados Linha de Leite");
        linha.add(edlinhaleite);
        edlinhaleite.addActionListener(e ->{
            JInternalFrame forne = new JInternalFrame("Dados da Linha de Leite Cadastrados", true, true, false, false);
            forne.add(new TedLinhaLeite());
            forne.pack();
            JP.add(forne);
            forne.setVisible(true);
        });
        JMenuItem exlinhaleite = new JMenuItem ( "Exluir dados Linha de Leite");
        linha.add(exlinhaleite);
        exlinhaleite.addActionListener(e ->{
            JInternalFrame forne = new JInternalFrame("Dados da Linha de Leite Cadastrados", true, true, false, false);
            forne.add(new TexLinhaLeite());
            forne.pack();
            JP.add(forne);
            forne.setVisible(true);
        });
        JMenuItem oldlinhaleite = new JMenuItem ( "Ver dados Linha de Leite");
        linha.add(oldlinhaleite);
        oldlinhaleite.addActionListener(e ->{
            JInternalFrame forne = new JInternalFrame("Dados da Linha de Leite Cadastrados", true, true, false, false);
            forne.add(new ToldLinhaLeite());
            forne.pack();
            JP.add(forne);
            forne.setVisible(true);
        });
        JMenuItem rlinhaleite = new JMenuItem ( "Relacao Mensal Linha de Leite");
        linha.add(rlinhaleite);
        rlinhaleite.addActionListener(e ->{
            JInternalFrame forne = new JInternalFrame("Quantidades e Preços Mensais da Linha de Leite", true, true, false, false);
            forne.add(new RLinhaLeite());
            forne.pack();
            JP.add(forne);
            forne.setVisible(true);
        });

        JMenu graficos = new JMenu("Gráficos");
        barra.add(graficos);
        JMenuItem grafForne = new JMenuItem ( "Gŕafico por fornecedor");
        graficos.add(grafForne);
        grafForne.addActionListener(e ->{
            JInternalFrame graf = new JInternalFrame("Gráfico por fornecedor", true, true, true, false);
            graf.add(new GraficosForne());
            graf.pack();
            JP.add(graf);
            graf.setVisible(true);
        });
        JMenuItem grafMes = new JMenuItem ( "Gŕafico por mês");
        graficos.add(grafMes);
        grafMes.addActionListener(e ->{
            JInternalFrame graf = new JInternalFrame("Gráfico por mês", true, true, true, false);
            graf.add(new GraficosMes());
            graf.pack();
            JP.add(graf);
            graf.setVisible(true);
        });
    }
    
    public static void main(String[] args) {
        AppLB jan = new AppLB();
        jan.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
    
}
