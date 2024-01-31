package DLayer;

import Model.ExchangeOperation;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ExchangeRepository {
    private ArrayList<ExchangeOperation> operations;

    // Конструктор класса
    public ExchangeRepository() {
        this.operations = new ArrayList<>();
    }

    // Добавление операции в репозиторий и сохранение в файл
    public boolean addOperation(ExchangeOperation operation) {
        boolean result = this.operations.add(operation);
        if (result) {
            saveToFile(operation);
        }
        return result;
    }

    //// Метод сохранения информации об операции в файл в формате CSV
    private void saveToFile(ExchangeOperation operation) {
        try (FileWriter writer = new FileWriter("exchangeLog.txt", true)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // Форматирование даты в удобочитаемый вид
            String formattedDate = operation.getDateOfOperation().format(formatter);

            // Создание строки с данными операции для записи в файл
            String dataString = String.format("Обмен %.2f %s на %s. С учётом комиссии ВЫ получите %.2f %s. %s\n",
                    operation.getSumIn(),
                    operation.getCurrencyIn(),
                    operation.getCurrencyOut(),
                    operation.getSumOut(),
                    operation.getCurrencyOut(),
                    formattedDate);

            // Запись строки в файл
            writer.write(dataString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Получение операции по ID
    public ExchangeOperation getOperation(int id) {
        for (ExchangeOperation operation : operations) {
            if (operation.getId() == id) {
                return operation;
            }
        }
        return null;
    }

    // Получение списка всех операций
    public ArrayList<ExchangeOperation> getAllOperations() {
        return new ArrayList<>(this.operations);
    }
}