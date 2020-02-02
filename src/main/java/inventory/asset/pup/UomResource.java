package inventory.asset.pup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "uom" path)
 */
@Path("uom")
@Produces(MediaType.APPLICATION_JSON)
public class UomResource {
	
	private UomModel uomModel = new UomModel();
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    public Response getIt() {
    	
    	List<HashMap<String, String>> uoms = new ArrayList<HashMap<String, String>>();
    	
    	uoms = this.uomModel.getAll();
    	
        return Response.ok(uoms).build();
    }
    
    @GET()
    @Path("/{id}")
    public Response getOne(@PathParam("id") String id) {
    	HashMap<String, String> uoms = new HashMap<String, String>();
    	
    	uoms = this.uomModel.findOne(id);
    	
        return Response.ok(uoms).build();
    }
    
    @POST()
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(UomDao uom) {
    	return Response.ok(uom).build();
    }
}
