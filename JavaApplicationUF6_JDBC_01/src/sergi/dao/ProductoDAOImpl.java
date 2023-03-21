

package sergi.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import sergi.model.Producto;
import java.sql.*;

public class ProductoDAOImpl implements ProductoDAO{
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/inventario?useSSL=false";
    static final String DB_USR = "root";
    static final String DB_PWD = "";
    

    private void registerDriver(){
        try{
            Class.forName(JDBC_DRIVER);
        } catch(ClassNotFoundException ex){
            System.err.println("ERROR");
            ex.printStackTrace();
        }
    }
    @Override
    public void insert(Producto producto){
        Connection conn = null;
        try{
            registerDriver();
            conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
            Statement stmt = conn.createStatement();
            
            stmt.executeUpdate("insert into producto values ("
                    + producto.getId() + ",'"
                    + producto.getNombre() + "','"
                    + producto.getPrecio() + "');");
        } catch (SQLException ex){
            throw new RuntimeException(ex);
            
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    @Override
    public void update(Producto producto) {
        Connection conn = null;
        try {
            registerDriver();
            conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
            try (Statement stmt = conn.createStatement()) {

                String sql = "UPDATE product SET name=?, price=? WHERE id=?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, producto.getNombre());
                statement.setDouble(2, producto.getPrecio());
                statement.setInt(3, producto.getId());
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Ha sido actualizado correctamente!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    @Override
    public void delete(Integer id) {
        Connection conn = null;
        try {
            registerDriver();
            conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
            try (PreparedStatement ps = conn.prepareStatement(
                    "Borrar el producto por id")) {
                ps.setInt(1, id);
                int rowsDeleted = ps.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Ha sido eliminado correctamente!");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public Producto read(Integer id){
        Connection conn = null;
        Producto prod = null;
        
        try{
            registerDriver();
            conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
            
            try(PreparedStatement ps = conn.prepareStatement("select * from producto where id = ?")){
                ps.setInt(1, id);
                
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        prod = new Producto(id, rs.getString("nombre"), rs.getDouble(DB_URL));
                    }
                }
            }
        } catch(SQLException ex){
            throw new RuntimeException(ex);
        }finally{
            if (conn != null){
                try{
                    conn.close();
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        }
        return prod;
    }
}
