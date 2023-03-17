package com.epf.rentmanager.service;

import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

	private ClientDao clientDao;
	public static ClientService instance;
	
	private ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
	

	
	
	public long create(Client client) throws ServiceException {
		try    {
			return clientDao.create(client);

		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public long update(Client client,long id) throws ServiceException {
		try    {
			return clientDao.update(client, id);

		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public Optional<Client> findById(long id) throws ServiceException {
		try    {
			return clientDao.findById(id);

		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public List<Client> findAll() throws ServiceException {

		try    {
			return clientDao.findAll();

		}catch (DaoException e){
			e.printStackTrace();
               throw new ServiceException();
		}

	}

	public Long delete(Client client) throws ServiceException {

		try    {
			return clientDao.delete(client);

		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}

	}

	public int getNumber() throws ServiceException {

		try    {
			return clientDao.getNumber();

		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}

	}
	
}
