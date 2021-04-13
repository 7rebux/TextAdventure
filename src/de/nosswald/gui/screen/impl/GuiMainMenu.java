package de.nosswald.gui.screen.impl;

import de.nosswald.game.TextAdventure;
import de.nosswald.gui.element.impl.ElementButton;
import de.nosswald.gui.screen.GuiScreen;
import de.nosswald.utils.DrawUtils;

import java.awt.*;

/**
 * @author Nils Osswald
 * @version 1.0
 */
@GuiScreen.ScreenData(title = "Main Menu")
public class GuiMainMenu extends GuiScreen
{
    private final Image LOGO = Toolkit.getDefaultToolkit().getImage("assets/logo.png");

    /**
     * called on gui screen setup
     */
    @Override
    public void init()
    {
        int buttonWidth = 200;
        int buttonHeight = 30;
        int centerX = (DrawUtils.getScreenWidth() - buttonWidth) / 2;
        int centerY = (DrawUtils.getScreenHeight() - buttonHeight) / 2 + 50;

        ElementButton playButton = new ElementButton("Play", centerX, centerY - 80, buttonWidth, buttonHeight);
        playButton.setClickAction(() -> TextAdventure.getInstance().getGameFrame().loadGuiScreen(new GuiIngame()));

        ElementButton settingsButton = new ElementButton("Settings", centerX, centerY, buttonWidth, buttonHeight);
        settingsButton.setClickAction(() -> TextAdventure.getInstance().getGameFrame().loadGuiScreen(new GuiSettings(this)));

        ElementButton quitButton = new ElementButton("Quit", centerX, centerY + 80, buttonWidth, buttonHeight);
        quitButton.setClickAction(() -> TextAdventure.getInstance().getGameFrame().loadGuiScreen(
                new GuiYesNo(this, () -> System.exit(0), "Are you sure you want to quit?")));

        this.registerElement(playButton);
        this.registerElement(settingsButton);
        this.registerElement(quitButton);
    }

    /**
     * draws the screen
     * @param g the graphics object
     */
    @Override
    public void drawScreen(Graphics g)
    {
        // draw logo
        g.drawImage(LOGO, 0, 0, null);
    }

    /**
     * called on keyboard input
     * @param keyCode the key code of the pressed key
     */
    @Override
    public void keyPressed(int keyCode)
    {
        System.out.println(keyCode);
    }

    /**
     * called on mouse input
     * @param mouseButton   the mouse button
     */
    @Override
    public void mouseClicked(int mouseButton) { }
}
