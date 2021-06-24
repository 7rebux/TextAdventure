package de.nosswald.game.entity;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public abstract class Entity
{
    protected int health;
    protected int damage;

    /**
     * @param health default health
     * @param damage default attack damage
     */
    public Entity(int health, int damage)
    {
        this.health = health;
        this.damage = damage;
    }

    /**
     * damages another entity
     * @param entity the entity
     */
    public void attack(Entity entity)
    {
        entity.setHealth(entity.getHealth() - getDamage());
        if (entity.getHealth() <= 0) entity.onDeath();
    }

    /**
     * @return the health
     */
    public int getHealth()
    {
        return health;
    }

    /**
     * sets the health
     * @param health the total health
     */
    public void setHealth(int health)
    {
        this.health = health;
    }

    /**
     * @return the damage
     */
    public int getDamage()
    {
        return damage;
    }

    /**
     * called if the entity dies
     */
    public abstract void onDeath();
}
