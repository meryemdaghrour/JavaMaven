package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;

import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ReservationService {

    private ReservationDao reservationDao;
    private ClientDao clientDao;
    private VehicleDao vehicleDao;
    public static ReservationService instance;

    private ReservationService(ReservationDao reservationDao,ClientDao clientDao,VehicleDao vehicleDao)
    {

        this.reservationDao = reservationDao;
        this.clientDao=clientDao;
        this.vehicleDao=vehicleDao;
    }




    public long create(Reservation reservation) throws ServiceException {
        try    {
            return reservationDao.create(reservation);

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
                Client c= clientDao.findById(r.getClient().getIdentifier());
                Vehicle v= vehicleDao.findById(r.getVehicle().getIdentifier());
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
            List<Reservation> myList= reservationDao.findAll();
            myList=findListe(myList);
            return myList;

        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }

    }

    public Long delete(Reservation reservation) throws ServiceException {

        try    {
            return reservationDao.delete(reservation);

        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }

    }

    public List<Reservation> findByClientId(long id) throws ServiceException {

        try    {
            List<Reservation> myList= reservationDao.findResaByClientId(id);
            myList=findListe(myList);
            return myList;

        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }

    }


    public List<Reservation> findByVehiculeId(long id) throws ServiceException {

        try    {
            List<Reservation> myList= reservationDao.findResaByVehicleId(id);
            myList=findListe(myList);
            return myList;

        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }

    }

    public List<Vehicle> listVehiculeReser(long clientId) throws ServiceException
    {
        try {
            List<Vehicle> list= reservationDao.listVehiculeReser(clientId);
            for (Vehicle vv : list) {

                vv = vehicleDao.findById(vv.getIdentifier());


            }
            return list;
        } catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }

        }
    }

