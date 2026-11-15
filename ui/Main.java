package ui;

import controller.Controller;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import model.RentalManagement;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // Use the default Swing look and feel if the system one is unavailable.
            }

            RentalManagement management = new RentalManagement();
            Controller controller = new Controller(management);
            new MainForm(controller).setVisible(true);
        });
    }
}
