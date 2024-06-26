<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    <%@ taglib prefix="c" uri= "jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Spring Legacy 2</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
      crossorigin="anonymous">
</head>
<body>
    <div class="container-fluid">
        <c:set var="pageTitle" value="Post Modify" />
        <%-- scope의 기본값은 page이기 때문에 생략 가능. --%>
        <%@ include file="../fragments/header.jspf"%>

        <main>
            <div class="mt-2 card">
                <div class="card-header">
                    <h2>포스트 수정 페이지</h2>
                </div>
                <div class="card-body">
                    <form id="modifyForm">
                        <div class="mt-2">
                            <label for="id" class="form-label">번호</label>
                            <input id="id" class="form-control"
                                type="text" name="id" value="${post.id}"
                                readonly />
                        </div>
                        <div class="mt-2">
                            <label for="title" class="form-label">제목</label>
                            <input id="title" class="form-control"
                                type="text" name="title"
                                value="${post.title}" />
                        </div>
                        <div class="mt-2">
                            <label for="content" class="form-label">내용</label>
                            <textarea id="content" class="form-control"
                                name="content" rows="5">${post.content}</textarea>
                        </div>
                        <div class="mt-2 d-none">
                            <%-- d-none: 해당 div를 화면에 나타내지 않음. --%>
                            <label for="author" class="form-label">작성자</label>
                            <input id="author" class="form-control"
                                type="text" value="${post.author}"
                                readonly />
                        </div>
                    </form>
                </div>
               <%--  <c:if test="${post.author eq signedInUser}"> --%>
                    <div class="card-footer">
                        <button id="btnDelete"
                            class="btn btn-outline-danger">삭제</button>
                        <button id="btnUpdate"
                            class="btn btn-outline-success">업데이트</button>
                    </div>
               <%-- </c:if> --%>
            </div>
        </main>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
            crossorigin="anonymous"></script>
    <%-- c:url을 설정해서 script src를 EL로 작성. --%>
    <c:url var="post_modify_js" value="/js/post_modify.js" /> <%-- 맨 앞 /를 webapp이라고 생각. --%>         
    <script src="${post_modify_js}"></script> 
           
</body>
</html>