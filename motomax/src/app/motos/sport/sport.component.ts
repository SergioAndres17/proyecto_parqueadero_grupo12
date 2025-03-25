import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from "../../header/header.component";
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-sport',
  standalone: true,
  imports: [RouterModule, HeaderComponent, RouterLink],
  templateUrl: './sport.component.html',
  styleUrl: './sport.component.css'
})
export class SportComponent {

}
