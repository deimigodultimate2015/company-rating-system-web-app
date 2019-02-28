package com.example.demo.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.controller.message.SearchData;
import com.example.demo.controller.responseMessage.RecordListingResponse;
import com.example.demo.model.dao.RecordsDao;
import com.example.demo.model.dao.impl.utils.SearchDataChecker;
import com.example.demo.model.entities.custom.PageMKII;
import com.example.demo.model.entities.custom.RecordMKII;

@Repository
public class RecordsDaoImpl implements RecordsDao{
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	SearchDataChecker searchDataChecker;
	
	@Override
	public RecordListingResponse searchRecordsByStaffId(SearchData searchData, boolean onlyStaffId) {
		
		String[] sortKeyWords = {"id","type","reason","date","saff_id"};
		SearchData _searchData = searchDataChecker.SearchDataCheckerSortingFix(searchData, sortKeyWords, "id");
		PageMKII pageMKII = new PageMKII();
		
		pageMKII.setCurrentPage(_searchData.getPage());
		pageMKII.setPageSize(_searchData.getSize());
		
		int page = (_searchData.getPage())*_searchData.getSize();
		
		String FIND_RECORDS_BY_ALL_COLUMN = "select rc.* from record rc where rc.date like :search or rc.reason like :search or rc.staff_id like :search order by rc."+_searchData.getSort()+" limit "+page+","+_searchData.getSize();
		String FIND_RECORDS_BY_STAFFID = "select rc.* from record rc where staff_id = :search order by rc.id asc"+" limit "+page+","+_searchData.getSize();
		String FIND_TOTAL_RECORDS = "select count(rc.id) from record rc where rc.date like :search or rc.reason like :search or rc.staff_id like :search";
		String FIND_TOTAL_RECORDS_BY_STAFFID = "select count(rc.id) from record rc where staff_id = :search";
		if(onlyStaffId) {
			List<RecordMKII> list = namedParameterJdbcTemplate.query(FIND_RECORDS_BY_STAFFID, new MapSqlParameterSource("search", _searchData.getSearch()), new RecordsMapper());
			if(list == null || list.isEmpty()) {
				list = Collections.emptyList();
			}
			pageMKII.setTotalEntity(namedParameterJdbcTemplate.queryForObject(FIND_TOTAL_RECORDS_BY_STAFFID, new MapSqlParameterSource("search", _searchData.getSearch()), Integer.class));
			return new RecordListingResponse(list, pageMKII);
		} else {
			List<RecordMKII> list = namedParameterJdbcTemplate.query(FIND_RECORDS_BY_ALL_COLUMN, new MapSqlParameterSource("search", "%"+_searchData.getSearch()+"%"), new RecordsMapper());
			if(list == null || list.isEmpty()) {
				list = Collections.emptyList();
			}
			pageMKII.setTotalEntity(namedParameterJdbcTemplate.queryForObject(FIND_TOTAL_RECORDS, new MapSqlParameterSource("search", "%"+_searchData.getSearch()+"%"), Integer.class));
			return new RecordListingResponse(list, pageMKII);
		}
	}
	
}

class RecordsMapper implements RowMapper<RecordMKII> {

	@Override
	public RecordMKII mapRow(ResultSet rs, int rowNum) throws SQLException {
		RecordMKII record = new RecordMKII();
		record.setId(rs.getLong(1));
		record.setDate(rs.getDate(2));
		record.setReason(rs.getNString(3));
		record.setType(rs.getBoolean(4));
		record.setStaffId(rs.getLong(5));
		return record;
	}
	
}
