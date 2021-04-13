package de.nosswald.game.entity;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class Entity
{
    private int health;
    private int damage;

    public Entity(int health, int damage)
    {
        this.health = health;
        this.damage = damage;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public int getDamage()
    {
        return damage;
    }

    public void setDamage(int damage)
    {
        this.damage = damage;
    }
}
