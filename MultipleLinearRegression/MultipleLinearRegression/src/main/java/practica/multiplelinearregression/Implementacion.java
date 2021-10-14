package practica.multiplelinearregression;

public class Implementacion implements Interfaz {
    
    private double[][] MatrizX;
    private double[][] VectorY;
    private double[][] TranspuestaX;
    
    public Implementacion(){
        this.MatrizX = new double[][]{{1, 41.9, 29.1}, {1, 43.4, 29.3}, {1, 43.9, 29.5}, {1, 44.5, 29.7},
                                      {1, 47.3, 29.9}, {1, 47.5, 30.3}, {1, 47.9, 30.5}, {1, 50.2, 30.7},
                                      {1, 52.8, 30.8}, {1, 53.2, 30.9}, {1, 56.7, 31.5}, {1, 57.0, 31.7},
                                      {1, 63.5, 31.9}, {1, 65.3, 32.0}, {1, 71.1, 32.1}, {1, 77.0, 32.5}, {1, 77.8, 32.9} };
        
        this.VectorY = new double[][]{{251.3}, {251.3}, {248.3}, {267.5}, {273.0}, {276.5}, {270.3}, {274.9}, {285.0},
                                      {290.0}, {297.0}, {302.5}, {304.5}, {309.3}, {321.7}, {330.7}, {349.0} };
        
        this.TranspuestaX = new double[this.MatrizX[0].length][this.MatrizX.length];
    }
    
    @Override
    public void Metodo() {
        this.TranspuestaX = Transpuesta(this.MatrizX);
        
        double[][] XtX = new double[this.TranspuestaX.length][this.MatrizX[0].length];
        XtX = Multiplicacion(this.TranspuestaX,this.MatrizX);
        
        double[][] XtY = new double[this.TranspuestaX.length][this.VectorY[0].length];
        XtY = Multiplicacion(this.TranspuestaX,this.VectorY);
        
        double[][] inversa = Inversa(XtX);
        
        double[][] resultado = Multiplicacion(inversa, XtY);
        
        print_(resultado);
    }

    @Override
    public double[][] Multiplicacion(double[][] valor1, double[][] valor2) {
        int filas1 = valor1.length; 
        int columnas1 = valor1[0].length;
        //3x17
        
        int filas2 = valor2.length;
        int columnas2 = valor2[0].length;
        //17x3
        
        double[][] resultado = new double [filas1][columnas2];
        
        for(int i = 0; i < filas1; i++){
            for(int j = 0; j < columnas2; j++){
                for(int k = 0; k < columnas1; k++){
                    resultado[i][j] += valor1[i][k] * valor2[k][j];
                }
            }
        }
        return resultado;
    }
    
    @Override
    public void MultiplicacionEscalar(double Escalar, double[][] matriz) {
        for(int i = 0;i < matriz.length; i++){
            for(int j = 0;j < matriz.length; j++){
                matriz[i][j] *= Escalar;
            }      
        }
    }

    @Override
    public double[][] Transpuesta(double[][] matriz) {

        int filas = matriz.length;
        int columnas = matriz[0].length;
        
        double[][] aux = new double[columnas][filas];
        
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                aux[j][i] = matriz[i][j];
            }
        }
        return aux;
    }
    
    @Override
    public double[][] Inversa(double[][] matriz) {
        
        double determinante = 1 / Determinante(matriz);
        double[][] aux = Adjunta(matriz);
        MultiplicacionEscalar(determinante, aux);
        
        return aux;
    }

    @Override
    public double Determinante(double[][] matriz) {
        double determinante;
        if(matriz.length == 2){
            determinante = (matriz[0][0] * matriz[1][1]) - (matriz[1][0] * matriz[0][1]);
            return determinante;
        }
        double suma = 0;
        for(int i = 0; i < matriz.length; i++){
        double[][] aux = new double[matriz.length - 1][matriz.length - 1];
            for(int j = 0; j < matriz.length; j++){
                if(j != i){
                    for(int k = 1; k < matriz.length; k++){
                        int indice = -1;
                        if(j < i)
                            indice = j;
                        else if(j > i)
                            indice = j - 1;                    
                            aux[indice][k - 1] = matriz[j][k];
                    }
                }
            }
            if(i%2 == 0)
                suma += matriz[i][0] * Determinante(aux);
            else
                suma -= matriz[i][0] * Determinante(aux);
        }
        return suma;
    }

    @Override
    public double[][] Cofactores(double[][] matriz) {
        
        double[][] cofactores = new double[matriz.length][matriz.length];
        
        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz.length; j++) {
                double[][] aux = new double[matriz.length-1][matriz.length-1];
                double celda;
                for(int k = 0;k < matriz.length; k++) {
                    if(k != i) {
                        for(int l = 0;l < matriz.length; l++) {
                            if(l != j){
                                int indice1 = k < i ? k : k - 1 ;
                                int indice2 = l < j ? l : l - 1 ;
                                aux[indice1][indice2] = matriz[k][l];
                            }
                        }
                    }
                }
                celda = Determinante(aux);
                cofactores[i][j] = celda * (double)Math.pow(-1, i + j + 2);
            }
        }
        return cofactores;
    }

    @Override
    public double[][] Adjunta(double[][] matriz) {
        return Transpuesta(Cofactores(matriz));
    }
    
    @Override
    public void print_(double[][] matriz){
        for(double[] fila : matriz){
            for(double columna : fila){
                System.out.print(columna + " ");
            }
        System.out.println("");
        }
    }
}