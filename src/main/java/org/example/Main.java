package org.example;

public class Main {
    public static void main(String[] args) {
        // 1. Создаем мутабельный адрес
        Address myAddress = new Address("Москва");

        // 2. Создаем иммутабельного человека
        Person person = new Person("Иван", myAddress);
        System.out.println("Изначально: " + person);

        // --- ПОПЫТКА ВЗЛОМА №1 ---
        // Пытаемся изменить оригинальный объект адреса
        myAddress.setCity("Санкт-Петербург");

        System.out.println("После попытки взлома 1: " + person);
        // Результат: у Ивана все еще "Москва". Защита в конструкторе сработала!

        // --- ПОПЫТКА ВЗЛОМА №2 ---
        // Пытаемся получить адрес через геттер и изменить его
        Address stolenAddress = person.getAddress();
        stolenAddress.setCity("Новосибирск");

        System.out.println("После попытки взлома 2: " + person);
        // Результат: у Ивана все еще "Москва". Защита в геттере сработала!
    }
}