package com.stocksba.company.apicontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stocksba.company.mapper.SectorMapper;
import com.stocksba.company.model.Sector;
import com.stocksba.company.rspmodel.RspModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/sector")
@Api(description = "Stock SBA Sector Interface")
public class SectorApi {

	@Autowired
	private SectorMapper sectormapper;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "SBA Sector List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "No Authroization"), @ApiResponse(code = 403, message = "No Permission"),
			@ApiResponse(code = 404, message = "No Mentors Found"),
			@ApiResponse(code = 500, message = "Internal Error") })
	public ResponseEntity<RspModel> finSectors() {

		try {

			List<Sector> sectors = sectormapper.getSectors();

			if (sectors.size() > 0) {

				RspModel rsp = new RspModel();
				rsp.setCode(200);
				rsp.setMessage("Found Sectors");
				rsp.setData(sectors);
				return new ResponseEntity<RspModel>(rsp, HttpStatus.OK);

			} else {
				RspModel rsp = new RspModel();
				rsp.setCode(404);
				rsp.setMessage("No Found Sectors");
				return new ResponseEntity<RspModel>(rsp, HttpStatus.OK);
			}

		} catch (Exception ex) {
			RspModel rsp = new RspModel();
			rsp.setCode(500);
			rsp.setMessage(ex.getMessage());
			return new ResponseEntity<RspModel>(rsp, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
