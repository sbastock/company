package com.stocksba.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.stocksba.company.model.Sector;



@Mapper
public interface SectorMapper {
	@Select("SELECT * FROM sba_company.sector;")
	List<Sector> getSectors();

}
