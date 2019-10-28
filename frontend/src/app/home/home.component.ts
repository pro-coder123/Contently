import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {



  clicked:any;

  constructor() { }

  ngOnInit() {

    this.clicked = false;


  this.loadScript("../../assets/js/jquery-3.3.1.min.js");
  this.loadScript("../../assets/js/jquery-migrate-3.0.1.min.js");
  this.loadScript("../../assets/js/jquery-ui.js");
  this.loadScript("../../assets/js/popper.min.js");
  this.loadScript("../../assets/js/bootstrap.min.js");
  this.loadScript("../../assets/js/owl.carousel.min.js");
  this.loadScript("../../assets/js/jquery.stellar.min.js");
  this.loadScript("../../assets/js/jquery.countdown.min.js");
  this.loadScript("../../assets/js/bootstrap-datepicker.min.js");
  this.loadScript("../../assets/js/jquery.easing.1.3.js");
  this.loadScript("../../assets/js/aos.js");
  this.loadScript("../../assets/js/jquery.fancybox.min.js");
  this.loadScript("../../assets/js/jquery.sticky.js");
  this.loadScript("../../assets/js/main.js");


  }

  public loadScript(url: string) {
    const body = <HTMLDivElement> document.body;
    const script = document.createElement('script');
    script.innerHTML = '';
    script.src = url;
    script.async = false;
    script.defer = true;
    body.appendChild(script);
  }


  fire(){

      this.clicked = true;

  }



}
