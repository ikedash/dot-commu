<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
  <title>投稿画面</title>
  <link href="/webjars/Semantic-UI/2.2.9/semantic.min.css" rel="stylesheet">
  <link href="/css/main.css" rel="stylesheet">
</head>

<body>
  <jsp:include page="/WEB-INF/views/common/header.jsp" flush="true" />
  <div class="full height">
    <div class="ui main container">
      <h1 class="ui center aligned header">投稿画面</h1>
      <form class="ui form" action="/report/create" method="post">
        <div class="field">
          <label>タイトル</label>
          <input name="title" value="${reportForm.title}"/>
          <p class="error-messages"><c:out value="${errors.title}" /></p>
        </div>

        <div class="field">
          <label>内容</label>
          <textarea name="reportBody" ><c:out value="${reportForm.reportBody}" /></textarea>
          <p class="error-messages"><c:out value="${errors.reportBody}" /></p>
        </div>

        <div class="field">
          <label>本日の満足度</label>
          <c:forEach var="satisfaction" items="${satisfactions}" >
            <div class="ui radio checkbox">
              <input type="radio" name="satisfaction" value="${satisfaction}" <c:if test="${reportForm.satisfaction == satisfaction}">checked</c:if> />
              <label><c:out value="${satisfaction}" /></label>
            </div>
          </c:forEach>
          <p class="error-messages"><c:out value="${errors.satisfaction}" /></p>
        </div>

        <div class="field">
          <label>満足度の理由</label>
          <textarea id="cause" name="cause">${reportForm.cause}</textarea>
          <p class="error-messages"><c:out value="${errors.cause}" /></p>
        </div>

        <div class="five wide field">
          <label>関連するタグ</label>
          <select class="ui dropdown" name="tag">
            <c:forEach var="tag" items="${tags}" >
              <%-- Eclipseで下記構文だとエラーが出るため仕方なしの実装 --%>
              <%-- <option value="${tag.tagName}" <c:if test="${reportForm.tag == tag.tagName}">selected</c:if> ><c:out value="${tag.tagName}" /></option> --%>
              <c:choose>
                <c:when test="${reportForm.tag == tag.tagName}"><c:set var="select" value="selected" /></c:when>
                <c:otherwise><c:set var="select" value="" /></c:otherwise>
              </c:choose>
              <option value="${tag.tagName}" ${select}><c:out value="${tag.tagName}" /></option>
            </c:forEach>
          </select>
          <p class="error-messages"><c:out value="${errors.tag}" /></p>
        </div>

        <div class="field">
          <input class="ui fluid green button" type="submit" value="投稿する" name = "confirm" />
        </div>
      </form>
    </div>
  </div>
</body>
</html>
