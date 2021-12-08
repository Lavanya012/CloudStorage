package com.udacity.jwdnd.course1.cloudstorage.constant;

public final class ConstantMsgs {
    private ConstantMsgs() {
    }

    //signup messages
    public static final String SIGNUP_ERR = "Sign Failed, Please try again later";
    public static final String SIGNUP_USER_EXISTING_ERR ="User name exists, Please try a different user name";
    public static final String SIGNUP_SUCCESS ="Sign up is successful! Welcome ";

    public static final String CREDENTIAL_INVALIDSESSION_ERR="Invalid session for credential operation";
    public static final String CREDENTIAL_CREATE_ERR="Creating credential failed, please try later";
    public static final String CREDENTIAL_UPDATE_ERR="Updating credential failed, please try later";
    public static final String CREDENTIAL_DELETE_ERR="Deleting credential failed, please try later";
    public static final String CREDENTIAL_CREATE_SUCCESS="Creating credential is successful";
    public static final String CREDENTIAL_UPDATE_SUCCESS="Updating credential is successful";
    public static final String CREDENTIAL_DELETE_SUCCESS="Deleting credential is successful";

    public static final String NOTE_ERR_INVALIDSESSION="Invalid session for credential";
    public static final String NOTE_ERR_CREATION_FAILURE="Creating Note failed, please try later";
    public static final String NOTE_ERR_UPDATE_FAILURE="Updating Note failed, please try later";
    public static final String NOTE_DELETE_ERR="Deleting Note failed, please try later";
    public static final String NOTE_NEW_SUCCESS="New note added successfully";
    public static final String NOTE_EDIT_SUCCESS="Note edit was successful";
    public static final String NOTE_DELETE_SUCCESS="Note deleted successfully";

    public static final String FILE_UPLOAD_SUCCESS="File successfully uploaded";
    public static final String FILE_UNKNOWN_ERR="Unexpected exception";
    public static final String FILE_NOT_SELECTED_ERR="Please select a file before upload";
    public static final String FILE_DELETE_FAILURE="Deleting File failed, please try later";
    public static final String FILE_DELETE_SUCCESS="Deleting File is successful";
    public static final String FILE_DUPLICATE_ERR="Duplicated file with the same file name";
    public static final String FILE_SIZE_LIMIT_EXCEED="ERROR: File size exceeds the limit";
}
