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
import java.util.Optional;

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




    public Optional<Long> create(Reservation reservation) throws ServiceException {
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
               Optional< Client> c= clientDao.findById(r.getClient().getIdentifier());
                Optional<Vehicle> v= vehicleDao.findById(r.getVehicle().getIdentifier());
                r.getClient().setName(c.get().getName());
                r.getClient().setLastName(c.get().getLastName());
                r.getClient().setEmailAdress(c.get().getEmailAdress());
                r.getClient().setDateOfbirth(c.get().getDateOfbirth());
                r.getVehicle().setConstructor(v.get().getConstructor());
                r.getVehicle().setModel(v.get().getModel());
                r.getVehicle().setNbPlaces(v.get().getNbPlaces());
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

    public Optional<Long> delete(Reservation reservation) throws ServiceException {

        try    {
            return reservationDao.delete(reservation);

        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }

    }
    public Optional<Reservation> findById(long id) throws ServiceException {
        try    {
            return reservationDao.findById(id);

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





    public Optional<Long> update(Reservation reservation,long id) throws ServiceException {
        try    {
            return reservationDao.update(reservation, id);

        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public int getNumber(long id) throws ServiceException {

        try    {
            return reservationDao.getNumberResByClient(id);

        }catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }

    }

    }

