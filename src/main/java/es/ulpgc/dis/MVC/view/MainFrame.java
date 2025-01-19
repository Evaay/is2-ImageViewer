package es.ulpgc.dis.MVC.view;

import es.ulpgc.dis.MVC.control.Command;
import es.ulpgc.dis.MVC.model.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private ImageDisplay imageDisplay;
    private final Map<String, Command> commands;

    public MainFrame() {
        this.commands = new HashMap<>();
        setTitle("Image Viewer");
        setSize(800, 673);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(createImageDisplay());
        add(createToolbar(), BorderLayout.SOUTH);
    }

    public MainFrame initMainFrame(Image image) {
        this.imageDisplay.show(image);
        return this;
    }

    private Component createToolbar() {
        JPanel panel = new JPanel();
        panel.add(createButton("<"));
        panel.add(createButton(">"));
        return panel;
    }

    private Component createButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get(label).execute();
            }
        });
        return button;
    }

    private Component createImageDisplay() {
        SwingImageDisplay display = new SwingImageDisplay();
        this.imageDisplay = display;
        return display;
    }

    public MainFrame add(String name, Command command) {
        commands.put(name, command);
        return this;
    }

    public ImageDisplay imageDisplay() {
        return imageDisplay;
    }
}
