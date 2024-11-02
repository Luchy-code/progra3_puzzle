package View;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuDif extends JFrame {

    private JPanel contentPane;
    private JButton btnBack, btnStartGame;
    private JComboBox<String> dificultadComboBox;
    private String modo;
    
    public MenuDif(String modo) {
        initialize(modo);
    }
    
    private void initialize(String modo) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        setResizable(false); // Evita que se pueda redimensionar
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Cargar y establecer el ícono de la ventana
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/questinMark.PNG"));
        setIconImage(icon.getImage());

        setContentPane(contentPane);
        contentPane.setLayout(null);

        dificultadComboBox = new JComboBox<>(new String[] {"Facil", "Medio", "Dificil"});
        dificultadComboBox.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        dificultadComboBox.setBounds(145, 54, 83, 23);
        contentPane.add(dificultadComboBox);

        btnStartGame = new JButton("Iniciar Juego");
        btnStartGame.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnStartGame.setBounds(105, 148, 169, 45);
        contentPane.add(btnStartGame);

        // Cargar la imagen y escalarla
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/img/izquierda.png"));
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        btnBack = new JButton(scaledIcon);
        btnBack.setBounds(5, 210, 45, 45);
        contentPane.add(btnBack);
    }
    
    public void setmodo(String modo) {
        this.modo = modo;
    }
    
    public String getmodo() {
        return this.modo;
    }
    
    public String getDif() {
        return dificultadComboBox.getSelectedItem().toString();
    }
    
    public void addActionButtonBack(ActionListener listener) {
        btnBack.addActionListener(listener);
    }
    
    public void addActionButtonPlay(ActionListener listener) {
        btnStartGame.addActionListener(listener);
    }
    
    public void mostrarMenu() {
        setVisible(true);
    }

    public void close() {
        dispose();
    }
}
