package com.repository.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.model.*;
import com.repository.*;

import org.h2.jdbc.JdbcResultSet;

import com.model.Store;

@Repository
@SuppressWarnings("unused")
public class JdbcCDRepositoryImpl implements CdRepository {

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

	@SuppressWarnings("deprecation")
	public Collection<CD> getProductList() throws DataAccessException {
		List<CD> cds = new ArrayList<CD>();

		Store.productList.addAll(this.jdbcTemplate.query(
				"SELECT type, genre, name FROM cds",
				ParameterizedBeanPropertyRowMapper.newInstance(CD.class)));
		return Store.productList;
	}

	@SuppressWarnings("deprecation")
	public void getProductList(String name) throws DataAccessException {
		List<CD> cds = new ArrayList<CD>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "%" + name + "%");
		Store.productList.addAll(this.namedParameterJdbcTemplate.query(
				"SELECT type, genre, name FROM cds WHERE name like :name",
				params,
				ParameterizedBeanPropertyRowMapper.newInstance(CD.class)));
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public void getProductList(String type, String genre)
			throws DataAccessException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		params.put("genre", genre);
		List<CD> newItemsList = new ArrayList<CD>();
		Store.productList
				.addAll(this.namedParameterJdbcTemplate
						.query("SELECT type, genre, name FROM cds WHERE  type =:type AND genre=:genre ",
								params, new JdbcCdRowMapper()));

	}

	@SuppressWarnings({ "deprecation" })
	public Collection<CD> getNewItems() throws DataAccessException {
		CD cd;
		List<CD> newItemsList = new ArrayList<CD>();
		List<String> genres = this.jdbcTemplate.query(
				"SELECT DISTINCT genre FROM CDs",
				new ParameterizedRowMapper<String>() {
					public String mapRow(java.sql.ResultSet rs, int rowNum)
							throws SQLException {
						return rs.getString(1);
					}
				});
		for (String genre : genres) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("genre", genre);
			cd = this.namedParameterJdbcTemplate
					.queryForObject(
							"SELECT * FROM (SELECT * FROM CDs "
									+ "ORDER BY CD_id DESC "
									+ ") AS CDdata WHERE CDdata.genre =:genre ORDER BY CD_id DESC LIMIT 1",
							params, new JdbcCdRowMapper());
			Store.addProduct(cd);
		}
		return Store.productList;
	}
}