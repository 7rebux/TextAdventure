package de.nosswald.gui.element.impl;

import de.nosswald.gui.element.Element;
import de.nosswald.utils.DrawUtils;

import java.awt.*;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class ElementTextBox extends Element
{
    private String[] lines;

    /**
     * @param lines  the text of the box separated in lines
     * @param x      element position x
     * @param y      element position y
     * @param width  element width measured in pixels
     * @param height element height measured in pixels
     */
    public ElementTextBox(String[] lines, int x, int y, int width, int height)
    {
        super(x, y, width, height);

        this.lines = lines;
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

        // draw box
        g.drawRect(x, y, width, height);

        // draw lines
        int y = this.y + DrawUtils.getStringHeight(lines[0], g);
        for (String line : lines)
        {
            g.drawString(line, x + 4, y);
            y += DrawUtils.getStringHeight(line, g) + 4;
        }
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
