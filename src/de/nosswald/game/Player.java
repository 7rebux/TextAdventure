package de.nosswald.game;

import de.nosswald.game.item.Item;

import java.util.ArrayList;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class Player
{
    private int health;
    private int mana;
    private final ArrayList<Item> inventory = new ArrayList<>();

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

    public void setHealth(int health)
    {
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

    public ArrayList<Item> getInventory()
    {
        return inventory;
    }
}
