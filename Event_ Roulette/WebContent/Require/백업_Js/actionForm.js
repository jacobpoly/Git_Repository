/**
 * 
 */
	function btn_disable(disabled_yn) {
		
		if (disabled_yn == "btn_n") {
			$(".roulet_start_img").attr("disabled", "disabled");
		}else{
			$(".roulet_start_img").removeAttr("disabled");
		}

	}


    var action = "";
	var form_name = "";

	function ActionSubmit(action, form_name) {
		this._url = action + '.do';

		//if ( this._url.substring( 0, 1 ) == '/' ) this._url = Config.contextPath + this._url;

		console.log("action ::" + action);
		console.log("form_name ::" + form_name);

		console.log($("#" + form_name));

		$.ajax({
			type : "POST" //"POST", "GET"
			,
			async : true //true, false
			,
			url : _url //Request URL
			,
			dataType : "html" //전송받을 데이터의 타입
			//"xml", "html", "script", "json" 등 지정 가능
			//미지정시 자동 판단
			,
			timeout : 30000 //제한시간 지정
			,
			cache : false //true, false
			,
			data : $("#" + form_name).serialize() //서버에 보낼 파라메터
			//form에 serialize() 실행시 a=b&c=d 형태로 생성되며 한글은 UTF-8 방식으로 인코딩
			//"a=b&c=d" 문자열로 직접 입력 가능
			//{a:b, c:d} json 형식 입력 가능
			,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			error : function(request, status, error) {
				//통신 에러 발생시 처리
				alert("code : " + request.status + "\r\nmessage : "
						+ request.reponseText);
			},
			success : function(response, status, request) {
				//통신 성공시 처리

				alert("전송 성공");
			}
		});
	}
	
