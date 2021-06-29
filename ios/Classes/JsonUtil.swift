//
//  JsonUtil.swift
//  Runner
//
//  Created by z1u24 on 2021/6/28.
//

import Foundation

public class JsonUtil {
    
    public static func toString(object: NSDictionary?)->String{
        if (object == nil) {
            return "";
        }
        let data = try? JSONSerialization.data(withJSONObject: object!, options: JSONSerialization.WritingOptions.init(rawValue: 0))
        let jsonStr = NSString(data: data!, encoding: String.Encoding.utf8.rawValue)
        return jsonStr! as String
    }
    
}
