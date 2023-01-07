package ylix9g.catalog.entity;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class JPQL {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the name of the new category : ");
        String newCategory = input.readLine();
        try {
            manager.getTransaction().begin();
            TypedQuery<Long> productCountTypedQuery = manager.createQuery(
                    "select count(c.id) from Category c where c.name = :name", Long.class);
            productCountTypedQuery.setParameter("name", newCategory);
            Long productCount =  productCountTypedQuery.getSingleResult();
            if (productCount > 0) {
                System.out.println("The category with that name already exists !");
            } else {
                System.out.print("List the characteristics for the following category : ");
                String newChar = input.readLine();
                String[] words = newChar.split(",");
                for (String word : words) {
                    Category category = new Category();
                    category.setName(newCategory);
                    manager.persist(category);
                    Characteristics characteristic = new Characteristics();
                    characteristic.setCategory(category);
                    characteristic.setName(word);
                    manager.persist(characteristic);
                }
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
