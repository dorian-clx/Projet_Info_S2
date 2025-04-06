/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet_info_s2;

/**
 *
 * @author doriancacheleux
 */
public class Operation {
    private String nomOp;
    private Poste posteAssocie;
    private double duree;
    private double cout;
    private String typeOperation;         
    private int ordre;

    public String getNomOp() {
        return nomOp;
    }

    public void setNomOp(String nomOp) {
        this.nomOp = nomOp;
    }

    public Poste getPosteAssocie() {
        return posteAssocie;
    }

    public void setPosteAssocie(Poste posteAssocie) {
        this.posteAssocie = posteAssocie;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public Operation(String nomOp, Poste posteAssocie, double duree, double cout, String typeOperation, int ordre) {
        this.nomOp = nomOp;
        this.posteAssocie = posteAssocie;
        this.duree = duree;
        this.cout = cout;
        this.typeOperation = typeOperation;
        this.ordre = ordre;
    }
    
    //méthode pour afficher les caractéristiques d'une opération
    /*public String afficheOperation(){
         return "Opération #" + ordre + 
                 " [" + typeOperation + 
                 "] : " + nomOp +
                 " | Durée : " + duree + 
                 "h | Coût : " + cout + 
                 "€ | Poste : " + posteAssocie.getRefPoste();
    }*/
    
    public String afficheOperation() {
    StringBuilder sb = new StringBuilder();
    sb.append("Opération #").append(ordre)
      .append(" [").append(typeOperation).append("] : ").append(nomOp)
      .append(" | Durée : ").append(duree).append("h")
      .append(" | Coût : ").append(cout).append("€")
      .append(" | Poste : ");

    if (posteAssocie.getIsDeleted() == true) {
            sb.append("Poste supprimé (").append(posteAssocie.getRefPoste()).append(")");
    } 
    else if (posteAssocie.getIsDeleted() == false) {
            sb.append(posteAssocie.getRefPoste());
    } 
    else {
        sb.append("Aucun poste associé");
    }
    return sb.toString();
}
    
}
