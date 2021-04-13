package de.nosswald.game.entity.impl;

import de.nosswald.game.entity.Entity;
import de.nosswald.game.item.Item;

import java.util.Arrays;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class EntityPlayer extends Entity
{
    private int mana;
    private final Item[] inventory = new Item[6];

    public EntityPlayer()
    {
        super(87, 2);

        // default values
        this.mana = 43;
    }

    public void attack(EntityEnemy enemy)
    {
        if (!(mana >= 10))
            return;

        enemy.setHealth(enemy.getHealth() - getDamage());
        if (enemy.getHealth() <= 0) enemy.onDeath();
        setMana(getMana() - 10);
    }

    public int getMana()
    {
        return mana;
    }

    public void setMana(int mana)
    {
        this.mana = mana;
    }

    @Override
    public int getDamage()
    {
        return super.getDamage() + Arrays.stream(inventory).mapToInt(Item::getBonusAttackDamage).sum();
    }

    public Item[] getInventory()
    {
        return inventory;
    }
}
