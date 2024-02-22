package geocoding;

import connection.ISimpleHttpClient;
import connection.TqsBasicHttpClient;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressResolverTest {

    String jsonError= "{\n" +
        "  \"info\": {\n" +
        "    \"statuscode\": 400,\n" +
        "    \"copyright\": {\n" +
        "      \"text\": \"© 2024 MapQuest, Inc.\",\n" +
        "      \"imageUrl\": \"http://api.mqcdn.com/res/mqlogo.gif\",\n" +
        "      \"imageAltText\": \"© 2024 MapQuest, Inc.\"\n" +
        "    },\n" +
        "    \"messages\": [\n" +
        "      \"Illegal argument from request: Invalid LatLng specified.\"\n" +
        "    ]\n" +
        "  },\n" +
        "  \"options\": {\n" +
        "    \"maxResults\": 1,\n" +
        "    \"ignoreLatLngInput\": false\n" +
        "  },\n" +
        "  \"results\": [\n" +
        "    {\n" +
        "      \"providedLocation\": {},\n" +
        "      \"locations\": []\n" +
        "    }\n" +
        "  ]\n" +
        "}";


    String jsonString = "{\n" +
        "  \"info\": {\n" +
        "    \"statuscode\": 0,\n" +
        "    \"copyright\": {\n" +
        "      \"text\": \"© 2024 MapQuest, Inc.\",\n" +
        "      \"imageUrl\": \"http://api.mqcdn.com/res/mqlogo.gif\",\n" +
        "      \"imageAltText\": \"© 2024 MapQuest, Inc.\"\n" +
        "    },\n" +
        "    \"messages\": []\n" +
        "  },\n" +
        "  \"options\": {\n" +
        "    \"maxResults\": 1,\n" +
        "    \"ignoreLatLngInput\": false\n" +
        "  },\n" +
        "  \"results\": [\n" +
        "    {\n" +
        "      \"providedLocation\": {\n" +
        "        \"latLng\": {\n" +
        "          \"lat\": 40.6336,\n" +
        "          \"lng\": -8.6595\n" +
        "        }\n" +
        "      },\n" +
        "      \"locations\": [\n" +
        "        {\n" +
        "          \"street\": \"Campus Universtário de Santiago\",\n" +
        "          \"adminArea6\": \"Aveiro\",\n" +
        "          \"adminArea6Type\": \"Neighborhood\",\n" +
        "          \"adminArea5\": \"Aveiro\",\n" +
        "          \"adminArea5Type\": \"City\",\n" +
        "          \"adminArea4\": \"Aveiro\",\n" +
        "          \"adminArea4Type\": \"County\",\n" +
        "          \"adminArea3\": \"\",\n" +
        "          \"adminArea3Type\": \"State\",\n" +
        "          \"adminArea1\": \"PT\",\n" +
        "          \"adminArea1Type\": \"Country\",\n" +
        "          \"postalCode\": \"3810-168\",\n" +
        "          \"geocodeQualityCode\": \"B1AAA\",\n" +
        "          \"geocodeQuality\": \"STREET\",\n" +
        "          \"dragPoint\": false,\n" +
        "          \"sideOfStreet\": \"R\",\n" +
        "          \"linkId\": \"0\",\n" +
        "          \"unknownInput\": \"\",\n" +
        "          \"type\": \"s\",\n" +
        "          \"latLng\": {\n" +
        "            \"lat\": 40.63359,\n" +
        "            \"lng\": -8.65971\n" +
        "          },\n" +
        "          \"displayLatLng\": {\n" +
        "            \"lat\": 40.63359,\n" +
        "            \"lng\": -8.65971\n" +
        "          },\n" +
        "          \"mapUrl\": \"\"\n" +
        "        }\n" +
        "      ]\n" +
        "    }\n" +
        "  ]\n" +
        "}";

    @Mock
    ISimpleHttpClient httpClient;

    @InjectMocks
    AddressResolverService resolver;

    @BeforeEach
    public void init(){
        resolver = new AddressResolverService(httpClient);
    }

    @Test
    void whenResolveDetiGps_returnJacintoMagalhaeAddress() throws ParseException, IOException, URISyntaxException {

        //todo: implement test; remove Disabled annotation

        // will crash for now...need to set the resolver before using it
        when(httpClient.doHttpGet(anyString())).thenReturn(jsonString);
        Optional<Address> result = resolver.findAddressForLocation(40.6336, -8.6595);        
        
        //return
        Address expected = new Address( "Campus Universtário de Santiago", "Aveiro","3810-168", "");

        assertTrue( result.isPresent());
        assertEquals( expected, result.get());

    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddress() throws IOException, URISyntaxException, ParseException {

        when(httpClient.doHttpGet(anyString())).thenReturn(jsonError);
        Optional<Address> result = resolver.findAddressForLocation( -361,-361);
        // verify no valid result
        assertFalse( result.isPresent());

    }
}