package com.stepashka.jsptask.servlet.storehouse;

import com.stepashka.jsptask.service.StorehouseService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "StorehouseDeleteServlet", value = "/storehouse-delete-servlet")
public class StorehouseDeleteServlet extends HttpServlet {
    private final StorehouseService storehouseService = new StorehouseService();
    private static final Logger logger = Logger.getLogger(StorehouseDeleteServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer storehouseId = Integer.valueOf(request.getParameter("storehouseId"));
            storehouseService.delete(storehouseId);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/storehouse-read-all-servlet");
            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException | NumberFormatException exception){
            logger.error(exception.getMessage());
        }
    }
}
