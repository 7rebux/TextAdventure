package de.nosswald.game.entity.impl.enemies;

import de.nosswald.game.TextAdventure;
import de.nosswald.game.entity.impl.EntityEnemy;
import de.nosswald.game.item.impl.ItemLongSword;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class EnemySkeleton extends EntityEnemy
{
    public EnemySkeleton()
    {
        super(10, 10, "Skeleton");
    }

    /**
     * called if the entity dies
     */
    @Override
    public void onDeath()
    {
        if (Math.random() <= 0.1D)
            TextAdventure.getInstance().getPlayer().addItemToInventory(new ItemLongSword());
    }
}
