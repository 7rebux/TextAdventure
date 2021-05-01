package de.nosswald.gui.screen.impl;

import de.nosswald.game.TextAdventure;
import de.nosswald.gui.element.impl.ElementButton;
import de.nosswald.gui.element.impl.ElementTextBox;
import de.nosswald.gui.screen.GuiScreen;
import de.nosswald.utils.DrawUtils;

import java.awt.*;

// TODO this screen has to be opened first on the first startup

/**
 * @author Nils Osswald
 * @version 1.0
 */
@GuiScreen.ScreenData(title = "Tutorial")
public class GuiTutorial extends GuiScreen
{
    private final GuiScreen parent;

    public GuiTutorial(GuiScreen parent)
    {
        this.parent = parent;
    }

    /**
     * called on gui screen setup
     */
    @Override
    public void init()
    {
        int buttonWidth = 200;
        int centerX = (DrawUtils.getScreenWidth() - buttonWidth) / 2;

        ElementTextBox textBox = new ElementTextBox(new String[]
        {
            "This screen shows the tutorial about the different features of the game",
            "Items, Enemies, Levels, Dialogs, Characters and progressing through the levels"
        }, 20, 20, DrawUtils.getScreenWidth() - 40, DrawUtils.getScreenHeight() - 130);


        ElementButton backButton = new ElementButton("Back", centerX,DrawUtils.getScreenHeight() - 70, buttonWidth, 30);
        backButton.setClickAction(() -> TextAdventure.getInstance().getGameFrame().loadGuiScreen(parent));

        this.registerElement(textBox);
        this.registerElement(backButton);
    }

    /**
     * draws the screen
     *
     * @param g the graphics object
     */
    @Override
    public void drawScreen(Graphics g)
    {

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
