CREATE DATABASE "Assignment_7"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;





CREATE TABLE graph (s INTEGER, t INTEGER);


INSERT INTO graph values(0,1);
INSERT INTO graph values(0,2);
INSERT INTO graph values(2,1);
INSERT INTO graph values(1,3);
INSERT INTO graph values(1,4);
INSERT INTO graph values(1,6);
INSERT INTO graph values(3,5);
INSERT INTO graph values(4,5);

INSERT INTO graph values(6,1);
INSERT INTO graph values(5,0);



CREATE TABLE TC (s INTEGER, t INTEGER);


Create or replace function transitive_closure()
returns table (s integer, t integer) AS
$$
Declare N Integer;
Begin
	Drop table TC cascade;
	Create table TC(s integer, t integer);

		select count(T.node) into N
		from(
			(select edge.s as node
			from graph edge)
			UNION
			(select edge.t as node
			from graph edge)
			) T;
	Create or replace view Q1 as
		(select pair.s, edge.t
		from TC pair, graph edge
		where pair.t = edge.s and
		(pair.s,edge.t) not in (select * from TC)
		);
	Insert into TC (select * from graph);
	while(n>0)
		loop
		begin
			insert into TC (select * from Q1);
			n := n-1;
		end;
		end loop;
	return query(select distinct * from TC);
End;
$$ LANGUAGE PLPGSQL;

select * from transitive_closure() order by s desc;



----removal of a non-articulation node
--DROP TABLE graph CASCADE;
--CREATE TABLE graph (s INTEGER, t INTEGER);

--INSERT INTO graph values(0,1);
--INSERT INTO graph values(0,2);
--INSERT INTO graph values(2,1);
--INSERT INTO graph values(1,3);
--INSERT INTO graph values(1,4);
----INSERT INTO graph values(1,6);
--INSERT INTO graph values(3,5);
--INSERT INTO graph values(4,5);

----INSERT INTO graph values(6,1);
--INSERT INTO graph values(5,0);


----removal of a articulation node
--DROP TABLE graph CASCADE;
--CREATE TABLE graph (s INTEGER, t INTEGER);

----INSERT INTO graph values(0,1);
--INSERT INTO graph values(0,2);
----INSERT INTO graph values(2,1);
----INSERT INTO graph values(1,3);
----INSERT INTO graph values(1,4);
----INSERT INTO graph values(1,6);
--INSERT INTO graph values(3,5);
--INSERT INTO graph values(4,5);

----INSERT INTO graph values(6,1);
--INSERT INTO graph values(5,0);


----removal of a articulation node
--DROP TABLE graph CASCADE;
--CREATE TABLE graph (s INTEGER, t INTEGER);

--INSERT INTO graph values(0,1);
--INSERT INTO graph values(0,2);
--INSERT INTO graph values(2,1);
--INSERT INTO graph values(1,3);
--INSERT INTO graph values(1,4);
--INSERT INTO graph values(1,6);
----INSERT INTO graph values(3,5);
----INSERT INTO graph values(4,5);

--INSERT INTO graph values(6,1);
----INSERT INTO graph values(5,0);


----removal of two non-articulation node
--DROP TABLE graph CASCADE;
--CREATE TABLE graph (s INTEGER, t INTEGER);

--INSERT INTO graph values(0,1);
----INSERT INTO graph values(0,2);
----INSERT INTO graph values(2,1);
--INSERT INTO graph values(1,3);
----INSERT INTO graph values(1,4);
--INSERT INTO graph values(1,6);
--INSERT INTO graph values(3,5);
----INSERT INTO graph values(4,5);

--INSERT INTO graph values(6,1);
--INSERT INTO graph values(5,0);


--We can check it a node is an articulation node by looking at the relationship between the transitive_closure size
--of the new graph when we remove the node


--Problem 1



--This function assumes your nodes start at 0 and do not skip indices
Create or replace function articulation_points()
returns table (node integer) as
$$
DECLARE N Integer;
DECLARE Connected Integer; --the number of rows tc should return if it is a connected graph
Begin
	drop table if exists outputT;
	create table outputT (node integer);
	select power(count(distinct s)-1,2) into Connected  from graph;
	select count(distinct s)-1 into N  from graph;
	create table temp (s integer, t integer);
	insert into temp (select * from graph);


	while(N>-1)
		loop
			begin
				--loop code
				--perform a transitive closure on a table minus the edges containing node n
				drop table graph cascade;
				create table graph (s integer, t integer);
				insert into graph (select * from temp where s <> N and t <> N);
				--compare the count of tc to the value of connected
				if (select count(*) from transitive_closure()) <> Connected then
					insert into outputT values(N);
				end if;
				N := N-1; --decrement n
			end;
		end loop;
	drop table graph cascade;
	create table graph(s integer, t integer);
	insert into graph (select * from temp);

	drop table temp;
		return query(select * from outputT);
End;
$$ LANGUAGE PLPGSQL;


select * from articulation_points();




--Problem 2
CREATE TABLE Parts_SubParts (pid integer, sid integer, quantity integer);
CREATE TABLE Parts (pid integer, weight integer);


CREATE TABLE WeightTemp(id integer, q integer);

Create or replace function weight(part INTEGER)
returns INTEGER as
$$
DECLARE N INTEGER;
DECLARE total INTEGER;
DECLARE subPart INTEGER;
DECLARE quantity INTEGER;
Begin
	--Base case
	if (select not exists(select 1 from Parts_SubParts where pid = part)) then
		return (select p.weight from parts p where p.pid = part);
	end if; 


	
	insert into WeightTemp (select p.sid,p.quantity from Parts_SubParts p where p.pid = part);
	select count(*) into N from Parts_SubParts where pid = part;

	--Recursive step
	total := 0;
	while(N>0)
		loop
			begin
				--loop code
				select id into subPart from WeightTemp limit 1;
				select q into quantity from WeightTemp limit 1;
				delete from WeightTemp where id = subPart and q = quantity;
				total := total + (quantity * (select * from weight(subPart)));
				N := N-1; --decrement n
				
			end;
		end loop;
	return total;
End;
$$ LANGUAGE PLPGSQL;

select * from weight(1);

select * from weight(2);
select * from weight(3);
select * from weight(4);
select * from weight(5);





--Problem 3
create table Parent_Child (pid integer, sid integer);

insert into Parent_Child values (1,2);
insert into Parent_Child values (1,4);
insert into Parent_Child values (1,5);
insert into Parent_Child values (1,6);
insert into Parent_Child values (3,2);
insert into Parent_Child values (3,4);
insert into Parent_Child values (3,5);
insert into Parent_Child values (3,6);
insert into Parent_Child values (2,7);
insert into Parent_Child values (2,8);
insert into Parent_Child values (12,9);
insert into Parent_Child values (12,10);
insert into Parent_Child values (10,11);







Create or replace function generation()
returns table (id1 Integer, id2 Integer) as
$$
Begin
	drop table if exists gens;
	create table gens (person integer, gen integer);
	DECLARE r record;

	--First step (determine who is first generation)
	begin
	FOR r IN select distinct pid from Parent_Child
		loop
			if (select not exists(select 1 from Parent_Child where sid = r.pid)) then
				insert into gens values (r.pid, 1);
			end if;
		end loop;
	end;

	DECLARE a record;
	--Second step (label who isn't first generation)
	begin
	FOR a IN (select distinct sid from Parent_Child)
		loop
			insert into gens values (a.sid, 0);
		end loop;
	end;

	--Walk the generation trees starting at the first gens	
	DECLARE c record;
	begin
	while(exists(select 1 from gens where gen = 0))
		loop
			begin
				FOR c IN (select person from gens where gen = 0)
					loop
						if( (select gen from gens where person = (select pid from Parent_Child where sid = c.person limit 1)) <> 0) then
						update gens set gen = (select gen from gens where person = (select pid from Parent_Child where sid = c.person limit 1)) + 1 where person = c.person;
						end if;
					end loop;
			end;
		end loop;
	end;

	return query(select g1.person, g2.person from gens g1, gens g2 where g1.gen = g2.gen and g1.person <> g2.person);
End;
$$ LANGUAGE PLPGSQL;

select * from generation();
select * from gens;





--Problem 4
create table DGraph (n integer, m integer, w integer);
insert into DGraph values(0,1,2);
insert into DGraph values(0,4,10);
insert into DGraph values(1,2,3);
insert into DGraph values(1,4,7);
insert into DGraph values(2,3,4);
insert into DGraph values(3,4,5);
insert into DGraph values(4,2,6);


Create or replace function dijkstra(Source integer)
returns table (Target Integer, Distance Integer) as
$$
DECLARE SourceNode integer; 
DECLARE CurrentDistance integer;
DECLARE N integer;

Begin
	drop table if exists nodeList;
	CREATE TABLE nodeList (TargetNode integer NOT NULL, Distance integer NULL, Predecessor integer NULL, Done integer NOT NULL);



	INSERT INTO nodeList (TargetNode, Distance, Predecessor, Done) (select nodes.*, 2147483647, NULL, 0 FROM ((select DGraph.n from DGraph) union (select DGraph.m from DGraph)) nodes);

    UPDATE nodeList SET Distance = 0 WHERE TargetNode = Source;

    
    select 0 into N;
    -- Run the algorithm until we decide that we are finished
    while(1=1)
    loop
        
        
            -- Reset the variable, so we can detect getting no records in the next step.
            select null into SourceNode;

            -- Select the Target and current distance for a node not done, with the lowest distance.
            Select nodeList.TargetNode,  nodeList.Distance into SourceNode, CurrentDistance  
            FROM nodeList WHERE Done = 0 AND nodeList.distance < 2147483647
            ORDER BY nodeList.distance limit 1;
            
            -- Stop if we have no more unvisited, reachable nodes.
            
            if (SourceNode IS NULL) then
                exit;
            end if;
            -- We are now done with this node.
            UPDATE nodeList SET Done = 1 WHERE TargetNode = SourceNode;
            -- Update the distances to all neighbour nodes of this one 
            -- Only update the distance if the new distance lower.


            DECLARE r record;
            begin
            FOR r in (select * from DGraph where DGraph.n = SourceNode)
                loop
                    UPDATE nodeList SET distance = CurrentDistance + (select w from DGraph where r.m = DGraph.m and SourceNode = DGraph.n) 
                        WHERE nodeList.TargetNode = r.m and CurrentDistance + (select w from DGraph where r.m = DGraph.m and SourceNode = DGraph.n) < (select nodeList.distance from nodeList where nodeList.TargetNode = r.m);
                end loop;
            end;


    end loop;
    
    --update nodelist n set distance = null where n.distance = 2147483647; --this is if you prefer null over infinity

	return query(select TargetNode, nodeList.distance from nodeList order by TargetNode);
End;
$$ LANGUAGE PLPGSQL;



select * from dijkstra(0);


