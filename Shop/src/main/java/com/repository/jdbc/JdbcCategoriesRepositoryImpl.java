package com.repository.jdbc;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import com.repository.CategoriesRepository;

public class JdbcCategoriesRepositoryImpl implements CategoriesRepository {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
	}

	@SuppressWarnings("unchecked")
	public List<String> populateDropDownList(String type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		List<String> genres = this.namedParameterJdbcTemplate.query(
				"SELECT DISTINCT genre  FROM cds where type=:type", params,
				new ParameterizedRowMapper<String>() {
					public String mapRow(java.sql.ResultSet rs, int rowNum)
							throws SQLException {
						return rs.getString("genre");
					}
				});
		return genres;
	}

}