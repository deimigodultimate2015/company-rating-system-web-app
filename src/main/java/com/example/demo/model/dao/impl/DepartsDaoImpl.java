package com.example.demo.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.controller.message.SearchData;
import com.example.demo.controller.responseMessage.DepartListingResponse;
import com.example.demo.model.dao.DepartsDao;
import com.example.demo.model.dao.impl.utils.SearchDataChecker;
import com.example.demo.model.entities.Staffs;
import com.example.demo.model.entities.custom.DepartsDetails;
import com.example.demo.model.entities.custom.PageMKII;

@Repository
public class DepartsDaoImpl implements DepartsDao {

	@Autowired
	NamedParameterJdbcTemplate NamedParameterjdbcTemplate;
	
	@Autowired
	SearchDataChecker searchDataChecker;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public DepartListingResponse searchDepartDetails(SearchData searchData) {
		
		SearchData searchDataVerified = searchDataChecker.checkSearch(searchData); 
		DepartListingResponse dlr = new DepartListingResponse(null,null);
		PageMKII pageMKII = new PageMKII();
		pageMKII.setCurrentPage(searchDataVerified.getPage());
		pageMKII.setPageSize(searchDataVerified.getSize());
		
		if(searchDataVerified.getSort().equalsIgnoreCase("staffs")) {
			searchDataVerified.setSort("total_staff");
		} else if(searchDataVerified.getSort().equalsIgnoreCase("ratings")) {
			searchDataVerified.setSort("total_record");
		} else if(searchDataVerified.getSort().equalsIgnoreCase("score")) {
			searchDataVerified.setSort("rating");
		} else if(searchDataVerified.getSort().equalsIgnoreCase("name")) {
			searchDataVerified.setSort("dp.name");
		}	else {
			searchDataVerified.setSort("dp.name");
		}
		
		List<DepartsDetails> list = new ArrayList<>();
		String sort = searchDataVerified.getSort();
		int page =  (searchDataVerified.getPage()-1)*15;
		if(page < 0) page = 0;
		if(searchDataVerified.getSize() < 5) searchDataVerified.setSize(5);
		
		String SERACH_DEPARTS_DETAILS_TOTAL_ENTITIES = "select count(dp.name) \n" + 
				"from departs dp where dp.name like :search\n";
		
		String SEARCH_DEPARTS_DETAILS_SQL = "select dp.* , \n" + 
				"	count(rc.id) as total_record, sum(case when rc.type = 1 then 1 else 0 end) as good_record, sum(case when rc.type = 0 then 1 else 0 end) as bad_record,  \n" + 
				"    count(distinct st.id) as total_staff,\n" + 
				"	(select sum(case when (select sum(case when rc2.type = 1 then 1 else -1 end) from record rc2 where rc2.staff_id = st2.id group by rc2.staff_id) >= 0 then 1 else 0 end )from staffs st2 where st2.departs_id = dp.id ) as good_staffs,\n" + 
				"    (select sum(case when (select sum(case when rc2.type = 1 then 1 else -1 end) from record rc2 where rc2.staff_id = st2.id group by rc2.staff_id) < 0 then 1 else 0 end )from staffs st2 where st2.departs_id = dp.id ) as bad_staffs,\n" + 
				"    sum(case when rc.type = 1 then 1 else -1 end) as rating\n" + 
				"from departs dp left join staffs st on st.departs_id = dp.id left join record rc on rc.staff_id = st.id\n" + 
				"group by dp.id having dp.name like :search\n" + 
				"order by "+sort+" desc limit "+page+","+searchDataVerified.getSize();
		list = NamedParameterjdbcTemplate.query(SEARCH_DEPARTS_DETAILS_SQL, new MapSqlParameterSource("search", "%"+searchDataVerified.getSearch()+"%"), new DepartsDetailsMapper());
		int totalEntity = NamedParameterjdbcTemplate.queryForObject(SERACH_DEPARTS_DETAILS_TOTAL_ENTITIES,new MapSqlParameterSource("search", "%"+searchDataVerified.getSearch()+"%"), Integer.class);
		pageMKII.setTotalEntity(totalEntity);
		dlr.setDepart(list);
		dlr.setPageinf(pageMKII);
		return dlr;
	}

	@Override
	public List<Staffs> getDepartStaffs(Long id) {
		String GET_DEPART_STAFF = "select st.id, st.name, st.email, st.level, st.active from staffs st where st.departs_id = :departID";
		List<Staffs> list = new ArrayList<>();
		list = NamedParameterjdbcTemplate.query(GET_DEPART_STAFF, new MapSqlParameterSource("departID", id), new StaffMapper());
		return list;
	}

	@Override
	public Optional<DepartsDetails> getDepartDetailsByID(Long departID) {
		
		String SEARCH_DEPARTS_DETAILS_SQL = "select dp.* , \n" + 
				"	count(rc.id) as total_record, sum(case when rc.type = 1 then 1 else 0 end) as good_record, sum(case when rc.type = 0 then 1 else 0 end) as bad_record,  \n" + 
				"    count(distinct st.id) as total_staff,\n" + 
				"	(select sum(case when (select sum(case when rc2.type = 1 then 1 else -1 end) from record rc2 where rc2.staff_id = st2.id group by rc2.staff_id) >= 0 then 1 else 0 end )from staffs st2 where st2.departs_id = dp.id ) as good_staffs,\n" + 
				"    (select sum(case when (select sum(case when rc2.type = 1 then 1 else -1 end) from record rc2 where rc2.staff_id = st2.id group by rc2.staff_id) < 0 then 1 else 0 end )from staffs st2 where st2.departs_id = dp.id ) as bad_staffs,\n" + 
				"    sum(case when rc.type = 1 then 1 else -1 end) as rating\n" + 
				"from departs dp left join staffs st on st.departs_id = dp.id left join record rc on rc.staff_id = st.id\n" + 
				"group by dp.id having dp.id = :search";		
		
		Optional<DepartsDetails> departS =  Optional.of(NamedParameterjdbcTemplate.queryForObject(SEARCH_DEPARTS_DETAILS_SQL, new MapSqlParameterSource("search", departID), new DepartsDetailsMapper()));
		return departS;
	}
	
}


class StaffMapper implements RowMapper<Staffs> {

	@Override
	public Staffs mapRow(ResultSet rs, int rowNum) throws SQLException {

		Staffs staff = new Staffs();
		staff.setId(rs.getLong(1));
		staff.setName(rs.getNString(2));
		staff.setEmail(rs.getString(3));
		staff.setLevel(rs.getByte(4));
		staff.setactive(rs.getBoolean(5));
		
		return staff;
	}
	
}

class DepartsDetailsMapper implements RowMapper<DepartsDetails> {

	@Override
	public DepartsDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		DepartsDetails dd = new DepartsDetails(
					rs.getLong(1),
					rs.getBoolean(2),
					rs.getNString(3),
					rs.getInt(4),
					rs.getInt(5),
					rs.getInt(6),
					rs.getInt(7),
					rs.getInt(8),
					rs.getInt(9),
					rs.getInt(10)
				);
		
		return dd;
	}
	
}