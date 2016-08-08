package com.okhttppractices.wanyt.network.responsemodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2016/8/3 17:14
 * <p>
 * author wanyt
 * <p>
 * Description:
 */
public class Menu {

    public String resultcode;//请求结果的返回值
    public String reason;//是否成功
    public ResultBean result;
    public int error_code;//错误码

    public static class ResultBean {
        public String totalNum;//当前搜索结果的总数
        public String pn;
        public String rn;
        public ArrayList<Cook> data;

        public static class Cook {
            public String id;
            public String title;//菜名
            public String tags;//事宜人群
            public String imtro;
            public String ingredients;//配料
            public String burden;//佐料
            public List<String> albums;//图片
            public List<StepsBean> steps;
            public static class StepsBean implements Parcelable {
                public String img;//图片
                public String step;//具体做法

                protected StepsBean(Parcel in) {
                    img = in.readString();
                    step = in.readString();
                }

                public static final Creator<StepsBean> CREATOR = new Creator<StepsBean>() {
                    @Override
                    public StepsBean createFromParcel(Parcel in) {
                        return new StepsBean(in);
                    }

                    @Override
                    public StepsBean[] newArray(int size) {
                        return new StepsBean[size];
                    }
                };

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel parcel, int i) {
                    parcel.writeString(img);
                    parcel.writeString(step);
                }
            }
        }
    }
}
