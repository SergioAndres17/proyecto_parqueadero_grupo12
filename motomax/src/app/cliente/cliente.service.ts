import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, Observable, of } from "rxjs";
import { Cliente } from "./cliente";
import { response } from "express";

@Injectable({
    providedIn: 'root'
})
export class ClientesService {

    private urlEndpoint:string="http://localhost:8080/api"

    private httpHeaders = new HttpHeaders({'Content-Type':'application/json'})

    constructor(private http: HttpClient) {}

    getClientes():Observable<Cliente[]>{
        
        return this.http.get(this.urlEndpoint+"/cliente").pipe(
            map((response)=> response as Cliente[])
        )
    }
    create(cliente:Cliente):Observable<Cliente>{
        return this.http.post<Cliente>(this.urlEndpoint+"/cliente", cliente, {headers:this.httpHeaders})
    }

    getCliente(idCliente: any):Observable<Cliente>{
        return this.http.get<Cliente>(`${this.urlEndpoint+"/cliente"}/${idCliente}`)
    }

    update(cliente: Cliente):Observable<Cliente>{
        return this.http.put<Cliente>(`${this.urlEndpoint+"/cliente"}/${cliente.idCliente}`, cliente, {headers:this.httpHeaders})
    }

    delete(idCliente:number):Observable<Cliente>{
        return this.http.delete<Cliente>(`${this.urlEndpoint+"/cliente"}/${idCliente}`, {headers:this.httpHeaders})
    }



    
}