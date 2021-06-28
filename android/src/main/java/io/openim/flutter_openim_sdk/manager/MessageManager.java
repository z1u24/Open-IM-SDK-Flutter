package io.openim.flutter_openim_sdk.manager;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.openim.flutter_openim_sdk.listener.AdvancedMsgListenerImpl;
import io.openim.flutter_openim_sdk.listener.BaseImpl;
import io.openim.flutter_openim_sdk.listener.SendMsgProgressListener;
import io.openim.flutter_openim_sdk.util.CommonUtil;
import open_im_sdk.OnAdvancedMsgListener;
import open_im_sdk.Open_im_sdk;


public class MessageManager {
    final static String KEY_ID = "id";
    final MethodChannel channel;
    final static Map<String, OnAdvancedMsgListener> listeners = new HashMap<>();
    static SendMsgProgressListener sendMsgProgressListener;

    public MessageManager(MethodChannel channel) {
        this.channel = channel;
    }

    public void addAdvancedMsgListener(MethodCall methodCall, MethodChannel.Result result) {
        if (null == sendMsgProgressListener) {
            sendMsgProgressListener = new SendMsgProgressListener(channel);
        }
        String key = methodCall.argument(KEY_ID);
        if (!listeners.containsKey(key)) {
            AdvancedMsgListenerImpl listener = new AdvancedMsgListenerImpl(channel, key);
            listeners.put(methodCall.argument(KEY_ID), listener);
            Open_im_sdk.addAdvancedMsgListener(listener);
            System.out.println("=================add msg listener======" + listener);
        }
    }

    public void removeAdvancedMsgListener(MethodCall methodCall, MethodChannel.Result result) {
        String key = methodCall.argument(KEY_ID);
        OnAdvancedMsgListener listener = listeners.remove(key);
        Open_im_sdk.removeAdvancedMsgListener(listener);
        System.out.println("=================remove msg listener======" + listener);
    }

    public void sendMessage(MethodCall methodCall, MethodChannel.Result result) {
        sendMsgProgressListener.setCall(methodCall);
        sendMsgProgressListener.setResult(result);
        System.out.println("===============sendMessage===============");
        Open_im_sdk.sendMessage(sendMsgProgressListener, CommonUtil.getSendMessageContent(methodCall), CommonUtil.getSendMessageReceiver(methodCall), CommonUtil.geSendMessageGroupId(methodCall), CommonUtil.getSendMessageOnlineOnly(methodCall));
    }

    public void getHistoryMessageList(MethodCall methodCall, MethodChannel.Result result) {
        Open_im_sdk.getHistoryMessageList(new BaseImpl(result), CommonUtil.getSDKJsonParam(methodCall));
    }

    public void revokeMessage(MethodCall methodCall, MethodChannel.Result result) {
        Open_im_sdk.revokeMessage(new BaseImpl(result), CommonUtil.getSDKJsonParam(methodCall));
    }

    public void deleteMessageFromLocalStorage(MethodCall methodCall, MethodChannel.Result result) {
        Open_im_sdk.deleteMessageFromLocalStorage(new BaseImpl(result), CommonUtil.getSDKJsonParam(methodCall));
    }

    public void deleteMessages(MethodCall methodCall, MethodChannel.Result result) {
//        Open_im_sdk.deleteMessages(new BaseImpl(result), CommonUtil.getSDKJsonParam(methodCall));
    }

    public void insertSingleMessageToLocalStorage(MethodCall methodCall, MethodChannel.Result result) {
        Open_im_sdk.insertSingleMessageToLocalStorage(new BaseImpl(result), CommonUtil.getSingleMessageContent(methodCall), CommonUtil.getSingleMessageUserid(methodCall), CommonUtil.getSingleMessageSender(methodCall));
    }

    public void findMessages(MethodCall methodCall, MethodChannel.Result result) {
        Open_im_sdk.findMessages(new BaseImpl(result), CommonUtil.getFindMessageIds(methodCall));
    }

    public void markSingleMessageHasRead(MethodCall methodCall, MethodChannel.Result result) {
        Open_im_sdk.markSingleMessageHasRead(new BaseImpl(result), CommonUtil.getSingleMessageUserid(methodCall));
    }

    public void createTextMessage(MethodCall methodCall, MethodChannel.Result result) {
        CommonUtil.runMainThreadReturn(result, Open_im_sdk.createTextMessage(CommonUtil.getMessageText(methodCall)));
    }

    public void createTextAtMessage(MethodCall methodCall, MethodChannel.Result result) {
        CommonUtil.runMainThreadReturn(result, Open_im_sdk.createTextAtMessage(CommonUtil.getMessageText(methodCall), CommonUtil.getAtUserList(methodCall)));
    }

    public void createImageMessage(MethodCall methodCall, MethodChannel.Result result) {
        CommonUtil.runMainThreadReturn(result, Open_im_sdk.createImageMessage(CommonUtil.getImagePath(methodCall)));
    }

    public void createSoundMessage(MethodCall methodCall, MethodChannel.Result result) {
        CommonUtil.runMainThreadReturn(result, Open_im_sdk.createSoundMessage(CommonUtil.getSoundPath(methodCall), CommonUtil.getSoundDuration(methodCall)));
    }

    public void createVideoMessage(MethodCall methodCall, MethodChannel.Result result) {
        CommonUtil.runMainThreadReturn(result, Open_im_sdk.createVideoMessage(CommonUtil.getVideoPath(methodCall), CommonUtil.getVideoType(methodCall), CommonUtil.getVideoDuration(methodCall), CommonUtil.getVideoSnapshotPath(methodCall)));
    }

    public void createFileMessage(MethodCall methodCall, MethodChannel.Result result) {
        CommonUtil.runMainThreadReturn(result, Open_im_sdk.createFileMessage(CommonUtil.getFilePath(methodCall), CommonUtil.getFileName(methodCall)));
    }

    public void createMergerMessage(MethodCall methodCall, MethodChannel.Result result) {
        CommonUtil.runMainThreadReturn(result, Open_im_sdk.createMergerMessage(CommonUtil.getMergerMessageList(methodCall), CommonUtil.getMergerMessageTitle(methodCall), CommonUtil.getSummaryList(methodCall)));
    }

    public void createForwardMessage(MethodCall methodCall, MethodChannel.Result result) {
        CommonUtil.runMainThreadReturn(result, Open_im_sdk.createForwardMessage(CommonUtil.getForwardMessage(methodCall)));
    }
}
