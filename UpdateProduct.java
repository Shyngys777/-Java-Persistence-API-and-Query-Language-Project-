package ylix9g.catalog;
import ylix9g.catalog.entity.Characteristics;
import ylix9g.catalog.entity.CharacteristicsMeaning;
import ylix9g.catalog.entity.Product;
import javax.persistence.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class UpdateProduct {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Choose the product by its ID: ");
            long updateProduct = Long.parseLong(in.readLine());
            Product product = manager.find(Product.class, updateProduct);
            System.out.print("Enter the new name for the product: ");
            String newName = in.readLine();
            product.setName(newName);
            if (!newName.isEmpty()) {
                product.setName(newName);
            }
            boolean check = false;
            while (!check) {
                System.out.println("Enter the price of the product: ");
                String priceInput = in.readLine();
                if (priceInput != null && !priceInput.trim().isEmpty()) {
                    if (priceInput.matches("\\d+")) {
                        int productPrice = Integer.parseInt(priceInput);
                        product.setPrice(productPrice);
                        check = true;
                    }
                    else {
                        System.out.println("Enter the price of the product: ");
                    }
                } else {
                    check = true;
                }
            }
            TypedQuery<Characteristics> characteristicsTypedQuery = manager.createQuery(
                    "select c from Characteristics c where c.category = ?1", Characteristics.class
            );characteristicsTypedQuery.setParameter(1, product.getCategory());
            List<Characteristics> characteristics = characteristicsTypedQuery.getResultList();
            for(Characteristics characteristic: characteristics ){
                System.out.println("Enter the new --> " + characteristic.getName() + ": ");
                String newCharMean = in.readLine();
                TypedQuery<CharacteristicsMeaning> characteristicsMeaningTypedQuery = manager.createQuery(
                        "select c from CharacteristicsMeaning c where c.product.id = ?1 and c.characteristics.id = ?2",
                        CharacteristicsMeaning.class
                );characteristicsMeaningTypedQuery.setParameter(1, product.getId());
                characteristicsMeaningTypedQuery.setParameter(2, characteristic.getId());
                characteristicsMeaningTypedQuery.getSingleResult().setName(newCharMean);
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
