package ui;

// Importing the required libraries
import barul.*;
import clienti.Student;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UI {
	// Setting the static variables
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean PG13 = true;
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static boolean paharPressed = false;
    private static boolean dozaPressed = false;
    private static boolean sticlaPressed = false;
    private static String lastButtonPressed = "";
    //////////////////////////////// Don't forget to modify this /////////////////////////////////////////////////////////////////////////////////////////////////
    private static String fileLocation = "F:\\WIP\\Javra\\bartenderTheJavaMix-update\\tot\\bin\\ui";
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static JLabel labelPahar;
    private static JLabel labelDoza;
    private static JLabel labelSticla;

    // Class for the clickable images
    private static JLabel createClickableLabel(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        JLabel label = new JLabel(icon);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return label;
    }
    
    // Custom JPanel class used as the background panel for the UI
    @SuppressWarnings("serial")
	private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        // Constructor to initialize the background image with the specified path
        public BackgroundPanel(String absolutePath) {
            ImageIcon imageIcon = new ImageIcon(absolutePath);
            backgroundImage = imageIcon.getImage();
        }
        
        // Method to change the background image dynamically
        public void changeBackgroundImage(String newImagePath) {
            ImageIcon newImageIcon = new ImageIcon(newImagePath);
            backgroundImage = newImageIcon.getImage();
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Method for initializing the UI
    public static void initializeUI() {
        // Create a JFrame
        JFrame frame = new JFrame("Bartender the Java Mix");
        frame.setSize(1280, 729); // Setting the size of the frame

        // PG13 verification
        if(PG13 == true) {
        	labelPahar = createClickableLabel(fileLocation + "/coke_glass.png");
            labelDoza = createClickableLabel(fileLocation + "/coke_can.png");
            labelSticla = createClickableLabel(fileLocation + "/coke_bottle.png");
        } else {
        	labelPahar = createClickableLabel(fileLocation + "/beer_glass.png");
        	labelDoza = createClickableLabel(fileLocation + "/ciucas_can.png");
        	labelSticla = createClickableLabel(fileLocation + "/heineken_bottle.png");
        }
        
        // Setting the default background image
        BackgroundPanel backgroundPanel = new BackgroundPanel(fileLocation + "/default.png");

        // Creating the drink button
        JButton drinkButton = createYellowButton("noroc");

        // Creating the toggle for the adaptor
        JToggleButton toggleButton = new JToggleButton("Toggle");
        toggleButton.setBackground(Color.YELLOW);
        toggleButton.setForeground(Color.BLACK);

        // Creating the textfield
        JTextField textField = createNonEditableYellowTextField("Barul 1301B");

        // Adding all the elements inside the UI
        backgroundPanel.add(labelPahar);
        backgroundPanel.add(labelDoza);
        backgroundPanel.add(labelSticla);
        backgroundPanel.add(drinkButton);
        backgroundPanel.add(toggleButton);
        backgroundPanel.add(textField);

        backgroundPanel.setLayout(null);

        // Positioning the elements
        labelPahar.setBounds(80, 85, 109, 225);
        labelDoza.setBounds(400, 85, 225, 225);
        labelSticla.setBounds(690, 85, 225, 225);
        drinkButton.setBounds(80, 475, 300, 150);
        toggleButton.setBounds(1200, 575, 40, 80);
        textField.setBounds(500, 620, 500, 30);

        // Creating MouseListeners for checking which object was last pressed
        labelPahar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                paharPressed = true;
                lastButtonPressed = "pahar";
                backgroundPanel.changeBackgroundImage(fileLocation + "/glass.png");
            }
        });

        labelDoza.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dozaPressed = true;
                lastButtonPressed = "doza";
                backgroundPanel.changeBackgroundImage(fileLocation + "/glass.png");
            }
        });

        labelSticla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sticlaPressed = true;
                lastButtonPressed = "sticla";
                backgroundPanel.changeBackgroundImage(fileLocation + "/glass.png");
            }
        });

        drinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bautura selectedBautura = null;

                // The default case where nothing is selected
                if (!(paharPressed || dozaPressed || sticlaPressed)) {
                    textField.setText("N-ai nimic de baut");
                } else {
                    if ("sticla".equals(lastButtonPressed)) {
                        // Check if it's a direct Sticla object or an AdaptorSticla
                        if (toggleButton.isSelected()) {
                            // AdaptorSticla object creation
                            if(PG13 == true)
                                selectedBautura = new AdaptorSticla(new Sticla("Suc"));
                            else
                                selectedBautura = new AdaptorSticla(new Sticla("Bere"));
                        } else {
                            textField.setText("Nu poti sa bei din sticla fara sa folosesti adaptorul. Activeaza Toggle!");
                        }
                    } else if ("doza".equals(lastButtonPressed)) {
                        // Direct Doza object creation
                        if(PG13 == true)
                            selectedBautura = new Doza("Doza de suc");
                        else
                            selectedBautura = new Doza("Doza de bere");

                        // Check if toggle is selected, then open the Doza
                        if (toggleButton.isSelected()) {
                            ((Doza) selectedBautura).desfaceDoza();
                        } else {
                            textField.setText("Nu poti sa bei din doza fara sa o desfaci. Activeaza Toggle!");
                            selectedBautura = null;  // Set to null to prevent drinking from Doza without toggle
                        }
                    } else if ("pahar".equals(lastButtonPressed)) {
                        // Direct Pahar object creation
                        if(PG13 == true)
                            selectedBautura = new Pahar("Suc");
                        else
                            selectedBautura = new Pahar("Bere");
                    }

                    if (selectedBautura != null) {
                        textField.setText("Hai noroc!");
                        backgroundPanel.changeBackgroundImage(fileLocation + "/serving.png");

                        // Create an instance of the Student class
                        Student student = new Student();
                        // Call the beaBautura method with the selected drink
                        student.beaBautura(selectedBautura);
                    }
                }

                // Resetting the values to false so you can't drink twice from the same recipient
                paharPressed = false;
                dozaPressed = false;
                sticlaPressed = false;
            }
        });


        frame.setContentPane(backgroundPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Default button object
    private static JButton createYellowButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.YELLOW);
        button.setForeground(Color.BLACK);
        return button;
    }
    
    // Default text field object
    private static JTextField createNonEditableYellowTextField(String text) {
        JTextField textField = new JTextField(text);
        textField.setBackground(Color.YELLOW);
        textField.setForeground(Color.BLACK);
        textField.setEditable(false);
        return textField;
    }
}
