
//import scanner
import java.util.Arrays;

public class Signal {
    int frequency = 0;
    double[] data;
    double time = 0;
    //constructor for test class with no info
    Signal(){
        this.frequency = 1000;
        this.data = new double[frequency];
        this.time = 1;
    }

    //constructor for test class with frequency fill with 0s
    Signal(int frequency, double time){
        this.frequency = frequency;
        this.data = new double[(int)Math.round(frequency*time)];
        Arrays.fill(this.data, 0);
        this.time = time;
    }

    //constructor for test class with frequency and data
    Signal(int frequency, double[] data){
        this.frequency = frequency;
        this.data = data.clone();
        this.time = data.length/frequency;
    } 

    Signal sousEchantillonner(int frequency){
        double ratio = this.frequency/frequency;
        if(ratio != Math.round(ratio)){
            return null;
        }
        double[] newData = new double[this.data.length/(int)ratio];
        for(int i = 0; i < this.data.length; i++){
            if(i%(int)ratio == 0){
                newData[i/(int)ratio] = this.data[i];
            }
        }
        return new Signal(frequency, newData);
    }

    Signal filtreMoyenneur(int range){
        double[] newData = new double[this.data.length];
        for(int i = 0; i < this.data.length; i++){
            double sum = 0;
            for(int j = i-range; j <= i+range; j++){
                if(j >= 0 && j < this.data.length){
                    sum += this.data[j];
                }
                else if(j < 0){
                    sum += this.data[0];
                }
                else{
                    sum += this.data[this.data.length-1];
                }
            }
            newData[i] = sum/(2*range+1);
        }
        return new Signal(this.frequency, newData);
    }

    double mean(){
        double sum = 0;
        for(int i = 0; i < this.data.length; i++){
            sum += this.data[i];
        }
        return sum/this.data.length;
    }

    double corelationCoefManual(Signal signal){
        if(this.data.length != signal.data.length){
            return 0.0;
        }
        double sum1 = 0;
        double sum2 = 0;
        double sum3 = 0;
        for(int i = 0; i < this.data.length; i++){
            sum1 += (this.data[i] - this.mean())*(signal.data[i] - signal.mean());
            sum2 += (this.data[i] - this.mean())*(this.data[i] - this.mean());
            sum3 += (signal.data[i] - signal.mean())*(signal.data[i] - signal.mean());
        }
        return sum1/(Math.sqrt(sum2*sum3));
    }

    /* double[][] FastFourierTransformManu(double[] data){
        if(data.length == 1){
            return new double[][]{{data[0], 0}};
        }
        double[] odd = new double[(int)Math.floor(data.length/2)];
        double[] even = new double[(int)Math.ceil(data.length/2)];
        for(int i = 0; i < even.length; i++){
            even[i] = data[i*2];
            if(i < odd.length){
                odd[i] = data[i*2+1];
            }
        }
        double[][] oddFFT = FastFourierTransform(odd);
        double[][] evenFFT = FastFourierTransform(even);
        double[][] FFT = new double[data.length][2];
        for(int i = 0; i < data.length/2; i++){
            FFT[i][0] = evenFFT[i][0];
            FFT[i][1] = Math.exp(-2*Math.PI*i/data.length)*oddFFT[i][0] + Math.exp(-2*Math.PI*i/data.length)*oddFFT[i][1]+evenFFT[i][1];
            FFT[i+data.length/2][0] = evenFFT[i][0];
            FFT[i+data.length/2][1] = -Math.exp(-2*Math.PI*i/data.length)*oddFFT[i][0] + Math.exp(-2*Math.PI*i/data.length)*oddFFT[i][1]+evenFFT[i][1];
        }
        return FFT;
    } */

    double[][] FastFourierTransform(double[] data){
        int n = data.length;
        if(n == 1){
            return new double[][]{{data[0], 0}};
        }
        double[] even = new double[n/2];
        double[] odd = new double[n/2];
        for(int i = 0; i < n/2; i++){
            even[i] = data[2*i];
            odd[i] = data[2*i+1];
        }
        double[][] evenFFT = FastFourierTransform(even);
        double[][] oddFFT = FastFourierTransform(odd);
        double[][] result = new double[n][2];
        for(int i = 0; i < n/2; i++){
            double angle = -2*Math.PI*i/n;
            double real = Math.cos(angle);
            double imag = Math.sin(angle);
            result[i][0] = evenFFT[i][0] + real*oddFFT[i][0] - imag*oddFFT[i][1];
            result[i][1] = evenFFT[i][1] + real*oddFFT[i][1] + imag*oddFFT[i][0];
            result[i+n/2][0] = evenFFT[i][0] - real*oddFFT[i][0] + imag*oddFFT[i][1];
            result[i+n/2][1] = evenFFT[i][1] - real*oddFFT[i][1] - imag*oddFFT[i][0];
        }
        return result;
    }
    
}
