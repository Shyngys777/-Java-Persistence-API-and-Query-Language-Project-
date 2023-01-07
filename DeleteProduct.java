package ylix9g.catalog;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DeleteProduct {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try {
            manager.getTransaction().begin();
            System.out.println("Enter the ID of the Product to Delete from the Database : ");
            String productIdString = input.readLine();
            long productId = Long.parseLong(productIdString);
            Query query = manager.createQuery("delete from Product p where p.id = ?1");
            query.setParameter(1, productId);
            Query query2 = manager.createQuery("delete from CharacteristicsMeaning c where c.product.id = ?1");
            query2.setParameter(1, productId);
            System.out.println("Product was deleted successfully !");
            query2.executeUpdate();
            query.executeUpdate();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
