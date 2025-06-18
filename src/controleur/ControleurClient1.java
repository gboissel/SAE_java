package controleur;
import exception.*;
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
        this.vue.changerVue("view/VuePageClient2.fxml");
    }

    @FXML
    public void controleurBoutMag2(ActionEvent e){
        System.out.println("vous etes sur le magasin2");
    } 

    @FXML
    public void controleurBoutMag3(ActionEvent e){
        System.out.println("vous etes sur le magasin3");
    } 

    @FXML
    public void controleurBoutMag4(ActionEvent e){
        System.out.println("vous etes sur le magasin4");
    }

    @FXML
    public void controleurBoutMag5(ActionEvent e){
        System.out.println("vous etes sur le magasin5");
    }

    @FXML
    public void controleurBoutMag6(ActionEvent e){
        System.out.println("vous etes sur le magasin6");
    }

    @FXML
    public void controleurBoutMag7(ActionEvent e){
        System.out.println("vous etes sur le magasin7");
    }

    @FXML
    public void controleurBoutMag8(ActionEvent e){
        System.out.println("vous etes sur le magasin8");
    }

    @FXML
    public void controleurBoutMag9(ActionEvent e){
        System.out.println("vous etes sur le magasin9");
    }
    @FXML
    public void pagePrec(){
        if (this.nbPage>0) this.nbPage-=1;

    }

    @FXML
    public void pageSuivante(){
        this.nbPage+=1;
    }
    @FXML
    private void accesPanier(){
    }
    @Override
    public void chargerPage(){
        this.nbPage = 0;
        int dep = nbPage*9;
        String nomMag;        
        for (int i=nbPage*9;i<(nbPage+1)*9;i++){// sa s'execute 9 fois pour chaque page
            System.out.println(this.modele.getMagasins().get(i).getNom());

            switch ((i+dep)%9){
            case 0:
                try{
                    nomMag = this.modele.getMagasins().get(i+dep%9).getNom();
                    this.btnMag1.setText(nomMag);
                }catch(Exception e){
                    this.btnMag1.setText("A venir ... Il n'y a plus de magasin.");
                }
                
            case 1:
                try{nomMag = this.modele.getMagasins().get(i+dep%9).getNom();
                    this.btnMag2.setText(nomMag);
                }catch(Exception e){
                    this.btnMag2.setText("A venir ... Il n'y a plus de magasin.");
                }
                
            case 2:
                try{nomMag = this.modele.getMagasins().get(i+dep%9).getNom();
                    this.btnMag3.setText(nomMag);}
                catch(Exception e){
                    this.btnMag3.setText("A venir ... Il n'y a plus de magasin.");
                }
                
            case 3:
                try{
                    nomMag = this.modele.getMagasins().get(i+dep%9).getNom();
                    this.btnMag4.setText(nomMag);
                }catch(Exception e){
                    this.btnMag4.setText("A venir ... Il n'y a plus de magasin.");
                }
                
            case 4:
                try{nomMag = this.modele.getMagasins().get(i+dep%9).getNom();
                    this.btnMag5.setText(nomMag);
                }catch(Exception e){
                    this.btnMag5.setText("A venir ... Il n'y a plus de magasin.");
                }
                
            case 5:
                try{nomMag = this.modele.getMagasins().get(i+dep%9).getNom();
                    this.btnMag6.setText(nomMag);}
                catch(Exception e){
                    this.btnMag6.setText("A venir ... Il n'y a plus de magasin.");
                }
                
            case 6:
                try{nomMag = this.modele.getMagasins().get(i+dep%9).getNom();
                    this.btnMag7.setText(nomMag);}
                catch(Exception e){
                    this.btnMag7.setText("A venir ... Il n'y a plus de magasin.");
                }
                    
            case 7:
                try{nomMag = this.modele.getMagasins().get(i+dep%9).getNom();
                    this.btnMag8.setText(nomMag);
                }catch(Exception e){
                    this.btnMag8.setText("A venir ... Il n'y a plus de magasin.");
                }
                
            case 8:
                try{nomMag = this.modele.getMagasins().get(i+dep%9).getNom();
                    this.btnMag9.setText(nomMag);
                }catch(Exception e){
                    this.btnMag9.setText("A venir ... Il n'y a plus de magasin.");
                }
                
            }
            
        }
    }
}
