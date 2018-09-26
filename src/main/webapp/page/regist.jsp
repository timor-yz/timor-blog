<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timor网 - 注册</title>
<%@ include file="base-common.jsp"%>
<link rel="stylesheet" href="${ctx}/static/css/regist.css" />
</head>
<body>
<div class="body">
	<form class="layui-form" action="" method="post">
	<div class="regist-main">
		<div class="layui-form-item regist-title">
			注册
		</div>
		<div class="layui-form-item">
			<input type="text" name="email" class="layui-input" lay-verify="email" autocomplete="off" placeholder="电子邮箱" maxlength="40" />
		</div>
		<div class="layui-form-item">
			<input type="text" name="phone" class="layui-input" lay-verify="phone" autocomplete="off" placeholder="手机号" maxlength="11" />
		</div>
		<div class="layui-form-item">
			<input type="password" name="password" class="layui-input" lay-verify="pwd" autocomplete="off" placeholder="密码" maxlength="16" />
		</div>
		<div class="layui-form-item">
			<input type="password" class="layui-input" lay-verify="pwd2" autocomplete="off" placeholder="确认密码" maxlength="16" />
		</div>
		<div class="layui-form-item">
			<input type="text" name="nickName" class="layui-input" lay-verify="nickName" autocomplete="off" placeholder="昵称" maxlength="10" />
		</div>
		<div class="layui-form-item">
			<input type="text" name="code" class="layui-input code-input" lay-verify="code" autocomplete="off" placeholder="验证码" maxlength="4" />
			<div class="code-img-div">
				<img id="captchaImg" class="code-img" onclick="changeCaptcha()" title="看不清楚?请点击刷新验证码" src="${ctx}/captchaServlet" />
			</div>
		</div>
		<div class="layui-form-item">
			<input id="protocol" type="checkbox" lay-verify="protocol" lay-skin="primary" title="已阅读并同意" value="1" />
			<span id="to-user-agreement" class="to-user-agreement">《Timor网用户协议》</span>
		</div>
		<div class="layui-form-item">
			<button class="layui-btn btn-regist" lay-submit="" lay-filter="regist">注册</button>
		</div>
	</div>
	</form>
</div>

<script type="text/javascript" src="${ctx}/static/js/regist.js"></script>
</body>
</html>