package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

public class ReservationDao {

	private static ReservationDao instance = null;

	Connection connection=null;
	PreparedStatement pstatement=null;
	Statement statement=null;
	ResultSet rs = null;
	private ReservationDao() {}
	public static ReservationDao getInstance() {
		if(instance == null) {
			instance = new ReservationDao();
		}
		return instance;
	}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
		
	public long create(Reservation reservation) throws DaoException {

		try {
			connection = ConnectionManager.getConnection();
			pstatement = connection.prepareStatement(CREATE_RESERVATION_QUERY,Statement.RETURN_GENERATED_KEYS);
			pstatement.setLong(1, reservation.getClient().getIdentifier());
			pstatement.setLong(2, reservation.getVehicle().getIdentifier());
			pstatement.setDate(3, java.sql.Date.valueOf(reservation.getDebut()));
			pstatement.setDate(4, java.sql.Date.valueOf(reservation.getFin()));
			pstatement.executeUpdate();

			rs = pstatement.getGeneratedKeys();

			if (rs.next()) {
				reservation.setIdentifier(rs.getInt(1));
				System.out.println("reservation added with succes " );
			}
			else {
				System.out.println("Error while creating the reservation " );
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return reservation.getIdentifier();
	}
	
	public long delete(Reservation reservation) throws DaoException {
		try {
			connection = ConnectionManager.getConnection();
			pstatement = connection.prepareStatement(DELETE_RESERVATION_QUERY);
			pstatement.setLong(1, reservation.getIdentifier());
			pstatement.executeUpdate();
			System.out.println("reservation deleted");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return reservation.getIdentifier();
	}

	
	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		List<Reservation> myList=null;
		try {
			connection = ConnectionManager.getConnection();
			pstatement = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			pstatement.setLong(1, clientId);
			rs = pstatement.executeQuery();
			System.out.println("Test");

			while (rs.next()) {
				int id = rs.getInt("id");
				Client c = new Client(clientId);
				Vehicle v = new Vehicle(rs.getInt("vehicle_id"));
				LocalDate dateDebut = rs.getDate("debut").toLocalDate();
				LocalDate dateFin = rs.getDate("fin").toLocalDate();
				myList.add(new Reservation(id, c, v, dateDebut, dateFin));
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return myList;
	}
	
	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		List<Reservation> myList=null;
		try {
			connection = ConnectionManager.getConnection();
			pstatement = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			pstatement.setLong(1, vehicleId);
			rs = pstatement.executeQuery();
			System.out.println("Test");
			while (rs.next()) {
				int id = rs.getInt("id");
				Vehicle v = new Vehicle(vehicleId);
				Client c= new Client(rs.getInt("client_id"));
				LocalDate dateDebut = rs.getDate("debut").toLocalDate();
				LocalDate dateFin = rs.getDate("fin").toLocalDate();
				myList.add(new Reservation(id, c, v, dateDebut, dateFin));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return myList;
	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> myList=null;

		try {
			connection = ConnectionManager.getConnection();
			statement=connection.createStatement();
			rs=statement.executeQuery(FIND_RESERVATIONS_QUERY);
			while (rs.next())
			{
				int id=rs.getInt("id");
				Client c= new Client(rs.getInt("client_id"));
				Vehicle v= new Vehicle(rs.getInt("vehicle_id"));
				LocalDate dateDebut=rs.getDate("debut").toLocalDate();
				LocalDate dateFin=rs.getDate("fin").toLocalDate();
				myList.add(new Reservation(id,c,v,dateDebut,dateFin));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return myList;
	}


}
