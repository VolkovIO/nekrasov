INSERT INTO AUTHOR VALUES (-1, 'Пушкин');
INSERT INTO AUTHOR VALUES (-2, 'Лермонтов');
INSERT INTO AUTHOR VALUES (-3, 'Беляев');

INSERT INTO GENRE VALUES (-11, 'Стихи');
INSERT INTO GENRE VALUES (-12, 'Фантастика');

INSERT INTO BOOK VALUES (-21, 'Сборник стихов', -11);
INSERT INTO BOOK  ( ID , NAME , FK_GENRE ) VALUES (-22, 'Человек амфибия', -12);

INSERT INTO AUTHOR_BOOK VALUES (-21, -1);    -- почему то сначала книга, после автор
INSERT INTO AUTHOR_BOOK ( AUTHOR_ID , BOOK_ID ) VALUES ( -21, -2);
INSERT INTO AUTHOR_BOOK ( AUTHOR_ID , BOOK_ID ) VALUES ( -22, -3);

INSERT INTO COMMENT VALUES (-31, 'Издательство Казань 1985 год', -22);
INSERT INTO COMMENT VALUES (-32, 'Морской дьявол', -22);