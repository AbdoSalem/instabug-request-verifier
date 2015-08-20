package com.example.requestverifier;

public enum Constants {
	USERS_FILE("Users"),
	USER_NAME_KEY("User_name"),
    ACCESS_TOKEN_KEY("Access_token"),
    ACCOUNT_TYPE ( "com.odps"),
    ACCOUNT_ADMIN ( "com.odps.admin"),
    ACCOUNT_FIRST_NAME ( "com.odps.first_name"),
    ACCOUNT_LAST_NAME( "com.odps.last_name"),
    AUTHTOKEN_TYPE ("com.odps"),
    LOCATION_SERVICE("com.odps.services.LocationService"),
    LOCATION_CONTEXT("com.odps.services.RefreshLocation"),
    Timed_SERVICE_INTERVAL("com.odps.services.TimedService.interval"),
    ;

    private final String text;

    /**
     * @param text
     */
    private Constants(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
