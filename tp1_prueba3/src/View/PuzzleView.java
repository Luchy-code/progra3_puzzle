package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PuzzleView {
	private JFrame frame;
    private JPanel panel;
    private JButton[] buttons;
    private JButton pauseButton, upButton, downButton, leftButton, rightButton;
    private JMenuItem undoButton, btnReset, btnVolver;
    private JLabel moveCountLabel, timerLabel;
    private JLabel lblModo, lblDif;

    public PuzzleView() {
            frame = new JFrame("Puzzle 15");
            frame.setSize(600, 600);
            frame.setResizable(false); // Evita que se pueda redimensionar
            frame.getContentPane().setLayout(new BorderLayout()); 

            panel = new JPanel(new GridLayout(4, 4)); 
            setButtons(new JButton[16]);
            
            pauseButton = new JButton("Pause");
            pauseButton.setBackground(Color.red);
            
            upButton = new JButton("↑");
            upButton.setBackground(new Color(224, 255, 255));
            downButton = new JButton("↓");
            downButton.setBackground(new Color(224, 255, 255));
            leftButton = new JButton("←");
            leftButton.setBackground(new Color(224, 255, 255));
            rightButton = new JButton("→");
            rightButton.setBackground(new Color(224, 255, 255));

            setUndoButton(new JMenuItem("Deshacer Movimiento"));
            moveCountLabel = new JLabel("Movimientos: 0");
            timerLabel = new JLabel("Tiempo: 0");

            for (int i = 0; i < getButtons().length; i++) {
                getButtons()[i] = new JButton();
                panel.add(getButtons()[i]);
            }

            // Crear panel de control
            JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
            menuBar(controlPanel);

            // Agregar componentes al panel de control
            controlPanel.add(moveCountLabel);
            controlPanel.add(timerLabel);
            controlPanel.add(pauseButton);

            // Panel de dirección
            JPanel directionPanel = new JPanel();
            directionPanel.setLayout(new BorderLayout()); // BorderLayout para mejor distribución

            // Panel de información
            JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            lblModo = new JLabel("Modo: ");
            lblDif = new JLabel("Dificultad: ");
            infoPanel.add(lblModo);
            infoPanel.add(new JSeparator(SwingConstants.VERTICAL)); // Separador entre etiquetas
            infoPanel.add(lblDif);

            // Panel de botones de dirección
            JPanel arrowPanel = new JPanel(new GridLayout(3, 3, 5, 5));
            arrowPanel.add(new JLabel("")); // Espacio vacío
            arrowPanel.add(upButton);
            arrowPanel.add(new JLabel("")); // Espacio vacío
            arrowPanel.add(leftButton);
            arrowPanel.add(new JLabel("")); // Centro vacío
            arrowPanel.add(rightButton);
            arrowPanel.add(new JLabel("")); // Espacio vacío
            arrowPanel.add(downButton);
            arrowPanel.add(new JLabel("")); // Espacio vacío

            upButton.setPreferredSize(new Dimension(30, 30));
            downButton.setPreferredSize(new Dimension(30, 30));
            leftButton.setPreferredSize(new Dimension(30, 30));
            rightButton.setPreferredSize(new Dimension(30, 30));

            directionPanel.add(infoPanel, BorderLayout.NORTH); // Coloca infoPanel arriba
            directionPanel.add(arrowPanel, BorderLayout.CENTER); // Coloca arrowPanel en el centro

            // Agregar los paneles al JFrame
            frame.getContentPane().add(panel, BorderLayout.CENTER);
            frame.getContentPane().add(controlPanel, BorderLayout.NORTH); // Mover el panel de control a la parte superior
            frame.getContentPane().add(directionPanel, BorderLayout.EAST); // Panel de dirección a la derecha
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true); // Mostrar la ventana

            // Configurar teclado
            frame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    handleKeyPress(e);
                }
            });

            activateKey(); 
        }
    
        private void menuBar(JPanel controlPanel) {
            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("Menú");
            menu.setBackground(Color.PINK);

            btnReset = new JMenuItem("Resetear");
            undoButton = new JMenuItem("Deshacer");
            btnVolver = new JMenuItem("Volver");

            menu.add(btnReset);
            menu.add(undoButton);
            menu.add(btnVolver);
            menuBar.add(menu);

            controlPanel.add(menuBar); // Añadir el JMenuBar al panel de control
        }

        public void setInfo(String modo, String dif) {
            lblModo.setText("Modo: " + modo);
            lblDif.setText("Dificultad: " + dif);
        }

    
    public void setBottones() {
        JPanel controlPanel = new JPanel();
        controlPanel.add(undoButton);
        controlPanel.add(pauseButton);
        controlPanel.add(moveCountLabel);
        controlPanel.add(timerLabel);
        
        frame.getContentPane().add(controlPanel, BorderLayout.NORTH); 
        frame.setVisible(true);
    }
    
    public void blockArrows() {
    	upButton.setEnabled(false);
    	downButton.setEnabled(false);
    	leftButton.setEnabled(false);
    	rightButton.setEnabled(false);
    }

    private void handleKeyPress(KeyEvent e) {
        int keyCode = e.getKeyCode();
        //doClick(); Simula el clic en el botón de arriba
        if (keyCode == KeyEvent.VK_UP) {
            upButton.doClick();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            downButton.doClick(); 
        } else if (keyCode == KeyEvent.VK_LEFT) {
            leftButton.doClick(); 
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            rightButton.doClick();
        } else if (keyCode == KeyEvent.VK_W) {
            upButton.doClick();
        } else if (keyCode == KeyEvent.VK_S) {
            downButton.doClick();
        } else if (keyCode == KeyEvent.VK_A) {
            leftButton.doClick(); 
        } else if (keyCode == KeyEvent.VK_D) {
            rightButton.doClick();
        }
    }

    public void setButtonAction(ActionListener actionListener) {
        for (JButton button : buttons) {
            button.addActionListener(actionListener);
        }
        undoButton.addActionListener(actionListener);
        pauseButton.addActionListener(actionListener);
        upButton.addActionListener(actionListener); 
        downButton.addActionListener(actionListener);
        leftButton.addActionListener(actionListener); 
        rightButton.addActionListener(actionListener); 
        btnReset.addActionListener(actionListener);
        undoButton.addActionListener(actionListener);
        btnVolver.addActionListener(actionListener);
    }

    public void updateTile(int index, String value) {
        getButtons()[index].setText(value);
        getButtons()[index].setFont(new Font("Arial", Font.BOLD, 24));
        getButtons()[index].setBackground(Color.white);
    }

    public void resetGame() {
        for (JButton button : getButtons()) {
            button.setEnabled(true);
            button.setText("");
        }
        moveCountLabel.setText("Movimientos: 0");
        timerLabel.setText("Tiempo: 0");
    }

    public void setGameEnabled(boolean enabled) {
        for (JButton button : buttons) {
            button.setEnabled(enabled);
        }
        undoButton.setEnabled(enabled);
    }
    
    public void activateKey() {
    	 frame.requestFocusInWindow();
    }
    
    public void changeColorButtonPause(String text) {
    	if (text.equals("Pausar")) {
            pauseButton.setBackground(Color.RED); // Fondo verde para "Reanudar"
        } else {
            pauseButton.setBackground(Color.GREEN); // Fondo rojo para "Pause"
        }
    }

    public void updatePauseButton(String text) {
        getPauseButton().setText(text); // Cambiar texto del botón de pausa/play
    }

    public void setTileEnabled(int index, boolean enabled) {
        getButtons()[index].setEnabled(enabled);
    }

    public void updateMoveCount(int count) {
        moveCountLabel.setText("Movimientos: " + count);
    }

    public void updateTimer(int seconds) {
        int minutes = seconds / 60; // Calcular minutos
        int remainingSeconds = seconds % 60; // Calcular segundos restantes
        timerLabel.setText(String.format("Tiempo: %02d:%02d", minutes, remainingSeconds)); // Formato mm:ss
    }


    public JButton[] getButtons() {
        return buttons;
    }

    public void setButtons(JButton[] buttons) {
        this.buttons = buttons;
    }

    public JMenuItem getUndoButton() {
        return undoButton;
    }

    public JButton getUpButton() {
        return upButton;
    }

    public JButton getDownButton() {
        return downButton;
    }

    public JButton getLeftButton() {
        return leftButton;
    }

    public JButton getRightButton() {
        return rightButton;
    }
    public void setRightButton(JButton rightButton) {
        this.rightButton = rightButton;
    }
   
    public JMenuItem getBtnResetListener() {
        return btnReset;
    }
    
    public JMenuItem getBtnVolverListener() {
        return btnVolver;
    }
    
    public void setUndoButton(JMenuItem undoButton) {
        this.undoButton = undoButton;
    }

    public JButton getPauseButton() {
        return pauseButton;
    }
    
    public void play() {
        frame.setVisible(true);
    }
    
	public void closeWindow() {
		frame.dispose();
	}

}
