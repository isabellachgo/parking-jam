package es.upm.pproject.parkingjam.parking_jam.view;

import java.awt.*;

import javax.swing.*;

import es.upm.pproject.parkingjam.parking_jam.controller.Controller;
import es.upm.pproject.parkingjam.parking_jam.model.Game;
import javafx.util.Pair;

public class EndGameView {
    private JFrame frame;
    private Game game;
    private Controller cont;

    public EndGameView(JFrame frame, Game g, Controller cont) {
        this.frame = frame;
        this.cont = cont;
        this.game = g;
        initEG();
        this.frame.setVisible(true);
    }

    // Builds the elements and the structure of the view
    private void initEG() {
        // Dimensions:
        Dimension buttonSize = new Dimension(60, 60);
        
        // Colors:
        Color bg = new Color(180, 220, 110);
        Color buttonColor = new Color(39, 193, 245); // Azul
        Color winPColor = new Color(180,220,110);

        // Icons:
        ImageIcon parkingIcon = new ImageIcon(getClass().getResource("/images/finalParking.png"));
        ImageIcon starIcon = Factory.resizeIcon(new ImageIcon(getClass().getResource("/icons/yellowstar.png")), 50, 50);
        ImageIcon starIcon2 = Factory.rotateIcon(starIcon, 180);
        ImageIcon saveMIcon = Factory.resizeIcon(new ImageIcon(getClass().getResource("/icons/save.png")),30,30);
        ImageIcon homeMIcon = Factory.resizeIcon(new ImageIcon(getClass().getResource("/icons/home.png")),30,30);
        ImageIcon closeMIcon = Factory.resizeIcon(new ImageIcon(getClass().getResource("/icons/close.png")),30,30);
        
        // Images:
        Image parkingImage = parkingIcon.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);

        // Elements:
        JLabel titleL = new JLabel();
        titleL.setText("Parking Jam");
        titleL.setFont(Factory.titleFont);
        titleL.setHorizontalAlignment(SwingConstants.CENTER);

        // Structure:
        JPanel panel = new JPanel();
        panel.setBackground(bg);
        panel.setLayout(new BorderLayout());

        JLayeredPane panelCenter = new JLayeredPane();
        panelCenter.setPreferredSize(new Dimension(900, 900));

        JPanel panelBg = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(parkingImage, 50, 30, this);
            }
        };
        panelBg.setBackground(bg);
        panelBg.setBounds(0, 0, 900, 900);

        JPanel winPanel = new JPanel();
        winPanel.setLayout(new BoxLayout(winPanel, BoxLayout.Y_AXIS));
        winPanel.setBackground(winPColor);
        winPanel.setBounds(123, 230, 450, 300);

       
        JLabel winL = new JLabel("Congratulations!");
        winL.setFont(Factory.titleFont);
        winL.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel info = new JLabel("<html><div style='text-align: center;'>You have completed all the levels in the game '" + game.getName() + "'</div></html>");
        info.setFont(Factory.infoFont);
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        info.setPreferredSize(new Dimension(400, 90));  // Set preferred size to ensure it fits

        JLabel pointsWL = new JLabel(game.getGamePoints().toString());
        pointsWL.setFont(Factory.levelPointsFont);
        pointsWL.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel star1W = new JLabel(starIcon);
        JLabel star2W = new JLabel(starIcon2);
        JPanel starPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        starPanel.setBackground(winPColor);
        starPanel.add(star1W);
        starPanel.add(pointsWL);
        starPanel.add(star2W);

        JPanel buttonpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonpanel.setBackground(winPColor);

        JButton gamesB = new JButton(homeMIcon);
        Factory.setFormatButton(gamesB, null, buttonSize, null, new Pair<>(null, buttonColor), null, null);       
        gamesB.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamesB.addActionListener(e ->{
                frame.getContentPane().removeAll();
                cont.gamesMenuButton();
        });

        JButton closeB = new JButton(closeMIcon);
        Factory.setFormatButton(closeB, null, buttonSize, null, new Pair<>(null, buttonColor), null, null);
        closeB.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeB.addActionListener(e -> {
                frame.dispose();
                System.exit(0);
        });
        JButton saveB = new JButton(saveMIcon);
        Factory.setFormatButton(saveB, null, buttonSize, null, new Pair<>(Color.white, buttonColor), null, SwingConstants.LEFT);
		saveB.addActionListener(e -> 				
				cont.saveGame()
		);

        buttonpanel.add(gamesB);
        buttonpanel.add(closeB);
        buttonpanel.add(saveB);
        winPanel.add(Box.createVerticalGlue());
        winPanel.add(winL);
        winPanel.add(info);
        winPanel.add(starPanel);
        winPanel.add(buttonpanel);
        winPanel.add(Box.createVerticalGlue());

        panelCenter.add(panelBg, JLayeredPane.DEFAULT_LAYER);
        panelCenter.add(winPanel, JLayeredPane.PALETTE_LAYER);

        panel.add(panelCenter, BorderLayout.CENTER);
        frame.add(panel);
        Factory.playSound("src/main/resources/sounds/winGame.wav");
    }

    
 
}