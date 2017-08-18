<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

<html>

<head>
  <title>プロフィール詳細</title>
  <link href="/webjars/Semantic-UI/2.2.9/semantic.min.css" rel="stylesheet">
  <link href="/css/profile.css" rel="stylesheet">
  <link href="/css/main.css" rel="stylesheet">
  <link href="/css/report.css" rel="stylesheet">
  <sec:authentication var="principal" property="principal" />
</head>

<body>
  <jsp:include page="/WEB-INF/views/common/header.jsp" flush="true" />
  <div class="ui main container form">
    <div class="ui card">
    <div class="image">
      <c:choose>
        <c:when test="${!empty profile.image}">
          <img src="/images/people/large/${profile.image}.png">
        </c:when>
        <c:otherwise><span class="notset">画像未設定</span></c:otherwise>
      </c:choose>
    </div>
    <div class="content">
      <c:choose>
        <c:when test="${empty profile.lastName}"><span class="notset">未設定</span></c:when>
        <c:when test="${empty profile.firstName}"><span class="notset">未設定</span></c:when>
        <c:otherwise>
          <a class="header"><c:out value="${profile.lastName}" />&nbsp;<c:out value="${profile.firstName}" /></a>
        </c:otherwise>
      </c:choose>
    </div>
    </div>

    <div class="field">
      <label>出身地</label>
      <c:choose>
        <c:when test="${empty profile.birthplace}"><span class="notset">未設定</span></c:when>
        <c:otherwise><c:out value="${profile.birthplace}" /></c:otherwise>
      </c:choose>
    </div>

    <div class="field">
      <label>好きな食べ物</label>
      <c:choose>
        <c:when test="${empty profile.favoriteFood}"><span class="notset">未設定</span></c:when>
        <c:otherwise><c:out value="${profile.favoriteFood}" /></c:otherwise>
      </c:choose>
    </div>

    <div class="field">
      <label>嫌いな食べ物</label>
      <c:choose>
        <c:when test="${empty profile.hatedFood}"><span class="notset">未設定</span></c:when>
        <c:otherwise><c:out value="${profile.hatedFood}" /></c:otherwise>
      </c:choose>
    </div>

    <div class="field">
      <label>血液型</label>
      <c:choose>
        <c:when test="${empty profile.bloodType}"><span class="notset">未設定</span></c:when>
        <c:otherwise><c:out value="${profile.bloodType}" /></c:otherwise>
      </c:choose>
    </div>

    <div class="field">
      <label>生年月日</label>
      <c:choose>
        <c:when test="${empty profile.birthday}"><span class="notset">未設定</span></c:when>
        <c:otherwise><c:out value="${profile.birthday}" /></c:otherwise>
      </c:choose>
    </div>

    <div class="field">
      <label>いってみたい国</label>
      <c:choose>
        <c:when test="${empty profile.country}"><span class="notset">未設定</span></c:when>
        <c:otherwise><c:out value="${profile.country}" /></c:otherwise>
      </c:choose>
    </div>

    <div class="field">
      <label>自己紹介</label>
      <c:choose>
        <c:when test="${empty profile.introduction}"><span class="notset">未設定</span></c:when>
        <c:otherwise><pre><c:out value="${profile.introduction}" /></pre></c:otherwise>
      </c:choose>
    </div>

    <c:if test="${principal.username == profile.userId}">
      <a href="/profile/update?userId=${profile.userId}" class="ui fluid green button">プロフィール編集</a>
    </c:if>
  </div>
</body>
</html>
