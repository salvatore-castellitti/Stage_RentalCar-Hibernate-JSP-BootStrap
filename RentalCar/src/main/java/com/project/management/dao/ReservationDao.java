package com.project.management.dao;

import com.project.management.model.Reservation;
import com.project.management.model.User;
import com.project.management.model.Vehicle;
import com.project.management.utl.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


//CRUD Reservation
public class ReservationDao {

    //add Reservation in the DB
    public static void saveReservation(Reservation reservation) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the Reservation object
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


    //update Reservation NB the blank field == "" (NOT null)
    public void updateReservation(Reservation reservation) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the Reservation object
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

    //delete Reservation by id NB need to Integer.parseInt()
    public void deleteReservation(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a reservation object
            Reservation reservation = session.get(Reservation.class, id);
            if (reservation != null) {
                session.delete(reservation);
                System.out.println("reservation is deleted");
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

    //return Reservation passing his id NB need to Integer.parseInt()
    public Reservation getReservation(int id) {

        Transaction transaction = null;
        Reservation reservation = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an Reservation object
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

    //return all Reservation from a specific user
    @SuppressWarnings("unchecked")
    public static List <Reservation> getAllReservation(int id) {

        UserDao userDao = new UserDao();
        User user = userDao.getUser(id);

        List < Reservation > listOfReservation;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            listOfReservation = session.createQuery(" from Reservation R WHERE R.user = :user ").setParameter("user",user).getResultList();
        }
        return listOfReservation;
    }

    public static List <Reservation> getAllReservation() {

        List < Reservation > listOfReservation;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            listOfReservation = session.createQuery(" from Reservation order by vehicle.id,endDate").getResultList();
        }
        return listOfReservation;
    }

}