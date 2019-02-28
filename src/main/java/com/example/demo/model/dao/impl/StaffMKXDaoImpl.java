package com.example.demo.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.dao.StaffMKXDao;
import com.example.demo.model.entities.Staffs;
import com.example.demo.model.entities.custom.StaffMKX;

@Repository
public class StaffMKXDaoImpl implements StaffMKXDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private final String getTop10 = "select\n" + 
			"	st.*, sum(case when rc.type = 1 then 1 else -1 end) as score,\n" + 
			"    count(rc.id) as total_rating, sum(rc.type) as good_rating, sum(case when rc.type = 0 then 1 else 0 end) as bad_rating\n" + 
			"from\n" + 
			"	staffs st join record rc on rc.staff_id = st.id	\n" + 
			"group by \n" + 
			"	st.id\n" + 
			"having\n" + 
			"	score >= /*Get minium value of top 10 score*/(select temp1320.* from (select (select sum(case when record.type = 1 then 1 else -1 end) from record where record.staff_id = st.id) as score from staffs st group by score order by score desc limit 10) as temp1320 order by temp1320.score asc limit 1)\n" + 
			"order by\n" + 
			"	score desc limit 10\n" + 
			"";
	
	@Override
	public List<StaffMKX> getTop10Staff() {
		// TODO Auto-generated method stub
		List<StaffMKX> list = jdbcTemplate.query(getTop10, new StaffMKXRowMapper());
		if(list == null || list.isEmpty()) {
			return Collections.emptyList();
		} else {
			return list;
		}
	}

	
	
}

class StaffMKXRowMapper implements RowMapper<StaffMKX>{

	@Override
	public StaffMKX mapRow(ResultSet rs, int rowNum) throws SQLException {
		StaffMKX staffMKX = new StaffMKX();
		Staffs staff = new Staffs();
		staff.setId(rs.getLong(1));
		staff.setactive(rs.getBoolean(2));
		staff.setBirthday(rs.getDate(3));
		staff.setEmail(rs.getString(4));
		staff.setGender(rs.getBoolean(5));
		staff.setLevel(rs.getByte(6));
		staff.setName(rs.getNString(7));
		staff.setNotes(rs.getNString(8));
		staff.setPhone(rs.getString(9));
		staff.setSalary(rs.getLong(10));
		staffMKX.setStaff(staff);
		staffMKX.setDepartId(rs.getLong(11));
		staffMKX.setScore(rs.getInt(12));
		staffMKX.setTotalRating(rs.getInt(13));
		staffMKX.setGoodRating(rs.getInt(14));
		staffMKX.setBadRating(rs.getInt(15));
		return staffMKX;
	}
	
}