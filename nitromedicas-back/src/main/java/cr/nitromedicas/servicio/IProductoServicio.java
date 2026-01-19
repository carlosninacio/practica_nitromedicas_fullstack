package cr.nitromedicas.servicio;

import cr.nitromedicas.modelo.Producto;

import java.util.List;

public interface IProductoServicio {
    List<Producto> listarProductos();
    Producto buscarProductoPorId(Integer idProducto);
    Producto guardarProducto(Producto producto);
    void eliminarProductoPorId(Integer idProducto);
    void agregarExistencia(Integer idProducto, int cantidad);
    void quitarExistencia(Integer idProducto, int cantidad);
}