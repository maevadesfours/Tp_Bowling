/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bowling;

import java.util.ArrayList;

public class Tour {
	private ArrayList<Lancer> lesLancers;
	private int numT;
	private int nCoup=1;
	private boolean terminee = false;

	public Tour ( int numT){
		this.numT = numT;
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
	public boolean Terminee() {
		return terminee;
	}
	public int getScore() {
		int score = 0;
		for (Lancer lancer : lesLancers) {
			score += lancer.getnbQuillesTombees();
		}
		return score;
	}
	public int getScoreLancer(int numLancer) {
		if (numLancer >= 1 && numLancer <= lesLancers.size()) {
			return lesLancers.get(numLancer - 1).getnbQuillesTombees();
		} else {
			return 0;
		}
	}

	public int getProchainNumeroCoup() {
		return nCoup;
	}
	public boolean enregistreLancer(Lancer lancer) {
		if (nCoup == 1) {
			lesLancers.add(lancer);
			if (getScore() == PartieMonoJoueur.quilles && numT != PartieMonoJoueur.nbTours) {
				terminee = true;
			} else if (getScore() == PartieMonoJoueur.quilles) {
				nCoup += 1;
			} else {
				nCoup++;
			}
		} else if (nCoup == 2) {
			lesLancers.add(lancer);
			terminee = true;
			if (numT == PartieMonoJoueur.nbTours && !(getScore() < PartieMonoJoueur.quilles)) {
				nCoup++;
				terminee = false;
			}
		} else if (nCoup == 3) {
			lesLancers.add(lancer);
			terminee = true;
		}

		return !terminee;
	}
}