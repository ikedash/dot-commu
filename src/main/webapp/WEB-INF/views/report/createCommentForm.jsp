<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
  <title>コメント投稿画面</title>
  <link href="/webjars/Semantic-UI/2.2.9/semantic.min.css" rel="stylesheet">
  <link href="/css/main.css" rel="stylesheet">
</head>

<body>
  <jsp:include page="/WEB-INF/views/common/header.jsp" flush="true" />
  <div class="full height">
    <div class="ui main container">
      <h1 class="ui center aligned header">コメント投稿画面</h1>
      <form class="ui form" action="/report/detail/createComment" method="post">
        <div class="field">
          <label>内容</label>
          <textarea name="commentBody"><c:out value="${commentForm.commentBody}" /></textarea>
          <p class="error-messages"><c:out value="${errors.commentBody}" /></p>
        </div>
        <input type="hidden" name="reportId" value="${reportId}" />
        <div class="field">
          <input type="submit" class="ui fluid green button" value="投稿する" />
        </div>
        <div class="field">
          <a class="ui fluid button" href="/report/detail?reportId=${reportId}">戻る</a>
        </div>
      </form>
    </div>
  </div>
</body>
</html>
