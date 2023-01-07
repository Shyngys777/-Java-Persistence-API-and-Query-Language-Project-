package ylix9g.catalog.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateCategory2 {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the name of the new category : ");
        String newCategory = in.readLine();
        System.out.print("List the characteristics for the following category : ");
        String newChar = in.readLine();
        String[] words = newChar.split(",");
        try {
            manager.getTransaction().begin();
            Category category = new Category();
            category.setName(newCategory);
            manager.persist(category);
            for (String word : words) {
                Characteristics characteristic = new Characteristics();
                characteristic.setCategory(category);
                characteristic.setName(word);
                manager.persist(characteristic);
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
