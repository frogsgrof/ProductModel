import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {

    public static void main(String[] args) {

        File directory = new File(System.getProperty("user.dir") + "\\src\\");

        JFileChooser jFileChooser = new JFileChooser(directory);

        // limiting the file chooser to only show .txt files
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        jFileChooser.setAcceptAllFileFilterUsed(false);

        // after the user picks a text file...
        if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

            // instantiates the ArrayList of text file contents
            ArrayList<String> productData = new ArrayList<>();

            // reads the file
            Path path = jFileChooser.getSelectedFile().toPath();

            try {
                InputStream inputStream = new BufferedInputStream(Files.newInputStream(path, CREATE));
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                // reads the input
                while (bufferedReader.ready()) {

                    // stores each line in a new String, then adds it to the list
                    String line = bufferedReader.readLine();
                    productData.add(line);
                }
                bufferedReader.close(); // closes the reader afterwards

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // for storing products
            ArrayList<Product> products = new ArrayList<>();

            // array for storing split Strings
            String[] split;

            // iterates through each line of the file
            for (String line : productData) {

                // splits the current line into the array
                split = line.split(", ", 4);

                // constructs product and adds it to the list
                Product product = new Product(split[0], split[1], split[2], Double.parseDouble(split[3]));
                products.add(product);
            }

            // prints table header
            System.out.printf("%8s   %16s   %55s   %8s%n", "ID", "NAME", "DESCRIPTION", "PRICE");
            int len = 8 + 16 + 55 + 8 + 9;
            for (int i = 0; i < len; i++)
                System.out.print("=");
            System.out.println();

            // iterates through the list of products and prints each row of the table using a method
            for (Product product : products)
                System.out.println(product.getAsTableRow());
        }
    }
}
