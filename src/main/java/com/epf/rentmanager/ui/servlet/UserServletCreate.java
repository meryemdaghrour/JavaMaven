package com.epf.rentmanager.ui.servlet;


import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/users/create")
public class UserServletCreate extends HttpServlet {

public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
{

    this.getServletContext().
            getRequestDispatcher("/WEB-INF/views/users/create.jsp").
            forward(request, response);
}
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        ClientService clientService=ClientService.getInstance();

        try {
            Client c= new Client
                    (request.getParameter("last_name"),
                    request.getParameter("first_name"),
                    request.getParameter("email"),
                    LocalDate.parse(request.getParameter("dateBirth"))
                     );
            clientService.create(c);
            doGet(request,response);

        } catch (ServiceException e) {
            throw new RuntimeException(e);



    }
}}
