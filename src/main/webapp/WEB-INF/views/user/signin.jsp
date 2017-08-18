<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
  <title>サインイン</title>
  <link href="/webjars/Semantic-UI/2.2.9/semantic.min.css" rel="stylesheet">
  <link href="/css/user.css" rel="stylesheet">
</head>

<body>
<div class="ui middle aligned center aligned grid">
  <div class="column">
    <h2 class="ui header sign">Sign in to DotCommu</h2>

    <c:if test="${param.error}">
      <div class="ui error message">
        <p>Incorrect username or password.</p>
      </div>
    </c:if>

    <c:if test="${joined}">
      <div class="ui message">
        <p>User creation is complete.</p>
      </div>
    </c:if>

    <form class="ui large form" action="/signin" method="post">
      <div class="ui raised segment">
        <div class="field">
          <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="username" placeholder="Username">
          </div>
        </div>
        <div class="field">
          <div class="ui left icon input">
            <i class="lock icon"></i>
            <input type="password" name="password" placeholder="Password">
          </div>
        </div>
        <input type="submit" value="Sign in" class="ui fluid large green submit button"/>
      </div>
    </form>

    <div class="ui message">
      New to DotCommu? <a href="/signup?form">Create an account.</a>
    </div>
  </div>
</div>
</body>
</html>
