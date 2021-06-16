package de.nosswald.game.entity.impl.enemies;

import de.nosswald.game.entity.impl.EntityEnemy;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class EnemySoulEater extends EntityEnemy
{
    public EnemySoulEater()
    {
        super(50, 10, "SoulEater");
    }

    /**
     * called if the entity dies
     */
    @Override
    public void onDeath()
    {
        // TODO something special
    }
}

