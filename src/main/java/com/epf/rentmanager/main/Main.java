package com.epf.rentmanager.main;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

public class Main {

    public static void main(String[] args) {


      try{

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

       try{
         Client c=new Client("haytem","mannoubi","mer.da@espeit.tn",
                 LocalDate.of(1995,10,9));
         long i=ClientService.getInstance().create(c);

        }
        catch (ServiceException e)
        {
            e.printStackTrace();
        }




    }
}
