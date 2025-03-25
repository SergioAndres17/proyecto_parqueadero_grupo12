import { Component } from '@angular/core';
import { RouterLink, RouterModule } from '@angular/router';

@Component({
  selector: 'app-pantalla-inicial',
  standalone: true,
  imports: [RouterModule, RouterLink],
  templateUrl: './pantalla.inicial.component.html',
  styleUrl: './pantalla.inicial.component.css'
})
export class pantallaInicialComponent {
  title = 'motomax';
}
