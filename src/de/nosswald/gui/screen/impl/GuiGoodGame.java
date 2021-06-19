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
@GuiScreen.ScreenData(title = "Good Game")
public class GuiGoodGame extends GuiScreen
{
    /**
     * called on gui screen setup
     */
    @Override
    public void init()
    {
        int buttonWidth = 400;
        int centerX = (DrawUtils.getScreenWidth() - buttonWidth) / 2;

        ElementButton backButton = new ElementButton("Zurück zum Hauptmenü", centerX,DrawUtils.getScreenHeight() - 70, buttonWidth, 30);
        backButton.setClickAction(() -> TextAdventure.getInstance().getGameFrame().loadGuiScreen(new GuiMainMenu()));

        registerElement(backButton);
    }

    /**
     * draws the screen
     *
     * @param g the graphics object
     */
    @Override
    public void drawScreen(Graphics g)
    {
        String str = "Du hast das Spiel erfolgreich abgeschlossen! Good Game!";
        g.setColor(Color.WHITE);
        g.drawString(str, (DrawUtils.getScreenWidth() - DrawUtils.getStringWidth(str, g)) / 2, DrawUtils.getScreenHeight() / 2);
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
