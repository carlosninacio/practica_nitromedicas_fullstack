import { Component, signal } from '@angular/core';
import { ProductoLista } from "./producto-lista/producto-lista";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  imports: [RouterLink, RouterLinkActive, RouterOutlet]
})
export class App {
  protected readonly title = signal('nitromedicas-app');
}
