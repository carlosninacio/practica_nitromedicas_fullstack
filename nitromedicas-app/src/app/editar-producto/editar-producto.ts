import { Component, inject } from '@angular/core';
import { Producto } from '../producto';
import { ProductoServicio } from '../producto.servicio';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-editar-producto',
  imports: [FormsModule],
  templateUrl: './editar-producto.html'
})
export class EditarProducto {
  producto: Producto = new Producto();
  id!: number;

  private productoServicio = inject(ProductoServicio);
  private ruta = inject(ActivatedRoute);

  ngOnInit() {
    this.id = this.ruta.snapshot.params['id'];
    this.productoServicio.obtenerProductoPorId(this.id).subscribe({
      next: (datos) => this.producto = datos,
      error: (errores: any) => console.log(errores)
    });
  }

  onSubmit() {
    
  }
}