package de.nosswald.game.entity.impl.enemies;


import de.nosswald.game.TextAdventure;
import de.nosswald.game.entity.impl.EntityEnemy;
import de.nosswald.game.item.impl.ItemHealthPotion;
import de.nosswald.game.item.impl.ItemLongSword;
import de.nosswald.game.item.impl.ItemManaPotion;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class EnemyShark extends EntityEnemy
{
    public EnemyShark()
    {
        super(50, 15, "Hai");
    }

    /**
     * called if the entity dies
     */
    @Override
    public void onDeath()
    {
        TextAdventure.getInstance().getPlayer().addItemToInventory(new ItemHealthPotion());
        TextAdventure.getInstance().getPlayer().addItemToInventory(new ItemManaPotion());
        if (Math.random() <= 0.5D) TextAdventure.getInstance().getPlayer().addItemToInventory(new ItemLongSword());
    }
}
