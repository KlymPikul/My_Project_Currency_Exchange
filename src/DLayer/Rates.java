package DLayer;

import Currency.Currency;

import java.util.HashMap;

public class Rates {
    // Хэш-карта для хранения курсов обмена
    private static final HashMap<String, Double> rates = new HashMap<>();

    // Статический блок инициализации, где устанавливаются курсы обмена
    static {
        rates.put("USD", 1.09);
        rates.put("EUR", 1.00);  // Базовая валюта
        rates.put("GBP", 0.85);
        rates.put("CHF", 0.94);
        rates.put("CAD", 1.46);
    }

    // Метод для получения курса обмена для заданной валюты
    public static double getRate(Currency currency) {
        // Проверка наличия валюты в хэш-карте
        if (rates.containsKey(currency.name())) {
            return rates.get(currency.name());
        } else {
            // Если валюта отсутствует, можно вернуть некий стандартный курс (например, 1.0)
            System.out.println("Курс для валюты " + currency + " не найден.");
            return 1.0;
        }
    }

    // Метод для установки курса обмена для заданной валюты
    public static void setRate(Currency currency, double rate) {
        // Проверка наличия валюты в хэш-карте
        if (rates.containsKey(currency.name())) {
            // Установка нового курса
            rates.put(currency.name(), rate);
            System.out.println("Курс для валюты " + currency + " успешно обновлен.");
        } else {
            // Если валюта отсутствует, можно вывести сообщение об ошибке
            System.out.println("Ошибка: Курс для валюты " + currency + " не может быть обновлен, так как валюта не найдена.");
        }
    }
}