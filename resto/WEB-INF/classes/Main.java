package main;

import resto.*;
import olona.*;
import java.util.Date;
import connexion.*;
import java.sql.*;
import java.util.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;

public class Main{
	public static void main(String[] args)throws Exception {	

        // Produit p=new Produit();
        // Vector allproduct=p.selectProduit();
        // for (int i=0;i<allproduct.size() ;i++ ) {
        // 	System.out.println(( (Produit) allproduct.elementAt(i)).getNomproduit());
        // 	System.out.println(( (Produit) allproduct.elementAt(i)).getPrix());
        // 	System.out.println(( (Produit) allproduct.elementAt(i)).getPrixIntermediaire());
        // 	System.out.println(( (Produit) allproduct.elementAt(i)).getPrixRevient());
        // }

        // Facture f=new Facture();
        // Produit p=new Produit();
        // Vector idcommande=f.id_commande(8);
        // Vector nomcommande=p.nom_commande(idcommande);
        // Vector allfacture=f.selectFacture(8);
        // for (int i=0;i<allfacture.size() ;i++ ) {
        // 	System.out.println(( (Facture) allfacture.elementAt(i)).getquantite());
        // 	System.out.println(( (Facture) allfacture.elementAt(i)).getidintermediaire());
        // }
        // int idfacture=f.last_facture_id();
        // Date d=f.datezao();
        // Facture fa=new Facture(3,13,7,d);
        // f.insertFacture(fa);

        // Produit rep=p.produit_base(1);

        // System.out.println(idfacture);
        // System.out.println(d);
        // System.out.println(rep.getIdproduit());

		// Facture f=new Facture();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse("2023-12-05");
            Date endDate = dateFormat.parse("2023-12-05");
        //     Vector general = f.statistique_detaille(startDate, endDate);
		// for (int i=0;i<general.size() ;i++ ) {
		// 	System.out.println(( (Vector) general.elementAt(0)).elementAt(i));
		// 	System.out.println(( (Vector) general.elementAt(1)).elementAt(i));
		// 	System.out.println(( (Vector) general.elementAt(2)).elementAt(i));
		// 	System.out.println(( (Vector) general.elementAt(3)).elementAt(i));
		// 	System.out.println(( (Vector) general.elementAt(4)).elementAt(i));
		// }

		Intermediaire i=new Intermediaire();
		i.insert_vola_intermediaire(8,startDate,endDate);  

	}
}