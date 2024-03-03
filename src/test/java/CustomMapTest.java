import org.example.CustomMap;
import org.example.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CustomMapTest {

    private CustomMap<String, Purchase> customMap;

    @BeforeEach
    public void setUp() {
        customMap = new CustomMap<>();
        for (int i = 0; i < 500_000; i++) {
            Purchase purchase = new Purchase();
            customMap.put(purchase.getProduct(), purchase);
        }
        customMap.put("test product", new Purchase("test product", 10, Instant.now()));
    }

    @Test
    public void whenGetValueByTheKey_thenValueIsReturned() {
        Purchase returnedPurchase = customMap.getValue("test product");
        assertEquals("test product", returnedPurchase.getProduct());

    }

    @Test
    public void whenAddObject_thenObjectIsAdded() {
        customMap.put("test product 2", new Purchase("test product 2", 10, Instant.now()));
        Purchase returnedPurchase = customMap.getValue("test product 2");
        assertEquals("test product 2", returnedPurchase.getProduct());

    }


    @Test
    public void whenRemoveValueByKey_thenRemoved() {
        customMap.removeValue("test product");
        Purchase returnedPurchase = customMap.getValue("test product");
        assertNull(returnedPurchase);

    }

    @Test
    public void whenLastModified_thenCorrectReturn() {
        Instant expected = Instant.now();
        customMap.removeValue("test product");
        assertEquals(expected, customMap.getLastModified());

    }


}
