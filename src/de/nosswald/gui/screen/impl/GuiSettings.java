package de.nosswald.gui.screen.impl;

import de.nosswald.game.TextAdventure;
import de.nosswald.gui.element.impl.*;
import de.nosswald.gui.screen.GuiScreen;
import de.nosswald.utils.DrawUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Nils Osswald
 * @version 1.0
 */
@SuppressWarnings("SpellCheckingInspection")
@GuiScreen.ScreenData(title = "Einstellungen")
public class GuiSettings extends GuiScreen
{
    private final GuiScreen parent;

    /**
     * @param parent the parent screen
     */
    public GuiSettings(@NotNull GuiScreen parent)
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

        ElementButton saveButton = new ElementButton("Speichern", centerX,DrawUtils.getScreenHeight() - 70, buttonWidth, 30);
        saveButton.setClickAction(() -> TextAdventure.getInstance().getGameFrame().loadGuiScreen(parent));

        this.registerElement(saveButton);

        // testing
        this.registerElement(new ElementTextBox(new String[]{ "line1", "line2", "line3" }, 20, 20, 300, 60));
        this.registerElement(new ElementLabel("Example Label", 20, 120));
    }

    /**
     * draws the screen
     * @param g the graphics object
     */
    @Override
    public void drawScreen(Graphics g) { }

    /**
     * called on keyboard input
     * @param keyCode the key code of the pressed key
     */
    @Override
    public void keyPressed(int keyCode) { }

    /**
     * called on mouse input
     * @param mouseButton the mouse button
     */
    @Override
    public void mouseClicked(int mouseButton) { }
}
