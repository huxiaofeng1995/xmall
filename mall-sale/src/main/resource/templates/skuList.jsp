<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
    $(function () {
        $("#sku_count").text("共" + ${count} + "件商品");
    })
</script>
<title>硅谷商城</title>
</head>
<body>
<c:forEach items="${list_sku}" var="item">
<div style="margin-top:10px;margin-left:10px;border: 1px solid red;float:left;width: 250px;height: 250px;">
    <img src="upload/image/${item.spu.shp_tp}" width="180px" height="180px"><br>
    <a href="goto_sku_detail.do?sku_id=${item.id}&spu_id=${item.spu.id}" target="_blank">${item.sku_mch}</a><br>
    ${item.jg}<br>
    ${item.sku_xl}
</div>
</c:forEach>
</body>
</html>
