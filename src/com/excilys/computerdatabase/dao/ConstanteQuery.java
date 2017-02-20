package com.excilys.computerdatabase.dao;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 */
public class ConstanteQuery {
	public static final String SELECT_COMPUTERS = "select * from computer;";
	
	public static final String SELECT_COMPUTER_BY_ID = "select * from computer where id= ?;";

	public static final String DELETE_COMPUTER_BY_ID = "delete from computer where id = ? ";

	public static final String SELECT_COMPANIES = "select * from company;";
	
	public static final String SELECT_COMPANY_BY_ID = "select * from company where id= ?;";

	public static final String INSERT_COMPANY = "insert into company(name) values( ? )";
}
