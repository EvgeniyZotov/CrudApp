package kz.runtime.catalog.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import kz.runtime.catalog.jpa.entity.Category;
import kz.runtime.catalog.jpa.entity.Option;
import kz.runtime.catalog.jpa.entity.Product;
import kz.runtime.catalog.jpa.entity.Value;
import kz.runtime.catalog.jpa.util.JPAUtil;

import java.util.List;
import java.util.Scanner;

public class Operations {
    ForReadEntity readEntity = new ForReadEntity();

    Scanner scanner = new Scanner(System.in);

    private final String queryCategoryName = "select c from Category c order by c.name";
    private final String queryProductName = "select p from Product p order by p.name";

    public void createEntity() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            // Select categories to display as list
            TypedQuery<Category> categoryTypedQuery = em.createQuery(queryCategoryName, Category.class);
            List<Category> categories = categoryTypedQuery.getResultList();
            for (int i = 0; i < categories.size(); i++) {
                System.out.printf("- %s [%d]\n", categories.get(i).getName(), categories.get(i).getId());
            }
            //Input category number
            System.out.printf("Select category by number: ");
            String categoryNumScanner = scanner.nextLine();
            int categoryNum = Integer.parseInt(categoryNumScanner);
            Category category = categories.get(categoryNum);
            //Input product name
            System.out.print("Input product name: ");
            String productNameScanner = scanner.nextLine();
            //Input product price
            System.out.println("Input product price: ");
            String productPriceScanner = scanner.nextLine();
            int productPrice = Integer.parseInt(productPriceScanner);
            //Create product entity
            Product product = new Product();
            product.setCategory(category);
            product.setName(productNameScanner);
            product.setPrice(productPrice);
            //Persist product entity to database
            em.persist(product);
            //Iterate category options
            for (Option option : category.getOptions()) {
                //Input option value
                System.out.println(option.getName() + ": ");
                String valueScanner = scanner.nextLine();
                //Create value entity
                Value value = new Value();
                value.setProduct(product);
                value.setOption(option);
                value.setValue(valueScanner);
                //Persist volue entity to database
                em.persist(value);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void readEntity() {
        System.out.println("""
                        - Categories [1]
                        - Options [2]
                        - Products [3]
                        - Values [4]
                        - All Tables [5]
                        Select an table:
                        """);
        String tableScannerNum = scanner.nextLine();
        switch (tableScannerNum) {
            case "1" -> readEntity.ReadEntityCategory();
            case "2" -> readEntity.ReadEntityOptions();
            case "3" -> readEntity.ReadEntityProducts();
            case "4" -> readEntity.ReadEntityValues();
            case "5" -> readEntity.ReadEntityAll();
        }
    }

    public void updateEntity() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deleteEntity() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Product> productsTypedQuery = em.createQuery(queryProductName, Product.class);
            List<Product> products = productsTypedQuery.getResultList();
            for(int i = 0; i < products.size(); i++) {
                System.out.printf("- %s [%d]\n", products.get(i).getName(), products.get(i).getId());
            }
            System.out.printf("Select product by number: ");
            String productIdScanner = scanner.nextLine();
            long productId = Integer.parseInt(productIdScanner);
            Product product = em.find(Product.class, productId);
            em.remove(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
