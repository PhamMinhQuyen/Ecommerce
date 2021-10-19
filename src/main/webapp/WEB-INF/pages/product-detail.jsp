<%-- 
    Document   : product-detail
    Created on : Oct 6, 2021, 9:10:45 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Detail Page</title>
        <link href="<c:url value="/webjars/bootstrap/4.6.0/css/bootstrap.min.css" />" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/resources/css/app.css" />"  rel="stylesheet"/>
    </head>
    <body>             
        <div class="container" >
            <div class="row">
                <div class="col-12 text-align-center ">
                    <h4>Product Detail</h4>
                </div>
            </div>
            <c:if test="${message != null}">
                <br/>
                <div class="row">
                    <div class="col-12 text-align-center">
                        <div class="alert alert-danger" role="alert">
                            <p>${message}</p>
                        </div>
                    </div>
                </div>
            </c:if>     
            <div class="images">
                <c:forEach var="image" items="${images}" varStatus="index">
                    <img src="${pageContext.request.contextPath}/resources/images/${image.name}" style="float: right; height: 300px">
                </c:forEach>
            </div>  
            <mvc:form action="${pageContext.request.contextPath}/show-items" method="post" modelAttribute="productDetail">
                <input hidden name="product.name" value="${product.name}">
                <input hidden name="price" value="${priceOfProductByColordAndSize.price}">
                

                <div class="row">
                    <div class="col-12 col-lg-6">
                        <div class="form-group">
                            <label for="name">Product Name: <b>${product.name}</b></label> 
                        </div>
                    </div>  
                </div>
                <div class="row">
                    <div class="col-12 col-lg-6">
                        <div class="form-group">
                            <label>Size</label>
                            <select  id="optionsize" name="size.size" class="form-control">     

                                <c:forEach var="sizeOfProductDetail" items="${sizesOfProductDetail}">
                                    <c:if test="${size eq sizeOfProductDetail.size.size }">
                                        <option selected value="${sizeOfProductDetail.size.size}">${sizeOfProductDetail.size.size}</option>
                                    </c:if>
                                    <c:if test="${size != sizeOfProductDetail.size.size }">
                                        <option value="${sizeOfProductDetail.size.size}">${sizeOfProductDetail.size.size}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-12 col-lg-6">
                        <div class="form-group">
                            <label>Color</label>
                            <%-- onchang="this.form.submit()"--%>
                            <select id="optioncolor" name="color.color" class="form-control" >         
                                <c:forEach var="colorsOfProductDetail" items="${colorsOfProductDetailBySize}">
                                    <c:if test="${setColor eq colorsOfProductDetail.color.color}">
                                        <option selected value="${colorsOfProductDetail.color.color}">${colorsOfProductDetail.color.color}</option>
                                    </c:if>
                                    <c:if test="${setColor != colorsOfProductDetail.color.color}">
                                        <option value="${colorsOfProductDetail.color.color}">${colorsOfProductDetail.color.color}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                </div>

                <div class="row">
                    <div class="col-12 col-lg-6">
                        <label for="price">Price</label>
                        <div class="form-control"  >
                            <label>${priceOfProductByColordAndSize.price}</label> 

                        </div>
                    </div>
                </div>

                <div class="row">                
                    <div class="col-12 col-lg-6">
                        <div class="form-group">
                            <label for="quantity">Quantity</label>
                            <input type="number" class="form-control" name="quantityPurchased" value="0" min="0" 
                                   id="quantity" >
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="col-12 text-align-center">
                        <button type="submit" class="btn btn-secondary">Add Card</button>
                    </div>
                </div>
            </mvc:form>
        </div>
        <script>
            var optionElementSize = document.querySelector('#optionsize');
            var optionElementColor = document.querySelector('#optioncolor');
            var inputElement = document.querySelector('input[name="quantityPurchased"]');
            optionElementSize.onchange = function () {
                inputElement.value = 0;
                this.form.submit();
            };
            optionElementColor.onchange = function () {
                inputElement.value = 0;
                this.form.submit();
            };
        </script>
        <script src="<c:url value="/resources/js/jquery.min.js" />"/> 
        <script src="<c:url value="/resources/js/popper.min.js/" />"/> 
        <script src="<c:url value="/webjars/bootstrap/4.6.0/js/bootstrap.min.js" />"/> 
    </body>
</html>
