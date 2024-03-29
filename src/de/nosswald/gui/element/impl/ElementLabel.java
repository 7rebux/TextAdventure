package de.nosswald.gui.element.impl;

import de.nosswald.gui.element.Element;

import java.awt.*;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class ElementLabel extends Element
{
    private final String text;

    /**
     * @param text   label text
     * @param x      element position x
     * @param y      element position y
     */
    public ElementLabel(String text, int x, int y)
    {
        super(x, y, 0, 0);

        this.text = text;
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
        g.setColor(Color.WHITE);

        // draw text
        g.drawString(text, x, y);
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
