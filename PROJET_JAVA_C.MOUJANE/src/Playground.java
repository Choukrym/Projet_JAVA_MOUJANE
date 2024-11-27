import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Playground {
    private ArrayList<Sprite> environment = new ArrayList<>();
    private ArrayList<Door> doors = new ArrayList<>(); // Liste pour stocker les portes

    public Playground(String pathName) {
        try {
            // Chargement des images des objets
            final Image imageTree = ImageIO.read(new File("./img/tree.png"));
            final Image imageGrass = ImageIO.read(new File("./img/grass.png"));
            final Image imageRock = ImageIO.read(new File("./img/rock.png"));
            final Image imageTrap = ImageIO.read(new File("./img/trap.png"));

            final int imageTreeWidth = imageTree.getWidth(null);
            final int imageTreeHeight = imageTree.getHeight(null);

            final int imageGrassWidth = imageGrass.getWidth(null);
            final int imageGrassHeight = imageGrass.getHeight(null);

            final int imageRockWidth = imageRock.getWidth(null);
            final int imageRockHeight = imageRock.getHeight(null);

            // Lecture du fichier de niveau
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName));
            String line = bufferedReader.readLine();
            int lineNumber = 0;
            int columnNumber = 0;

            // Parcours du fichier ligne par ligne
            while (line != null) {
                for (byte element : line.getBytes(StandardCharsets.UTF_8)) {
                    switch (element) {
                        case 'T': // Arbre
                            environment.add(new SolidSprite(columnNumber * imageTreeWidth,
                                    lineNumber * imageTreeHeight, imageTree, imageTreeWidth, imageTreeHeight));
                            break;
                        case ' ' : // Herbe
                            environment.add(new Sprite(columnNumber * imageGrassWidth,
                                    lineNumber * imageGrassHeight, imageGrass, imageGrassWidth, imageGrassHeight));
                            break;
                        case 'R' : // Rocher
                            environment.add(new SolidSprite(columnNumber * imageRockWidth,
                                    lineNumber * imageRockHeight, imageRock, imageRockWidth, imageRockHeight));
                            break;
                        case 'D': // Détection d'une porte
                            // Séparer les parties de la ligne et vérifier que la ligne a le bon format
                            String[] parts = line.split("\\s+");
                            if (parts.length >= 4) {
                                try {
                                    int doorX = Integer.parseInt(parts[1]);
                                    int doorY = Integer.parseInt(parts[2]);
                                    String nextLevel = parts[3];
                                    doors.add(new Door(doorX * imageTreeWidth, doorY * imageTreeHeight, nextLevel));
                                } catch (NumberFormatException e) {
                                    System.out.println("Erreur de format pour les coordonnées de la porte : " + line);
                                }
                            } else {
                                System.out.println("Format invalide pour la porte : " + line);
                            }
                            break;

                    }
                    columnNumber++;
                }
                columnNumber = 0;
                lineNumber++;
                line = bufferedReader.readLine(); // Lire la ligne suivante
            }
        } catch (Exception e) {
            e.printStackTrace(); // Affichage de l'erreur si quelque chose échoue
        }
    }

    // Getter pour obtenir la liste des portes
    public ArrayList<Door> getDoors() {
        return doors;
    }

    // Méthode pour obtenir les sprites solides
    public ArrayList<Sprite> getSolidSpriteList() {
        ArrayList<Sprite> solidSpriteArrayList = new ArrayList<>();
        for (Sprite sprite : environment) {
            if (sprite instanceof SolidSprite) solidSpriteArrayList.add(sprite);
        }
        return solidSpriteArrayList;
    }

    // Méthode pour obtenir la liste des objets affichables (sprites)
    public ArrayList<Displayable> getSpriteList() {
        ArrayList<Displayable> displayableArrayList = new ArrayList<>();
        for (Sprite sprite : environment) {
            displayableArrayList.add((Displayable) sprite);
        }
        return displayableArrayList;
    }
}
