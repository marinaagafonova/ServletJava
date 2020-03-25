package app.servlets;

import app.domain.Department;
import app.service.ClinicService;

import javax.rmi.CORBA.Util;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DeleteDepartmentServlet extends HttpServlet {
    ClinicService clinicService = new ClinicService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException{
        String id = req.getParameter("id");
        clinicService.removeDepartmentByID(Long.parseLong(id));
        resp.sendRedirect("/");
    }
 }
