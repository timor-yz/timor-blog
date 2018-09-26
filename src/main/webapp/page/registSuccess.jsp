<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="base-common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timor网 - 注册</title>
</head>
<body>
	这里：
	<h2>${ctx}</h2>

<script type="text/javascript">
layui.use([ 'layer' ], function() {
	var layer = layui.layer;
	layer.msg('Registered successfully!');
});
</script>
</body>
</html>