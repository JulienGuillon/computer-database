package com.excilys.computerdatabase.dao;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 */
public class ConstanteQuery {
	public static final String SELECT_COMPUTERS = "select computer.id, computer.name, introduced, discontinued, company_id, company.name cname from computer left join company on company.id = computer.company_id;";
	
	public static final String SELECT_COMPUTER_BY_ID = "select computer.id, computer.name, introduced, discontinued, company_id, company.name cname from computer left join company on company.id = computer.company_id where computer.id= ?;";

	public static final String PAGINATION_COMPUTERS = "select computer.id, computer.name, introduced, discontinued, company_id, company.name cname from computer left join company on company.id = computer.company_id limit ? offset ?;";

	public static final String DELETE_COMPUTER_BY_ID = "delete from computer where id = ? ";

	public static final String UPDATE_COMPUTER_BY_ID = "update computer set name = ?, introduced = ?, discontinued = ?, company_id = ? where id = ?";
	
	public static final String INSERT_COMPUTER = "insert into computer(name, introduced, discontinued, company_id) values (?, ?, ?, ?)";

	public static final String SELECT_COMPANIES = "select * from company;";
	
	public static final String SELECT_COMPANY_BY_ID = "select * from company where id= ?;";

	public static final String PAGINATION_COMPANIES = "select * from company limit ? offset ?;";
	
	public static final String INSERT_COMPANY = "insert into company(name) values( ? )";
	
	public static final int PAGE = 10;
}