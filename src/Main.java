import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Interface swingInterface;
    public static void main(String[] args)
    {
        swingInterface = new Interface();
        while(true)
        {

        }
    }

    public static void calculate(int power)
    {
        LeastSquareMethod method = new LeastSquareMethod();

        List<Double> X = new ArrayList<>();
        List<Double> Y = new ArrayList<>();

        if(!swingInterface.getPoints(X, Y))
            return;

        swingInterface.getDrawer().clear();
        swingInterface.getDrawer().drawPoints(X,Y, Color.RED);

        // first function
        List<Double> koefs = method.caclulate(X, Y,power);
        Function function = new Function(koefs);
        swingInterface.setFirstBKoefs(koefs);
        swingInterface.getDrawer().drawFunction(function, Color.GREEN);

        // delete point
        int extraIndex = findExtraPoint(X,Y, function);
        swingInterface.setDeletedPoint(X.get(extraIndex), Y.get(extraIndex));
        X.remove(extraIndex);
        Y.remove(extraIndex);

        // second function
        koefs = method.caclulate(X,Y, power);
        function = new Function(koefs);
        swingInterface.setSecondBKoefs(koefs);
        swingInterface.getDrawer().drawFunction(function, Color.BLUE);
    }

    private static int findExtraPoint(List<Double> X,List<Double> Y, Function function)
    {
        int maxInd = 0;
        for(int i = 0;i<X.size();i++)
        {
            if(Math.abs(Y.get(i)-function.calc(X.get(i))) > Math.abs(Y.get(maxInd)-function.calc(X.get(maxInd))))
                maxInd = i;
        }
        return maxInd;
    }
}
