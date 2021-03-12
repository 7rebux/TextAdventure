package de.nosswald.gui.screen.impl;

import de.nosswald.game.TextAdventure;
import de.nosswald.game.level.Level;
import de.nosswald.gui.element.impl.ElementButton;
import de.nosswald.gui.element.impl.ElementInventory;
import de.nosswald.gui.element.impl.ElementTextBox;
import de.nosswald.gui.screen.GuiScreen;
import de.nosswald.utils.DrawUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;

/**
 * @author Nils Osswald
 * @version 1.0
 */
@GuiScreen.ScreenData(title = "Loading..")
public class GuiIngame extends GuiScreen
{
    private ElementInventory inventory;
    private ElementTextBox dialogBox;
    private ElementButton[] answerButton;

    private Level level;

    private void renderGameOverlay(Graphics g)
    {
        String help = "Help [F1]    |   Ingame Menu [ESC]";

        // draw help
        g.setColor(Color.WHITE);
        g.drawString(help, DrawUtils.getScreenWidth() - DrawUtils.getStringWidth(help, g),
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
        inventory = new ElementInventory("Inventory", 10, 70, 200, 120);
        this.registerElement(inventory);

        // add dialog box and answer buttons
        dialogBox = new ElementTextBox(null, 0, 340, DrawUtils.getScreenWidth(), 200);
        this.registerElement(dialogBox);

        answerButton = new ElementButton[3];
        answerButton[0] = new ElementButton("", 0, 299, 200, 40);
        answerButton[1] = new ElementButton("", 200, 299, 200, 40);
        answerButton[2] = new ElementButton("", 400, 299, 200, 40);
        Arrays.stream(answerButton).forEach(a -> this.registerElement(a));

        // create new game score
        TextAdventure.getInstance().createScore();

        // load first level
        setLevel(TextAdventure.getInstance().getLevelManager().getLevel("The Beginning"));
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
     * @return the inventory element
     */
    public ElementInventory getInventory()
    {
        return inventory;
    }

    /**
     * @return the dialog box element
     */
    public ElementTextBox getDialogBox()
    {
        return dialogBox;
    }

    /**
     * @return the different answer button elements
     */
    public ElementButton[] getAnswerButton()
    {
        return answerButton;
    }

    public void setLevel(@NotNull Level level)
    {
        this.level = level;
        level.load(this);
    }
}
