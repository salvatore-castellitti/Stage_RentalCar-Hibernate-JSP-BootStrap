package com.project.management.dao;

import java.util.ArrayList;
import java.util.List;

import com.project.management.utl.HibernateUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.project.management.model.User;

import javax.persistence.Query;


//CRUD User
public class UserDao {

    //add User in the DB
    public static void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    //update user NB the blank field == "" (NOT null)
    public void updateUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //delete User by id NB need to Integer.parseInt()
    public void deleteUser(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a user object
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
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

    //return User passing his id NB need to Integer.parseInt()
    public User getUser(int id) {

        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = session.get(User.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    public static boolean validate(String email, String password){
        //System.out.println(email);
        Transaction transaction = null;
        User user = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            //start transaction
            transaction = session.beginTransaction();
            //get user obj
            user = (User) session.createQuery("FROM User U WHERE U.email = :email AND U.password = :password").setParameter("email",email).setParameter("password",password).uniqueResult();
            //System.out.println(user);
            if(user != null)
                return true;

            //commit transaction
            transaction.commit();
        }catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();

        }
        return false;
    }

    public int loginUser(String email, String password){
        //System.out.println(email);
        Transaction transaction = null;
        User user = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            //start transaction
            transaction = session.beginTransaction();
            //get user obj
            user = (User) session.createQuery("FROM User U WHERE U.email = :email AND U.password = :password").setParameter("email",email).setParameter("password",password).uniqueResult();
            //System.out.println(user);
            if(user != null)
                return user.getId();

            //commit transaction
            transaction.commit();
        }catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();

        }
        return 0;
    }

    //return all CUSTOMER User, NOT ADMIN need to create one other of all?
    @SuppressWarnings("unchecked")
    public static List < User > getAllUser() {

        List < User > listOfUser;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            listOfUser = session.createQuery(" from User U where U.role = false").getResultList();

        }
        return listOfUser;
    }

    //return (at login, if user is admin)
    public static boolean isAdmin(String email, String password){
        //System.out.println(email);
        Transaction transaction = null;
        User user = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            //start transaction
            transaction = session.beginTransaction();
            //get user obj
            user = (User) session.createQuery("FROM User U WHERE U.email = :email AND U.password = :password").setParameter("email",email).setParameter("password",password).uniqueResult();
            System.out.println(user);

            //commit transaction
            transaction.commit();

            return user.isRole();
        }catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();

        }
        return false;
    }


}