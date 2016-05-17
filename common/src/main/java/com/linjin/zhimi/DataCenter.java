package com.linjin.zhimi;

import android.content.Context;
import android.text.TextUtils;

import com.zhimi.model.ConstantsModel;
import com.zhimi.model.account.User;
import com.tinggu.common.utils.LogUtils;
import com.cyou.quick.QuickApplication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataCenter {
    public static final String USER_FILE_NAME = "user.dat";
    public static final String CONSTANTS_FILE_NAME = "constants.dat";
    private static DataCenter dataCenter;

    private boolean tokenTimeout;

    private User user;
    private ConstantsModel constants;

    public static DataCenter getInstance() {
        if (dataCenter == null) {
            synchronized (DataCenter.class) {
                if (dataCenter == null) {
                    dataCenter = new DataCenter();
                }
            }
        }
        return dataCenter;
    }

    private DataCenter() {
        reset();
    }

    private void reset() {
        tokenTimeout = false;
    }

    public void setTokenTimeout() {
        tokenTimeout = true;
    }

    public boolean isTokenTimeout() {
        return tokenTimeout;
    }

    private static long serverTime = System.currentTimeMillis();

    public String getServerTime() {
        return Long.toString(serverTime);
    }

    public void setServerTime(long time) {
        serverTime = time;
    }
    
    public void init(){
        initConstants();
        initUser();
    }

    public void initUser() {
        reset();
        //读取产生异常，返回null
        Context context = QuickApplication.getInstance();
        try {
            DataInputStream dataInputStream = new DataInputStream(context.openFileInput(USER_FILE_NAME));
//            user = new User(dataInputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        user = readObject(USER_FILE_NAME);
        LogUtils.d("token", "readUser " + user);
    }

    public void saveUser(User user) {
        LogUtils.d("token", "saveUser " + user);
        Context context = QuickApplication.getInstance();
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(USER_FILE_NAME, Context.MODE_PRIVATE);
            DataOutputStream dos = new DataOutputStream(fos);
//            user.writeToFile(dos);
            this.user = user;

        } catch (Exception e) {
            e.printStackTrace();
            //这里是保存文件产生异常  
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    //fis流关闭异常  
                    e.printStackTrace();
                }
            }
        }

    }

    public void removeUser() {
        Context context = QuickApplication.getInstance();
        //实现1
        context.deleteFile(USER_FILE_NAME);
//        //实现2
//        File file = context.getFileStreamPath(USER_FILE_NAME);
//        file.deleteOnExit();
//        //实现3
//        File file = new File(context.getFilesDir(), USER_FILE_NAME);
//        file.delete();

        this.user = null;
    }

    private <E> E readObject(String fileName) {
        Context context = QuickApplication.getInstance();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = context.openFileInput(fileName);
            ois = new ObjectInputStream(fis);
            return (E) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            //这里是读取文件产生异常  
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    //fis流关闭异常  
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    //ois流关闭异常  
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private void saveObject(String fileName, User user) {
        Context context = QuickApplication.getInstance();
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(user);
        } catch (Exception e) {
            e.printStackTrace();
            //这里是保存文件产生异常  
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    //fos流关闭异常  
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    //oos流关闭异常  
                    e.printStackTrace();
                }
            }
        }
    }

//    public void saveUserMobile(final String mobileNum) {
//        Hawk.put(Constants.HAWK_KEY_MOBILE, mobileNum);
//        mobilephone = mobileNum;
//        LogUtils.d("mobilephone", "saveUserMobile mobileNum " + mobileNum.toString());
//    }


    public String getUserID() {
        if (user == null || TextUtils.isEmpty(user.getUid()))
            return "-1";
        return user.getUid();
    }

    public String getUserToken() {
//        if (user == null || TextUtils.isEmpty(user.getToken()))
//            return "";
//        return user.getToken();
        return "";
    }

    public boolean hasDefaultCard() {
        if (user == null) {
            return false;
        }
        return 0 != user.getIsIdentity();
    }

    public void setHasDefaultCard() {
        user.setIsIdentity((byte) 1);
        saveUser(user);
        LogUtils.d("checkDefaultCardIsExist", "setHasDefaultCard be" + true);
    }

    public boolean hasUser() {
        return user != null;
    }

    public boolean isFirstIn() {
        return true;
    }

    public void saveFirstIn() { 
    }

    public void initConstants() {
      
        //读取产生异常，返回null
        Context context = QuickApplication.getInstance();
        try {
            DataInputStream dataInputStream = new DataInputStream(context.openFileInput(CONSTANTS_FILE_NAME));
            constants = new ConstantsModel(dataInputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        user = readObject(USER_FILE_NAME);
        LogUtils.d("token", "initConstants " + constants);
    }
    
    public void saveConstants(ConstantsModel constants){
        LogUtils.d("token", "saveConstants " + constants);
        Context context = QuickApplication.getInstance();
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(CONSTANTS_FILE_NAME, Context.MODE_PRIVATE);
            DataOutputStream dos = new DataOutputStream(fos);
            constants.writeToFile(dos);
            this.constants = constants;

        } catch (Exception e) {
            e.printStackTrace();
            //这里是保存文件产生异常  
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    //fis流关闭异常  
                    e.printStackTrace();
                }
            }
        }
        LogUtils.d("token", "saveConstants " + constants);
    }
    
    public String getPrefixImgUrl(){
        if(constants == null){
            return "";
        }
       return constants.getPrefixImgUrl();
    }
}
