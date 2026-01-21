package cr.nitromedicas.servicio;

import cr.nitromedicas.modelo.Producto;

import java.util.List;

public interface IProductoServicio {
    List<Producto> listarProductos();
    Producto buscarProductoPorId(Integer idProducto);
    Producto guardarProducto(Producto producto);
    void eliminarProductoPorId(Integer idProducto);
    Producto agregarExistencia(Integer idProducto, int cantidad);
    Producto quitarExistencia(Integer idProducto, int cantidad);
}