/* 公共js方法处理存放类. Added By YuanZhe. 2018年12月26日 09:22:35 */

/*-- 格式验证 --*/
/** 
 * 根据指定正则验证格式
 * val : 需验证的值
 * reg : 验证格式正则
 */
function isMatch(val, reg) {
	return reg.test(val);
}
/** 验证邮箱格式 */
function isEmail(val) {
	return reg_email.test(val);
}
/** 验证手机号格式 */
function isPhone(val) {
	return reg_phone.test(val);
}
/** 验证链接URL格式 */
function isUrl(val) {
	return reg_url.test(val);
}
/** 验证日期格式 */
function isDate(val) {
	return reg_date.test(val);
}
/** 验证身份证号（中国大陆）格式 */
function isIdentity(val) {
	return reg_identity.test(val);
}
/** 验证是否为数字 */
function isNumber(val) {
	return reg_num.test(val);
}
/** 验证是否为整数 */
function isInt(val) {
	return reg_int.test(val);
}

/**
 * 验证是否为空
 */
function isEmpty(val) {
	if (!val || val == null || val == undefined || val.length == 0) {
		return true;
	}
	return false;
}

/**
 * 验证是否存在空格
 */
function hasSpace(val) {
	return val.indexOf(' ') > -1;
}
