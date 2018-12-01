import java.util.List;

public class Function {
    private List<Double> koefs;

    Function(List<Double> koefs)
    {
        this.koefs = koefs;
    }

    double calc(double x)
    {
        double result = 0;
        for(int i = 0;i<koefs.size();i++)
        {
            result += koefs.get(i)*Math.pow(x,i);
        }

        return result;
    }
}
