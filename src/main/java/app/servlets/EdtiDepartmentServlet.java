package app.servlets;

import app.domain.Department;
import app.service.ClinicService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EdtiDepartmentServlet extends HttpServlet {

    ClinicService clinicService = new ClinicService();
    String id = "";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //String id = req.getParameter("id");
        String name = req.getParameter("name");
        clinicService.updateDepartment(new Department(Long.parseLong(this.id), name));
        req.setAttribute("departmentName", name);
        doGet(req, resp);
//        HttpSession session = req.getSession();
//        Long id = (Long)session.getAttribute("id");
//        String name = (String)session.getAttribute("name");
//        clinicService.updateDepartment(new Department(id, name));
//        //resp.sendRedirect("/edit");
//        doGet(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException {
        String id = req.getParameter("id");
        this.id = id;
        Department department = clinicService.getDepartment(Integer.parseInt(id));
        req.setAttribute("department", department);
        req.getRequestDispatcher("views/edit.jsp").forward(req, resp);
        //        int id = Integer.parseInt(req.getParameter("id"));
//        Department department = clinicService.getDepartment(1);
//        HttpSession session = req.getSession();
//        req.setAttribute("id", department.getId());
//        req.setAttribute("name", department.getName());
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/edit.jsp");
//        requestDispatcher.forward(req, resp);
    }
}
