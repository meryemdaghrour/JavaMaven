package com.epf.rentmanager.ui.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/users/details")
public class UserServletDetails extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        ClientService clientService=ClientService.getInstance();
        ReservationService reservationService=ReservationService.getInstance();
        try {
            request.setAttribute("client",
                    clientService.findById(Long.parseLong(request.getParameter("id"))
                    ));
            request.setAttribute("reservations",
                    reservationService.findByClientId(Long.parseLong(request.getParameter("id"))));


        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().
                getRequestDispatcher("/WEB-INF/views/users/details.jsp").
                forward(request, response);

    }

}
