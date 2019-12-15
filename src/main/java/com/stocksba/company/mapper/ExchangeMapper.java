package com.stocksba.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.stocksba.company.model.Exchange;
import com.stocksba.company.model.ExchangeCompany;

@Mapper
public interface ExchangeMapper {
	
	@Select("SELECT * FROM sba_company.exchange")
	List<Exchange> getExchanges();
	
	@Insert("insert into sba_company.exchange(name,brief,address,remarks) values(#{name},#{brief},#{address},#{remarks})")
	@SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
	void addExchange(Exchange exchange);
	
	@Update("update sba_company.exchange set name=#{name},brief=#{brief},address=#{address},remarks=#{remarks} where id = #{id}")
	void updateExchange(Exchange exchange);
	
	@Select("SELECT id,name,code FROM sba_company.company where exchangeid =#{exchangeid}")
	List<ExchangeCompany> getExchangeId(@Param("exchangeid") String exchangeid);

}
