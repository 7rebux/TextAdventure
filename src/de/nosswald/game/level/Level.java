package de.nosswald.game.level;

import de.nosswald.game.TextAdventure;
import de.nosswald.gui.screen.GuiScreen;
import de.nosswald.gui.screen.impl.GuiIngame;
import de.nosswald.utils.DrawUtils;

import java.awt.*;
import java.io.FileNotFoundException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class Level
{
    /*
     * The visibility of this fields has to be public
     * for reflection to work properly.
     */
    @Attribute(name = "levelName")
    public String name;

    /**
     * creates a level object by its file path
     * @param path the path
     * @return the level object
     */
    public Level fromPath(Path path)
    {
        try
        {
            Scanner scanner = new Scanner(path.toFile());

            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();

                // check if line is comment
                if (line.startsWith("#"))
                    continue;

                // check if line is attribute
                if (line.startsWith("§"))
                {
                    for (Field field : getClass().getFields())
                    {
                        if (field.isAnnotationPresent(Attribute.class))
                        {
                            Attribute attribute = field.getAnnotation(Attribute.class);

                            if (attribute.name().equals(line.split("§")[1].split("=")[0]))
                            {
                                String value = line.split("=")[1];

                                if (field.getType() == String.class)
                                    field.set(this, value);
                            }
                        }
                    }
                }
            }
        }
        catch (FileNotFoundException | IllegalAccessException ignored) { }

        return this;
    }

    /**
     * loads the level into the ingame gui
     */
    public void load()
    {
        // load level into ingame gui
        if (TextAdventure.getInstance().getGameFrame().getCurrentScreen() instanceof GuiIngame)
        {
            GuiIngame screen = (GuiIngame)TextAdventure.getInstance().getGameFrame().getCurrentScreen();
            screen.setLevel(this);
        }
    }

    /**
     * draws the screen
     *
     * @param g the graphics object
     */
    public void drawScreen(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.drawString(name, 4, DrawUtils.getScreenHeight() - 4);
    }

    /**
     * used to define attributes
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    private @interface Attribute
    {
        /**
         * @return attribute name
         */
        String name();
    }
}
