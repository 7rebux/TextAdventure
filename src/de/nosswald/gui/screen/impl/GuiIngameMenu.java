package de.nosswald.gui.screen.impl;

import de.nosswald.game.TextAdventure;
import de.nosswald.gui.element.impl.ElementButton;
import de.nosswald.gui.screen.GuiScreen;
import de.nosswald.utils.DrawUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Nils Osswald
 * @version 1.0
 */
@GuiScreen.ScreenData(title = "Ingame Menu")
public class GuiIngameMenu extends GuiScreen
{
    private GuiScreen parent;

    /**
     * @param parent the parent screen
     */
    public GuiIngameMenu(@NotNull GuiScreen parent)
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
        int buttonHeight = 30;
        int centerX = (DrawUtils.getScreenWidth() - buttonWidth) / 2;
        int centerY = (DrawUtils.getScreenHeight() - buttonHeight) / 2;

        ElementButton continueButton = new ElementButton("Continue", centerX, centerY - 80, buttonWidth, buttonHeight);
        continueButton.setClickAction(() -> TextAdventure.getInstance().getGameFrame().loadGuiScreen(parent));

        ElementButton settingsButton = new ElementButton("Settings", centerX, centerY, buttonWidth, buttonHeight);
        settingsButton.setClickAction(() -> TextAdventure.getInstance().getGameFrame().loadGuiScreen(new GuiSettings(this)));

        ElementButton quitButton = new ElementButton("Main Menu", centerX, centerY + 80, buttonWidth, buttonHeight);
        quitButton.setClickAction(() -> TextAdventure.getInstance().getGameFrame().loadGuiScreen(
                new GuiYesNo(this, () -> TextAdventure.getInstance().getGameFrame().loadGuiScreen(new GuiMainMenu()),
                        "Are you sure you want to go back?", "All progress made will be lost forever.")));

        this.registerElement(continueButton);
        this.registerElement(settingsButton);
        this.registerElement(quitButton);
    }

    /**
     * draws the screen
     *
     * @param g the graphics object
     */
    @Override
    public void drawScreen(Graphics g) { }

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
