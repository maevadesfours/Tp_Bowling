package bowling;


import java.util.ArrayList;
/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancers successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class PartieMonoJoueur {
	private ArrayList<Tour> partie=new ArrayList<>() ;
	public static final int nbTours = 10;
	public static final int quilles = 10;
	private int nTour = 1;
	/**
	 * Constructeur
	 */
	public PartieMonoJoueur() {
		for (int i = 1; i <= nbTours; i++) {
			partie.add(new Tour(i));
	}
		
	/**
	 * Cette méthode doit être appelée à chaque lancer de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de ce lancer
	 * @throws IllegalStateException si la partie est terminée
	 * @return vrai si le joueur doit lancer à nouveau pour continuer son tour, faux sinon	
	 */
		public boolean enregistreLancer(int nombreDeQuillesAbattues) {
			if (estTerminee()) throw new IllegalStateException("la partie est terminée !");

			Lancer l = new Lancer(nombreDeQuillesAbattues);
			boolean continuerTour = partie.get(nTour - 1).enregistreLancer(l);

			if (!continuerTour) {
				if (nTour < nbTours) nTour++;
			}

			return continuerTour;
		}
	

	/**
	 * Cette méthode donne le score du joueur.
	 * Si la partie n'est pas terminée, on considère que les lancers restants
	 * abattent 0 quille.
	 * @return Le score du joueur
	 */
		public int score() {
			int scoreTot = 0;

			for (int i = 0; i < nbTours - 1; i++) {
				Tour tour = partie.get(i);
				scoreTot =scoreTot + tour.getScore();

				if (tour.Spare()) {
					scoreTot += partie.get(i + 1).getScoreLancer(1);
				} else if (tour.Strike()) {
					if (i + 1 == nbTours - 1 || !partie.get(i + 1).Strike()) {
						scoreTot += partie.get(i + 1).getScore();
					} else {
						scoreTot += partie.get(i + 1).getScoreLancer(1) + partie.get(i + 2).getScoreLancer(1);
					}
				}
			}

			Tour dernierTour = partie.get(nbTours - 1);
			scoreTot += dernierTour.getScore();

			if (scoreTot>300){

				scoreTot=300;
			}
			return scoreTot;
		}

	/**
	 * @return vrai si la partie est terminée pour ce joueur, faux sinon
	 */
	public boolean estTerminee() {
			boolean b=false;
			if(partie.get(nTour-1).Terminee()) {
				b = true;
			}
			return b;

		}

	
	/**
	 * @return Le numéro du tour courant [1..10], ou 0 si le jeu est fini
	 */

	public int numeroTourCourant() {
		if (estTerminee()) {
			nTour = 0;
		}
		return nTour;
	}


	/**
	 * @return Le numéro du prochain lancer pour tour courant [1..3], ou 0 si le jeu
	 *         est fini
	 */
	public int numeroProchainLancer() {
		if (estTerminee()) {
			return 0;
		} else if (nTour == nbTours) {
			return partie.get(nbTours - 1).getProchainNumeroCoup();
		} else {
			return partie.get(nTour).getProchainNumeroCoup();
		}
	}
	}
	

	