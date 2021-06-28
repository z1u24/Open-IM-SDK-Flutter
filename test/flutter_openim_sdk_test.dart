import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_openim_sdk/flutter_openim_sdk.dart';

void main() {
  /* const MethodChannel channel = MethodChannel('flutter_openim_sdk');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {});*/

  UserInfo u1 = new UserInfo('1', 'n_1');
  UserInfo u2 = new UserInfo('2', 'n_2');
  UserInfo u3 = new UserInfo('3', 'n_3');
  UserInfo u4 = new UserInfo('3', 'n_4');

  List<UserInfo> list = List.of([u1, u2, u3]);
  print('${list.contains(u4)}');
  print('${u1== u4}');
  print('${u2== u4}');
  print('${u3== u4}');
}

class UserInfo {
  String uid;
  String uname;

  UserInfo(this.uid, this.uname);

  @override
  bool operator ==(Object other) {
    // TODO: implement ==
    if (other is UserInfo) {
      return uid == other.uid;
    }
    return this == other;
  }
}
