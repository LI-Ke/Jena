package formatConversion;

import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

public class RDFTrig {

	public static void main(String[] args) {
		
		final String inputFileName1  = "data.rdf";
		
		// create an empty model
        Model model = ModelFactory.createDefaultModel();
		
        InputStream in = FileManager.get().open(inputFileName1);

		if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName1 + " not found");
        }
        
        // read the RDF/XML file
        model.read(in, "");
        
        System.out.println("RDF to TriG : ");
        // write it to standard out
        model.write(System.out, "TriG");
        
        System.out.println();
        
        // create an empty model
        Model model2 = ModelFactory.createDefaultModel();
        
        // read the Trig file
        model2.read("data.trig");
                 
        System.out.println("TriG to RDF : ");
        // write it to standard out
        model2.write(System.out, "RDF/XML");		

	}

}
