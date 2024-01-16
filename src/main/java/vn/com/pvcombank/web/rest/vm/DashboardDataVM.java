package vn.com.pvcombank.web.rest.vm;

/**
 * Dashboard data
 *
 * @author phuctd <phuctd@pvcombank.com.vn>
 */
public class DashboardDataVM {

    private long totalHumans;
    private long totalApplications;
    private long totalApplicationRunning;
    private long totalDatabases;
    private long totalDatabaseRunning;
    private long totalApi;
    private long totalApirunning;

    public long getTotalHumans() {
        return totalHumans;
    }

    public void setTotalHumans(long totalHumans) {
        this.totalHumans = totalHumans;
    }

    public long getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(long totalApplications) {
        this.totalApplications = totalApplications;
    }

    public long getTotalApplicationRunning() {
        return totalApplicationRunning;
    }

    public void setTotalApplicationRunning(long totalApplicationRunning) {
        this.totalApplicationRunning = totalApplicationRunning;
    }

    public long getTotalDatabases() {
        return totalDatabases;
    }

    public void setTotalDatabases(long totalDatabases) {
        this.totalDatabases = totalDatabases;
    }

    public long getTotalDatabaseRunning() {
        return totalDatabaseRunning;
    }

    public void setTotalDatabaseRunning(long totalDatabaseRunning) {
        this.totalDatabaseRunning = totalDatabaseRunning;
    }

    public long getTotalApi() {
        return totalApi;
    }

    public void setTotalApi(long totalApi) {
        this.totalApi = totalApi;
    }

    public long getTotalApirunning() {
        return totalApirunning;
    }

    public void setTotalApirunning(long totalApirunning) {
        this.totalApirunning = totalApirunning;
    }

    @Override
    public String toString() {
        return (
            "DashboardDataVM{" +
            "totalHumans=" +
            totalHumans +
            ", totalApplications=" +
            totalApplications +
            ", totalApplicationRunning=" +
            totalApplicationRunning +
            ", totalDatabases=" +
            totalDatabases +
            ", totalDatabaseRunning=" +
            totalDatabaseRunning +
            ", totalApi=" +
            totalApi +
            ", totalApirunning=" +
            totalApirunning +
            '}'
        );
    }
}
