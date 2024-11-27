import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite {
    private Direction direction = Direction.EAST;
    private double speed = 5; // Vitesse de déplacement
    private double timeBetweenFrame = 250;
    private final int spriteSheetNumberOfColumn = 10;

    private int maxHealth = 100; // Santé max
    private int currentHealth = 100; // Santé actuelle


    public DynamicSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double newSpeed) {
        this.speed = newSpeed;
    }

    public void decreaseHealth(int amount) {

        currentHealth -= amount;
        if (currentHealth < 0) currentHealth = 0; // la santé ne peux pas descendre à - de 0
    }

    public void increaseHealth(int amount) {
        // Augmente la santé du personnage
        currentHealth += amount;
        if (currentHealth > maxHealth) currentHealth = maxHealth; // Limite à la santé maximale
    }

    public double getHealth() {
        // Retourne la santé sous forme de ratio (0.0 à 1.0)
        return (double) currentHealth / maxHealth;
    }

    public void setHealth(int health) {
        // Définit la santé en pourcentage (entre 0 et 100)
        if (health < 0) health = 0;
        if (health > maxHealth) health = maxHealth;
        this.currentHealth = health;
    }

    private boolean isMovingPossible(ArrayList<Sprite> environment) {
        // Vérifie si le déplacement est possible sans collision
        Rectangle2D.Double moved = new Rectangle2D.Double();
        switch (direction) {
            case EAST:
                moved.setRect(super.getHitBox().getX() + speed, super.getHitBox().getY(),
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case WEST:
                moved.setRect(super.getHitBox().getX() - speed, super.getHitBox().getY(),
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case NORTH:
                moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() - speed,
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case SOUTH:
                moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() + speed,
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
        }

        for (Sprite s : environment) {
            if ((s instanceof SolidSprite) && (s != this)) {
                if (((SolidSprite) s).intersect(moved)) {
                    return false;
                }
            }
        }
        return true; // Déplacement possible
    }

    private void move() {
        // Effectue le déplacement en fonction de la direction actuelle
        switch (direction) {
            case NORTH -> this.y -= speed;
            case SOUTH -> this.y += speed;
            case EAST -> this.x += speed;
            case WEST -> this.x -= speed;
        }
    }

    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (isMovingPossible(environment)) {
            move();
        }
    }

    @Override
    public void draw(Graphics g) {
        // Dessin de l'animation du personnage
        int index = (int) (System.currentTimeMillis() / timeBetweenFrame % spriteSheetNumberOfColumn);

        g.drawImage(image, (int) x, (int) y, (int) (x + width), (int) (y + height),
                (int) (index * this.width), (int) (direction.getFrameLineNumber() * height),
                (int) ((index + 1) * this.width), (int) ((direction.getFrameLineNumber() + 1) * this.height), null);

        // Dessin de la barre de vie au-dessus du personnage
        drawHealthBar(g);
    }

    private void drawHealthBar(Graphics g) {

        int barWidth = 50; // Large barre
        int barHeight = 5; // Haut barre
        int xBar = (int) (x + (width - barWidth) / 2); // Pos. horiz
        int yBar = (int) (y - 10);

        double healthRatio = getHealth(); // Récupère la santé en ratio

        // Fond rouge
        g.setColor(Color.RED);
        g.fillRect(xBar, yBar, barWidth, barHeight);

        // Barre verte selon le ratio
        g.setColor(Color.GREEN);
        g.fillRect(xBar, yBar, (int) (barWidth * healthRatio), barHeight);

        // Contour noir
        g.setColor(Color.BLACK);
        g.drawRect(xBar, yBar, barWidth, barHeight);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
