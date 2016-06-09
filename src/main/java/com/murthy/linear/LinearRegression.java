package com.murthy.linear;

import com.murthy.domains.Data;
import com.murthy.domains.Params;
import com.murthy.domains.ReadData;

import java.util.ArrayList;

/**
 * @author dmalladi
 */
public class LinearRegression {

    ArrayList<Data> dataList;
    Params parameters;
    double alpha;

    public static void main(String[] args) {

        ReadData dataSet = new ReadData();
        dataSet.readingData();
        ArrayList<Data> list = dataSet.getDataList();
        Params params = new Params();
        LinearRegression linearReg = new LinearRegression(list,params,0.01);
        linearReg.learn();

        System.out.println(linearReg.parameters.getTheta0()+", " + linearReg.parameters.getTheta1());
        System.out.println(linearReg.classify(7));

    }

    public double classify(double value) {
        double h = this.parameters.getTheta0() + this.parameters.getTheta1() * value;

        return h;
    }
    LinearRegression(ArrayList<Data> list,Params params,double alpha) {
        this.dataList = list;
        this.parameters = params;
        params.setTheta0(0);
        params.setTheta1(0);
        this.alpha = alpha;
    }

    public void learn() {

        boolean convergence = true;
        int count = 0;
        while (count < 1500)  {
            iteration();
            count++;
        }
    }
    public boolean iteration() {
        final float convergenceMin = 0.0000001f;
      //  double thetaZeroTemp = this.parameters.getTheta0() - this.alpha * this.computeEstimateZero(this.dataList);

        double[] thetaTemp = new double[2];
        thetaTemp[0] = this.parameters.getTheta0() - this.alpha * this.computeEstimateZero(this.dataList);
        thetaTemp[1] = this.parameters.getTheta1() - this.alpha * this.computeEstimateN(this.dataList);

        boolean convergence = true;

        if (Math.abs(thetaTemp[0] - this.parameters.getTheta0()) > convergenceMin)
            convergence = false;
        this.parameters.setTheta0(thetaTemp[0]);

        if (Math.abs(thetaTemp[1] - this.parameters.getTheta1()) > convergenceMin)
            convergence = false;
        this.parameters.setTheta1(thetaTemp[1]);

        return convergence;

    }


    public double computeEstimate(Data data) {
        double temp0 = this.parameters.getTheta0();
        double temp1 = this.parameters.getTheta1();

        return temp0 + temp1*data.x;

    }
    public double computeEstimateZero(ArrayList<Data> list) {
        int length = list.size();
        double sum = 0;
        Data data;
        for (int i = 0; i < length; i++) {
            data = list.get(i);
            sum = sum + (computeEstimate(data) - data.y);
        }

        sum = sum / length;
        return sum;
    }

    public double computeEstimateN(ArrayList<Data> list) {
        int length = list.size();
        double sum = 0;
        Data data;
        for (int i = 0; i < length; i++) {
            data = list.get(i);
            sum = sum + (computeEstimate(data) - data.y)*data.x;
        }

        sum = sum / length;
        return sum;
    }

}
