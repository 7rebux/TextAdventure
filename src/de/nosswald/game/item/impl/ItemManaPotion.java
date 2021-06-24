package de.nosswald.game.item.impl;

import de.nosswald.game.TextAdventure;
import de.nosswald.game.item.Item;

/**
 * @author Nils Osswald
 * @version 1.0
 */
@SuppressWarnings("SpellCheckingInspection")
@Item.ItemData(
        name = "Manatrank",
        iconPath = "assets/items/mana_potion.png",
        color = 0x0000ff,
        description = { "Stellt 25 Mana her" })
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

        TextAdventure.getInstance().getPlayer().setMana(mana >= 75 ? 100 : mana + 25);

        return true;
    }
}
