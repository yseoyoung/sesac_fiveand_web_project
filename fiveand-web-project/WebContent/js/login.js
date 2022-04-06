/**
 * 로그아웃
 * 
 */

var logInOut = function(){

	
	var btnLogin = document.getElementById('btnLogin');
	var btnLogout = document.getElementById('btnLogout');
	
	if(btnLogin != null){
	btnLogin.onclick = function(){
		var frm = document.loginForm;
		frm.action = 'http://localhost:9999/fiveand-web-project/jsp/member/login.jsp';
		frm.submit();
	}
	}
		
	if(btnLogout != null){
	btnLogout.onclick = function(){
		if( confirm("정말 로그아웃 하시겠습니까?")){
		alert("로그아웃되었습니다");
		location.href = 'http://localhost:9999/fiveand-web-project/jsp/member/logout.jsp';
		}
	}
	}

}