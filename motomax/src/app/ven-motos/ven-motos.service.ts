import { HttpClient, HttpHeaders } from "@angular/common/http"
import { Injectable } from "@angular/core"
import { map } from "rxjs"
import { Observable } from "rxjs/internal/Observable"
import { Moto } from "./ven-moto"

@Injectable({
    providedIn: 'root'
})
export class venMotosService {

    private urlEndpoint:string="http://localhost:8080/api"

    private httpHeaders = new HttpHeaders({'Content-Type':'application/json'})

    constructor(private http: HttpClient) {}

    getVenMotos():Observable<Moto[]>{
        
        return this.http.get(this.urlEndpoint+"/moto").pipe(
            map((response)=> response as Moto[])
        )
    }
    create(moto:Moto):Observable<Moto>{
        return this.http.post<Moto>(this.urlEndpoint+"/moto", moto, {headers:this.httpHeaders})
    }

    getVenMoto(idMoto: any):Observable<Moto>{
        return this.http.get<Moto>(`${this.urlEndpoint+"/moto"}/${idMoto}`)
    }

    update(moto: Moto):Observable<Moto>{
        return this.http.put<Moto>(`${this.urlEndpoint+"/moto"}/${moto.idMoto}`, moto, {headers:this.httpHeaders})
    }

    delete(idMoto:number):Observable<Moto>{
        return this.http.delete<Moto>(`${this.urlEndpoint+"/moto"}/${idMoto}`, {headers:this.httpHeaders})
    }



    
}