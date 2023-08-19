import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.Scanner;
import java.util.ArrayList;

public class ProductWriter {

    public static void main(String[] args) {

        // initializing scanner
        Scanner scanner = new Scanner(System.in);

        // for storing all products
        ArrayList<String> products = new ArrayList<>();

        // this time I'm having it attempt to open the output stream before getting any input,
        // so it doesn't waste the user's time if it fails

        // directory of the file is in this project's files
        Path path = Paths.get(System.getProperty("user.dir") + "\\src\\ProductTestData.txt");

        try {
            OutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(path, CREATE));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            // now it can continue on to getting the input

            // for controlling the input loop
            boolean createNewProduct = false;

            do {
                // variables for storing product elements
                String id,
                        name,
                        description;
                double cost;

                // prompts for ID and stores it
                id = SafeInput.getRegExString(scanner, "Enter an ID number", "[0-9]");

                // prompts for product name and stores it
                name = SafeInput.getRegExString(scanner, "Enter product name", "[^,]");

                // prompts for description and stores it
                description = SafeInput.getRegExString(scanner, "Enter a description (NOTE: description cannot contain commas.)", "[^,]");

                // prompts for cost and stores it
                cost = SafeInput.getDouble(scanner, "Enter a cost (without any currency symbols)");

                // puts all the data into a string and stores it
                String productData = id + ", " + name + ", " + description + ", " + cost;
                products.add(productData);

                // I realize that in the Person Model program I added the ability to edit the people, but now I realize
                // that wasn't even in the directions so unfortunately I'm not adding it this time

                // prompts to continue/break loop
                createNewProduct = SafeInput.getYNConfirm(scanner, "Add another product? (y/n)");

            } while (createNewProduct);

            // after the loop, it saves everything to the file
            for (String product : products) {
                bufferedWriter.write(product);
                bufferedWriter.newLine();
            }
            bufferedWriter.close(); // closes the writer

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
