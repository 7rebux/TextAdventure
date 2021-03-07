package de.nosswald.gui.screen.impl;

import de.nosswald.game.TextAdventure;
import de.nosswald.game.level.Level;
import de.nosswald.gui.element.impl.ElementInventory;
import de.nosswald.gui.screen.GuiScreen;
import de.nosswald.utils.DrawUtils;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author Nils Osswald
 * @version 1.0
 */
@GuiScreen.ScreenData(title = "Loading..")
public class GuiIngame extends GuiScreen
{
    private Level level;

    private void renderGameOverlay(Graphics g)
    {
        String help = "Help [F1]    |   Ingame Menu [ESC]";

        // draw help
        g.setColor(Color.WHITE);
        g.drawString(help, DrawUtils.getScreenWidth() - DrawUtils.getStringWidth(help, g) - 18,
                DrawUtils.getScreenHeight() - 4);

        // draw status bars
        DrawUtils.drawStatusBar("Health", TextAdventure.getInstance().getPlayer().getHealth(), 100, 10,
                10, 200, 20, Color.RED, g);
        DrawUtils.drawStatusBar("Mana", TextAdventure.getInstance().getPlayer().getMana(), 100, 10,
                40, 200, 20, Color.BLUE, g);
    }

    /**
     * called on gui screen setup
     */
    @Override
    public void init()
    {
        // add inventory
        registerElement(new ElementInventory("Inventory", 10, 70, 200, 120));

        // create new game score
        TextAdventure.getInstance().createScore();

        // load first level
        level = TextAdventure.getInstance().getLevelManager().getLevel("The Beginning");
        level.load();
    }

    /**
     * draws the screen
     *
     * @param g the graphics object
     */
    @Override
    public void drawScreen(Graphics g)
    {
        // set title
        if (!TextAdventure.getInstance().getGameFrame().getTitle().equals(level.name))
            TextAdventure.getInstance().getGameFrame().setTitle(level.name);

        // draw game overlay
        renderGameOverlay(g);

        // draw active level
        level.drawScreen(g);
    }

    /**
     * called on keyboard input
     *
     * @param keyCode the key code of the pressed key
     */
    @Override
    public void keyPressed(int keyCode)
    {
        switch (keyCode)
        {
            case KeyEvent.VK_ESCAPE:
                TextAdventure.getInstance().getGameFrame().loadGuiScreen(new GuiIngameMenu(this));
        }
    }

    /**
     * called on mouse input
     *
     * @param mouseButton the mouse button
     */
    @Override
    public void mouseClicked(int mouseButton) { }

    /**
     * allows you to change the current level
     * @param level the level
     */
    public void setLevel(Level level)
    {
        this.level = level;
    }
}
