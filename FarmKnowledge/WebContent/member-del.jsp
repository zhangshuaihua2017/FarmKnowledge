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
    
    <style>
    	.page{
    		margin-right:25px;
    	}
    </style>
    
    <script>
		//初始化左侧菜单（用户管理）
		window.onload = function(){
			$("#initUserManager").attr("class","sub-menu opened");
			$("#initUserManager2").attr("class","current");
		}
		
		//恢复单个用户信息
        function recoveryOneUser(id){
            layer.confirm('确认要恢复吗？',function(index){
            	$.post("${ctx}/admin_user/recoveryOneUser",{"userId":id},function(data){
	    			if(data == "succeed"){
	    				window.location.href="${ctx}/admin_user/findUserPage?exist=0";
	    			}else if(data == "fail"){
	    				alert('恢复失败');
	    			}
	    		})    
            });
        }
		
        //恢复批量用户信息
        function recoveryMultiUser() {
            var arrRecovery = document.getElementsByName("checkBox");
            var recoveryStr="";
		    for(i in arrRecovery){
			   if(arrRecovery[i].checked){
				   recoveryStr = recoveryStr + arrRecovery[i].value + ",";
			   }
		    }
            layer.confirm('确认要批量恢复吗？',function(index){
        	   $.post("${ctx}/admin_user/recoveryMultiUser",{"recoveryStr":recoveryStr},function(data){
		    	 	 if(data == "succeed"){
		    			 window.location.href="${ctx}/admin_user/findUserPage?exist=0";
		    		 }else if(data == "fail"){
		    			 alert('删除失败');
		    		 }
	    	   }) 
            });
         }
        
        //彻底删除用户信息
        function deleteThoroughUser(id){
            layer.confirm('彻底删除无法恢复，确认要删除数据吗？',function(index){
            	$.post("${ctx}/admin_user/deleteThoroughUser",{"userId":id},function(data){
	    			if(data == "succeed"){
	    				window.location.href="${ctx}/admin_user/findUserPage?exist=0";
	    			}else if(data == "fail"){
	    				alert('删除失败');
	    			}
	    		}) 
            });
        }
    </script>
    
</head>
<body>
    <!-- 顶部开始 -->
    	<%@ include file="/layout/header.jsp"%>
    <!-- 顶部结束 -->
    <!-- 中部开始 -->
    <div class="wrapper">
        <!-- 左侧菜单开始 -->
        	<%@ include file="/layout/menuLeft.jsp"%>
        <!-- 左侧菜单结束 -->
        <!-- 右侧主体开始 -->
        <div class="page-content">
          <div class="content">
            <!-- 右侧内容框架，更改从这里开始 -->
            <form class="layui-form xbs" action="${ctx}/admin_user/findUserPage">
                <div class="layui-form-pane" style="text-align: center;">
                  <div class="layui-form-item" style="display: inline-block;">
                    <div class="layui-input-inline">
                      <input type="text" name="accout" placeholder="请输入账号" autocomplete="off" class="layui-input" value="${param.accout}">
                      <input type="hidden" name="exist" value="0"/>
                    </div>
                    <div class="layui-input-inline" style="width:80px">
                        <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
                    </div>
                  </div>
                </div> 
            </form>
            <xblock>
            	<button class="layui-btn layui-btn-danger" onClick="recoveryMultiUser()">
            		<i class="layui-icon">&#xe640;</i>批量恢复
            	</button>
            	<span class="x-right" style="line-height:40px">共有数据：${userPage.totalRow} 条</span>
            </xblock>
            <table class="layui-table">
                <thead >
                    <tr>
                        <th></th>
                        <th style="text-align:center;">用户ID</th>
                        <th style="text-align:center;">账号</th>
                        <th style="text-align:center;">密码</th>
                        <th style="text-align:center;">名称</th>
                        <th style="text-align:center;">头像</th>
                        <th style="text-align:center;">等级</th>
                        <th style="text-align:center;">经验</th>
                        <th style="text-align:center;">年级</th>
                        <th style="text-align:center;">金币</th>
                        <th style="text-align:center;">是否在线</th>
                        <th style="text-align:center;">状态</th>
                        <th style="text-align:center;">操作</th>
                    </tr>
                </thead>
                <tbody align="center">
                    <c:forEach var="page" items="${userPage.list}">
	                    <tr>
	                        <td><input type="checkbox" value="${page.id}" name="checkBox"></td>
	                        <td>${page.id}</td>
	                        <td>${page.accout}</td>
	                        <td>${page.password}</td>
	                        <td>${page.nickName}</td>
	                        <td><img style="width:50px;height:50px" src="${page.photo}" /></td>
	                        <td>${page.level}</td>
	                        <td>${page.experience}</td>
	                        <td>${page.grade}</td>
	                        <td>${page.money}</td>
	                        <td>离线</td>
	                        <td class="td-status">
	                        	<span class="layui-btn layui-btn-danger layui-btn-mini">已删除</span>
	                        </td>
	                        <td class="td-manage" align="center">
	                            <a style="text-decoration:none" onclick="recoveryOneUser(${page.id})" href="javascript:;" title="恢复">
	                                <i class="layui-icon">&#xe618;</i>
	                            </a>
	                            <a title="彻底删除" href="javascript:;" onclick="deleteThoroughUser(${page.id})" style="text-decoration:none">
	                                <i class="layui-icon">&#xe640;</i>
	                            </a>
	                        </td>
                    	</tr>
                    </c:forEach>
                </tbody>
            </table>
            <!-- 右侧内容框架，更改从这里结束 -->
          </div>
          <!-- 分页处理开始 -->
          <!-- 上一页 -->
	          	<c:choose>
	        		<c:when test="${userPage.pageNumber-1 > 0}">
	        			<c:set var="prePage" value="${userPage.pageNumber-1}"></c:set>
	        		</c:when>
	        		<c:when test="${userPage.pageNumber-1 <= 0}">
	        			<c:set var="prePage" value="1"></c:set>
	        		</c:when>
	        	</c:choose>
	        	<!-- 查询结果不为空 -->
	          	<c:if test="${userPage.totalPage != 0}">
	          		<!-- 下一页 -->
	          		<c:choose>
	          			<c:when test="${userPage.pageNumber+1 <= userPage.totalPage}">
	          				<c:set var="nextPage" value="${userPage.pageNumber+1}"></c:set>
	          			</c:when>
	          			<c:when test="${userPage.pageNumber+1 > userPage.totalPage}">
	          				<c:set var="nextPage" value="${userPage.totalPage}"></c:set>
	          			</c:when>
	          		</c:choose>
	          		<!-- 末页 -->
	          		<c:set var="lastPage" value="${userPage.totalPage}"></c:set>
	          	</c:if>
	          	<!-- 查询结果为空 -->
	          	<c:if test="${userPage.totalPage == 0}">
	          		<!-- 下一页 -->
	          		<c:set var="nextPage" value="1"></c:set>
	          		<!-- 末页 -->
	          		<c:set var="lastPage" value="1"></c:set>
	          	</c:if>
			  <div align="center">
				<a  class="page" style="margin-left:25px;" href="${ctx}/admin_user/findUserPage?accout=${param.accout}&&pageNumber=1&&pageSize=${userPage.pageSize}&&exist=0">首页</a>
				<a  class="page" href="${ctx}/admin_user/findUserPage?accout=${param.accout}&&pageNumber=${prePage}&&pageSize=${userPage.pageSize}&&exist=0">上一页</a>
				<a  class="page" href="${ctx}/admin_user/findUserPage?accout=${param.accout}&&pageNumber=${nextPage}&&pageSize=${userPage.pageSize}&&exist=0">下一页</a>
				<a  class="page" href="${ctx}/admin_user/findUserPage?accout=${param.accout}&&pageNumber=${lastPage}&&pageSize=${userPage.pageSize}&&exist=0">末页</a>			
			  </div>
			  <div align="center" style="margin-top:20px;">
				<span style="margin-right:10px;">
					<!-- 查询结果不为空 -->
					<c:if test="${userPage.totalPage != 0}">
						${userPage.pageNumber}
					</c:if>
					<!-- 查询结果为空 -->
					<c:if test="${userPage.totalPage == 0}">
						0
					</c:if>
				</span>
				<span>/</span>
				<span style="margin-left:10px;">${userPage.totalPage}</span>
			  </div>
		  <!-- 分页处理结束 -->
        </div>
        <!-- 右侧主体结束 -->
    </div>
    <!-- 中部结束 -->
    <!-- 底部开始 -->
    <!-- 底部结束 -->
    <!-- 背景切换开始 -->
    	<%@ include file="/layout/background.jsp"%>
    <!-- 背景切换结束 -->
    <!-- 页面动态效果 -->
    <script>

        layui.use(['laydate'], function(){
          laydate = layui.laydate;//日期插件

          //以上模块根据需要引入
          //
          

          
          var start = {
            min: laydate.now()
            ,max: '2099-06-16 23:59:59'
            ,istoday: false
            ,choose: function(datas){
              end.min = datas; //开始日选好后，重置结束日的最小日期
              end.start = datas //将结束日的初始值设定为开始日
            }
          };
          
          var end = {
            min: laydate.now()
            ,max: '2099-06-16 23:59:59'
            ,istoday: false
            ,choose: function(datas){
              start.max = datas; //结束日选好后，重置开始日的最大日期
            }
          };
          
          document.getElementById('LAY_demorange_s').onclick = function(){
            start.elem = this;
            laydate(start);
          }
          document.getElementById('LAY_demorange_e').onclick = function(){
            end.elem = this
            laydate(end);
          }
          
        });

        </script>
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