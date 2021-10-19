<%-- 
    Document   : add-product-detail
    Created on : Sep 24, 2021, 2:26:58 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <div class="container">
            <mvc:form action="${pageContext.request.contextPath}/add-product-detail" method="post" modelAttribute="productDetail">
                <div class="row">
                    <div class="col-12 text-align-center">

                        <h4>Add Product Detail</h4>            
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 col-lg-6">
                        <div class="form-group">
                            <label for="quantity">Quantity</label>
                            <input type="number" class="form-control" name="quantity" 
                                   id="quantity" ">

                        </div>
                    </div>

                    <div class="col-12 col-lg-6">
                        <div class="form-group">
                            <label for="price">Price</label>
                            <input type="number" class="form-control" name="price" 
                                   id="price" ">
                        </div>

                    </div>
                </div>


                <div class="row">
                    <div class="col-12 col-lg-6">
                        <div class="form-group">
                            <label for="color">Color</label>
                            <select name="color.color" class="form-control">
                                <option></option>
                                <c:forEach var="color" items="${colors}"> 
                                    <option value="${color}">${color}</option>
                                </c:forEach>

                            </select>


                        </div>
                    </div>
                    <div class="col-12 col-lg-6">
                        <div class="form-group">
                            <label for="size">Size</label>
                            <select name="size.size" class="form-control">
                                <option></option>
                                <c:forEach var="size" items="${sizes}"> 
                                    <option value="${size}">${size}</option>
                                </c:forEach>

                            </select>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-12 text-align-center">
                        <button type="submit" class="btn btn-secondary">Add</button>
                    </div>
                </div>
            </mvc:form>
        </div>


        <div class="container">
            <c:if test="${message != null}">
                <div class="alert alert-danger">
                    <i>Product Detail Exists</i>
                </div>
            </c:if>

            <div class="row">
                <div class="col-12">

                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Color</th>
                                    <th>Size</th>
                                    <th>Action</th>
                                </tr>  
                            <thead>
                            <tbody>
                                <c:forEach var="productDetail" items="${productDetails}" varStatus="loop"> 
                                  
                                    <tr>
                                        <td>${productDetail.product.name}</td>
                                        <td>${productDetail.price}</td>
                                        <td>${productDetail.quantity}</td>
                                        <td>${productDetail.color.color}</td>
                                        <td>${productDetail.size.size}</td>
                                        <td>
                                            <button class="btn btn-danger"
                                                    onclick="location.href = '<c:url value="/delete-product-detail/${loop.index}"/>'">Remove</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>	
                </div>
            </div>
            <div class="row">
                <div class="col-12 text-align-center">
                    <button  class="btn btn-success" onclick="location.href = '<c:url value="/save-product-detail"/>'">Submit</button>
                </div>        
            </div>
        </div>



        <script src="<c:url value="/resources/js/jquery.min.js" />"/> 
        <script src="<c:url value="/resources/js/popper.min.js/" />"/> 
        <script src="<c:url value="/webjars/bootstrap/4.6.0/js/bootstrap.min.js" />"/> 
    </body>
</html>

