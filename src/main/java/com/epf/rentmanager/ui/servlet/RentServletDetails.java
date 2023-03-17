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
import java.util.Optional;


@WebServlet("/rents/details")
public class RentServletDetails extends HttpServlet {

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

        try {

            Reservation reservation=reservationService.findById(Long.parseLong(request.getParameter("id")));
            Optional<Client> client=clientService.findById(reservation.getClient().getIdentifier());
            Vehicle vehicle=vehicleService.findById(reservation.getVehicle().getIdentifier());

            request.setAttribute("client", client);
            request.setAttribute("reservation",reservation);
            request.setAttribute("vehicle",vehicle);


        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().
                getRequestDispatcher("/WEB-INF/views/rents/details.jsp").
                forward(request, response);

    }

}
