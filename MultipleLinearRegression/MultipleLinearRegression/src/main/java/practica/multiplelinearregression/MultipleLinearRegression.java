/*
* AUTOR = Victor Manuel Iñiguez Mercado
* TRABAJO = Hands-on 6
*/
package practica.multiplelinearregression;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class MultipleLinearRegression extends Agent {
    
    @Override
    protected void setup(){
        System.out.println("Agente " + getLocalName() + " iniciado");
        addBehaviour(new MyOneShotBehaviour());
    }
    
    private class MyOneShotBehaviour extends OneShotBehaviour {

        @Override
        public void action() {
            System.out.println("- Agente en accion -");
            Implementacion Benetton = new Implementacion();
            Benetton.Metodo();
        } 
    
        @Override
        public int onEnd() {
        System.out.println("-Agente muriendo-");
        myAgent.doDelete();   
        return super.onEnd();
        } 
    }    
}
