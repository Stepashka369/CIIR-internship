package com.stepashka.jsptask.servlet.good;

import com.stepashka.jsptask.entity.Good;
import com.stepashka.jsptask.entity.Manufacturer;
import com.stepashka.jsptask.service.GoodService;
import com.stepashka.jsptask.service.ManufacturerService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GoodUpdateServlet", value = "/good-update-servlet")
public class GoodUpdateServlet extends HttpServlet {
    private final GoodService goodService = new GoodService();
    private final ManufacturerService manufacturerService = new ManufacturerService();
    private static final Logger logger = Logger.getLogger(GoodUpdateServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer goodId = Integer.valueOf(request.getParameter("goodId"));
            Good good = goodService.findById(goodId);
            request.setAttribute("good", good);
            List<Manufacturer> manufacturers = manufacturerService.findAll();
            request.setAttribute("manufacturersList", manufacturers);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/good/goodUpdate.jsp");
            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException | NumberFormatException exception){
            logger.error(exception.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            Good good = buildGood(request);
            goodService.save(good);
            request.setAttribute("good", good);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/good/goodReadOne.jsp");
            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException | NumberFormatException exception){
            logger.error(exception.getMessage());
        }
    }

    private Good buildGood(HttpServletRequest request) throws NumberFormatException{
        Good good = new Good();
        good.setId(Integer.valueOf(request.getParameter("id")));
        good.setName(request.getParameter("name"));
        good.setModel(request.getParameter("model"));
        good.setGuarantee(Integer.valueOf(request.getParameter("guarantee")));
        good.setPrice(Float.valueOf(request.getParameter("price")));
        good.setDescription(request.getParameter("description"));
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(Integer.valueOf(request.getParameter("manufacturerId")));
        good.setManufacturer(manufacturer);
        return good;
    }
}
