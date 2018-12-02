
import sun.plugin2.message.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Interface {
    private JFrame frame;
    private JPanel mainPanel;

    private JPanel inDataPanel;
    private JPanel scrollInData;

    private JPanel outDataPanel;
    private JSpinner functionSpinner;
    private JPanel firstBKoefs;
    private JPanel secondBKoefs;
    private JLabel deletedPoint;

    private Drawer graphicsPanel;

    Interface() {
        frame = new JFrame("VMLab3");
        frame.setSize(1100, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        createPanels();
        frame.setVisible(true);
    }

    void createPanels() {
        GridBagConstraints c = new GridBagConstraints();

        mainPanel = new JPanel(new GridBagLayout());
        frame.setContentPane(mainPanel);

        createInDataPanel();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.2f;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        mainPanel.add(inDataPanel, c);

        createOutDataPanel();
        c.gridx = 1;
        c.weightx = 0.2f;
        mainPanel.add(outDataPanel, c);

        createGraphicsPanel();
        c.gridx = 2;
        c.weightx = 1;
        mainPanel.add(graphicsPanel, c);

        addPoint(1,1);
        addPoint(2,4);
        addPoint(3,9);
        addPoint(4,16);
        addPoint(5,25);

    }

    private void createInDataPanel() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;

        inDataPanel = new JPanel();
        inDataPanel.setLayout(new GridBagLayout());

        //label
        JLabel label = new JLabel("Введите точки:");
        label.setAlignmentX(0.5f);
        c.gridy = 0;
        c.weighty = 0.05f;
        inDataPanel.add(label, c);

        // scroll Panel
        scrollInData = new JPanel();
        scrollInData.setLayout(new BoxLayout(scrollInData, BoxLayout.PAGE_AXIS));
        JScrollPane listScroller = new JScrollPane(scrollInData);
        listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        listScroller.setAlignmentX(0.5f);
        c.gridy = 1;
        c.weighty = 0.9f;
        inDataPanel.add(listScroller, c);

        // Buttons
        JPanel buttons = new JPanel(new GridLayout(0, 1));
        //buttons.setAlignmentX(0.5f);
        c.gridy = 2;
        c.weighty = 0.05f;
        inDataPanel.add(buttons, c);

        // add Button
        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(e -> addPoint(0, 0));
        buttons.add(addButton);

        // load Button
        JButton loadButton = new JButton("Загрузить");
        //buttons.add(loadButton);

        // save Button
        JButton saveButton = new JButton("Сохранить");
        //buttons.add(saveButton);
    }

    private void createOutDataPanel() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;

        outDataPanel = new JPanel(new GridBagLayout());
        //outDataPanel.add(new JButton("Button 1"));

        // label
        c.gridy = 0;
        c.weighty = 0.05f;
        outDataPanel.add(new JLabel("Выберите степень функции:"), c);

        // function spinner
        c.gridy = 1;
        c.weighty = 0.05f;
        functionSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 9, 1));
        outDataPanel.add(functionSpinner, c);

        // calc button
        c.gridy = 2;
        c.weighty = 0.05f;
        JButton calcButton = new JButton("Расчитать");
        calcButton.addActionListener(e->calculate());
        outDataPanel.add(calcButton, c);

        // b koefs
        firstBKoefs = new JPanel(new GridLayout(10, 2));
        secondBKoefs = new JPanel(new GridLayout(10, 2));
        for (int i = 0; i < 10; i++) {
            firstBKoefs.add(new Label("B" + i));
            firstBKoefs.add(new Label());
            secondBKoefs.add(new Label("B" + i));
            secondBKoefs.add(new Label());
        }

        c.gridy = 3;
        c.weighty = 0.05f;
        outDataPanel.add(new JLabel("Первые коэффициенты"), c);
        c.gridy = 4;
        c.weighty = 0.2f;
        outDataPanel.add(firstBKoefs, c);

        c.gridy = 5;
        c.weighty = 0.05f;
        outDataPanel.add(new JLabel("Вторые коэффициенты"), c);
        c.gridy = 6;
        c.weighty = 0.2f;
        outDataPanel.add(secondBKoefs, c);


        // deleted point
        c.gridy = 7;
        c.weighty = 0.05f;
        outDataPanel.add(new JLabel("Удвленная точка:"), c);

        deletedPoint = new JLabel("x =   y = ");
        c.gridy = 8;
        c.weighty = 0.05f;
        outDataPanel.add(deletedPoint, c);
    }

    private void createGraphicsPanel() {
        graphicsPanel = new Drawer();
    }

    public void addPoint(double x, double y) {
        JTextField xField = new JTextField(Double.toString(x));
        xField.setMaximumSize(new Dimension(1000, 20));
        JTextField yField = new JTextField(Double.toString(y));
        yField.setMaximumSize(new Dimension(1000, 20));
        JButton delButton = new JButton("Удалить");
        delButton.setMaximumSize(new Dimension(1000, 20));

        delButton.setActionCommand("deletePoint");
        delButton.addActionListener(e -> deletePoint(delButton));

        scrollInData.add(xField);
        scrollInData.add(yField);
        scrollInData.add(delButton);
        scrollInData.updateUI();
    }

    private void deletePoint(JButton button) {
        List<Component> list = new ArrayList<Component>(Arrays.asList(scrollInData.getComponents()));
        int index = list.indexOf(button);
        scrollInData.remove(index);
        scrollInData.remove(index - 1);
        scrollInData.remove(index - 2);
        scrollInData.updateUI();
    }


    public boolean getPoints(List<Double> X, List<Double> Y) {
        if(scrollInData.getComponentCount()<9)
        {
            JOptionPane.showMessageDialog(frame, "Введите от трех точек");
            return false;
        }
        for (int i = 0; i < scrollInData.getComponentCount(); i += 3) {
            try {
                X.add(Double.parseDouble(((JTextField) (scrollInData.getComponent(i))).getText()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Координата X точки под номером " + (i / 2 + 1) + " задана неверно");
                return false;
            }

            try {
                Y.add(Double.parseDouble(((JTextField) (scrollInData.getComponent(i + 1))).getText()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Координата Y точки под номером " + (i / 2 + 1) + " задана неверно");
                return false;
            }
        }
        return true;
    }

    public Drawer getDrawer() {
        return graphicsPanel;
    }

    public void setFirstBKoefs(List<Double> koefs)
    {
        for(int i = 1;i<firstBKoefs.getComponentCount();i+=2)
        {
            if(i/2<koefs.size())
                ((Label)firstBKoefs.getComponent(i)).setText(koefs.get(i/2)+"");
            else
                ((Label)firstBKoefs.getComponent(i)).setText("");
        }
    }
    public void setSecondBKoefs(List<Double> koefs)
    {
        for(int i = 1;i<secondBKoefs.getComponentCount();i+=2)
        {
            if(i/2<koefs.size())
                ((Label)secondBKoefs.getComponent(i)).setText(koefs.get(i/2)+"");
            else
                ((Label)secondBKoefs.getComponent(i)).setText("");
        }
    }

    public void setDeletedPoint(double x, double y)
    {
        deletedPoint.setText("x = " + x+"   y = " + y);
    }

    private void calculate()
    {
        Main.calculate((Integer)functionSpinner.getValue()+1);
    }
}
