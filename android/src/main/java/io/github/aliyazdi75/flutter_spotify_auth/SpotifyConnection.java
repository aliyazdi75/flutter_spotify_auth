package io.github.aliyazdi75.flutter_spotify_auth;

import android.app.Activity;
import android.content.Intent;
import android.telecom.Call;

import androidx.appcompat.app.AppCompatActivity;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

public class SpotifyConnection extends AppCompatActivity {

    private Activity parent;
    private String CLIENT_ID;
    private String REDIRECT_URI;
    public static final int AUTH_TOKEN_REQUEST_CODE = 0x10;
    //    public static final int AUTH_CODE_REQUEST_CODE = 0x11;
    private static final String SCOPES = "user-read-recently-played,user-library-modify,user-library-read,playlist-modify-public,playlist-modify-private,user-read-email,user-read-private,user-read-birthdate,playlist-read-private,playlist-read-collaborative";

    //    private final OkHttpClient mOkHttpClient = new OkHttpClient();
    public boolean getAccessTokenError = false;
    public String mAccessToken;
    private String mAccessCode;
    private Call mCall;

    SpotifyConnection(Activity parent, String CLIENT_ID, String REDIRECT_URI) {
        this.parent = parent;
        this.CLIENT_ID = CLIENT_ID;
        this.REDIRECT_URI = REDIRECT_URI;
    }

    public void loginRequest() {
        final AuthenticationRequest request = getAuthenticationRequest();
        AuthenticationClient.openLoginActivity(parent, AUTH_TOKEN_REQUEST_CODE, request);
    }

    public void logoutRequest() {
//        AuthenticationClient.clearCookies();
    }

    private AuthenticationRequest getAuthenticationRequest() {
        return new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)
                .setShowDialog(false)
                .setScopes(new String[]{SCOPES})
//                .setCampaign("your-campaign-token")
                .build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, data);

        if (response.getType() == AuthenticationResponse.Type.TOKEN) {
            mAccessToken = response.getAccessToken();
        } else {
            getAccessTokenError = true;
        }
    }
}
