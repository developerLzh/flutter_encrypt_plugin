//
//  LanAES.h
//  demo
//
//  Created by RQunner on 2017/10/23.
//  Copyright © 2017年 rvakva. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "NSData+CommonCrypto.h"
#import "GTMBase64.h"

@interface LanAES : NSObject

+ (NSString *)AES128enAES:(NSString *)content key:(NSString *)key; //处理加密
+(NSString *)AES128Decrypt:(NSString *)encryptText key:(NSString *)key;//处理解密
+ (NSData *)DecryptDataAES128:(NSData *)encryptData key:(NSString*)key;

//hmacsha1加密
+ (NSString *)hmacSha1:(NSString*)key text:(NSString*)text;

@end
