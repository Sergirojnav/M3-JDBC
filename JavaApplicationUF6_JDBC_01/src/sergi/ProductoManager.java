/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sergi;

import sergi.dao.ProductoDAO;
import sergi.dao.ProductoDAOImpl;
import sergi.model.Producto;


public class ProductoManager {

    public static void main(String[] args) {
        ProductoDAO producto = new ProductoDAOImpl();
        
        //producto.insert(new Producto(200,"Pollo",10.0));
        
        producto.insert(new Producto(100, "Nachos", 1.50));
        // obtener el producto con ID = 100
        Producto p = producto.read(100);
        System.out.println(p);
        producto.update(new Producto(100, "Nachos", 1.50));
        System.out.println(p);
        // eliminar el producto con ID = 100
        producto.delete(100);
    }
    
}
