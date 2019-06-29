#import "EncryptPlugin.h"
#import <encrypt_plugin/encrypt_plugin-Swift.h>

@implementation EncryptPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftEncryptPlugin registerWithRegistrar:registrar];
}
@end
