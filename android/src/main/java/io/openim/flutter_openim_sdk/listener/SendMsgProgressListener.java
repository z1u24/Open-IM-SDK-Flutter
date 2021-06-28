package io.openim.flutter_openim_sdk.listener;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.openim.flutter_openim_sdk.util.CommonUtil;
import open_im_sdk.SendMsgCallBack;

public class SendMsgProgressListener implements SendMsgCallBack {

    public SendMsgProgressListener(MethodChannel channel) {
        this.channel = channel;
    }

    public void setResult(MethodChannel.Result result) {
        this.result = result;
    }

    public void setCall(MethodCall call) {
        this.call = call;
    }

    private MethodChannel.Result result;
    final private MethodChannel channel;
    private MethodCall call;
    final Map<String, Object> values = new HashMap<>();


    @Override
    public void onError(long l, String s) {
        System.out.println("=================onError============" + s);
        CommonUtil.runMainThreadReturnError(result, l, s, null);
    }

    @Override
    public void onProgress(long l) {
        System.out.println("================onProgress============" + l);
        values.put("clientMsgID", CommonUtil.getSendMessageClientMsgID(call));
        values.put("progress", l);
        CommonUtil.emitEvent(channel, "messageProgressListener", "onProgress", values);
    }

    @Override
    public void onSuccess(String s) {
        System.out.println("=================onSuccess============" + s);
        CommonUtil.runMainThreadReturn(result, s);
    }
}
