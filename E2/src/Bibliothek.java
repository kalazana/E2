/* @author Fuad Aliev
 *  06.11.2018 add 02.12.2018 add 10.12.2018
 */
import java.io.IOException;

public class Bibliothek {

//neue Aufgaben

    public static void main(String[] args) throws IOException {

        @SuppressWarnings("unused")
        BinaryPersistency binaryPersistency = new BinaryPersistency();
        @SuppressWarnings("unused")
        HumanReadablePersistency humanReadablePersistency = new HumanReadablePersistency();
        WikibooksParser w = new WikibooksParser();
        Zettelkasten zettelkasten = new Zettelkasten();

        if (args.length == 2) {
            if (args[0].equals("parse")) {
                zettelkasten.addMedium(w.parse(args[1]));
            }
        }

        zettelkasten.addMedium(w.parse("Java_Standard"));
        zettelkasten.addMedium(w.parse("_XHTML"));

        //für GUI nicht mehr benötigt
/*		for (Medium medium : zettelkasten) {
			System.out.println(medium.calculateRepresentation());
			System.out.println();
		} */
    }
}

// alte Aufgaben

/**		public static void main(final String[] args) {
 String _sSearch = args[0];


 WikibooksParser parser = new WikibooksParser();

 WikiBooks w = (WikiBooks) parser.parse(_sSearch);
 System.out.println(w.calculateRepraesentation());

 Zettelkasten zettelkasten = new Zettelkasten();

 zettelkasten.addMedium(new CD("Live At Wembley","Queen","Parlophone (EMI)"));


 zettelkasten.addMedium(new Buch("Duden 01. Die deutsche Rechtschreibung",2004,
 "Bibliographosches Institut, Mannheim","3-411-04013-0","-"));

 zettelkasten.addMedium(new CD("1","Apple (Bea (EMI)","The Beatles"));

 zettelkasten.addMedium(new Zeitschrift("Der Siegel","0038-7452",54,6));

 //Test BinaryPersistancy
 BinaryPersistency binaryPersistency = new BinaryPersistency();
 binaryPersistency.save(zettelkasten, "media");
 zettelkasten = binaryPersistency.load("media");

 //Test HumanReadablePersistancy
 HumanReadablePersistency humanReadablePersistency = new HumanReadablePersistency();
 humanReadablePersistency.save(zettelkasten, "medien_humanReadable");
 //    humanReadablePersistency.load("medien_humanReadable");

 for (Medium medium : zettelkasten) {
 System.out.println("\n" + medium.calculateRepresentation());
 }

 zettelkasten.sort();
 zettelkasten.sort();

 zettelkasten.removeAllDuplicates("Duden 01. Die deutsche Rechtschreibung");
 zettelkasten.dropMedium("Duden 01. Die deutsche Rechtschreibung", Buch.class);

 for (Medium medium : zettelkasten) {
 System.out.println("\n" + medium.calculateRepresentation());
 }

 }
 }
 **/
/*		


		HumanReadablePersistency Save = new HumanReadablePersistency();
		Save.save(zettelkasten, "media");
		
		
        BinaryPersistency binaryPersistency = new BinaryPersistency();
        binaryPersistency.save(zettelkasten, "media");
        

        zettelkasten = binaryPersistency.load("media");


        
        System.out.println("\n");
        System.out.print("Bitte geben Sie den Titel ein den Sie löschen möchten:");
        


        

        
        System.out.println("\n\n");
        System.out.println("---------------------");
        System.out.println("Before sort");
        System.out.println("---------------------");
        

        for (Medium medium : zettelkasten) {
            System.out.println(medium.calculateRepresentation());
        }

        System.out.println("\n\n");

        zettelkasten.sort();

        
        System.out.println("---------------------");
        System.out.println("After sort");
        System.out.println("---------------------");
        

        for (Medium medium : zettelkasten) {
            System.out.println(medium.calculateRepresentation());
        }
        zettelkasten.removeAllDuplicates("Duden 01. Die deutsche Rechtschreibung");
        zettelkasten.dropMedium("Duden 01. Die deutsche Rechtschreibung", Buch.class);

        for (Medium medium : zettelkasten) {
            System.out.println("\n" + medium.calculateRepresentation());
        }
    }

    
    public static String getScann() {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader buf = new BufferedReader(input);

		System.out.print("\nBitte machen Sie eine Eingabe:");

        //TODO maybe rename to input or maybe title since you only ask for a title
        String title = null;

        try {
            title = buf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return title;
    }
}*/