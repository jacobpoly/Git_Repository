/**
 * 
 */

/*
 * function btn_disable(disabled_yn) {
 * 
 * if (disabled_yn == "btn_n") { $(".roulet_start_img").attr("disabled",
 * "disabled"); }else{ $(".roulet_start_img").removeAttr("disabled"); }
 *  }
 */

var action = "";
var form_name = "";

function ActionSubmit(action, form_name) {
	this._url = action + '.do';

	// if ( this._url.substring( 0, 1 ) == '/' ) this._url = Config.contextPath
	// + this._url;

	//console.log("action ::" + action);
//	console.log("form_name ::" + form_name);

	//console.log($("#" + form_name));

	$.ajax({
		type : "POST" // "POST", "GET"
		,
		async : true // true, false
		,
		url : _url // Request URL
		,
		dataType : "html" // 전송받을 데이터의 타입
		// "xml", "html", "script", "json" 등 지정 가능
		// 미지정시 자동 판단
		,
		timeout : 30000 // 제한시간 지정
		,
		cache : false // true, false
		,
		data : $("#" + form_name).serialize() // 서버에 보낼 파라메터
		// form에 serialize() 실행시 a=b&c=d 형태로 생성되며 한글은 UTF-8 방식으로 인코딩
		// "a=b&c=d" 문자열로 직접 입력 가능
		// {a:b, c:d} json 형식 입력 가능
		,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		error : function(request, status, error) {
			// 통신 에러 발생시 처리
			//alert("code : " + request.status + "\r\nmessage : "
			//		+ request.reponseText);
			
			alert("서버 접속이 원할 하지 않습니다. 다시 시도해주시길 바랍니다.");
			if (action == "e_start_btn") {
				$("#result").val( (Math.random() * (4040 - 3920 + 1)) + 3920);	
				$("#result_no").val(8);
			}
		},
		
	     complete: function() {
	        //통신이 완료된 후 처리
	   
	       },
	       
		success : function(msg) {
			// 통신 성공시 처리
			
			if (action == "e_start_btn" ) {		// start
				var json = $.parseJSON(msg);
					
				
			//	console.log("1 "+msg);
			//	console.log("2 "+json);
				
				//console.log(json.result);
				//console.log(json.result.result);
			//	console.log(json.result.result_no);
			//	console.log(json.result.result_txt);
				// 7 이면 꽝으로 처리 

				$("#result").val(json.result.result);
				$("#result_no").val(json.result.result_no);
				$("#result_txt").val(json.result.result_txt);

			}else if(action == "e_participation"){			// 참여하기
				var json = $.parseJSON(msg);
				
				
			//	console.log(msg);
			//	console.log(json);

			//	console.log(json.on);
			//	console.log(json.e_start_btn);
				$("#event_btn").val(json.e_start_btn);
				$("#bt_start").attr('class', json.on);
			}
//			else if (action == "excel") { // 엑셀 다운로드
//	    		//		console.log(msg);
//	    				var msg = 'find=commoncode';
//	    				var method = 'post';
//	    				
//	    			       msg = typeof msg == 'string' ? msg : jQuery.param(msg);
//	    			        // 파라미터를 form의  input으로 만든다.
//	    			        var inputs = '';
//	    			        jQuery.each(msg.split('&'), function(){ 
//	    			            var pair = this.split('=');
//	    			            inputs+='<input type="hidden" name="'+ pair[0] +'" value="'+ pair[1] +'" />'; 
//	    			        });
//	    			        // request를 보낸다.
//	    			        jQuery('<form action="'+ _url +'" method="'+ (method||'post') +'">'+inputs+'</form>').appendTo('body').submit().remove();
//	    				
//	    				$("#excel_div").unmask();
//	    			}
	    	
		}
	});
}
