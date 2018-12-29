/* 用户注册处理js. Added By YuanZhe. 2018年12月27日 09:32:43 */

var form, layer;

layui.use([ 'form', 'layer' ], function() {
	form = layui.form;
	layer = layui.layer;
	
	// 自定义验证规则
	form.verify({
		username : function(val, item) {
			if (isEmpty(val)) {
				return '请输入用户名';
			}
			if ($.trim(val).length < 5) {
				return '用户名长度不能低于5位';
			}
			var flag = true;
			var msg = '';
			$.ajax({
                type : 'POST',
                url : ctxPath + '/regist/checkUsername',
                data : { username : $.trim(val) },
                dataType : 'JSON',
                async : false,
                success : function(data) {
                	flag = data.success;
                	msg = data.msg;
                }
            });
			if (!flag) { return msg; }
		},
		password : function(val, item) {
			if (isEmpty(val)) {
				return '密码不能为空';
			}
			if (hasSpace(val)) {
				return '密码中不能含有空格';
			}
			if ($.trim(val).length > 12 || $.trim(val).length < 6) {
				return '密码长度应为6到12位';
			}
		},
		password2 : function(val, item) {
			var pwd = $('input[name=password]').val();
			if (pwd != val) {
				return '两次密码不一致';
			}
		},
		email : function(val, item) {
			if (!isEmail(val)) {
				return '邮箱格式不正确';
			}
			var flag = true;
			var msg = '';
			$.ajax({
                type : 'POST',
                url : ctxPath + '/regist/checkEmail',
                data : { email : $.trim(val) },
                dataType : 'JSON',
                async : false,
                success : function(data) {
                	flag = data.success;
                	msg = data.msg;
                }
            });
			if (!flag) { return msg; }
		},
		validCode : function(val, item) {
			if (isEmpty(val)) {
		        return '请输入验证码';
			}
			var flag = true;
			var msg = '';
			$.ajax({
                type : 'POST',
                url : ctxPath + '/regist/checkCode',
                data : { code : $.trim(val) },
                dataType : 'JSON',
                async : false,
                success : function(data) {
                	flag = data.success;
                	msg = data.msg;
                }
            });
			if (!flag) { return msg; }
		}
	});
	
	// 监听提交
	form.on('submit(regist)', function(data) {
	});

	form.render();
});

// 更新验证码
var changeCaptcha = function() {
	$('#captchaImg').attr('src', ctxPath + '/captchaServlet?t=' + (new Date().getTime()));
};