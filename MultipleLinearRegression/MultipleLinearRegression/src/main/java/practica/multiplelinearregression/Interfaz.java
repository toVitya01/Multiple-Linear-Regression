package practica.multiplelinearregression;

public interface Interfaz {
    public void Metodo();
    public double[][] Multiplicacion(double[][] valor1, double[][] valor2);
    public void MultiplicacionEscalar(double Escalar, double[][] matriz);
    public double[][] Transpuesta(double[][] matriz);
    public void print_(double[][] matriz);
    
    public double[][] Inversa(double[][] matriz);
    public double Determinante(double[][] matriz);
    public double[][] Cofactores(double[][] matriz);
    public double[][] Adjunta(double[][] matriz);
}
