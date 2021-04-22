package de.nosswald.game.entity.impl;

import de.nosswald.game.entity.Entity;
import de.nosswald.game.item.Item;
import de.nosswald.game.item.impl.ItemHealthPotion;

import java.util.Arrays;
import java.util.stream.IntStream;

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
        super(50, 5);

        // set default values
        this.mana = 25;

        // add starter item
        addItemToInventory(new ItemHealthPotion());
    }

    @Override
    public void attack(Entity enemy)
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

    public void addItemToInventory(Item item)
    {
        IntStream.range(0, inventory.length).filter(i -> inventory[i] == null).findFirst().ifPresent(i -> inventory[i] = item);
    }

    @Override
    public void onDeath()
    {
        // TODO GAME OVER
    }
}
