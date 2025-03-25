import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { pantallaInicialComponent } from './pantalla_inicial/pantalla.inicial.component';
import { SportComponent } from './motos/sport/sport.component';
import { InicioSesionComponent } from './inicio-sesion/inicio-sesion.component';
import { clienteComponent } from './cliente/cliente.component';
import { RegistrosClientesComponent } from './registros-clientes/registros-clientes.component';
import { Component, NgModule } from '@angular/core';
import { venMotoComponent } from './ven-motos/ven-motos.component';
import { VistaMotosComponent } from './vista-motos/vista-motos.component';



export const routes: Routes = [
    {path:'', redirectTo:'/cliente', pathMatch:'full'},
    {path:'header', component: HeaderComponent},
    {path:'footer', component: FooterComponent},
    {path:'pantallaInicial', component: pantallaInicialComponent},
    {path: 'motos/sport', component: SportComponent },
    {path: 'inicio-sesion', component: InicioSesionComponent },
    {path: 'registro', component: clienteComponent },
    {path: 'registros-clientes', component: RegistrosClientesComponent},
    {path: 'cliente/:id', component: clienteComponent},
    {path: 'cliente', component: clienteComponent},
    {path: 'ven-motos', component: venMotoComponent},
    {path: 'ven-motos/:idMoto', component: venMotoComponent},
    {path: 'registros-motos', component: VistaMotosComponent}
];
