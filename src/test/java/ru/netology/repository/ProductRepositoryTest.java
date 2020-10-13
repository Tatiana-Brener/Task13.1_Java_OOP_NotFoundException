package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();

    private Book firstBook = new Book(1, "Rich brother, rich sister", 500, "Robert Kiyosaki");
    private Book secondBook = new Book(2, "Diamond Wisdom", 700, "Michael Roach");
    private Book thirdBook = new Book(3, "Rich father, poor father", 800, "Robert Kiyosaki");
    private Smartphone firstPhone = new Smartphone(4, "IPhone 11", 52000, "Apple");
    private Smartphone secondPhone = new Smartphone(5, "Samsung Galaxy", 58000, "Samsung");
    private Smartphone thirdPhone = new Smartphone(6, "IPhone 7", 25000, "Apple");

    @BeforeEach
    public void setUp() {
        repository.saveProduct(firstBook);
        repository.saveProduct(secondBook);
        repository.saveProduct(thirdBook);
        repository.saveProduct(firstPhone);
        repository.saveProduct(secondPhone);
        repository.saveProduct(thirdPhone);
    }

    @Test
    public void shouldThrowNotFoundExceptionAfterRemoveByIdIfNotExists() {

        int idToRemove = 7;
        assertThrows(NotFoundException.class, () -> repository.removeById(idToRemove));
    }

    @Test
    public void shouldRemoveByIdIfExists() {

        repository.removeById(5);

        Product[] expected = new Product[]{firstBook, secondBook, thirdBook, firstPhone, thirdPhone};
        Product[] actual = repository.getAllProducts();

        assertArrayEquals(expected, actual);

    }
}