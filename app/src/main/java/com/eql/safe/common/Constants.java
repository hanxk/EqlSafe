package com.eql.safe.common;


public class Constants {


    public final static int USER_GENDER_FEMALE = 0;
    public final static int USER_GENDER_MALE = 1;

    public final static String COMMON_DELIMITER = ",";
    public final static String COMMON_WRAP = "\n";

    public final static String MAP_KEY = "f87332dd995e3f9708c79144e95689ac";


    public final static String MIPUSH_APP_ID = "2882303761517118525";
    public final static String MIPUSH_APP_KEY = "5391711842525";


    /**
     * 每页显示多少条数据
     */
    public static final int PAGE_SIZE = 20;

    public final static int USER_AGE_MIN = 0;
    public final static int USER_AGE_MAX = 120;
    public final static int PASSWORD_LENGTH_MIN = 8;
    public final static int PASSWORD_LENGTH_MAX = 16;
    public final static int LESION_BRIEF_LENGTH_MIN = 20;
    public final static int LESION_BRIEF_LENGTH_MAX = 256;
    public final static int VERIFICATION_CODE_LENGTH = 6;
    public final static int MOBILE_NO_LENGTH = 11;

    public final static int USER_TYPE_PATIENT = 0;
    public final static int USER_TYPE_DOCTOR = 1;

    public final static int REQUEST_QUESTION_TYPE_ALL = 0;
    public final static int REQUEST_QUESTION_TYPE_DOCTOR = 1;
    public final static int REQUEST_QUESTION_TYPE_PATIENT = 2;

    public static final String URL_POST_DATAIL = "http://42.96.186.79/notices/pro/";
    public static final String URL_PRODUCT_INFO = "http://42.96.186.79/notices/pro/ProductIntro.html";
    public static final String URL_SERVICE_AGREEMENT = "http://42.96.186.79/notices/pro/ServiceAgreement.html";


    public static final int GOTO_IMAGE_CAPTURE = 1;
    public static final int GOTO_PHOTO_GALLERY = 2;
    public static final int GOTO_CHOOSE_VIEWIMG = 3;
    public static final int GOTO_CHOOSE_CHOOSEIMG = 4;

    public static final int GOTO_SUBMIT_QUESTION = 3;
    public static final int GOTO_SOLUTION = 4;
    public static final int GOTO_VERIFICATION = 5;
    public static final int GOTO_USER_INFO = 6;

    public static final String PARA_DOCTOR = "doctor";
    public static final String PARA_PATIENT = "PARA_PATIENT";
    public static final String PARA_DOCTOR_ID = "doctor_id";
    public static final String PARA_QUESTION = "question";
    public static final String PARA_QUESTION_ID = "question_id";
    public static final String PARA_USER_NAME = "user_name";
    public static final String PARA_USER_ID = "PARA_USER_ID";
    public static final String PARA_PHONE_NUM = "PARA_PHONE_NUM";
    public static final String PARA_SMSCODE = "PARA_SMSCODE";
    public static final String PARA_PASSWORD = "password";
    public static final String PARA_VIEW_TYPE = "view_type";
    public static final String PARA_VERIFY_CODE = "verify_code";
    public static final String PARA_TITLE = "title";
    public static final String PARA_CONTENT = "content";
    public static final String PARA_IS_PERSONAL = "is_personal";
    public static final String PARA_TARGET = "is_edit";
    public static final String PARA_PUSHTOKEN = "PARA_PUSHTOKEN";

//	public static final String INTENT_KEY_VIEW_IMAGE_PATH = "INTENT_KEY_VIEW_IMAGE_PATH";

    public static final int HOSPITAL_DEPARTMENT_SKIN = 1;

    public static final int DESCRIPTION_MIN_LENGTH = 10;

    public static final String APP_CLIENT_TYPE = "user";
    public static final String APP_VERSION = "1.0";

    //http://114.112.81.97/api/http/httpService.php";
    public static final int APP_SERVER_PORT = 4567;


    //Network
    public static final String NETWORK_REQUEST_METHOD_GET = "GET";
    public static final String NETWORK_REQUEST_METHOD_POST = "POST";
    public static final String NETWORK_CONTENT_TYPE_JSON = "application/json";
    public static final String NETWORK_CONTENT_TYPE_XML = "text/xml";
    public static final String NETWORK_CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
    public static final String NETWORK_ENCODER_UTF8 = "utf-8";
    public static final String NETWORK_ENCODER_GB2312 = "gb2312";

    public static final int NETWORK_REQUEST_TYPE_LOGIN = 0;
    public static final int NETWORK_REQUEST_TYPE_REGISTER = 1;
    public static final int NETWORK_REQUEST_TYPE_GET_DOCTOR_INFO = 2;
    public static final int NETWORK_REQUEST_TYPE_GET_DOCTOR_LIST = 3;
    public static final int NETWORK_REQUEST_TYPE_GET_QUESTION_LIST = 4;
    public static final int NETWORK_REQUEST_TYPE_GET_RESPONSE_LIST = 5;
    public static final int NETWORK_REQUEST_TYPE_SUBMIT_QUESTION = 6;
    public static final int NETWORK_REQUEST_TYPE_SUBMIT_ANSWER = 7;
    public static final int NETWORK_REQUEST_TYPE_SUBMIT_DIAGNOSE = 8;
    public static final int NETWORK_REQUEST_TYPE_GET_DIAGNOSE = 9;
    public static final int NETWORK_REQUEST_TYPE_SUBMIT_SURVEY = 10;
    public static final int NETWORK_REQUEST_TYPE_UPDATE_QUESTION = 11;
    public static final int NETWORK_REQUEST_TYPE_USER_INIT_INFO = 12;
    public static final int NETWORK_REQUEST_TYPE_DOCTOR_INIT_INFO = 13;
    public static final int NETWORK_REQUEST_TYPE_UPDATE_QUESTION_STATUS = 14;
    public static final int NETWORK_REQUEST_TYPE_GET_SMS_CODE = 15;
    public static final int NETWORK_REQUEST_TYPE_RESET_PASSWORD = 16;
    public static final int NETWORK_REQUEST_TYPE_UPDATE_PREPAY_STATE = 17;
    public static final int NETWORK_REQUEST_TYPE_UPDATE_USER_INFO = 18;
    public static final int NETWORK_REQUEST_TYPE_UPDATE_DOCTOR_INFO = 19;
    public static final int NETWORK_REQUEST_TYPE_LOGOUT = 20;
    public static final int NETWORK_REQUEST_TYPE_SUBMIT_SUBUSER_INFO = 21;
    public static final int NETWORK_REQUEST_TYPE_DELETE_SUBUSER_INFO = 22;
    public static final int NETWORK_REQUEST_TYPE_GET_SUBUSER_LIST = 23;


    public static final int NETWORK_RESPONSE_ERRCODE_OK = 0;
    public static final int NETWORK_RESPONSE_CALL_SUCCESS = 1;
    public static final int NETWORK_RESPONSE_CALL_FAILED = 0;
    public static final int NETWORK_ITEM_LENGTH = 20;

    public static final String NETWORK_PARA_USERNAME = "UserName";
    public static final String NETWORK_PARA_PASSWORD = "Password";
    public static final String NETWORK_PARA_USERTYPE = "UserType";
    public static final String NETWORK_PARA_USER_ID = "UserId";
    public static final String NETWORK_PARA_USER_TOKEN = "Token";
    public static final String NETWORK_PARA_ERROR_CODE = "ErrorCode";
    public static final String NETWORK_PARA_ERROR_MESSAGE = "ErrorMessage";
    public static final String NETWORK_PARA_RESULT_RESPONSE = "ResultResponse";
    public static final String NETWORK_PARA_NICKNAME = "NickName";
    public static final String NETWORK_PARA_GENDER = "Gender";
    public static final String NETWORK_PARA_BIRTHDAY = "Birth";
    public static final String NETWORK_PARA_HEAD = "Head";

    public static final String NETWORK_PARA_CONTENT = "Content";
    public static final String NETWORK_PARA_QUESTION_ID = "QuestionId";
    public static final String NETWORK_PARA_DOCTOR_ID = "DoctorId";
    public static final String NETWORK_PARA_PATIENT_NAME = "PatientName";
    public static final String NETWORK_PARA_PATIENT_AGE = "PatientAge";
    public static final String NETWORK_PARA_PATIENT_GENDER = "PatientGender";
    public static final String NETWORK_PARA_STATUS = "Status";
    public static final String NETWORK_PARA_CREATE_TIME = "CreateTime";
    public static final String NETWORK_PARA_DECSCIPTION = "Description";
    public static final String NETWORK_PARA_DEPARTMENT_ID = "DepartmentId";
    public static final String NETWORK_PARA_SUBMITTER_ID = "SubmitterId";
    public static final String NETWORK_PARA_HOME_IMAGE = "HomeImagePath";
    public static final String NETWORK_PARA_CD_PROMPT = "ConditionDescribePrompt";
    public static final String NETWORK_PARA_OP_PROMPT = "OpenPrescriptionPrompt";
    public static final String NETWORK_PARA_PAY_TEST = "PayTest";
    public static final String NETWORK_PARA_SERVICE_AGREEMENT = "ServiceAgreement";
    public static final String NETWORK_PARA_PRODUCT_INFO = "ProductIntro";


    //友盟自定义key 格式: UMENG_EVENT_xxxx
    public static final String UMENG_EVENT_PAYTEST = "PAY_TEST";


    //REQUESTCODE
    public static final int REQUESTCODE_LOGIN = 10;

//    public static final String INTENT_IS_SELF = "INTENT_IS_SELF";
    public static final String INTENT_USER_INFO = "INTENT_USER";
    public static final String INTENT_USER_TYPE = "INTENT_USER_TYPE";
    public static final String INTENT_ADDRESS = "INTENT_ADDRESS";
    public static final String INTENT_ADDRESS_FORESULT = "INTENT_ADDRESS_FORESULT";
    public static final String INTENT_ADDRESS_NOT_JUMP = "INTENT_ADDRESS_NOT_JUMP";
    public static final String INTENT_ADDRESS_QUESTIONLIST = "INTENT_ADDRESS_QUESTIONLIST";
    public static final String INTENT_ADDRESS_QUESTIONEDIT = "INTENT_ADDRESS_QUESTIONEDIT";
    public static final String INTENT_KEY_UNREADQUANTITY = "INTENT_KEY_UNREADQUANTITY";

    public static final String INTENT_KEY_ACCOUNT = "INTENT_KEY_ACCOUNT";


    public static final String INTENT_KEY_ISFROMPUSH = "INTENT_KEY_ISFROMPUSH";
    public static final String INTENT_KEY_ISSINGLEPUSH = "INTENT_KEY_ISSINGLEPUSH";
    public static final String INTENT_KEY_PUSHMESSAGEID = "INTENT_KEY_PUSHMESSAGEID";
    public static final String INTENT_KEY_QUESTIONID = "INTENT_KEY_QUESTIONID";
    public static final String INTENT_KEY_FORUM = "INTENT_KEY_FORUM";
    public static final String INTENT_KEY_LOCALITEMS = "INTENT_KEY_LOCALITEMS";
    public static final String INTENT_KEY_IMGINFOS = "INTENT_KEY_IMGINFOS";
    public static final String INTENT_KEY_POSTS = "INTENT_KEY_POSTS";
    public static final String INTENT_KEY_CHOOSEIMG_TARGETCLASS="INTENT_KEY_CHOOSEIMG_TARGETCLASS";
    public static final String INTENT_KEY_CHOOSEIMG_COUNT="INTENT_KEY_CHOOSEIMG_COUNT";
    public static final String INTENT_KEY_REGISTERPARAM="INTENT_KEY_REGISTERPARAM";
    public static final String INTENT_KEY_USERNAME="INTENT_KEY_USERNAME";

    public static final String INTENT_KEY_MAINTABINDEX="INTENT_KEY_MAINTABINDEX";


    public final static String INTENT_KEY_VIEW_IMAGE_PATH_INDEX = "INTENT_KEY_VIEW_IMAGE_PATH_INDEX";


    public static final String INTENT_KEY_IMAGEDIR = "INTENT_KEY_IMAGEDIR";






    public static final String ACTION_NEW_MESSAGE = "ACTION_NEW_MESSAGE";


    public interface SubUserType{

        public int SELF = 0;
    }


}
