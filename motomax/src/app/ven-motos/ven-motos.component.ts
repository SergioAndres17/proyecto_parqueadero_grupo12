import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, RouterLink, RouterOutlet, Router, RouterModule } from '@angular/router';
import { Moto } from './ven-moto';
import { venMotosService } from './ven-motos.service';
import Swal from 'sweetalert2';



@Component({
  selector: 'app-ven-motos',
  standalone: true,
  imports: [RouterOutlet, RouterLink, FormsModule, CommonModule, RouterModule],
  templateUrl: './ven-motos.component.html',
  styleUrl: './ven-motos.component.css'
})
export class venMotoComponent implements OnInit{
  public moto: Moto = new Moto()

  titulo:string="Formulario de Ingreso de motos"


  constructor(private venmotoService: venMotosService, private router: Router,
    private activatedRouted: ActivatedRoute
  ){}
  
  ngOnInit(){
    this.cargarMoto()
  }

  //cargar la tarea actual
  cargarMoto(): void{
    this.activatedRouted.params.subscribe(params => {
      let idMoto = params['idMoto']
      if(idMoto){
        this.venmotoService.getVenMoto(idMoto).subscribe( (Moto) => this.moto = Moto)
      }
    })
  }

  //Actualizar tarea
  update():void{
    this.venmotoService.update(this.moto).subscribe(Moto=> {
      this.router.navigate(['/venMotos'])
      Swal.fire('registro moto', `: moto: ${Moto.modelo} Actualizado con éxito!`, 'success')
    })
  }


  public create():void{
  //console.log("clicked");
  console.log(this.moto);

  this.venmotoService.create(this.moto).subscribe(venMoto =>
    {this.router.navigate(["/venMotos"])
      Swal.fire('Nueva moto', `moto: ${venMoto.modelo} creado con éxito`, 'success')
    }
  );
  }
  
}