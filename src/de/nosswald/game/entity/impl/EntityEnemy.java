package de.nosswald.game.entity.impl;

import de.nosswald.game.entity.Entity;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public abstract class EntityEnemy extends Entity
{
    public final int maxHealth;

    protected String name;

    /**
     * @param health    health
     * @param damage    base damage
     * @param name      display name
     */
    public EntityEnemy(int health, int damage, String name)
    {
        super(health, damage);

        maxHealth = health;
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }
}
