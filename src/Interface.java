
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Interface{
    JFrame frame;
    JPanel mainPanel;
    JPanel inDataPanel;
    JPanel scrollInData;
    JPanel outDataPanel;
    JPanel graphicsPanel;

    Interface()
    {
        frame = new JFrame("VMLab3");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        createPanels();
        frame.setVisible(true);
    }

    void createPanels()
    {
        GridBagConstraints c = new GridBagConstraints();

        mainPanel = new JPanel(new GridBagLayout());
        frame.setContentPane(mainPanel);

        createInDataPanel();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.2f;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        mainPanel.add(inDataPanel,c);

        createOutDataPanel();
        c.gridx = 1;
        c.weightx = 0.2f;
        mainPanel.add(outDataPanel,c);

        createGraphicsPanel();
        c.gridx = 2;
        c.weightx = 1;
        mainPanel.add(graphicsPanel,c);


    }

    private void createInDataPanel()
    {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;

        inDataPanel = new JPanel();
        inDataPanel.setLayout(new GridBagLayout());
        inDataPanel.setBackground(Color.ORANGE);

        //label
        JLabel label = new JLabel("Введите точки:");
        label.setAlignmentX(0.5f);
        c.gridy = 0;
        c.weighty = 0.05f;
        inDataPanel.add(label,c);

        // scroll Panel
        scrollInData = new JPanel();
        scrollInData.setLayout(new BoxLayout(scrollInData,BoxLayout.PAGE_AXIS));
        JScrollPane listScroller = new JScrollPane(scrollInData);
        listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        listScroller.setAlignmentX(0.5f);
        c.gridy = 1;
        c.weighty = 0.8f;
        inDataPanel.add(listScroller,c);

        // Buttons
        JPanel buttons = new JPanel(new GridLayout(0,1));
        //buttons.setAlignmentX(0.5f);
        c.gridy = 2;
        c.weighty = 0.15f;
        inDataPanel.add(buttons,c);

        // add Button
        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(e->addPoint(0,0));
        buttons.add(addButton);

        // load Button
        JButton loadButton = new JButton("Загрузить");
        buttons.add(loadButton);

        // save Button
        JButton saveButton = new JButton("Сохранить");
        buttons.add(saveButton);
    }

    private void createOutDataPanel()
    {
        outDataPanel = new JPanel(new GridBagLayout());
        outDataPanel.setBackground(Color.RED);
        outDataPanel.add(new JButton("Button 1"));
    }

    private void createGraphicsPanel()
    {
        graphicsPanel = new JPanel(new GridBagLayout());
        graphicsPanel.setBackground(Color.GREEN);
        graphicsPanel.add(new JButton("Button 1"));
    }

    public void addPoint(double x, double y)
    {
        JTextField xField = new JTextField(Double.toString(x));
        xField.setMaximumSize(new Dimension(1000,20));
        JTextField yField = new JTextField(Double.toString(y));
        yField.setMaximumSize(new Dimension(1000,20));
        JButton delButton = new JButton("Удалить");
        delButton.setMaximumSize(new Dimension(1000,20));

        delButton.setActionCommand("deletePoint");
        delButton.addActionListener(e->deletePoint(delButton));

        scrollInData.add(xField);
        scrollInData.add(yField);
        scrollInData.add(delButton);
        scrollInData.updateUI();
    }

    private void deletePoint(JButton button)
    {
        List<Component> list = new ArrayList<Component>(Arrays.asList(scrollInData.getComponents()));
        int index = list.indexOf(button);
        scrollInData.remove(index);
        scrollInData.remove(index-1);
        scrollInData.remove(index-2);
        scrollInData.updateUI();
    }
}
