import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import * as http from "http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  public customers : any;
  constructor(private  http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.http.get("http://localhost:8888/CUSTOMER-SERVICE/customers/?projection=fullcustomer")
      .subscribe({ next : (data)=>{
          this.customers= data;
        },
        error : ()=>{}
      });
  }


  getBills(c: any) {
    this.router.navigateByUrl("/bills/"+c.id);
  }
}
