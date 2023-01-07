package ylix9g.catalog;

import ylix9g.catalog.entity.Category;
import ylix9g.catalog.entity.Characteristics;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class CreateCategory {
    public static void main(String[] args) {
        // New name for the category
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();

            String categoryName = "Клавиатуры";
            String characteristics = "Подстветка, Разьем подключения";

            Category category = new Category();
            category.setName(categoryName);
            manager.persist(category);

            String[] characteristicsArray = characteristics.split(",\\s");
            for (String s : characteristicsArray) {

            }
            Characteristics characteristic1 = new Characteristics();
            characteristic1.setName("Characteristic #1");
            characteristic1.setCategory(category);
            manager.persist(characteristic1);

            Characteristics characteristics2 = new Characteristics();
            characteristics2.setName("Characteristic #2");
            characteristics2.setCategory(category);
            manager.persist(characteristics2);

            Characteristics characteristics3 = new Characteristics();
            characteristics3.setName("Characteristic #3");
            characteristics3.setCategory(category);
            manager.persist(characteristics3);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}

