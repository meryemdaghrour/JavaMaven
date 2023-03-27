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

@WebServlet("/rents/edit")
public class RentServletEdit  extends HttpServlet {
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
            throws ServletException, IOException {
        try {
            Optional<Reservation> reservation=reservationService.findById(Long.parseLong(request.getParameter("id")));
            Optional<Client> client=clientService.findById(reservation.get().getClient().getIdentifier());
            Optional< Vehicle> vehicle=vehicleService.findById(reservation.get().getVehicle().getIdentifier());

            request.setAttribute("c", client.get());
            request.setAttribute("reservation",reservation.get());
            request.setAttribute("v",vehicle.get());

            request.setAttribute("clients", clientService.findAll());
            request.setAttribute("rents",vehicleService.findAll());

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        this.getServletContext().
                getRequestDispatcher("/WEB-INF/views/rents/edit.jsp").
                forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Optional<Client> c=clientService.findById(Long.parseLong(request.getParameter("client")));
           Optional< Vehicle> vehicle=vehicleService.findById(Long.parseLong(request.getParameter("rent")));

            Reservation reservation= new Reservation
                    (c.get(),vehicle.get(),
                            LocalDate.parse( request.getParameter("begin")),
                            LocalDate.parse( request.getParameter("end"))
                    );
            reservationService.update(reservation, Long.parseLong(request.getParameter("id")));
            response.sendRedirect("/rentmanager/rents");

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    }
