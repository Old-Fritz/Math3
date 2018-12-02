import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Drawer extends JPanel {
    private class Point
    {
        double x,y;
        Color color;
        Point(double x, double y, Color color)
        {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }
    private class Line
    {
        double x1,x2,y1,y2;
        Color color;

        public Line(double x1, double y1, double x2, double y2, Color color) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
            this.color = color;
        }
    }

    List<Point> points;
    List<Line> lines;
    double height;
    double width;
    double minX,minY,maxX, maxY;

    Drawer()
    {
        lines = new ArrayList<Line>();
        points = new ArrayList<Point>();
        height = getSize().height;
        width = getSize().width;
    }

    @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,getSize().width,getSize().height);
        ((Graphics2D)g).setStroke(new BasicStroke(10F));
        for (Point point: points) {
            g.setColor(point.color);
            g.fillOval(convertCoordX(point.x), convertCoordY(point.y),20,20);
        }
        for(Line line:lines)
        {
            g.setColor(line.color);
            g.drawLine(convertCoordX(line.x1),convertCoordY(line.y1),convertCoordX(line.x2),convertCoordY(line.y2));
        }
    }

    public void drawPoints(List<Double> X, List<Double> Y, Color color)
    {
        for(int i = 0;i<X.size();i++)
            drawPoint(X.get(i), Y.get(i), color);
    }

    public void drawPoint(double x, double y, Color color)
    {
        changeBorderCoords(x,y);
        points.add(new Point(x,y,color));
        repaint();
    }

    public void drawLine(double x1, double y1, double x2, double y2, Color color)
    {
        changeBorderCoords(x1,y1);
        changeBorderCoords(x2,y2);
        lines.add(new Line(x1,y1, x2, y2,color));
        repaint();
    }

    public void drawFunction(Function function, Color color)
    {
        double step = width/(double)getSize().width;
        for(int i = 0;i<getSize().width;i++)
        {
            double x = minX+i*step;
            drawLine(x,function.calc(x),x+step,function.calc(x+step),color);
        }
    }

    private int convertCoordX(double x)
    {
        return (int)((double)getSize().width/width*x-minX);
    }

    private int convertCoordY(double y)
    {
        return (int)((double)getSize().height/height*y-minY);
    }

    private void setSize(double x, double y)
    {
        if(x/y>(double)getSize().width/(double)getSize().height)
        {
            width = x;
            height = (double)getSize().height/(double)getSize().width*x;
        }
        else
        {
            height = y;
            width = (double)getSize().width/(double)getSize().height * y;
        }
    }

    private void changeBorderCoords(double x, double y)
    {
        if(x>maxX)
            maxX = x;
        else if(x<minX)
            minY = x;

        if(y>maxY)
            maxY = y;
        else if(y<minY)
            minY = y;

        setSize(maxX-minX, maxY-minY);
    }

    public void clear()
    {
        lines.clear();
        points.clear();
        minX=minY=maxX=maxY=width=height=0;
    }
}
