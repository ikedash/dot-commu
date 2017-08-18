<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<html>

<head>
  <title>投稿内容画面</title>
  <link href="/webjars/Semantic-UI/2.2.9/semantic.min.css" rel="stylesheet">
  <link href="/css/main.css" rel="stylesheet">
  <link href="/css/report.css" rel="stylesheet">
</head>

<body>
  <jsp:include page="/WEB-INF/views/common/header.jsp" flush="true" />
  <div class="full height">
    <div class="ui main container">
      <div class="ui fluid card">
        <div class="content">
          <div class="ui two colum">
            <div class="colum">
              <a href="/profile?userId=${report.user_id}">
                <c:out value="${report.last_name}" />&nbsp;<c:out value="${report.first_name}" />
              </a>
            </div>
            <div class="colum">
              <div class="right floated">
                <fmt:formatDate value="${report.created_at}" pattern="yyyy年MM月dd日(E) HH:MM:ss" timeZone="JST"/>
              </div>
            </div>
          </div>
        </div>
        <div class="content">
          <h2 class="ui dividing header"><c:out value="${report.title}" /></h2>
          <pre class="msg"><c:out value="${report.report_body}" /></pre>
        </div>
        <div class="content">
          <h5>本日の研修満足度</h5>
          <c:out value="${report.satisfaction}" />
          <h5>満足度の理由</h5>
          <pre><c:out value="${report.cause}" /></pre>
        </div>
        <div class="extra content">
          <a class="ui label"><c:out value="${report.tag}" /></a>
        </div>
      </div>

      <c:if test="${report.user_id == myself}">
        <a href="/report/update?reportId=${report.report_id}" class="ui fluid green button">編集</a>
      </c:if>

      <div class="ui divider"></div>

      <div class="ui comments">
        <c:forEach items="${comments}" var="comment" varStatus="status">
          <div class="comment">
            <a class="avatar">
              <img src="/images/people/small/${comment.image}.png">
            </a>
            <div class="content">
              <a class="author" href="/profile?userId=${comment.user_id}">
                <c:out value="${comment.last_name}" />&nbsp;<c:out value="${comment.first_name}" />
              </a>
              <div class="metadata">
                <div class="date">
                  <fmt:formatDate value="${comment.created_at}" pattern="yyyy年MM月dd日(E) HH:MM:ss" timeZone="JST"/>
                </div>
              </div>
              <div class="text">
                <pre class="msg"><c:out value="${comment.comment_body}" /></pre>
              </div>
              <c:if test="${comment.user_id == myself}">
                <div class="actions">
                  <form name="deleteComment${status.index}" action="/report/detail/deleteComment" method="post">
                    <input type="hidden" name="reportId" value="${report.report_id}" >
                    <input type="hidden" name="commentId" value="${comment.comment_id}" >
                    <a class="edit" href="/report/detail/updateComment?reportId=${report.report_id}&commentId=${comment.comment_id}">Edit</a>
                    <a class="delete" href="javascript:document.deleteComment${status.index}.submit()">Delete</a>
                  </form>
                </div>
              </c:if>
            </div>
          </div>
        </c:forEach>
      </div>

      <input type="hidden" name="reportId" value="${report.report_id}" >
      <a href="/report/detail/createComment?reportId=${report.report_id}" class="ui fluid primary submit labeled icon button"><i class="icon edit"></i>コメントする</a>
    </div>
  </div>
</body>
</html>
