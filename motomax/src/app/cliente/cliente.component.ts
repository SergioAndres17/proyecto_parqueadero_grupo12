import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink, RouterModule } from '@angular/router';
import { ClientesService } from './cliente.service';
import { Cliente } from './cliente';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-cliente',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterModule, RouterLink],
  templateUrl: './cliente.component.html',
  styleUrl: './cliente.component.css'
})

export class clienteComponent implements OnInit{
  public cliente: Cliente = new Cliente()

  titulo:string="Formulario de Ingreso de Clientes"

  constructor(private clienteService: ClientesService, private router: Router,
    private activatedRouted: ActivatedRoute
  ){}
  
  ngOnInit(){
    this.cargarCliente()
  }

  //cargar la tarea actual
  cargarCliente(): void{
    this.activatedRouted.params.subscribe(params => {
      let idCliente = params['idCliente']
      if(idCliente){
        this.clienteService.getCliente(idCliente).subscribe( (cliente) => this.cliente = cliente)
      }
    })
  }

  //Actualizar tarea
  update():void{
    this.clienteService.update(this.cliente).subscribe(Cliente=> {
      this.router.navigate(['/cliente'])
      Swal.fire('cliente actualizado', `: cliente: ${Cliente.nombre} Actualizada con éxito!`, 'success')
    })
  }


  public create():void{
  //console.log("clicked");
  console.log(this.cliente);

  this.clienteService.create(this.cliente).subscribe(cliente =>
    {this.router.navigate(["/cliente"])
      Swal.fire('Nuevo cliente', `cliente: ${cliente.nombre} creado con éxito`, 'success')
    }
  );
  }
}






















