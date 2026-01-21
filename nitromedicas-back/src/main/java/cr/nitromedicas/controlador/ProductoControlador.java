package cr.nitromedicas.controlador;

import cr.nitromedicas.dto.StockDTO;
import cr.nitromedicas.modelo.Producto;
import cr.nitromedicas.servicio.ProductoServicio;
import excepcion.RecursoNoEncontradoExcepcion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("nitromedicas-app") //http://localhost:8080/nitromedicas-app
@CrossOrigin(value = "http://localhost:4200") // Puerto default Angular
public class ProductoControlador {
    private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("/productos") //http://localhost:8080/nitromedicas-app/productos
    public List<Producto> obtenerProductos() {
        List<Producto> productos = this.productoServicio.listarProductos();
        logger.info("Productos obtenidos:");
        productos.forEach(producto -> logger.info(producto.toString()));
        return productos;
    }

    @PostMapping("/productos")
    public Producto agregarProducto(@RequestBody Producto producto) {
        logger.info("Producto a agregar: " + producto);
        return this.productoServicio.guardarProducto(producto);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable int id) {
        Producto producto = this.productoServicio.buscarProductoPorId(id);
        if(producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            throw new RecursoNoEncontradoExcepcion("No se encontró el id: " + id);
        }
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable int id, @RequestBody Producto productoRecibido) {
        Producto producto = this.productoServicio.buscarProductoPorId(id);
        producto.setDescripcion(productoRecibido.getDescripcion());
        producto.setPrecio(productoRecibido.getPrecio());
        producto.setExistencia(productoRecibido.getExistencia());
        this.productoServicio.guardarProducto(producto);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable int id) {
        Producto producto= this.productoServicio.buscarProductoPorId(id);
        if (producto == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el id: " + id);
        } else {
            this.productoServicio.eliminarProductoPorId(producto.getIdProducto());
            Map<String, Boolean> respuesta = new HashMap<>();
            respuesta.put("eliminado", Boolean.TRUE);
            return ResponseEntity.ok(respuesta);
        }
    }

    @PutMapping("/agregar-stock")
    public ResponseEntity<Void> agregarStock(@RequestBody StockDTO dto) {
        productoServicio.agregarExistencia(dto.getIdProducto(), dto.getCantidad());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/quitar-stock")
    public ResponseEntity<Void> quitarStock(@RequestBody StockDTO dto) {
        productoServicio.quitarExistencia(dto.getIdProducto(), dto.getCantidad());
        return ResponseEntity.ok().build();
    }
}