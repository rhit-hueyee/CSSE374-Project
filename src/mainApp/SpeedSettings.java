package mainApp;

public class SpeedSettings {
    private static SpeedSettings instance;
    private int globalVx;
    private int globalVy;
    private int globalSpeedLimit;

    private SpeedSettings() {
        // Private constructor to prevent instantiation from outside the class
    }

    public static synchronized SpeedSettings getInstance() {
        if (instance == null) {
            instance = new SpeedSettings();
        }
        return instance;
    }

    public int getGlobalVx() {
        return globalVx;
    }

    public void setGlobalVx(int globalVx) {
        this.globalVx = globalVx;
    }

    public int getGlobalVy() {
        return globalVy;
    }

    public void setGlobalVy(int globalVy) {
        this.globalVy = globalVy;
    }

    public int getGlobalSpeedSettings() {
        return globalSpeedLimit;
    }

    public void setGlobalSpeedSettings(int globalSpeedSettings) {
        this.globalSpeedLimit = globalSpeedSettings;
    }
}

