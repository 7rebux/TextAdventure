package de.nosswald.game.item.impl;

import de.nosswald.game.TextAdventure;
import de.nosswald.game.item.Item;

/**
 * @author Nils Osswald
 * @version 1.0
 */
@Item.ItemData(
        name = "Health Potion",
        iconPath = "assets/items/health_potion.png",
        color = 0xff0000,
        description = { "Restores 10 Health" })
public class ItemHealthPotion extends Item
{
    /**
     * called when the item is used
     * @return if the item was used successfully
     */
    @Override
    public boolean useItem()
    {
        int health = TextAdventure.getInstance().getPlayer().getHealth();

        if (health >= 100)
            return false;
        else if (health > 90)
            TextAdventure.getInstance().getPlayer().setHealth(100);
        else
            TextAdventure.getInstance().getPlayer().setHealth(health + 10);

        return true;
    }
}
