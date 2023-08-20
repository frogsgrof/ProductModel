public class Product {

    /* fields */

    private String id, name, description;
    private double cost;

    /* constructors */

    public Product() {
        id = "";
        name = "";
        description = "";
        cost = -1.0;
    }

    public Product(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = price;
    }

    /* getters */

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    /* setters */

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    /* other */

    public String toCSVDataRecord() {
        return id + ", " + name + ", " + description + ", " + cost;
    }

    public String getAsTableRow() {

        // calculates the widths of each column
        int width1 = Math.max(id.length(), 8);
        int width2 = Math.max(id.length(), 16);
        int width3 = Math.max(id.length(), 55);
        int width4 = Math.max(id.length(), 8);

        // returns as formatted String
        return String.format("%" + width1 + "s   %" + width2 + "s   %" + width3 + "s   %" + width4 + ".2f",
                id, name, description, cost);
    }
}
