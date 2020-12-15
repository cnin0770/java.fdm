package com.fdm.w6.currencyconverter;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class EcbCurrencyReader implements iEcbCurrencyReader {
    // xml element form: <name attribute_pair1=attribute_pair1 attribute_pair2=attribute_pair2>content</name>
    private static final String url = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
    private static Map<String, Double> currencies;
    private Scanner scanner = new Scanner(System.in);

    void load() {
        Document doc = null;
        Element root = null;
        Element cubes = null;
        Element cube = null;
        currencies = new HashMap<String, Double>();
        CurrencyException fetch_error = new CurrencyException("[APP] ECB xml fetched failed.");
        SAXBuilder builder = new SAXBuilder();

        try {
            doc = builder.build(new URL(url));
            root = doc.getRootElement();
        } catch (IOException | JDOMException e) {
            e.printStackTrace();
        }

        for (Element i: root.getChildren()) {
            if (i.getName().equals("Cube")) {
                cubes = i;
                break;
            }
        }

        for (Element j: cubes.getChildren()) {
            if (j.getName().equals("Cube")) {
                cube = j;
                break;
            }
        }

        if (cube.getChildren().size() < 1) fetch_error.printStackTrace();

        for (Element k: cube.getChildren())
            currencies.put(k.getAttributeValue("currency"), Double.parseDouble(k.getAttributeValue("rate")));

        if (currencies.isEmpty()) fetch_error.printStackTrace();
    }

    void validate(String expression) throws CurrencyException {
        CurrencyException parse_error = new CurrencyException("[ERR] invalid input.");
        if (expression == null || expression.isEmpty()) throw parse_error;

        String[] commands = format(expression);
        if (commands.length != 3) throw parse_error;

        boolean val2 = (commands[0].equals("BUY") || commands[0].equals("SELL"));
        boolean val3 = currencies.keySet().contains(commands[1]);
        boolean val4 = ((Double.parseDouble(commands[2])) > 0);

        if (!(val2 && val3 && val4)) throw parse_error;
    }

    String[] format(String expression) {
        return expression.trim().toUpperCase().split("\\s+");
    }

    String read() {
        String expression = null;

        System.out.println("==== Euro exchange calculator ====");
        System.out.println("[APP] usage: \'buy/sell currency_mark amount\'.");
        System.out.println("[APP] use \'marks\' to check all available currency marks.");
        System.out.printf("> ");

        try {
            expression = scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expression;
    }

    String write(double result, String currency) {
        return String.format("[APP] result: %.2f %s.", result, currency);
    }

    String convert(String expression) {
        String[] commands = format(expression);
        String result = "";
        double amount = Double.parseDouble(commands[2]);
        if (commands[0].equals("BUY")) result = write(amount / currencies.get(commands[1])," euro");
        if (commands[0].equals("SELL")) result = write(amount * currencies.get(commands[1]), commands[1]);
        return result;
    }

    void showMark(String expression) {
        if (expression.equals("marks")) {
            System.out.println(currencies.keySet().toString());
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        EcbCurrencyReader instance = new EcbCurrencyReader();
        instance.load();
        String expression = instance.read();
        instance.showMark(expression);
        try {
            instance.validate(expression);
            System.out.println(instance.convert(expression));
        } catch (CurrencyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle() {
        load();
        String expression = read();
        try {
            validate(expression);
            System.out.println(convert(expression));
        } catch (CurrencyException e) {
            e.printStackTrace();
        }
    }

    private class CurrencyException extends Exception {
        private static final long serialVersionUID = 1L;
        CurrencyException(String message) {
            super(message);
        }
    }
}
