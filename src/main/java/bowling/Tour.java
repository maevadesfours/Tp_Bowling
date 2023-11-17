/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bowling;

import java.util.ArrayList;

public class Tour {
	private ArrayList<Lancer> lesLancers;
	private int num;

	public Tour (ArrayList<Lancer> lesLancers, int num){
		this.lesLancers = lesLancers;
		this.num = num;
	}
	public Tour(int num){
		this.num=num;
	}
	public int  Score(){
		int score=0;
		for (Lancer l:lesLancers){
			score=score + l.getnbQuillesTombees();
		}
		return score;
	}
	public Boolean Spare(){
		Boolean res = false;
		if (Score()==10 && lesLancers.size()==2)
			res= true;
		return res;
	}

	public Boolean Strike(){
		Boolean res = false;
		if (Score()==10 && lesLancers.size()==1)
			res= true;
		return res;
	}
	public ArrayList<Lancer> getLancer() {
		return lesLancers;
	}
	public double bonus(){
		double ret=0;
		Tour tourprecedent=new Tour(num-1);
		//fait un spare
		if(Spare()==true) {
			ret=lesLancers.get(0).getnbQuillesTombees();
		}
		//fait un strike
		if(Strike()==true){
			ret=lesLancers.get(0).getnbQuillesTombees()+lesLancers.get(1).getnbQuillesTombees();
		}
		return ret;
	}

}