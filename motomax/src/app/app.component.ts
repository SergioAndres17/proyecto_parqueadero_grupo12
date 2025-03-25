import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from "./header/header.component";
import { FooterComponent } from "./footer/footer.component";
import { pantallaInicialComponent } from './pantalla_inicial/pantalla.inicial.component';
import { clienteComponent } from './cliente/cliente.component';
import { SportComponent } from "./motos/sport/sport.component";
import { RegistrosClientesComponent } from './registros-clientes/registros-clientes.component';
import { venMotoComponent } from './ven-motos/ven-motos.component';
import { VistaMotosComponent } from './vista-motos/vista-motos.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, FooterComponent, pantallaInicialComponent, clienteComponent, SportComponent, RegistrosClientesComponent, venMotoComponent, VistaMotosComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
