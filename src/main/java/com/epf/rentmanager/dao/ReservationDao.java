package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDao {




	private ReservationDao() {
	}


	private static final String UPDATE_RESERVATION_QUERY = "UPDATE Reservation SET client_id = ?, vehicle_id = ?, debut = ?, fin = ? WHERE id= ?;";

	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String FIND_RESERVATION_QUERY = "SELECT  client_id, vehicle_id, debut, fin FROM Reservation where id=?;";

	private static final String GET_NUMBER_Res_By_Client = "SELECT COUNT(*) AS total FROM Reservation where client_id=? ;";
	public Optional<Long> create(Reservation reservation) throws DaoException {

		Optional<Long> optional = Optional.empty();
		try (Connection connection = ConnectionManager.getConnection();
		PreparedStatement pstatement = connection.prepareStatement(CREATE_RESERVATION_QUERY, Statement.RETURN_GENERATED_KEYS);){

			pstatement.setLong(1, reservation.getClient().getIdentifier());
			pstatement.setLong(2, reservation.getVehicle().getIdentifier());
			pstatement.setDate(3, java.sql.Date.valueOf(reservation.getDebut()));
			pstatement.setDate(4, java.sql.Date.valueOf(reservation.getFin()));
			pstatement.executeUpdate();

			ResultSet rs = pstatement.getGeneratedKeys();

			if (rs.next()) {
				reservation.setIdentifier(rs.getInt(1));
				optional = Optional.of(reservation.getIdentifier());
				System.out.println("reservation added with succes ");
			} else {
				System.out.println("Error while creating the reservation ");
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return optional;
	}

	public Optional<Reservation> findById(long id) throws DaoException {

		Optional<Reservation> optional = Optional.empty();

		try(Connection connection = ConnectionManager.getConnection();
		PreparedStatement pstatement = connection.prepareStatement(FIND_RESERVATION_QUERY);) {

			pstatement.setLong(1, id);
			ResultSet rs = pstatement.executeQuery();
			System.out.println("Reservation found ");

			if (rs.next()) {

				Client c = new Client(rs.getInt("client_id"));
				Vehicle v = new Vehicle(rs.getInt("vehicle_id"));
				LocalDate dateDebut = rs.getDate("debut").toLocalDate();
				LocalDate dateFin = rs.getDate("fin").toLocalDate();
				Reservation reservation = new Reservation(id, c, v, dateDebut, dateFin);
				optional = Optional.of(reservation);

			} else {
				System.out.println("no vehicle : " + id);
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return optional;
	}

	public Optional<Long> delete(Reservation reservation) throws DaoException {

		Optional<Long> optional = Optional.empty();

		try (Connection connection = ConnectionManager.getConnection();
		PreparedStatement pstatement = connection.prepareStatement(DELETE_RESERVATION_QUERY);)
		{

			pstatement.setLong(1, reservation.getIdentifier());
			pstatement.executeUpdate();
			System.out.println("reservation deleted");
			optional = Optional.of(reservation.getIdentifier());

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return optional;
	}


	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		List<Reservation> myList = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection();
	    PreparedStatement	pstatement = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);){
			System.out.println("salut");

			pstatement.setLong(1, clientId);
			ResultSet rs = pstatement.executeQuery();
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
		List<Reservation> myList = new ArrayList<>();
		try(	Connection connection = ConnectionManager.getConnection();
		PreparedStatement pstatement = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);) {

			pstatement.setLong(1, vehicleId);
			ResultSet rs = pstatement.executeQuery();
			System.out.println("Test");
			while (rs.next()) {
				int id = rs.getInt("id");
				Vehicle v = new Vehicle(vehicleId);
				Client c = new Client(rs.getInt("client_id"));
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
		List<Reservation> myList = new ArrayList<>();


		try (Connection connection = ConnectionManager.getConnection();
		Statement statement = connection.createStatement();
		ResultSet  rs = statement.executeQuery(FIND_RESERVATIONS_QUERY);){


			while (rs.next()) {


				Reservation reservation = new Reservation();
				reservation.setIdentifier(rs.getInt("id"));
				reservation.setVehicle(new Vehicle(rs.getInt("vehicle_id")));
				reservation.setClient(new Client(rs.getInt("client_id")));
				reservation.setDebut(rs.getDate("debut").toLocalDate());
				reservation.setFin(rs.getDate("fin").toLocalDate());

				myList.add(reservation);

				System.out.println("salut");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return myList;
	}



	public Optional<Long> update(Reservation reservation, long id) throws DaoException {


		Optional<Long> optional = Optional.empty();

		try (Connection connection = ConnectionManager.getConnection();
		PreparedStatement pstatement = connection.prepareStatement(UPDATE_RESERVATION_QUERY);){

			pstatement.setLong(5, id);
			pstatement.setLong(1, reservation.getClient().getIdentifier());
			pstatement.setLong(2, reservation.getVehicle().getIdentifier());
			pstatement.setDate(3, java.sql.Date.valueOf(reservation.getDebut()));
			pstatement.setDate(4, java.sql.Date.valueOf(reservation.getFin()));
			pstatement.executeUpdate();
			System.out.println("Reservation updated with succes ");
			optional = Optional.of(reservation.getIdentifier());
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return optional;

	}

	public int getNumberResByClient(long  id) throws DaoException {

	int nb=0;
		try (Connection connection = ConnectionManager.getConnection();
		PreparedStatement pstatement = connection.prepareStatement(GET_NUMBER_Res_By_Client);){

			pstatement.setLong(1, id);
			ResultSet rs = pstatement.executeQuery();

			if (rs.next()) {
				 nb = rs.getInt("total");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return nb;

	}



}
