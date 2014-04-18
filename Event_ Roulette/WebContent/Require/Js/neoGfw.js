var spinCount = 0;
var wheelNum = 0;
var startAngle = 0;
var spinTimeTotal = 0;

var slow_spinAngleStart = 0;
var slow_spinTime = 0;
var slow_spinTimeTotal = 0;

var spinAngleStart = 0;
var spinTime = 0;
var spinAngle = 0;

var arc = 0;

var angle = 0;
var ctx;

var titleArray = Array();
var arcArray = Array();
var angleArray = Array();
var iconArray = Array();
var icons = ["images/roulette_goods01.png", "images/roulette_goods02.png" , "images/roulette_goods03.png", "images/roulette_goods04.png" , "images/roulette_goods05.png", "images/roulette_goods06.png","images/roulette_goods07.png", "images/roulette_goods08.png"];


var colors=["#34BCBC","#FFFFFF","#34BCBC","#FFFFFF","#34BCBC","#FFFFFF","#34BCBC","#FFFFFF"];

//var sample = [ "KPSmall 상품권 5만 원권", "KPSmall 상품권 3만 원권", "KPSmall 상품권 1만 원권", "자석 책갈피", "폴리 기념품", "다음 기회에", "e-POLY 6개월 기본과목 이용권", "KPSmall 상품권 10만원" ];
var no = 0;
var canvas = null;
var w = 0;
var h = 0;
var pin = 0;
var fontsize = 0;
var tempAngle = 0;
var msg = "";
var timefunc_start = "";
var timefunc_stop = "";
var temp =0 ;
var setTimeout_cnt =16;   // 룰렛 회전 속도 


function InitRoulet() {  //canvas 부분

	temp = document.getElementById("rouletcanvas_1");
	
	if (temp.getContext) {
		
		var title = Array();

		no = icons.length;
	
		//alert("canvas :: "+ temp);

		for (var i = 0; i < parseInt(no); i++) {
			title[i] = icons[i];
		}
			rouletGame.readyGame(title);
		}
}


function result_view(text, _text_no){
	
	if (parseInt(_text_no) < 7) {		// 꽝이 아닐 때만 룰렛 _2 를 보여주기
		$("#rouletcanvas_2").show();
		$("#rouletcanvas_2").find("span").text(text);

	}else{		// 꽝
		$("#rouletcanvas_3").show();
	}
	
}


function result_stop(){
	var _text = $("#result_txt").val();
	var _text_no = $("#result_no").val();
	
	console.log("진입");
	//console.log(_text);
	//console.log(_text_no);
	
	switch (parseInt(_text_no)) {
	case 1:
		document.getElementById("rouletcanvas_1").style.backgroundImage= "url(images/roulette_01.png)";
		console.log(_text_no);
		setTimeout(function(){result_view(_text, _text_no);},1500);
		break;
	case 2:
		document.getElementById("rouletcanvas_1").style.backgroundImage= "url(images/roulette_02.png)";
		console.log(_text_no);
		setTimeout(function(){result_view(_text, _text_no);},1500);
		break;
	case 3:
		document.getElementById("rouletcanvas_1").style.backgroundImage= "url(images/roulette_03.png)";
		console.log(_text_no);
		setTimeout(function(){result_view(_text, _text_no);},1500);
		break;
	case 4:
		document.getElementById("rouletcanvas_1").style.backgroundImage= "url(images/roulette_04.png)";
		console.log(_text_no);
		setTimeout(function(){result_view(_text, _text_no);},1500);
		break;
	case 5:
		document.getElementById("rouletcanvas_1").style.backgroundImage= "url(images/roulette_05.png)";
		console.log(_text_no);
		setTimeout(function(){result_view(_text, _text_no);},1500);
		break;
	case 6:
		document.getElementById("rouletcanvas_1").style.backgroundImage= "url(images/roulette_06.png)";
		console.log(_text_no);
		setTimeout(function(){result_view(_text, _text_no);},1500);
		break;
	case 7:
		document.getElementById("rouletcanvas_1").style.backgroundImage= "url(images/roulette_07.png)";
		console.log(_text_no);
		setTimeout(function(){result_view(_text, _text_no);},1500);
		break;
	default:
		document.getElementById("rouletcanvas_1").style.backgroundImage= "url(images/roulette_08.png)";
		console.log(_text_no);
		setTimeout(function(){result_view(_text, _text_no);},1500);
		break;
	}
	
}
function rouletGame_start(msg){
	
	if (msg=="up") {		// up 호출 시 
		
		rouletGame.spinWheel('start');	// canvas 전용 회전 메서드
		
	}else if(msg =="down") { // 하위 버전 전용 회전 메서드
	//	alert("!!!!");
	//$("#rouletcanvas_1").css('background-image', "images/roulette_ani.gif");
		
			// document.getElementById("result_txt").value;
		
		document.getElementById("rouletcanvas_1").style.backgroundImage= "url(images/roulette_ani.gif)";
		
		setTimeout(function(){result_stop()},5000);
		
	}
	return;
}


var rouletGame = {
	$gamecanvas : {},
	$roulet : {},
	title : [],

	readyGame : function(obj1) {
		titleArray = obj1;
		wheelNum = titleArray.length;

		arc = (Math.PI * 2) / (parseInt(wheelNum));

		rouletGame.$roulet = $("#roulette_wrap");

		rouletGame.drawRouletWheel();
	},

	drawRouletWheel : function() {

		var img_Radius = 0;

		canvas = document.getElementById("rouletcanvas_1");
		w = $("#rouletcanvas_1").innerWidth();
		h = $("#rouletcanvas_1").innerHeight();
		img_Radius = 130; // 글자 지정 위치

		var w_h = parseInt(w) / 2;
		var h_h = parseInt(h) / 2;

		var outsideRadius = w_h;
		var insideRadius = 125;

		ctx = canvas.getContext("2d");
		ctx.clearRect(0, 0, w, h);

		
		
		var j = 0;
		var aa = Array();
		for (var i = 0; i < wheelNum; i++) {
			
			iconArray[i] = new Image();			
			iconArray[i].src = icons[i];
			
			angle = startAngle + i * arc;
			
		    
			ctx.fillStyle = colors[i];
			ctx.beginPath();

			ctx.arc(w_h, w_h, w_h, angle, angle + arc, false);
			ctx.arc(w_h, w_h, 0, angle + arc, angle, true);
			ctx.fill();

			ctx.save();
			
			
			iconArray[i].onload = function () {				
				
				angle = startAngle + j * arc;
				
			    ctx.fillStyle = ctx.createPattern(iconArray[j++], 'no-repeat');
			    
			    ctx.arc(w_h, w_h, w_h, angle, angle + arc, false);
				ctx.arc(w_h, w_h, 0, angle + arc, angle, true);
			    
				var  a = w_h + Math.cos(angle + arc / 30) * img_Radius;
				var  b = w_h + Math.sin(angle + arc / 30) * img_Radius;
				var  c = angle + arc / 2 + Math.PI / 2;				
				ctx.translate(a, b);				
			    ctx.rotate(c); 
			    
			    ctx.fill();
				
				ctx.restore();
			}
		}
		
	},
	
	slow_Wheel : function() { // 멈출 지점 지정하는 회전


	 slow_spinAngleStart = 5.625; 			// 각도 지정 값
	 slow_spinTime = 0;

	 if ( $("#result").val() != null || $("#result").val() != "" ) {
		 slow_spinTimeTotal = $("#result").val();  // 처리 값
		 
		// console.log("slow_spinTimeTotal :: "+ slow_spinTimeTotal );
	}
	 else{
		// consoel.log(" 꽝");
		 slow_spinTimeTotal = (Math.random() * (4040 - 3920 + 1)) + 3920;
	 }
	 
	// console.log("slow_spinTimeTotal  :: "+  slow_spinTimeTotal) ;  // 처리 값
	 
	    clearTimeout(timefunc_stop);
		rouletGame.slow_RWheel();

},

slow_RWheel : function () {
	
	slow_spinTime += 15;
	
	//temp +=1;
	
	if(slow_spinTime >= slow_spinTimeTotal) {
		
		rouletGame.stopRotateWheel();
	    
	    return;
    }	
    	    	    
    var spinAngle = slow_spinAngleStart - rouletGame.easeOut(slow_spinTime, 0, slow_spinAngleStart, slow_spinTimeTotal);
    
    
    startAngle += (spinAngle * Math.PI / 90);     
    
    setTimeout( function() {rouletGame.slow_RWheel();}, setTimeout_cnt );
    
    rouletGame.drawRouletWheel();	        		
},


	spinWheel : function(msg) { // 회전 신호 보내기


		spinAngleStart = 5.625;
		spinTime = 0;
		spinTimeTotal = 1; // 디폴트 값
		// spinTimeTotal = Math.random() * 3 + 4 * 1000;


		if (msg == 'stop') {

			clearTimeout(timefunc_start);
			rouletGame.rotateWheel('stop');

		} else if(msg == 'start') {
			rouletGame.rotateWheel('start');

		}
	},
	
	rotateWheel : function(msg) { // 신호에 따른  회전

		if (msg == 'start') { // start
			
			    timefunc_start = setTimeout(function() {rouletGame.rotateWheel(msg);}, setTimeout_cnt);
		}
		spinCount += 1;

		spinAngle = spinAngleStart - rouletGame.easeOut(spinTime, 0, spinAngleStart, spinTimeTotal);
		startAngle += (spinAngle * Math.PI / 90);
	
		//console.log("spinCount :: " + spinCount);

			if (spinCount % 32 == 0 && msg == 'stop') {

		//		console.log("spinCount :: " + spinCount);
		//		console.log("==========stop 진입 ============");

				rouletGame.slow_Wheel();
				
			//	console.log("spinTime  :: " + spinTime);
		}
			else if (spinCount % 32 != 0 && msg == 'stop'){
			   timefunc_stop = setTimeout(function() {rouletGame.rotateWheel(msg);}, setTimeout_cnt);
		}
			
				rouletGame.drawRouletWheel();
				
				if (msg == 'start' && spinCount % 150 == 0) {
					
					return 	rouletGame.spinWheel('stop');
				}
		
	},

	
	easeOut : function(t, b, c, d) {
		var ts = (t /= d) * t;
		var tc = ts * t;

		pin = 3;
		$("#roulet_pin").rotate(pin);

		return b + c * (tc + -3 * ts + 3 * t);
	},

	stopRotateWheel : function() {

		clearTimeout(rouletGame.slow_RWheel);   // 회전  정지
/*
		var degrees = 0;

		var arcd = 0;
		var index = 0;
*/
	//	console.log("================================");

		
	//	console.log("startAngle :: "+ startAngle);
		
	//	degrees = startAngle * 180 / Math.PI + 90;
		
	//	console.log("degrees :: "+ degrees);
		
	//	arcd = arc * 180 / Math.PI;
		
	//	console.log("arcd :: "+ arcd);

		//index = Math.floor((360 - degrees % 360) / arcd);
		ctx.save();
		
		var text = $("#result_txt").val();
		var text_no = $("#result_no").val();
	
		console.log("parseInt(text_no :: "+	parseInt(text_no));
		console.log("text :: "+ text);
		
		if (parseInt(text_no) < 7) {		// 꽝이 아닐 때만 룰렛 _2 를 보여주기
			$("#rouletcanvas_2").show();
			$("#rouletcanvas_2").find("span").text(text);

		}else{		// 꽝
			$("#rouletcanvas_3").show();
		}


	}
};
