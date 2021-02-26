package de.nosswald.game.item.impl;

import de.nosswald.game.TextAdventure;
import de.nosswald.game.item.Item;

/**
 * @author Nils Osswald
 * @version 1.0
 */
@Item.ItemData(
        name = "Mana Potion",
        iconPath = "assets/items/mana_potion.png",
        color = 0x0000ff,
        description = { "Restores 25 Mana" })
public class ItemManaPotion extends Item
{
    /**
     * called when the item is used
     *
     * @return if the item was used successfully
     */
    @Override
    public boolean useItem()
    {
        int mana = TextAdventure.getInstance().getPlayer().getMana();

        if (mana >= 100)
            return false;
        else if (mana > 90)
            TextAdventure.getInstance().getPlayer().setMana(100);
        else
            TextAdventure.getInstance().getPlayer().setMana(mana + 10);

        return true;
    }
}
