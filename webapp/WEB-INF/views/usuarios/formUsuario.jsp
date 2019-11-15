<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%><!-- etiqueta para usar las tags de spring-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">    
		<meta name="author" content="">
		<title>Creacion de Usuarios</title>
		
		<spring:url value="/resources" var="urlPublic" />
      <spring:url value="/" var="urlRoot" />
      <spring:url value="/usuarios/save" var="urlForm" />
		<link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">    
		<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
	</head>

	<body>
		<jsp:include page="../includes/menu.jsp"></jsp:include>
		<div class="container theme-showcase" role="main">

			<h3 class="blog-title"><span class="label label-success">Datos del Usuario</span></h3>  

			<form:form action="${urlForm}" method="post" modelAttribute="usuario">
				<div class="row">         
					<div class="col-sm-3">
						<div class="form-group">
							<label for="perfil" class="control-label">Rol</label>              
							<select id="perfil" name="rolesX" class="form-control" required="required">
								<c:forEach items="${roles}" var="rol">
									<option value="${rol.id}-${rol.nombre}">${rol.nombre}</option>
								</c:forEach>				 
									
 							</select>              
						</div> 
					</div>
				</div>	
				<div class="row"> 	
					<div class="col-sm-3">
						<div class="form-group">
							<label for="cuenta">Nombre</label>             
							<form:input type="text" class="form-control" path="nombre" id="cuenta" required="required"/>
						</div>  
					</div>
					
					<div class="col-sm-3">
						<div class="form-group">
							<label for="pwd">Password</label>             
							<form:input type="password" class="form-control" path="password" id="pwd" required="required"/>
						</div>  
					</div>
					
					<div class="col-sm-3">
						<div class="form-group">
							<label for="email">Email</label>
							<form:input type="text" class="form-control" path="email" id="email" placeholder="Correo electrónico" required="required"/>
						</div>  
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<label for="telefono">Estatus</label>
							<form:select path="estatus" class="form-control">
								<form:option value="0">Inactivo</form:option>
								<form:option value="1">Activo</form:option>
							</form:select>
						</div>  
					</div>

				</div>

				<button type="submit" class="btn btn-danger" >Guardar</button>
			</form:form> 

			<hr class="featurette-divider">

			<!-- FOOTER -->
			<jsp:include page="../includes/footer.jsp"></jsp:include>
		</div> <!-- /container -->

		<!-- Bootstrap core JavaScript
		================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
		<script src="${urlPublic}/bootstrap/js/bootstrap.min.js"></script>    
	</body>
</html>
