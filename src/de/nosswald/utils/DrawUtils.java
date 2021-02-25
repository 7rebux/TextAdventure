package de.nosswald.utils;

import de.nosswald.game.TextAdventure;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class DrawUtils
{
    /**
     * @return the width of the screen measured in pixels
     */
    public static int getScreenWidth()
    {
        return TextAdventure.getInstance().getGameFrame().getWidth();
    }

    /**
     * (subtracts 40 because the top border of the frame is 40 pixels tall)
     * @return the height of the screen measured in pixels
     */
    public static int getScreenHeight()
    {
        return TextAdventure.getInstance().getGameFrame().getHeight() - 40;
    }

    /**
     * @param text  the text
     * @param g     the graphics object
     * @return the width of a string measured in pixels
     */
    public static int getStringWidth(String text, @NotNull Graphics g)
    {
        return g.getFontMetrics().stringWidth(text);
    }

    /**
     * @param text  the text
     * @param g     the graphics object
     * @return the height of a string measured in pixels
     */
    public static int getStringHeight(String text, @NotNull Graphics g)
    {
        return (int)g.getFontMetrics().getFont().getStringBounds(text, g.getFontMetrics().getFontRenderContext()).getHeight();
    }

    /**
     * draws a status bar
     * @param title     the title
     * @param value     the current value
     * @param maxValue  the maximum value
     * @param x         position x
     * @param y         position y
     * @param width     bar width
     * @param height    bar height
     * @param color     fill color
     * @param g         the graphics object
     */
    public static void drawStatusBar(String title, int value, int maxValue, int x, int y, int width, int height, Color color, @NotNull Graphics g)
    {
        float barWidth = ((float)value) / (maxValue);
        String s = value + "/" + maxValue;

        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.fillRect(x + 1, y + 1, width - 1, height - 1);
        g.setColor(color);
        g.fillRect(x + 1, y + 1, (int)Math.min((barWidth * width - 1), width - 1), height - 1);

        g.setColor(Color.WHITE);
        g.drawString(title, x + 4, y + getStringHeight(title, g));
        g.drawString(s, x + width - getStringWidth(s, g) - 4, y + getStringHeight(s, g));
    }
}
