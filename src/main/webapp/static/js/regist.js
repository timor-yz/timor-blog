var userAgreement;
layui.use([ 'layer', 'form' ], function() {
	var $ = layui.$,
		layer = layui.layer,
		form = layui.form;
	
	// 展示用户协议
	$('#to-user-agreement').click(function() {
		layer.open({
			type : 2,
			area: ['1100px', '600px'],
			title: '<p align="center">Timor网用户协议</p>',
			content : '/page/UserAgreement.html', //这里content是一个普通的String
			resize : false, // 不允许拉伸
			btn: ['已阅读并同意', '关闭'],
			btnAlign : 'c',
			yes : function(index, layero) {
				$('#protocol').prop('checked', true);
				form.render('checkbox');
				layer.close(index);
			},
			btn2 : function() {
				
			}
		});
	});
	
	// 自定义验证规则
	form.verify({
		email : function(value, item) {
			if (!isEmail(value)) {
				return '邮箱格式不正确';
			}
			var flag = true;// 邮箱是否已被注册
			$.ajax({
                type : 'post',
                url : '/regist/checkEmail',
                data : { email : value},
                dataType : 'json',
                async : false,
                success : function(data) {
                    if (!data || !data.result) {
                    	flag = false;
                    }
                }
            });
			if (!flag) { return '该邮箱已被注册'; }
		},
		phone : function(value, item) {
			if (!isPhone(value)) {
				return '请输入正确的手机号';
			}
			var flag = true;// 手机号是否已被注册
			$.ajax({
                type : 'post',
                url : '/regist/checkPhone',
                data : { phone : value},
                dataType : 'json',
                async : false,
                success : function(data) {
                    if (!data || !data.result) {
                    	flag = false;
                    }
                }
            });
			if (!flag) { return '该手机号已被注册'; }
		},
		pwd : [ /(.+){6,12}$/, '密码必须6到12位' ],
		pwd2 : function(value, item) {
			var pass = $('input[name=password]').val();
			if (pass != value) {
				return '两次密码不一致';
			}
		},
		nickName : function(value, item) {
			if (isEmpty(value)) {
		        return '请输入昵称';
			}
		},
		code : function(value, item) {
			if (isEmpty(value)) {
		        return '请输入验证码';
			}
			var flag = true;// 验证码是否正确
			$.ajax({
                type : 'post',
                url : '/regist/checkCode',
                data : { code : value},
                dataType : 'json',
                async : false,
                success : function(data) {
                    if (!data || !data.result) {
                    	$(item).val('');
                    	changeCaptcha();
                    	flag = false;
                    }
                }
            });
			if (!flag) { return '验证码输入有误'; }
		},
		protocol : function(value, item) {
			if (!item.checked) {
		        return '请阅读并同意《Timor网用户协议》';
			}
		}
	});
	
	// 监听提交
	form.on('submit(regist)', function(data) {

		$.ajax({
			type : 'post',
            url: '/regist/regist',
            data : data.field,
            dataType : 'json',
            async : false,
            success : function(result) {
            	if (result.success == true) {// true代表成功，其他代表失败
            		layer.msg(result.msg);
    	   		} else {
    	   			layer.msg(result.msg);
    	   		}
            },
            error : function(result) {
            	layer.msg(result);
            }
        });
		
		return false;
	});

	form.render();
});

// 更新验证码
var changeCaptcha = function() {
	$("#captchaImg").attr('src', systemPath + '/captchaServlet?t=' + (new Date().getTime()));
};