//Desenvolvedor: Ryan Wyllyan Ribeiro Inacio
//Classe de conexão com o Banco de Dados

package BancoDados;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.*;
import javax.swing.*;

public class Conexao extends JPanel {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/laticiniobenata";
    private static final String user = "lb";
    private static final String pass = "Benata";
    
    public Conexao(){};
    
    public void messagem(){
        JOptionPane.showMessageDialog(Conexao.this, "Problemas ao conectar com o banco de dados", 
                        "Conexão não realizada", JOptionPane.PLAIN_MESSAGE);
    }
    
    public static Connection openConnection(){
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Conexao c = new Conexao();
            c.messagem();
            throw new RuntimeException("Erro na conexão:",ex);
        }
    }
    
    public static void closeConnection(Connection con){
        try {
            if(con != null) con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt){
        closeConnection(con);
        
        try {
            if(stmt != null) stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        closeConnection(con, stmt);
        
        try {
            if(rs != null) rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
}
