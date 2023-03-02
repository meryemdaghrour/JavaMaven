package com.epf.rentmanager.main;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

public class Main {

    public static void main(String[] args) {


   /*   try{

            List<Client> clients= ClientService.getInstance().findAll();
            for (Client c: clients
                 ) {
                System.out.println(c.toString());
            }
            int nb=ClientService.getInstance().getNumber();
          System.out.println(nb);
        }
        catch (ServiceException e)
        {
            e.printStackTrace();
        }

        System.out.println("salut");
      /* try{

          Client client= ClientService.getInstance().findById(4);
            System.out.println(client.toString());
            long i=ClientService.getInstance().delete(client);
            System.out.println(i);
        }
        catch (ServiceException e)
        {
            e.printStackTrace();
        }*/

      /* try{
         Client c=new Client("haytem","mannoubi","mer.da@espeit.tn",
                 LocalDate.of(1995,10,9));
         long i=ClientService.getInstance().create(c);

           List<Client> clients= ClientService.getInstance().findAll();
           for (Client cc: clients
           ) {
               System.out.println(cc.toString());
           }

           long ii=ClientService.getInstance().update(c,1);



        }
        catch (ServiceException e)
        {
            e.printStackTrace();
        }*/

        System.out.println("liste des voitures");
        try{

           // long i=VehicleService.getInstance().delete(VehicleService.getInstance().findById(8));

            List<Vehicle> vehicules= VehicleService.getInstance().findAll();
            for (Vehicle v: vehicules
            ) {
                System.out.println(v.toString());
            }
            int nb=VehicleService.getInstance().getNumber();
            System.out.println(nb);

            Vehicle vv=new Vehicle(5,"bm",5);
            long ii=VehicleService.getInstance().create(vv);
            System.out.println(ii);
            List<Vehicle> vehiculess= VehicleService.getInstance().findAll();
            for (Vehicle v: vehiculess
            ) {
                System.out.println(v.toString());
            }
            int nbs=VehicleService.getInstance().getNumber();
            System.out.println(nbs);
            long up=VehicleService.getInstance().update(vv,1);
            System.out.println(up);
            List<Vehicle> vehiculesss= VehicleService.getInstance().findAll();
            for (Vehicle v: vehiculesss
            ) {
                System.out.println(v.toString());
            }


          /*  Vehicle vv= VehicleService.getInstance().findById(4);
            System.out.println(vv.toString());
            long i=VehicleService.getInstance().delete(vv);
            System.out.println(i);*/
        }
        catch (ServiceException e)
        {
            e.printStackTrace();
        }





    }
}
