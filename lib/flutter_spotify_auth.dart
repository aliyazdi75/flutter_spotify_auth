import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class FlutterSpotifyAuth {
  static const MethodChannel _channel =
      const MethodChannel('flutter_spotify_auth');

  static Future<String> spotifyLogin({@required String clientID}) async {
    String mAccessToken;
    try {
      final Map<String, String> params = <String, String>{
        'CLIENT_ID': clientID,
      };
      mAccessToken = await _channel.invokeMethod('login', params);
    } on PlatformException catch (e) {
      print("Failed to perform logging in: '${e.message}'.");
    }
    return mAccessToken;
  }

  static Future<void> spotifyLogout() async {
    // TODO(ALI): handle logout.
    try {
      await _channel.invokeMethod('logout');
    } on PlatformException catch (e) {
      print("Failed to perform logging out: '${e.message}'.");
    }
  }
}
