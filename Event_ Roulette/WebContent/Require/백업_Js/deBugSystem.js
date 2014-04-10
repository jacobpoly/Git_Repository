/**
 * @author seungtae
 * 개발 시  사용하는 콘솔용 디버그 모드
 */

var _deb_m_System = {
	
	deb_isDebugMode : true, deb_isUseAlert : false,
	
	_deb_s_Log : function ( type, msg ) {
		if (this.deb_isDebugMode == false) {
			return;
		}
		
		if (typeof console == "undefined") {
			return;
		}
		
		switch ( type ) {
			case "LOG":
				console.log( msg );
				break;
			
			case "WARN":
				console.log( msg );
				break;
				
			case "ERROR":
				console.log( msg );
				break;
		}
	}
};
