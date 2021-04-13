package de.nosswald.game.item.impl;

import de.nosswald.game.item.Item;

/**
 * @author Nils Osswald
 * @version 1.0
 */
@Item.ItemData(
        name = "Long Sword",
        iconPath = "assets/items/long_sword.png",
        color = 0x545a66,
        description = { "+15 Attack Damage" })
public class ItemLongSword extends Item
{
    /**
     * called when the item is used
     *
     * @return if the item was used successfully
     */
    @Override
    public boolean useItem()
    {
        return false; // unusable
    }

    @Override
    public int getBonusAttackDamage()
    {
        return 15;
    }
}
