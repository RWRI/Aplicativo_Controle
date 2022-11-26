//Desenvolvedor: Ryan Wyllyan Ribeiro Inacio
//Classe para fazer os comandos no Banco de Dados

package BancoDados;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Acesso {
        
    public static boolean insertFornecedor(Fornecedor f){
        Connection con = Conexao.openConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("insert into fornecedor values (default,?,?)");
            
            stmt.setString(1, f.getNome());
            stmt.setDouble(2, f.getPreco());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            Conexao.closeConnection(con, stmt);
        }
        
        return true;
    }
    
    public static ArrayList<Fornecedor> selectFornecedores(){
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        
        Connection con = Conexao.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("Select * from fornecedor");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                fornecedores.add(new Fornecedor(rs.getString("nome"),rs.getDouble("preco")));
            }  
        } catch (SQLException ex) {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return fornecedores;
    }
    
    public static boolean updateFornecedores(String nf,Fornecedor f){
        Connection con = Conexao.openConnection();
        PreparedStatement stmt = null, stmt1 = null;
        
        try {
            stmt = con.prepareStatement("update fornecedor set preco = ? where nome = ?");
            
            stmt.setDouble(1, f.getPreco());
            stmt.setString(2, nf);
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Conexao.closeConnection(con, stmt);
        }
        
        con = Conexao.openConnection();
        
        try {
            stmt1 = con.prepareStatement("update fornecedor set nome = ? where nome = ?");
            
            stmt1.setString(1, f.getNome());
            stmt1.setString(2, nf);
            
            stmt1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            Conexao.closeConnection(con, stmt1);
        }
        
        return true;
    }
    
    public static boolean deleteFornedor(String nf){
        Connection con = Conexao.openConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("delete from fornecedor where nome = ?");
            
            stmt.setString(1, nf);
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            Conexao.closeConnection(con, stmt);
        }
        
        return true;
    }
    
    private static boolean existe(String dia, String fornecedor){
        Connection con = Conexao.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("Select * from linhaleite where dia = ? and fornecedor = ?");
           
            stmt.setString(1, dia);
            stmt.setString(2, fornecedor);
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
                return true;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return false;
    }
    
    public static boolean insert(LinhaLeite l){
        if(existe(l.getDia(),l.getFornecedor())){
            return false;
        }
        
        Connection con = Conexao.openConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("insert into linhaleite values (default,?,?,?,?)");
            
            stmt.setString(1, l.getDia());
            stmt.setString(2, l.getFornecedor());
            stmt.setInt(3,l.getQnt_leite());
            stmt.setDouble(4,l.getPreco());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            Conexao.closeConnection(con, stmt);
        }
        
        return true;
    }
    
    public static ArrayList<LinhaLeite> selectQntLeite(LinhaLeite l){
        ArrayList<LinhaLeite> qntLeite = new ArrayList<>();
        
        Connection con = Conexao.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("Select * from linhaleite where dia like ? and fornecedor = ? order by dia");
            
            stmt.setString(1, l.getDia()+"%");
            stmt.setString(2, l.getFornecedor());
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                qntLeite.add(new LinhaLeite(rs.getString("dia"),rs.getString("fornecedor"),
                        rs.getInt("qnt_leite"),rs.getDouble("preco")));
            }  
        } catch (SQLException ex) {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return qntLeite;
    }
    
    public static boolean updateLinhaLeite(String nf,LinhaLeite l){
        Connection con = Conexao.openConnection();
        PreparedStatement stmt = null, stmt1 = null;
        
        try {
            stmt = con.prepareStatement("update linhaleite set qnt_leite = ? where fornecedor = ? and dia = ?");
            
            stmt.setInt(1, l.getQnt_leite());
            stmt.setString(2, nf);
            stmt.setString(3, l.getDia());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Conexao.closeConnection(con, stmt);
        }
        
        con = Conexao.openConnection();
        
        try {
            stmt1 = con.prepareStatement("update linhaleite set preco = ? where fornecedor = ? and dia = ?");
            
            stmt1.setDouble(1, l.getPreco());
            stmt1.setString(2, nf);
            stmt1.setString(3, l.getDia());
            
            stmt1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            Conexao.closeConnection(con, stmt1);
        }
        
        return true;
    }
    
    public static boolean deleteLinhaLeite(LinhaLeite l){
        Connection con = Conexao.openConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("delete from linhaleite where fornecedor = ? and dia = ?");
            
            stmt.setString(1, l.getFornecedor());
            stmt.setString(2, l.getDia());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            Conexao.closeConnection(con, stmt);
        }
        
        return true;
    }
    
    public static ArrayList<Fornecedor> selectFornedorLinhaLeite(String d){
        ArrayList<Fornecedor> qntLeite = new ArrayList<>();
        
        Connection con = Conexao.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("Select fornecedor from linhaleite where dia like ?"
                    + "group by fornecedor order by fornecedor");
            
            stmt.setString(1, d+"%");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                qntLeite.add(new Fornecedor(rs.getString("fornecedor"),0.0));
            }  
        } catch (SQLException ex) {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return qntLeite;
    }
    
    public static int qntLeiteDia(String dia){
        Connection con = Conexao.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("select sum(qnt_leite) from linhaleite where dia = ?");
           
            stmt.setString(1, dia);
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
                return rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return 0;
    }
    
    public static double medPrecoDia(String dia){
        Connection con = Conexao.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("select avg(preco) from linhaleite where dia = ?");
           
            stmt.setString(1, dia);
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
                return rs.getDouble(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return 0;
    }
}
