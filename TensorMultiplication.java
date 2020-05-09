package LinearAlgebra;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TensorMultiplication {

    private static final Integer[] zero = {1,0};
    private static final Integer[] one = {0,1};

    private static final String[] zerostr = {"1","0"};
    private static final String[] onestr = {"0","1"};


    public static void main(String[] args) {
        System.out.println("Enter your tensor product:");
//        String equation = System.console().readLine();
        String equation = "aXb";


        String[] eq = equation.split("X");
        System.out.println(tensorMultiplyAlgebraMatrices(eq));
    }

    public static String tensorMultiplyRowVectors(String[] eq){
        String out = "";
        List<Integer[]> vectoreq = new ArrayList<>();

        //change kets into vectors
        for (int v = 0; v < eq.length; v++) {

            if(eq[v].equals("0")){
                vectoreq.add(zero);
            }
            else if(eq[v].equals("1")){
                vectoreq.add(one);
            }

            else{

                String[] vector = eq[v].split("");
                Integer[] vec = Arrays.stream(vector).mapToInt(x-> Integer.parseInt(x)).boxed().toArray(Integer[]::new);
                vectoreq.add(vec);
            }

        }

        //do the tensor multiplication
        List<Integer> a = new ArrayList<>();
        a = Arrays.asList(vectoreq.get(0));

        for (int v = 1; v < eq.length; v++) {
            List<Integer> u = new ArrayList<>();
            for (int ai: a ) {
                for (int b : vectoreq.get(v)) {
                    u.add(ai*b);
                }
            }
            a = u;
        }

        out = a.toString();
        return out;
    }


    public static String tensorMultiplyMatrices(String[] eq){
        String out = "";
        //List<Integer[]> vectoreq = new ArrayList<>();

        List<List<Integer>> a = new ArrayList<>();
        if(eq[0].equals("0")){
            a.add(Arrays.asList(zero));
        }
        else if(eq[0].equals("1")){
            a.add(Arrays.asList(one));
        }
        //matrix has / at each break
        else{
            String[] matrix = eq[0].split("/");
            for(String row: matrix){
                String[] vector = row.split("");
                Integer[] vec = Arrays.stream(vector).mapToInt(x-> Integer.parseInt(x)).boxed().toArray(Integer[]::new);
                a.add(Arrays.asList(vec));
            }
        }

        //change kets into vectors
        for (int v = 1; v < eq.length; v++) {
            List<Integer[]> b = new ArrayList<>();
            if(eq[v].equals("0")){
                b.add(zero);
            }
            else if(eq[v].equals("1")){
                b.add(one);
            }
            //matrix has / at each break
            else{
                String[] matrix = eq[v].split("/");
                for(String row: matrix){
                    String[] vector = row.split("");
                    Integer[] vec = Arrays.stream(vector).mapToInt(x-> Integer.parseInt(x)).boxed().toArray(Integer[]::new);
                    b.add(vec);
                }
            }

            //now have prepared next ket as b, do aXb
            List<List<Integer>> axb = new ArrayList<>();
            for (List<Integer> ai:a){
                for (Integer[] bi:b) {
                    axb.add(tensormultiplyrow(ai, bi));
                }
            }
            a = axb;
        }

        out = a.toString();
        return out;
    }


    public static List<Integer> tensormultiplyrow(List<Integer> a, Integer[] b){
        List<Integer> u = new ArrayList<>();
        for (int ai: a ) {
            for (int bi : b) {
                u.add(ai*bi);
            }
        }
        return u;
    }


    public static String tensorMultiplyAlgebraMatrices(String[] eq){
        String out = "";
        //List<Integer[]> vectoreq = new ArrayList<>();

        List<List<String>> a = new ArrayList<>();
        if(eq[0].equals("0")){
            a.add(Arrays.asList(zerostr));
        }
        else if(eq[0].equals("1")){
            a.add(Arrays.asList(onestr));
        }
        //matrix has / at each break
        else{
            String[] matrix = eq[0].split("/");
            for(String row: matrix){
                String[] vector = row.split(" ");//splits again on spaces to get each element
                //Integer[] vec = Arrays.stream(vector).mapToInt(x-> Integer.parseInt(x)).boxed().toArray(Integer[]::new);
                a.add(Arrays.asList(vector));
            }
        }

        //change kets into vectors
        for (int v = 1; v < eq.length; v++) {
            List<String[]> b = new ArrayList<>();
            if(eq[v].equals("0")){
                b.add(zerostr);
            }
            else if(eq[v].equals("1")){
                b.add(onestr);
            }
            //matrix has / at each break
            else{
                String[] matrix = eq[v].split("/");
                for(String row: matrix){
                    String[] vector = row.split("");
                    //Integer[] vec = Arrays.stream(vector).mapToInt(x-> Integer.parseInt(x)).boxed().toArray(Integer[]::new);
                    b.add(vector);
                }
            }

            //now have prepared next ket as b, do aXb
            List<List<String>> axb = new ArrayList<>();
            for (List<String> ai:a){
                for (String[] bi:b) {
                    axb.add(tensoralgebramultiplyrow(ai, bi));
                }
            }
            a = axb;
        }

        out = a.toString();
        return out;
    }


    public static List<String> tensoralgebramultiplyrow(List<String> a, String[] b){
        List<String> u = new ArrayList<>();
        for (String ai: a ) {
            for (String bi : b) {
                if(String || Float.valueOf(bi).isNaN()){
                    u.add(ai + bi);
                }
                else {
                    u.add(Float.valueOf(ai)*Float.valueOf(bi) + "");
                }
            }
        }
        return u;
    }
}