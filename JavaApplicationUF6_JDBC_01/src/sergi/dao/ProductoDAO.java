
package sergi.dao;

import sergi.model.Producto;

public interface ProductoDAO {
    public void insert(Producto producto);
    public void update(Producto producto);
    public void delete(Integer id);
    public Producto read(Integer id);
}
