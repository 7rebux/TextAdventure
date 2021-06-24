package de.nosswald.gui.element;

import java.awt.*;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public abstract class Element
{
    protected final int x;
    protected final int y;
    protected final int width;
    protected final int height;
    public int mouseX, mouseY;

    /**
     * @param x         element position x
     * @param y         element position y
     * @param width     element width measured in pixels
     * @param height    element height measured in pixels
     */
    public Element(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * used to run click or key events
     */
    public interface Action
    {
        /**
         * called on action
         */
        void performAction();
    }

    /**
     * @return if the mouse cursor is hovering the element
     */
    public boolean isHovered()
    {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    /**
     * called on gui screen setup
     */
    public abstract void init();

    /**
     * draws the screen
     * @param g the graphics object
     */
    public abstract void drawScreen(Graphics g);

    /**
     * called on keyboard input
     * @param keyCode the key code of the pressed key
     */
    public abstract void keyPressed(int keyCode);

    /**
     * called on mouse input
     * @param mouseButton   the mouse button
     */
    public abstract void mouseClicked(int mouseButton);
}
