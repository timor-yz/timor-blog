/* 公共方法js. Added By YuanZhe. 2018年12月27日 10:24:10 */

$(function() {
	$('input[type=password]').attr('onpaste', 'return false');
	$('input[type=password]').attr('oncontextmenu', 'return false');
	$('input[type=password]').attr('oncopy', 'return false');
	$('input[type=password]').attr('oncut', 'return false');
});