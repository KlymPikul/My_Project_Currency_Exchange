package Model;


import Currency.Currency;
import DLayer.Rates;

import java.time.LocalDateTime;

public class ExchangeOperation {
    private static int idOfOperation = 1;
    private int id;
    private Currency currencyIn;
    private Currency currencyOut;
    private double sumIn;
    private double sumOut;
    private LocalDateTime dateOfOperation;
    private final double COMMISION = 3;
    private double rate;

     //конструктор класса
    public ExchangeOperation(Currency currencyIn, Currency currencyOut, double sumIn) {
        this.id = idOfOperation++;
        this.currencyIn = currencyIn;
        this.currencyOut = currencyOut;
        this.sumIn = sumIn;
        this.dateOfOperation = LocalDateTime.now();
        this.rate = 0;
        this.sumOut = 0;

    }

    //Установка суммы после обмена
    public void setSumOut(double sumOut) {
        this.sumOut = sumOut;
    }

    // Установка курса обмена
    public void setRate(double rate) {
        this.rate = rate;
    }

    // Получение ID операции
    public int getId() {
        return this.id;
    }

    // Получение входной валюты
    public Currency getCurrencyIn() {
        return this.currencyIn;
    }

    // Получение выходной валюты
    public Currency getCurrencyOut() {
        return this.currencyOut;
    }

    // Получение суммы входной валюты
    public double getSumIn() {
        return this.sumIn;
    }

    // Получение суммы выходной валюты
    public double getSumOut() {
        return this.sumOut;
    }

    // Получение даты операции
    public LocalDateTime getDateOfOperation() {
        return this.dateOfOperation;
    }

    // Получение комиссии
    public double getCOMMISION() {
        return this.COMMISION;
    }

    // Получение курса обмена
    public double getRate() {
        return this.rate;
    }

    // Переопределение метода toString() для вывода информации об операции
    @Override
    public String toString() {

        return String.format(
                "#%d. Exchange " +
                        "%.2f%s to %.2f%s. Exchange rate - %.4f. Commision - %.2f%%.%n",
                this.id,
                this.sumIn,
                this.currencyIn,
                this.sumOut,
                this.currencyOut,
                this.rate,
                this.COMMISION);
    }

    public double calculateExchange() {
        double rate = Rates.getRate(currencyOut) / Rates.getRate(currencyIn);
        double sumOut = sumIn * rate;
        double commission = sumOut * COMMISION / 100.0;
        sumOut -= commission;

        setRate(rate);
        setSumOut(sumOut);

        return sumOut;
    }
}



