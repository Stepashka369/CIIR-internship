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

@WebServlet(name = "GoodInsertServlet", value = "/good-insert-servlet")
public class GoodInsertServlet extends HttpServlet {
    private GoodService goodService = new GoodService();
    private ManufacturerService manufacturerService = new ManufacturerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        request.setAttribute("manufacturersList", manufacturers);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/good/goodInsert.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        goodService.save(buildGood(request));
        List<Good> goods = goodService.findAll();
        request.setAttribute("goodsList", goods);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/good/goodReadAll.jsp");
        requestDispatcher.forward(request, response);
    }

    private Good buildGood(HttpServletRequest request){
        Good good = new Good();
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
