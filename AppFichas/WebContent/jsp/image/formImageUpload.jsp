<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form">
	<iframe id="hideIframe" name="hideIframe" style="display:none"></iframe>
	<form id="upload" method="post" action="<c:out value='${requestScope.action}'/>" enctype="multipart/form-data" target="hideIframe">
		<input type="hidden" id="_id" name="_id" value="<c:out value='${requestScope.id}'/>" />	
		<div class="droppable center" style="width:80%;height:80%;padding:10%;background-color:#1E90FF;color:#fff">Haz click o arrastra el fichero aquí</div>	
		<div class="output center">
			<img style="max-width:200px;max-height:200px" src="<c:out value='${requestScope.path}'/>" />
		</div>	
		<div id="submit" class="center" style="display:none;max-height:140px;">
			<input type="submit" class="button" value="Añadir" />
		</div>			
	</form>
</div>
<div class="script">
	<script type="text/javascript">
	 (function(window) {
		    function triggerCallback(e, callback) {
		      if(!callback || typeof callback !== 'function') {
		        return;
		      }
		      var files;
		      if(e.dataTransfer) {
		        files = e.dataTransfer.files;
		      } else if(e.target) {
		        files = e.target.files;
		      }
		      callback.call(null, files);
		    }
		    function makeDroppable(ele, callback) {
		      var input = document.createElement('input');
		      input.setAttribute('type', 'file');
		      input.setAttribute('multiple', false);
		      input.name="file";
		      input.style.display = 'none';
		      input.addEventListener('change', function(e) {
		        triggerCallback(e, callback);
		      });
		      ele.appendChild(input);
		      
		      ele.addEventListener('dragover', function(e) {
		        e.preventDefault();
		        e.stopPropagation();
		        ele.classList.add('dragover');
		      });

		      ele.addEventListener('dragleave', function(e) {
		        e.preventDefault();
		        e.stopPropagation();
		        ele.classList.remove('dragover');
		      });

		      ele.addEventListener('drop', function(e) {
		        e.preventDefault();
		        e.stopPropagation();
		        ele.classList.remove('dragover');
		        triggerCallback(e, callback);
		      });
		      
		      ele.addEventListener('click', function() {
		        input.value = null;
		        input.click();
		      });
		    }
		    window.makeDroppable = makeDroppable;
		  })(this);
		  (function(window) {
		    makeDroppable(window.document.querySelector('.droppable'), function(files) {
		      //console.log(files);
		      document.getElementById("submit").style.display="block";
		      var output = document.querySelector('.output');
		      output.innerHTML = '';
		      for(var i=0; i<files.length; i++) {
		        if(files[i].type.indexOf('image/') === 0) {
		          output.innerHTML += '<img style="max-width:200px;max-height:200px" src="' + URL.createObjectURL(files[i]) + '" />';
		        }
		        else{
		        	output.innerHTML += '<p>'+files[i].name+'</p>';    
			    }		       
		      }
		    });
		  })(this);
		
	</script>
</div>