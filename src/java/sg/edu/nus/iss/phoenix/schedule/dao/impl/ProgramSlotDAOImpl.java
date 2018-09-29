/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.dao.impl;

/**
 *
 * @author aswathyl
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.dao.ProgramSlotDao;
/**
 * ProgramSlotDao Data Access Object (DAO). This class contains all database
 * handling that is needed to permanently store and retrieve ProgramSlot object
 * instances.
 */
public class ProgramSlotDAOImpl implements ProgramSlotDao {
    
    Connection connection;

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.ProgramSlotDao#createValueObject()
	 */
	@Override
	public ProgramSlot createValueObject() {
		return new ProgramSlot();
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.ProgramSlotDao#getObject(java.lang.String)
	 */
	@Override
	public ProgramSlot getObject(Date dateOfProgram) throws NotFoundException,
			SQLException {

		ProgramSlot valueObject = createValueObject();
                valueObject.setDateOfProgram(dateOfProgram);
                
		load(valueObject);
		return valueObject;
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.ProgramSlotDao#load(sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot)
	 */
	@Override
	public void load(ProgramSlot valueObject) throws NotFoundException,
			SQLException {

		if (valueObject.getDateOfProgram()== null ) {
			// System.out.println("Can not select without Primary-Key!");
			throw new NotFoundException("Can not select without Primary-Key!");
		}

		String sql = "SELECT * FROM `program-slot` WHERE (`dateOfProgram` = ? ); ";
		PreparedStatement stmt = null;
		openConnection();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setDate(1, valueObject.getDateOfProgram());
			singleQuery(stmt, valueObject);

		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.ProgramSlotDao#loadAll()
	 */
	@Override
	public List<ProgramSlot> loadAll() throws SQLException {
		openConnection();
		String sql = "SELECT * FROM `program-slot` ORDER BY `dateOfProgram` ASC; ";
		List<ProgramSlot> searchResults = listQuery(connection
				.prepareStatement(sql));
		closeConnection();
		System.out.println("record size"+searchResults.size());
		return searchResults;
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.ProgramSlotDao#create(sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot)
	 */
	@Override
	public synchronized void create(ProgramSlot valueObject)
			throws SQLException {

		String sql = "";
		PreparedStatement stmt = null;
		openConnection();
		try {
			sql = "INSERT INTO `program-slot` (`duration`, `dateOfProgram`,`startTime`,"
                                + "`program-name`,`WeeklyScheduleId`,`Producer`,`Presenter`) VALUES (?,?,?,?,?,?,?); ";
			stmt = connection.prepareStatement(sql);
                        stmt.setTime(1, valueObject.getDuration());
                        stmt.setDate(2, valueObject.getDateOfProgram());
			stmt.setDate(3, valueObject.getStartTime());
			stmt.setString(4, valueObject.getProgramName());
                        stmt.setInt(5, valueObject.getWeeklyScheduleId());
                        stmt.setString(6, valueObject.getProducer());
                        stmt.setString(7, valueObject.getPresenter());
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
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.ProgramSlotDao#save(sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot)
	 */
	@Override
	public void save(ProgramSlot valueObject) throws NotFoundException,
			SQLException {

		String sql = "UPDATE `program-slot` SET `duration` = ?, `startTime` = ?,"
                        + "`Producer` = ?, `Presenter` = ? WHERE (`dateOfProgram` = ? ); ";
		PreparedStatement stmt = null;
		openConnection();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setTime(1, valueObject.getDuration());
                        stmt.setDate(2, valueObject.getStartTime());
			stmt.setString(3, valueObject.getProducer());
                        stmt.setString(4, valueObject.getPresenter());

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
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.ProgramSlotDao#delete(sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot)
	 */
	@Override
	public void delete(ProgramSlot valueObject) throws NotFoundException,
			SQLException {

		if (valueObject.getDateOfProgram() == null) {
			// System.out.println("Can not delete without Primary-Key!");
			throw new NotFoundException("Can not delete without Primary-Key!");
		}

		String sql = "DELETE FROM `program-slot` WHERE (`dateOfProgram` = ? ); ";
		PreparedStatement stmt = null;
		openConnection();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setDate(1, valueObject.getDateOfProgram());

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
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.ProgramSlotDao#deleteAll(java.sql.Connection)
	 */
	@Override
	public void deleteAll(Connection conn) throws SQLException {

		String sql = "DELETE FROM `program-slot`";
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
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.ProgramSlotDao#countAll()
	 */
	@Override
	public int countAll() throws SQLException {

		String sql = "SELECT count(*) FROM `program-slot`";
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
	 * @see sg.edu.nus.iss.phoenix.schedule.dao.impl.ProgramSlotDao#searchMatching(sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot)
	 */
	@Override
	public List<ProgramSlot> searchMatching(ProgramSlot valueObject) throws SQLException {

                @SuppressWarnings("UnusedAssignment")
		List<ProgramSlot> searchResults = new ArrayList<>();
		openConnection();
		boolean first = true;
		StringBuilder sql = new StringBuilder(
				"SELECT * FROM `program-slot` WHERE 1=1 ");

		if (valueObject.getProgramName() != null) {
			if (first) {
				first = false;
			}
			sql.append("AND `program-name` LIKE '").append(valueObject.getProgramName())
					.append("%' ");
		}

		if (valueObject.getDuration()!= null) {
			if (first) {
				first = false;
			}
			sql.append("AND `duration` LIKE '").append(valueObject.getDuration())
					.append("%' ");
		}
                
                if (valueObject.getDateOfProgram()!= null) {
			if (first) {
				first = false;
			}
			sql.append("AND `dateOfProgram` LIKE '").append(valueObject.getDateOfProgram())
					.append("%' ");
		}
                
                if (valueObject.getStartTime()!= null) {
			if (first) {
				first = false;
			}
			sql.append("AND `startTime` LIKE '").append(valueObject.getStartTime())
					.append("%' ");
		}
                
                if (valueObject.getWeeklyScheduleId()!= null) {
			if (first) {
				first = false;
			}
			sql.append("AND `weeklyScheduleId` LIKE '").append(valueObject.getWeeklyScheduleId())
					.append("%' ");
		}
                
                if (valueObject.getPresenter()!= null) {
			if (first) {
				first = false;
			}
			sql.append("AND `presenter` LIKE '").append(valueObject.getPresenter())
					.append("%' ");
		}
                
                if (valueObject.getProducer()!= null) {
			if (first) {
				first = false;
			}
			sql.append("AND `producer` LIKE '").append(valueObject.getProducer())
					.append("%' ");
		}

		sql.append("ORDER BY `dateOfProgram` ASC ");

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
	protected void singleQuery(PreparedStatement stmt, ProgramSlot valueObject)
			throws NotFoundException, SQLException {

		ResultSet result = null;
		openConnection();
		try {
			result = stmt.executeQuery();

			if (result.next()) {
                                valueObject.setDuration(result.getTime("duration"));
                                valueObject.setDateOfProgram(result.getDate("dateOfProgram"));
                                valueObject.setStartTime(result.getDate("startTime"));
                                valueObject.setProgramName(result.getString("program-name"));
                                valueObject.setweeklyScheduleId(result.getInt("weeklyScheduleId"));
                                valueObject.setProducer(result.getString("producer"));
                                valueObject.setPresenter(result.getString("presenter"));


			} else {
				// System.out.println("ProgramSlot Object Not Found!");
				throw new NotFoundException("ProgramSlot Object Not Found!");
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
	protected List<ProgramSlot> listQuery(PreparedStatement stmt) throws SQLException {

		ArrayList<ProgramSlot> searchResults = new ArrayList<>();
		ResultSet result = null;
		openConnection();
		try {
			result = stmt.executeQuery();

			while (result.next()) {
				ProgramSlot temp = createValueObject();

                                temp.setDuration(result.getTime("duration"));
                                temp.setDateOfProgram(result.getDate("dateOfProgram"));
                                temp.setStartTime(result.getDate("startTime"));
                                temp.setProgramName(result.getString("program-name"));
                                temp.setweeklyScheduleId(result.getInt("weeklyScheduleId"));
                                temp.setProducer(result.getString("producer"));
                                temp.setPresenter(result.getString("presenter"));
                                
				searchResults.add(temp);
			}

		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
			closeConnection();
		}

		return (List<ProgramSlot>) searchResults;
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