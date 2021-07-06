package de.nosswald.game.entity.impl;

import de.nosswald.game.TextAdventure;
import de.nosswald.game.entity.Entity;
import de.nosswald.game.item.Item;
import de.nosswald.game.item.impl.ItemHealthPotion;
import de.nosswald.gui.screen.impl.GuiGameOver;

import java.util.Arrays;
import java.util.Objects;
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
        this.mana = 50;

        // add starter item
        addItemToInventory(new ItemHealthPotion());
    }

    /**
     * damages another entity and consumes mana
     * @param entity the entity
     */
    @Override
    public void attack(Entity entity)
    {
        if (!(mana >= 5))
            return;

        entity.setHealth(entity.getHealth() - getDamage());
        if (entity.getHealth() < 0) entity.setHealth(0);
        if (entity.getHealth() == 0) entity.onDeath();
        setMana(getMana() - 5);
    }

    /**
     * @return the mana
     */
    public int getMana()
    {
        return mana;
    }

    /**
     * sets the mana
     * @param mana the total mana
     */
    public void setMana(int mana)
    {
        this.mana = mana;
    }

    /**
     * @return the damage (inclusive item stats)
     */
    @Override
    public int getDamage()
    {
        return super.getDamage() + Arrays.stream(inventory).filter(Objects::nonNull).mapToInt(Item::getBonusAttackDamage).sum();
    }

    /**
     * @return the inventory as item array
     */
    public Item[] getInventory()
    {
        return inventory;
    }

    /**
     * adds an item to the next empty slot in the inventory
     * @param item the item
     */
    public void addItemToInventory(Item item)
    {
        IntStream.range(0, inventory.length).filter(i -> inventory[i] == null).findFirst().ifPresent(i -> inventory[i] = item);
    }

    /**
     * called if the entity dies
     */
    @Override
    public void onDeath()
    {
        TextAdventure.getInstance().getGameFrame().loadGuiScreen(new GuiGameOver());
    }
}
