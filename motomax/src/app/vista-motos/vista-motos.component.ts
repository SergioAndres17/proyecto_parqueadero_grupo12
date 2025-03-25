import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { Moto } from '../ven-motos/ven-moto';
import { venMotosService } from '../ven-motos/ven-motos.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-vista-motos',
  standalone: true,
  imports: [CommonModule, RouterModule, RouterOutlet],
  templateUrl: './vista-motos.component.html',
  styleUrl: './vista-motos.component.css'
})
export class VistaMotosComponent implements OnInit{

  moto: Moto[];

  constructor(private venMotoService : venMotosService) {

  }
  ngOnInit(): void {
  this.venMotoService.getVenMotos().subscribe(
  Moto => this.moto = Moto)
  }

  

  //invocando el método de eliminar
  delete(venMoto:Moto):void{
    Swal.fire({
      title: "Esta segur@?",
      text: `Seguro deseas eliminar el registro: ${venMoto.modelo} !`,
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Si, Eliminar!"
    }).then((result) => {
      if (result.isConfirmed) {
        this.venMotoService.delete(venMoto.idMoto).subscribe(
          response=>{
            this.moto = this.moto.filter(tar=> tar !== venMoto)
            Swal.fire({
              title: "Borrada!",
              text: `Tu registro ha sido eliminado: ${venMoto.modelo}`,
              icon: "success"
            });
          }
        )
      }
    });
  }


}
