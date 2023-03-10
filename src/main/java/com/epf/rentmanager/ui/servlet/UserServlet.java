package com.epf.rentmanager.ui.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/users")
public class UserServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        ClientService clientService=ClientService.getInstance();

        try {

            request.setAttribute("clients", clientService.findAll());

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

         this.getServletContext().
                getRequestDispatcher("/WEB-INF/views/users/list.jsp").
                forward(request, response);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
      doGet(request,response);
    }






}
