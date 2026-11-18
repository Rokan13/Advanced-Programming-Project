package cse.bubt.edu.vehicle_parts;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GarageGUI extends JFrame {

    private JPanel mainPanel;
    private GarageService currentService;

    // Screen 1
    private JTextField usernameField;
    private JPasswordField passwordField;

    // Screen 2
    private JTextField customerNameField;

    // Screen 3
    private String selectedVehicleName;
    private JLabel serviceChargeLabel;
    private JPanel partsContainer;
    private JButton startRepairBtn;
    private JRadioButton stdRadio;
    private JRadioButton prmRadio;
    private Map<String, JCheckBox> partCheckBoxes;

    public GarageGUI() {
        setTitle("Garage Management System");
        setSize(700, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        setContentPane(scrollPane);

        showLoginScreen();
    }

    private void clearScreen() {
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // =========================================================================
    // Screen 1: Staff Login
    // =========================================================================
    private void showLoginScreen() {
        clearScreen();

        mainPanel.add(new JLabel("=== STAFF LOGIN ==="));
        mainPanel.add(new JLabel("Username (admin):"));
        usernameField = new JTextField("admin", 20);
        usernameField.setMaximumSize(new Dimension(250, 28));
        usernameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(usernameField);

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(new JLabel("Password (1234):"));
        passwordField = new JPasswordField("1234", 20);
        passwordField.setMaximumSize(new Dimension(250, 28));
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(passwordField);

        mainPanel.add(Box.createVerticalStrut(15));
        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> handleLogin());
        mainPanel.add(loginBtn);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void handleLogin() {
        String user = usernameField.getText().trim();
        String pass = new String(passwordField.getPassword()).trim();

        if ("admin".equals(user) && "1234".equals(pass)) {
            showWelcomeScreen();
        } else {
            JOptionPane.showMessageDialog(this, "Wrong Username or Password!");
        }
    }

    // =========================================================================
    // Screen 2: Welcome & Customer Entry
    // =========================================================================
    private void showWelcomeScreen() {
        clearScreen();
        currentService = null;

        mainPanel.add(new JLabel("=== WELCOME TO GARAGE ==="));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(new JLabel("Enter Customer Name:"));

        customerNameField = new JTextField(25);
        customerNameField.setMaximumSize(new Dimension(300, 28));
        customerNameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(customerNameField);

        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(new JLabel("Choose Service Option:"));

        JButton opt1Btn = new JButton("1. Repair Vehicle");
        opt1Btn.addActionListener(e -> proceedWithOption("Repair Vehicle"));
        mainPanel.add(opt1Btn);

        mainPanel.add(Box.createVerticalStrut(8));

        JButton opt2Btn = new JButton("2. Repair and Change Part of Vehicle");
        opt2Btn.addActionListener(e -> proceedWithOption("Repair & Change Part"));
        mainPanel.add(opt2Btn);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void proceedWithOption(String serviceOption) {
        String cName = customerNameField.getText().trim();
        if (cName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter customer name first!");
            return;
        }
        currentService = new GarageService(cName, serviceOption);
        showVehicleAndPartsScreen();
    }

    // =========================================================================
    // Screen 3: Left-Side CheckBox Vehicle & Parts Selection
    // =========================================================================
    private void showVehicleAndPartsScreen() {
        clearScreen();
        selectedVehicleName = null;

        mainPanel.add(new JLabel("Customer: " + currentService.getCustomerName() + " | Mode: " + currentService.getServiceType()));
        mainPanel.add(new JSeparator());
        mainPanel.add(Box.createVerticalStrut(10));

        // ১. সব গাড়ির লিস্ট বাম পাশে RadioButton আকারে
        mainPanel.add(new JLabel("Step 1: Select Vehicle:"));
        ButtonGroup vehicleGroup = new ButtonGroup();
        List<String> vehicles = VehicleParts.getAllVehicles();

        for (String v : vehicles) {
            JRadioButton vRadio = new JRadioButton(v);
            vRadio.addActionListener(e -> onVehicleSelected(v));
            vehicleGroup.add(vRadio);
            mainPanel.add(vRadio);
        }

        mainPanel.add(Box.createVerticalStrut(10));
        serviceChargeLabel = new JLabel("Service Charge: (Select a vehicle above)");
        mainPanel.add(serviceChargeLabel);
        mainPanel.add(Box.createVerticalStrut(10));

        // ২. পার্টস দেখানোর জায়গা (বাম পাশে CheckBox থাকবে)
        partsContainer = new JPanel();
        partsContainer.setLayout(new BoxLayout(partsContainer, BoxLayout.Y_AXIS));
        partsContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(partsContainer);

        mainPanel.add(Box.createVerticalStrut(15));

        // ৩. Start Repairing বাটন
        startRepairBtn = new JButton("Start Repairing");
        startRepairBtn.setVisible(false);
        startRepairBtn.addActionListener(e -> handleStartRepairing());
        mainPanel.add(startRepairBtn);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void onVehicleSelected(String selectedV) {
        this.selectedVehicleName = selectedV;
        currentService.setVehicle(selectedV);
        partsContainer.removeAll();
        startRepairBtn.setVisible(false);

        int charge = VehicleParts.getServiceCharge(selectedV);
        serviceChargeLabel.setText("General Service Charge: " + charge + " BDT");

        if ("Repair Vehicle".equals(currentService.getServiceType())) {
            partsContainer.add(new JLabel("Ready for general repair of " + selectedV + "."));
            startRepairBtn.setVisible(true);
        } else {
            buildCheckBoxPartsList(selectedV);
        }

        partsContainer.revalidate();
        partsContainer.repaint();
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void buildCheckBoxPartsList(String vehicleName) {
        Font banglaFont = new Font("Nirmala UI", Font.PLAIN, 13);
        partCheckBoxes = new LinkedHashMap<>();

        partsContainer.add(new JSeparator());
        partsContainer.add(new JLabel("Step 2: Select Quality Tier & Mark Parts on the Left:"));

        // Tier সিলেকশন (Standard / Premium)
        JPanel tierPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tierPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        stdRadio = new JRadioButton("Standard (Normal)", true);
        prmRadio = new JRadioButton("Premium");
        ButtonGroup tierGroup = new ButtonGroup();
        tierGroup.add(stdRadio);
        tierGroup.add(prmRadio);
        tierPanel.add(new JLabel("Select Quality: "));
        tierPanel.add(stdRadio);
        tierPanel.add(prmRadio);
        partsContainer.add(tierPanel);

        // প্রতিটি পার্টসের বাম পাশে সরাসরি JCheckBox (টিক মার্ক)
        List<String> parts = VehicleParts.getVehicleParts(vehicleName);
        for (int i = 0; i < parts.size(); i++) {
            String partName = parts.get(i);
            int stdPrice = VehicleParts.getPartPrice(vehicleName, partName, "Standard");
            int prmPrice = VehicleParts.getPartPrice(vehicleName, partName, "Premium");

            String text = (i + 1) + ". " + partName + "  [Standard: " + stdPrice + " Tk | Premium: " + prmPrice + " Tk]";
            JCheckBox cb = new JCheckBox(text);
            cb.setFont(banglaFont);
            cb.setAlignmentX(Component.LEFT_ALIGNMENT);

            // অন্তত ১টি পার্টস মার্ক করলেই নিচে Start Repairing বাটন দেখাবে
            cb.addActionListener(e -> updateStartButtonVisibility());

            partCheckBoxes.put(partName, cb);
            partsContainer.add(cb);
        }
    }

    private void updateStartButtonVisibility() {
        boolean anyChecked = false;
        for (JCheckBox cb : partCheckBoxes.values()) {
            if (cb.isSelected()) {
                anyChecked = true;
                break;
            }
        }
        startRepairBtn.setVisible(anyChecked);
    }

    private void handleStartRepairing() {
        // Option B হলে যেসব CheckBox মার্ক করা আছে সেগুলো GarageService-এ যোগ হবে
        if (!"Repair Vehicle".equals(currentService.getServiceType()) && partCheckBoxes != null) {
            currentService.setVehicle(selectedVehicleName); // লিস্ট ফ্রেশ রাখা
            String tier = prmRadio.isSelected() ? "Premium" : "Standard";

            for (Map.Entry<String, JCheckBox> entry : partCheckBoxes.entrySet()) {
                if (entry.getValue().isSelected()) {
                    currentService.addPart(entry.getKey(), tier);
                }
            }
        }

        currentService.startRepairing();
        showBillScreen();
    }

    // =========================================================================
    // Screen 4: Cash Memo
    // =========================================================================
    private void showBillScreen() {
        clearScreen();

        mainPanel.add(new JLabel("=== REPAIR DONE & CASH MEMO ==="));
        mainPanel.add(Box.createVerticalStrut(10));

        JTextArea memoArea = new JTextArea(currentService.generateBillMemo());
        memoArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        memoArea.setEditable(false);
        memoArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(memoArea);

        mainPanel.add(Box.createVerticalStrut(15));
        JButton payBtn = new JButton("Pay Bill Now");
        payBtn.addActionListener(e -> showThankYouScreen());
        mainPanel.add(payBtn);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // =========================================================================
    // Screen 5: Thank You
    // =========================================================================
    private void showThankYouScreen() {
        clearScreen();

        mainPanel.add(new JLabel("Thank you, " + currentService.getCustomerName() + ", for visiting our garage!"));
        mainPanel.add(Box.createVerticalStrut(15));

        JButton nextBtn = new JButton("Ready for Next Customer");
        nextBtn.addActionListener(e -> showWelcomeScreen());
        mainPanel.add(nextBtn);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GarageGUI().setVisible(true));
    }
}