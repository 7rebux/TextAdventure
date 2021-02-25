package de.nosswald.gui.element.impl;

import de.nosswald.gui.element.Element;
import de.nosswald.utils.DrawUtils;

import java.awt.*;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class ElementButton extends Element
{
    private String title;
    private Action action;

    /**
     * @param title     button title
     * @param x         element position x
     * @param y         element position y
     * @param width     element width measured in pixels
     * @param height    element height measured in pixels
     */
    public ElementButton(String title, int x, int y, int width, int height)
    {
        super(x, y, width, height);

        this.title = title;
    }

    /**
     * used to add an action interface
     * @param action the action
     */
    public void setClickAction(Action action)
    {
        this.action = action;
    }

    /**
     * called on gui screen setup
     */
    @Override
    public void init() { }

    /**
     * draws the screen
     * @param g the graphics object
     */
    @Override
    public void drawScreen(Graphics g)
    {
        g.setColor(Color.CYAN);
        g.drawRect(x, y, width, height);
        g.drawString(title,
                x + ((width - DrawUtils.getStringWidth(title, g)) / 2),
                y + ((height + DrawUtils.getStringHeight(title, g)) / 2));
    }

    /**
     * called on keyboard input
     * @param keyCode the key code of the pressed key
     */
    @Override
    public void keyPressed(int keyCode) { }

    /**
     * called on mouse input
     * @param mouseButton   the mouse button
     */
    @Override
    public void mouseClicked(int mouseButton)
    {
        if (action == null)
            return;

        if (this.isHovered())
            action.performAction();
    }
}
