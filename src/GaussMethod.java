import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GaussMethod {
    public List<Double> calculate(List<List<Double>> A, List<Double> B)
    {
        List<Double> list  = new ArrayList<>();
        for(int i = 0;i<B.size();i++)
        {
            list.add((new Random().nextDouble()));
        }

        return list;
    }
}
