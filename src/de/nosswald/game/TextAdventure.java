package de.nosswald.game;

import de.nosswald.game.entity.Player;
import de.nosswald.gui.GameFrame;
import de.nosswald.gui.screen.impl.GuiMainMenu;
import de.nosswald.game.level.LevelManager;
import org.jetbrains.annotations.Nullable;

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

    private static TextAdventure instance;

    private GameFrame gameFrame;
    private Player player;
    private LevelManager levelManager;

    /**
     * creates the instance
     */
    public TextAdventure()
    {
        instance = this;
    }

    /**
     * prepares the start of the game, creates the frame and loads the main menu
     */
    public void startGame()
    {
        // create game window
        gameFrame = new GameFrame();
        gameFrame.setVisible(true);

        // load main menu screen
        gameFrame.loadGuiScreen(new GuiMainMenu());

        // load levels
        levelManager = new LevelManager();
    }

    /**
     * creates a new game score
     */
    public void createScore()
    {
        player = new Player();
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
    public Player getPlayer()
    {
        return player;
    }
}
