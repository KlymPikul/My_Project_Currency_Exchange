package UI;


import Currency.Currency;

import DLayer.ExchangeRepository;
import Model.ExchangeOperation;
import ServiceLayer.ExchangeService;

import java.util.Scanner;

public class ExchangeUI {
    // Статический объект ExchangeService для работы с операциями обмена
    private static ExchangeService exchangeService = new ExchangeService(new ExchangeRepository());

    // Метод для запуска пользовательского интерфейса
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Обмен валюты");
            System.out.println("2. Получить информацию об операции");
            System.out.println("3. Получить историю операций");
            System.out.println("4. Выход");
            System.out.print("Выберите действие: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    exchange();
                    break;
                case 2:
                    getOperation();
                    break;
                case 3:
                    getAllOperations();
                    break;
                case 4:
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова.");
            }
        } while (choice != 4);
    }

    // Метод для выполнения операции обмена
    private static void exchange() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите сумму для обмена: ");
        double sumIn = scanner.nextDouble();

        System.out.print("Введите валюту для обмена (USD, EUR, GBP, CHF, CAD): ");
        String currencyInStr = scanner.next();
        Currency currencyIn = Currency.get(currencyInStr);

        System.out.print("Введите валюту, которую вы хотите приобрести (USD, EUR, GBP, CHF, CAD): ");
        String currencyOutStr = scanner.next();
        Currency currencyOut = Currency.get(currencyOutStr);

        double sumOut = exchangeService.exchange(sumIn, currencyIn, currencyOut);

        System.out.println("Вы получите " + sumOut + " " + currencyOut);
    }

    // Метод для получения информации об операции по ID
    private static void getOperation() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите ID операции: ");
        int id = scanner.nextInt();

        ExchangeOperation operation = exchangeService.getOperation(id);

        if (operation != null) {
            System.out.println(operation);
        } else {
            System.out.println("Операция с ID " + id + " не найдена.");
        }
    }

    // метод для вывода всех операций в истории
    private static void getAllOperations() {
        for (ExchangeOperation operation : exchangeService.getAllOperations()) {
            System.out.println(operation);
        }
    }
}