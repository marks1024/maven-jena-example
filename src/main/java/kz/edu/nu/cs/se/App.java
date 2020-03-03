package kz.edu.nu.cs.se;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;


public class App {
    public static final String pr = "PREFIX foaf:   <http://xmlns.com/foaf/0.1/>\n"
            + "PREFIX ex: <http://example/com/>\n";
    
    public static final String query1 = pr + "SELECT ?mbox "
            + "WHERE "
            + "{ ?x foaf:name ?fn . "
            + "?x foaf:mbox ?mbox . "
            + "?x foaf:nick ?nickname . }";
    
    public static final String query2 = pr + "SELECT ?mbox "
            + "WHERE "
            + "{ ?x foaf:name ?fn . "
            + "?x foaf:mbox ?mbox . }";
    
    public static final String query3 = pr + "SELECT ?a ?b ?i "
            + "WHERE "
            + "{ ?a foaf:interest ?i . "
            + "?b foaf:interest ?i . "
            + "FILTER (?a != ?b) }";
    
    public static final String query5 = pr + "SELECT ?a ?b ?c "
            + "WHERE "
            + "{ ?a foaf:knows ?b . "
            + "?b foaf:knows ?c . "
            + " }";
    
    public static void main(String[] args) {
        Model modelData = ModelFactory.createDefaultModel();
        modelData.read("data.ttl");
        
        String queryString = query5;
        
        System.out.println(queryString);
        Query query = QueryFactory.create(queryString);
        
        QueryExecution qexec = QueryExecutionFactory.create(query, modelData);
        ResultSet results = qexec.execSelect();
        
        System.out.println("\nQuery Results\n\n");
        
        while (results.hasNext()) {
            QuerySolution qs = results.nextSolution();
            System.out.println("\nResult\n-------");
            System.out.println(qs);
        }
    }
}
