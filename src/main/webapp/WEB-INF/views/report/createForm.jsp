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

        <c:choose>
          <c:when test="${reportForm.satisfaction == 'VeryGood'}">
            <c:set var="satisfaction_VeryGood_checked">checked="checked"</c:set>
          </c:when>
          <c:when test="${reportForm.satisfaction == 'Good'}">
            <c:set var="satisfaction_Good_checked">checked="checked"</c:set>
          </c:when>
          <c:when test="${reportForm.satisfaction == 'Medium'}">
            <c:set var="satisfaction_Medium_checked">checked="checked"</c:set>
          </c:when>
          <c:when test="${reportForm.satisfaction == 'Bad'}">
            <c:set var="satisfaction_Bad_checked">checked="checked"</c:set>
          </c:when>
          <c:when test="${reportForm.satisfaction == 'VeryBad'}">
            <c:set var="satisfaction_VeryBad_checked">checked="checked"</c:set>
          </c:when>
        </c:choose>

        <div class="field">
          <label>本日の満足度</label>
          <div class="ui radio checkbox">
            <input type="radio" name="satisfaction" value="VeryGood" <c:out value="${satisfaction_VeryGood_checked}" />/>
            <label>VeryGood</label>
          </div>
          <div class="ui radio checkbox">
            <input type="radio" name="satisfaction" value="Good"<c:out value="${satisfaction_Good_checked}" />/>
            <label>Good</label>
          </div>
          <div class="ui radio checkbox">
            <input type="radio" name="satisfaction" value="Medium"<c:out value="${satisfaction_Medium_checked}"/>/>
            <label>Medium</label>
          </div>
          <div class="ui radio checkbox">
            <input type="radio" name="satisfaction" value="Bad"<c:out value="${satisfaction_Bad_checked}"/>/>
            <label>Bad</label>
          </div>
          <div class="ui radio checkbox">
            <input type="radio" name="satisfaction" value="VeryBad"<c:out value="${satisfaction_VeryBad_checked}"/>/>
            <label>VeryBad</label>
          </div>
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
            <c:choose>
              <c:when test="${reportForm.tag == tag.tagName}">
                <option value="${tag.tagName}" selected>${tag.tagName}</option>
              </c:when>
              <c:otherwise>
                <option value="${tag.tagName}">${tag.tagName}</option>
              </c:otherwise>
            </c:choose>
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
