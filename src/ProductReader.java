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
            ArrayList<String> products = new ArrayList<>();

            // reads the file
            Path path = jFileChooser.getSelectedFile().toPath();

            try {
                InputStream inputStream = new BufferedInputStream(Files.newInputStream(path, CREATE));
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                // reads the input
                while (bufferedReader.ready()) {

                    // stores each line in a new String, then adds it to the list
                    String line = bufferedReader.readLine();
                    products.add(line);
                }
                bufferedReader.close(); // closes the reader afterwards

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // prints table header
            System.out.printf("%8s   %16s   %55s   %8s%n", "ID", "NAME", "DESCRIPTION", "PRICE");
            int len = 8 + 16 + 55 + 8 + 9;
            for (int i = 0; i < len; i++)
                System.out.print("=");

            // iterates through each line of the file
            for (String product : products) {

                // I'm using StringBuilders here to hold the products' data because I'm going to be changing them
                // many, many times, and I know it's a better idea to use StringBuilders than Strings for this.
                StringBuilder id = new StringBuilder(),
                        name = new StringBuilder(),
                        description = new StringBuilder(),
                        price = new StringBuilder();

                int i; // declares a pointer for iterating, so it can save its spot in the product String

                // iterates over the line until it hits a comma, adding everything into the ID String
                for (i = 0; i < product.length() && product.charAt(i) != ','; i++) {
                    id.append(product.charAt(i));
                }

                // repeats for the rest of the StringBuilders
                for (i = i + 2; i < product.length() && product.charAt(i) != ','; i++) {
                    name.append(product.charAt(i));
                }

                for (i = i + 2; i < product.length() && product.charAt(i) != ','; i++) {
                    description.append(product.charAt(i));
                }

                for (i = i + 2; i < product.length() && product.charAt(i) != ','; i++) {
                    price.append(product.charAt(i));
                }

                // calculates the final widths of each column
                int width1 = Math.max(id.length(), 8);
                int width2 = Math.max(id.length(), 16);
                int width3 = Math.max(id.length(), 55);
                int width4 = Math.max(id.length(), 8);

                // finally prints the finished row
                System.out.printf("%n%" + width1 + "s   %" + width2 + "s   %" + width3 + "s   %" + width4 + "s", id, name, description, price);
            }
            System.out.println();
        }
    }
}
