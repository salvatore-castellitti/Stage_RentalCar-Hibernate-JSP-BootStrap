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
                try {
                    createReservationForm(request,response);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case "update":
                updateReservationForm(request,response);
                break;
            case "listVehicle":
                listVehicleCustomer(request,response);
                break;
            case "createDate":
                createFormChooseDate(request,response);
                break;


            default:
                listReservation(request,response);
        }
    }

    private void createFormChooseDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/create-reservation-choose-date.jsp");
        dispatcher.forward(request,response);
    }

    private void listVehicleCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Vehicle> cars = VehicleReservationDao.getAllVehicle();

        request.setAttribute("CARS_LIST", cars);


        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/homepage-car-customer.jsp");
        dispatcher.forward(request,response);
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

    private void createReservationForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        String date = request.getParameter("startDate");
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        String date2 = request.getParameter("endDate");
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
        if (startDate.before(new Date())){
            request.setAttribute("Error","Sorry, Sorry you can't choose past Date");
            RequestDispatcher dispatcher = request.getRequestDispatcher("customer/create-reservation-choose-date.jsp");
            dispatcher.forward(request,response);
        }
        if(endDate.before(startDate)){
            request.setAttribute("Error","Sorry, the End Date must be equal or greater then Start Date");
            RequestDispatcher dispatcher = request.getRequestDispatcher("customer/create-reservation-choose-date.jsp");
            dispatcher.forward(request,response);
        }
        List<Vehicle> vehicle = VehicleReservationDao.getFreeVehicle(startDate);
        request.setAttribute("startDate",date);
        request.setAttribute("endDate",date2);

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

        switch (action){
            case "update":
            case "create":
                createUpdateReservation(request,response);
                break;
            case "delete":
                deleteReservation(request,response);

        }
    }

    private void deleteReservation(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
            reservationDao.deleteReservation(id);
            response.sendRedirect("ReservationServlet");
        }
    }

    private void createUpdateReservation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDao userDao = new UserDao();
        VehicleReservationDao vehicleDao = new VehicleReservationDao();
        ReservationDao reservationDao = new ReservationDao();
        HttpSession session = request.getSession(false);
        int idSess = (int) session.getAttribute("sessionId");
        Reservation reservation = null;
        if(request.getParameter("reservationId")!=null){
            reservation = reservationDao.getReservation(Integer.parseInt(request.getParameter("reservationId")));
        }else{
            reservation = new Reservation();
        }


        if(request.getParameter("startDate")!=""){
            //set StartDate with Cast to java.util.Date format
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startDate"));
                reservation.setStartDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(!request.getParameter("endDate").equals("")){
            //set EndDate with Cast to java.util.Date format
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endDate"));
                reservation.setEndDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        User user = userDao.getUser(idSess);
        reservation.setUser(user);

        if(!request.getParameter("vehicleId").equals("")){
            Vehicle vehicle = vehicleDao.getVehicle(Integer.parseInt(request.getParameter("vehicleId")));
            reservation.setVehicle(vehicle);
        }

        String str = request.getParameter("isConfirmed");
        boolean bool = Boolean.parseBoolean(str);
        reservation.setConfirmed(bool);


        if(request.getParameter("reservationId")!=null) {
            reservation.setId(Integer.parseInt(request.getParameter("reservationId")));
            reservationDao.updateReservation(reservation);
        }else{
            reservationDao.saveReservation(reservation);
        }
        response.sendRedirect("ReservationServlet");
    }
}
