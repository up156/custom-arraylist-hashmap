import org.example.CustomList;
import org.example.Purchase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomListTest {

    private CustomList<Purchase> customList;

    @BeforeEach
    public void setUp() {
        customList = new CustomList<>();
        for (int i = 0; i < 500_000; i++) {
            Purchase purchase = new Purchase();
            customList.add(purchase);
        }
        customList.add(new Purchase("test product", 10, Instant.now()));
    }

    @Test
    public void whenGetObject_thenObjectIsReturned() {
        Purchase returnedPurchase = customList.get(500_000);
        assertEquals("test product", returnedPurchase.getProduct());

    }


    @Test
    public void whenAddObject_thenObjectIsAdded() {
        customList.add(new Purchase("test product 2", 10, Instant.now()));
        Purchase returnedPurchase = customList.get(500_001);
        assertEquals("test product 2", returnedPurchase.getProduct());

    }

    @Test
    public void whenAddArrayObjects_thenObjectsIsAdded() {

        assertEquals(500_001, customList.getLength());
        Purchase[] array = new Purchase[]{new Purchase(), new Purchase(), new Purchase()};
        customList.addAll(array);
        assertEquals(500_004, customList.getLength());

    }

    @Test
    public void whenRemoveAllObjects_thenLengthIs0() {
        customList.removeAll();
        assertEquals(0, customList.getLength());

    }

    @Test
    public void whenRemoveAllObjects_thenIsEmpty() {
        customList.removeAll();
        assertTrue(customList.isEmpty());

    }

    @Test
    public void whenLastModified_thenCorrectReturn() {
        Instant expected = Instant.now();
        customList.removeAll();
        assertEquals(expected, customList.getLastModified());

    }

    @Test
    public void whenRemoveAllAndTryToGet_ExceptionIsThrown() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
                    Purchase returnedPurchase = customList.get(500_000);
                    assertEquals("test product", returnedPurchase.getProduct());
                    customList.removeAll();
                    customList.get(500_000);
                }
        );

    }

}
