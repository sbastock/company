package com.stocksba.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.stocksba.company.model.Company;
import com.stocksba.company.model.Companys;

@Mapper
public interface CompanyMapper {
	
	@Select("SELECT a.id,a.name,a.turnover,a.ceo,a.code, a.stockcode, b.name as sectorname,a.ipodate,a.description FROM sba_company.company a ,sba_company.sector b where  a.sectorid = b.id")
	List<Companys> getCompanys();
	
	@Select("SELECT name FROM sba_company.company where code =#{code}")
	String getCompanyId(@Param("code") String code);
	
	@Insert("insert into sba_company.company(name,turnover,ceo,code,sectorid,description,stockcode,ipodate) values(#{name},#{turnover},#{ceo},#{code}, #{sectorid},#{description},#{stockcode},date_add(#{ipodate},interval 1 day))")
	@SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
	void addCompany(Company company);
	
	@Update("update sba_company.company set name=#{name},turnover=#{turnover},ceo=#{ceo},code=${code}, sectorid=#{sectorid},description=#{description},ipodate=#{ipodate} where id = #{id}")
	void updateCompany(Company company);

}
