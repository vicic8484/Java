public class td2 {
    public static void main(String[] args) {
        double[] sign = {0.86 ,0.81,0.47,0.97,0.23,0.62,0.43,0.67,0.45,0.17,0.15,0.90,0.73,0.04,0.63,0.35};
        //double[] sign = {1,2,3,4,5,6,7,8};
        //Signal testSign = new Signal (4, sign);
        //convert testSign to json
        //String json = "{ \"frequency\": " + testSign.frequency + ", \"data\": " + Arrays.toString(testSign.data) + ", \"time\": " + testSign.time + "}";
        
        double[][] FFT = (new Signal()).FastFourierTransform(sign);
        
        //convert double[][] to json
        String json = "[";
        for(int i = 0; i < FFT.length; i++){
            json += "[" + FFT[i][0] + "," + FFT[i][1] + "]";
            if(i != FFT.length-1){
                json += ",";
            }
        }
        json += "]";

        System.out.println(json); 
    }
}
