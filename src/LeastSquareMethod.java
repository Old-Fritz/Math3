import java.util.ArrayList;
import java.util.List;

public class LeastSquareMethod {
    public Function caclulate(List<Double> X, List<Double> Y)
    {
        List<List<Double>> B = new ArrayList<List<Double>>();
        List<Double> C = new ArrayList<Double>();

        for(int i = 0;i<X.size();i++)
        {
            List<Double> Brow = new ArrayList<Double>();
            double c = 0;
            for(int j = 0;j<X.size();j++)
            {
                c+=Y.get(j)*Math.pow(X.get(j), i);
                double b = 0;
                for(int k = 0;k<X.size();k++)
                {
                    b+=Math.pow(X.get(k), i+j);
                }
                Brow.add(b);
            }
            B.add(Brow);
            C.add(c);
        }

        List<Double> koefs = (new GaussMethod()).calculate(B,C);

        return new Function(koefs);
    }
}
