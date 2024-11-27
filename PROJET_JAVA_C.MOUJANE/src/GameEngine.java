import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements Engine, KeyListener {
    DynamicSprite hero;
    private boolean isSprinting = false; // Variable pour suivre l'état de sprint

    public GameEngine(DynamicSprite hero) {
        this.hero = hero;
    }

    @Override
    public void update() {
        // Logique de mise à jour supplémentaire

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Non utilisé dans ce cas
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
                break;
            case KeyEvent.VK_CONTROL: // Mode sprint activé
                if (!isSprinting) {
                    isSprinting = true;
                    hero.setSpeed(hero.getSpeed() * 2); // Double la vitesse
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            if (isSprinting) {
                isSprinting = false;
                hero.setSpeed(hero.getSpeed() / 2);
            }
        }
    }


}
