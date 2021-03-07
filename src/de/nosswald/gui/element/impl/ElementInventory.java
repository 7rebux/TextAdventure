package de.nosswald.gui.element.impl;

import de.nosswald.game.item.Item;
import de.nosswald.gui.element.Element;
import de.nosswald.utils.DrawUtils;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class ElementInventory extends Element
{
    private String title;
    private final ArrayList<Item> items = new ArrayList<>();

    /**
     * @param title  combo box title
     * @param x      element position x
     * @param y      element position y
     * @param width  element width measured in pixels
     * @param height element height measured in pixels
     */
    public ElementInventory(String title, int x, int y, int width, int height)
    {
        super(x, y, width, height);

        this.title = title;
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
        int gap = width / 10;

        g.setColor(Color.WHITE);

        // draw combo box
        g.drawLine(x, y, x, y + height);
        g.drawLine(x + width, y, x + width, y + height);
        g.drawLine(x, y + height, x + width, y + height);
        g.drawLine(x, y, x + gap - 4, y);
        g.drawLine(x + gap + DrawUtils.getStringWidth(title, g) + 4, y, x + width, y);

        // draw title
        g.drawString(title, x + gap, y + (DrawUtils.getStringHeight(title, g) / 2));
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
    public void mouseClicked(int mouseButton) { }
}
