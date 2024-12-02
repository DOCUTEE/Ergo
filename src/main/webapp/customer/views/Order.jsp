<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:url value="/" var="URL"></c:url>   
<html>

<!-- Shipping info -->

<div class="container mt-5 d-flex justify-content-center">
   <div class="card p-4 mt-3">
      <div class="first d-flex justify-content-between align-items-center mb-3">
         <div class="info">
            <span class="order">Order - 4554645</span>                
         </div>           
         <img src="https://i.imgur.com/NiAVkEw.png" width="40"/>        
      </div>
      <hr>
      <div class="text d-flex justify-content-between align-items-center">
         <div>
            <span class="d-block new mb-1">Alex Dorlew - 0585281758</span>
            <span class="d-block address mb-3">672 Conaway Street Bryantiville Massachusetts 02327</span>
         </div>
         <a href="/shippingInfo" class="edit-link">
            <img src="https://cdn-icons-png.flaticon.com/512/1250/1250615.png" width="20" alt="Edit Icon">
         </a>
      </div>
   </div>
</div>

<!-- END shipping info -->

<!-- OrderItem -->

<div class="container-fluid my-5  d-flex  justify-content-center">
        <div class="card card-1">
            <div class="card-body">
                <div class="row justify-content-between mb-3">
                    <div class="col-auto"> <h6 class="color-1 mb-0 change-color">Order Item</h6> </div>
                    <div class="col-auto  "> <small>Receipt Voucher : 1KAU9-84UIL</small> </div>
                </div>
                <ul class="list-unstyled">
				    <c:forEach var="item" items="${listItems}">
				        <li class="media">
				            <div class="sq align-self-center">
				                <img class="img-fluid my-auto align-self-center mr-2 mr-md-4 pl-0 p-0 m-0" src="${item.image}" width="135" height="135" />
				            </div>
				            <div class="media-body my-auto text-right">
				                <div class="row my-auto flex-column flex-md-row">
				                    <div class="col my-auto">
				                        <h6 class="mb-0">${item.name}</h6>
				                    </div>
				                    <div class="col-auto my-auto">
				                        <small>${item.rim}</small>
				                    </div>
				                    <div class="col my-auto">
				                        <small>Size : ${item.size}</small>
				                    </div>
				                    <div class="col my-auto">
				                        <small>Qty : ${item.quantity}</small>
				                    </div>
				                    <div class="col my-auto">
				                        <h6 class="mb-0">&#8377;${item.price}</h6>
				                    </div>
				                </div>
				            </div>
				        </li>
				    </c:forEach>
				</ul>

                
                <div class="row mt-4">
                    <div class="col">
                        <div class="row justify-content-between">
                            <div class="col-auto"><p class="mb-1 text-dark"><b>Order Details</b></p></div>
                            <div class="flex-sm-col text-right col"> <p class="mb-1"><b>Total</b></p> </div>
                            <div class="flex-sm-col col-auto"> <p class="mb-1">&#8377;4,835</p> </div>
                        </div>
                        <div class="row justify-content-between">
                            <div class="flex-sm-col text-right col"><p class="mb-1"> <b>Discount</b></p> </div>
                            <div class="flex-sm-col col-auto"><p class="mb-1">&#8377;150</p></div>
                        </div>
                        <div class="row justify-content-between">
                            <div class="flex-sm-col text-right col"><p class="mb-1"><b>GST 18%</b></p></div>
                            <div class="flex-sm-col col-auto"><p class="mb-1">843</p></div>
                        </div>
                        <div class="row justify-content-between">
                            <div class="flex-sm-col text-right col"><p class="mb-1"><b>Delivery Charges</b></p></div>
                            <div class="flex-sm-col col-auto"><p class="mb-1">Free</p></div>
                        </div>
                    </div>
                </div>
                <div class="row invoice ">
                    <div class="col"><p class="mb-1"> Invoice Number : 788152</p><p class="mb-1">Invoice Date : 22 Dec,2019</p><p class="mb-1">Recepits Voucher:18KU-62IIK</p></div>
                </div>
            </div>
            <div class="card-footer">
                <div class="jumbotron-fluid">
                    <div class="row justify-content-between ">
                        <div class="col-sm-auto col-auto my-auto"><img class="img-fluid my-auto align-self-center " src="https://i.imgur.com/7q7gIzR.png" width="115" height="115"></div>
                        <div class="col-auto my-auto "><h2 class="mb-0 font-weight-bold">TOTAL PAID</h2></div>
                        <div class="col-auto my-auto ml-auto"><h1 class="display-3 ">&#8377; 5,528</h1></div>
                    </div>
                    <div class="row mb-3 mt-3 mt-md-0">
                        <div class="col-auto border-line"> <small class="text-white">PAN:AA02hDW7E</small></div>
                        <div class="col-auto border-line"> <small class="text-white">CIN:UMMC20PTC </small></div>
                        <div class="col-auto "><small class="text-white">GSTN:268FD07EXX </small> </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<!-- END OrderItem -->

</html>

<style>
/* Ensure both containers have the same width and are centered */
.container, .container-fluid {
  width: 100%; /* Full width */
  max-width: 1200px; /* Set max width for consistency */
  margin: 0 auto; /* Center the containers */
  padding-left: 0;  /* Remove extra padding */
  padding-right: 0; /* Remove extra padding */
}

.container > div, .container-fluid > div {
  width: 100%; /* Ensure each child div takes full container width */
  box-sizing: border-box; /* Include padding and border in width calculation */
}

.edit-link img {
  width: 20px; /* Size of icon */
  cursor: pointer; /* Pointer on hover */
}

.edit-link img:hover {
  filter: brightness(1.2); /* Increase brightness on hover */
}

.edit-link {
  margin-left: 40px; /* Spacing for the icon */
  text-decoration: none;
}
</style>

