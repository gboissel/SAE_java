package controleur;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;

public class ControleurClient1 extends Controleur{
    private int nbPage = 0;
    @FXML
    private Button btnDeco;

    @FXML
    private Button btnMag1;

    @FXML
    private Button btnMag2;

    @FXML
    private Button btnMag3;

    @FXML
    private Button btnMag4;

    @FXML
    private Button btnMag5;

    @FXML
    private Button btnMag6;
    
    @FXML
    private Button btnMag7;

    @FXML
    private Button btnMag8;

    @FXML
    private Button btnMag9;

    @FXML
    private Button btnPrec;

    @FXML
    private Button btnSuiv;

    @FXML
    public void controleurBoutDeco(ActionEvent e){
        System.out.println("vous etes bien deconnecter");
    }

    @FXML
    public void controleurBoutMag1(ActionEvent e){
        this.modele.setCurMag(this.modele.getMagasins().get(0+this.nbPage*9));     
        this.vue.changerVue("/view/VuePageClient2.fxml");
    }

    @FXML
    public void controleurBoutMag2(ActionEvent e){
        this.modele.setCurMag(this.modele.getMagasins().get(1+this.nbPage*9));
        this.vue.changerVue("/view/VuePageClient2.fxml");
    } 

    @FXML
    public void controleurBoutMag3(ActionEvent e){
        this.modele.setCurMag(this.modele.getMagasins().get(2+this.nbPage*9));
        this.vue.changerVue("/view/VuePageClient2.fxml");
    } 

    @FXML
    public void controleurBoutMag4(ActionEvent e){
        this.modele.setCurMag(this.modele.getMagasins().get(3+this.nbPage*9));
        this.vue.changerVue("/view/VuePageClient2.fxml");
    }

    @FXML
    public void controleurBoutMag5(ActionEvent e){
        this.modele.setCurMag(this.modele.getMagasins().get(4+this.nbPage*9));
        this.vue.changerVue("/view/VuePageClient2.fxml");
    }

    @FXML
    public void controleurBoutMag6(ActionEvent e){
        this.modele.setCurMag(this.modele.getMagasins().get(5+this.nbPage*9));
        this.vue.changerVue("/view/VuePageClient2.fxml");
    }

    @FXML
    public void controleurBoutMag7(ActionEvent e){
        this.modele.setCurMag(this.modele.getMagasins().get(6+this.nbPage*9));
        this.vue.changerVue("/view/VuePageClient2.fxml");
    }

    @FXML
    public void controleurBoutMag8(ActionEvent e){
        this.modele.setCurMag(this.modele.getMagasins().get(7+this.nbPage*9));
        this.vue.changerVue("/view/VuePageClient2.fxml");
    }

    @FXML
    public void controleurBoutMag9(ActionEvent e){
        this.modele.setCurMag(this.modele.getMagasins().get(8+this.nbPage*9));
        this.vue.changerVue("/view/VuePageClient2.fxml");
    }
    @FXML
    public void pagePrec(){
        if (this.nbPage>0) {
            this.nbPage-=1;
            this.maJ();
        }

    }

    @FXML
    public void pageSuivante(){
        this.nbPage+=1;
        this.maJ();
    }
    @FXML
    private void accesPanier(){
    }

    private void maJ(){
        int dep = nbPage*9;
        int compteur = 0;
        String nomMag;
        List<Button> btnL = Arrays.asList(this.btnMag1,this.btnMag2,this.btnMag3,this.btnMag4,this.btnMag5,this.btnMag6,this.btnMag7,this.btnMag8,this.btnMag9);
        for(Button btn:btnL){
            if (compteur<10 && (nbPage*9+compteur)<this.modele.getMagasins().size()){
                nomMag = this.modele.getMagasins().get(dep+compteur).getNom();
                btn.setText(nomMag);
                btn.setDisable(false);
            }else{
                btn.setDisable(true);
                btn.setText("N/A");
            }++compteur;
        }
    }              

    @Override
    public void chargerPage(){
        this.nbPage = 0;
        this.maJ();
    }
}
