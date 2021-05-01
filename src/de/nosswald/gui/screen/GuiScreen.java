package de.nosswald.gui.screen;

import de.nosswald.gui.element.Element;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public abstract class GuiScreen extends JPanel
{
    private final ArrayList<Element> elements = new ArrayList<>();
    protected int mouseX, mouseY;

    /**
     * sets up the gui screen and all its elements
     */
    public GuiScreen()
    {
        // initialize
        elements.forEach(Element::init);
        init();

        // add mouse listener
        this.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent event)
            {
                elements.forEach(e -> e.mouseClicked(event.getButton()));
                GuiScreen.this.mouseClicked(event.getButton());
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent event)
            {
                elements.forEach(e -> { e.mouseX = event.getX(); e.mouseY = event.getY(); });

                mouseX = event.getX();
                mouseY = event.getY();
            }
        });
    }

    /**
     * adds an element
     * @param e the element
     */
    public void registerElement(Element e)
    {
        elements.add(e);
    }

    /**
     * draws the screen of the j-panel
     * @param g the graphics object
     */
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        // default background
        this.setBackground(Color.BLACK);

        elements.forEach(e -> e.drawScreen(g));
        drawScreen(g);

        repaint();
    }

    /**
     * stores useful information about the gui screen
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface ScreenData
    {
        String title();
    }

    /**
     * @return the screen data
     */
    @Nullable
    public ScreenData getScreenData()
    {
        if (getClass().isAnnotationPresent(ScreenData.class))
            return getClass().getAnnotation(ScreenData.class);

        return null;
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

    /**
     * @return all elements of the screen stored in a arraylist
     */
    public ArrayList<Element> getElements()
    {
        return elements;
    }
}
