package dws;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author ...
 *
 */
public class Program {

	public static void main(String[] args) {

		String csvfile = "C:/Users/Moritz/OneDrive - hochschule-stralsund.de/LP Software/2. Aufgabenblatt/sfd/DWS-TOP-DIVIDENDE.csv";
		String test1 = "";
		String test2 = ";";

		try (BufferedReader in = new BufferedReader(new FileReader(csvfile))){
			int zähler = 0;
			ArrayList<String> aktienwerte = new ArrayList<>();
			String reihe;

			while ((reihe = in.readLine()) != null) {
				String[] test3 = test1.split(csvfile);
				System.out.println(test3[85]);
				aktienwerte.add(reihe);
			}

			ArrayList<String> aktienwertegeklont = new ArrayList<String>();
			for (String ztest : aktienwerte) {
				if(ztest.matches("(?i)(30.12.2011).*") || ztest.matches("(?i)(03.01.2011).*")){
					aktienwertegeklont.add(ztest);

				} else{
					zähler++;
				}
			}
			ArrayList<String> aktienwertegeklontAnfangJahr = new ArrayList<>();
			ArrayList<String> aktienwertegeklontEndeJahr = new ArrayList<>();

			for(int u = 1; u < aktienwertegeklont.size();u++) {
				aktienwertegeklontAnfangJahr.add(aktienwertegeklont.get(u));
			}
			for(int u = 0; u < aktienwertegeklont.size()-1;u++) {
				aktienwertegeklontEndeJahr.add(aktienwertegeklont.get(u));
			}

			String[][] aktienwerte2geklontAnfangJahrgesplitted = new String[aktienwertegeklontAnfangJahr.size()][];
			String[][] aktienwerte2geklontEndeJahrgesplitted = new String[aktienwertegeklontAnfangJahr.size()][];

			for (int i = 0; i < aktienwertegeklontAnfangJahr.size(); i++)
			{
				aktienwerte2geklontAnfangJahrgesplitted[i] = aktienwertegeklontAnfangJahr.get(i).split(";");
				System.out.println("Anfangsdatum Datum: "+String.join("; ", aktienwerte2geklontAnfangJahrgesplitted[i]));
			}

			for (int i = 0; i < aktienwertegeklontAnfangJahr.size(); i++)
			{
				aktienwerte2geklontEndeJahrgesplitted[i] = aktienwertegeklontEndeJahr.get(i).split(";");
				System.out.println("Enddatum Datum: "+String.join("; ", aktienwerte2geklontEndeJahrgesplitted[i]));
			}

			System.out.println("Werte am Anfang des Jahres: "+(String.join("", aktienwertegeklontAnfangJahr)));
			System.out.println("Werte am Ende des Jahres: "+(String.join("", aktienwertegeklontEndeJahr)));
			System.out.println(zähler);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



//SimpleDateFormat datumsFormat = new SimpleDateFormat("dd.MM.yyyy");

//ArrayList<String> endedesJahresArray= new ArrayList<String>();
//ArrayList<String> anfangJahresArray= new ArrayList<String>();
//Date jahresEnde = null;
//Date jahresAnfang = null;

//	try {
//		jahresEnde = datumsFormat.parse("30.12.2011");
//		jahresAnfang = datumsFormat.parse("03.01.2011");
//		System.out.printf("Jahresende gesetzt: %tF%n", jahresEnde);
//		System.out.printf("Jahresende gesetzt: %tF%n", jahresAnfang);
//	} catch (ParseException e) {
//		e.printStackTrace();
//	}

//System.out.println(aktienwerte);


//while ((s = in.readLine()) != null) {


//	Date d = null;
//	try {
//		d = datumsFormat.parse(datumsString);
//	} catch (ParseException e) {
//		e.printStackTrace();
//		break;
//	}
//	if (jahresEnde.equals(d)) {
//		System.out.printf("gefunden %tF%n", d);
//
//					for (String x : s.split(";")) {
//			System.out.println(x);
//		}

//	}
//}
