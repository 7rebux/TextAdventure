package de.nosswald.game.item.impl;

import de.nosswald.game.TextAdventure;
import de.nosswald.game.item.Item;

/**
 * @author Nils Osswald
 * @version 1.0
 */
@SuppressWarnings("SpellCheckingInspection")
@Item.ItemData(
        name = "Heiltrank",
        iconPath = "assets/items/health_potion.png",
        color = 0xff0000,
        description = { "Stellt 25 Leben her" })
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

        TextAdventure.getInstance().getPlayer().setHealth(health > 75 ? 100 : health + 25);

        return true;
    }
}
