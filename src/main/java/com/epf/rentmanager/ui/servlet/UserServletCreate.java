package com.epf.rentmanager.ui.servlet;


import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
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

@WebServlet("/users/create")
public class UserServletCreate extends HttpServlet {
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

    this.getServletContext().
            getRequestDispatcher("/WEB-INF/views/users/create.jsp").
            forward(request, response);
}
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {




        try {
            Client c= new Client
                    (request.getParameter("last_name"),
                    request.getParameter("first_name"),
                    request.getParameter("email"),
                    LocalDate.parse(request.getParameter("dateBirth"))
                     );
            clientService.create(c);
            response.sendRedirect("/rentmanager/users");

        } catch (ServiceException e) {
            throw new RuntimeException(e);



    }
}}
