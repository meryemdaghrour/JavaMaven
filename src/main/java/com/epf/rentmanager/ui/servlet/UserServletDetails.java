package com.epf.rentmanager.ui.servlet;

import com.epf.rentmanager.exception.ServiceException;
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


@WebServlet("/users/details")
public class UserServletDetails extends HttpServlet {

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
            request.setAttribute("client",
                    clientService.findById(Long.parseLong(request.getParameter("id"))
                    ).get());
            request.setAttribute("reservations",
                    reservationService.findByClientId(Long.parseLong(request.getParameter("id"))));
            request.setAttribute("total",
                    reservationService.getNumber(Long.parseLong(request.getParameter("id"))));


        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().
                getRequestDispatcher("/WEB-INF/views/users/details.jsp").
                forward(request, response);

    }

}
