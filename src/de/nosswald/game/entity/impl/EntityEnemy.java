package de.nosswald.game.entity.impl;

import de.nosswald.game.entity.Entity;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class EntityEnemy extends Entity
{
    private String name;

    public EntityEnemy(int health, int damage, String name)
    {
        super(health, damage);

        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void onDeath() { }
}
