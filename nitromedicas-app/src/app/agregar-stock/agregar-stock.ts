import { Component, inject, OnInit } from '@angular/core';
import { Producto } from '../producto';
import { ProductoServicio } from '../producto.servicio';
import { Router, ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-agregar-stock',
  imports: [FormsModule],
  templateUrl: './agregar-stock.html'
})
export class AgregarStock implements OnInit {

  producto: Producto = new Producto();
  cantidad!: number;

  private productoServicio = inject(ProductoServicio);
  private enrutador = inject(Router);
  private ruta = inject(ActivatedRoute);

  ngOnInit(): void {
    const id = Number(this.ruta.snapshot.paramMap.get('id'));
    this.obtenerProducto(id);
  }

  obtenerProducto(id: number) {
    this.productoServicio.obtenerProductoPorId(id).subscribe({
      next: (datos) => {
        this.producto = datos;
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

onSubmit() {
  console.log('CLICK EN AGREGAR'); // 👈 CLAVE
  this.productoServicio
    .agregarStock(this.producto.idProducto, this.cantidad)
    .subscribe({
      next: () => {
        console.log('RESPUESTA OK');
        this.irListaProductos();
      },
      error: (error: any) => {
        console.error('ERROR PATCH', error);
      }
    });
}


  irListaProductos() {
    this.enrutador.navigate(['/productos']);
  }
}
