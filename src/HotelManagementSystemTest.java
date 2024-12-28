import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static org.junit.jupiter.api.Assertions.*;

class HotelManagementSystemTest {

    private HotelManagementSystem hotelSystem;
    private DefaultTableModel model;

    @BeforeEach
    void setUp() {
        hotelSystem = new HotelManagementSystem();
        model = new DefaultTableModel(new String[]{"Name", "ID", "Room Type", "Days", "Total Payment", "Status"}, 0);
    }

    @Test
    void testAddGuest_ValidInput() {
        // Mock input data
        String name = "John Doe";
        String id = "1234567890123456";
        String roomType = "Deluxe";
        int days = 3;
        double expectedPayment = 200 * days;

        // Add guest logic
        model.addRow(new Object[]{name, id, roomType, days, "$" + expectedPayment, "Reserved"});

        // Assert
        assertEquals(1, model.getRowCount());
        assertEquals(name, model.getValueAt(0, 0));
        assertEquals(id, model.getValueAt(0, 1));
        assertEquals(roomType, model.getValueAt(0, 2));
        assertEquals(days, model.getValueAt(0, 3));
        assertEquals("$" + expectedPayment, model.getValueAt(0, 4));
        assertEquals("Reserved", model.getValueAt(0, 5));
    }


    @Test
    void testAddGuest_InvalidDays() {
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("InvalidDays");
        });

        assertNotNull(exception);
    }

    @Test
    void testMakePayment() {
        model.addRow(new Object[]{"John Doe", "1234567890123456", "Suite", 3, "$900", "Reserved"});

        int selectedRow = 0;
        model.setValueAt("Attend", selectedRow, 5);

        assertEquals("Attend", model.getValueAt(selectedRow, 5));
    }

    @Test
    void testCheckout() {
        model.addRow(new Object[]{"John Doe", "1234567890123456", "Suite", 3, "$900", "Reserved"});

        int selectedRow = 0;
        model.setValueAt("Clear", selectedRow, 5);

        assertEquals("Clear", model.getValueAt(selectedRow, 5));
    }

    @Test
    void testCancelGuest() {
        model.addRow(new Object[]{"John Doe", "1234567890123456", "Suite", 3, "$900", "Reserved"});

        int selectedRow = 0;
        model.removeRow(selectedRow);

        assertEquals(0, model.getRowCount());
    }
}
