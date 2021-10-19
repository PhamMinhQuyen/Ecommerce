<%-- 
    Document   : products
    Created on : Sep 21, 2021, 10:43:03 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products Page</title>
        <link href="<c:url value="/webjars/bootstrap/4.6.0/css/bootstrap.min.css" />" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/resources/css/app.css" />"  rel="stylesheet"/>


    </head>
    <body>
        <div class="container" >
            <div class="row">
                <div class="col-12 text-align-center">
                    <h4>List Product</h4>
                </div>
            </div>

            <c:if test="${message != null || message !=''}">
                <div class="alert alert-${type}">
                    ${message}
                </div>
            </c:if>


            <div class="row">
                <div class="col-12 col-lg-6 ">
                    <button class="btn btn-success" onclick="location.href = '<c:url value="/add-products"/>'">Add Book</button>
                </div>
                <div class="col-12 col-lg-6">
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <tr>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Create Date</th>
                                <th>Image</th>
                                <th>Category</th>
                                <th>Action</th>
                                
                            </tr>

                            <c:forEach items="${products}" var="product">
                                <tr>
                                    <td>${product.name}</td>
                                    <td>${product.description}</td>
                                    <td>
                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${product.createDate}" />
                                    </td>

                                    <td class="td-with">
                                        <c:forEach items="${product.images}" var="image" >
                                            <img width="25%""
                                                 src="<c:url value="/resources/images/"/>${image.name}"/>
                                        </c:forEach>
                                    </td>
                                    <td>${product.category.name}</td>
                                    <td>
                                        <button class="btn btn-danger"
                                                onclick="location.href = '<c:url value="/delete-product/${product.id}"/>'">Delete</button>
                                        <button class="btn btn-info"
                                                onclick="location.href = '<c:url value="/order-product/${product.id}"/>'">Order</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>	
                </div>
            </div>


        </div>
        <script src="<c:url value="/resources/js/jquery.min.js" />"/> 
        <script src="<c:url value="/resources/js/popper.min.js/" />"/> 
        <script src="<c:url value="/webjars/bootstrap/4.6.0/js/bootstrap.min.js" />"/> 
    </body>
</html>
