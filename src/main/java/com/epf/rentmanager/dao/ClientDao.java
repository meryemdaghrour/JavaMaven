package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

public class ClientDao {

	private static ClientDao instance = null;
	Connection connection=null;
	PreparedStatement pstatement=null;
	Statement statement=null;
	ResultSet rs = null;
	private ClientDao()  {}
	public static ClientDao getInstance()  {
		if(instance == null) {
			instance = new ClientDao();
		}
		return instance;
	}


	private static final String UPDATE_CLIENT_QUERY="UPDATE Client SET nom = ?, prenom = ?, email = ?, naissance = ? WHERE id= ?;";
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";

	private static final String GET_NUMBER = "SELECT COUNT(*) AS total FROM Client;";
	
	public long create(Client client) throws DaoException {

		try {
			connection = ConnectionManager.getConnection();
			pstatement = connection.prepareStatement(CREATE_CLIENT_QUERY,Statement.RETURN_GENERATED_KEYS);
			pstatement.setString(1, client.getLastName());
			pstatement.setString(2, client.getName());
			pstatement.setString(3, client.getEmailAdress());
			pstatement.setDate(4, java.sql.Date.valueOf(client.getDateOfbirth()));
			pstatement.executeUpdate();
			//int success = pstatement.executeUpdate();
			rs = pstatement.getGeneratedKeys();
			if (rs.next()) {
				client.setIdentifier(rs.getInt(1));
				System.out.println("Client added with succes " );
			}
			/*if (success == 1) {
				System.out.println("Client ajouté avec succès " );
			} */else {
				System.out.println("Error while creating thecliet " );
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return client.getIdentifier();

	}
	
	public long delete(Client client) throws DaoException {


		try {
			connection = ConnectionManager.getConnection();
			pstatement = connection.prepareStatement(DELETE_CLIENT_QUERY);
			pstatement.setLong(1, client.getIdentifier());
			pstatement.executeUpdate();
			System.out.println("Client deleted");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return client.getIdentifier();
	}

	public Client findById(long id) throws DaoException {

		Client c=null;

		try {
			connection = ConnectionManager.getConnection();
			pstatement = connection.prepareStatement(FIND_CLIENT_QUERY);
			pstatement.setLong(1, id);

			rs = pstatement.executeQuery();
			System.out.println("Client found");
			if (rs.next()) {
				String nom=rs.getString("nom");
				String prenom=rs.getString("prenom");
				String email=rs.getString("email");
				LocalDate date=rs.getDate("naissance").toLocalDate();
				c= new Client(id,nom,prenom,email,date);
			} else {
				System.out.println(" client not found " );
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}
		return c;

	}

	public List<Client> findAll() throws DaoException {

		List<Client> myList=new ArrayList<>();
		try {
			connection = ConnectionManager.getConnection();
			statement=connection.createStatement();
			 rs=statement.executeQuery(FIND_CLIENTS_QUERY);
			while (rs.next())
			{
			  int id=rs.getInt("id");
			  String nom=rs.getString("nom");
			  String prenom=rs.getString("prenom");
			  String email=rs.getString("email");
			  LocalDate date=rs.getDate("naissance").toLocalDate();
			  myList.add(new Client(id,nom,prenom,email,date));
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


	public long update(Client client,long id) throws DaoException {

		try {
			connection = ConnectionManager.getConnection();
			pstatement = connection.prepareStatement(UPDATE_CLIENT_QUERY);
			pstatement.setLong(5, id);
			pstatement.setString(1, client.getLastName());
			pstatement.setString(2, client.getName());
			pstatement.setString(3, client.getEmailAdress());
			pstatement.setDate(4, java.sql.Date.valueOf(client.getDateOfbirth()));
			pstatement.executeUpdate();
			System.out.println("Client updated with succes " );

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return client.getIdentifier();

	}

}
