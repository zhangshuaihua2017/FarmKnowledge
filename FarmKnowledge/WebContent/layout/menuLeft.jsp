<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<div class="left-nav">
          <div id="side-nav">
            <ul id="nav">
                <li class="list" current>
                    <a href="${ctx}/admin/gotoIndex">
                        <i class="iconfont">&#xe761;</i>
                        	欢迎页面
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                </li>
                <li class="list">
                    <a href="javascript:;">
                        <i class="iconfont">&#xe70b;</i>
                        	用户管理
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                    <ul class="sub-menu">
                        <li>
                            <a href="${ctx}/member-list.jsp">
                                <i class="iconfont">&#xe6a7;</i>
                        	用户列表
                            </a>
                        </li>
                        <li>
                            <a href="${ctx}/member-del.jsp">
                                <i class="iconfont">&#xe6a7;</i>
                        	用户删除
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="list" >
                    <a href="javascript:;">
                        <i class="iconfont">&#xe6a3;</i>
                        	作物管理
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                    <ul class="sub-menu">
                        <li>
                            <a href="${ctx}/crop-list.jsp">
                                <i class="iconfont">&#xe6a7;</i>
                        	作物列表
                            </a>
                        </li>
                        <li>
                            <a href="${ctx}/crop-del.jsp">
                                <i class="iconfont">&#xe6a7;</i>
                        	作物删除
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="list" >
                    <a href="javascript:;">
                        <i class="iconfont">&#xe6a3;</i>
                        	管理员管理
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                    <ul class="sub-menu" style="display:none">
                        <li>
                            <a href="${ctx}/admin-list.jsp">
                                <i class="iconfont">&#xe6a7;</i>
                        	管理员列表
                            </a>
                        </li>
                        <li>
                            <a href="${ctx}/admin-del.jsp">
                                <i class="iconfont">&#xe6a7;</i>
                        	管理员删除
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
          </div>
    </div>