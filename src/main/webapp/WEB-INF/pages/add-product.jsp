<%-- 
    Document   : add-product
    Created on : Sep 24, 2021, 1:39:14 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product Page</title>
        <link href="<c:url value="/webjars/bootstrap/4.6.0/css/bootstrap.min.css" />" type="text/css" rel="stylesheet"/>
        <link href="<c:url value="/resources/css/app.css" />"  rel="stylesheet"/>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-12 text-align-center">
                    <c:if test="${action == 'add'}">
                        <h4>Add Product</h4>
                    </c:if>

                </div>
            </div>
             <mvc:form action="${pageContext.request.contextPath}/result" method="post" modelAttribute="product"
        enctype="multipart/form-data">
                <div class="row">
                    <div class="col-12 col-lg-6">
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input type="text" class="form-control" name="name" id="name">

                        </div>
                    </div>

                    <div class="col-12 col-lg-6">
                        <div class="form-group">
                            <label for="category">Category</label>
                            <select name="category.id" class="form-control">
                                <option></option>
                                <c:forEach var="c" items="${categories}"> 
                                    <option value="${c.id}">${c.name}</option>
                                </c:forEach>

                            </select>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="col-12 col-lg-6">
                        <div class="form-group">
                            <label for="description">Description</label>
                            <textarea name="description" class="form-control"></textarea>

                        </div>
                    </div>

                    <div class="col-12 col-lg-6">
                        <div class="form-group">
                            <label for="image">Image</label><br>
                            <input type="file" name="files" id="image" multiple="multiple"/>
                        </div>
                    </div>
                </div>
                
                  <div class="row">
                    <div class="col-12 text-align-center">
                        <button type="submit" class="btn btn-secondary">Submit</button>
                    </div>
                </div>
            </mvc:form>


            <script src="<c:url value="/resources/js/jquery.min.js" />"/> 
            <script src="<c:url value="/resources/js/popper.min.js/" />"/> 
            <script src="<c:url value="/webjars/bootstrap/4.6.0/js/bootstrap.min.js" />"/> 
    </body>
</html>
