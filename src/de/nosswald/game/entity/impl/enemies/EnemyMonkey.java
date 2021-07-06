package de.nosswald.game.entity.impl.enemies;

import de.nosswald.game.TextAdventure;
import de.nosswald.game.entity.impl.EntityEnemy;
import de.nosswald.game.item.impl.ItemLongSword;

/**
 * @author Nils Osswald
 * @version 1.0
 */
@SuppressWarnings("SpellCheckingInspection")
public class EnemyMonkey extends EntityEnemy
{
    public EnemyMonkey()
    {
        super(10, 10, "Affe");
    }

    /**
     * called if the entity dies
     */
    @Override
    public void onDeath()
    {
        if (Math.random() <= 0.5D)
            TextAdventure.getInstance().getPlayer().addItemToInventory(new ItemLongSword());
    }
}
