/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.dao.impl;

/**
 *
 * @author kooc
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.dao.AnnualScheduleDao;
/**
 * AnnualSchedule Data Access Object (DAO). This class contains all database
 * handling that is needed to permanently store and retrieve AnnualSchedule object
 * instances.
 */
public class AnnualScheduleDAOImpl implements AnnualScheduleDao {
    
    Connection connection;

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.AnnualScheduleDao#createValueObject()
	 */
	@Override
	public AnnualSchedule createValueObject() {
		return new AnnualSchedule();
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.AnnualScheduleDao#getObject(java.lang.String)
	 */
	@Override
	public AnnualSchedule getObject(Integer year) throws NotFoundException,
			SQLException {

		AnnualSchedule valueObject = createValueObject();
		valueObject.setYear(year);
		load(valueObject);
		return valueObject;
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.AnnualScheduleDao#load(sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule)
	 */
	@Override
	public void load(AnnualSchedule valueObject) throws NotFoundException,
			SQLException {

		if (valueObject.getYear()== null) {
			// System.out.println("Can not select without Primary-Key!");
			throw new NotFoundException("Can not select without Primary-Key!");
		}

		String sql = "SELECT * FROM `annual-schedule` WHERE (`name` = ? ); ";
		PreparedStatement stmt = null;
		openConnection();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, valueObject.getYear());

			singleQuery(stmt, valueObject);

		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.AnnualScheduleDao#loadAll()
	 */
	@Override
	public List<AnnualSchedule> loadAll() throws SQLException {
		openConnection();
		String sql = "SELECT * FROM `annual-schedule` ORDER BY `name` ASC; ";
		List<AnnualSchedule> searchResults = listQuery(connection
				.prepareStatement(sql));
		closeConnection();
		System.out.println("record size"+searchResults.size());
		return searchResults;
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.AnnualScheduleDao#create(sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule)
	 */
	@Override
	public synchronized void create(AnnualSchedule valueObject)
			throws SQLException {

		String sql = "";
		PreparedStatement stmt = null;
		openConnection();
		try {
			sql = "INSERT INTO `annual-schedule` (`year`, `assingedBy`) VALUES (?,?); ";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, valueObject.getYear());
			stmt.setString(2, valueObject.getAssignedBy());
			int rowcount = databaseUpdate(stmt);
			if (rowcount != 1) {
				// System.out.println("PrimaryKey Error when updating DB!");
				throw new SQLException("PrimaryKey Error when updating DB!");
			}

		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}

	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.AnnualScheduleDao#save(sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule)
	 */
	@Override
	public void save(AnnualSchedule valueObject) throws NotFoundException,
			SQLException {

		String sql = "UPDATE `annual-schedule` SET `assingedBY` = ? WHERE (`year` = ? ); ";
		PreparedStatement stmt = null;
		openConnection();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, valueObject.getAssignedBy());

			stmt.setInt(2, valueObject.getYear());

			int rowcount = databaseUpdate(stmt);
			if (rowcount == 0) {
				// System.out.println("Object could not be saved! (PrimaryKey not found)");
				throw new NotFoundException(
						"Object could not be saved! (PrimaryKey not found)");
			}
			if (rowcount > 1) {
				// System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
				throw new SQLException(
						"PrimaryKey Error when updating DB! (Many objects were affected!)");
			}
		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.AnnualScheduleDao#delete(sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule)
	 */
	@Override
	public void delete(AnnualSchedule valueObject) throws NotFoundException,
			SQLException {

		if (valueObject.getYear() == null) {
			// System.out.println("Can not delete without Primary-Key!");
			throw new NotFoundException("Can not delete without Primary-Key!");
		}

		String sql = "DELETE FROM `annual-schedule` WHERE (`year` = ? ); ";
		PreparedStatement stmt = null;
		openConnection();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, valueObject.getYear());

			int rowcount = databaseUpdate(stmt);
			if (rowcount == 0) {
				// System.out.println("Object could not be deleted (PrimaryKey not found)");
				throw new NotFoundException(
						"Object could not be deleted! (PrimaryKey not found)");
			}
			if (rowcount > 1) {
				// System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
				throw new SQLException(
						"PrimaryKey Error when updating DB! (Many objects were deleted!)");
			}
		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.AnnualScheduleDao#deleteAll(java.sql.Connection)
	 */
	@Override
	public void deleteAll(Connection conn) throws SQLException {

		String sql = "DELETE FROM `radio-program`";
		PreparedStatement stmt = null;
		openConnection();
		try {
			stmt = connection.prepareStatement(sql);
			int rowcount = databaseUpdate(stmt);
			System.out.println(""+rowcount);
		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
			
		}
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.AnnualScheduleDao#countAll()
	 */
	@Override
	public int countAll() throws SQLException {

		String sql = "SELECT count(*) FROM `annual-schedule`";
		PreparedStatement stmt = null;
		ResultSet result = null;
		int allRows = 0;
		openConnection();
		try {
			stmt = connection.prepareStatement(sql);
			result = stmt.executeQuery();

			if (result.next())
				allRows = result.getInt(1);
		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
			closeConnection();
		}
		return allRows;
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.AnnualScheduleDao#searchMatching(sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule)
	 */
	@Override
	public List<AnnualSchedule> searchMatching(AnnualSchedule valueObject) throws SQLException {

                @SuppressWarnings("UnusedAssignment")
		List<AnnualSchedule> searchResults = new ArrayList<>();
		openConnection();
		boolean first = true;
		StringBuilder sql = new StringBuilder(
				"SELECT * FROM `annual-schedule` WHERE 1=1 ");

		if (valueObject.getYear() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND `year` LIKE '").append(valueObject.getYear())
					.append("%' ");
		}

		if (valueObject.getAssignedBy()!= null) {
			if (first) {
				first = false;
			}
			sql.append("AND `assignedBy` LIKE '").append(valueObject.getAssignedBy())
					.append("%' ");
		}

		sql.append("ORDER BY `year` ASC ");

		// Prevent accidential full table results.
		// Use loadAll if all rows must be returned.
		if (first)
			searchResults = new ArrayList<>();
		else
			searchResults = listQuery(connection.prepareStatement(sql
					.toString()));
		closeConnection();
		return searchResults;
	}

	/**
	 * databaseUpdate-method. This method is a helper method for internal use.
	 * It will execute all database handling that will change the information in
	 * tables. SELECT queries will not be executed here however. The return
	 * value indicates how many rows were affected. This method will also make
	 * sure that if cache is used, it will reset when data changes.
	 * 
	 * @param stmt
	 *            This parameter contains the SQL statement to be executed.
     * @return 
     * @throws java.sql.SQLException
	 */
	protected int databaseUpdate(PreparedStatement stmt) throws SQLException {

		int result = stmt.executeUpdate();

		return result;
	}

	/**
	 * databaseQuery-method. This method is a helper method for internal use. It
	 * will execute all database queries that will return only one row. The
	 * resultset will be converted to valueObject. If no rows were found,
	 * NotFoundException will be thrown.
	 * 
	 * @param stmt
	 *            This parameter contains the SQL statement to be executed.
	 * @param valueObject
	 *            Class-instance where resulting data will be stored.
     * @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException
     * @throws java.sql.SQLException
	 */
	protected void singleQuery(PreparedStatement stmt, AnnualSchedule valueObject)
			throws NotFoundException, SQLException {

		ResultSet result = null;
		openConnection();
		try {
			result = stmt.executeQuery();

			if (result.next()) {

				valueObject.setYear(result.getInt("year"));
				valueObject.setAssignedBy(result.getString("assingedBy"));

			} else {
				// System.out.println("AnnualSchedule Object Not Found!");
				throw new NotFoundException("AnnualSchedule Object Not Found!");
			}
		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
			closeConnection();
		}
	}

	/**
	 * databaseQuery-method. This method is a helper method for internal use. It
	 * will execute all database queries that will return multiple rows. The
	 * resultset will be converted to the List of valueObjects. If no rows were
	 * found, an empty List will be returned.
	 * 
	 * @param stmt
	 *            This parameter contains the SQL statement to be executed.
     * @return 
     * @throws java.sql.SQLException
	 */
	protected List<AnnualSchedule> listQuery(PreparedStatement stmt) throws SQLException {

		ArrayList<AnnualSchedule> searchResults = new ArrayList<>();
		ResultSet result = null;
		openConnection();
		try {
			result = stmt.executeQuery();

			while (result.next()) {
				AnnualSchedule temp = createValueObject();

				temp.setYear(result.getInt("year"));
				temp.setAssignedBy(result.getString("assingedBy"));

				searchResults.add(temp);
			}

		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
			closeConnection();
		}

		return (List<AnnualSchedule>) searchResults;
	}

	private void openConnection() {
		try {
			Class.forName(DBConstants.COM_MYSQL_JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			this.connection = DriverManager.getConnection(DBConstants.dbUrl,
					DBConstants.dbUserName, DBConstants.dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
