package kz.runtime.catalog.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import kz.runtime.catalog.jpa.entity.Category;
import kz.runtime.catalog.jpa.entity.Option;
import kz.runtime.catalog.jpa.entity.Product;
import kz.runtime.catalog.jpa.entity.Value;
import kz.runtime.catalog.jpa.util.JPAUtil;

import java.util.List;

public class ForReadEntity {
    private final String queryCategoryName = "select c from Category c order by c.name";
    private final String queryOptionName = "select o from Option o order by o.name";
    private final String queryProductName = "select p from Product p order by p.name";
    private final String queryValuesName = "select v from Value v order by v.value";

    public void ReadEntityCategory() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Category> categoryTypedQuery = em.createQuery(queryCategoryName, Category.class);
            List<Category> categories = categoryTypedQuery.getResultList();
            for (int i = 0; i < categories.size(); i++) {
                System.out.printf("- %s [%d]\n", categories.get(i).getName(), categories.get(i).getId());
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void ReadEntityOptions() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Option> optionsTypedQuery = em.createQuery(queryOptionName, Option.class);
            List<Option> options = optionsTypedQuery.getResultList();
            for(int i = 0; i < options.size(); i++) {
                System.out.printf("- %s [%d]\n", options.get(i).getName(), options.get(i).getId());
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void ReadEntityProducts() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Product> productsTypedQuery = em.createQuery(queryProductName, Product.class);
            List<Product> products = productsTypedQuery.getResultList();
            for(int i = 0; i < products.size(); i++) {
                System.out.printf("- %s [%d]\n", products.get(i).getName(), products.get(i).getId());
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void ReadEntityValues() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Value> valueTypedQuery = em.createQuery(queryValuesName, Value.class);
            List<Value> values = valueTypedQuery.getResultList();
            for(int i = 0; i < values.size(); i++) {
                System.out.printf("- %s [%d]\n", values.get(i).getValue(), values.get(i).getId());
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void ReadEntityAll() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            System.out.println("===============================\n          Table Category");

            TypedQuery<Category> categoryTypedQuery = em.createQuery(queryCategoryName, Category.class);
            List<Category> categories = categoryTypedQuery.getResultList();
            for (int i = 0; i < categories.size(); i++) {
                System.out.printf("- %s [%d]\n", categories.get(i).getName(), categories.get(i).getId());
            }
            System.out.println("===============================\n");

            System.out.println("===============================\n          Table Option");
            TypedQuery<Option> optionsTypedQuery = em.createQuery(queryOptionName, Option.class);
            List<Option> options = optionsTypedQuery.getResultList();
            for(int i = 0; i < options.size(); i++) {
                System.out.printf("- %s [%d]\n", options.get(i).getName(), options.get(i).getId());
            }
            System.out.println("===============================\n");

            System.out.println("===============================\n          Table Product");
            TypedQuery<Product> productsTypedQuery = em.createQuery(queryProductName, Product.class);
            List<Product> products = productsTypedQuery.getResultList();
            for(int i = 0; i < products.size(); i++) {
                System.out.printf("- %s [%d]\n", products.get(i).getName(), products.get(i).getId());
            }
            System.out.println("===============================\n");

            System.out.println("===============================\n          Table Value");
            TypedQuery<Value> valueTypedQuery = em.createQuery(queryValuesName, Value.class);
            List<Value> values = valueTypedQuery.getResultList();
            for(int i = 0; i < values.size(); i++) {
                System.out.printf("- %s [%d]\n", values.get(i).getValue(), values.get(i).getId());
            }
            System.out.println("===============================\n");

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
