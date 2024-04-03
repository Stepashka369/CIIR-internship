package com.stepashka.jsptask.servlet.storehouse;

import com.stepashka.jsptask.entity.Storehouse;
import com.stepashka.jsptask.service.StorehouseService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StorehouseReadAllServlet", value = "/storehouse-read-all-servlet")
public class StorehouseReadAllServlet extends HttpServlet {
    private final StorehouseService storehouseService = new StorehouseService();
    private static final Logger logger = Logger.getLogger(StorehouseReadAllServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Storehouse> storehouses = storehouseService.findAll();
            request.setAttribute("storehouseList", storehouses);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/storehouse/storehouseReadAll.jsp");
            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException exception) {
            logger.error(exception.getMessage());
        }
    }
}
