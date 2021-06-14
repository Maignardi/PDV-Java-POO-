/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Clientes;
import model.bean.Vendas;

/**
 *
 * @author mmaig
 */
public class ClienteDao {
    public void create(Clientes c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO clientes(nome)VALUES(?)");
            
            stmt.setString(1, c.getNome());
            
            
            stmt.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error ao salvar: "+ ex);
            
        }finally{
            ConnectionFactory.CloseConnection(con, stmt);
        }
    }
    public List<Clientes> read(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rc = null;
        
        List<Clientes> clientes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM clientes");
            rc = stmt.executeQuery();
            
            while(rc.next()){
                
                Clientes cliente = new Clientes();
                cliente.setId(rc.getInt("id"));
              
                cliente.setNome(rc.getString("nome"));
                clientes.add(cliente);
               
               
                
            
            }
            
                    
                    } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.CloseConnection(con, stmt, rc);
        }
        
        return clientes;
    
    }
    public void update(Clientes c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE clientes SET nome = ? WHERE id = ?");
            
            stmt.setString(1, c.getNome());
            stmt.setInt(2, c.getId());
            
            stmt.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error ao Atualizar: "+ ex);
            
        }finally{
            ConnectionFactory.CloseConnection(con, stmt);
        }
    }
    public void delete(Clientes c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM clientes WHERE id = ?");
            
            
            stmt.setInt(1, c.getId());
            
            stmt.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "Deletado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error ao Deletar: "+ ex);
            
        }finally{
            ConnectionFactory.CloseConnection(con, stmt);
        }
    }
}

