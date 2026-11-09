import java.awt.*;
import java.awt.event.*;

public class EduForm extends Frame implements ItemListener {

    // Input fields and UI components
    private TextField nameField;
    private TextField emailField;
    private Choice courseChoice;
    private CheckboxGroup ratingGroup;
    private Checkbox rbExcellent, rbGood, rbAverage;
    private Button submitButton;
    private Button clearButton;
    private Label messageLabel;

    public EduForm() {
        // Frame properties
        setTitle("EduForm - Student Feedback System");
        setSize(480, 420);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        // Header Title
        Label headerLabel = new Label("EduForm - Student Feedback Collection", Label.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(headerLabel, BorderLayout.NORTH);

        // Center Panel for Form Controls
        Panel formPanel = new Panel();
        formPanel.setLayout(new GridLayout(5, 2, 10, 15));

        // Name
        formPanel.add(new Label("Student Name:"));
        nameField = new TextField(20);
        formPanel.add(nameField);

        // Email
        formPanel.add(new Label("Email Address:"));
        emailField = new TextField(20);
        formPanel.add(emailField);

        // Course Choice
        formPanel.add(new Label("Select Course:"));
        courseChoice = new Choice();
        courseChoice.add("MCA");
        courseChoice.add("B.Tech");
        courseChoice.add("BCA");
        courseChoice.add("MBA");
        formPanel.add(courseChoice);

        // Rating Radio Buttons (CheckboxGroup)
        formPanel.add(new Label("Rate Experience:"));
        Panel ratingPanel = new Panel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        ratingGroup = new CheckboxGroup();
        rbExcellent = new Checkbox("Excellent", ratingGroup, true);
        rbGood = new Checkbox("Good", ratingGroup, false);
        rbAverage = new Checkbox("Average", ratingGroup, false);

        rbExcellent.addItemListener(this);
        rbGood.addItemListener(this);
        rbAverage.addItemListener(this);

        ratingPanel.add(rbExcellent);
        ratingPanel.add(rbGood);
        ratingPanel.add(rbAverage);
        formPanel.add(ratingPanel);

        // Buttons
        submitButton = new Button("Submit");
        clearButton = new Button("Clear");

        // Event Handling for Submit Button using Anonymous Inner Class
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();

                // Validation: Check if Name or Email is empty
                if (name.isEmpty() || email.isEmpty()) {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("Please fill all required fields.");
                    showMessageDialog("Please fill all required fields.", "Validation Error");
                } else {
                    Checkbox selectedRating = ratingGroup.getSelectedCheckbox();
                    String ratingText = (selectedRating != null) ? selectedRating.getLabel() : "None";
                    String courseText = courseChoice.getSelectedItem();

                    // Display entered details in the console
                    System.out.println("========== FEEDBACK SUBMITTED ==========");
                    System.out.println("Student Name : " + name);
                    System.out.println("Email Address: " + email);
                    System.out.println("Course       : " + courseText);
                    System.out.println("Rating       : " + ratingText);
                    System.out.println("========================================\n");

                    messageLabel.setForeground(new Color(0, 128, 0));
                    messageLabel.setText("Feedback submitted successfully!");
                    showMessageDialog("Feedback submitted successfully!", "Success");
                }
            }
        });

        // Event Handling for Clear Button using Anonymous Inner Class
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset all TextFields and selections
                nameField.setText("");
                emailField.setText("");
                courseChoice.select(0);
                ratingGroup.setSelectedCheckbox(rbExcellent);
                messageLabel.setText("");

                System.out.println("Form has been reset.\n");
            }
        });

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);

        formPanel.add(new Label("")); // Spacer
        formPanel.add(buttonPanel);

        add(formPanel, BorderLayout.CENTER);

        // Message Label at the bottom
        messageLabel = new Label("", Label.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 13));
        add(messageLabel, BorderLayout.SOUTH);

        // Window closing handling
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    // Displays a modal AWT Dialog message
    private void showMessageDialog(String message, String title) {
        Dialog dialog = new Dialog(this, title, true);
        dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        dialog.setSize(320, 130);
        dialog.setLocationRelativeTo(this);

        Label dialogLabel = new Label(message, Label.CENTER);
        Button okButton = new Button("OK");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.add(dialogLabel);
        dialog.add(okButton);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                dialog.dispose();
            }
        });

        dialog.setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // Handle checkbox selection response
        Checkbox selected = ratingGroup.getSelectedCheckbox();
        if (selected != null) {
            messageLabel.setForeground(Color.BLUE);
            messageLabel.setText("Selected Rating: " + selected.getLabel());
        }
    }

    public static void main(String[] args) {
        new EduForm();
    }
}
