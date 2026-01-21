import { Component, inject } from '@angular/core';
import { Producto } from '../producto';
import { ProductoServicio } from '../producto.servicio';
import { Router } from '@angular/router';

@Component({
  selector: 'app-producto-lista',
  imports: [],
  templateUrl: './producto-lista.html'
})
export class ProductoLista {
  productos!: Producto[];

  private productoServicio = inject(ProductoServicio);
  private enrutador = inject(Router);

  ngOnInit() {
    this.obtenerProductos();
  }

  private obtenerProductos(): void {
    this.productoServicio.obtenerProductosLista().subscribe(
      {
        next: (datos) => {
          this.productos = datos;
        },
        error: (error) => {
          console.error("Error al obtener los productos: ", error)
        }
      }
    );
  }

  editarProducto(id: number) {
    this.enrutador.navigate(['editar-producto', id])
  }

   agregarStock(id: number) {
    this.enrutador.navigate(['agregar-stock', id])
  }

   quitarStock(id: number) {
    this.enrutador.navigate(['quitar-stock', id])
  }

  eliminarProducto(id:number) {
    this.productoServicio.eliminarProducto(id).subscribe({
      next: (datos) => this.obtenerProductos(),
      error: (errores) => console.log(errores)
    });
  }

}
