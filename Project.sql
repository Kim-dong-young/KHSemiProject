DROP TABLE MEMBER CASCADE CONSTRAINTS;
DROP TABLE CATEGORY CASCADE CONSTRAINTS;
DROP TABLE QUIZ CASCADE CONSTRAINTS;
DROP TABLE PROBLEM CASCADE CONSTRAINTS;
DROP TABLE ANSWER CASCADE CONSTRAINTS;
DROP TABLE QUIZ_TAG CASCADE CONSTRAINTS;
DROP TABLE BOOKMARK CASCADE CONSTRAINTS;
DROP TABLE QUIZ_LOG CASCADE CONSTRAINTS;
DROP TABLE QUIZ_RATE CASCADE CONSTRAINTS;
DROP TABLE ACHIEVE CASCADE CONSTRAINTS;
DROP TABLE MEMBER_ACHIEVE CASCADE CONSTRAINTS;
DROP TABLE QUEST CASCADE CONSTRAINTS;
DROP TABLE MEMBER_QUEST CASCADE CONSTRAINTS;
DROP TABLE COMMUNITY_TAB CASCADE CONSTRAINTS;
DROP TABLE COMMUNITY CASCADE CONSTRAINTS;
DROP TABLE COMMUNITY_COMMENT CASCADE CONSTRAINTS;
DROP TABLE COMMUNITY_LIKE CASCADE CONSTRAINTS;
DROP TABLE DAILY_CHECK CASCADE CONSTRAINTS;
DROP TABLE QUIZ_COMMENT CASCADE CONSTRAINTS;
DROP TABLE RP CASCADE CONSTRAINTS;
DROP TABLE IMGS_file CASCADE CONSTRAINTS;
DROP TABLE COMMUNITY_IMG CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_MNO;
DROP SEQUENCE SEQ_DCNO;
DROP SEQUENCE SEQ_COMMUNITY;
DROP SEQUENCE SEQ_COMMUNITY_COMMENT;
DROP SEQUENCE SEQ_QUIZ;
DROP SEQUENCE SEQ_COMMUNITY_IMG;
DROP SEQUENCE SEQ_BNO;
DROP SEQUENCE SEQ_REPORT;
DROP SEQUENCE SEQ_PNO;
DROP SEQUENCE SEQ_QLOG;
DROP SEQUENCE SEQ_QUEST;
DROP SEQUENCE SEQ_AN;


CREATE SEQUENCE SEQ_MNO
      INCREMENT BY 1
          START WITH 7;
CREATE SEQUENCE SEQ_DCNO
      INCREMENT BY 1
          START WITH 8;

CREATE SEQUENCE SEQ_COMMUNITY
    INCREMENT BY 1
    START WITH 264;
    
CREATE SEQUENCE SEQ_COMMUNITY_IMG;
    
CREATE SEQUENCE SEQ_COMMUNITY_COMMENT
    INCREMENT BY 1
    START WITH 73;

CREATE SEQUENCE SEQ_QUIZ
    INCREMENT BY 1
    START WITH 4;

CREATE SEQUENCE SEQ_BNO;


CREATE SEQUENCE SEQ_PNO
    INCREMENT BY 1
    START WITH 13;

CREATE SEQUENCE SEQ_REPORT;

CREATE SEQUENCE SEQ_QLOG
    INCREMENT BY 1
    START WITH 42;
    
CREATE SEQUENCE SEQ_QUEST;

CREATE SEQUENCE SEQ_AN
    INCREMENT BY 1
    START WITH 13;

CREATE TABLE MEMBER(
    MEMBER_number number not null,
    MEMBER_id varchar2(50) not null unique,
    MEMBER_pwd varchar2(50) not null,
    MEMBER_nickname varchar2(50) not null,
    MEMBER_exp number DEFAULT 0 not null,
    MEMBER_image varchar2(300) DEFAULT '/static/img/userProfile/guest-icon.png',
    MEMBER_join_date Date default SYSDATE not null,
    MEMBER_check_continueCount NUMBER DEFAULT 0 NOT NULL,
    MEMBER_status VARCHAR2(1) DEFAULT 'Y' NOT NULL,
    MEMBER_introduce VARCHAR2(100),
    MEMBER_EMAIL VARCHAR2(100),
    ADDRESS VARCHAR2(100),
    PHONE VARCHAR2(100),
    PRIMARY KEY(MEMBER_number)
);
    
CREATE TABLE CATEGORY (
    CATEGORY_number NUMBER NOT NULL,
    CATEGORY_name VARCHAR2(50) NOT NULL,
    PRIMARY KEY (CATEGORY_number)
);

CREATE TABLE QUIZ(
    QUIZ_number number not null,
    QUIZ_title varchar2(1500) not null,
    QUIZ_date date default SYSDATE not null,
    QUIZ_modify_date date default SYSDATE,
    QUIZ_explanation varchar2(1500),
    MEMBER_number number not null,
    CATEGORY_number number not null,
    THUMBNAIL VARCHAR2(1500) DEFAULT 'static/img/THUMBNAIL/default.png',
    PRIMARY KEY(QUIZ_number),
    FOREIGN KEY(MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY(CATEGORY_number) references CATEGORY(CATEGORY_number)
);

CREATE TABLE PROBLEM( 
    PROBLEM_number number not null,
    PROBLEM_content varchar2(1500),
    PROBLEM_media_kind number,
    Ptime number,
   -- Pdate date,
    PROBLEM_media varchar2(1500),
    PROBLEM_hint varchar2(1500),
    QUIZ_number number not null,
    PRIMARY KEY(PROBLEM_number),
    FOREIGN KEY (QUIZ_number) REFERENCES QUIZ(QUIZ_number)
);

CREATE TABLE ANSWER (
    ANSWER_number number not null,
    ANSWER_content varchar2(1000) not null,
    PROBLEM_number number not null,
    PRIMARY KEY (ANSWER_number),
    FOREIGN KEY (PROBLEM_number) references PROBLEM(PROBLEM_number)
);

CREATE TABLE QUIZ_TAG (
    TAG_name varchar2(50) not null,
    QUIZ_number number not null,
    PRIMARY KEY (TAG_name, QUIZ_number),
    FOREIGN KEY (QUIZ_number) references QUIZ(QUIZ_number)
);

CREATE TABLE BOOKMARK (
    BOOKMARK_NUMBER NUMBER unique not null,
    MEMBER_number number not null,
    QUIZ_number number not null,
    CONSTRAINT PK_BOOKMARK PRIMARY KEY (MEMBER_number, QUIZ_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (QUIZ_number) references QUIZ(QUIZ_number)
);

CREATE TABLE QUIZ_LOG( 
    QUIZ_LOG_number number not null,
    MEMBER_number number not null,
    QUIZ_number number not null,
    QUIZ_LOG_count number not null,
    QUIZ_LOG_date date default SYSDATE not null,
    PRIMARY KEY (QUIZ_LOG_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (QUIZ_number) references QUIZ(QUIZ_number)
);

CREATE TABLE QUIZ_RATE( 
    QUIZ_rate_rating number not null,
    MEMBER_number number not null,
    QUIZ_number number not null,
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (QUIZ_number) references QUIZ(QUIZ_number)
);

CREATE TABLE ACHIEVE(
    ACHIEVE_number number not null,
    ACHIEVE_title varchar2(50) not null,
    ACHIEVE_content varchar2(100) not null,
    PRIMARY KEY (ACHIEVE_number)
);

CREATE TABLE MEMBER_ACHIEVE( 
    MEMBER_number number not null,
    ACHIEVE_number number not null,
    MEMBER_ACHIEVE_date date default SYSDATE not null,
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (ACHIEVE_number) references ACHIEVE(ACHIEVE_number)
);

CREATE TABLE QUEST(
    QUEST_number number not null,
    QUEST_content varchar2(100) not null,
    PRIMARY KEY (QUEST_number)
);

CREATE TABLE MEMBER_QUEST(
    MEMBER_QUEST_number number not null,
    MEMBER_QUEST_success number default 0 not null,
    MEMBER_QUEST_date date default SYSDATE not null,
    MEMBER_number number not null,
    QUEST_number number not null,
    PRIMARY KEY (MEMBER_QUEST_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (QUEST_number) references QUEST(QUEST_number)        
);

CREATE TABLE COMMUNITY_TAB(
    TAB_number number not null,
    TAB_name varchar2(20) not null,
    PRIMARY KEY (TAB_number)
);

CREATE TABLE COMMUNITY( 
    COMMUNITY_number number not null,
    COMMUNITY_title varchar2(50) not null,
    COMMUNITY_content varchar2(3000) not null,
    COMMUNITY_viewcount number not null,
    COMMUNITY_date date default SYSDATE not null,
    MEMBER_number number not null,
    TAB_number number not null,
    PRIMARY KEY (COMMUNITY_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (TAB_number) references COMMUNITY_TAB(TAB_number)
);


CREATE TABLE COMMUNITY_COMMENT(
    COMMUNITY_COMMENT_number number not null,
    COMMUNITY_parent_number number,
    COMMUNITY_number number not null,
    MEMBER_number number not null,
    COMMUNITY_COMMENT_content varchar2(500) not null,
    COMMUNITY_COMMENT_date date default SYSDATE not null,
    COMMENT_GROUP NUMBER DEFAULT 0,
    COMMENT_DEPTH NUMBER DEFAULT 0,
    COMMENT_ORDER NUMBER DEFAULT 0,
    COMMENT_CHILD_COUNT NUMBER DEFAULT 0,
    COMMENT_STATUS VARCHAR(3) DEFAULT 'Y' CHECK (COMMENT_STATUS IN ('Y','N')) ,
    PRIMARY KEY (COMMUNITY_COMMENT_number),
    FOREIGN KEY (COMMUNITY_parent_number) references COMMUNITY_COMMENT(COMMUNITY_COMMENT_number) ON DELETE CASCADE,
    FOREIGN KEY (COMMUNITY_number) references COMMUNITY(COMMUNITY_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number)
);

CREATE TABLE COMMUNITY_LIKE(
    MEMBER_number number not null,
    COMMUNITY_number number not null,
    CONSTRAINT PK_COMMUNITY_LIKE PRIMARY KEY(MEMBER_number, COMMUNITY_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (COMMUNITY_number) references COMMUNITY(COMMUNITY_number)
);

CREATE TABLE DAILY_CHECK( 
    DAILY_CHECK_number number not null,
    DAILY_CHECK_date date default SYSDATE not null,
    MEMBER_number number not null,
    PRIMARY KEY (DAILY_CHECK_number), 
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number)
);

CREATE TABLE QUIZ_COMMENT( 
    QUIZ_COMMENT_number number not null, 
    QUIZ_COMMENT_content varchar2(500) not null,
    QUIZ_number number not null,
    MEMBER_number number not null,
    PRIMARY KEY (QUIZ_COMMENT_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (QUIZ_number) references QUIZ(QUIZ_number)
);

CREATE TABLE RP( 
    RP_number number not null,
    RP_encounter_number number not null,
    RP_reason varchar2(50),
    RP_date date DEFAULT SYSDATE not null,
    MEMBER_number number not null,
    REPORTED_MEMBER_number number, 
    QUIZ_number number,
    QUIZ_COMMENT_number number,
    COMMUNITY_number number,
    COMMUNITY_COMMENT_number number,
    PRIMARY KEY (RP_number),
    FOREIGN KEY (MEMBER_number) references MEMBER(MEMBER_number),
    FOREIGN KEY (REPORTED_MEMBER_number) references MEMBER(MEMBER_number), 
    FOREIGN KEY (QUIZ_number) references QUIZ(QUIZ_number),
    FOREIGN KEY (QUIZ_COMMENT_number) references QUIZ_COMMENT(QUIZ_COMMENT_number),
    FOREIGN KEY (COMMUNITY_number) references COMMUNITY(COMMUNITY_number),
    FOREIGN KEY (COMMUNITY_COMMENT_number) references COMMUNITY_COMMENT(COMMUNITY_COMMENT_number)
);

CREATE TABLE IMGS_file (
    IMGS_file_id NUMBER PRIMARY KEY NOT NULL,  -- ????? ID
    IMGS_link_path VARCHAR2(1500),              -- ????? ???? ???
    IMGS_file_name VARCHAR2(255),     -- ????? ???? ???
    IMGS_file_upload_date DATE DEFAULT SYSDATE,  -- ???¥å? ???
    FOREIGN KEY (IMGS_file_id) references PROBLEM(problem_number)
);

CREATE TABLE COMMUNITY_IMG (
    FILE_NO NUMBER PRIMARY KEY,
    COMMUNITY_NUMBER NUMBER,
    ORIGIN_NAME VARCHAR2(300) NOT NULL,
    CHANGE_NAME VARCHAR2(300) NOT NULL,
    FILE_PATH VARCHAR2(600) NOT NULL,
    UPLOAD_DATE DATE DEFAULT SYSDATE,
    FILE_LEVEL NUMBER DEFAULT 1,
    STATUS VARCHAR(3)  DEFAULT 'Y' CHECK (STATUS IN ('Y','N')),
    FOREIGN KEY(COMMUNITY_NUMBER) REFERENCES COMMUNITY(COMMUNITY_NUMBER)
);



