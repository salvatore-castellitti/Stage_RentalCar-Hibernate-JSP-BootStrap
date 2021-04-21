package com.project.management.dao;

import com.project.management.model.Reservation;
import com.project.management.model.User;
import com.project.management.model.Vehicle;
import com.project.management.utl.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


//CRUD Vehicle
public class ReservationDao {

    //add Vehicle in the DB
    public static void saveReservation(Reservation reservation) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the REservation object
            session.save(reservation);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    //update Vehicle NB the blank field == "" (NOT null)
    public void updateReservation(Reservation reservation) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the Vehicle object
            session.update(reservation);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //delete Vehicle by id NB need to Integer.parseInt()
    public void deleteReservation(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a vehicle object
            Vehicle vehicle = session.get(Vehicle.class, id);
            if (vehicle != null) {
                session.delete(vehicle);
                System.out.println("user is deleted");
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //return Vehile passing his id NB need to Integer.parseInt()
    public Reservation getReservation(int id) {

        Transaction transaction = null;
        Reservation reservation = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an VEHICLE object
            reservation = session.get(Reservation.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return reservation;
    }

    //return all Vehicles
    @SuppressWarnings("unchecked")
    public static List <Reservation> getAllReservation(int id) {

        //System.out.println(id);
        UserDao userDao = new UserDao();
        User user = userDao.getUser(id);
        Transaction transaction = null;
        List < Reservation > listOfReservation = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            //get a list of Vehicle
            listOfReservation = session.createQuery(" from Reservation R WHERE R.user = :user ").setParameter("user",user).getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfReservation;
    }

}