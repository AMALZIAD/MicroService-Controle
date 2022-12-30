import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-productitems',
  templateUrl: './productitems.component.html',
  styleUrls: ['./productitems.component.css']
})
export class ProductitemsComponent implements OnInit {
  productitems: any;
  billId!:number;
  constructor(private http: HttpClient,private router:Router,private route:ActivatedRoute ) {
    this.billId=route.snapshot.params['billId'];
    console.log(this.billId);
  }

  ngOnInit(): void {
    console.log("hhhh "+this.billId);
    this.http.get("http://localhost:8888/BILLING-SERVICE/fullbill/"+this.billId)
      .subscribe({ next : (data)=>{
          this.productitems= data;
        },
        error : (err)=>{}
      });
  }

}
