package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleDao {
	

	Connection connection=null;
	PreparedStatement pstatement=null;
	Statement statement=null;
	ResultSet rs = null;
	private VehicleDao() {}

	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur,model,nb_places) VALUES(?, ?,?);";

	private static final String UPDATE_VEHICLE_QUERY ="UPDATE Vehicle SET constructeur = ?,model=?, nb_places = ? WHERE id = ?;";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur,model,nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur,model, nb_places FROM Vehicle;";

	private static final String GET_NUMBER = "SELECT COUNT(*) AS total FROM Vehicle;";
	
	public long create(Vehicle vehicle) throws DaoException {
		try {
			connection = ConnectionManager.getConnection();
			pstatement = connection.prepareStatement(CREATE_VEHICLE_QUERY,Statement.RETURN_GENERATED_KEYS);
			pstatement.setString(1, vehicle.getConstructor());
			pstatement.setString(2, vehicle.getModel());
			pstatement.setInt(3, vehicle.getNbPlaces());
			pstatement.executeUpdate();
			rs = pstatement.getGeneratedKeys();
			if (rs.next()) {
				vehicle.setIdentifier(rs.getInt(1));
				System.out.println("vehicle added with succes " );
			}
			else {
				System.out.println("Error while creating the vehicle " );
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return vehicle.getIdentifier();
	}

	public long update(Vehicle vehicle, long id) throws DaoException {

		try {
			connection = ConnectionManager.getConnection();
			pstatement = connection.prepareStatement(UPDATE_VEHICLE_QUERY);
			pstatement.setLong(4, id);
			pstatement.setString(1, vehicle.getConstructor());
			pstatement.setString(2, vehicle.getModel());
			pstatement.setInt(3, vehicle.getNbPlaces());
			pstatement.executeUpdate();
			System.out.println("Vehicle updated with succes " );

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return vehicle.getIdentifier();

	}

	public long delete(Vehicle vehicle) throws DaoException {

		try {
			connection = ConnectionManager.getConnection();
			pstatement = connection.prepareStatement(DELETE_VEHICLE_QUERY);
			pstatement.setLong(1, vehicle.getIdentifier());
			pstatement.executeUpdate();
			System.out.println("vehicle deleted");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return vehicle.getIdentifier();
	}

	public Vehicle findById(long id) throws DaoException {
		Vehicle vehicle=null;
		try {
			connection = ConnectionManager.getConnection();
			pstatement = connection.prepareStatement(FIND_VEHICLE_QUERY);
			pstatement.setLong(1, id);
			rs = pstatement.executeQuery();
			System.out.println("Veficle found ");
			if (rs.next()) {
				String constructeur=rs.getString("constructeur");
				String model=rs.getString("model");
				int nb_places=rs.getInt("nb_places");
				vehicle= new Vehicle(id,constructeur,model,nb_places);
			} else {
				System.out.println("no vehicle : " + id);
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}
		return vehicle;
	}

	public List<Vehicle> findAll() throws DaoException {

		List<Vehicle> myList=new ArrayList<>();

		try {
			connection = ConnectionManager.getConnection();
			statement=connection.createStatement();
			rs=statement.executeQuery(FIND_VEHICLES_QUERY);
			while (rs.next())
			{
				int id=rs.getInt("id");
				String constructeur=rs.getString("constructeur");
				String model=rs.getString("model");
				int nb_places=rs.getInt("nb_places");
				myList.add(new Vehicle(id,constructeur,model,nb_places));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}
		return myList;
		
	}

	public int getNumber() throws DaoException {

		int nb=0;

		try {
			connection = ConnectionManager.getConnection();
			statement=connection.createStatement();
			rs=statement.executeQuery(GET_NUMBER);
			if (rs.next()) {
				nb = rs.getInt("total");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}finally {
			try {
				if (rs != null) rs.close();
				if (statement != null) statement.close();
				if (connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nb;
	}
	

}
