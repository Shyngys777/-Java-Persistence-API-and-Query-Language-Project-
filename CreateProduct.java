package ylix9g.catalog;
import ylix9g.catalog.entity.Category;
import ylix9g.catalog.entity.Characteristics;
import ylix9g.catalog.entity.CharacteristicsMeaning;
import ylix9g.catalog.entity.Product;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

// ask id of the product
// then ask the new name of this product change the name of it
// and then same as before we have to update the values of the product and its characteristics
public class CreateProduct {
    public static void main(String[] args) throws IOException{
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try {
            manager.getTransaction().begin();
            List<Category> categories = manager.createQuery("select c from Category c", Category.class)
                    .getResultList();
            for (int i = 0; i < categories.size(); i++) {
                Category category = categories.get(i);
                String name = category.getName();
                System.out.println(name + " [" + (i+1) + "]");
            }
            System.out.println("Choose the category of the product : ");
            String categoryList = input.readLine();
            // Category selectedCategory = manager.find(Category.class, Long.parseLong(categoryList));
            Category selectedCategory = categories.get(Integer.parseInt(categoryList) - 1);

            System.out.println("Create the name of the product: ");
            String productName = input.readLine();
            Product product = new Product();
            product.setName(productName);
            product.setCategory(selectedCategory);

            System.out.println("Enter the price of the product: ");
            String productPrice = input.readLine();
            product.setPrice(Integer.parseInt(productPrice));
            manager.persist(product);

            List<Characteristics> characteristicsList = manager.createQuery("select c from Characteristics c",
                    Characteristics.class).getResultList();
            for (Characteristics characteristics : characteristicsList) {
                String characteristicsName = characteristics.getName();
                System.out.print(characteristicsName + " : ");
                String NameCharacteristicsMeaning = input.readLine();

                CharacteristicsMeaning characteristicsMeaning = new CharacteristicsMeaning();
                characteristicsMeaning.setCharacteristics(characteristics);
                characteristicsMeaning.setName(NameCharacteristicsMeaning);
                characteristicsMeaning.setProduct(product);
                product.setCategory(selectedCategory);
                manager.persist(characteristicsMeaning);
                // product.getCategory
                // enter new everything
                // setters
            }
            input.close();
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
