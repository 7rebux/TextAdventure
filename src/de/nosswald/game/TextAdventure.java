package de.nosswald.game;

import de.nosswald.game.entity.impl.EntityPlayer;
import de.nosswald.gui.GameFrame;
import de.nosswald.gui.screen.impl.GuiMainMenu;
import de.nosswald.game.level.LevelManager;
import de.nosswald.gui.screen.impl.GuiTutorial;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class TextAdventure
{
    /**
     * the field that holds the application name
     */
    public static final String NAME = "Text Adventure";
    /**
     * the field that holds the application version
     */
    public static final int VERSION = 1;

    /**
     * this field determinates if the application is being started for the first time
     */
    private final boolean IS_FIRST_START;

    private static TextAdventure instance;

    private GameFrame gameFrame;
    private EntityPlayer player;
    private LevelManager levelManager;

    /**
     * creates the instance
     */
    public TextAdventure()
    {
        instance = this;

        // check if the application is being started for the first time
        File file = new File("assets", "yep");
        IS_FIRST_START = !file.exists();
        System.out.println(IS_FIRST_START);
        if (IS_FIRST_START)
            try { file.createNewFile(); } catch (IOException ignored) { }
    }

    /**
     * prepares the start of the game, creates the frame and loads the main menu
     */
    public void startGame()
    {
        // create game window
        gameFrame = new GameFrame();
        gameFrame.setVisible(true);

        // load first gui screen
        gameFrame.loadGuiScreen(IS_FIRST_START ? new GuiTutorial(new GuiMainMenu()) : new GuiMainMenu());

        // load levels
        levelManager = new LevelManager();
    }

    /**
     * creates a new game score
     */
    public void createScore()
    {
        player = new EntityPlayer();
    }

    /**
     * @return the instance
     */
    public static TextAdventure getInstance()
    {
        return instance;
    }

    /**
     * @return the game frame object
     */
    public GameFrame getGameFrame()
    {
        return gameFrame;
    }

    /**
     * @return the level manager object
     */
    public LevelManager getLevelManager()
    {
        return levelManager;
    }

    /**
     * @return the player object or null if theres no player
     */
    @Nullable
    public EntityPlayer getPlayer()
    {
        return player;
    }
}
