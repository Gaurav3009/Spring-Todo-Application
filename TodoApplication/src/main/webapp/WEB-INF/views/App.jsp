<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "com.todoApplication.Entity.Task" %>
<%@page isELIgnored = "false" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TaskList</title>
    <link rel="stylesheet" href="<c:url value = "/resources/css/style.css"/>">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  </head>
    
    <div class = "body" style = "background-image: url('<c:url value = "resources/images/bg-image.jpg"/>')">
        <div class="container">
            <div class="top">
                <section class="left">
                    <h1 class = "heading">TaskList</h1>
                </section>
                <section class="right" onclick="add()">
                    Add Task +
                </section>
            </div>
            
            <c:if test="${empty dataArray}">
		        <p>No data available.</p>
		    </c:if>
		    
		    <div class="bottom">
	             <c:forEach items="${dataArray }" var="data" >
		            <div class="task mt-3">
		                    <div class="details">
		                        <span id = "${data.taskId }">${data.taskId }</span>
		                        <span id = "taskName">${data.taskName }</span>
		                    </div>
		                    <div class="action">
		                        <a href = "deleteTask/${data.taskId}">
		                        	<box-icon type = "solid" name = "message-square-minus" color = "red"></box-icon>
		                        </a>
		                        <a href = "updateTask/${data.taskId }">
		                        	<box-icon type = "solid" name = "pen" color = "blue" onclick = "add()"></box-icon>
		                        </a>
		                    </div>
		                </div>
			    </c:forEach>   
	        </div>
		    
            
            
        </div>
    </div>
    <div class = "entry invisible" id = "entry">
        <div class="title">
            ${formName}
        </div>
        <form class = "form" action = "addTask" method = "post">
            <div class="mb-4">
              <label for="taskTitle" class="form-label">Task name</label>
              <input type="text" class="form-control" id="taskTitle" placeholder="Enter the task name" name = "taskName" value = "${taskName}">
              <!-- <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div> -->
            </div>
            <div class="mb-4">
              <label for="description" class="form-label">Description</label>
              <textarea type="text" class="form-control" id="description" name = "taskDescription">${taskDescription}</textarea>
            </div>
            
            <div>
                <button type="submit" class="btn btn-primary" onclick = "add()">Submit</button>
            </div>
          </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
    <script src="<c:url value = "/resources/javascript/script.js"/>"></script>
</body>
</html>