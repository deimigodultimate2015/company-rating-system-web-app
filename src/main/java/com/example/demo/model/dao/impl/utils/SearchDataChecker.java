package com.example.demo.model.dao.impl.utils;

import org.springframework.stereotype.Component;

import com.example.demo.controller.message.SearchData;

@Component
public class SearchDataChecker {
	
	public SearchData checkSearch(SearchData searchData) {
		if(searchData.getSearch() == null) {
			searchData.setSearch("");
		}
		
		if(searchData.getSort() == null) {
			searchData.setSort("");
		}
		
		if(searchData.getPage() < 0) {
			searchData.setPage(0);
		}
		
		if(searchData.getSize() < 1) {
			searchData.setSize(15);
		}
		
		return searchData;
		
	}
	
	public SearchData SearchDataCheckerSortingFix(SearchData searchData, String[] sortKeyWords, String defaultSortKeyWord ) {
		SearchData _searchData = checkSearch(searchData);
		if(sortKeyWords == null || sortKeyWords.length == 0) {
			_searchData.setSort(defaultSortKeyWord);
			return _searchData;
		}
		
		for(int i = 0; i < sortKeyWords.length; i++) {
			if(_searchData.getSort().equals(sortKeyWords[i])) {
				return _searchData;
			}
		}
		
		_searchData.setSort(defaultSortKeyWord);
		return searchData;
		
	}
	
}
