package de.nosswald.game.entity;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public abstract class Entity
{
    protected int health;
    protected int damage;

    public Entity(int health, int damage)
    {
        this.health = health;
        this.damage = damage;
    }

    public void attack(Entity entity)
    {
        entity.setHealth(entity.getHealth() - getDamage());
        if (entity.getHealth() <= 0) entity.onDeath();
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

    public abstract void onDeath();
}
