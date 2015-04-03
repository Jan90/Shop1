package com.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import com.model.CD;

public class JdbcCdRowMapper implements ParameterizedRowMapper<CD> {

	public CD mapRow(ResultSet rs, int rowNum) throws SQLException {
		CD cd = new CD();
		cd.setType(rs.getString("type"));
		cd.setGenre(rs.getString("genre"));
		cd.setName(rs.getString("name"));
		return cd;
	}

}
