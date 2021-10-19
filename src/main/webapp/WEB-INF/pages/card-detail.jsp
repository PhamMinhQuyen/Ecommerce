<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Detail Page</title>
        <link href="<c:url value="/webjars/bootstrap/4.6.0/css/bootstrap.min.css" />" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/resources/css/app.css" />"  rel="stylesheet"/>
    </head>
    <body>

        <div class="container">
            <div class="row">
                <div class="col-12 text-align-center">
                    <h4>Card Detail</h4>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Image</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Color</th>
                                    <th>Size</th>
                                    <th>Unit price</th>
                                    <th>Action</th>
                                </tr>  
                            <thead>
                            <tbody>
                            <h2></h2>
                            
                            
                                <c:forEach var="productDetailAddCart" items="${productDetailAddCarts}" varStatus="loop" >
                                    <tr>
                                        <td>${productDetailAddCart.product.name}</td>      
                                        <td><img src="${pageContext.request.contextPath}/resources/images/${productDetailAddCart.images.get(0).name}" height="50" width="50">
                                        </td>
                                        <td>
                                            <fmt:formatNumber type="number" pattern="###,### VND" value=" ${productDetailAddCart.price}" />
                                           
                                        </td>
                                        <td> 
                                            <mvc:form action="${pageContext.request.contextPath}/update-card-detail/${loop.index}" method="post">
                                                <input max="${productDetailAddCart.quantity}" onchange="this.form.submit()" name="updateQuantity" style="width: 100px" type="number" min="0" value="${productDetailAddCart.quantityPurchased}"/>                                                                                   
                                            </mvc:form>
                                        </td>
                                        <td>${productDetailAddCart.color.color}</td>
                                         <td>${productDetailAddCart.size.size}</td>
                                         <td>
                                             <fmt:formatNumber type="number" pattern="###,### VND" value=" ${productDetailAddCart.price*productDetailAddCart.quantityPurchased}" />
                                            
                                         </td>
                                         <td>
                                            <button class="btn btn-danger"
                                                    onclick="location.href = '<c:url value="/delete-card-detail/${loop.index}"/>'">Remove</button>
                                        </td>
                                    </tr>
                                    </c:forEach>
                            </tbody>
                        </table>
                        <p style="float: right">Total price: <fmt:formatNumber type="number" pattern="###,### VND" value=" ${totalPrice}" /></p>
                    </div>	
                </div>
            </div>
            
        </div>



        <script src="<c:url value="/resources/js/jquery.min.js" />"/> 
        <script src="<c:url value="/resources/js/popper.min.js/" />"/> 
        <script src="<c:url value="/webjars/bootstrap/4.6.0/js/bootstrap.min.js" />"/> 
    </body>
</html>

