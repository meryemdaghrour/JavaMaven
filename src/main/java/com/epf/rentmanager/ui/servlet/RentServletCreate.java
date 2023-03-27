package com.epf.rentmanager.ui.servlet;


import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@WebServlet("/rents/create")
public class RentServletCreate extends HttpServlet {
    @Autowired
    VehicleService vehicleService;
    @Autowired
    ClientService clientService;
    @Autowired
    ReservationService reservationService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        try {
            request.setAttribute("clients", clientService.findAll());
            request.setAttribute("rents",vehicleService.findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().
                getRequestDispatcher("/WEB-INF/views/rents/create.jsp").
                forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       try {

          Optional<Client> c=clientService.findById(Long.parseLong(request.getParameter("client")));
           Optional<Vehicle> vehicle=vehicleService.findById(Long.parseLong(request.getParameter("rent")));

           Reservation reservation= new Reservation
                    (c.get(),vehicle.get(),
                            LocalDate.parse( request.getParameter("begin")),
                            LocalDate.parse( request.getParameter("end"))
                    );

            reservationService.create(reservation);
            response.sendRedirect("/rentmanager/rents");

        } catch (ServiceException e) {
            throw new RuntimeException(e);



        }
    }
}
