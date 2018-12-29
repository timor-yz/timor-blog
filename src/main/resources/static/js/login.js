/* 用户登录处理js. Added By YuanZhe. 2018年12月26日 09:28:56 */

var form, layer;

layui.use([ 'form', 'layer' ], function() {
	form = layui.form;
	layer = layui.layer;
	
	form.verify({
		username : function(val, item) {// val：表单的值、item：表单的DOM对象
			if (!val) {
				return '用户名不能为空';
			}
		},
		password : function(val, item) {// val：表单的值、item：表单的DOM对象
			if (!val) {
				return '密码不能为空';
			}
		}
	});
	
	form.on('submit(login)', function(res) {
		/* var data = res.field;
		
    	layer.msg(JSON.stringify(data));
    	return false; */
	});
});