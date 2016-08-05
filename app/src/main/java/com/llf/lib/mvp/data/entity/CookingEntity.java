package com.llf.lib.mvp.data.entity;

import java.util.List;

/**
 * Created by llf on 2016/8/2.
 * 秘制红烧肉所对应的bean
 */
public class CookingEntity {
        public DataBean[] data;

        public class DataBean {
            public String id;
            public String title;
            public String tags;
            public String imtro;
            public String ingredients;
            public String burden;
            public List<String> albums;
            public List<Stemp> steps;

            public class Stemp{
                public String img;
                public String step;
            }
    }
}
