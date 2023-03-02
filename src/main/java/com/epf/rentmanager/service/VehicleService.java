package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.VehicleDao;

public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;
	
	private VehicleService() {
		this.vehicleDao = VehicleDao.getInstance();
	}
	
	public static VehicleService getInstance() {
		if (instance == null) {
			instance = new VehicleService();
		}
		
		return instance;
	}
	
	
	public long create(Vehicle vehicle) throws ServiceException {
		try    {
			return vehicleDao.getInstance().create(vehicle);

		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
		
	}
	public long update(Vehicle vehicle,long id) throws ServiceException {
		try    {
			return vehicleDao.getInstance().update(vehicle,id);

		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}

	}

	public Vehicle findById(long id) throws ServiceException {
		try    {
			return vehicleDao.getInstance().findById(id);

		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
		
	}

	public List<Vehicle> findAll() throws ServiceException {

		try    {
			return vehicleDao.getInstance().findAll();

		}catch (DaoException e)
		{
			e.printStackTrace();
			throw new ServiceException();
		}

	}
	public Long delete(Vehicle vehicle) throws ServiceException {

		try    {
			return vehicleDao.getInstance().delete(vehicle);

		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}

	}

	public int getNumber() throws ServiceException {

		try    {
			return vehicleDao.getInstance().getNumber();

		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}

	}
	
}
