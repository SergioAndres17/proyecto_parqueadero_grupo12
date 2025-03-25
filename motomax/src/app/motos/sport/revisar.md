export class clienteComponent implements OnInit{

  clientes: Cliente[];

  constructor(private clientesService: ClientesService){
    Cliente
    this.clientes = [];
  }

  ngOnInit(): void {
      this.clientesService.getClientes().subscribe(
        clientes => this.clientes = clientes
      )
  }

  delete(cliente:Cliente):void{
    
        this.clientesService.delete(cliente.idCliente).subscribe(
          response=>{
            this.clientes = this.clientes.filter(tar=> tar !== cliente)
            
          }
        );
  }
}
