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

import com.stocksba.company.mapper.ExchangeMapper;
import com.stocksba.company.model.Exchange;
import com.stocksba.company.model.ExchangeCompany;
import com.stocksba.company.rspmodel.RspModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/exchange")
@Api(description = "Stock SBA Exchange Interface")
public class ExchangeApi {
	
	@Autowired
	private ExchangeMapper exchangemapper;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "SBA Exchange List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 403, message = "No Permission"),
			@ApiResponse(code = 404, message = "No Mentors Found"),
			@ApiResponse(code = 500, message = "Internal Error") })
	public ResponseEntity<RspModel> findExchanges() {

		try {

			List<Exchange> exchanges = exchangemapper.getExchanges();

			if (exchanges.size() > 0) {

				RspModel rsp = new RspModel();
				rsp.setCode(200);
				rsp.setMessage("Found Exchanges");
				rsp.setData(exchanges);
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
	@ApiOperation(value = "SBA Company Name Query")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 403, message = "No Permission"),
			@ApiResponse(code = 404, message = "No Mentors Found"),
			@ApiResponse(code = 500, message = "Internal Error") })
	public ResponseEntity<RspModel> findCompanyCode(@ApiParam(name = "exchangeid", required = true) @RequestParam String exchangeid) {

		try {

			List<ExchangeCompany> companys = exchangemapper.getExchangeId(exchangeid);

			if (companys.size() > 0 ) {

				RspModel rsp = new RspModel();
				rsp.setCode(200);
				rsp.setMessage("Found Company");
				rsp.setData(companys);
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
	@ApiOperation(value = "Stock SBA Exchage Add")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 403, message = "No Permission"),
			@ApiResponse(code = 500, message = "Internal Error") })
	public ResponseEntity<RspModel> addExchange(@ApiParam(name = "body", required = true) @RequestBody Exchange exchange) {

		try {

			exchangemapper.addExchange(exchange);

			RspModel rsp = new RspModel();
			rsp.setCode(200);
			rsp.setMessage("Exchange Created");
			return new ResponseEntity<RspModel>(rsp, HttpStatus.CREATED);

		} catch (Exception ex) {
			RspModel rsp = new RspModel();
			rsp.setCode(500);
			rsp.setMessage(ex.getMessage());
			return new ResponseEntity<RspModel>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	
	@RequestMapping(value = "/update/{exchangeid}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Stock SBA Update Exchange")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 403, message = "No Permission"),
			@ApiResponse(code = 404, message = "No Mentors Found"),
			@ApiResponse(code = 500, message = "Internal Error") })
	public ResponseEntity<RspModel> updateCourse(
			@ApiParam(name = "exchangeid", required = true) @PathVariable("exchangeid") Integer id, 
			@ApiParam(name = "body", required = true) @RequestBody Exchange exchange) {

		try {

			exchangemapper.updateExchange(exchange);

			RspModel rsp = new RspModel();
			rsp.setCode(200);
			rsp.setMessage("Updated Exchange");
			return new ResponseEntity<RspModel>(rsp, HttpStatus.OK);

		} catch (Exception ex) {
			RspModel rsp = new RspModel();
			rsp.setCode(500);
			rsp.setMessage(ex.getMessage());
			return new ResponseEntity<RspModel>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
	
