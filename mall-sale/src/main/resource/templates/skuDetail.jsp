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
    <link rel="stylesheet" type="text/css" href="css/css.css">
    <link rel="shortcut icon" type="image/icon" href="images/jd.ico">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
    function get_skuid(dom) {

        $(dom).parent().parent().children().removeClass("attrselected");
        $(dom).parent().addClass("attrselected");
        var $color = $(".colorItem .attrselected");
        var $version = $(".versionItem .attrselected");
        if($color.length==1&&$version.length==1){
            var color_id = $color.attr("data-id");
            var version_id =$version.attr("data-id");
            $.get("/get_skuId.do?color_id="+color_id+"&version_id="+version_id,function (data) {
                var spu_id = '${detail_sku.spu.id}';
                if(data != "-1"){
                    location.href="goto_sku_detail.do?sku_id="+data+"&spu_id="+spu_id;
                }else {
                    console.log(data);
                }
            })
        }
    }
</script>
<title>硅谷商城</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="top_img">
    <img src="images/top_img.jpg" alt="">
</div>
<jsp:include page="searchArea.jsp"></jsp:include>
<div class="Dbox">
    <div class="box">
        <div class="left">
            <div class="timg"><img src="upload/image/${detail_sku.spu.shp_tp}" alt=""></div>
            <div class="timg2">
                <div class="lf"><img src="images/lf.jpg" alt=""></div>
                <div class="center">
                    <c:forEach items="${detail_sku.list_image}" var="img">
                        <span><img src="upload/image/${img.url}" alt="" width="50px" height="50px"></span>
                    </c:forEach>
                </div>
                <div class="rg"><img src="images/rg.jpg" alt=""></div>
            </div>
            <div class="goods"><img src="images/img_6.jpg" alt=""></div>
        </div>
        <div class="cent">
            <div class="title">${detail_sku.sku_mch}</div>
            <div class="bg">
                <p>市场价：<strong>${detail_sku.jg}</strong></p>
                <p>促销价：<strong>${detail_sku.jg}</strong></p>
            </div>
            <div class="clear">
                <div class="min_t">选择类型：</div>
                <c:forEach items="${list_sku}" var="sku">
                    <div class="min_mx"><a href="goto_sku_detail.do?sku_id=${sku.id}&spu_id=${sku.shp_id}" >${sku.sku_mch}</a></div>
                </c:forEach>
            </div>
            <div class="clear colorItem">
                <div class="min_t">颜色：</div>
                <c:forEach items="${obj_spu.list_color}" var="color">
                    <div class="min_mx " data-id="${color.id}"><a href="javascript:;" onclick="get_skuid(this)" >${color.shp_ys}</a></div>
                </c:forEach>
            </div>
            <div class="clear versionItem">
                <div class="min_t" >版本：</div>
                <c:forEach items="${obj_spu.list_version}" var="version">
                    <div class="min_mx" data-id="${version.id}"><a href="javascript:;" onclick="get_skuid(this)" >${version.shp_bb}</a></div>
                </c:forEach>
            </div>
            <div class="clear" style="margin-top:20px;">
                <div class="min_t" style="line-height:36px">数量：</div>
                <div class="num_box">
                    <input type="text" name="num" value="1" style="width:40px;height:32px;text-align:center;float:left">
                    <div class="rg">
                        <img src="images/jia.jpg" id='jia' alt="">
                        <img src="images/jian.jpg" id='jian' alt="">
                    </div>
                </div>
            </div>
            <div class="clear" style="margin-top:20px;">
                <img src="images/mai.jpg" alt="">
                <img src="images/shop.jpg" alt="">
            </div>
        </div>
    </div>
</div>
<div class="Dbox1">
    <div class="boxbottom">
        <div class="top">
            <span>商品详情</span>
            <span>评价</span>
        </div>
        <div class="btm">
            <div>${detail_sku.spu.shp_msh}</div>
            <c:forEach items="${detail_sku.list_av_name}" var="av_name">
                ${av_name.shxm_mch}:${av_name.shxzh_mch}<br>
            </c:forEach>
            <div></div>
        </div>
    </div>
</div>
<div class="footer">
    <div class="top"></div>
    <div class="bottom"><img src="images/foot.jpg" alt=""></div>
</div>
</body>
</html>
