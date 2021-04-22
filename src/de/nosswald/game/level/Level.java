package de.nosswald.game.level;

import de.nosswald.game.TextAdventure;
import de.nosswald.game.entity.impl.EntityEnemy;
import de.nosswald.game.entity.impl.enemies.EnemySkeleton;
import de.nosswald.game.entity.impl.enemies.EnemyZombie;
import de.nosswald.gui.screen.impl.GuiIngame;
import de.nosswald.utils.DrawUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.FileNotFoundException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class Level
{
    private String[] dialogText;
    private final HashMap<String, String> answers = new HashMap<>();

    /*
     * The visibility of this fields has to be public
     * for reflection to work properly.
     */
    @Attribute(name = "levelName")
    public String name;

    @Attribute(name = "characterName")
    public String characterName;

    @Attribute(name = "enemy")
    public String enemy;

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

                // check if line is an attribute
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
                // check if line is the dialog
                else if (line.startsWith("[DIALOG]"))
                    dialogText = line.replace("[DIALOG]", "").split("//");
                // check if line is an answer
                else if (line.startsWith("[ANSWER]"))
                {
                    String[] answer = line.replace("[ANSWER]", "").split(":");
                    answers.put(answer[0], answer[1]);
                }
            }
        }
        catch (FileNotFoundException | IllegalAccessException ignored) { }

        return this;
    }

    /**
     * loads the level into the ingame gui
     * @param screen the screen object
     */
    public void load(@NotNull GuiIngame screen)
    {
        // set dialog box content
        screen.getDialogBox().setLines(dialogText);

        // set answer buttons content
        int i = answers.size() - 1;
        for (Map.Entry<String, String> entry : answers.entrySet())
        {
            screen.getAnswerButton()[i].setTitle(entry.getKey());
            screen.getAnswerButton()[i].setClickAction(() ->
                    screen.setLevel(TextAdventure.getInstance().getLevelManager().getLevel(entry.getValue())));
            --i;
        }

        // set enemy
        if (enemy != null) screen.setEnemy(getEnemyByName(enemy));
    }

    private EntityEnemy getEnemyByName(String enemy)
    {
        return switch (enemy.toLowerCase())
        {
            case "zombie" -> new EnemyZombie();
            case "skeleton" -> new EnemySkeleton();
            default -> null;
        };
    }

    /**
     * draws the screen
     *
     * @param g the graphics object
     */
    public void drawScreen(Graphics g)
    {
        g.setColor(Color.WHITE);

        // draw level name
        g.drawString(name, 4, DrawUtils.getScreenHeight() - 4);

        // draw character name
        g.drawString(characterName, DrawUtils.getScreenWidth() - DrawUtils.getStringWidth(characterName, g), 335);
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
