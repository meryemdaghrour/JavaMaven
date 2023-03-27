package com.epf.rentmanager.ui.servlet;
import com.epf.rentmanager.exception.ServiceException;
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

@WebServlet("/cars/edit")
public class VehicleServletEdit extends HttpServlet {
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
            request.setAttribute("vehicle",
                    vehicleService.findById(Long.parseLong(request.getParameter("id"))
                    ).get());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        this.getServletContext().
                getRequestDispatcher("/WEB-INF/views/vehicles/edit.jsp").
                forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Vehicle vehicle= new Vehicle
                    (request.getParameter("manufacturer"),
                            request.getParameter("modele"),
                            Integer.parseInt(request.getParameter("seats")));

            vehicleService.update(vehicle,Long.parseLong(request.getParameter("id")));

            response.sendRedirect("/rentmanager/cars");

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}

