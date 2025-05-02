import java.util.List;

public class Livre {
    private int isbn;
    private String titre;
    private double prix;
    private int nombrePage;
    private String datePubli;
    private Auteur auteur;
    private Editeur editeur;
    private List<String> classifications;
    
    public Livre(int isbn,String titre,double prix,int pages,String datePubli,Auteur aut,Editeur editeur){
        this.isbn=isbn;
        this.auteur=aut;
        this.titre=titre;
        this.datePubli=datePubli;
        this.nombrePage=pages;
        this.editeur = editeur;
    }