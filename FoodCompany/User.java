import java.io.IOException;

public interface User {
    void showQueries() throws IOException;
    void getRewards();
    void setRewardPoints( User a, double amount)throws IOException;

}
