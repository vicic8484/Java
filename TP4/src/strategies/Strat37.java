package strategies;

import cantstop.Jeu;
        
/**
 * Votre Stratégie (copie de la Strat0 pour l'instant)
 *
 * Expliquez votre stratégie en une 20aine de lignes maximum.
 *
 * RENDU: Ce fichier, correctement nommé et rempli.
 * Votre Stratégie aura un numéro (pour être similaire à Strat0 qui sera votre position dans l'alphabet de la promo + 14. 
 * (attention calcul compliqué) Le premier aura donc pour numéro 15 et le dernier 334
 * Pour "préparer" votre stratégie, sur le fichier StratX.java, vous cliquez sur Bouton Droit, Refactor, Rename et vous 
 * nommez bien votre stratégie genre Strat245.java (pour le 231e).
 *
 * @author Victor Berhault
 */

public class Strat37 implements Strategie {
    private int [][] lesChoix = new int[6][2];
    private int [][] bonzes = new int[3][2];
    private int [] maximum = {3,5,7,9,11,13,11,9,7,5,3};
    private int [] avancementAdversaire = new int[11];
    private int [] avancement = new int[11];
    private boolean [] bloque = new boolean[11];
    private int probaLimite = 95;
    private int scoreDouble = 100;
    private double scoreDistMax = 2.25;
    private int scoreDistAdv = 1;
    private double scoreBonzeExiste = 45;
    private int scoreFinishLine = 225;
    private int scoreProba = 1;
    private int score = 0;
    private int scoreAdv = 0;
    private int tries = 0;
    private double[] probas = {.1320463,.2332046,.3559846,.4478764,.5613900,.6440154,.5613900,.4478764,.3559846,.2332046,.1320463};

    /**
    * @param j le jeu
    * @return toujours le 1er choix
    */
   public int choix(Jeu j) {
    int scorePos;
    int scoreMax = -500;
    int posScoreMax = 1;
    this.lesChoix = j.getLesChoix();
    this.bonzes = j.getBonzes();
    this.bloque = j.getBloque();
    this.avancementAdversaire = j.avancementAutreJoueur();
    this.avancement = j.avancementJoueurEnCours();
    for(int i = 0; i < j.getNbChoix(); i++){
        scorePos = scorePosition(i,j);
        if(scorePos>scoreMax){
            scoreMax = scorePos;
            posScoreMax = i;
        }
    }
    return posScoreMax;
   }

   /**
    * @param j le jeu
    * @return toujours vrai (pour s'arrêter)
    */
   public boolean stop(Jeu j) {
        double proba = 1;
        this.score = j.scoreJoueurEnCours();
        this.scoreAdv = j.scoreAutreJoueur();
        this.bonzes = j.getBonzes();
        this.bloque =j.getBloque();
        boolean score1 = false;

        if(j.getBonzesRestants()>0){
            tries = 0;
            return false;
        }

        for(int i = 0;i<3; i++){
            if(bonzes[i][1] == maximum[bonzes[i][0]-2]){
                score1 = true;
            }
        }
        if(score1){
            return true;
        }

        for (int i = 0; i < 3; i++) {
            if(!bloque[bonzes[i][0]-2]){
                proba *= 1-probas[bonzes[i][0]-2];
            }
            
        }
        if(proba*100>probaLimite){
            return false;
        }


       return j.getNbCoup()<4;
   }

   int scorePosition(int pos, Jeu j){
        int score = 0;
        int valeur = j.possible(this.lesChoix[pos][0]);
        if(this.lesChoix[pos][1] != 0){
            valeur+=j.possible(this.lesChoix[pos][1]);
        }
        if(valeur == 2) score += scoreDouble;
        for(int i = 0; i < 3; i++){
            if(bonzes[i][0] == this.lesChoix[pos][0]){
                score += scoreDistMax*(bonzes[i][1]-maximum[this.lesChoix[pos][0]-2]);
                score += scoreDistAdv*(bonzes[i][1]-avancementAdversaire[this.lesChoix[pos][0]-2]);
                score += scoreBonzeExiste;
                if(bonzes[i][0] == maximum[this.lesChoix[pos][0]-2]-1){
                    score += scoreFinishLine;
                    if(score == 2){
                        score += 999999;
                    }
                }
            }
            if(bonzes[i][0] == this.lesChoix[pos][1] && this.lesChoix[pos][1] != 0){
                score += scoreDistMax*(bonzes[i][1]-maximum[this.lesChoix[pos][1]-2]);
                score += scoreDistAdv*(bonzes[i][1]-avancementAdversaire[this.lesChoix[pos][1]-2]);
                score += scoreBonzeExiste;
                if(bonzes[i][0] == maximum[this.lesChoix[pos][1]-2]-1){
                    score += scoreFinishLine;
                    if(score == 2){
                        score += 999999;
                    }
                }                
            }
        }
        score += scoreDistMax*(avancement[j.getLesChoix()[pos][0]-2]-maximum[j.getLesChoix()[pos][0]-2]);
        score += scoreDistAdv*(avancement[j.getLesChoix()[pos][0]-2]-avancementAdversaire[j.getLesChoix()[pos][0]-2]);
        score += scoreProba*(1-probas[j.getLesChoix()[pos][0]-2]);
        if(j.getLesChoix()[pos][1] != 0){
            score += scoreDistMax*(avancement[j.getLesChoix()[pos][1]-2]-maximum[j.getLesChoix()[pos][1]-2]);
            score += scoreDistAdv*(avancement[j.getLesChoix()[pos][1]-2]-avancementAdversaire[j.getLesChoix()[pos][1]-2]);
        }
        //System.out.println("Score de la position "+j.getLesChoix()[pos][1]+" : "+score);
        return score;
   }

   /**
    * @return vos noms
    */
   public String getName() {
       return "Victor Berhault";
   }
}
