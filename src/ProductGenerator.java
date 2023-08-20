import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static java.nio.file.StandardOpenOption.CREATE;

import java.util.Locale;
import java.util.Scanner;

public class ProductGenerator {

    public static void main(String[] args) {

        // initializing scanner
        Scanner scanner = new Scanner(System.in);

        // I had to put this in here because as it turns out, my computer thinks I'm in France, meaning the number
        // 10.5 would have to be written as 10,5 (using commas) which would break the program
        scanner.useLocale(Locale.ROOT);

        // directory of the file is in this project's files
        Path path = Paths.get(System.getProperty("user.dir") + "\\src\\ProductTestData.txt");

        // declares OutputStream and BufferedWriter to keep them in scope
        OutputStream outputStream;
        BufferedWriter bufferedWriter;

        try {
            outputStream = new BufferedOutputStream(Files.newOutputStream(path, CREATE));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // for controlling the input loop
        boolean createNewProduct;

        // for storing all products
        ArrayList<Product> products = new ArrayList<>();

        do {
            // instantiates the new product
            Product product = new Product();

            // prompts for ID and stores it
            product.setId(SafeInput.getRegExString(scanner, "Enter an ID number", "[0-9]+[^,]?"));

            // prompts for product name and stores it
            product.setName(SafeInput.getRegExString(scanner, "Enter product name", ".+[^,]"));

            // prompts for description and stores it
            product.setDescription(SafeInput.getRegExString(scanner, "Enter a description (NOTE: description cannot contain commas.)", ".+[^,]"));

            // prompts for cost and stores it
            product.setCost(SafeInput.getDouble(scanner, "Enter a cost (without any currency symbols)"));

            // stores the object in the array
            products.add(product);

            // prompts to continue/break loop
            createNewProduct = SafeInput.getYNConfirm(scanner, "Add another product? (y/n)");

        } while (createNewProduct);

        // after the user inputs all data, it saves it to the file by iterating through the ArrayList
        try {

            for (Product product : products) {

                // uses toCSVDataRecord to generate the String
                bufferedWriter.write(product.toCSVDataRecord());
                bufferedWriter.newLine();
            }

            bufferedWriter.close(); // closes the writer after it goes through each item in the ArrayList

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
