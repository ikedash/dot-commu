<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

  <sec:authentication var="principal" property="principal" />
  <header class="ui fixed inverted main menu">
  <div class="ui container">

    <div class="left menu">
      <a class="item" href="/report">
        <i class="home icon"></i> Home
      </a>
      <a class="item" href="/report/create">
        <i class="write icon"></i> 投稿
      </a>
    </div>

    <div class="right menu">
      <a class="item" href="/profile?userId=${principal.username}">
        <i class="user icon"></i> My Profile
      </a>
      <a class="item" href="/logout">
        <i class="sign out icon"></i> Sign out
      </a>
    </div>

  </div>
  </header>
