package com.epf.rentmanager.service;

import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.VehicleDao;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;
	
	private VehicleService(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}
	



	
	
	public Optional<Long> create(Vehicle vehicle) throws ServiceException {
		try    {
			return vehicleDao.create(vehicle);

		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
		
	}
	public Optional<Long> update(Vehicle vehicle, long id) throws ServiceException {
		try    {
			return vehicleDao.update(vehicle,id);

		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}

	}

	public Optional<Vehicle> findById(long id) throws ServiceException {
		try    {
			return vehicleDao.findById(id);

		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
		
	}

	public List<Vehicle> findAll() throws ServiceException {

		try    {
			return vehicleDao.findAll();

		}catch (DaoException e)
		{
			e.printStackTrace();
			throw new ServiceException();
		}

	}
	public Optional<Long> delete(Vehicle vehicle) throws ServiceException {

		try    {
			return vehicleDao.delete(vehicle);

		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}

	}

	public int getNumber() throws ServiceException {

		try    {
			return vehicleDao.getNumber();

		}catch (DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}

	}
	
}
