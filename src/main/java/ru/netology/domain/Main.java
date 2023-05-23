package ru.netology.domain;

import ru.netology.repository.NotFoundException;
import ru.netology.repository.ShopRepository;

public class Main {
    public static void main(String[] args) {
        ShopRepository repository = new ShopRepository();

        try {
            repository.removeById(1);
            System.out.println("Hello");
        } catch (NotFoundException s) {
            System.out.println("error");
        }

    }
}