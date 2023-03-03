package com.epf.rentmanager.ui.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import javax.servlet.http.HttpServlet;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/home")

public class HomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ClientService clientService=ClientService.getInstance();
        VehicleService vehicleService=VehicleService.getInstance();

        try {

           int nbClients = clientService.getNumber();
            request.setAttribute("nbClients", nbClients);
            int nbVehicule = vehicleService.getNumber();
            request.setAttribute("nbVehicule",nbVehicule);

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").
                forward(request, response);




    }


    }
