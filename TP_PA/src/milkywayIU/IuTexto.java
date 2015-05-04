package milkywayIU;

import java.util.Scanner;
import milkyway_logic.gameplanner.Game;

public class IuTexto {
    
    private Game game;
    private boolean isExit;

    public IuTexto() {
        this.isExit = false;
    }
    
  
    public void start(){
        
        printMenu();
        while(!isExit){
            
            System.out.println(" Option:");
            Scanner mScanner = new Scanner(System.in);
            int option = mScanner.nextInt();
            
            switch(option){
                case 1: //startGame();
                    break;
                case 2: //vercenas
                
                case 3: //consultar
                
                case 4: System.out.println(" Thank's for being with us, mate!");
                        isExit = true;
                    return;
                        
            }
            
        }
        
    }
    
    private void printMenu(){
        
        System.out.println(" :::::::::  MILKY WAY EXPRESS  ::::::::::::");
        System.out.println(" 1. Iniciar Jogo");
        System.out.println(" 2. ver cenas");
        System.out.println(" 3. Consultar");
        System.out.println(" 4. Sair");
    }
}
