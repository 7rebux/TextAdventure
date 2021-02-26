package de.nosswald.game.item;

import org.jetbrains.annotations.Nullable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public abstract class Item
{
    /**
     * called when the item is used
     * @return if the item was used successfully
     */
    public abstract boolean useItem();

    /**
     * stores useful information about an item
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface ItemData
    {
        String name();
        String iconPath();
        int color(); // needs to be int (hex) to support annotations
        String[] description();
    }

    /**
     * @return the item data
     */
    @Nullable
    public ItemData getItemData()
    {
        if (getClass().isAnnotationPresent(ItemData.class))
            return getClass().getAnnotation(ItemData.class);

        return null;
    }
}
