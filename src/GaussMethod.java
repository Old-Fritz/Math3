import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GaussMethod {
    public List<Double> calculate(List<List<Double>> A, List<Double> B)
    {
        List<Double> list  = new ArrayList<>();


        //return list;


        SimpleMatrix matrixA = new SimpleMatrix(A.size(), A.size());
        SimpleMatrix matrixB = new SimpleMatrix(B.size(),1);
        for(int  i  = 0;i<A.size();i++)
        {
            for(int j = 0;j<A.size();j++)
            {
                matrixA.set(i,j,A.get(i).get(j));
            }
            matrixB.set(i,0,B.get(i));
        }
        SimpleMatrix result = matrixA.solve(matrixB);
        for(int i = 0;i<B.size();i++)
        {
            list.add(result.get(i,0));
        }

        return list;
    }
}
