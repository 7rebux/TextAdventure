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
@GuiScreen.ScreenData(title = "Bestätigen")
@SuppressWarnings("SpellCheckingInspection")
public class GuiYesNo extends GuiScreen
{
    private final GuiScreen parent;
    private final Callback callback;
    private final String[] lines;

    /**
     * @param parent    the parent screen
     * @param callback  the callback
     * @param lines     the message separated in lines
     */
    public GuiYesNo(@NotNull GuiScreen parent, Callback callback, String... lines)
    {
        this.parent = parent;
        this.callback = callback;
        this.lines = lines;
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

        ElementButton yesButton = new ElementButton("Ja", centerX - buttonWidth, centerY, buttonWidth, buttonHeight);
        //noinspection Convert2MethodRef
        yesButton.setClickAction(() -> callback.confirm());

        ElementButton noButton = new ElementButton("Nein", centerX + buttonWidth, centerY, buttonWidth, buttonHeight);
        noButton.setClickAction(() -> TextAdventure.getInstance().getGameFrame().loadGuiScreen(parent));

        registerElement(yesButton);
        registerElement(noButton);
    }

    /**
     * used to define what happens when pressing yes
     */
    public interface Callback
    {
        /**
         * called on yes
         */
        void confirm();
    }

    /**
     * draws the screen
     *
     * @param g the graphics object
     */
    @Override
    public void drawScreen(Graphics g)
    {
        g.setColor(Color.WHITE);

        int y = 100;
        for (String line : lines)
        {
            g.drawString(line, (DrawUtils.getScreenWidth() - DrawUtils.getStringWidth(line, g)) / 2, y);
            y += DrawUtils.getStringHeight(line, g) + 4;
        }
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
