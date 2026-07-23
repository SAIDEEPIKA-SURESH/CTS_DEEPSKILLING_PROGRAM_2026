public class CustomerService {

    CustomerRepository repo;

    public CustomerService(
            CustomerRepository repo) {

        this.repo = repo;
    }

    void getCustomer(int id) {
        System.out.println(
            repo.findCustomerById(id));
    }
}