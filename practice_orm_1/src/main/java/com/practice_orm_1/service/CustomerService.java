package com.practice_orm_1.service;

import com.practice_orm_1.model.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerService implements ICustomerService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        String sqlQuery = "select c from Customer c";
        TypedQuery<Customer> query = entityManager.createQuery(sqlQuery, Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer getCustomerById(int id) {
        String sqlQuery = "select c from Customer c where c.id = :id";
        TypedQuery<Customer> query = entityManager.createQuery(sqlQuery, Customer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void addCustomer(Customer customer) {
        Transaction tx = null;
        Customer origin;
        if (customer.getId() == 0) {
            origin = new Customer();
        } else {
            origin = getCustomerById(customer.getId());
        }
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            origin.setName(customer.getName());
            origin.setEmail(customer.getEmail());
            origin.setAddress(customer.getAddress());
            session.saveOrUpdate(origin);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public void removeCustomer(Customer customer) {
        Customer customer1 = getCustomerById(customer.getId());
        if (customer1 != null) {
            Transaction tx = null;
            try (Session session = sessionFactory.openSession()) {
                tx = session.beginTransaction();
                session.remove(customer1);
                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if (tx != null) {
                    tx.rollback();
                }
            }
        }
    }
}
