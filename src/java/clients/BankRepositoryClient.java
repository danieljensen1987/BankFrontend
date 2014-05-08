package clients;

import java.util.Collection;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

public class BankRepositoryClient implements AutoCloseable {
  private final WebTarget target;
  private final Client client;
  private static final String BASE_URI =
      "http://datdb.cphbusiness.dk:8080/BankRepository/ws/";
  private static final GenericType<Collection<Bank>> BANKS =
      new GenericType<Collection<Bank>>() {};
  
  public BankRepositoryClient(String uri) {
    ClientConfig config = new ClientConfig().register(new JacksonFeature());
    client = ClientBuilder.newClient(config);
    target = client.target(uri).path("bank");
    }

  public BankRepositoryClient() { this(BASE_URI); }
  
  public Collection list() {
    return target.request(APPLICATION_JSON_TYPE).get(BANKS);
    }
  
  public Bank find(String reg) {
    return target.path(reg).request(APPLICATION_JSON_TYPE).get(Bank.class);
    }
  
  public void save(Bank bank) {
    target.request().post(Entity.entity(bank, APPLICATION_JSON_TYPE));
    }
  
  public void drop(String reg) {
    target.path(reg).request().delete();
    }
  
  @Override
  public void close() {
    client.close();
    }
  
  }