package de.nosswald.gui.element.impl;

import de.nosswald.game.item.Item;
import de.nosswald.gui.element.Element;

import java.awt.*;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class ElementItem extends Element
{
    private Item item;

    /**
     * @param item   the item
     * @param x      element position x
     * @param y      element position y
     * @param width  element width measured in pixels
     * @param height element height measured in pixels
     */
    public ElementItem(Item item, int x, int y, int size)
    {
        super(x, y, size, size);

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

        // TODO draw item icon

        // draw item preview
        if (!isHovered())
            return;

        g.setColor(Color.decode(String.valueOf(item.getItemData().color())));
        g.drawString(item.getItemData().name(), mouseX + 4, mouseY);
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
     * @param mouseX      the x mouse position
     * @param mouseY      the y mouse position
     */
    @Override
    public void mouseClicked(int mouseButton)
    {
        boolean used;

        if (this.isHovered())
            used = item.useItem();

        // TODO destroy item if used successfully
    }
}
