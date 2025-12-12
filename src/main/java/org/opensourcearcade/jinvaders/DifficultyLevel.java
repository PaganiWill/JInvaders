package org.opensourcearcade.jinvaders;

public enum DifficultyLevel {
    EASY(5, "EASY"),
    MEDIUM(3, "MEDIUM"),
    HARD(2, "HARD"),
    SUPREME(1, "SUPREME");

    private final int lives;
    private final String displayName;

    DifficultyLevel(int lives, String displayName) {
        this.lives = lives;
        this.displayName = displayName;
    }

    public int getLives() {
        return lives;
    }

    public String getDisplayName() {
        return displayName;
    }
}

