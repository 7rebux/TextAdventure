package de.nosswald.gui.screen.impl;

import de.nosswald.game.TextAdventure;
import de.nosswald.gui.element.impl.ElementButton;
import de.nosswald.gui.element.impl.ElementTextBox;
import de.nosswald.gui.screen.GuiScreen;
import de.nosswald.utils.DrawUtils;

import java.awt.*;

/**
 * @author Nils Osswald
 * @version 1.0
 */
@SuppressWarnings("SpellCheckingInspection")
@GuiScreen.ScreenData(title = "Tutorial")
public class GuiTutorial extends GuiScreen
{
    private final GuiScreen parent;

    public GuiTutorial(GuiScreen parent)
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

        ElementTextBox textBox = new ElementTextBox(new String[]
        {
            "Herzlich Willkommen zu " + TextAdventure.NAME + "!",
            "In diesem Abenteuer wird es dein Ziel sein den König von Nemacia",
            "zu konfrontieren und Senna in Sicherheit zu bringen.",
            "",
            "Der Nachfolgende Text wird dich mit den verschiedenen Mechaniken",
            "in dem Spiel vertraut machen.",
            "",
            "Oben links findest du dein aktuelles Leben und Mana sowohl",
            "auch dein Inventar mit deinen Gegenständen welche du im Besitz hast.",
            "Um herauszufinden was ein Gegenstand bringt, kannst du einfach mit der Maus darüberfahren.",
            "Darunter befindet sich der Knopf 'Angreifen' mit welchem du einem Gegner Schaden zufügen kannst.",
            "Dieser Knopf wird aber nur funktionieren, falls ein Gegner existiert. Dies erkennst du so bald rechts oben",
            "eine Lebensleiste deines aktuellen Gegners angezeigt wird.",
            "Jeder Angriff kostet dich 5 Mana. Wenn du nicht mehr ausreichend Mana hast, wirst du keinen Schaden machen.",
            "Nach jedem Angriff wird dein Gegner einen Gegenangriff machen welcher je nach Gegnertyp unterschiedlich viel",
            "Schaden anrichtet. Solltest du 0 oder weniger Leben haben hast du das Spiel verloren.",
            "",
            "Alles weitere wird im Laufe des Spiels erklärt.",
            "",
            "Viel Spaß!"
        }, 20, 20, DrawUtils.getScreenWidth() - 40, DrawUtils.getScreenHeight() - 130);

        ElementButton backButton = new ElementButton("Zurück", centerX,DrawUtils.getScreenHeight() - 70, buttonWidth, 30);
        backButton.setClickAction(() -> TextAdventure.getInstance().getGameFrame().loadGuiScreen(parent));

        this.registerElement(textBox);
        this.registerElement(backButton);
    }

    /**
     * draws the screen
     *
     * @param g the graphics object
     */
    @Override
    public void drawScreen(Graphics g) { }

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
