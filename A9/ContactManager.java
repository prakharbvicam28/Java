import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class Contact {
    String name;
    String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}

public class ContactManager extends JFrame {

    // Contact data list
    private List<Contact> contactList = new ArrayList<>();

    // GUI Components
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField searchField;
    private JTable contactTable;
    private DefaultTableModel tableModel;

    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;
    private JButton btnSearch;
    private JButton btnShowAll;

    public ContactManager() {
        setTitle("Contact Manager - CRUD & Search");
        setSize(700, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ------------------ Top Input Panel ------------------
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setBorder(BorderFactory.createTitledBorder("Contact Details"));

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 8));
        formPanel.add(new JLabel("  Contact Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("  Phone Number:"));
        phoneField = new JTextField();
        formPanel.add(phoneField);

        topPanel.add(formPanel, BorderLayout.CENTER);

        // Buttons for CRUD
        JPanel crudButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnAdd = new JButton("Add (Create)");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear Fields");

        crudButtonPanel.add(btnAdd);
        crudButtonPanel.add(btnUpdate);
        crudButtonPanel.add(btnDelete);
        crudButtonPanel.add(btnClear);

        topPanel.add(crudButtonPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        // ------------------ Center Table Panel (Retrieve) ------------------
        String[] columnNames = {"Name", "Phone Number"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // read-only table cells
            }
        };

        contactTable = new JTable(tableModel);
        contactTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(contactTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Contact List (Click row to select)"));
        add(scrollPane, BorderLayout.CENTER);

        // ------------------ Bottom Search Panel ------------------
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Contacts"));

        searchPanel.add(new JLabel("Search (Name / Number):"));
        searchField = new JTextField(15);
        searchPanel.add(searchField);

        btnSearch = new JButton("Search");
        btnShowAll = new JButton("Show All");
        searchPanel.add(btnSearch);
        searchPanel.add(btnShowAll);

        add(searchPanel, BorderLayout.SOUTH);

        // ------------------ Pre-load Sample Contacts ------------------
        addInitialContacts();
        refreshTable(contactList);

        // ------------------ Event Listeners ------------------

        // Table Row Selection Listener (Retrieve into text fields)
        contactTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = contactTable.getSelectedRow();
                if (selectedRow != -1) {
                    nameField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    phoneField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                }
            }
        });

        // 1. CREATE (Add Contact)
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String phone = phoneField.getText().trim();

                if (name.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(ContactManager.this, "Please enter both Name and Phone Number.", "Input Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Check for duplicate number
                for (Contact c : contactList) {
                    if (c.phone.equals(phone)) {
                        JOptionPane.showMessageDialog(ContactManager.this, "A contact with this phone number already exists.", "Duplicate Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                Contact newContact = new Contact(name, phone);
                contactList.add(newContact);
                refreshTable(contactList);
                clearInputFields();
                JOptionPane.showMessageDialog(ContactManager.this, "Contact added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // 2. UPDATE Contact
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = contactTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(ContactManager.this, "Please select a contact from the table to update.", "Selection Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String newName = nameField.getText().trim();
                String newPhone = phoneField.getText().trim();

                if (newName.isEmpty() || newPhone.isEmpty()) {
                    JOptionPane.showMessageDialog(ContactManager.this, "Name and Phone Number cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String currentPhone = tableModel.getValueAt(selectedRow, 1).toString();

                // Find contact in main list
                for (Contact c : contactList) {
                    if (c.phone.equals(currentPhone)) {
                        c.name = newName;
                        c.phone = newPhone;
                        break;
                    }
                }

                refreshTable(contactList);
                clearInputFields();
                JOptionPane.showMessageDialog(ContactManager.this, "Contact updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // 3. DELETE Contact
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = contactTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(ContactManager.this, "Please select a contact from the table to delete.", "Selection Required", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String phoneToDelete = tableModel.getValueAt(selectedRow, 1).toString();
                String nameToDelete = tableModel.getValueAt(selectedRow, 0).toString();

                int confirm = JOptionPane.showConfirmDialog(ContactManager.this,
                        "Are you sure you want to delete contact: " + nameToDelete + " (" + phoneToDelete + ")?",
                        "Confirm Delete", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    contactList.removeIf(c -> c.phone.equals(phoneToDelete));
                    refreshTable(contactList);
                    clearInputFields();
                    JOptionPane.showMessageDialog(ContactManager.this, "Contact deleted successfully!", "Deleted", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // CLEAR Input Fields
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInputFields();
                contactTable.clearSelection();
            }
        });

        // 4. SEARCH Contact
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText().trim().toLowerCase();
                if (query.isEmpty()) {
                    JOptionPane.showMessageDialog(ContactManager.this, "Please enter a name or phone number to search.", "Search Query Empty", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                List<Contact> matchingContacts = new ArrayList<>();
                for (Contact c : contactList) {
                    if (c.name.toLowerCase().contains(query) || c.phone.contains(query)) {
                        matchingContacts.add(c);
                    }
                }

                if (matchingContacts.isEmpty()) {
                    JOptionPane.showMessageDialog(ContactManager.this, "No contact found matching: " + query, "No Results", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    refreshTable(matchingContacts);
                    JOptionPane.showMessageDialog(ContactManager.this, "Found " + matchingContacts.size() + " contact(s).", "Search Results", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // RESET / SHOW ALL Contacts
        btnShowAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchField.setText("");
                refreshTable(contactList);
            }
        });

        setVisible(true);
    }

    // Refresh table rows with given list of contacts
    private void refreshTable(List<Contact> list) {
        tableModel.setRowCount(0);
        for (Contact c : list) {
            tableModel.addRow(new Object[]{c.name, c.phone});
        }
    }

    // Clear input text fields
    private void clearInputFields() {
        nameField.setText("");
        phoneField.setText("");
    }

    // Sample pre-stored contacts
    private void addInitialContacts() {
        contactList.add(new Contact("Rahul Sharma", "9876543210"));
        contactList.add(new Contact("Priya Verma", "9812345678"));
        contactList.add(new Contact("Amit Singh", "9123456789"));
        contactList.add(new Contact("Neha Gupta", "9988776655"));
        contactList.add(new Contact("Vikas Kumar", "9765432109"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ContactManager();
            }
        });
    }
}

