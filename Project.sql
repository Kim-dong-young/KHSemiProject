--DROP TABLE `MEMBER`;
--DROP TABLE `CATEGORY`;
--DROP TABLE `QUIZ`;
--DROP TABLE `PROBLEM`;
--DROP TABLE `ANSWER`;
--DROP TABLE `QUIZ_TAG`;
--DROP TABLE `BOOKMARK`;
--DROP TABLE `QUIZ_LOG`;
--DROP TABLE `QUIZ_RATE`;
--DROP TABLE `ACHIEVE`;
--DROP TABLE `MEMBER_ACHIEVE`;
--DROP TABLE `QUEST`;
--DROP TABLE `MEMBER_QUEST`;
--DROP TABLE `COMMUNITY`;
--DROP TABLE `COMMUNITY_COMMENT`;
--DROP TABLE `COMMUNITY_LIKE`;
--DROP TABLE `DAILY_CHEC`;
--DROP TABLE `QUIZ_COMMENT`;
--DROP TABLE `RP`;

<<<<<<< HEAD
CREATE TABLE MEMBER( --À¯Àú ¸ñ·Ï
=======
CREATE TABLE MEMBER( --ìœ ì € ëª©ë¡
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
    MEMBER_number number not null,
    MEMBER_id varchar2(50) not null,
    MEMBER_pwd varchar2(50) not null,
    MEMBER_nickname varchar2(50) not null,
    MEMBER_exp number DEFAULT 0 not null,
    MEMBER_image varchar2(50),
    MEMBER_join_date Date default SYSDATE not null,
    MEMBER_check_continueCount NUMBER NOT NULL,
    MEMBER_status VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    MEMBER_introduce VARCHAR2(100),
    PRIMARY KEY(MEMBER_number)
);
    
<<<<<<< HEAD
CREATE TABLE CATEGORY ( --Ä«Å×°í¸®
=======
CREATE TABLE CATEGORY ( --ì¹´í…Œê³ ë¦¬
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
    CATEGORY_number NUMBER NOT NULL,
    CATEGORY_name VARCHAR2(50) NOT NULL,
    PRIMARY KEY (CATEGORY_number)
);

<<<<<<< HEAD
CREATE TABLE QUIZ( --ÄûÁî¹®Á¦Áý
=======
CREATE TABLE QUIZ( --í€´ì¦ˆë¬¸ì œì§‘
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
    QUIZ_number number not null,
    QUIZ_title varchar2(50) not null,
    QUIZ_date date default SYSDATE not null,
    QUIZ_modify_date date default SYSDATE,
    MEMBER_number number not null,
    CATEGORY_number number not null,
    PRIMARY KEY(QUIZ_number),
    FOREIGN KEY(MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY(CATEGORY_number) references CATEGORY(CATEGORY_number)
);

<<<<<<< HEAD
CREATE TABLE PROBLEM( --¹®Á¦
=======
CREATE TABLE PROBLEM( --ë¬¸ì œ
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
    PROBLEM_number number not null,
    PROBLEM_content varchar2(50),
    PROBLEM_media_kind number,
    PROBLEM_media varchar2(50),
    PROBLEM_hint varchar2(50),
    QUIZ_number number not null,
    PRIMARY KEY(PROBLEM_number),
    FOREIGN KEY (QUIZ_number) REFERENCES QUIZ(QUIZ_number)
);

<<<<<<< HEAD
CREATE TABLE ANSWER ( --ÇØ´ä
=======
CREATE TABLE ANSWER ( --í•´ë‹µ
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
    ANSWER_number number not null,
    ANSWER_kind number not null,
    ANSWER_content varchar2(1000) not null,
    PROBLEM_number number not null,
    PRIMARY KEY (ANSWER_number),
    FOREIGN KEY (PROBLEM_number) references PROBLEM(PROBLEM_number)
);

<<<<<<< HEAD
CREATE TABLE QUIZ_TAG ( --ÄûÁî ÅÂ±×
    TAG_name varchar2(50) not null,
    QUIZ_number number not null,
    PRIMARY KEY (TAG_name, QUIZ_number), -- º¹ÇÕÅ°·Î ¼öÁ¤
    FOREIGN KEY (QUIZ_number) references QUIZ(QUIZ_number)
);

CREATE TABLE BOOKMARK ( --ºÏ¸¶Å©
=======
CREATE TABLE QUIZ_TAG ( --í€´ì¦ˆ íƒœê·¸
    TAG_name varchar2(50) not null,
    QUIZ_number number not null,
    PRIMARY KEY (TAG_name, QUIZ_number), -- ë³µí•©í‚¤ë¡œ ìˆ˜ì •
    FOREIGN KEY (QUIZ_number) references QUIZ(QUIZ_number)
);

CREATE TABLE BOOKMARK ( --ë¶ë§ˆí¬
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
    MEMBER_number number not null,
    QUIZ_number number not null,
    CONSTRAINT PK_BOOKMARK PRIMARY KEY (MEMBER_number, QUIZ_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (QUIZ_number) references QUIZ(QUIZ_number)
);

<<<<<<< HEAD
CREATE TABLE QUIZ_LOG( --Ç¬ ¹®Á¦Áý
=======
CREATE TABLE QUIZ_LOG( --í‘¼ ë¬¸ì œì§‘
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
    QUIZ_LOG_number number not null,
    MEMBER_number number not null,
    QUIZ_number number not null,
    QUIZ_LOG_count number not null,
    QUIZ_LOG_date date default SYSDATE not null,
    PRIMARY KEY (QUIZ_LOG_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (QUIZ_number) references QUIZ(QUIZ_number)
);

<<<<<<< HEAD
CREATE TABLE QUIZ_RATE( --ÄûÁîº°Á¡
=======
CREATE TABLE QUIZ_RATE( --í€´ì¦ˆë³„ì 
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
    QUIZ_rate_rating number not null,
    MEMBER_number number not null,
    QUIZ_number number not null,
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (QUIZ_number) references QUIZ(QUIZ_number)
);

<<<<<<< HEAD
CREATE TABLE ACHIEVE( --¾÷Àû
=======
CREATE TABLE ACHIEVE( --ì—…ì 
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
    ACHIEVE_number number not null,
    ACHIEVE_title varchar2(50) not null,
    ACHIEVE_content varchar2(100) not null,
    PRIMARY KEY (ACHIEVE_number)
);

<<<<<<< HEAD
CREATE TABLE MEMBER_ACHIEVE( --À¯Àúº° ¾÷Àû
=======
CREATE TABLE MEMBER_ACHIEVE( --ìœ ì €ë³„ ì—…ì 
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
    MEMBER_number number not null,
    ACHIEVE_number number not null,
    MEMBER_ACHIEVE_date date default SYSDATE not null,
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (ACHIEVE_number) references ACHIEVE(ACHIEVE_number)
);

<<<<<<< HEAD
CREATE TABLE QUEST( --Äù½ºÆ®
=======
CREATE TABLE QUEST( --í€˜ìŠ¤íŠ¸
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
    QUEST_number number not null,
    QUEST_content varchar2(100) not null,
    PRIMARY KEY (QUEST_number)
);

<<<<<<< HEAD
CREATE TABLE MEMBER_QUEST( --À¯Àúº°Äù½ºÆ®
=======
CREATE TABLE MEMBER_QUEST( --ìœ ì €ë³„í€˜ìŠ¤íŠ¸
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
    MEMBER_QUEST_number number not null,
    MEMBER_QUEST_success number default 0 not null,
    MEMBER_QUEST_date date default SYSDATE not null,
    MEMBER_number number not null,
    QUEST_number number not null,
    PRIMARY KEY (MEMBER_QUEST_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (QUEST_number) references QUEST(QUEST_number)        
);

<<<<<<< HEAD
CREATE TABLE COMMUNITY( --Ä¿¹Â´ÏÆ¼
=======
CREATE TABLE COMMUNITY( --ì»¤ë®¤ë‹ˆí‹°
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
    COMMUNITY_number number not null,
    COMMUNITY_title varchar2(50) not null,
    COMMUNITY_content varchar2(1000) not null,
    COMMUNITY_tab number not null,
    COMMUNITY_viewcount number not null,
    COMMUNITY_date date default SYSDATE not null,
    MEMBER_number number not null,
    PRIMARY KEY (COMMUNITY_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number)
);

<<<<<<< HEAD
CREATE TABLE COMMUNITY_COMMENT( --Ä¿¹Â´ÏÆ¼ ´ñ±Û
=======
CREATE TABLE COMMUNITY_COMMENT( --ì»¤ë®¤ë‹ˆí‹° ëŒ“ê¸€
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
    COMMUNITY_COMMENT_number number not null,
    COMMUNITY_parent_number number,
    COMMUNITY_number number not null,
    MEMBER_number number not null,
    COMMUNITY_COMMENT_content varchar2(500) not null,
    COMMUNITY_COMMENT_date date default SYSDATE not null,
    PRIMARY KEY (COMMUNITY_COMMENT_number),
    FOREIGN KEY (COMMUNITY_parent_number) references COMMUNITY_COMMENT(COMMUNITY_COMMENT_number),
    FOREIGN KEY (COMMUNITY_number) references COMMUNITY(COMMUNITY_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number)
);

<<<<<<< HEAD
CREATE TABLE COMMUNITY_LIKE( --Ä¿¹Â´ÏÆ¼ ÁÁ¾Æ¿ä
=======
CREATE TABLE COMMUNITY_LIKE( --ì»¤ë®¤ë‹ˆí‹° ì¢‹ì•„ìš”
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
    MEMBER_number number not null,
    COMMUNITY_number number not null,
    CONSTRAINT PK_COMMUNITY_LIKE PRIMARY KEY(MEMBER_number, COMMUNITY_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (COMMUNITY_number) references COMMUNITY(COMMUNITY_number)
);

<<<<<<< HEAD
CREATE TABLE DAILY_CHECK( --Ãâ¼®Å×ÀÌºí
=======
CREATE TABLE DAILY_CHECK( --ì¶œì„í…Œì´ë¸”
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
    DAILY_CHECK_number number not null,
    DAILY_CHECK_date date default SYSDATE not null,
    MEMBER_number number not null,
    PRIMARY KEY (DAILY_CHECK_number), 
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number)
);

<<<<<<< HEAD
CREATE TABLE QUIZ_COMMENT( --ÄûÁî´ñ±Û
=======
CREATE TABLE QUIZ_COMMENT( --í€´ì¦ˆëŒ“ê¸€
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
    QUIZ_COMMENT_number number not null, 
    QUIZ_COMMENT_content varchar2(500) not null,
    QUIZ_number number not null,
    MEMBER_number number not null,
    PRIMARY KEY (QUIZ_COMMENT_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (QUIZ_number) references QUIZ(QUIZ_number)
);

<<<<<<< HEAD
CREATE TABLE RP( --½Å°í
=======
CREATE TABLE RP( --ì‹ ê³ 
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
    RP_number number not null,
    RP_encounter_number number not null,
    RP_reason varchar2(50),
    RP_date date DEFAULT SYSDATE not null,
    MEMBER_number number not null,
<<<<<<< HEAD
    REPORTED_MEMBER_number number, -- MEMBER_number2¸¦ ¸íÈ®ÇÑ ÀÌ¸§À¸·Î º¯°æ
=======
    REPORTED_MEMBER_number number, -- MEMBER_number2ë¥¼ ëª…í™•í•œ ì´ë¦„ìœ¼ë¡œ ë³€ê²½
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
    QUIZ_number number,
    QUIZ_COMMENT_number number,
    COMMUNITY_number number,
    COMMUNITY_COMMENT_number number,
    PRIMARY KEY (RP_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
<<<<<<< HEAD
    FOREIGN KEY (REPORTED_MEMBER_number) references MEMBER(MEMBER_number), -- º¯°æµÈ ¿Ü·¡ Å°
=======
    FOREIGN KEY (REPORTED_MEMBER_number) references MEMBER(MEMBER_number), -- ë³€ê²½ëœ ì™¸ëž˜ í‚¤
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
    FOREIGN KEY (QUIZ_number) references QUIZ(QUIZ_number),
    FOREIGN KEY (QUIZ_COMMENT_number) references QUIZ_COMMENT(QUIZ_COMMENT_number),
    FOREIGN KEY (COMMUNITY_number) references COMMUNITY(COMMUNITY_number),
    FOREIGN KEY (COMMUNITY_COMMENT_number) references COMMUNITY_COMMENT(COMMUNITY_COMMENT_number)
);










<<<<<<< HEAD




=======
>>>>>>> b068bf7c736f901c5b2be630f395d6939788417c
