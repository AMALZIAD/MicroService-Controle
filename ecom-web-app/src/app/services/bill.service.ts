import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Bill} from "../model/bill.model";
import {ActivatedRoute} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class BillService {
  bills !: Bill[];

  constructor(private http: HttpClient) {}

  public getBill(customerId:number):Observable<Bill[]>{
    console.log("hello from bill service");
    return this.http.get<Bill[]>("http://localhost:8888/BILLING-SERVICE/byCustomerId/"+customerId)

  }
  public getBillDetails(billId:number):Observable<Bill>{
    console.log("hello from bill service");
    return this.http.get<Bill>("http://localhost:8888/BILLING-SERVICE/fullbill/"+billId)

  }
}
