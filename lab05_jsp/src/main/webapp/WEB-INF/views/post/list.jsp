<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    <%@ taglib prefix="c" uri= "jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Lab 5</title>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
    crossorigin="anonymous">
</head>
<body>
    <div class="container-fluid">
        <c:set var="pageTitle" value="Post 목록" scope="page" />
        <%@ include file="../fragments/header.jspf"%>

        <main>
            <div class="mt-2 card">
                <div class="card-header text-center">
                    <h2>POSTS</h2>
                </div>
                <div class="card-body">
                    <table class="table table-striped table-hover">
                        <caption></caption>
                        <!-- 테이블 제목 -->
                        <thead>
                            <!-- 테이블 헤더: 컬럼 이름들 -->
                            <tr>
                                <!-- table row(행) -->
                                <th>No</th>
                                <th>Title</th>
                                <th>Author</th>
                                <th>ModifiedTime</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="p" items="${ posts }">
                                <tr>
                                    <%-- EL은 프로퍼티 이름으로 getter 메서드를 찾음. --%>
                                    <td>${ p.id }</td>      
                                    <td>
                                        <!-- c:url은 context root를 붙이지 않음. -->
                                        <c:url var="postDetailPage" value="/post/details">
                                            <c:param name="id" value="${p.id}"></c:param>
                                        </c:url>
                                        <a href="${postDetailPage}">${ p.title }</a>
                                    </td>
                                    <td>${ p.author }</td>
                                    <td>${ p.modifiedTime }</td>
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
                </div>
            </div>

        </main>

    </div>


    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>