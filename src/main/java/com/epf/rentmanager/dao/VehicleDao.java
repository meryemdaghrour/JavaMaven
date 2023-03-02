package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

public class VehicleDao {
	
	private static VehicleDao instance = null;
	Connection connection=null;
	PreparedStatement pstatement=null;
	Statement statement=null;
	ResultSet rs = null;
	private VehicleDao() {}
	public static VehicleDao getInstance() {
		if(instance == null) {
			instance = new VehicleDao();
		}
		return instance;
	}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";

	private static final String GET_NUMBER = "SELECT COUNT(*) AS total FROM Vehicle;";
	
	public long create(Vehicle vehicle) throws DaoException {
		try {
			connection = ConnectionManager.getConnection();
			pstatement = connection.prepareStatement(CREATE_VEHICLE_QUERY,Statement.RETURN_GENERATED_KEYS);
			pstatement.setString(1, vehicle.getConstructor());
			pstatement.setInt(2, vehicle.getNbPlaces());
			pstatement.executeUpdate();
			//int success = pstatement.executeUpdate();
			rs = pstatement.getGeneratedKeys();
			if (rs.next()) {
				vehicle.setIdentifier(rs.getInt(1));
				System.out.println("vehicle added with succes " );
			}
			/*if (success == 1) {
				System.out.println("Client ajouté avec succès " );
			} */else {
				System.out.println("Error while creating the vehicle " );
			}
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
				int nb_places=rs.getInt("nb_places");
				vehicle= new Vehicle(id,constructeur,nb_places);
			} else {
				System.out.println("no vehicle : " + id);
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return vehicle;
	}

	public List<Vehicle> findAll() throws DaoException {

		List<Vehicle> myList=null;


		try {
			connection = ConnectionManager.getConnection();
			statement=connection.createStatement();
			rs=statement.executeQuery(FIND_VEHICLES_QUERY);
			while (rs.next())
			{
				int id=rs.getInt("id");
				String constructeur=rs.getString("constructeur");
				int nb_places=rs.getInt("nb_places");
				myList.add(new Vehicle(id,constructeur,nb_places));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
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
