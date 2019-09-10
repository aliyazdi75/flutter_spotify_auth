import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_spotify_auth/flutter_spotify_auth.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  static const CLIENT_ID = '';
  String _mAccessToken = 'Unknown';
  String pkgName = 'io.github.aliyazdi75.flutter_spotify_auth_example';

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Flutter Spotify Auth example app'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              RaisedButton(
                onPressed: () => _initSpotifyLogin(),
                child: Text('Login With Spotify'),
              ),
              Text('Your Spotify Access Token: $_mAccessToken\n'),
            ],
          ),
        ),
      ),
    );
  }

  Future<void> _initSpotifyLogin() async {
    String mAccessToken;
    try {
      mAccessToken = await FlutterSpotifyAuth.spotifyLogin(
        clientID: CLIENT_ID,
      );
    } on PlatformException {
      mAccessToken = 'Failed to get Access Token.';
    }

    if (!mounted) return;

    setState(() {
      _mAccessToken = mAccessToken;
    });
  }
}
