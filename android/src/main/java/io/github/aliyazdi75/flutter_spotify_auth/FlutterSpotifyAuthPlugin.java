package io.github.aliyazdi75.flutter_spotify_auth;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * FlutterSpotifyAuthPlugin
 */
public class FlutterSpotifyAuthPlugin implements MethodCallHandler {

    private static Registrar mRegistrar;

    private FlutterSpotifyAuthPlugin(Registrar r) {
        mRegistrar = r;
    }

    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_spotify_auth");
        channel.setMethodCallHandler(new FlutterSpotifyAuthPlugin(registrar));
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        Context context = mRegistrar.context();
        if (call.method.equals("login")) {
            final String CLIENT_ID = call.argument("CLIENT_ID");
            final String REDIRECT_URI = context.getPackageName() + "://callback";
            SpotifyConnection spotifyConnection = new SpotifyConnection(mRegistrar.activity(), CLIENT_ID, REDIRECT_URI);
            spotifyConnection.loginRequest();
            if (spotifyConnection.getAccessTokenError) {
                result.error("Failed To Get Access Token!", null, null);
            } else {
                result.success(spotifyConnection.mAccessToken);
            }
        } else if (call.method.equals("logout")) {
            result.success(null);
        } else {
            result.notImplemented();
        }
    }
}
