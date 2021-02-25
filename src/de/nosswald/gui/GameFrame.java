package de.nosswald.gui;

import de.nosswald.game.TextAdventure;
import de.nosswald.gui.screen.GuiScreen;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Nils Osswald
 * @version 1.0
 */
public class GameFrame extends JFrame
{
    private GuiScreen currentScreen;
    private KeyAdapter keyAdapter;

    /**
     * sets up the frame
     */
    public GameFrame()
    {
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle("Loading..");
        setBackground(Color.BLACK);
    }

    /**
     * switches the current screen which is being shown
     * @param guiScreen the screen to load
     */
    public void loadGuiScreen(@NotNull GuiScreen guiScreen)
    {
        // update screen
        currentScreen = guiScreen;
        setContentPane(currentScreen);

        // set title
        if (currentScreen.getScreenData() != null)
            setTitle(currentScreen.getScreenData().title());

        // remove old keyboard listener if existing
        if (keyAdapter != null) removeKeyListener(keyAdapter);
        // add new keyboard listener
        keyAdapter = new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent event)
            {
                currentScreen.getElements().forEach(e -> e.keyPressed(event.getKeyCode()));
                currentScreen.keyPressed(event.getKeyCode());
            }
        };
        this.addKeyListener(keyAdapter);

        // paint and validate the new screen
        repaint();
        revalidate();
    }

    /**
     * @param title the prefix of the frame title
     */
    @Override
    public void setTitle(String title)
    {
        super.setTitle(String.format("%s | %s v%d", title, TextAdventure.NAME, TextAdventure.VERSION));
    }

    /**
     * @return the prefix of the current frame title
     */
    @Override
    public String getTitle()
    {
        // double backslash needed to escape regex syntax
        return super.getTitle().split(" \\|")[0];
    }

    /**
     * @return the current gui screen
     */
    public GuiScreen getCurrentScreen()
    {
        return currentScreen;
    }
}
