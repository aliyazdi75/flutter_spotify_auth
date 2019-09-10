#import "FlutterSpotifyAuthPlugin.h"
#import <flutter_spotify_auth/flutter_spotify_auth-Swift.h>

@implementation FlutterSpotifyAuthPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterSpotifyAuthPlugin registerWithRegistrar:registrar];
}
@end
