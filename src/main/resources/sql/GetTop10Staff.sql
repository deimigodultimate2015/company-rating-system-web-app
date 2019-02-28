use dght001;
select
	st.*, sum(case when rc.type = 1 then 1 else -1 end) as score,
    count(rc.id) as total_rating, sum(rc.type) as good_rating, sum(case when rc.type = 0 then 1 else 0 end) as bad_rating
from
	staffs st join record rc on rc.staff_id = st.id	
group by 
	st.id
having
	score >= /*Get minium value of top 10 score*/(select temp1320.* from (select (select sum(case when record.type = 1 then 1 else -1 end) from record where record.staff_id = st.id) as score from staffs st group by score order by score desc limit 10) as temp1320 order by temp1320.score asc limit 1)
order by
	score desc

