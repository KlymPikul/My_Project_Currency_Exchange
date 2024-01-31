package ServiceLayer;


import Currency.Currency;
import DLayer.ExchangeRepository;
import DLayer.Rates;
import Model.ExchangeOperation;

import java.util.ArrayList;

public class ExchangeService {
    private ExchangeRepository repository;

    // Конструктор для создания сервиса обмена с переданным репозиторием
    public ExchangeService(ExchangeRepository repository) {
        this.repository = repository;
    }

    // Метод для выполнения операции обмена
    public double exchange(double sumIn, Currency currencyIn, Currency currencyOut) {
        ExchangeOperation operation = new ExchangeOperation(currencyIn, currencyOut, sumIn);
        double sumOut = calculateExchange(operation);
        operation.setSumOut(sumOut);
        repository.addOperation(operation);
        return sumOut;
    }

    // Метод для расчета суммы после обмена с учетом курса и комиссии
    private double calculateExchange(ExchangeOperation operation) {
        double exchangeRate = Rates.getRate(operation.getCurrencyIn());
        double sumOut = calculatedSumOut(operation, exchangeRate);
        return sumOut;
    }

    // Метод для расчета суммы после обмена с учетом курса и комиссии
    private double calculatedSumOut(ExchangeOperation operation, double exchangeRate) {
        double sumOut = operation.getSumIn() * exchangeRate * (1 - operation.getCOMMISION() / 100);
        return sumOut;
    }

    // Метод для получения операции по ID
    public ExchangeOperation getOperation(int id) {
        return repository.getOperation(id);
    }

    // Метод для получения всех операций из репозитория
    public ArrayList<ExchangeOperation> getAllOperations() {
        return repository.getAllOperations();
    }
}