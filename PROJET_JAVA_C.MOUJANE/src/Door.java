public class Door {
    private int x, y; // Position de la porte
    private String nextLevel; // Niveau suivant auquel la porte mène

    public Door(int x, int y, String nextLevel) {
        this.x = x;
        this.y = y;
        this.nextLevel = nextLevel;
    }

    // Méthode pour vérifier si le joueur est proche de la porte
    public boolean isPlayerInRange(DynamicSprite player) {
        // Utilisation des getters pour obtenir les coordonnées du joueur
        return player.getX() > x - 10 && player.getX() < x + 10
                && player.getY() > y - 10 && player.getY() < y + 10;
    }

    public String getNextLevel() {
        return nextLevel;
    }
}
