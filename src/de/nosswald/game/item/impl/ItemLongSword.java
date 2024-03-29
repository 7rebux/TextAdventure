package de.nosswald.game.item.impl;

import de.nosswald.game.item.Item;

/**
 * @author Nils Osswald
 * @version 1.0
 */
@SuppressWarnings("SpellCheckingInspection")
@Item.ItemData(
        name = "Langschwert",
        iconPath = "assets/items/long_sword.png",
        color = 0x545a66,
        description = { "+10 Angriffsschaden" })
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

    /**
     * default value is zero
     * @return the bonus attack damage stat
     */
    @Override
    public int getBonusAttackDamage()
    {
        return 10;
    }
}
