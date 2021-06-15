package de.nosswald.game.entity.impl.enemies;

import de.nosswald.game.TextAdventure;
import de.nosswald.game.entity.impl.EntityEnemy;
import de.nosswald.game.item.impl.ItemHealthPotion;
import de.nosswald.game.item.impl.ItemManaPotion;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class EnemyCrocodile extends EntityEnemy
{
    public EnemyCrocodile()
    {
        super(20, 5, "Crocodile");
    }

    /**
     * called if the entity dies
     */
    @Override
    public void onDeath()
    {
        TextAdventure.getInstance().getPlayer().addItemToInventory(
                Math.random() >= 0.5D ? new ItemHealthPotion() : new ItemManaPotion());
    }
}
