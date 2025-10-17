package com.thealgorithms.conversions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class USDtoIND {

    // ✅ Free, keyless API endpoint
    private static final String API_URL = "https://open.er-api.com/v6/latest/USD";

    /**
     * Fetches the live USD to INR rate from open.er-api.com
     * 
     * @return the exchange rate, or -1 if an error occurs
     */
    public static double fetchLiveRate() {
        try {
            // Step 1: Connect to the URL
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Step 2: Read API response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Step 3: Extract INR value manually from the JSON text
            String json = response.toString();

            // Find INR rate
            int start = json.indexOf("\"INR\":") + 6;
            int end = json.indexOf(",", start);
            if (end == -1)
                end = json.indexOf("}", start);
            String rateStr = json.substring(start, end);

            return Double.parseDouble(rateStr);
        } catch (Exception e) {
            System.out.println("Error fetching exchange rate: " + e.getMessage());
            return -1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("USD ↔ INR Converter");
        System.out.println("--------------------");
        System.out.println("Choose conversion type:");
        System.out.println("1. USD → INR");
        System.out.println("2. INR → USD");
        System.out.print("Enter your choice (1 or 2): ");
        int choice = sc.nextInt();

        // Fetch live exchange rate
        double rate = fetchLiveRate();

        if (rate <= 0) {
            System.out.println("Failed to retrieve live exchange rate. Please check your internet connection.");
            sc.close();
            return;
        }

        // Conversion logic
        if (choice == 1) {
            System.out.print("Enter amount in USD: ");
            double usd = sc.nextDouble();
            double inr = usd * rate;
            System.out.printf("%.2f USD = %.2f INR (Rate: %.2f)%n", usd, inr, rate);
        } else if (choice == 2) {
            System.out.print("Enter amount in INR: ");
            double inr = sc.nextDouble();
            double usd = inr / rate;
            System.out.printf("%.2f INR = %.2f USD (Rate: %.2f)%n", inr, usd, rate);
        } else {
            System.out.println("Invalid choice. Please run the program again and select 1 or 2.");
        }

        sc.close();
    }
}
