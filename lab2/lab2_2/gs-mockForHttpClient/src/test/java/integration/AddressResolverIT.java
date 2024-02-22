package integration;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import connection.TqsBasicHttpClient;
import geocoding.Address;
import geocoding.AddressResolverService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressResolverIT {

    private AddressResolverService resolver;

    @BeforeEach
    public void init(){
        this.resolver = new AddressResolverService(new TqsBasicHttpClient());
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {
        Optional<Address> result = resolver.findAddressForLocation(40.6336, -8.6595); 
        Address expected = new Address( "Campus Universtário de Santiago", "Aveiro","3810-168", "");
        assertTrue( result.isPresent());
        assertEquals( expected, result.get());
    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {
        Optional<Address> result = resolver.findAddressForLocation( -361,-361);
        assertFalse( result.isPresent());
        
    }

}
