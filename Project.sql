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

CREATE TABLE MEMBER( --유저 목록
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
    
CREATE TABLE CATEGORY ( --카테고리
    CATEGORY_number NUMBER NOT NULL,
    CATEGORY_name VARCHAR2(50) NOT NULL,
    PRIMARY KEY (CATEGORY_number)
);

CREATE TABLE QUIZ( --퀴즈문제집
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

CREATE TABLE PROBLEM( --문제
    PROBLEM_number number not null,
    PROBLEM_content varchar2(50),
    PROBLEM_media_kind number,
    PROBLEM_media varchar2(50),
    PROBLEM_hint varchar2(50),
    QUIZ_number number not null,
    PRIMARY KEY(PROBLEM_number),
    FOREIGN KEY (QUIZ_number) REFERENCES QUIZ(QUIZ_number)
);

CREATE TABLE ANSWER ( --해답
    ANSWER_number number not null,
    ANSWER_kind number not null,
    ANSWER_content varchar2(1000) not null,
    PROBLEM_number number not null,
    PRIMARY KEY (ANSWER_number),
    FOREIGN KEY (PROBLEM_number) references PROBLEM(PROBLEM_number)
);

CREATE TABLE QUIZ_TAG ( --퀴즈 태그
    TAG_name varchar2(50) not null,
    QUIZ_number number not null,
    PRIMARY KEY (TAG_name, QUIZ_number), -- 복합키로 수정
    FOREIGN KEY (QUIZ_number) references QUIZ(QUIZ_number)
);

CREATE TABLE BOOKMARK ( --북마크
    MEMBER_number number not null,
    QUIZ_number number not null,
    CONSTRAINT PK_BOOKMARK PRIMARY KEY (MEMBER_number, QUIZ_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (QUIZ_number) references QUIZ(QUIZ_number)
);

CREATE TABLE QUIZ_LOG( --푼 문제집
    QUIZ_LOG_number number not null,
    MEMBER_number number not null,
    QUIZ_number number not null,
    QUIZ_LOG_count number not null,
    QUIZ_LOG_date date default SYSDATE not null,
    PRIMARY KEY (QUIZ_LOG_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (QUIZ_number) references QUIZ(QUIZ_number)
);

CREATE TABLE QUIZ_RATE( --퀴즈별점
    QUIZ_rate_rating number not null,
    MEMBER_number number not null,
    QUIZ_number number not null,
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (QUIZ_number) references QUIZ(QUIZ_number)
);

CREATE TABLE ACHIEVE( --업적
    ACHIEVE_number number not null,
    ACHIEVE_title varchar2(50) not null,
    ACHIEVE_content varchar2(100) not null,
    PRIMARY KEY (ACHIEVE_number)
);

CREATE TABLE MEMBER_ACHIEVE( --유저별 업적
    MEMBER_number number not null,
    ACHIEVE_number number not null,
    MEMBER_ACHIEVE_date date default SYSDATE not null,
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (ACHIEVE_number) references ACHIEVE(ACHIEVE_number)
);

CREATE TABLE QUEST( --퀘스트
    QUEST_number number not null,
    QUEST_content varchar2(100) not null,
    PRIMARY KEY (QUEST_number)
);

CREATE TABLE MEMBER_QUEST( --유저별퀘스트
    MEMBER_QUEST_number number not null,
    MEMBER_QUEST_success number default 0 not null,
    MEMBER_QUEST_date date default SYSDATE not null,
    MEMBER_number number not null,
    QUEST_number number not null,
    PRIMARY KEY (MEMBER_QUEST_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (QUEST_number) references QUEST(QUEST_number)        
);

CREATE TABLE COMMUNITY( --커뮤니티
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

CREATE TABLE COMMUNITY_COMMENT( --커뮤니티 댓글
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

CREATE TABLE COMMUNITY_LIKE( --커뮤니티 좋아요
    MEMBER_number number not null,
    COMMUNITY_number number not null,
    CONSTRAINT PK_COMMUNITY_LIKE PRIMARY KEY(MEMBER_number, COMMUNITY_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (COMMUNITY_number) references COMMUNITY(COMMUNITY_number)
);

CREATE TABLE DAILY_CHECK( --출석테이블
    DAILY_CHECK_number number not null,
    DAILY_CHECK_date date default SYSDATE not null,
    MEMBER_number number not null,
    PRIMARY KEY (DAILY_CHECK_number), 
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number)
);

CREATE TABLE QUIZ_COMMENT( --퀴즈댓글
    QUIZ_COMMENT_number number not null, 
    QUIZ_COMMENT_content varchar2(500) not null,
    QUIZ_number number not null,
    MEMBER_number number not null,
    PRIMARY KEY (QUIZ_COMMENT_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (QUIZ_number) references QUIZ(QUIZ_number)
);

CREATE TABLE RP( --신고
    RP_number number not null,
    RP_encounter_number number not null,
    RP_reason varchar2(50),
    RP_date date DEFAULT SYSDATE not null,
    MEMBER_number number not null,
    REPORTED_MEMBER_number number, -- MEMBER_number2를 명확한 이름으로 변경
    QUIZ_number number,
    QUIZ_COMMENT_number number,
    COMMUNITY_number number,
    COMMUNITY_COMMENT_number number,
    PRIMARY KEY (RP_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (REPORTED_MEMBER_number) references MEMBER(MEMBER_number), -- 변경된 외래 키
    FOREIGN KEY (QUIZ_number) references QUIZ(QUIZ_number),
    FOREIGN KEY (QUIZ_COMMENT_number) references QUIZ_COMMENT(QUIZ_COMMENT_number),
    FOREIGN KEY (COMMUNITY_number) references COMMUNITY(COMMUNITY_number),
    FOREIGN KEY (COMMUNITY_COMMENT_number) references COMMUNITY_COMMENT(COMMUNITY_COMMENT_number)
);














