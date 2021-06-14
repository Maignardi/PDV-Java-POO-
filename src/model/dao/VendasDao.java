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
import model.bean.Produto;
import model.bean.Vendas;

/**
 *
 * @author mmaig
 */
public class VendasDao {
    public void create(Vendas v){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO vendas(produto,preco,cliente,qtd)VALUES(?,?,?,?)");
            stmt.setString(1, v.getProduto());
            stmt.setDouble(2, v.getPreco());
            stmt.setString(3, v.getCliente());
            stmt.setInt(4, v.getQtd());
            
            stmt.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error ao salvar: "+ ex);
            
        }finally{
            ConnectionFactory.CloseConnection(con, stmt);
        }
    }

    public List<Vendas> read(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rv = null;
        
        List<Vendas> vendas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM vendas");
            rv = stmt.executeQuery();
            
            while(rv.next()){
                
                Vendas venda = new Vendas();
                venda.setId(rv.getInt("id"));
                venda.setProduto(rv.getString("produto"));
                venda.setCliente(rv.getString("cliente"));
                venda.setQtd(rv.getInt("qtd"));
                venda.setPreco(rv.getDouble("preco"));
                vendas.add(venda);
               
                
            
            }
            
                    
                    } catch (SQLException ex) {
            Logger.getLogger(VendasDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.CloseConnection(con, stmt, rv);
        }
        
        return vendas;
    
    }
    public void update(Vendas v){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE vendas SET produto = ?, preco = ?, cliente = ?, qtd = ? WHERE id = ?");
            stmt.setString(1, v.getProduto());
            stmt.setDouble(2, v.getPreco());
            stmt.setString(3, v.getCliente());
            stmt.setInt(4, v.getQtd());
            stmt.setInt(5, v.getId());
            
            stmt.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error ao atualizar: "+ ex);
            
        }finally{
            ConnectionFactory.CloseConnection(con, stmt);
        }
    }
    public void delete(Vendas v){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM vendas WHERE id = ?");
            
            stmt.setInt(1, v.getId());
            
            stmt.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null, "Deletado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error ao Deletar: "+ ex);
            
        }finally{
            ConnectionFactory.CloseConnection(con, stmt);
        }
    }
}
