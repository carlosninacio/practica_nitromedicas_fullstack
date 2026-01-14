import { Component, signal } from '@angular/core';
import { ProductoLista } from "./producto-lista/producto-lista";

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  imports: [ProductoLista]
})
export class App {
  protected readonly title = signal('nitromedicas-app');
}
