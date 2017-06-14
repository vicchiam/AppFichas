<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form">
	<form id="upload" method="post" action="upload.php" enctype="multipart/form-data">
    	<div id="drop">
         	Arrastrar aquí
            <a>Buscar</a>
            <input type="file" name="upl" />
        </div>
        <ul>
            <!-- The file uploads will be shown here -->
        </ul>
	</form>
</div>
<div class="script">
	<script type="text/javascript" src="<c:url value="/resources/js/libs/jquery.ui.widget.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/libs/jquery.iframe-transport.js" />"></script> 
	<script type="text/javascript" src="<c:url value="/resources/js/libs/jquery.fileupload.js" />"></script>  
	
	<script type="text/javascript" src="<c:url value="/resources/js/libs/fileUpload.js" />"></script>
</div>