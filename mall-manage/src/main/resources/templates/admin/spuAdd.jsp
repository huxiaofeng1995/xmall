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
        function cimg(index){
            $("#file_"+index).click();//触发文件input的点击事件
            if(window.URL.createObjectURL) {
                $("#file_" + index).change(function () {
                    //获得图片对象
                    var blob_img = $("#file_" + index)[0].files[0];//Jquery对象转dom对象 $x.[0]
                    var url = window.URL.createObjectURL(blob_img);
                    //替换img
                    $("#img_" + index).attr("src", url);
                    add_image(index);
                });
            }else {
                $("#file_" + index).change(function () {
                    //获得图片对象
                    var blob_img = $("#file_" + index)[0].files[0];
                    var fr = new FileReader();
                    fr.onload = function () {
                        $("#img_" + index).attr("src", this.result);
                    }
                    fr.readAsDataURL(blob_img);
                    add_image(index);
                });
            }
        }

        function add_image(index) {
            index++;
            var a="<input id='file_" + index +"' type='file' name='files' style='display: none'/>"
            var b="<img id='img_" + index +"' style='cursor: pointer' src='image/upload_hover.png' width='100px' onclick='cimg("+index+")'/>"
            $("#dimg").append(a+b);
        }
	</script>
	<title>硅谷商城</title>
</head>
<body>
spu信息添加${spu.flbh1}|${spu.flbh2}|${spu.pp_id}
<hr>
<!--注意要添加 enctype,才能提交文件-->
<form action="spu_add.do" enctype="multipart/form-data" method="post">
	<input name="flbh1" type="hidden" value="${spu.flbh1}"/>
	<input name="flbh2" type="hidden" value="${spu.flbh2}"/>
	<input name="pp_id" type="hidden" value="${spu.pp_id}"/>
	商品名称：<input name="shp_mch" type="text" /><br>
	商品描述：<textarea name="shp_msh" rows="10" cols="50"></textarea><br>
	商品图片：<br>
	<div id = "dimg">
		<input id="file_0" type="file" name="files" style="display: none"/>
		<img id="img_0" style="cursor: pointer" src="image/upload_hover.png" width="100px" onclick="cimg(0)"/></div><!-- 这里的div结束标签不能换行，换行会导致append新元素时有空格间距-->
	<input type="submit" value="提交"/>
</form>
</body>
</html>