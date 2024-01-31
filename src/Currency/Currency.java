package Currency;

public enum Currency {

        USD,EUR,GBP,CHF,CAD;
        public static Currency get(String str) throws IllegalArgumentException{
            return Currency.valueOf(str);
        }

}
