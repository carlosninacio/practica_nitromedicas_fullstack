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
  productoIdSeleccionado!: number;
  cantidad!: number;

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

  onProductoSeleccionado(event: Event) {
    const select = event.target as HTMLSelectElement;
    this.productoIdSeleccionado = Number(select.value);
  }

  onCantidadChange(event: Event) {
    const input = event.target as HTMLInputElement;
    this.cantidad = Number(input.value);
  }

  agregarStock() {
    if (!this.productoIdSeleccionado || this.cantidad <= 0) {
      alert('Seleccione un producto y una cantidad válida');
      return;
    }

    this.productoServicio.agregarStock({
      idProducto: this.productoIdSeleccionado,
      cantidad: this.cantidad
    }).subscribe({
      next: () => {
        alert('Stock agregado correctamente');
      },
      error: err => {
        alert(err.error.message || 'Error al agregar stock');
      }
    });
  }
}
