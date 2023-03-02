package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;

import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;


import java.util.List;

public class ReservationService {

    private ReservationDao reservationDao;
    public static ReservationService instance;

    private ReservationService()
    {
        this.reservationDao = ReservationDao.getInstance();
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }


    public long create(Reservation reservation) throws ServiceException {
        try    {
            return reservationDao.getInstance().create(reservation);

        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }
    public List<Reservation> findListe(List<Reservation> list)  throws ServiceException
    {
        try{
            for (Reservation r: list)
            {
                Client c= ClientDao.getInstance().findById(r.getClient().getIdentifier());
                Vehicle v= VehicleDao.getInstance().findById(r.getVehicle().getIdentifier());
                r.setClient(c);
                r.setVehicle(v);
            }

            return list;
        }
        catch (DaoException e)
        {
            e.printStackTrace();
            throw new ServiceException();
        }

    }



    public List<Reservation> findAll() throws ServiceException {

        try    {
            List<Reservation> myList= reservationDao.getInstance().findAll();
            myList=findListe(myList);
            return myList;

        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }

    }

    public Long delete(Reservation reservation) throws ServiceException {

        try    {
            return reservationDao.getInstance().delete(reservation);

        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }

    }

    public List<Reservation> findByClientId(long id) throws ServiceException {

        try    {
            List<Reservation> myList= reservationDao.getInstance().findResaByClientId(id);
            myList=findListe(myList);
            return myList;

        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }

    }


    public List<Reservation> findByVehiculeId(long id) throws ServiceException {

        try    {
            List<Reservation> myList= reservationDao.getInstance().findResaByVehicleId(id);
            myList=findListe(myList);
            return myList;

        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }

    }
}
