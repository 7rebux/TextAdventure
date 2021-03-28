package de.nosswald.gui.element.impl;

import de.nosswald.game.TextAdventure;
import de.nosswald.game.item.Item;
import de.nosswald.gui.element.Element;
import de.nosswald.utils.DrawUtils;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class ElementItem extends Element
{
    private final Item item;

    private int index;

    /**
     * @param item   the item
     * @param index  the index of the inventory array
     * @param x      element position x
     * @param y      element position y
     * @param size   element width and height
     */
    public ElementItem(Item item, int index, int x, int y, int size)
    {
        super(x, y, size, size);

        this.index = index;
        this.item = item;
    }

    /**
     * called on gui screen setup
     */
    @Override
    public void init() { }

    /**
     * draws the screen
     *
     * @param g the graphics object
     */
    @Override
    public void drawScreen(Graphics g)
    {
        g.setColor(Color.GRAY);
        g.drawRect(x, y, width, height);

        if (item == null)
            return;

        // draw item icon
        g.drawImage(Toolkit.getDefaultToolkit().getImage(item.getItemData().iconPath()), x, y, width, height, null);

        // draw item preview
        if (!isHovered())
            return;

        int y = mouseY + 10;
        int x = mouseX + 10;
        String name = item.getItemData().name();

        // draw border
        g.setColor(Color.WHITE);
        g.drawRect(x - 2, y - 2, getPreviewWidth(g) + 4, getPreviewHeight(g) + 4);

        // draw item name
        y += DrawUtils.getStringHeight(name, g);
        g.setColor(Color.decode(String.valueOf(item.getItemData().color())));
        g.drawString(name, x, y);

        // draw item description
        g.setColor(Color.WHITE);
        y += DrawUtils.getStringHeight(item.getItemData().description()[0], g);
        for (String line : item.getItemData().description()) {
            g.drawString(line, x, y);
            y += DrawUtils.getStringHeight(line, g);
        }
    }

    private int getPreviewWidth(Graphics g)
    {
        return DrawUtils.getStringWidth(
                Arrays.stream(item.getItemData().description()).max(Comparator.comparingInt(String::length)).get(), g);
    }

    private int getPreviewHeight(Graphics g)
    {
        return DrawUtils.getStringHeight(item.getItemData().name(), g) +
                Arrays.stream(item.getItemData().description()).mapToInt(s -> DrawUtils.getStringHeight(s, g)).sum();
    }

    /**
     * called on keyboard input
     *
     * @param keyCode the key code of the pressed key
     */
    @Override
    public void keyPressed(int keyCode) { }

    /**
     * called on mouse input
     *
     * @param mouseButton the mouse button
     */
    @Override
    public void mouseClicked(int mouseButton)
    {
        // remove item from player inventory if used successfully
        if (this.isHovered() && item.useItem())
            TextAdventure.getInstance().getPlayer().getInventory()[index] = null;
    }

    /**
     * @return the item that is being displayed
     */
    public Item getItem()
    {
        return item;
    }
}
