package com.epf.rentmanager.main;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import com.epf.rentmanager.configuration.AppConfiguration;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) throws ServiceException {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ClientService clientService = context.getBean(ClientService.class);
        VehicleService vehicleService = context.getBean(VehicleService.class);
        ReservationService reservationService = context.getBean(ReservationService.class);

    /* try{

            List<Client> clients= clientService.findAll();
            for (Client c: clients
                 ) {
                System.out.println(c.toString());
            }
            int nb=clientService.getNumber();
          System.out.println(nb);
        }
        catch (ServiceException e)
        {
            e.printStackTrace();
        }

        System.out.println("salut");
      try{

          Client client= clientService.findById(1);
            System.out.println(client.toString());
            long i=clientService.delete(client);
            System.out.println(i);
        }
        catch (ServiceException e)
        {
            e.printStackTrace();
        }

       try{
         Client c=new Client("haytem","mannoubi","mer.da@espeit.tn",
                 LocalDate.of(1995,10,9));
         long i=clientService.create(c);

           List<Client> clients= clientService.findAll();
           for (Client cc: clients
           ) {
               System.out.println(cc.toString());
           }

           long ii=clientService.update(c,69);



        }
        catch (ServiceException e)
        {
            e.printStackTrace();
        }

        System.out.println("liste des voitures");
        try{

           // long i=VehicleService.getInstance().delete(VehicleService.getInstance().findById(8));

            List<Vehicle> vehicules= vehicleService.findAll();
            for (Vehicle v: vehicules
            ) {
                System.out.println(v.toString());
            }
            int nb=vehicleService.getNumber();
            System.out.println(nb);

            Vehicle vv=new Vehicle(5,"bm","hh",5);
            long ii=vehicleService.create(vv);
            System.out.println(ii);
            List<Vehicle> vehiculess= vehicleService.findAll();
            for (Vehicle v: vehiculess
            ) {
                System.out.println(v.toString());
            }
            int nbs=vehicleService.getNumber();
            System.out.println(nbs);
            long up=vehicleService.update(vv,1);
            System.out.println(up);
            List<Vehicle> vehiculesss= vehicleService.findAll();
            for (Vehicle v: vehiculesss
            ) {
                System.out.println(v.toString());
            }


            Vehicle vvv= vehicleService.findById(4);
            System.out.println(vv.toString());
            long i=vehicleService.delete(vvv);
            System.out.println(i);
        }
        catch (ServiceException e)
        {
            e.printStackTrace();
        }

    }*/
        Vehicle vv=new Vehicle(5,"bm","hh",5);
        vehicleService.create(vv)
        ;
        Client c=new Client(2,"haytem","mannoubi","mer.da@espeit.tn",
                LocalDate.of(1995,10,9));
        clientService.create(c);
        Reservation reservation=new Reservation(c,vv,
                LocalDate.of(2000,10,9),
                LocalDate.of(2000,10,19));
        Reservation reservation1=new Reservation(c,vv,
                LocalDate.of(2000,10,9),
                LocalDate.of(2000,10,19));
        reservationService.create(reservation);
        reservationService.create(reservation1);
        List<Reservation> reservations= reservationService.findAll();
        for (Reservation v: reservations
        ) {
            System.out.println(v.toString());
        }


    }
}