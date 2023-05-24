package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Product;

import java.util.Arrays;

public class ShopRepositoryTest {
    ShopRepository repository = new ShopRepository();

    Product product1 = new Product(1, "bread", 10);
    Product product2 = new Product(2, "milk", 20);
    Product product3 = new Product(3, "sugar", 30);
    Product product4 = new Product(4, "potato", 40);

    @BeforeEach
    public void setup() {

        repository.add(product1);     //добавляем товары
        repository.add(product2);
        repository.add(product3);
    }

    @Test
    public void addProduct() {

        Product[] products = repository.findAll();
        System.out.print("Тест-3_Добавление продукта. До добавления продуктов - " + Arrays.toString(new int[]{products.length}));

        repository.add(product4);

        products = repository.findAll();
        Product[] expected = {product1, product2, product3, product4};
        Assertions.assertEquals(4, products.length);
        Assertions.assertArrayEquals(expected, products);

        System.out.println(", после добавления продуктов - " + Arrays.toString(new int[]{products.length}));
    }

    @Test
    public void removeExistingProduct() {

        repository.removeById(product3.getId());
        Product[] actual = repository.findAll();

        Product[] expected = {product1, product2};

        System.out.println("Тест-1_Удаление существующего продукта. Продукт с индентификатором " + product3.getId() + " успешно удален из корзины, сейчас в корзине " + actual.length + " продукта");

        Assertions.assertEquals(2, actual.length); //Сравниваем длину массива (количество товаров) в корзине по итогу
        Assertions.assertArrayEquals(expected, actual); //Сравниваем содержимое массива (товаров)

    }

    @Test
    public void removeNonExistingProduct() {

        System.out.println("Тест-2_Удаление не сеществующего продукта. Продукт с индентификатором " + product4.getId() + " не найден.");
        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.removeById(product4.getId());
        });
    }

    @Test
    public void addExistProduct() {

        System.out.print("Тест-4_Добавление уже имеющегося продукта.");
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repository.add(product1);
        });
        System.out.println(" Продукт с Id '" + product1.getId() + "' уже добавлен в корзину");
    }
}
