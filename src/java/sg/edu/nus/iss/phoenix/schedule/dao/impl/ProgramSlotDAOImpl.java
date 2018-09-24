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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.schedule.dao.WeeklyScheduleDao;
/**
 * WeeklySchedule Data Access Object (DAO). This class contains all database
 * handling that is needed to permanently store and retrieve WeeklySchedule object
 * instances.
 */
public class WeeklyScheduleDAOImpl implements WeeklyScheduleDao {
    
    Connection connection;

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.WeeklyScheduleDao#createValueObject()
	 */
	@Override
	public WeeklySchedule createValueObject() {
		return new WeeklySchedule();
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.WeeklyScheduleDao#getObject(java.lang.String)
	 */
	@Override
	public WeeklySchedule getObject(Date startDate) throws NotFoundException,
			SQLException {

		WeeklySchedule valueObject = createValueObject();
		valueObject.setStartDate(startDate);
		load(valueObject);
		return valueObject;
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.WeeklyScheduleDao#load(sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule)
	 */
	@Override
	public void load(WeeklySchedule valueObject) throws NotFoundException,
			SQLException {

		if (valueObject.getStartDate()== null) {
			// System.out.println("Can not select without Primary-Key!");
			throw new NotFoundException("Can not select without Primary-Key!");
		}

		String sql = "SELECT * FROM `weekly-schedule` WHERE (`startDate` = ? ); ";
		PreparedStatement stmt = null;
		openConnection();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setDate(1, valueObject.getStartDate());

			singleQuery(stmt, valueObject);

		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.WeeklyScheduleDao#loadAll()
	 */
	@Override
	public List<WeeklySchedule> loadAll() throws SQLException {
		openConnection();
		String sql = "SELECT * FROM `weekly-schedule` ORDER BY `startDate` ASC; ";
		List<WeeklySchedule> searchResults = listQuery(connection
				.prepareStatement(sql));
		closeConnection();
		System.out.println("record size"+searchResults.size());
		return searchResults;
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.WeeklyScheduleDao#create(sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule)
	 */
	@Override
	public synchronized void create(WeeklySchedule valueObject)
			throws SQLException {

		String sql = "";
		PreparedStatement stmt = null;
		openConnection();
		try {
			sql = "INSERT INTO `weekly-schedule` (`startDate`, `assignedBy`) VALUES (?,?); ";
			stmt = connection.prepareStatement(sql);
			stmt.setDate(1, valueObject.getStartDate());
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
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.WeeklyScheduleDao#save(sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule)
	 */
	@Override
	public void save(WeeklySchedule valueObject) throws NotFoundException,
			SQLException {

		String sql = "UPDATE `weekly-schedule` SET `assingedBY` = ? WHERE (`startDate` = ? ); ";
		PreparedStatement stmt = null;
		openConnection();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, valueObject.getAssignedBy());

			stmt.setDate(2, valueObject.getStartDate());

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
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.WeeklyScheduleDao#delete(sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule)
	 */
	@Override
	public void delete(WeeklySchedule valueObject) throws NotFoundException,
			SQLException {

		if (valueObject.getStartDate() == null) {
			// System.out.println("Can not delete without Primary-Key!");
			throw new NotFoundException("Can not delete without Primary-Key!");
		}

		String sql = "DELETE FROM `weekly-schedule` WHERE (`startDate` = ? ); ";
		PreparedStatement stmt = null;
		openConnection();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setDate(1, valueObject.getStartDate());

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
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.WeeklyScheduleDao#deleteAll(java.sql.Connection)
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
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.WeeklyScheduleDao#countAll()
	 */
	@Override
	public int countAll() throws SQLException {

		String sql = "SELECT count(*) FROM `weekly-schedule`";
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
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.WeeklyScheduleDao#searchMatching(sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule)
	 */
	@Override
	public List<WeeklySchedule> searchMatching(WeeklySchedule valueObject) throws SQLException {

                @SuppressWarnings("UnusedAssignment")
		List<WeeklySchedule> searchResults = new ArrayList<>();
		openConnection();
		boolean first = true;
		StringBuilder sql = new StringBuilder(
				"SELECT * FROM `weekly-schedule` WHERE 1=1 ");

		if (valueObject.getStartDate() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND `name` LIKE '").append(valueObject.getStartDate())
					.append("%' ");
		}

		if (valueObject.getAssignedBy()!= null) {
			if (first) {
				first = false;
			}
			sql.append("AND `desc` LIKE '").append(valueObject.getAssignedBy())
					.append("%' ");
		}

		sql.append("ORDER BY `startDate` ASC ");

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
	protected void singleQuery(PreparedStatement stmt, WeeklySchedule valueObject)
			throws NotFoundException, SQLException {

		ResultSet result = null;
		openConnection();
		try {
			result = stmt.executeQuery();

			if (result.next()) {

				valueObject.setStartDate(result.getDate("startDate"));
				valueObject.setAssignedBy(result.getString("assignedBy"));

			} else {
				// System.out.println("WeeklySchedule Object Not Found!");
				throw new NotFoundException("WeeklySchedule Object Not Found!");
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
	protected List<WeeklySchedule> listQuery(PreparedStatement stmt) throws SQLException {

		ArrayList<WeeklySchedule> searchResults = new ArrayList<>();
		ResultSet result = null;
		openConnection();
		try {
			result = stmt.executeQuery();

			while (result.next()) {
				WeeklySchedule temp = createValueObject();

				temp.setStartDate(result.getDate("startDate"));
				temp.setAssignedBy(result.getString("assignedBy"));

				searchResults.add(temp);
			}

		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
			closeConnection();
		}

		return (List<WeeklySchedule>) searchResults;
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
