import configuration.DataSource;
import loading.impl.LoadingServiceImpl;
import repository.impl.CustomerRepositoryImpl;

public class Client {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        LoadingServiceImpl loadingService = new LoadingServiceImpl(new CustomerRepositoryImpl());
        long insertedCustomers = loadingService.executeChunkLoading(System.getProperty("file.path"), Integer.parseInt(System.getProperty("chunk.size")));
        long stop = System.currentTimeMillis();
        System.out.println("\nSuccessfuly inserted " + insertedCustomers + " customers, elapsed: " + (stop-start));
        DataSource.closeConnection();
    }
}
