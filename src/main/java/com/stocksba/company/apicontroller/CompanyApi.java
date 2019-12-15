package com.stocksba.company.apicontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stocksba.company.mapper.CompanyMapper;
import com.stocksba.company.model.Company;
import com.stocksba.company.model.Companys;
import com.stocksba.company.rspmodel.RspModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/company")
@Api(description = "Stock SBA Company Interface")
public class CompanyApi {
	
	@Autowired
	private CompanyMapper companymapper;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "SBA Company List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 403, message = "No Permission"),
			@ApiResponse(code = 404, message = "No Mentors Found"),
			@ApiResponse(code = 500, message = "Internal Error") })
	public ResponseEntity<RspModel> findCompanys() {

		try {

			List<Companys> companys = companymapper.getCompanys();

			if (companys.size() > 0) {

				RspModel rsp = new RspModel();
				rsp.setCode(200);
				rsp.setMessage("Found Companys");
				rsp.setData(companys);
				return new ResponseEntity<RspModel>(rsp, HttpStatus.OK);

			} else {
				RspModel rsp = new RspModel();
				rsp.setCode(404);
				rsp.setMessage("No Found Companys");
				return new ResponseEntity<RspModel>(rsp, HttpStatus.OK);
			}

		} catch (Exception ex) {
			RspModel rsp = new RspModel();
			rsp.setCode(500);
			rsp.setMessage(ex.getMessage());
			return new ResponseEntity<RspModel>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	@RequestMapping(value = "/query", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "SBA Company Code Query")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 403, message = "No Permission"),
			@ApiResponse(code = 404, message = "No Mentors Found"),
			@ApiResponse(code = 500, message = "Internal Error") })
	public ResponseEntity<RspModel> findCompanyCode(@ApiParam(name = "code", required = true) @RequestParam String code) {

		try {

			String companyname = companymapper.getCompanyId(code);

			if (companyname != null) {

				RspModel rsp = new RspModel();
				rsp.setCode(200);
				rsp.setMessage("Found Company");
				rsp.setData(companyname);
				return new ResponseEntity<RspModel>(rsp, HttpStatus.OK);

			} else {
				RspModel rsp = new RspModel();
				rsp.setCode(404);
				rsp.setMessage("No Found Company");
				return new ResponseEntity<RspModel>(rsp, HttpStatus.NOT_FOUND);
			}

		} catch (Exception ex) {
			RspModel rsp = new RspModel();
			rsp.setCode(500);
			rsp.setMessage(ex.getMessage());
			return new ResponseEntity<RspModel>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Stock SBA Company Add")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 403, message = "No Permission"),
			@ApiResponse(code = 500, message = "Internal Error") })
	public ResponseEntity<RspModel> addCompany(@ApiParam(name = "body", required = true) @RequestBody Company company) {

		try {

			companymapper.addCompany(company);

			RspModel rsp = new RspModel();
			rsp.setCode(200);
			rsp.setMessage("Company Created");
			return new ResponseEntity<RspModel>(rsp, HttpStatus.CREATED);

		} catch (Exception ex) {
			RspModel rsp = new RspModel();
			rsp.setCode(500);
			rsp.setMessage(ex.getMessage());
			return new ResponseEntity<RspModel>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	
	@RequestMapping(value = "/update/{companyid}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Stock SBA Update Company")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 403, message = "No Permission"),
			@ApiResponse(code = 404, message = "No Mentors Found"),
			@ApiResponse(code = 500, message = "Internal Error") })
	public ResponseEntity<RspModel> updateCourse(
			@ApiParam(name = "companyid", required = true) @PathVariable("companyid") Integer companyid, 
			@ApiParam(name = "body", required = true) @RequestBody Company course) {

		try {

			companymapper.updateCompany(course);

			RspModel rsp = new RspModel();
			rsp.setCode(200);
			rsp.setMessage("Updated Company");
			return new ResponseEntity<RspModel>(rsp, HttpStatus.OK);

		} catch (Exception ex) {
			RspModel rsp = new RspModel();
			rsp.setCode(500);
			rsp.setMessage(ex.getMessage());
			return new ResponseEntity<RspModel>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	

}
