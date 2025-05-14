package ui;

import model.Credential;
import util.FileManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PasswordManagerUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public PasswordManagerUI() {
        setTitle("Password Manager");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel - Input Form
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        JTextField siteField = new JTextField();
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton addBtn = new JButton("Add Credential");

        inputPanel.add(new JLabel("Website:"));
        inputPanel.add(siteField);
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(userField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passField);
        inputPanel.add(new JLabel());
        inputPanel.add(addBtn);

        // Table for Displaying Credentials
        String[] columns = {"Website", "Username", "Password"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Load saved credentials
        loadCredentials();

        // Add button logic
        addBtn.addActionListener(e -> {
            String site = siteField.getText();
            String user = userField.getText();
            String pass = new String(passField.getPassword());

            if (!site.isEmpty() && !user.isEmpty() && !pass.isEmpty()) {
                Credential cred = new Credential(site, user, pass);
                FileManager.saveCredential(cred);
                tableModel.addRow(new String[]{site, user, pass});
                siteField.setText("");
                userField.setText("");
                passField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "All fields are required.");
            }
        });
    }

    private void loadCredentials() {
        List<Credential> creds = FileManager.loadCredentials();
        for (Credential c : creds) {
            tableModel.addRow(new String[]{c.getWebsite(), c.getUsername(), c.getPassword()});
        }
    }
}
