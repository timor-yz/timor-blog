/** ------ 公共js方法处理存放类 ------ */
/** ------- Add By YuanZhe ----- */
/** -- 2018年9月12日 09:42:00 -- */

/*-- 格式验证 --*/
/** 
 * 根据指定正则验证格式
 * value : 需验证的值
 * reg : 验证格式正则
 */
function isNumber(value, reg) {
	return reg_num.test(value);
}
/** 验证邮箱格式 */
function isEmail(value) {
	return reg_email.test(value);
}
/** 验证手机号格式 */
function isPhone(value) {
	return reg_phone.test(value);
}
/** 验证链接URL格式 */
function isUrl(value) {
	return reg_url.test(value);
}
/** 验证日期格式 */
function isDate(value) {
	return reg_date.test(value);
}
/** 验证身份证号（中国大陆）格式 */
function isIdentity(value) {
	return reg_identity.test(value);
}
/** 验证是否为数字 */
function isNumber(value) {
	return reg_num.test(value);
}
/** 验证是否为整数 */
function isInt(value) {
	return reg_int.test(value);
}

/**
 * 验证是否为空
 */
function isEmpty(value) {
	if (!value || value == null || value == undefined || $.trim(value).length == 0) {
		return true;
	}
	return false;
}
