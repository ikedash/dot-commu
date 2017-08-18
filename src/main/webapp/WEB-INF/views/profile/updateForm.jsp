<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>
  <title>プロフィール編集</title>
  <link href="/webjars/Semantic-UI/2.2.9/semantic.min.css" rel="stylesheet">
  <link href="/css/main.css" rel="stylesheet">
</head>

<body>
  <jsp:include page="/WEB-INF/views/common/header.jsp" flush="true" />
  <div class="full height">
    <div class="ui main container">
      <form class="ui form" action="/profile/update" method=POST>
        <div class="field">
          <label>名字</label>
          <input type="text" name="lastName" value="${profileForm.lastName}" placeholder="必須"/>
          <p class="error-messages"><c:out value="${errors.lastName}" /></p>
        </div>

        <div class="field">
          <label>名前</label>
          <input type="text" name="firstName" value="${profileForm.firstName}" placeholder="必須"/>
          <p class="error-messages"><c:out value="${errors.firstName}" /></p>
        </div>

        <div class="field">
          <label>出身地</label>
          <input type="text" name="birthplace" value="${profileForm.birthplace}"/>
        </div>

        <div class="field">
          <label>好きな食べ物</label>
          <input type="text" name="favoriteFood" value="${profileForm.favoriteFood}"/>
        </div>

        <div class="field">
          <label>嫌いな食べ物</label>
          <input type="text" name="hatedFood" value="${profileForm.hatedFood}"/>
        </div>

        <div class="field">
          <label>血液型</label>
          <input type="text" name="bloodType" value="${profileForm.bloodType}"/>
        </div>

        <div class="field">
          <label>生年月日</label>
          <input type="text" name="birthday" value="${profileForm.birthday}" placeholder="yyyy/MM/dd"/>
          <p class="error-messages"><c:out value="${errors.birthday}" /></p>
        </div>

        <div class="field">
          <label>いってみたい国</label>
          <input type="text" name="country" value="${profileForm.country}"/>
        </div>

        <div class="field">
          <label>自己紹介</label>
          <textarea id="introduction" name="introduction">${profileForm.introduction}</textarea>
        </div>

        <c:choose>
          <c:when test="${profileForm.image == 'boy1'}">
            <c:set var="image_boy1_checked">checked="checked"</c:set>
          </c:when>
          <c:when test="${profileForm.image == 'boy2'}">
            <c:set var="image_boy2_checked">checked="checked"</c:set>
          </c:when>
          <c:when test="${profileForm.image == 'girl1'}">
            <c:set var="image_girl1_checked">checked="checked"</c:set>
          </c:when>
          <c:when test="${profileForm.image == 'girl2'}">
            <c:set var="image_girl2_checked">checked="checked"</c:set>
          </c:when>
          <c:when test="${profileForm.image == 'girl3'}">
            <c:set var="image_girl3_checked">checked="checked"</c:set>
          </c:when>
          <c:when test="${profileForm.image == 'girl4'}">
            <c:set var="image_girl4_checked">checked="checked"</c:set>
          </c:when>
        </c:choose>

        <div class="four wide field">
          <label>画像</label>
          <table class="ui table"><tr>
            <td>
              <input type="radio" name="image" value="boy1" <c:out value="${image_boy1_checked}"/>/>
              <img class="ui mini circular image" src="/images/people/small/boy1.png">
            </td>
            <td>
              <input type="radio" name="image" value="boy2" <c:out value="${image_boy2_checked}"/>/>
              <img class="ui mini circular image" src="/images/people/small/boy2.png">
            </td>
            <td>
              <input type="radio" name="image" value="girl1" <c:out value="${image_girl1_checked}"/>/>
              <img class="ui mini circular image" src="/images/people/small/girl1.png">
            </td>
            <td>
              <input type="radio" name="image" value="girl2" <c:out value="${image_girl2_checked}"/>/>
              <img class="ui mini circular image" src="/images/people/small/girl2.png">
            </td>
            <td>
              <input type="radio" name="image" value="girl3" <c:out value="${image_girl3_checked}"/>/>
              <img class="ui mini circular image" src="/images/people/small/girl3.png">
            </td>
            <td>
              <input type="radio" name="image" value="girl4" <c:out value="${image_girl4_checked}"/>/>
              <img class="ui mini circular image" src="/images/people/small/girl4.png">
            </td>
          </tr></table>
        </div>

        <div class="field">
          <input type="hidden" name="userId" value="${userId}" />
          <input class="ui fluid green button" type="submit" value="編集する" />
        </div>
        <div class="field">
          <a href="/profile?userId=${userId}" class="ui fluid button">戻る</a>
        </div>
      </form>
    </div>
  </div>
</body>
</html>
