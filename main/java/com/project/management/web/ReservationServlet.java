package com.project.management.web;

import com.project.management.dao.ReservationDao;
import com.project.management.dao.UserDao;
import com.project.management.dao.VehicleReservationDao;
import com.project.management.model.Reservation;
import com.project.management.model.User;
import com.project.management.model.Vehicle;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ReservationServlet", value = "/ReservationServlet")
public class ReservationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //HttpSession session = request.getSession();
        //String idSess = session.getAttribute("sessionId");
        String action = request.getParameter("action");
        if(action == null)
            action =  "list";

        switch (action){
            case "create":
                createReservationForm(request,response);
                break;
            case "update":
                updateReservationForm(request,response);

            default:
                listReservation(request,response);
        }
    }

    private void updateReservationForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Vehicle> vehicle = VehicleReservationDao.getAllVehicle();
        request.setAttribute("VEHICLE_LIST", vehicle);
        ReservationDao reservationDao = new ReservationDao();
        int id = Integer.parseInt(request.getParameter("reservId"));
        Reservation reservation = reservationDao.getReservation(id);
        Date date = new Date();

        Calendar today = Calendar.getInstance();
        Calendar start = Calendar.getInstance();
        today.setTime(date);
        start.setTime(reservation.getStartDate());
        today.add(Calendar.DATE,2);

        if (today.getTime().compareTo(start.getTime())>=0) {
            HttpSession session = request.getSession(false);
            session.setAttribute("Error","error");
            response.sendRedirect("ReservationServlet");
        } else{
            request.setAttribute("reservation",reservation);
            RequestDispatcher dispatcher = request.getRequestDispatcher("customer/update-reservation-form.jsp");
            dispatcher.forward(request,response);
        }



    }

    private void createReservationForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Vehicle> vehicle = VehicleReservationDao.getAllVehicle();

        request.setAttribute("VEHICLE_LIST", vehicle);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/create-reservation-form.jsp");
        dispatcher.forward(request,response);
    }

    private void listReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int idSess = (int) session.getAttribute("sessionId");
        List<Reservation> reserv = ReservationDao.getAllReservation(idSess);

        request.setAttribute("RESERVATION_LIST", reserv);

        if (session.getAttribute("Error")!=null){
            request.setAttribute("error","Sorry you can't update this Reservation");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/homepage-customer.jsp");
        dispatcher.include(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        //System.out.println(action);
        //System.out.println("AAAAAAAAAAA");

        switch (action){
            case "update":
                updateReservation(request,response);
                break;
            case "create":
                createReservation(request,response);
                break;
            case "delete":
                //deleteUser(request,response);

        }
    }

    private void updateReservation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ReservationDao reservationDao = new ReservationDao();
        VehicleReservationDao vehicleDao = new VehicleReservationDao();
        Reservation reservation = reservationDao.getReservation(Integer.parseInt(request.getParameter("reservationId")));

        if (request.getParameter("starDate")!=""){
            try {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("startDate"));
                reservation.setStartDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("endDate")!=""){
            try {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("startDate"));
                reservation.setEndDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(request.getParameter("vehicleId")!=""){
            Vehicle vehicle = vehicleDao.getVehicle(Integer.parseInt(request.getParameter("vehicleId")));
            reservation.setVehicle(vehicle);
        }

        reservationDao.updateReservation(reservation);
        response.sendRedirect("ReservationServlet");
    }

    private void createReservation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDao userDao = new UserDao();
        VehicleReservationDao vehicleDao = new VehicleReservationDao();
        ReservationDao reservationDao = new ReservationDao();
        HttpSession session = request.getSession(false);
        int idSess = (int) session.getAttribute("sessionId");
        Reservation reservation = new Reservation();


        //set StartDate with Cast to java.util.Date format
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("startDate"));
            reservation.setStartDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //set EndDate with Cast to java.util.Date format
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("endDate"));
            reservation.setEndDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User user = userDao.getUser(idSess);
        reservation.setUser(user);

        Vehicle vehicle = vehicleDao.getVehicle(Integer.parseInt(request.getParameter("vehicleId")));
        reservation.setVehicle(vehicle);

        reservationDao.saveReservation(reservation);
        response.sendRedirect("ReservationServlet");
    }


    /*private void createUpdateReservation(HttpServletRequest request, HttpServletResponse response, String action) throws IOException {
        UserDao userDao = new UserDao();
        VehicleReservationDao vehicleDao = new VehicleReservationDao();
        ReservationDao reservationDao = new ReservationDao();
        HttpSession session = request.getSession(false);
        int idSess = (int) session.getAttribute("sessionId");
        Reservation reservation = null;



        //if is an update insert also id so it will update and not create
        if (action.equals("update")) {
            System.out.println("--------------UPDATE---------------");
            reservation = reservationDao.getReservation(Integer.parseInt(request.getParameter("reservationId")));
            System.out.println(reservation.getId());
        }

        if(request.getParameter("startDate")!=""){
            //set StartDate with Cast to java.util.Date format
            try {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("startDate"));
                reservation.setStartDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(request.getParameter("endDate")!=""){
            //set EndDate with Cast to java.util.Date format
            try {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("endDate"));
                reservation.setEndDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        User user = userDao.getUser(idSess);
        reservation.setUser(user);

        if(request.getParameter("vehicleId")!=""){
            Vehicle vehicle = vehicleDao.getVehicle(Integer.parseInt(request.getParameter("vehicleId")));
            reservation.setVehicle(vehicle);
        }



        if(request.getParameter("reservationId")!="")
            reservation.setId(Integer.parseInt(request.getParameter("reservationId")));
        System.out.println(reservation.getId());
        reservationDao.saveReservation(reservation);
        response.sendRedirect("ReservationServlet");



    }*/
}
