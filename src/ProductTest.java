import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    Product p1, p2, p3, p4, p5;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        p1 = new Product("000001", "name", "description", 10.5);
        p2 = new Product("000001", "Sassy Mint",
                "Changes the Pokémon's stats to match the Sassy nature.", 10.5);
    }

    @Test
    void setId() {
        p1.setId(":)");
        assertEquals(":)", p1.getId());
    }

    @Test
    void setName() {
        p1.setName("new name");
        assertEquals("new name", p1.getName());
    }

    @Test
    void setDescription() {
        p1.setDescription("new desc");
        assertEquals("new desc", p1.getDescription());
    }

    @Test
    void setCost() {
        p1.setCost(4);
        assertEquals(4, p1.getCost());
    }

    @Test
    void toCSVDataRecord() {
        String test = p1.getId() + ", " + p1.getName() + ", " + p1.getDescription() + ", " + p1.getCost();
        assertEquals(test, p1.toCSVDataRecord());
    }

    @Test
    void getAsTableRow() {
        String test = "  000001         Sassy Mint    Changes the Pokémon's stats to match the Sassy nature.      10,50";
        assertEquals(test, p2.getAsTableRow());
    }
}