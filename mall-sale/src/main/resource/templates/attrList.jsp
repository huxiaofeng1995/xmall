<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>信息管理系统界面</title>
    <script language="JavaScript" src="js/jquery/2.0.0/jquery.min.js"></script>
    <script src="js/vue/vue.js"></script>
    <script src="js/axios/0.17.1/axios.min.js"></script>
    <title>小红商城</title>
</head>
<script type="text/javascript">
    $(function () {
        $("#attrParam").on("click","a i",function () {
            var id = $(this).attr("attrid");
            $("#attrItem"+id).show();
            $(this).parent().remove();
            doajax();
        })
    });

    function get_list_byattr(shxm_id,shxzh_id,name) {

        //页面显示
        $("#attrItem"+shxm_id).hide();
        $("#attrParam").append("<a href='javascript:(0);' style='border:1px solid black;display: inline-block;height: 25px;' ><input name='shxparam' type='hidden' value='{\"shxm_id\":"+shxm_id+",\"shxzh_id\":"+shxzh_id+"}'><span style='height: 23px;line-height: 23px;'>"+name+"</span><i style='float:right;display:inline-block;height: 25px;width: 25px;background: url(/images/search.ele.png) no-repeat 7px -140px;' attrid='"+shxm_id+"'></i></a>");
        //异步请求刷新页面
        doajax1();
    }

    function doajax() {
        //构造参数
        var data = {"flbh2":${flbh2}};
        $("#attrParam input[name='shxparam']").each(function(i,item){
            //这里获取到的每个item对象都是input的Dom对象，不是jq对象
            var json = $.parseJSON(item.value);
            data["list_attr["+i+"].shxm_id"] = json.shxm_id;
            data["list_attr["+i+"].shxzh_id"] = json.shxzh_id;
        });
        console.log(data);
        //发送ajax请求
        $.get("get_list_by_attr.do",data,function (data) {
            $("#skuListInner").html(data);
        })
    }
    function doajax1(){
        var data = "flbh2="+${flbh2};
        $("#attrParam input[name='shxparam']").each(function(i,item){
            var json = $.parseJSON(item.value);
            data += "&list_attr["+i+"].shxm_id="+json.shxm_id+"&list_attr["+i+"].shxzh_id="+json.shxzh_id;
        });
        console.log(data);
        //发送ajax请求
        $.get("get_list_by_attr.do",data,function (data) {
            $("#skuListInner").html(data);
        })
    }
</script>
<body>
<div style="margin: 0 auto;width: 1390px;">
    <div id="attrParam"></div>
    <hr>
    <div style="background: #f1f1f1;"><em style="font-size: 14px;font-weight: bold;font-style: normal;height: 30px;line-height: 30px;">商品筛选</em><span id="sku_count" style="font-size: 12px;font-family: Arial;margin-left: 30px;">共${count}件商品</span></div>
    <c:forEach items="${list_attr}" var="attr">
        <div id="attrItem${attr.id}" style="height: 35px;line-height: 35px;border-bottom: 1px dotted grey;">
            <div style="width: 110px;display: inline-block;"><span>${attr.shxm_mch}:</span></div>
            <c:forEach items="${attr.list_value}" var="val">
                <a id="attr${attr.id}" style="margin-left: 30px;" href="javascript:(0);" onclick="get_list_byattr(${attr.id},${val.id},'${val.shxzh}${val.shxzh_mch}')"> ${val.shxzh} ${val.shxzh_mch}</a>
            </c:forEach>
        </div>
    </c:forEach>
</div>
</body>
</html>
