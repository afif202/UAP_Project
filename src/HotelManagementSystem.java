import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HotelManagementSystem {

    private static JFrame frame;
    private static JTable table;
    private static DefaultTableModel model;
    private static JTextField nameField, idField, daysField;
    private static JComboBox<String> roomTypeComboBox;
    private static JLabel roomImageLabel;

    public static void main(String[] args) {
        frame = new JFrame("Hotel Management System");
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top Panel for Input Fields
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nameField = new JTextField(15);
        idField = new JTextField(15);
        daysField = new JTextField(5);

        roomTypeComboBox = new JComboBox<>(new String[]{"Standard", "Deluxe", "Suite"});
        roomImageLabel = new JLabel();
        roomImageLabel.setPreferredSize(new Dimension(300, 300));
        roomImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5)); // Thicker border

        JButton addButton = new JButton("Add Guest");
        JButton payButton = new JButton("Make Payment");
        JButton searchButton = new JButton("Search");
        JButton checkoutButton = new JButton("Checkout");
        JButton cancelButton = new JButton("Cancel");

        // Adding components to the panel
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("ID (NIK):"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        inputPanel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(new JLabel("Room Type:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        inputPanel.add(roomTypeComboBox, gbc);

        gbc.gridx = 2; gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(roomImageLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridheight = 1;
        inputPanel.add(new JLabel("Days:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        inputPanel.add(daysField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(payButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(checkoutButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 3;
        inputPanel.add(buttonPanel, gbc);

        // Table to Display Guests
        model = new DefaultTableModel(new String[]{"Name", "ID", "Room Type", "Days", "Total Payment", "Status"}, 0);
        table = new JTable(model);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Update room image and panel color based on selection
        roomTypeComboBox.addActionListener(e -> {
            String selectedRoom = (String) roomTypeComboBox.getSelectedItem();
            try {
                BufferedImage image = switch (selectedRoom) {
                    case "Standard" -> ImageIO.read(new File("src/images/reguler.jpeg"));
                    case "Deluxe" -> ImageIO.read(new File("src/images/deluxe.jpeg"));
                    case "Suite" -> ImageIO.read(new File("src/images/suite.jpeg"));
                    default -> null;
                };

                if (image != null) {
                    roomImageLabel.setIcon(new ImageIcon(image.getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
                } else {
                    roomImageLabel.setIcon(null);
                }

                // Change background color
                Color bgColor = switch (selectedRoom) {
                    case "Standard" -> new Color(173, 216, 230); // Light Blue
                    case "Deluxe" -> new Color(144, 238, 144); // Light Green
                    case "Suite" -> new Color(255, 228, 196); // Light Peach
                    default -> Color.WHITE;
                };
                inputPanel.setBackground(bgColor);
            } catch (IOException ex) {
                System.out.println("Error loading image: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        // Action Listeners for Buttons
        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                String id = idField.getText().trim();
                String roomType = (String) roomTypeComboBox.getSelectedItem();
                int days = Integer.parseInt(daysField.getText().trim());

                if (name.isEmpty() || id.isEmpty() || id.length() != 16 || days <= 0) {
                    throw new IllegalArgumentException("Please fill all fields correctly.");
                }

                // Check for duplicate ID
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 1).equals(id)) {
                        throw new IllegalArgumentException("ID already exists. Please use a unique ID.");
                    }
                }

                double price = switch (roomType) {
                    case "Standard" -> 100;
                    case "Deluxe" -> 200;
                    case "Suite" -> 300;
                    default -> 0;
                };

                double totalPayment = price * days;
                model.addRow(new Object[]{name, id, roomType, days, "$" + totalPayment, "Reserved"});
                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Days must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        payButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.setValueAt("Attend", selectedRow, 5);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a guest to make payment.", "Payment Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        searchButton.addActionListener(e -> {
            String searchKey = JOptionPane.showInputDialog(frame, "Enter Name or ID to Search:");
            if (searchKey != null && !searchKey.isEmpty()) {
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 0).equals(searchKey) || model.getValueAt(i, 1).equals(searchKey)) {
                        table.setRowSelectionInterval(i, i);
                        break;
                    }
                }
            }
        });

        checkoutButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.setValueAt("Clear", selectedRow, 5);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a guest to checkout.", "Checkout Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a guest to cancel.", "Cancel Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(tableScrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static void clearFields() {
        nameField.setText("");
        idField.setText("");
        daysField.setText("");
        roomTypeComboBox.setSelectedIndex(0);
        roomImageLabel.setIcon(null);
    }
}