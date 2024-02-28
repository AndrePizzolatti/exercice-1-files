import entities.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter file path: ");
        String path = sc.nextLine();

        String file = path + "\\summary.csv";

        List<Product> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();

            while (line != null) {
                System.out.println(line);
                String[] term = line.split(",");
                String name = term[0];
                Double value = Double.valueOf(term[1]);
                Integer quantity = Integer.valueOf(term[2]);
                products.add(new Product(name, value, quantity));
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();

        boolean success = new File(path + "\\out").mkdir();

        String outputPath = path + "\\out\\summary.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {
            for (Product product : products) {
                bw.write(product.getName() + "," + product.total());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}