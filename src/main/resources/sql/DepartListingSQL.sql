use dght001;
select dp.* , 
	count(rc.id) as total_record, sum(case when rc.type = 1 then 1 else 0 end) as good_record, sum(case when rc.type = 0 then 1 else 0 end) as bad_record,  
    count(distinct st.id) as total_staff,
	(select sum(case when (select sum(case when rc2.type = 0 then 1 else -1 end) from record rc2 where rc2.staff_id = st2.id group by rc2.staff_id) >= 0 then 1 else 0 end )from staffs st2 where st2.departs_id = dp.id ) as good_staffs,
    (select sum(case when (select sum(case when rc2.type = 0 then 1 else -1 end) from record rc2 where rc2.staff_id = st2.id group by rc2.staff_id) < 0 then 1 else 0 end )from staffs st2 where st2.departs_id = dp.id ) as bad_staffs,
    sum(case when rc.type = 1 then 1 else -1 end) as rating
from departs dp left join staffs st on st.departs_id = dp.id left join record rc on rc.staff_id = st.id
group by dp.id having dp.name like '%%'
order by dp.id asc limit 0,25


