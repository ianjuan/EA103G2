<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

		<nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container">
                    <a class="navbar-brand" href="#">愛租I-ZU</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div class="navbar-nav ml-auto">
                            <a class="nav-item nav-link active" href="<%=request.getContextPath()%>/front-end/selectpage/SELECTpage.jsp">尋找房源<span class="sr-only">(current)</span></a>
                            <a class="nav-item nav-link" href="<%=request.getContextPath()%>/front-end/mapserach/gmap2.jsp">地圖找房</a>
                            <a class="nav-item nav-link" href="https://www.gamer.com.tw/">我的房屋</a>
                            <li class="nav-item dropdown">
                                <span data-toggle="dropdown" class="member">
                                    <input type="image" src="https://www.flaticon.com/svg/static/icons/svg/236/236831.svg" class="memberpic" />
                                    <span class="membername">劉德華</span>
                                </span>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                    <a class="dropdown-item" href="#">最新通知</a>
                                    <a class="dropdown-item" href="#">個人資訊</a>
                                    <a class="dropdown-item" href="#">我的錢包</a>
                                    <a class="dropdown-item" href="#">登出</a>
                                </div>
                            </li>
                        </div>
                    </div>
                </div>
            </nav> 

</html>