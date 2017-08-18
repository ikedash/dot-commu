<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<html>

<head>
  <title>タイムライン</title>
  <link href="/webjars/Semantic-UI/2.2.9/semantic.min.css" rel="stylesheet">
  <link href="/css/main.css" rel="stylesheet">
  <link href="/css/report.css" rel="stylesheet">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>

<jsp:include page="/WEB-INF/views/common/header.jsp" flush="true" />

<div class="ui main container">
  <div class="ui raised attached segment" id="labels">
    <form class="ui form" method="get" action="/report">
      <div class="inline fields">
        <label>名字</label>
        <div class="field">
          <input type="text" name="lastName" value="${reportSearchForm.lastName}">
        </div>
        <label>名前</label>
        <div class="field">
          <input type="text" name="firstName" value="${reportSearchForm.firstName}">
        </div>
        <label>タイトル</label>
        <div class="five wide field">
          <input type="text" name="title" value="${reportSearchForm.title}">
        </div>
        <label>タグ</label>
        <div class="field">
          <select class="ui dropdown" name="tag">
            <option value="">選択してください</option>
            <c:forEach var="tag" items="${tags}" >
              <c:choose>
                <c:when test="${reportSearchForm.tag == tag.tagName}">
                  <option value="${tag.tagName}" selected>${tag.tagName}</option>
                </c:when>
                <c:otherwise>
                  <option value="${tag.tagName}">${tag.tagName}</option>
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </select>
        </div>
      </div>
      <div class="field">
        <input class="ui fluid blue button" type="submit" value="検索" />
      </div>
    </form>
  </div>
</div>
<div class="ui information container">
  <div class="ui one column grid">
    <c:forEach items="${reports}" var="report">
      <div class="column">
        <div class="ui fluid card">
          <div class="content">
            <div class="meta">
            <a class="header" href="/profile/?userId=${report.user_id}">
              <c:out value="${report.last_name} ${report.first_name}" /></a>
            <div class="right floated">
              <fmt:formatDate value="${report.created_at}" pattern="yyyy年MM月dd日(E) HH:MM:ss" timeZone="JST"/>
            </div>
            </div>
          </div>
          <div class="content">
            <a class="header" href="/report/detail?reportId=${report.report_id}"><c:out value="${report.title}" /></a>
            <div class="description"><pre class="msg"><c:out value="${report.report_body}"/></pre></div>
          </div>
          <div class="extra content">
            <a class="ui label"><c:out value="${report.tag}" /></a>
          </div>
        </div>
      </div>
    </c:forEach>
<div class="ui information container">
  <div class="ui one column grid">
    <%-- フォーム関連のリクエストパラメータを設定 --%>
    <c:set var="strAboutForm"
           value="lastName=${reportSearchForm.lastName}&firstName=${reportSearchForm.firstName}&title=${reportSearchForm.title}&tag=${reportSearchForm.tag}" />
    <%-- 最初のページのボタン --%>
    <a class="ui button" href="/report?currentPage=1&${strAboutForm}">1</a>

    <c:if test="${2 <= maxPage}">
      <%-- あいだのページのボタン --%>
      <c:choose>
        <%-- ①最大ページが3以上5以下（5は最大表示ページ数）の場合 --%>
        <c:when test="${3 <= maxPage && maxPage <= 5}">
          <c:forEach var="i" begin="2" end="${maxPage - 1}" step="1">
            <a class="ui button" href="/report?currentPage=${i}&${strAboutForm}">${i}</a>
          </c:forEach>
        </c:when>

        <%-- ②最大ページが6以上の場合 --%>
        <c:when test="${6 <= maxPage}">
          <c:choose>
            <%-- (A)現在ページが3以下の場合、表示するページは[1],[2],[3],[4],[maxPage] --%>
            <c:when test="${currentPage <= 3 }">
              <c:forEach var="i" begin="2" end="4" step="1">
                <a class="ui button" href="/report?currentPage=${i}&${strAboutForm}">${i}</a>
              </c:forEach>
            </c:when>
            <%-- (B)現在ページが(maxPage-2)以上の場合、表示するページは[1],[maxPage-3],[maxPage-2],[maxPage-1],[maxPage] --%>
            <c:when test="${maxPage - 2 <= currentPage}">
              <c:forEach var="i" begin="${maxPage - 3}" end="${maxPage - 1}" step="1">
                <a class="ui button" href="/report?currentPage=${i}&${strAboutForm}">${i}</a>
              </c:forEach>
            </c:when>
            <%-- (C)それ以外の場合、表示するページは[1],[currentPage-1],[currentPage],[currentPage+1],[maxPage] --%>
            <c:otherwise>
              <c:forEach var="i" begin="${currentPage - 1}" end="${currentPage + 1}" step="1">
                <a class="ui button" href="/report?currentPage=${i}&${strAboutForm}">${i}</a>
              </c:forEach>
            </c:otherwise>
          </c:choose>
        </c:when>
      </c:choose>

      <%-- 最終ページのボタン --%>
      <a class="ui button" href="/report?currentPage=${maxPage}&${strAboutForm}"><c:out value="${maxPage}" /></a>
    </c:if>
  </div>
</div>

  </div>
</div>
</body>
</html>
