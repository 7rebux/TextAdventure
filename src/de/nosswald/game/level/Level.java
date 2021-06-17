package de.nosswald.game.level;

import de.nosswald.game.TextAdventure;
import de.nosswald.game.entity.impl.EntityEnemy;
import de.nosswald.game.entity.impl.enemies.*;
import de.nosswald.game.item.Item;
import de.nosswald.game.item.impl.*;
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
import java.util.*;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class Level
{
    public String fileName;

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

    @Attribute(name = "reward")
    public String reward;

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

        fileName = path.getFileName().toString();

        return this;
    }

    /**
     * loads the level into the ingame gui
     * @param screen the screen object
     */
    public void load(@NotNull GuiIngame screen)
    {
        // add reward to players inventory
        if (reward != null)
        {
            Item rewardItem = getItemByName(reward);
            TextAdventure.getInstance().getPlayer().addItemToInventory(rewardItem);
        }

        // clear enemy
        screen.setEnemy(null);

        // set enemy
        if (enemy != null) screen.setEnemy(getEnemyByName(enemy));

        // set dialog box content
        screen.getDialogBox().setLines(dialogText);

        // clear answer buttons
        Arrays.asList(screen.getAnswerButton()).forEach(button ->
        {
            button.setTitle("");
            button.setClickAction(null);
        });

        // set answer buttons content
        int i = answers.size() - 1;
        for (Map.Entry<String, String> entry : answers.entrySet())
        {
            screen.getAnswerButton()[i].setTitle(entry.getKey());
            screen.getAnswerButton()[i].setClickAction(() ->
            {
                if (screen.getEnemy() != null && !(screen.getEnemy().getHealth() <= 0))
                    return;

                screen.setLevel(Objects.requireNonNull(TextAdventure.getInstance().getLevelManager().getLevelByFileName(entry.getValue())));
            });
            --i;
        }
    }

    private EntityEnemy getEnemyByName(String enemy)
    {
        return switch (enemy.toLowerCase())
        {
            case "crocodile" -> new EnemyCrocodile();
            case "monkey" -> new EnemyMonkey();
            case "souleater" -> new EnemySoulEater();
            case "shark" -> new EnemyShark();
            default -> null;
        };
    }

    private Item getItemByName(String item)
    {
        return switch (item.toLowerCase())
        {
            case "healthpotion" -> new ItemHealthPotion();
            case "manapotion" -> new ItemManaPotion();
            case "longsword" -> new ItemLongSword();
            case "waterstaff" -> new ItemWaterStaff();
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
