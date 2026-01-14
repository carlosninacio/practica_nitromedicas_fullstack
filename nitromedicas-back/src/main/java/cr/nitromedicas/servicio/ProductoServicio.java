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
        return List.of();
    }

    @Override
    public Producto buscarProductoPorId(Integer idProducto) {
        return null;
    }

    @Override
    public void guardarProducto(Producto producto) {

    }

    @Override
    public void eliminarProductoPorId(Integer idProducto) {

    }

    @Override
    public void agregarExistencia(Integer idProducto, int cantidad) {

    }

    @Override
    public void quitarExistencia(Integer idProducto, int cantidad) {

    }
}