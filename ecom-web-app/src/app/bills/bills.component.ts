import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-bills',
  templateUrl: './bills.component.html',
  styleUrls: ['./bills.component.css']
})
export class BillsComponent implements OnInit {
 bills: any;
 customerId!:number;
  constructor(private http:HttpClient,private router:Router,private route:ActivatedRoute ) {
    this.customerId=route.snapshot.params['customerId'];
  }

  ngOnInit(): void {
    this.http.get("http://localhost:8888/BILLING-SERVICE/bills/search/byCustomerId?projection=fullbill&customerid="+this.customerId)
      .subscribe({ next : (data)=>{
          this.bills= data;
        },
        error : ()=>{}
      });

  }

  getBillDetails(b: any) {
    this.router.navigateByUrl("/bill-details/"+b.id);
  }
}
