/**
 * 
 */

var spinCount = 0;
var wheelNum = 0;
var startAngle = 0;
var spinTimeTotal = 0;

var spinAngleStart = 0;
var spinTime = 0;

var arc = 0;

// spinAngleStart = Math.random() * 10 + 10;

var angle = 0;
var ctx;
var text;

var titleArray = Array();

var imgArray = Array();

var imgs=["Images/Chrysanthemum.jpg", "Images/Desert.jpg" , "Images/Hydrangeas.jpg", "Images/Jellyfish.jpg" , "Images/Koala.jpg", "Images/Lighthouse.jpg","Images/Penguins.jpg", "Images/Tulips.jpg"];

var colors = [ "#E04141", "#FEDB4E", "#77C157", "#5FDEFE", "#E99EDA", "#FEDCBC", "#6F55AA", "#4374A3", "#EC6913", "#A05EA2" ];

var sample = [ "쏘맥원샷", "노래방 쏘기", "심부름 하기", "술값 내기", "2차 쏘기", "당첨 니가 쏴", "회비 면제", "파도타기" ];

var no = 0;
var canvas = null;
var w = 0;
var h = 0;
var pin = 0;
var fontsize = 0;
var tempAngle = 0;
var msg = "";
var timefunc_start = "";
var temp =0 ;

function InitRoulet() {

	var title = Array();

	no = sample.length;

	for (var i = 0; i < parseInt(no); i++) {
		title[i] = sample[i];
	}
	rouletGame.readyGame(title);
}

var rouletGame = {
	$gamecanvas : {},
	$roulet : {},
	title : [],

	readyGame : function(obj1) {
		titleArray = obj1;
		wheelNum = titleArray.length;

		arc = (Math.PI * 2) / (parseInt(wheelNum));

		$("#init_wrap").hide();

		$("#game_roulet").show();

		rouletGame.$roulet = $("#roulet_wrap");

		rouletGame.drawRouletWheel();
	},

	drawRouletWheel : function() {

		var textRadius = 0;

		canvas = document.getElementById("rouletcanvas_1");
		w = $("#rouletcanvas_1").innerWidth();
		h = $("#rouletcanvas_1").innerHeight();
		textRadius = 150; // 글자 지정 위치
		fontsize = 13; // 글자 크기

		var w_h = parseInt(w) / 2;
		var h_h = parseInt(h) / 2;

		var outsideRadius = w_h;
		var insideRadius = 125;

		ctx = canvas.getContext("2d");
		ctx.clearRect(0, 0, w, h);

		ctx.font = "bold " + fontsize + "px 나눔고딕";
		for (var i = 0; i < wheelNum; i++) {

			angle = startAngle + i * arc;
		
			imgArray[i] = new Image();
			imgArray[i].src =imgs[i];
	
			ctx.fillStyle = ctx.createPattern(imgArray[i], 'no-repeat');
	
			
			//	ctx.fillStyle = colors[i];
		
			
			ctx.beginPath();

			ctx.arc(w_h, w_h, w_h, angle, angle + arc, false);
			ctx.arc(w_h, w_h, 0, angle + arc, angle, true);
			ctx.fill();

			ctx.save();
			ctx.shadowOffsetX = -1;
			ctx.shadowOffsetY = -1;
			ctx.shadowBlur = 0;

			ctx.fillStyle = "black";
			ctx.translate(w_h + Math.cos(angle + arc / 2) * textRadius, w_h + Math.sin(angle + arc / 2) * textRadius);
			// ctx.rotate(angle + arc / 2 + Math.PI / 2);
			text = titleArray[i];
			ctx.fillText(text, -ctx.measureText(text).width / 2, 0);
			ctx.restore();
		}

	},
	spinWheel : function(num_time) { // 회전 신호 보내기

		// spinAngleStart = Math.random() * 10 + 10;

		spinAngleStart = 5.625;
		spinTime = 0;
		spinTimeTotal = num_time;
		// spinTimeTotal = Math.random() * 3 + 4 * 1000;

		console.log("spinAngleStart :: " + spinAngleStart);
		console.log("spinTime :: " + spinTime);
		console.log("spinTimeTotal :: " + spinTimeTotal);
		rouletGame.rotateWheel();
		},
	
	rotateWheel : function () {
		    spinTime += 15;
		   		   	
			if(spinTime >= spinTimeTotal) {
			    rouletGame.stopRotateWheel();
			    return;
		    }	
			
			console.log("1. spinAngleStart :: "+spinAngleStart);
			console.log("2. spinTime :: "+ spinTime);
			console.log("3. spinTimeTotal :: "+ spinTimeTotal);
		    	    	 	
			
		    var spinAngle = spinAngleStart - rouletGame.easeOut(spinTime, 0, spinAngleStart, spinTimeTotal);
		     startAngle += (spinAngle * Math.PI / 90);     		 	    
		    
		 	
			console.log("진행  spinAngle :: "+spinAngle);
			console.log("진행  startAngle :: "+startAngle);
		    	    	 
		    setTimeout( function() {rouletGame.rotateWheel();}, 30 );
		    
		    rouletGame.drawRouletWheel();	        		
		},
	
	easeOut : function(t, b, c, d) {
		var ts = (t /= d) * t;
		var tc = ts * t;

		pin = 3;
		$("#roulet_pin").rotate(pin);

		return b + c * (tc + -3 * ts + 3 * t);
	},

	stopRotateWheel : function() {

		clearTimeout(rouletGame.rotateWheel);   // 회전  정지

		var degrees = 0;

		var arcd = 0;
		var index = 0;

		console.log("stop.startAngle :: "+ startAngle);
		
		degrees = startAngle * 180 / Math.PI + 90;

		console.log("stop.degrees :: "+degrees);
		
		arcd = arc * 180 / Math.PI;

		console.log("stop.arcd :: "+arcd);
		
		index = Math.floor((360 - degrees % 360) / arcd);
		
	
		ctx.save();
		var text = titleArray[index];
		
		console.log("index :: "+index);
		console.log("titleArray[index] :: "+ titleArray[index]);
		
		$("#roulet_result").show();
		$("#r_td").html("<h3>" + text + "</h3>");

	}
};
