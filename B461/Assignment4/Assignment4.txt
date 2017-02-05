--A portion of this assignment was done on paper, if you want a physical copy of it instead of pictures 
--just email me

----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
CREATE DATABASE "Assignment_4"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;


CREATE TABLE w (A INTEGER, B VARCHAR(5));
INSERT INTO w values(1,'John');
INSERT INTO w values(2,'Ellen');
INSERT INTO w values(3,'Ann');

--1
RA expression written in notes

atLeastTwo = pi r.A(sigma r.A = r1.A and r.A = r2.A and r1.B <> r2.B(RX R1 X R2))
atLeastOne = pi r.A(R)







CREATE TABLE student(sid INTEGER, sname VARCHAR(15));
CREATE TABLE major(sid INTEGER, major VARCHAR(15));
CREATE TABLE book(bookNo INTEGER, title VARCHAR(30), price INTEGER);
CREATE TABLE cites(bookNo INTEGER, citedBookNo INTEGER);
CREATE TABLE buys(sid INTEGER, bookNo INTEGER);


\COPY student FROM 'C:\Users\Ryan\Desktop\B461\Assignment2\student.csv' (DELIMITER(E'\t'));
\COPY major FROM 'C:\Users\Ryan\Desktop\B461\Assignment2\major.csv' (DELIMITER(E'\t'));
\COPY book FROM 'C:\Users\Ryan\Desktop\B461\Assignment2\book.csv' (DELIMITER(E'\t'));
\COPY student FROM 'C:\Users\Ryan\Desktop\B461\Assignment2\buys.csv' (DELIMITER(E'\t'));
\COPY cites FROM 'C:\Users\Ryan\Desktop\B461\Assignment2\Cites_updated.txt' (DELIMITER(E';'));
\COPY buys FROM 'C:\Users\Ryan\Desktop\B461\Assignment2\Buys_updated.txt' (DELIMITER(E';'));

ALTER TABLE student
ADD PRIMARY KEY (sid);

ALTER TABLE major
ADD PRIMARY KEY (sid, major);

ALTER TABLE book
ADD PRIMARY KEY (bookNo);

ALTER TABLE cites
ADD PRIMARY KEY (bookNo,citedBookNo);

ALTER TABLE buys
ADD PRIMARY KEY (sid, bookNo);



ALTER TABLE major
ADD FOREIGN KEY (Sid)
REFERENCES student(Sid);


ALTER TABLE cites
ADD FOREIGN KEY (BookNo)
REFERENCES book(BookNo);

ALTER TABLE cites
ADD FOREIGN KEY (CitedBookNo)
REFERENCES book(BookNo);

ALTER TABLE buys
ADD FOREIGN KEY (Sid)
REFERENCES student(Sid);

ALTER TABLE buys
ADD FOREIGN KEY (BookNo)
REFERENCES book(BookNo);
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------


--2

--2.a
select distinct s.sid, s.sname
from student s, buys by, cites c
where s.sid = by.sid and by.bookNo = c.bookNo

WITH 
E1 AS (select distinct s.sid, s.sname from student s, buys by, cites c where s.sid = by.sid and by.bookNo = c.bookNo)
----------------------------------------------------------------------------------
--RA on paper

--2.b
WITH
E1 AS (select distinct s.sid, s.sname from student s, major m1, major m2 where s.sid = m1.sid and s.sid = m2.sid and m1.major <> m2.major)
----------------------------------------------------------------------------------
--RA on paper


--2.c
WITH
E1 AS (select s.sid from student s, buys by1, buys by2),
E2 AS (select s.sid from student s, buys by1, buys by2 where s.sid = by1.sid and s.sid = by2.sid and by1.bookNo <> by2.bookNo),
E3 AS ((select * from E1) EXCEPT (select * from E2))
select distinct q.sid from E3 q
----------------------------------------------------------------------------------
--RA on paper


--2.d
WITH
E1 AS (select DISTINCT b3.bookNo from book b3, student s4, book b1, book b2),
E2 AS (select DISTINCT b3.bookNo  from book b3, student s4, book b1, book b2 where b1.price > b2.price and b3.price = b1.price)
((select * from E1) EXCEPT (select * from E2))
----------------------------------------------------------------------------------
--RA on paper
----------------------------------------------------------------------------------

--2.e
WITH
E1 as (select b.bookNo from book b where b.price > 50),
E2 as (select distinct b1.bookNo 
	from book b1 
	where Not exists (select *
			 from (
				(select c.bookNo 
				from cites c
				where c.citedBookNo = b1.bookNo)
					INTERSECT
				(select * from E1)
			      ) q
			 )
       )
select * from E2;
----------------------------------------------------------------------------------
--RA on paper
----------------------------------------------------------------------------------


--2.f
WITH
E1 as (select b.bookno from book b where b.Price < 30),
E2 as (select s.sid from student s
	where not exists( (select T.bookno from buys T where s.sid = T.sid)
                  	  EXCEPT 
                  	  (select * from E1)
			)
       )
select * from E2; 
----------------------------------------------------------------------------------
--RA on paper
----------------------------------------------------------------------------------


--2.g
WITH
E1 as (select m.sid as sid from major m where m.major = 'CS'),
E2 as (select b.bookno 
	from book b 
	where not exists (select s.sid 
			from E1 s 
			where not exists (select T.bookno 
					from buys T 
					where  s.sid = T.sid and b.bookno = T.bookNo)))
select b.bookNo from book b where  not exists (select bookNo from E2 where b.bookNo = bookNo)
----------------------------------------------------------------------------------
--RA on paper
----------------------------------------------------------------------------------


--2.h
WITH
E1 AS(select distinct s1.sid, s2.sid
	from student s1, student s2, book b
	where not exists
	(
	 (select bookNo from buys by where by.sid = s1.sid)
	 EXCEPT
	 (select bookNo from buys by where by.sid = s2.sid)
	)
     )
select * from E1;
----------------------------------------------------------------------------------
--RA on paper
----------------------------------------------------------------------------------


--2.i
WITH
E1 AS (select distinct T.bookno from cites T
   where  not exists (select s1.bookNo from book s1
                      where not exists (select T1.bookNo from cites T1
                                            where  T1.citedbookno = T.bookno and T1.bookNo = s1.bookNo))),
E2 AS (select distinct b.bookno from cites b
   where  exists (select s1.bookNo, s2.bookNo from book s1, book s2
                  where  s1.bookNo <> s2.bookNo and
                         not exists (select T1.bookNo from cites T1
                                        where T1.citedBookNo = b.bookno and s1.bookNo = T1.bookNo) and
                         not exists (select T1.bookNo from cites T1
                                        where T1.citedbookno = b.bookno and s2.bookNo = T1.bookNo) )),
E3 AS (select * from ((select b.bookno from bookCitedByAllBooks b)
			union 
 		      (select b.bookno from bookCitedByAllButTwoBooks b)) q
      ),
E4 AS (select distinct b.bookno from book b)
select * from ((select* from E4) except (select* from E3)) q
----------------------------------------------------------------------------------
--RA on paper


----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
CREATE TABLE A(x INTEGER);
CREATE TABLE B(x INTEGER);

INSERT INTO A values(1);
INSERT INTO A values(3);

INSERT INTO B values(1);
INSERT INTO B values(2);
INSERT INTO B values(3);
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------


--Part 2
--1.a

select (select count(1)
	from ((select a.x from a)
	       except
	      (select b.x from b)) q) = 0 as "PropertySatisfied";
----------------------------------------------------------------------------------

INSERT INTO A values(4);

--1.b
select ((select count(1)
	from (select a.x from a)q)
	=
	(select count(1)
	from (select b.x from b)q)) as "PropertySatisfied"; 
----------------------------------------------------------------------------------


--1.c
select (select count(1)
	from ((select a.x from a)
	       intersect
	      (select b.x from b)) q) >= 2 as "PropertySatisfied";
----------------------------------------------------------------------------------


--1.d
select ((select count(x) from a) IN (select x from b)) as "PropertySatisfied";
----------------------------------------------------------------------------------


--2.a.i
(select s.sid, count(1) as CoursesTaken
from student s, buys by, book b
where s.sid = by.sid and by.bookNo = b.bookNo and b.price>= 20 and b.price <= 40
group by s.sid)
union
(select s2.sid, 0 as CoursesTaken
from student s2
where (select count(1)
	from
	(select s.sid
	from student s, buys by, book b
	where s.sid = by.sid 
		and by.bookNo = b.bookNo 
		and b.price>= 20 
		and b.price <= 40 
		and s2.sid =s.sid) as q) = 0);
----------------------------------------------------------------------------------

--2.a.ii
CREATE FUNCTION numberBooks(student INTEGER)
RETURNS bigint as
$$
select count(1)
from student s, buys by, book b
where s.sid = by.sid and by.bookNo = b.bookNo and b.price>= 20 and b.price <= 40 and s.sid = student;
$$ LANGUAGE SQL

select distinct s.sid, numberBooks(s.sid)
from student s;
----------------------------------------------------------------------------------


--2.b.i
CREATE FUNCTION notCited(bookNumber INTEGER)
RETURNS boolean as
$$
select count(1) = 0
from (select distinct b.bookNo
      from book b, cites c
      where b.bookNo = c.citedBookNo 
	and b.bookNo = bookNumber) as q;
$$ LANGUAGE SQL

select bookNo
from book
where notCited(bookNo);
----------------------------------------------------------------------------------


--2.b.ii


select q2.bookNo 
from ((select bookNo 
	from book) 
		EXCEPT 
	(select b1.bookNo
	from book b1, lateral(select distinct b.bookNo
      				from book b, cites c
      				where b.bookNo=c.citedBookNo and b.bookNo = b1.bookNo) as q )) q2
----------------------------------------------------------------------------------


--2.c
CREATE VIEW citedBook AS (select c.citedBookno AS bookno from cites c); 

CREATE VIEW studentWithAtLeastTwoMajors AS 
     (select distinct m1.sid 
	from major m1 
	where (select count(1) 
		from major m 
		where m.sid = m1.sid 
		group by sid) >=2);


select distinct s.sid, s.sname 
from student s 
where  (select count(1) 
	from (select sm.sid 
		from studentwithatleasttwomajors sm 
		where sm.sid = s.sid) q1)>=1
	and 
	(select count(1) 
	from (select b.bookno 
		from book b 
      		where  (select count(1) 
			from((select T.bookno 
				from buys T 
				where s.sid = T.sid and T.bookNo = b.bookNo) 
                              except
                              (select cb.bookno 
				from citedbook cb) ) q3) >=1 ) q2) = 0;
----------------------------------------------------------------------------------


--2.d.i
Find the sid and major of each student who bought at least one book, 
but who did not buy any books that cost less than $30.

select q1.sid, major.major
from(
	(
	select sid
	from (select student.sid, buys.bookNo 
		from student left join buys on student.sid = buys.sid) q
	group by sid --group by line
	)

	EXCEPT
	(
	select distinct sid
	from (select q.sid, q.bookNo, book.price 
		from (select student.sid, buys.bookNo 
			from student left join buys on student.sid = buys.sid) q left join book on q.bookNo = book.bookNo) boughtBooks
	where boughtBooks.price <30
	)
     ) q1 left join major on q1.sid = major.sid 
----------------------------------------------------------------------------------


--2.d.ii

--statement that replaced group by statement
--select distinct s.sid
--	from student s,
--		lateral (select student.sid, buys.bookNo --lateral line
--			from student left join buys on student.sid = buys.sid and student.sid = s.sid) q


select q1.sid, major.major
from(
	(
	select distinct s.sid
	from student s,
		lateral (select student.sid, buys.bookNo --lateral line
			from student left join buys on student.sid = buys.sid and student.sid = s.sid) q
	)

	EXCEPT
	(
	select distinct sid
	from (select q.sid, q.bookNo, book.price 
		from (select student.sid, buys.bookNo 
			from student left join buys on student.sid = buys.sid) q left join book on q.bookNo = book.bookNo) boughtBooks
	where boughtBooks.price <30
	)
     ) q1 left join major on q1.sid = major.sid 
----------------------------------------------------------------------------------


--2.e.i
select s1.sid, s2.sid

from	(select distinct s.sid, (select count(by.bookNo)
				from buys by
				where s.sid = by.sid) as numberBooks
	from student s) s1,
	(select distinct s.sid, (select count(by.bookNo)
				from buys by
				where s.sid = by.sid) as numberBooks
	from student s) s2


where s1.sid <> s2.sid and s1.numberBooks >= s2.numberBooks
----------------------------------------------------------------------------------


--2.e.ii
select s1.sid, s2.sid 
from student s1, student s2

where (select count(by.bookNo) as books
	from buys by
	where s1.sid = by.sid
	group by s1.sid) 
     >= 
	(select count(by.bookNo) as books
	from buys by
	where s2.sid = by.sid
	group by s2.sid)
     and
	s1.sid <> s2.sid;
----------------------------------------------------------------------------------


--2.f
select distinct by1.bookno
from buys by1, lateral (select count(1) from buys by where by1.bookNo = by.bookNo) q
where q.count = (select count(1) from student)-1
----------------------------------------------------------------------------------


--2.Jaccard


create or replace function C(bookFind INTEGER)
returns table (citedBook INTEGER) AS
$$
   SELECT c.citedBookNo 
   FROM cites c
   WHERE c.bookNo = bookFind 
$$ LANGUAGE SQL

create or replace function score( a INTEGER, b INTEGER) 
returns float AS 
$$
select
(
select cast( 


(select count(*) 
FROM
	((SELECT * FROM C(a) q1) 
    	 INTERSECT
 	(SELECT * FROM C(b) q1)) q)
as float)
)
/
(
select cast( 


(select count(*) 
FROM
	((SELECT * FROM C(a) q1) 
    	 UNION
 	(SELECT * FROM C(b) q1)) q)
as float)
)
$$ LANGUAGE SQL



create or replace function Jaccard(l float, u float) 
returns table (book1 integer, book2 integer, j float) AS 
$$
select b1.bookNo, b2.bookNo, score(b1.bookNo, b2.bookNo)
from cites b1, cites b2
where score(b1.bookNo,b2.bookNo) <= u and score(b1.bookNo, b2.bookNo) >= l and b1.bookNo <> b2.bookNo
$$ LANGUAGE SQL
----------------------------------------------------------------------------------



----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
CREATE TABLE courses(sid INTEGER, topic VARCHAR(15), cno INTEGER);
INSERT INTO courses values(2,'CS',241);
INSERT INTO courses values(2,'CS',461);

INSERT INTO courses values(3,'CS',241);
INSERT INTO courses values(3,'CS',461);
INSERT INTO courses values(3,'Math',241);
INSERT INTO courses values(3,'Math',241);

INSERT INTO courses values(5,'Math',211);
INSERT INTO courses values(5,'CS',461);
INSERT INTO courses values(5,'Biology',371);
INSERT INTO courses values(5,'Physics',201);
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------

--3.a
create or replace function Simpson(student integer) 
returns float AS 
$$ 
   select ((select * from leadingCoeffecientFromCourses)*(1-sum(power((ctd.percentage/100.0),2.0)))) as SimpsonDiversity
   from courseTopicDistribution ctd
   where ctd.sid = student
$$ LANGUAGE SQL
----------------------------------------------------------------------------------

create or replace view leadingCoeffecientFromCourses as
(
	select
	(
		select
		cast( (select count(distinct topic) from courses) as float)
	)
   /
	(
		select
		cast( (select count(distinct topic)-1 from courses) as float)
	)
)
----------------------------------------------------------------------------------

create or replace view CourseTopicDistribution as
(

  select distinct c.sid, q.percentage *100 as percentage, c.topic
  from courses c, lateral
  (
	select 
	(
		select
		cast( (select count(c2.topic) from courses c2 where c2.sid = c.sid and c2.topic = c.topic) as float)
	) 

    /

	(
		select 
		cast( (select count(c2.topic) from courses c2 where c2.sid = c.sid) as float)
	) as percentage
  ) q
  order by c.sid
)
----------------------------------------------------------------------------------


--3.b
select distinct c.sid, simpson(c.sid) as SimpsonMeasure
from courses c
----------------------------------------------------------------------------------


--3.c
create or replace function SimpsonRange(l float, u float) 
returns table(sid integer) AS 
$$
   select distinct c.sid
   from courses c
   where simpson(c.sid) <= u and simpson(c.sid) >=l
$$ LANGUAGE SQL
