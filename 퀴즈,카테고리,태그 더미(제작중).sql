INSERT INTO CATEGORY
VALUES (1, '����');
INSERT INTO CATEGORY
VALUES (2, '���� / ����');
INSERT INTO CATEGORY
VALUES (3, '����');
INSERT INTO CATEGORY
VALUES (4, '����');
INSERT INTO CATEGORY
VALUES (5, '���');
INSERT INTO CATEGORY
VALUES (6, '���� / �ڿ�');
INSERT INTO CATEGORY
VALUES (7, '������');
INSERT INTO CATEGORY
VALUES (8, '��Ÿ');

DROP SEQUENCE SEQ_QUIZ;

CREATE SEQUENCE SEQ_QUIZ
    INCREMENT BY 1
    START WITH 23;


INSERT INTO QUIZ
VALUES (1, '�ι��̸����߱�', SYSDATE, SYSDATE, '��ȣ', 1, 2, '');
commit;

INSERT INTO QUIZ
VALUES (2, '������� ��� ���', SYSDATE, SYSDATE, '���� ���� ��ȣ', 1, 1, '');
INSERT INTO QUIZ
VALUES (3, '�����¼����ܼ�', SYSDATE, SYSDATE, '�̰Դ�ü ���� �Ҹ���', 1, 3, '');
INSERT INTO QUIZ
VALUES (4, '����ȣ�� ���K��', SYSDATE, SYSDATE, '��� �� �׾��!', 1, 2, '');
INSERT INTO QUIZ
VALUES (5, '���� ����', SYSDATE, SYSDATE, '���� ���̱� �����̴�.', 1, 4, '');
INSERT INTO QUIZ
VALUES (6, '�ʴ� �����ΰ�?', SYSDATE, SYSDATE, '���� �ʰ� �ƴϱ� ������ �𸥴�.', 1, 5, '');
INSERT INTO QUIZ
VALUES (7, '�����ää�', SYSDATE, SYSDATE, '�ǤŤ̤����ä���,�̤��ͤ��̤�', 1, 6, '');
INSERT INTO QUIZ
VALUES (8, '�����Ĥļ¤���', SYSDATE, SYSDATE, '����', 1, 7, '');
INSERT INTO QUIZ
VALUES (9, '�������', SYSDATE, SYSDATE, '�����߾��', 1, 8, '');
INSERT INTO QUIZ
VALUES (10, '�Ƥ������Ӥ��Ƥ��Ӥ����Ƥ�', SYSDATE, SYSDATE, '������', 1, 3, '');
INSERT INTO QUIZ
VALUES (11, '�Ͼߴ٤̾ߤ��̴��Ƥä�', SYSDATE, SYSDATE, '������������', 1, 2, '');
INSERT INTO QUIZ
VALUES (12, '����ߤ��A', SYSDATE, SYSDATE, '����ȣ~~~', 1, 1, '');
INSERT INTO QUIZ
VALUES (13, '����ߤ��A', SYSDATE, SYSDATE, '����ȣ~~~', 1, 1, '');
INSERT INTO QUIZ
VALUES (14, '����ߤ��A', SYSDATE, SYSDATE, '����ȣ~~~', 1, 1, '');
INSERT INTO QUIZ
VALUES (15, '����ߤ��A', SYSDATE, SYSDATE, '����ȣ~~~', 1, 1, '');
INSERT INTO QUIZ
VALUES (16, '����ߤ��A', SYSDATE, SYSDATE, '����ȣ~~~', 1, 1, '');
INSERT INTO QUIZ
VALUES (17, '����ߤ��A', SYSDATE, SYSDATE, '����ȣ~~~', 1, 1, '');
INSERT INTO QUIZ
VALUES (18, '����ߤ��A', SYSDATE, SYSDATE, '����ȣ~~~', 1, 1, '');
INSERT INTO QUIZ
VALUES (19, '����ߤ��A', SYSDATE, SYSDATE, '����ȣ~~~', 1, 1, '');
INSERT INTO QUIZ
VALUES (20, '����ߤ��A', SYSDATE, SYSDATE, '����ȣ~~~', 1, 1, '');
INSERT INTO QUIZ
VALUES (21, '����ߤ��A', SYSDATE, SYSDATE, '����ȣ~~~', 1, 1, '');
INSERT INTO QUIZ
VALUES (22, '����ߤ��A', SYSDATE, SYSDATE, '����ȣ~~~', 1, 1, '');



COMMIT;

INSERT INTO QUIZ_TAG
VALUES ('����', 3);

INSERT INTO QUIZ_TAG
VALUES ('���', 1);

INSERT INTO QUIZ_TAG
VALUES ('����', 4);

INSERT INTO QUIZ_TAG
VALUES ('���', 3);

COMMIT;






