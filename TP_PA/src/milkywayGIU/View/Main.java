/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milkywayGIU.View;

import milkywayGIU.Model.Model;
import milkyway_logic.gameplanner.Game;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
            Model m = new Model(new Game());
            
		new IuGraphic(m);

	}

}