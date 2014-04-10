var rouletGame = {
	$gamecanvas : {}, $roulet : {},
	title : [],
	
	readyGame : function ( obj1 ) {
		titleArray = obj1;
		wheelNum = titleArray.length;
		
		arc = Math.PI / (parseInt(wheelNum) / 2);
		
		$("#init_wrap").hide();//.fadeTo( 500, 0, function () { $(this).hide() } );
		$("#game_roulet").show();//.fadeIn( 0, 1000, function () { $(this).show() } );
		
		rouletGame.$roulet = $("#roulet_wrap");
		
		rouletGame.drawRouletWheel();		
		//clearTimeout(rouletGame.readyGame);
	},
	
	drawRouletWheel : function () {
			
		var textRadius = 0;

		if (parseInt(screenSize) > 639) {
			canvas = document.getElementById("rouletcanvas_1");
			w = $("#rouletcanvas_1").innerWidth();
			h = $("#rouletcanvas_1").innerHeight();
			textRadius = 160;
			fontsize = 16;
		} else if (parseInt(screenSize) < 640 && parseInt(screenSize) > 500) {
			canvas = document.getElementById("rouletcanvas_2");
			w = $("#rouletcanvas_2").innerWidth();
			h = $("#rouletcanvas_2").innerHeight();
			textRadius = 150;
			fontsize = 14;			
		} else if (parseInt(screenSize) > 401 && parseInt(screenSize) <= 500) {
			canvas = document.getElementById("rouletcanvas_6");
			w = $("#rouletcanvas_6").innerWidth();
			h = $("#rouletcanvas_6").innerHeight();
			textRadius = 125;
			fontsize = 12;			
		}else if (parseInt(screenSize) > 360 && parseInt(screenSize) <= 400) {
			canvas = document.getElementById("rouletcanvas_5");
			w = $("#rouletcanvas_5").innerWidth();
			h = $("#rouletcanvas_5").innerHeight();
			textRadius = 115;
			fontsize = 12;
		} else if (parseInt(screenSize) > 320 && parseInt(screenSize) <= 360) {
			canvas = document.getElementById("rouletcanvas_3");
			w = $("#rouletcanvas_3").innerWidth();
			h = $("#rouletcanvas_3").innerHeight();
			textRadius = 95;
			fontsize = 12;			
			
		} else if (parseInt(screenSize) <= 320) {
			canvas = document.getElementById("rouletcanvas_4");
			w = $("#rouletcanvas_4").innerWidth();
			h = $("#rouletcanvas_4").innerHeight();
			textRadius = 80;
			fontsize = 12;
		}
		
		else {
			canvas = document.getElementById("rouletcanvas_4");
			w = $("#rouletcanvas_4").innerWidth();
			h = $("#rouletcanvas_4").innerHeight();
			textRadius = 86; //160;				
		}

		var w_h = parseInt(w) / 2;
		var h_h = parseInt(h) / 2;
				
		
			
	    var outsideRadius = w_h;		    
	    var insideRadius = 125;
  			
		ctx = canvas.getContext("2d");
		ctx.clearRect(0, 0, w, h);
	
		ctx.font = "bold "+fontsize+"px ³ª´®°íµñ";
		//alert (canvas.getContext);
		//if (canvas.getContext) {
		for ( var i = 0;  i < wheelNum; i++ ) {
			//arc = (( arc / 100 ) * test[i] * 2);
			//angle = 0;
			angle = startAngle + i * arc;								
			ctx.fillStyle = colors[i];				
			ctx.beginPath();

			ctx.arc (w_h, w_h, w_h, angle, angle + arc, false);
			ctx.arc (w_h, w_h, 0, angle + arc, angle, true);
			ctx.fill();
			
		    ctx.save();
		    ctx.shadowOffsetX = -1;
		    ctx.shadowOffsetY = -1;
		    ctx.shadowBlur    = 0;
		    
	        ctx.fillStyle = "black";
	        ctx.translate(w_h + Math.cos(angle + arc / 2) * textRadius, w_h + Math.sin(angle + arc / 2) * textRadius);
	        //ctx.rotate(angle + arc / 2  + Math.PI / 2);
	        text = titleArray[i];
	        ctx.fillText(text, -ctx.measureText(text).width / 2, 0);
	        ctx.restore();			    
		}
		//}
		
	},	
	
	spinWheel : function () {
	    spinAngleStart = Math.random() * 10 + 10;
	    spinTime = 0;
	    spinTimeTotal = Math.random() * 3 + 4 * 1000;
	    rouletGame.rotateWheel();	    
	},
	
	rotateWheel : function () {
	    spinTime += 15;
	   		   	
		if(spinTime >= spinTimeTotal) {
		    rouletGame.stopRotateWheel();
		    return;
	    }	
	    	    	    
	    var spinAngle = spinAngleStart - rouletGame.easeOut(spinTime, 0, spinAngleStart, spinTimeTotal);
	    startAngle += (spinAngle * Math.PI / 90);     		 	    
	    
	    setTimeout( function() {
	    	rouletGame.rotateWheel();
	    }, 30 );
	    
	    rouletGame.drawRouletWheel();	        		
	},
	
	stopRotateWheel : function () {
		
	    clearTimeout(rouletGame.rotateWheel);
	    
	    var degrees = 0; 
	    
	    var arcd = 0;
	    var index = 0;
	    
	    
	    degrees = startAngle * 180 / Math.PI + 90;	
		arcd = arc * 180 / Math.PI;    	    
		    
		index = Math.floor((360 - degrees % 360) / arcd);    
		
	    ctx.save();
	    //alert ("tet");
	    //alert (index);	    
	    var text = titleArray[index]
	    
	    $("#roulet_result").show();
	    $("#r_td").html("<h3>"+text+"</h3>");
	    $(".roulet_start").hide();
	    $(".roulet_restart, .roulet_resetup").show();
	    
	    //$("#roulet_btn_1").hide();
	    //$("#result_ques").hide();
	    
	    //$("#roulet_btn_2").show();
	    //$("#roulet_btn_3").show();
	    //$("#result_querybg").show();
	    
	    //$("#result_td").html(text);
	    
	},
	
	restartWheel : function () {
		$("#roulet_result").hide();
		
		//arc = Math.PI / (parseInt(wheelNum) / 2);
		//rouletGame.readyGame( titleArray );
		
		rouletGame.spinWheel();
	    $(".roulet_start").show();
	    $(".roulet_restart, .roulet_resetup").hide();		
	},
	
	restupWheel : function () {
				
		$("#roulet_result").hide();
		
		$("#game_roulet").hide();//.fadeTo( 500, 0, function () { $(this).hide() } );
		$("#init_wrap").show();
		//$("#init_wrap").fadeIn( 0, 1000, function () { $(this).show() } );
	    $("#r_td").html("");
	    $(".roulet_start").show();
	    $(".roulet_restart, .roulet_resetup").hide();
	    selectNum(8);
	},
	
	easeOut : function (t, b, c, d) {
	    var ts = (t/=d)*t;
	    var tc = ts*t;
	    
      		pin  = 3;
		    $("#roulet_pin").rotate(pin);   
		    	    
	    return b+c*(tc + -3*ts + 3*t);
	}	
};