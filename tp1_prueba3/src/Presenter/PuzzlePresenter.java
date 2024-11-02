package Presenter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import Model.PuzzleModel;
import View.PuzzleView;
import View.Menu;
import View.MenuDif;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class PuzzlePresenter {
		private Menu menu;
		private MenuDif mndif;
		private PuzzleModel model;
	    private PuzzleView view;
	    private int moveCount;
	    private Timer timer;
	    private boolean isPaused; 
	    private String dificultad, modo;
	    private int facil = 40, medio = 30, dificil = 15, tiempoRestante=0;

	    public PuzzlePresenter(Menu mn) {
	    	this.menu=mn;
	    	menu.mostrarMenu();
	        setActionBtnMenu();
	    }
	   
	    private void setActionBtnMenu() {
	        setActionBtn123();
	        setActionBtnABC();
	    }

	    private void setActionBtn123() {
	        this.menu.addAccionBtn123(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                playSound("/img/inicio.wav");
	                menu.close();
	                modo="123";
	                mostrarMenuDificultad();
	            }
	        });
	    }

	    private void setActionBtnABC() {
	        this.menu.addAccionBtnABC(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                playSound("/img/inicio.wav");
	                menu.close();
	                modo="abc";
	                mostrarMenuDificultad();
	            }
	        });
	    }
	    
	    private void mostrarMenuDificultad() {
	        if (mndif == null) {
	        	mndif = new MenuDif(modo);
	            setActionBtnMenuDif();
	        }
	        mndif.mostrarMenu(); 
	    }
	    
	    private void setActionBtnMenuDif() {
	        setAccionBtnStartMenuDificultad();
	        setAccionBtnBackMenuDificultad();
	    }

	    private void setAccionBtnBackMenuDificultad() {
	    	mndif.addActionButtonBack(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	mndif.close();
	                modo = null;
	                dificultad = null;
	                menu.mostrarMenu();
	            }
	        });
		}

	    private void setAccionBtnStartMenuDificultad() {
	        if (mndif != null) {
	            this.mndif.addActionButtonPlay(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                	mndif.close();
	                    dificultad=mndif.getDif();
	                    initializeGame();
	                }
	            });
	        }
	    }

	    private void initializeGame() {
	    	this.view = new PuzzleView();
	        setTime(dificultad);
	        startTimer();  
	    	view.setInfo(modo, dificultad);
	        this.view.setButtonAction(new ButtonClickListener());
	        boolean isAlphabet = modo.equals("abc");
	        model = new PuzzleModel(isAlphabet);
	        moveCount = 0;
	        updateView();
	        isPaused = false; // Inicialmente no está en pausa
	    }
	    
	    public void setTime(String dificultad) {
	        switch (dificultad) {
	            case "Facil":
	                tiempoRestante = facil * 60; // 40 minutos en segundos
	                break;
	            case "Medio":
	                tiempoRestante = medio * 60; // 30 minutos en segundos
	                break;
	            case "Dificil":
	                tiempoRestante = dificil * 60; // 15 minutos en segundos
	                break;
	            default:
	                throw new IllegalArgumentException("Dificultad no válida");
	        }
	        view.updateTimer(tiempoRestante); // Actualizar la vista con el tiempo inicial
	    }

	    private void startTimer() {
	        timer = new Timer(1000, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (!isPaused) { // Solo actualiza el tiempo si no está en pausa
	                    tiempoRestante--;
	                    view.updateTimer(tiempoRestante); // Actualizar el temporizador en la vista

	                    if (tiempoRestante == 0) { // Verificar si se acabó el tiempo
	                        timer.stop(); // Detener el temporizador
	                        gameOver(); // Llamar al método gameOver
	                    }else {
	                    	if(model.isSolved()) {
	                    		timer.stop(); // Detener el temporizador
	                            gameWin(); // Llamar al método de victoria
	                    	}
	                    }
	                }
	            }
	        });
	        timer.start();
	    }

	    private void updateView() {
	        for (int i = 0; i < model.getTiles().size(); i++) {
	            view.updateTile(i, model.getTiles().get(i));
	            view.setTileEnabled(i, !model.getTiles().get(i).isEmpty());
	        }
	        view.updateMoveCount(moveCount);
	        view.activateKey();
	    }

	    private class ButtonClickListener implements ActionListener {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            for (int i = 0; i < view.getButtons().length; i++) {
	                if (e.getSource() == view.getButtons()[i]) {
	                    if (!isPaused && model.moveTile(i)) {
	                        moveCount++;
	                        updateView();
	                    }
	                    break;
	                }
	            }
	            
	            if (e.getSource() == view.getUndoButton()) {
	                if (!isPaused && model.undoMove()) {
	                    moveCount--;
	                    updateView();
	                }
	            }

	            if (e.getSource() == view.getUpButton()) {
	                if (!isPaused && model.moveTileUp()) {
	                    moveCount++;
	                    updateView();
	                }
	            } else if (e.getSource() == view.getDownButton()) {
	                if (!isPaused && model.moveTileDown()) {
	                    moveCount++;
	                    updateView();
	                }
	            } else if (e.getSource() == view.getLeftButton()) {
	                if (!isPaused && model.moveTileLeft()) {
	                    moveCount++;
	                    updateView();
	                }
	            } else if (e.getSource() == view.getRightButton()) {
	                if (!isPaused && model.moveTileRight()) {
	                    moveCount++;
	                    updateView();
	                }
	            }

	            if (e.getSource() == view.getPauseButton()) {
	                isPaused = !isPaused;
	                view.setGameEnabled(!isPaused);
	                view.updatePauseButton(isPaused ? "Reanudar" : "Pausar");
	                view.changeColorButtonPause(isPaused ? "Reanudar" : "Pausar");
	                view.activateKey();
	            }

	            if (e.getSource() == view.getBtnResetListener()) {
	                if (view != null) {
	                    view.closeWindow();
	                }
	                initializeGame();
	            }

	            if (e.getSource() == view.getBtnVolverListener()) {
	                view.closeWindow();
	                volverMenu();
	            }
	        }
	    }

	    private void volverMenu() {
	        if (mndif != null) {
	            mndif.close();
	            mndif = null;
	        }
	        menu.mostrarMenu();
	    }

	    private void playSound(String soundFilePath) {
	        try {
	            InputStream audioSrc = getClass().getResourceAsStream(soundFilePath);
	            if (audioSrc == null) {
	                System.out.println("No se encontró el archivo de sonido: " + soundFilePath);
	                return;
	            }
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioSrc);

	            Clip clip = AudioSystem.getClip();
	            clip.open(audioInputStream);
	            clip.start();

	            clip.addLineListener(event -> {
	                if (event.getType() == LineEvent.Type.STOP) {
	                    clip.close();
	                }
	            });
	        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    private void Special_msm(String tit,String msm) {
			JOptionPane optionPane = new JOptionPane(
					    tit,
					    JOptionPane.INFORMATION_MESSAGE,
					    JOptionPane.DEFAULT_OPTION,
					    null,
					    new Object[]{},  // Arreglo vacío para no mostrar botones
					    null
					);

					// Crear el cuadro de diálogo sin botones
					JDialog dialog = optionPane.createDialog(msm);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

					dialog.setVisible(true);
		}
	   

	    public void gameOver() {
	        Special_msm("¡Se acabó el tiempo! Has perdido el juego", "Loser");
	        view.setGameEnabled(false);
	        view.blockArrows();
	        playSound("/img/YOU-LOSE.wav");
	    }

	    public void gameWin() {
	        playSound("/img/YouWin.wav");
	        view.setGameEnabled(false);
	        view.blockArrows();
	        Special_msm("¡Has ganado el juego!", "Winner");
	    }
}
