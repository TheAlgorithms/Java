import java.util.Scanner;

// --- Base Interface for Conversions ---
interface Convertible {
    void convert(Scanner scanner);
}

// --- Length Conversion Class ---
class LengthConverter implements Convertible {
    public void convert(Scanner scanner) {
        System.out.println("--- LENGTH CONVERSION ---");
        System.out.println("1. Centimeters to Meters");
        System.out.println("2. Meters to Centimeters");
        System.out.println("3. Meters to Feet");
        System.out.println("4. Feet to Meters");
        System.out.print("Choose conversion: ");
        int choice = scanner.nextInt();

        System.out.print("Enter value: ");
        double value = scanner.nextDouble();
        double result;

        switch (choice) {
            case 1:
                result = value / 100;
                System.out.println(value + " cm = " + result + " m");
                break;
            case 2:
                result = value * 100;
                System.out.println(value + " m = " + result + " cm");
                break;
            case 3:
                result = value * 3.28084;
                System.out.println(value + " m = " + result + " ft");
                break;
            case 4:
                result = value / 3.28084;
                System.out.println(value + " ft = " + result + " m");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
}

// --- Weight Conversion Class ---
class WeightConverter implements Convertible {
    public void convert(Scanner scanner) {
        System.out.println("--- WEIGHT CONVERSION ---");
        System.out.println("1. Grams to Kilograms");
        System.out.println("2. Kilograms to Grams");
        System.out.println("3. Kilograms to Pounds");
        System.out.println("4. Pounds to Kilograms");
        System.out.print("Choose conversion: ");
        int choice = scanner.nextInt();

        System.out.print("Enter value: ");
        double value = scanner.nextDouble();
        double result;

        switch (choice) {
            case 1:
                result = value / 1000;
                System.out.println(value + " g = " + result + " kg");
                break;
            case 2:
                result = value * 1000;
                System.out.println(value + " kg = " + result + " g");
                break;
            case 3:
                result = value * 2.20462;
                System.out.println(value + " kg = " + result + " lb");
                break;
            case 4:
                result = value / 2.20462;
                System.out.println(value + " lb = " + result + " kg");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
}

// --- Temperature Conversion Class ---
class TemperatureConverter implements Convertible {
    public void convert(Scanner scanner) {
        System.out.println("--- TEMPERATURE CONVERSION ---");
        System.out.println("1. Celsius to Fahrenheit");
        System.out.println("2. Fahrenheit to Celsius");
        System.out.println("3. Celsius to Kelvin");
        System.out.println("4. Kelvin to Celsius");
        System.out.print("Choose conversion: ");
        int choice = scanner.nextInt();

        System.out.print("Enter temperature: ");
        double value = scanner.nextDouble();
        double result;

        switch (choice) {
            case 1:
                result = (value * 9 / 5) + 32;
                System.out.println(value + " °C = " + result + " °F");
                break;
            case 2:
                result = (value - 32) * 5 / 9;
                System.out.println(value + " °F = " + result + " °C");
                break;
            case 3:
                result = value + 273.15;
                System.out.println(value + " °C = " + result + " K");
                break;
            case 4:
                result = value - 273.15;
                System.out.println(value + " K = " + result + " °C");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
}

// --- Currency Conversion Class ---
class CurrencyConverter implements Convertible {
    public void convert(Scanner scanner) {
        System.out.println("--- CURRENCY CONVERSION ---");
        System.out.println("1. USD to GBP");
        System.out.println("2. GBP to USD");
        System.out.println("3. USD to JPY");
        System.out.println("4. JPY to USD");
        System.out.print("Choose conversion: ");
        int choice = scanner.nextInt();

        System.out.print("Enter amount: ");
        double value = scanner.nextDouble();
        double result;

        // Example static rates
        double usdToGbp = 0.78;
        double gbpToUsd = 1.28;
        double usdToJpy = 150.35;
        double jpyToUsd = 1 / 150.35;

        switch (choice) {
            case 1:
                result = value * usdToGbp;
                System.out.println(value + " USD = " + result + " GBP");
                break;
            case 2:
                result = value * gbpToUsd;
                System.out.println(value + " GBP = " + result + " USD");
                break;
            case 3:
                result = value * usdToJpy;
                System.out.println(value + " USD = " + result + " JPY");
                break;
            case 4:
                result = value * jpyToUsd;
                System.out.println(value + " JPY = " + result + " USD");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
}

// --- Main Program ---
public class AdvancedConversions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Convertible converter;
        int choice;

        do {
            System.out.println("===== ADVANCED CONVERSIONS =====");
            System.out.println("1. Length Converter");
            System.out.println("2. Weight Converter");
            System.out.println("3. Temperature Converter");
            System.out.println("4. Currency Converter");
            System.out.println("0. Exit");
            System.out.print("Choose converter: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    converter = new LengthConverter();
                    converter.convert(scanner);
                    break;
                case 2:
                    converter = new WeightConverter();
                    converter.convert(scanner);
                    break;
                case 3:
                    converter = new TemperatureConverter();
                    converter.convert(scanner);
                    break;
                case 4:
                    converter = new CurrencyConverter();
                    converter.convert(scanner);
                    break;
                case 0:
                    System.out.println("Thank you for using the converter. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option!");
            }

            System.out.println();

        } while (choice != 0);

        scanner.close();
    }
}
