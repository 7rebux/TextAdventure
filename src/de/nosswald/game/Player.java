package de.nosswald.game;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class Player
{
    private int health;
    private int mana;

    // TODO replace with Item class (health pot..)
    private String[] inventory;

    public Player()
    {
        // default values
        this.health = 87;
        this.mana = 43;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana()
    {
        return mana;
    }

    public void setMana(int mana)
    {
        this.mana = mana;
    }
}
