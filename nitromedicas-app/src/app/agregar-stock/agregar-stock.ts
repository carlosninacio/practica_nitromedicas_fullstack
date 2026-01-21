import { Component, inject } from '@angular/core';
import { Producto } from '../producto';
import { ProductoServicio } from '../producto.servicio';
import { Router } from '@angular/router';

@Component({
  selector: 'app-agregar-stock',
  imports: [],
  templateUrl: './agregar-stock.html'
})
export class AgregarStock {
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
}
