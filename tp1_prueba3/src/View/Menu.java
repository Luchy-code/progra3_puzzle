package View;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Menu {

	private JFrame frame;
	private JButton btn123;
	private JButton btnABC;
	
	public Menu() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 300);
		frame.setResizable(false); // Evita que se pueda redimensionar
		frame.setTitle("Puzzle 15 - Educacion edition");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PUZZLE 15");
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 40));
		lblNewLabel.setBounds(94, 27, 196, 58);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("INTEGRANTES: OVIEDO & ROLDAN");
		lblNewLabel_1.setBounds(10, 105, 250, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		btn123 = createButton("123", "/img/123.PNG", Color.RED, 53, 161, 95, 65);
		frame.getContentPane().add(btn123);
		
		btnABC = createButton("ABC", "/img/abc.PNG", Color.BLUE, 242, 161, 95, 65);
		frame.getContentPane().add(btnABC);
	}

		public void addAccionBtn123(ActionListener listener) {
			btn123.addActionListener(listener);
		}
		
		public void addAccionBtnABC(ActionListener listener) {
			btnABC.addActionListener(listener);
		}
		
		public JButton createButton(String text, String imagePath, Color backgroundColor, int x, int y, int width, int height) {
		    JButton button = new JButton(text);
		    button.setForeground(new Color(255, 255, 255));
		    button.setBackground(backgroundColor);
		    button.setBounds(x, y, width, height); // Cambia esta línea para usar las coordenadas x y y pasadas como parámetros

		    ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
		    Image image = imageIcon.getImage();
		    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		    ImageIcon scaledIcon = new ImageIcon(scaledImage);

		    button.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseEntered(MouseEvent e) {
		            button.setText(""); 
		            button.setIcon(scaledIcon); 
		        }

		        @Override
		        public void mouseExited(MouseEvent e) {
		            button.setText(text); 
		            button.setIcon(null); 
		        }
		    });

		    return button;
		}

		
		public JFrame getFrame() {
		    return frame;
		}
		
		public void mostrarMenu() { 
		    frame.setVisible(true);
		}
		
		public void close() {
		    frame.dispose();
		}
}
