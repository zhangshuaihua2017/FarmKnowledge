<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>知识农场后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/css/font.css">
    <link rel="stylesheet" href="${ctx}/css/xadmin.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/Swiper/3.4.2/css/swiper.min.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/Swiper/3.4.2/js/swiper.jquery.min.js"></script>
    <script src="${ctx}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/js/xadmin.js"></script>
    
    <script type="text/javascript">
    	//修改用户信息
    	function updateUser(){
    		var newAccout = $("#accout").val();
    		var nickName = $("#nickName").val();
    		var photo = $("#photo").val();
    		if(newAccout == "" || nickName == "" || photo == ""){
    			layer.msg('输入框不能为空');
    		}else{
	    		$.post("${ctx}/admin/user/updateUser",{"oldAccout":"${user.accout}","newAccout":newAccout,"nickName":nickName,"photo":photo},function(data){
	    			if(data == "succeed"){
						x_admin_close();
	    			}else if(data == "fail"){
	    				layer.msg('修改失败');
	    			}else if(data == "already"){
	    				layer.msg('该账号已存在');
	    			}
	    	 	}) 
    		}
    	}
    </script>

</head>
<body>
    <!-- 中部开始 -->
    <div class="wrapper">
        <!-- 右侧主体开始 -->
        <div class="page-content">
          <div class="content">
            <!-- 右侧内容框架，更改从这里开始 -->
            <form class="layui-form" action="javascript:updateUser()">
                <div class="layui-form-item">
                   <label for="L_username" class="layui-form-label">
                    	<font color="red">*</font>账号
                    </label>
                    <div class="layui-input-inline">
                        <input id="accout" type="text" id="L_username" name="username" required lay-verify="required"
                        autocomplete="off" class="layui-input" value="${user.accout}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_username" class="layui-form-label">
                    	<font color="red">*</font>名称
                    </label>
                    <div class="layui-input-inline">
                        <input id="nickName" type="text" id="L_username" name="username" required lay-verify="required"
                        autocomplete="off" class="layui-input" value="${user.nickName}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_city" class="layui-form-label">
                    	<font color="red">*</font>头像
                    </label>
                    <div class="layui-input-inline">
                        <input id="photo" type="text" id="L_city" name="city" autocomplete="off" 
                        class="layui-input" value="${user.photo}">
                    </div>
                    <div class="layui-form-mid layui-word-aux">
                    	*头像地址
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label for="L_sign" class="layui-form-label">
                    	签名
                    </label>
                    <div class="layui-input-block">
                        <textarea placeholder="随便写些什么刷下存在感" id="L_sign" name="sign" autocomplete="off"
                        class="layui-textarea" style="height: 80px;"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_sign" class="layui-form-label">
                    </label>
                    <button class="layui-btn" key="set-mine" lay-filter="save" lay-submit>
                    	 保存          
                    </button>
                </div>
            </form>
            <!-- 右侧内容框架，更改从这里结束 -->
          </div>
        </div>
        <!-- 右侧主体结束 -->
    </div>
    <!-- 中部结束 -->
    <script>
    //百度统计可去掉
    var _hmt = _hmt || [];
    (function() {
      var hm = document.createElement("script");
      hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
      var s = document.getElementsByTagName("script")[0]; 
      s.parentNode.insertBefore(hm, s);
    })();
    </script>
</body>
</html>