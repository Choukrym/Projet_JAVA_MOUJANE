/*import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
    private static RenderEngine renderEngine;
    private static PhysicEngine physicEngine;
    private static GameEngine gameEngine;

    public static void main(String[] args) throws Exception {


        // Créer la fenêtre principale pour l'écran titre
        JFrame displayZoneFrame = new JFrame("Dungeon Crawler");
        displayZoneFrame.setSize(800, 600);
        displayZoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        displayZoneFrame.setLocationRelativeTo(null);

        //iiiii
        displayZoneFrame.addKeyListener(gameEngine);  // Ajouter l'écouteur d'événements clavier
        displayZoneFrame.setFocusable(true);          // Assurer que la fenêtre est focusable


        // Afficher l'écran titre avant de démarrer le jeu
        showTitleScreen(displayZoneFrame);

        // Attendre que l'utilisateur commence le jeu
        // Dès que l'utilisateur clique, on démarre le jeu
    }

    // Méthode pour afficher l'écran titre
    private static void showTitleScreen(JFrame displayZoneFrame) {
        // Créer un panel pour l'écran titre
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.PINK);

        // Ajouter un titre
        JLabel titleLabel = new JLabel("JEU DU GOAT", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 50));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel, BorderLayout.CENTER);

        // Ajouter un bouton pour commencer le jeu
        JButton startButton = new JButton("Commencer l'aventure");
        startButton.setFont(new Font("Serif", Font.PLAIN, 20));
        startButton.setBackground(Color.DARK_GRAY);
        startButton.setForeground(Color.WHITE);
        panel.add(startButton, BorderLayout.SOUTH);

        // Ajouter le panel à la fenêtre
        displayZoneFrame.getContentPane().add(panel);
        displayZoneFrame.setVisible(true);

        // Action au clic sur le bouton pour démarrer le jeu
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lancer le jeu
                displayZoneFrame.getContentPane().removeAll(); // Retirer l'écran titre
                startGame(displayZoneFrame); // Démarrer le jeu
            }
        });
    }

    // Méthode pour démarrer le jeu après l'écran titre
    private static void startGame(JFrame displayZoneFrame) {
        // Création du héros (Sprite dynamique)
        DynamicSprite hero = null;
        try {
            hero = new DynamicSprite(200, 300,
                    ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Initialisation des moteurs (rendu, physique, jeu)
        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero);

        // Créer les timers pour mettre à jour les moteurs
        Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
        Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
        Timer physicTimer = new Timer(50, (time) -> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        // Ajouter le moteur de rendu à la fenêtre
        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        // Charger le niveau et les sprites
        Playground level = new Playground("./data/level1.txt");
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());

        // Ajouter un KeyListener pour les entrées clavier
        // Assurez-vous que le KeyListener est ajouté au bon composant
        // Ici, on l'ajoute au renderEngine, car c'est là que vous gérez probablement le jeu
        renderEngine.addKeyListener(gameEngine);
        renderEngine.setFocusable(true);
        renderEngine.requestFocusInWindow();  // Assurez-vous que le focus est sur le composant
    }
}
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.ArrayList;

public class Main {
    private static RenderEngine renderEngine;
    private static PhysicEngine physicEngine;
    private static GameEngine gameEngine;

    private static int frameCount = 0; // Compteur de frames
    private static long lastTime = System.currentTimeMillis(); // Temps de la dernière mise à jour du FPS

    public static void main(String[] args) throws Exception
    {






        // Créer la fenêtre principale pour l'écran titre
        JFrame displayZoneFrame = new JFrame("Dungeon Crawler");
        displayZoneFrame.setSize(1450, 650);
        displayZoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        displayZoneFrame.setLocationRelativeTo(null);

        // Afficher l'écran titre avant de démarrer le jeu
        showTitleScreen(displayZoneFrame);
    }

    // Méthode pour afficher l'écran titre
    private static void showTitleScreen(JFrame displayZoneFrame) {
        // Créer un panel pour l'écran titre
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.blue);

        // Ajouter un titre
        JLabel titleLabel = new JLabel("JEU DE CHOUKRY", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 50));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel, BorderLayout.CENTER);

        // Ajouter un bouton pour commencer le jeu
        JButton startButton = new JButton("Commencer l'aventure");
        startButton.setFont(new Font("Serif", Font.PLAIN, 20));
        startButton.setBackground(Color.DARK_GRAY);
        startButton.setForeground(Color.WHITE);
        panel.add(startButton, BorderLayout.SOUTH);

        // Ajouter le panel à la fenêtre
        displayZoneFrame.getContentPane().add(panel);
        displayZoneFrame.setVisible(true);

        // Action au clic sur le bouton pour démarrer le jeu
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lancer le jeu
                displayZoneFrame.getContentPane().removeAll();
                startGame(displayZoneFrame); // Démarrer le jeu
            }
        });
    }

    // Méthode pour démarrer le jeu après l'écran titre
    private static void startGame(JFrame displayZoneFrame) {

        DynamicSprite hero = null;
        try {
            hero = new DynamicSprite(200, 300,
                    ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Initialisation des moteurs (rendu, physique, jeu)
        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero);

        // Créer les timers pour mettre à jour les moteurs
        Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
        Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
        Timer physicTimer = new Timer(50, (time) -> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        // Ajouter le moteur de rendu à la fenêtre
        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        // Charger le niveau et les sprites
        Playground level = new Playground("./data/level1.txt");
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());

        // Ajouter un KeyListener pour les entrées clavier
        renderEngine.addKeyListener(gameEngine);
        renderEngine.setFocusable(true);
        renderEngine.requestFocusInWindow();  // Assurez-vous que le focus est sur le composant
    }

    public void loadNextLevel(String levelName) {
        // Logique pour charger le niveau suivant
        System.out.println("Chargement du niveau : " + levelName);
    }

    // Méthode pour calculer et afficher le FPS dans le coin supérieur droit

    public static void displayFPS(Graphics g) {

        frameCount++;
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime >= 1000) {  // Si une seconde s'est écoulée
            int fps = frameCount;  // Nb de frames 1 seconde
            frameCount = 0;
            lastTime = currentTime;  // Réinitialiser le dernier temps
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString("FPS: " + fps, 700, 20);
        }
    }
    public void updateLevel(ArrayList<Door> doors, DynamicSprite player) {
        for (Door door : doors) {
            // Vérifie si le joueur est proche de la porte
            if (door.isPlayerInRange(player)) {
                // Si le joueur est près de la porte, on passe au niveau suivant
                String nextLevelPath = door.getNextLevel();
                loadNextLevel(nextLevelPath); // Charge le niveau suivant
            }
        }
    }

}


