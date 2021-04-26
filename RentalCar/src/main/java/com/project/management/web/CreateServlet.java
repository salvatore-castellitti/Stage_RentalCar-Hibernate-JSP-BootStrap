package com.project.management.web;

import com.project.management.dao.UserDao;
import com.project.management.model.User;
import com.project.management.utl.HibernateUtil;

import javax.jws.soap.SOAPBinding;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "CreateServlet", value = "/CreateServlet")
public class CreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Enumeration<String> parameterNames = request.getParameterNames();
        User user = new User();
        /* while(parameterNames.hasMoreElements()){
            String paramName = parameterNames.nextElement();
            System.out.println(paramName+ "  ");
            String[] paramValue = request.getParameterValues(paramName);
            for (int i =0;i<paramValue.length; i++){
                System.out.println(paramValue[i] + " paramValue  ");
            }
        }*/
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        String str = request.getParameter("role");

        //casting String birthday to Date format
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("birthday"));
            user.setBirthday(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Casting String role(true,false) to Boolean
        boolean bool = Boolean.parseBoolean(str);
        user.setRole(bool);
        user.setEmail(request.getParameter("email"));
        user.setTaxCode(request.getParameter("taxcode"));

        //updloading user
        UserDao.saveUser(user);

        /*List<User> usr = UserDao.getAllUser();
        for (User u : usr) {
            System.out.println(u.getName());
        }*/



    }
}
