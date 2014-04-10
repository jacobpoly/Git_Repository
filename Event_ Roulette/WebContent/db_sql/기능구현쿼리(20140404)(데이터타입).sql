USE G2_KEMS
GO

-- MK-01

SELECT	CASE WHEN ENTER_CNT > 0 THEN 'Y' ELSE 'N' END ENTER_YN				-- NCHAR(1)
		, ENTER_CNT															-- INT
		, CASE WHEN A.STUDENT_STT_BRN = '03' THEN 'Y' ELSE 'N' END NOW_YN	-- NCHAR(1)
		, A.MEMBER_CODE														-- NVARCHAR(10)
		, A.CLIENT_CODE														-- NVARCHAR(10)
		, A.CLIENT_MEM_CODE													-- INT
FROM	(
			SELECT	COUNT(*) ENTER_CNT, A.CLIENT_MEM_CODE, B.MEMBER_CODE, B.CLIENT_CODE, STUDENT_STT_BRN
			FROM	DBO.TB_MKB_EVENT_ENTER A
			INNER	JOIN G2_KCMS.dbo.VW_STA_STUDENT B ON A.CLIENT_MEM_CODE = B.CLIENT_MEM_CODE
			WHERE	A.EVENT_NO = 1
			AND		B.MEMBER_CODE = '09110400'
			GROUP	BY A.CLIENT_MEM_CODE, B.MEMBER_CODE, B.CLIENT_CODE, STUDENT_STT_BRN
)A

-- MK-03
SELECT	 C.PRODUCT_NO					-- INT
		, D.PRODUCT_NAME				-- NVARCHAR(30)
		, C.LEFT_PRODUCT_CNT			-- INT
FROM	DBO.TB_MKB_EVENT A
INNER	JOIN DBO.TB_MKB_EVENT_CLIENT B ON A.EVENT_NO = B.EVENT_NO
INNER	JOIN DBO.TB_MKB_EVENT_PRODUCT_CONFIG_CLIENT C ON B.EVENT_NO = C.EVENT_NO AND B.CLIENT_CODE = C.CLIENT_CODE
INNER	JOIN DBO.TB_MKA_PRODUCT D ON C.PRODUCT_NO = D.PRODUCT_NO
WHERE	1=1
AND		A.EVENT_NO = 1
AND		B.CLIENT_CODE = '1311001'


-- MK-03 회전 시작할 때 CheckLogic
-- 재학생 기준에서의 응모자 수
-- 재학생 수, 재학생 기준에서의 응모자 수, 이벤트 시작 날짜(초), 이벤트 종료 날짜(초), 현재 날짜(초)
SELECT	A.[재학생]												-- INT
		, A.[응모자수]											-- INT
		, DATEDIFF(S, B.EVENT_START_DATE, GETDATE())			-- 
		, DATEDIFF(S, EVENT_START_DATE, B.EVENT_END_DATE)		-- 
FROM (
		SELECT	SUM(A.재학생) [재학생], SUM(A.응모여부) [응모자수]
				--, (SELECT EVENT_NO, EVENT_NAME FROM G2_KEMS.DBO.TB_MKB_EVENT WHERE EVENT_NO = 1)
		FROM (
				SELECT	1 [재학생], CASE WHEN C.CLIENT_MEM_CODE IS NULL THEN 0 ELSE 1 END [응모여부]
				FROM	G2_KEMS.DBO.TB_CLB_MEMBER A
				INNER	JOIN G2_KEMS.DBO.TB_CLB_STUDENT B ON A.CLIENT_MEM_CODE = B.CLIENT_MEM_CODE
				LEFT	OUTER JOIN G2_KEMS.DBO.TB_MKB_EVENT_ENTER C ON B.CLIENT_MEM_CODE = C.CLIENT_MEM_CODE
				WHERE	A.CLIENT_CODE = '0608001'
				AND		A.CURRENT_APP_YN = 'Y'
				AND		B.STUDENT_STT_CODE IN ('31','32','33','34')
		) A
)A
INNER	JOIN (
		SELECT EVENT_START_DATE, EVENT_END_DATE, CONVERT(NCHAR(8), GETDATE(), 112) CURR_DATE
		FROM DBO.TB_MKB_EVENT 
		WHERE EVENT_NO = 1
)B ON 1=1

-- 캠퍼스별 상품 리스트
SELECT	D.PRODUCT_NAME											-- NVARCHAR(30)
		, C.LEFT_PRODUCT_CNT									-- INT
		, (	SELECT	SUM(PRODUCT_CNT)
			FROM	DBO.TB_MKB_EVENT_PRODUCT_CONFIG_CLIENT 
			WHERE	EVENT_NO = 1 
			AND		EVENT_SCHEDULE_SEQ = 1 
			AND		CLIENT_CODE = '0608001') AS PRODUCT_TOTAL	-- INT
FROM	DBO.TB_MKB_EVENT A
INNER	JOIN DBO.TB_MKB_EVENT_CLIENT B ON A.EVENT_NO = B.EVENT_NO
INNER	JOIN DBO.TB_MKB_EVENT_PRODUCT_CONFIG_CLIENT C ON B.EVENT_NO = C.EVENT_NO AND B.CLIENT_CODE = C.CLIENT_CODE
INNER	JOIN DBO.TB_MKA_PRODUCT D ON C.PRODUCT_NO = D.PRODUCT_NO
WHERE	1=1
AND		A.EVENT_NO = 1
AND		B.CLIENT_CODE = '0608001'


-- MK-03
SELECT	C.PRODUCT_NO			-- INT
		, D.PRODUCT_NAME		-- NVARCHAR(30)
		, C.LEFT_PRODUCT_CNT	-- INT
FROM	DBO.TB_MKB_EVENT A
INNER	JOIN DBO.TB_MKB_EVENT_CLIENT B ON A.EVENT_NO = B.EVENT_NO
INNER	JOIN DBO.TB_MKB_EVENT_PRODUCT_CONFIG_CLIENT C ON B.EVENT_NO = C.EVENT_NO AND B.CLIENT_CODE = C.CLIENT_CODE
INNER	JOIN DBO.TB_MKA_PRODUCT D ON C.PRODUCT_NO = D.PRODUCT_NO
WHERE	1=1
AND		A.EVENT_NO = 1
AND		B.CLIENT_CODE = '1311001'
AND		D.PRODUCT_NO = 7

USE G2_KEMS
GO
-- MK-06 (S1)
/*
INSERT	INTO DBO.TB_MKB_EVENT_ENTER 
VALUES	(1, 1, 52512, 6, 'Y', '폴리메시지', '03', GETDATE(), '09110400', '192.168.0.1', NULL, NULL, NULL)
*/
-- 변경 (2014-04-04)
INSERT	INTO DBO.TB_MKB_EVENT_ENTER (
										EVENT_NO				-- INT
										, EVENT_SCHEDULE_SEQ	-- INT
										, CLIENT_MEM_CODE		-- INT
										, PRODUCT_NO			-- INT
										, WIN_YN				-- NCHAR(1)
										, EVENT_MESSAGE			-- NVARCHAR(MAX)
										, STUDENT_STT_CODE		-- NCHAR(2)
										, FIRST_REG_DTTM		-- DATETIME
										, FIRST_REG_MEM_CODE	-- NVARCHAR(10)
										, FIRST_REG_IP			-- NVARCHAR(23)
								)	
VALUES	(1, 1, 52513, 6, 'Y', '폴리메시지', '03', GETDATE(), '09110400', '192.168.0.1')


-- MK-06 (U1)  -- 당첨 상품 차감
UPDATE	DBO.TB_MKB_EVENT_PRODUCT_CONFIG_CLIENT
SET		LEFT_PRODUCT_CNT = LEFT_PRODUCT_CNT - 1			-- INT
		, LAST_UPD_DTTM = GETDATE()						-- DATETIME
		, LAST_UPD_MEM_CODE = '09110400'				-- NVARCHAR(10)
		, LAST_UPD_IP = '192.168.0.1'					-- NVARCHAR(23)
WHERE	EVENT_NO = 1									
AND		PRODUCT_NO = 6									-- INT
AND		CLIENT_CODE = '1311001'							-- NVARCHAR(10)
AND		LEFT_PRODUCT_CNT > 0
AND		EVENT_SCHEDULE_SEQ = 1

-- MK-06 (U2)
UPDATE	DBO.TB_MKB_EVENT_CLIENT				
SET		LEFT_TARGET_CNT = LEFT_TARGET_CNT - 1
		, LAST_UPD_DTTM = GETDATE()
		, LAST_UPD_MEM_CODE = '09110400'
		, LAST_UPD_IP = '192.168.0.1'
WHERE	EVENT_NO = 1
AND		CLIENT_CODE = '1311001'			-- NVARCHAR(10)
AND		LEFT_TARGET_CNT > 0

-- MK-05
SELECT	A.MEMBER_CODE [학번]
		, A.MEMBER_NAME [이름]
		, A.STUDENT_STT [교육 상태]
		, A.CLIENT_NAME [캠퍼스명]
		, A.CLASS_NAME [클래스명]
		, A.COURSE_NAME [소속과정]
		, A.PRODUCT_NAME [이벤트 상품]
		, A.LOG_DTTM [로그인일시]
		, A.FIRST_REG_DTTM [이벤트 응모 일시]
		, A.EVENT_MESSAGE [이벤트 메시지]
		, A.EVENT_ENTER_YN [이벤트응모여부]
		, A.STUDENT_ADDR [주소]
		, A.MOTHER_HANDPHONE_NO [연락처]
FROM (
			SELECT	C.MEMBER_CODE
					, G2_KEMS.dbo.FN_SYD_GET_LOCALE_VALUE(C.N_LOCALE_CODE,'KR') [MEMBER_NAME]
					, CASE	WHEN C.STUDENT_STT_BRN = '02' THEN '대기'
							WHEN C.STUDENT_STT_BRN = '03' THEN '재학'	
							WHEN C.STUDENT_STT_BRN = '04' THEN '휴학'	
						END STUDENT_STT
					, G2_KEMS.DBO.FN_CLA_GET_CLIENT_NAME(C.CLIENT_CODE, 'KR', 'D') [CLIENT_NAME]
					, F.CLASS_NAME
					, G2_KEMS.dbo.FN_SYD_GET_LOCALE_VALUE(E.N_LOCALE_CODE, 'KR') [COURSE_NAME]
					, H.PRODUCT_NAME
					, LO.LOG_DTTM
					, G.FIRST_REG_DTTM
					, G.EVENT_MESSAGE
					, CASE	WHEN G.FIRST_REG_DTTM IS NULL THEN 'N' ELSE 'Y' END EVENT_ENTER_YN
					, C.ADDRESS +' '+ C.DETAIL_ADR STUDENT_ADDR
					, C.MOTHER_HANDPHONE_NO MOTHER_HANDPHONE_NO
					, C.CLIENT_MEM_CODE
			FROM	G2_KCMS.dbo.TB_CLB_CLASS_ASSIGN A WITH (NOLOCK)
			INNER	JOIN (
							SELECT	ROW_NUMBER() OVER (PARTITION BY A.CLIENT_MEM_CODE ORDER BY B.MANAGE_GBN, TL_STT) NO, A.CLASS_ASS_CODE 
							FROM	G2_KCMS.dbo.TB_CLB_CLASS_ASSIGN A WITH (NOLOCK)
							INNER	JOIN G2_KCMS.dbo.TB_CLB_CLASS B WITH (NOLOCK) ON A.CLIENT_CODE = B.CLIENT_CODE AND A.CLASS_CODE = B.CLASS_CODE
							WHERE	A.TL_STT IN ('01','02')
							AND		B.MANAGE_STT = '01'	-- 개강 클래스 
							AND		B.LEARNING_YEAR_CODE = 2013
							AND		B.SEMESTER_GBN = '02'
							--AND CONVERT(NCHAR(8), @SEARCH_DATE, 112) BETWEEN A.STATUS_SRT_DATE AND A.STATUS_END_DATE
							GROUP	BY A.CLIENT_MEM_CODE, A.CLASS_ASS_CODE,  B.MANAGE_GBN, A.TL_STT
						) B ON A.CLASS_ASS_CODE = B.CLASS_ASS_CODE
			INNER	JOIN G2_KCMS.dbo.VW_STA_STUDENT C WITH (NOLOCK) ON A.CLIENT_MEM_CODE = C.CLIENT_MEM_CODE
			LEFT	OUTER JOIN G2_LCMS.dbo.TB_MTA_COURSE D WITH (NOLOCK) ON C.FINAL_CRS_CODE = D.COURSE_CODE
			LEFT	OUTER JOIN G2_LCMS.dbo.TB_MTA_COURSE E WITH (NOLOCK) ON D.PARENT_CRS_CODE = E.COURSE_CODE	
			INNER	JOIN G2_KCMS.dbo.TB_CLB_CLASS F WITH (NOLOCK) ON A.CLIENT_CODE = F.CLIENT_CODE AND A.CLASS_CODE = F.CLASS_CODE
			LEFT	OUTER JOIN DBO.TB_MKB_EVENT_ENTER G ON C.CLIENT_MEM_CODE = G.CLIENT_MEM_CODE AND G.EVENT_NO = 1
			LEFT	OUTER JOIN DBO.TB_MKA_PRODUCT H ON G.PRODUCT_NO = H.PRODUCT_NO
			INNER	JOIN DBO.TB_MKB_EVENT_CLIENT I ON A.CLIENT_CODE = I.CLIENT_CODE
			FULL	OUTER JOIN G2_LOGDB.DBO.TB_CON_LOGIO_HOMEPAGE LO ON C.CLIENT_MEM_CODE = LO.CLIENT_MEM_CODE
			WHERE	1=1
			AND		B.NO = 1
			AND		C.STUDENT_STT_BRN IN ('02','03','04')	-- 대기/재학/휴학
)A
ORDER	BY A.CLIENT_NAME, A.MEMBER_CODE

