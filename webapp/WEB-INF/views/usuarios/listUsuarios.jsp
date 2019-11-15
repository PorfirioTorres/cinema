<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%><!-- etiqueta para usar las tags de spring-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><!-- etiquetas para formato -->

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Listado de usuarios</title>
    <spring:url value="/usuarios/create" var="urlNueva" />
    <spring:url value="/usuarios" var="urlPag" />
    <spring:url value="/resources" var="urlPublic" /><!--apunta a la carpeta resources -->
    <link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${urlPublic }/bootstrap/css/theme.css" rel="stylesheet">
    
  </head>

  <body>
	<c:set var = "nomrol" value = ""/>
    <jsp:include page="../includes/menu.jsp"></jsp:include>

    <div class="container theme-showcase" role="main">

      <h3>Listado de usuarios</h3>
      
      <a href="${urlNueva}" class="btn btn-success" role="button" title="Nuevo horario" >Nuevo</a><br><br>
	
      <div class="table-responsive">
        <table class="table table-hover table-striped table-bordered">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Email</th>
                <th>Estatus</th>
                <th>Rol</th>
            </tr>
            
            <c:forEach items="${usuarios}" var="usuario">
            	<tr>
	                <td>${usuario.id}</td>
	                <td>${usuario.nombre}</td>
	                <td>${usuario.email}</td>
	                <td>${usuario.estatus}</td>
	                <c:forEach items="${usuario.roles}" var="rol">
	                	<c:set var = "nomrol" value = "${rol.nombre}"/>
	                </c:forEach>
	                <td><c:out value="${nomrol}"></c:out></td>
	              </tr>
	         </c:forEach>
            
        </table>
      </div>
          
      <hr class="featurette-divider">

      <!-- FOOTER -->
      <jsp:include page="../includes/footer.jsp"></jsp:include>

    </div> <!-- /container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
    <script src="${urlPublic }/bootstrap/js/bootstrap.min.js"></script>     
  </body>
</html>
