<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
  <title>編集内容確認画面</title>
  <link href="/webjars/Semantic-UI/2.2.9/semantic.min.css" rel="stylesheet">
  <link href="/css/main.css" rel="stylesheet">
  <link href="/css/report.css" rel="stylesheet">
</head>

<body>
  <jsp:include page="/WEB-INF/views/common/header.jsp" flush="true" />
  <div class="full height">
    <div class="ui main container">
      <h1 class="ui center aligned header">編集する内容を確認してください。</h1>
      <form class="ui form" action="/report/update" method="post">
        <div class="field">
          <label>タイトル</label>
          <p class="msg">${reportForm.title}</p>
          <input type="hidden" name="title" value="${reportForm.title}"/>
        </div>

        <div class="field">
          <label>内容</label>
          <pre class="msg"><c:out value="${reportForm.reportBody}" /></pre>
          <input type="hidden" name="reportBody" value="${reportForm.reportBody}" />
        </div>

        <div class="field">
          <label>本日の研修満足度</label>
          <p class="msg"><c:out value="${reportForm.satisfaction}" /></p>
          <input type="hidden" name="satisfaction" value="${reportForm.satisfaction}"/>
        </div>

        <div class="field">
          <label>満足度の理由</label>
          <pre class="msg"><c:out value="${reportForm.cause}" /></pre>
          <input type="hidden" name="cause" value="${reportForm.cause}"/>
        </div>

        <div class="field">
          <label>タグ</label>
          <p class="msg"><c:out value="${reportForm.tag}" /></p>
          <input type="hidden" name="tag" value="${reportForm.tag}"/>
        </div>

        <div class="field">
          <input type="hidden" name="reportId" value="${reportId}">
          <input type="submit" class="ui fluid green button" value="編集する" />
        </div>

        <div class="field">
          <input type="submit" class="ui fluid button" value="戻る" name="redo"/>
        </div>
      </form>
    </div>
  </div>
</body>
</html>
