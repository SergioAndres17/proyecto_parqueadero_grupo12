import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import Swal from 'sweetalert2';
import { ClientesService } from '../cliente/cliente.service';
import { Cliente } from '../cliente/cliente';

@Component({
  selector: 'app-registros-clientes',
  standalone: true,
  imports: [CommonModule, RouterModule, RouterOutlet],
  templateUrl: './registros-clientes.component.html',
  styleUrl: './registros-clientes.component.css'
})

export class RegistrosClientesComponent implements OnInit{

  cliente: Cliente[];

  constructor(private clienteService: ClientesService) {

  }
  ngOnInit(): void {
  this.clienteService.getClientes().subscribe(
   cliente => this.cliente = cliente)
  }

  

  //invocando el método de eliminar
  delete(cliente:Cliente):void{
    Swal.fire({
      title: "Esta segur@?",
      text: `Seguro deseas eliminar el cliente: ${cliente.nombre} !`,
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Si, Eliminar!"
    }).then((result) => {
      if (result.isConfirmed) {
        this.clienteService.delete(cliente.idCliente).subscribe(
          response=>{
            this.cliente = this.cliente.filter(tar=> tar !== cliente)
            Swal.fire({
              title: "Borrada!",
              text: `Tu cliente ha sido eliminado: ${cliente.nombre}`,
              icon: "success"
            });
          }
        )
      }
    });
  }

}
