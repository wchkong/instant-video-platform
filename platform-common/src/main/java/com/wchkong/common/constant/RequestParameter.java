package com.wchkong.common.constant;

/**
 * @author haoli <lihao@wandoujia.com>
 */
public class RequestParameter {

    public enum ActionType {
        increase, decrease
    }

    public enum Strategy {
        newest, shareCount, playCount, createTime, manSort
    }

    public enum RankListStrategy {
        topCollection, topShare, topPlay, weekly, monthly, historical
    }

    public enum ReplyStrategy {
        date, likeCount
    }

    public enum SimilarityStrategy {
        traditional,
        advanced,
        aliyun,
    }

    public static final String DATE = "date";

    public static final String NUM = "num";

    public static final String TAB_INDEX = "tabIndex";

    public static final String START = "start";

    public static final String ID = "id";

    public static final String LAST_ID = "lastId";

    public static final String NAME = "name";

    public static final String ACTION = "action";

    public static final String STRATEGY = "strategy";

    public static final String LIBRARY = "library";

    public static final String MODEL = "model";

    public static final String DEVICE_MODEL = "deviceModel";

    public static final String CHANNEL = "last_channel";

    public static final String UID = "uid";

    public static final String TOTAL_NUM = "total";

    public static final String PHONE_NUMBER = "phoneNumber";

    public static final String ADDRESS = "address";

    public static final String WECHAT_NUMBER = "wechatNumber";

    public static final String TOKEN = "token";

    public static final String TYPE = "type";

    public static final String SOURCE_TYPE = "sourceType";

    public static final String ITEM_TYPE = "itemType";

    public static final String ITEM_ID = "itemId";

    public static final String ITEM_LIST = "itemList";

    public static final String QUERY = "query";

    public static final String TAG = "tag";

    public static final String DISTINCT = "distinct";

    public static final String TAG_ID = "tagId";

    public static final String TAG_LIST = "tagList";

    public static final String USER_ID = "userId";

    public static final String USER_TYPE = "userType";

    public static final String FOLLOW = "follow";

    public static final String PAGE = "page";

    public static final String START_ID = "startId";

    public static final String LAST_START_ID = "lastStartId";

    public static final String TOP = "top";

    public static final String TITLE = "title";

    public static final String DESCRIPTION = "description";

    public static final String REPLY_ID = "replyId";

    public static final String REFRESH = "refresh";

    public static final String DURATION = "duration";

    public static final String RESOURCE_TYPE = "resourceType";

    public static final String UGC_RESOURCE_STATUS = "ugcResourceStatus";

    public static final String UGC_ADMIN_CHECK_STATUS = "ugcAdminCheckStatus";

    public static final String UGC_VIDEO_STATUS = "ugcVideoStatus";

    public static final String UGC_PICTURE_STATUS = "ugcPictureStatus";

    public static final String NICKNAME = "nickname";

    public static final String RESOURCE_ID = "resourceId";

}
