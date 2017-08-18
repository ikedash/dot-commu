<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>

<head>
  <title>サインアップ</title>
  <link href="/webjars/Semantic-UI/2.2.9/semantic.min.css" rel="stylesheet">
  <link href="/css/user.css" rel="stylesheet">
</head>

<body>
<div class="ui middle aligned center aligned grid">
  <div class="column">
    <h2 class="ui header sign">Join DotCommu</h2>

    <c:if test="${used}">
      <div class="ui error message">
        <p>Already used username.</p>
      </div>
    </c:if>

    <form:form modelAttribute="userForm" class="ui large form" action="/signup">
      <div class="ui raised segment">
        <div class="field">
          <div class="ui left icon input">
            <i class="user icon"></i>
            <form:input path="username" placeholder="Username" />
          </div>
          <form:errors path="username" cssClass="error-messages" />
        </div>
        <div class="field">
          <div class="ui left icon input">
            <i class="lock icon"></i>
            <form:password path="password" placeholder="Password" />
          </div>
          <form:errors path="password" cssClass="error-messages" />
        </div>
        <div class="field">
          <input type="submit" value="Create an account" class="ui fluid large green submit button"/>
        </div>
        <div class="field">
          <a href="/signin" class="ui fluid button">Return to Sign in.</a>
        </div>
      </div>
    </form:form>
  </div>
</div>
</body>
</html>
