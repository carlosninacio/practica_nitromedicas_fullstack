package cr.nitromedicas.servicio;

import cr.nitromedicas.modelo.Producto;
import cr.nitromedicas.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio implements IProductoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Override
    public List<Producto> listarProductos() {
        return this.productoRepositorio.findAll();
    }

    @Override
    public Producto buscarProductoPorId(Integer idProducto) {
        return this.productoRepositorio.findById(idProducto).orElse(null);
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return this.productoRepositorio.save(producto);
    }

    @Override
    public void eliminarProductoPorId(Integer idProducto) {
        this.productoRepositorio.deleteById(idProducto);
    }

    @Override
    public Producto agregarExistencia(Integer idProducto, int cantidad) {
        Producto producto = this.productoRepositorio.findById(idProducto).orElse(null);
        assert producto != null;
        producto.setExistencia(producto.getExistencia() + cantidad);
        return this.productoRepositorio.save(producto);
    }

    @Override
    public Producto quitarExistencia(Integer idProducto, int cantidad) {
        Producto producto = this.productoRepositorio.findById(idProducto).orElse(null);
        assert producto != null;
        producto.setExistencia(producto.getExistencia() - cantidad);
        return this.productoRepositorio.save(producto);
    }
}