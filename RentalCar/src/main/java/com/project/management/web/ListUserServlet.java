package com.project.management.web;

import com.project.management.dao.UserDao;
import com.project.management.model.User;
import com.project.management.utl.HibernateUtil;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListUserServlet", value = "/ListUserServlet")
public class ListUserServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listUser(request,response);
    }


    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> usr = UserDao.getAllUser();
        /*for (User u : usr){
            System.out.println(u.getName());
        }
        System.out.println(usr);*/

        request.setAttribute("USER_LIST", usr);
        System.out.println(usr);

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/homepage-admin.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
